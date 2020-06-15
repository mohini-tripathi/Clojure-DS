(ns learn-clojure-test.stack.stack-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [learn-clojure.stack :as stack]))

(deftest sample-test
  (testing "abs"
    (is (= (+ 2 3) 5))))


(deftest stack-impl
  (let [st1 (stack/stack)]
    (testing "push"
      (is (= 3 (stack/peek (stack/push (stack/push st1 2) 3)))))
    (testing "pop"
      (is (= 2 (stack/peek (stack/pop (stack/push (stack/push st1 2) 3))))))
    (testing "peek"
      (is (= 1 (stack/peek (stack/push st1 1)))))
    (testing "s-count"
      (is (= 2 (stack/s-count (stack/push (stack/push st1 1) 2)))))
    (testing "empty?"
      (is (= false (stack/empty? (stack/push st1 1)))))))