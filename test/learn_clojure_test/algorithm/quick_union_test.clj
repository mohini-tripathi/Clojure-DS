(ns learn-clojure-test.algorithm.quick-union-test
  (:require 
   [clojure.test :refer [deftest are testing]]
   [learn-clojure.Algorithms.quick-union :as quick-union]))

(deftest connected-component
  (let [val-0 0
        val-1 1
        val-2 2
        val-3 3
        val-4 4
        vector [val-0 val-1 val-2 val-3 val-4]
        size [ 1 1 1 1 1]
        obj (quick-union/connected-components val-0 val-1 val-2 val-3 val-4)]
    (testing "components"
      (are [expected got] (= expected got)
        vector (quick-union/components obj)))
    
    (testing "sizes"
      (are [expected got] (= expected got)
        size (quick-union/sizes obj)))))

(deftest QuickUnionImpl
  (let [val-0 0
        val-1 1
        val-2 2
        val-3 3
        val-4 4
        obj (quick-union/connected-components val-0 val-1 val-2 val-3 val-4)
        conn1-2 (quick-union/connect obj val-1 val-2)
        conn2-3 (quick-union/connect conn1-2 val-2 val-3)
        conn0-4 (quick-union/connect conn2-3 val-0 val-4)
        conn1-4 (quick-union/connect conn0-4 val-1 val-4)
        components2-3 [val-0 val-2 val-2 val-2 val-4]
        sizes2-3 [1 1 3 1 1]]
    
    (testing "root"
      (are [expected got] (= expected got)
        val-2 (quick-union/root conn1-2 val-1)
        val-2 (quick-union/root conn2-3 val-2)
        val-4 (quick-union/root conn0-4 val-0)
        val-2 (quick-union/root conn1-4 val-0)))
    
    (testing "connected?"
      (are [expected got] (= expected got)
        false (quick-union/connected? obj val-0 val-1)
        true (quick-union/connected? conn1-2 val-1 val-2)
        true (quick-union/connected? conn0-4 val-0 val-4)
        true (quick-union/connected? conn1-4 val-0 val-1)))
    
    (testing "connect"
      (are [expected got] (= expected got)
        true (quick-union/connected? conn1-4 2 4)
        components2-3 (quick-union/components conn2-3)
        sizes2-3 (quick-union/sizes conn2-3)))))

