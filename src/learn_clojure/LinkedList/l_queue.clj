
(ns learn-clojure.LinkedList.l-queue
  (:refer-clojure :exclude [empty? reverse])

  (:require
   [learn-clojure.LinkedList.l-stack :as stack]))

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
  
  (reverse
   [queue]
   "Return the values in reversed form")
  
  (queue->vector
   [queue]
   "Returns vector from queue"))

(defrecord LQueueImpl [front back]
  LQueue
  
  (empty?
    [_]
    (stack/empty? front))
  
  (peek-top 
    [_]
      (if (nil? front)
        nil
        (stack/peek front)))
  
  (peek-bottom
    [_]
    (if (stack/empty? front)
      nil
      (stack/peek (stack/reverse front))))
  
  (dequeue
    [_]
    (if (stack/empty? front)
      (->LQueueImpl (stack/stack) (stack/pop (stack/reverse back)))
      (->LQueueImpl back (stack/pop front))))
  
  (enqueue
    [_ val]
    (->LQueueImpl (stack/push back val) front))
    
  (traverse 
    [_]
    (stack/traverse front))
  
  (reverse
   [_]
   (stack/reverse front))
  
  (queue->vector
    [_list]
    (stack/stack->vector front)))

(defn queue
  [& args]
  (->LQueueImpl (apply stack/stack args) (stack/stack)))


(def q1 (queue))

;;Algorithm for enqueue
;;Constraint: We can't use append
;;STEP-1 Take input i.e list and value
;;STEP-2 Define new-node to be enqueued
;;STEP-3 Reverse the list
;;STEP-4 Prepend new-node
;;STEP-5 Again reverse the list
