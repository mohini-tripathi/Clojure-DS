(ns learn-clojure.linked-list-test
  (:require 
   [clojure.test :refer [deftest are testing]]
   [learn-clojure.linked-list :as list]))


(deftest test-linked-list-node
  (let [node-data-1 "foo"
        node-1 (list/node node-data-1)
        node-data-2 "bar"
        node-2 (list/node node-data-2 node-1)]
    (testing "create node from data"
      (are [expected got] (= expected got)
        node-data-1 (list/data node-1)
        nil (list/next node-1)))
    (testing "link nodes together"
      (are [expected got] (= expected got)
        node-data-2 (list/data node-2)
        node-1 (list/next node-2)
        nil (-> node-2
                (list/next)
                (list/next))))))

(deftest test-linked-list
  (let [list-1 1]
    (testing "create an empty list - linked-list"
      (are [expected got] (= expected got)
        1 1))
    (testing "add node to the last list - append"
      (are [expected got] (= expected got)
        1 1))
    (testing "add node in start of th elist - prepend"
      (are [expected got] (= expected got)
        1 1))
    (testing "delete node in the list by value  - delete"
      (are [expected got] (= expected got)
        1 1))
    (testing "delete node from the head - delete-head"
      (are [expected got] (= expected got)
        1 1))
    (testing "delete node from the tail - delete-tail"
      (are [expected got] (= expected got)
        1 1))
    (testing "find node by value - find"
      (are [expected got] (= expected got)
        1 1))
    
    (testing "create list from a vector - vector->list"
      (are [expected got] (= expected got)
        1 1))
    
    (testing "return vector from the list - list->vector"
      (are [expected got] (= expected got)
        1 1))
    ))