(ns learn-clojure-test.algorithm.quick-find-test
  (:require
   [clojure.test :refer [deftest are testing]]
   [learn-clojure.Algorithms.quick-find :as quick-find]))

(deftest quick-find
  (let [val-1 1
        val-2 2
        val-3 3
        val-4 4
        val-5 5
        ids (quick-find/connected-ids val-1 val-2 val-3 val-4 val-5)]
    
    (testing "connect"
      (are [expected got] (= expected got)
        true (quick-find/connected? (quick-find/connect ids val-1 val-2) val-1 val-2)
        false (quick-find/connected? (quick-find/connect ids val-1 val-2) val-1 val-3)))
    
    (testing "connect-II"
      (are [expected got] (= expected got)
        true (quick-find/connected?
              (quick-find/connect (quick-find/connect
               (quick-find/connect ids val-1 val-2) val-2 val-3) val-1 val-4)
              val-1 val-2)
        false (quick-find/connected?
              (quick-find/connect
               (quick-find/connect ids val-1 val-2) val-2 val-3)
              val-1 val-4)))))