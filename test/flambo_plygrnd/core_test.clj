(ns flambo-plygrnd.core-test
  (:require [clojure.test :refer :all]
            [flambo-plygrnd.core :refer :all]))

(deftest wc-wrapper-test
  (testing "splitting line"
    (is (= 0
           (map #(apply ft/tuple %) (wc-mapper "this is a sample text"))))))


;(require '[flambo-plygrnd.core :refer :all]
;         '[flambo.tuple :as ft])
;
;(wc-mapper "this is a sample text")


