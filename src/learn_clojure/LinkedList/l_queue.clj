(ns learn-clojure.LinkedList.l-queue
  (:refer-clojure :exclude [empty?])

  (:require
   [learn-clojure.LinkedList.linked-list :as list]))

(defprotocol LQueue
  "Protocol for LQueue"
  
  (empty? 
   [queue]
   "Returns true if the queue is empty")
  
  (peek-top
   [queue]
   "Returns value at the top of the queue")
  
  (peek-bottom
   [queue]
   "Returns value at the bottom of the queue")
  
  (enqueue
   [queue val]
   "adds value to the queue")
  
  (dequeue
   [queue]
   "deletes value from the queue")
  
  (traverse
   [queue] 
   "Returns the values of queue")
  
  (queue->vector
   [queue]
   "Returns vector from queue"))

(defrecord LQueueImpl [_list]
  LQueue
  
  (empty?
    [_list]
    (list/empty? _list))
  
  (peek-top 
    [_list]
    (let [head-node (list/head _list)]
      (if (nil? head-node)
        nil
        (list/data head-node))))
  
  (peek-bottom
    [_list]
    (let [tail-node (list/tail _list)]
      (if (nil? tail-node)
        nil
        (list/data tail-node))))
  
  (dequeue
    [_list]
    (let [head-node (list/head _list)]
      (if (nil? head-node)
        (println "Queue is empty")
        (list/delete-head _list))))
  (enqueue
    [_list val]
    (let [new-node (list/node val)
          rev-list (list/reverselist _list)]
      (if (nil? new-node)
        (println "Queue is empty")
        (list/reverselist (list/prepend rev-list new-node)))))
  
  ; O(2n) operation)
  
  (traverse 
    [_list]
    (list/traverse _list))
  
  (queue->vector
    [_list]
    (list/list->vector _list)))

;;Algorithm for enqueue
;;Constraint: We can't use append
;;STEP-1 Take input i.e list and value
;;STEP-2 Define new-node to be enqueued
;;STEP-3 Reverse the list
;;STEP-4 Prepend new-node
;;STEP-5 Again reverse the list
