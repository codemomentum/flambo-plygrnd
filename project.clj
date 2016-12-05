(defproject
  flambo-plygrnd "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [yieldbot/flambo "0.8.0"]]

  ;had to be done
  :aot [flambo-plygrnd.core]

  :profiles {:provided
             {:dependencies
              [[org.apache.spark/spark-core_2.11 "2.0.1"]
               [org.apache.spark/spark-sql_2.11 "2.0.1"]
               [org.apache.spark/spark-hive_2.11 "2.0.1"]]}}
  )
