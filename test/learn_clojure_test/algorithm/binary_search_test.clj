(ns learn-clojure-test.algorithm.binary-search-test
  (:require
   [clojure.test :refer [deftest are testing]]
   [learn-clojure.Algorithms.binary-search :as binary-search]))

(deftest sort-vector
  (let [val-1 1
        val-2 2
        val-3 3
        val-4 4
        val-5 5
        val-6 6
        sorted-vector (binary-search/sort-vector val-1 val-2 val-3 val-4 val-5 val-6)]

    (testing "sort-vector"
      (are [expected got] (= expected got)
        sorted-vector (binary-search/sort-vector val-2 val-6 val-3 val-4 val-1 val-5)))))

(deftest BinarySearch
  (let [val-1 1
        val-2 2
        val-3 3
        val-4 4
        val-5 5
        val-6 6
        val-7 7
        sorted-vector (binary-search/sort-vector val-1 val-2 val-3 val-4 val-5 val-6)]
    
    (testing "b-search"
      (are [expected got] (= expected got)
        true (binary-search/b-search sorted-vector val-2)
        false (binary-search/b-search sorted-vector val-7)))))