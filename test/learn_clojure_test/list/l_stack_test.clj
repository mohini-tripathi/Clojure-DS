(ns learn-clojure-test.list.l-stack-test
  (:require
   [clojure.test :refer [deftest are testing]]
   [learn-clojure.LinkedList.l-stack :as stack]))

(deftest stack-impl
  (let [val-1 1
        val-2 2
        val-3 3]
    
    (testing "empty?"
      (are [expected got] (= expected got)
        true (stack/empty? (stack/stack))
        false (stack/empty? (stack/stack val-1))
        false (stack/empty? (stack/stack val-1 val-2))
        false (stack/empty? (stack/stack val-1 val-2 val-3))))
    
    (testing "peek"
      (are [expected got] (= expected got)
        nil (stack/peek (stack/stack))
        val-1 (stack/peek (stack/stack val-1))
        val-2 (stack/peek (stack/stack val-2 val-1))
        val-3 (stack/peek (stack/stack val-3 val-2 val-3))))
    
    (testing "push"
      (are [expected got] (= expected got)
        val-1 (stack/peek (stack/push (stack/stack) val-1))
        val-2 (stack/peek (stack/push (stack/stack val-1) val-2))
        val-3 (stack/peek (stack/push (stack/stack val-1 val-2) val-3))))
    
    (testing "pop"
      (are [expected got] (= expected got)
        nil (stack/peek (stack/pop (stack/stack val-1)))
        val-1 (stack/peek (stack/pop (stack/stack val-2 val-1)))
        val-2 (stack/peek (stack/pop (stack/stack val-3 val-2 val-1)))))
    

    (testing "reverse"
      (are [expected got] (= expected got)
        (stack/stack val-1 val-2 val-3) (stack/reverse (stack/stack val-3 val-2 val-1))))
      
    (testing "stack->vector"
      (are [expected got] (= expected got)
        [val-1 val-2 val-3] (stack/stack->vector (stack/stack val-1 val-2 val-3))))))
  