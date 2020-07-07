(ns learn-clojure-test.list.linked-list-test
  (:require
   [clojure.test :refer [deftest are testing]]
   [learn-clojure.LinkedList.linked-list :as list]))
  
  (deftest empty-list
    (let [list-1 (list/linkedlist)]
      (testing "empty linked list"
        (are [expected got] (= expected got)
          nil (list/head list-1)
          nil (list/tail list-1)))))
  
  (deftest linkedlist
    (let [list-1 (list/linkedlist 1 2 3 4)]
      (testing "list"
        (are [expected got] (= expected got)
          1 (list/data (list/head list-1))
          4 (list/data (list/tail list-1))))))
  
  (deftest linkedlist-impl
    (let [val-1 1
          val-2 2
          val-3 3
          ;; node-1 (list/node val-1 val-2)
          ;; node-2 (list/node val-2 val-3)
          ;; node-3 (list/node val-3 nil)
          ;; node-r1 (list/node val-1 nil)
          ;; node-r2 (list/node val-2 val-1)
          ;; node-r3 (list/node val-3 val-2)
          list-0 (list/linkedlist)
          list-1 (list/linkedlist val-1 val-2 val-3)  ;; 1->2->3
          list-2 (list/linkedlist val-3 val-2 val-1)
          ]
      
      (testing "head"
        (are [expected got] (= expected got)
          nil (list/head list-0)
          val-1 (list/data (list/head list-1))
          val-3 (list/data (list/head list-2))))
      
      (testing "tail"
        (are [expected got] (= expected got)
          nil (list/tail list-0)
          val-3 (list/data (list/tail list-1))
          val-1 (list/data (list/tail list-2))))
      
      (testing "empty?"
        (are [expected got] (= expected got)
          true (list/empty? list-0)
          false (list/empty? list-1)
          false (list/empty? list-2)))
      
      (testing "prepend"
        (are [expected got] (= expected got)
          val-1 (list/data (list/head (list/prepend list-2 val-1)))))
      
      (testing "append"
                (are [expected got] (= expected got)
                  val-1 (list/data (list/tail (list/append list-2 val-1)))))
      
      ;; (testing "delete-head")
      
      ;; (testing "delete-tail")
      
      ;; (testing "delete-all")
      
      ;; (testing "traverse")
      (testing "reverselist"
        (are [expected got] (= expected got)
          list-2 (list/reverselist list-1)))
      
      ;; (testing "list->vector")
      ))