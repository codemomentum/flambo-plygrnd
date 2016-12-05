(ns flambo-plygrnd.core
  (:require [flambo.conf :as conf]
            [flambo.api :as f]
            [flambo.tuple :as ft]
            [flambo.sql :as sql]
            [flambo.sql-functions :as sqlf]
            [clojure.string :as s]))

(defn wc-mapper
  [line]
  (let [words (re-seq #"\w+" line)]
    (partition 2 (interleave words (take (count words) (repeatedly (fn [] 1)))))))

(comment
  (wc-mapper "this is a test")
  )


(comment
  (let [conf (-> (conf/spark-conf)
                 (conf/master "local")
                 (conf/app-name "flame_princess"))]

    (f/with-context
      sc conf
      (let [data2 (f/text-file sc "LICENSE")]
        (-> data2
            (f/flat-map (f/iterator-fn [l] (wc-mapper l)))
            (f/map-to-pair (f/fn [w] (apply ft/tuple w)))
            (f/reduce-by-key (f/fn [x y] (+ x y)))
            (f/map (f/fn [t2] (str (._1 t2) "," (._2 t2))))
            (f/save-as-text-file (str "target/counts" (rand-int 1000) ".rdd"))
            ))))

  )

(def source-path "Downloads/kaggle/train.gz")
(def source-path "Downloads/kaggle/test.gz")

(comment

  (let [conf (-> (conf/spark-conf)
                 (conf/master "local")
                 (conf/app-name "flame_princess"))]

    (f/with-context
      sc conf
      (let [scon (sql/sql-context sc)
            csv-data (sql/read-csv scon
                                   source-path
                                   :header true)
            ]
        (-> csv-data
            (.write)
            (.option "compression" "gzip")
            (.parquet "target/parquet-test")
            ;(.limit 10)
            ;(.show)
            ))))

  )



(comment

  (let [conf (-> (conf/spark-conf)
                 (conf/master "local")
                 (conf/app-name "flame_princess"))]

    (f/with-context
      sc conf
      (let [scon (sql/sql-context sc)
            csv-data (sql/read-csv scon
                                   source-path
                                   :header true)
            ]
        (-> csv-data
            (.write)
            (.option "compression" "gzip")
            (.parquet "target/parquet-train")
            ;(.limit 10)
            ;(.show)
            ))))

  )


