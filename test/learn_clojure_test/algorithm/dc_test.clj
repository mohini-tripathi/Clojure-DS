(ns learn-clojure-test.algorithm.dc-test
  (:require
   [clojure.test :refer [deftest are testing]]
   [learn-clojure.Algorithms.dc :as dc]))

(deftest DCImpl
  
(let [val-1 1
      val-2 2
      val-3 3
      comp-1 (dc/connected-components val-1 val-2 val-3)]
  (testing "connect"
    (are [expected got] (= expected got)
      true  (dc/connected? (dc/connect 
                            comp-1 val-1 val-2) val-1 val-2)
      false (dc/connected? (dc/connect 
                            comp-1 val-1 val-2) val-1 val-3)
      true (dc/connected? (dc/connect 
                           (dc/connect
                           comp-1 val-1 val-2) val-2 val-3) val-2 val-3)))))