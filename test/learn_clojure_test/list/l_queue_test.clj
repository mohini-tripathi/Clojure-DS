(ns learn-clojure-test.list.l-queue-test
  (:require
   [clojure.test :refer [deftest are testing]]
   [learn-clojure.LinkedList.l-queue :as queue]))

(deftest queue-impl
  (let [val-1 1
        val-2 2
        val-3 3
        val-4 4
        queue-0 (queue/queue)
        queue-1 (-> queue-0
                    (queue/enqueue val-1)
                    (queue/enqueue val-2)
                    (queue/enqueue val-3)
                    (queue/enqueue val-4))]
    
    (testing "empty?"
      (are [expected got] (= expected got)
        true (queue/empty? queue-0)
        false (queue/empty? queue-1)))
    
    (testing "peek-top"
      (are [expected got] (= expected got)
        nil (queue/peek-top queue-0)
        val-4 (queue/peek-top queue-1)))
    
    (testing "peek-bottom"
      (are [expected got] (= expected got)
        nil (queue/peek-bottom queue-0)
        val-2 (queue/peek-bottom queue-1)))
    
    (testing "enqueue"
      (are [expected got] (= expected got)
        val-1 (queue/peek-bottom (queue/enqueue queue-0 val-1))
        val-1 (queue/peek-bottom(queue/enqueue queue-1 val-1))))
    
    (testing "enqueue"
       (are [expected got] (= expected got)
         val-1 (queue/peek-bottom (queue/enqueue queue-0 val-1))
         val-1 (queue/peek-bottom (queue/enqueue queue-1 val-1))))
    
     (testing "dequeue"
       (are [expected got] (= expected got)
         val-3 (queue/peek-top (queue/dequeue queue-1))))

    
    ;; (testing "traverse"
    ;;   (are [expected got] (= expected got)))
    ;; (testing "reverse"
    ;;   (are [expected got] (= expected got)))
    ;; (testing "queue->vector"
    ;;   (are [expected got] (= expected got)))
    ))