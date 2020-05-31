(ns learn-clojure.queue
  (:refer-clojure :exclude [push pop peek empty?]))

(defprotocol Queue
  "Queue Protocol"
  (enqueue [queue value] "Inserts the value")
  (dequeue [queue] "Removes value from end")
  (peekBottom [queue] "Peeks value of the top")
  (peekTop [queue] "Peeks value of the bottom")
  (s-count [queue] "counts the elements of queue")
  (empty? [queue] "returns true when queue is empty"))

(defrecord DefaultQueueImpl [coll]
  Queue
  (enqueue [queue value]
    (DefaultQueueImpl. (conj coll value)))
  (dequeue [queue]
    (DefaultQueueImpl. (rest coll)))
  (peekBottom [queue]
     (first coll))
  (peekTop [queue]
     (last coll))
  (s-count [queue] 
    (count coll))
  (empty? [queue]
    (= (s-count queue) 0)))


(defn queue
  []
  (DefaultQueueImpl. []))

(def q1 (queue))
(def fqueue (-> q1
                (enqueue 1)
                (enqueue 2)
                (enqueue 3)
                (enqueue 4)
                (dequeue)
                ))
(println "F-QUEUE" fqueue)
