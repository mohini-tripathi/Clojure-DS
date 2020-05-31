(ns learn-clojure.linked-list
  (:refer-clojure :exclude [next]))

(defprotocol Node
  (data [node] "Returns the value of the node")
  (next [node] "Returns next node in the list"))

(defrecord NodeImpl [data next]
  Node
  (data [_] data)
  (next [_] next))

(defn node
  ([data]
   (node data nil))
  ([data next]
   (->NodeImpl data next)))

(defprotocol LinkedList
  )