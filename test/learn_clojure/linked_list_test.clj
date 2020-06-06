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
        node-3 (list/node node-data-3 node-2)
        node-4 (list/node node-1)
        node-5 (list/node node-2 node-3)]
    
  (testing "node?"

      (are [expected got] (= expected got)
        true (list/node? node-3)
        false (list/node? node-data-1)))    
    
  (testing "create node from data"
    
    (are [expected got] (= expected got)
      node-data-1 (list/data node-1)
      nil (list/next node-1)
      node-data-2 (list/data node-2)
      node-1 (list/next node-2)
      node-data-3 (list/data node-3)
      node-2 (list/next node-3)))
    
    (testing "create node from node"
      (are [expected got] (= expected got)
        true (list/node? node-4)
        true (list/node? node-5)))
    
    (testing "link nodes together"
      
      (are [expected got] (= expected got)
        node-data-2 (list/data node-2)
        node-1 (list/next node-2)
        nil (list/next node-1)))))


(deftest test-linked-list
  
  (let [list-1 (list/l-list)
        node-1 (list/node 1 nil)
        node-2 (list/node 2 1)
        list-2 (list/l-list node-2 node-1)
        head-1 (list/head list-1)
        tail-1 (list/tail list-1)
        head-2 (list/head list-2)
        tail-2 (list/tail list-2)
        value 3
        p-new-node (list/node value head-2)
        a-new-node (list/node value nil)
        prepend-1 (list/prepend list-2 value)
        append-1 (list/append list-2 value)
        p-new-list (list/l-list p-new-node tail-2)]
    
    (testing "empty-linked-list"
      
    (are [expected got] (= expected got)
       nil head-1
       nil tail-1))
    
    (testing "non-empty-linked-list"
      
      (are [expected got] (= expected got)
        node-1 tail-2
        node-2 head-2))
    
    (testing "prepend"
      
      (are [expected got] (= expected got)
        p-new-list prepend-1))
    
    (testing "append"
      
      (are [expected got] (= expected got)
        a-new-node (list/tail append-1)))))