(ns learn-clojure.linked-list-test
  (:require 
   [clojure.test :refer [deftest are testing]]
   [learn-clojure.linked-list :as list]))

(deftest test-linked-list-node
  (let [node-data-1 1
        node-1 (list/node node-data-1)
        node-data-2 2
        node-2 (list/node node-data-2 node-1)
        node-data-3 3
        node-3 (list/node node-data-3 node-2)]
  (testing "create node from data"
    (are [expected got] (= expected got)
      node-data-1 (list/data node-1)
      nil (list/next node-1)
      node-data-2 (list/data node-2)
      node-1 (list/next node-2)
      node-data-3 (list/data node-3)
      node-2 (list/next node-3)))
    (testing "link nodes together"
      (are [expected got] (= expected got)
        node-data-2 (list/data node-2)
        node-1 (list/next node-2)
        nil (list/next node-1)))))

(deftest test-linked-list
  (let [list-1 (list/empty-linked-list)
        head (list/head list-1)
        tail (list/tail list-1)]
    (testing "empty-linked-list"
    (are [expected got] (= expected got)
       nil head
       nil tail))))