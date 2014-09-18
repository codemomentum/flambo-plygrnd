(ns flambo-plygrnd.core
  (:require [flambo.conf :as conf]
            [flambo.api :as f]))

(def c (-> (conf/spark-conf)
           (conf/master "local")
           (conf/app-name "flame_princess")))

(def sc (f/spark-context c))

(def data (f/parallelize sc [["a" 1] ["b" 2] ["c" 3] ["d" 4] ["e" 5]]))


(def data (f/text-file sc "README.md"))


;; NOTE: we are using the flambo.api/fn not clojure.core/fn
(-> data
    (f/map (f/fn [s] (count s)))
    (f/reduce (f/fn [x y] (+ x y))))


(def data2 (f/text-file sc "LICENSE"))

(defn wc-mapper
  [line]
  (let [words (re-seq #"\w+" line)]
    (partition 2 (interleave words (take (count words) (repeatedly (fn [] 1)))))))

;(defn wc-red [k v acc] (if (nil? acc) v (+ acc v)))

(-> data2
    (f/flat-map (f/fn [s] (wc-mapper s)))
    (f/reduce-by-key (f/fn [x y] (do (println x y) (+ x y))))
    (f/collect))


