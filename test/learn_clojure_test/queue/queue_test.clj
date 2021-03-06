(ns learn-clojure-test.queue.queue-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [learn-clojure.Queue.queue :as queue]))


(deftest queue-impl
  (let [q1 (queue/queue)]
    (testing "enqueue"
      (is (= 3 (queue/peekTop (queue/enqueue (queue/enqueue q1 2) 3)))))
    (testing "dequeue"
      (is (= 3 (queue/peekTop (queue/dequeue (queue/enqueue (queue/enqueue q1 2) 3))))))
    (testing "peekTop"
      (is (= 1 (queue/peekTop (queue/enqueue q1 1)))))
    (testing "peekBottom"
      (is (= 1 (queue/peekBottom (queue/enqueue (queue/enqueue q1 1) 2)))))
    (testing "s-count"
      (is (= 2 (queue/s-count (queue/enqueue (queue/enqueue q1 1) 2)))))
    (testing "empty?"
      (is (= false (queue/empty? (queue/enqueue q1 1)))))))