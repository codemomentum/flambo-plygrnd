(defproject
  flambo-plygrnd "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [yieldbot/flambo "0.4.0-SNAPSHOT"]
                 [org.apache.spark/spark-core_2.10 "1.1.0"]]

  ;had to be done
  :aot [flambo-plygrnd.core]
  )
