(ns learn-clojure-test.list.linked-list-test
  (:require
   [clojure.test :refer [deftest are testing]]
   [learn-clojure.LinkedList.linked-list :as list]))
  
  
  (deftest node-impl
    (let [val-1 1
          val-2 2]
      
      (testing "node?"
        (are [expected got] (= expected got)
          false (list/node? val-1)
          false (list/node? (list/linkedlist val-1))
          true (list/node? (list/node))
          true (list/node? (list/node val-1))
          true (list/node? (list/node val-1 val-2))))
      
      (testing "data"
        (are [expected got] (= expected got)
          nil (list/data (list/node))
          val-1 (list/data (list/node val-1))
          val-2 (list/data (list/node val-2 val-1))))
      
      (testing "next"
        (are [expected got] (= expected got)
          val-2 (list/next (list/node val  val-2))))))
  
  
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
          list-0 (list/linkedlist)
          list-1 (list/linkedlist val-1 val-2 val-3)  ;; 1->2->3
          list-2 (list/linkedlist val-3 val-2 val-1)]  ;; 3->2->1
      
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
          val-1 (list/data (list/head (list/prepend list-1 val-1)))
          val-2 (list/data (list/head (list/prepend list-1 val-2)))
          val-1 (list/data (list/head (list/prepend list-2 val-1)))
          val-2 (list/data (list/head (list/prepend list-2 val-2)))))
      
      (testing "append"
        (are [expected got] (= expected got)
          val-1 (list/data (list/tail (list/append list-2 val-1)))
          val-2 (list/data (list/tail (list/append list-2 val-2)))
          val-1 (list/data (list/tail (list/append list-1 val-1)))
          val-2 (list/data (list/tail (list/append list-1 val-2))))) 
                
      (testing "delete-head"
        (are [expected got] (= expected got)
          val-2 (list/data (list/head (list/delete-head list-1)))
          val-3 (list/data (list/head (list/delete-head 
                                       (list/delete-head list-1))))
          val-2 (list/data (list/head (list/delete-head list-2)))
          val-1 (list/data (list/head (list/delete-head
                                       (list/delete-head list-2)))))) ;;3 times not working
      
      (testing "delete-tail"
        (are [expected got] (= expected got)
          val-2 (list/data (list/tail (list/delete-tail list-1)))
          val-1 (list/data (list/tail (list/delete-tail
                                       (list/delete-tail list-1))))
          val-2 (list/data (list/tail (list/delete-tail list-2)))
          val-3 (list/data (list/tail (list/delete-tail 
                                       (list/delete-tail list-2))))))
      
      (testing "delete-all"
        (are [expected got] (= expected got)
          true (list/empty? (list/delete-all list-1))
          true (list/empty? (list/delete-all list-2))))
      
      ;; (testing "traverse"
      ;;   (are [expected got] (= expected got)
      ;;    false (list/empty? (list/traverse list-1))))
      
      (testing "reverselist"
        (are [expected got] (= expected got)
          list-2 (list/reverselist list-1)))
      
      (testing "list->vector"
        (are [expected got] (= expected got)
          [val-1 val-2 val-3] (list/list->vector list-1)))))