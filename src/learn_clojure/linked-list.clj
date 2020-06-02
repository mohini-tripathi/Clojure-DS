(ns learn-clojure.linked-list
  (:refer-clojure :exclude [next]))

(defprotocol Node
  "Node for the list"
  [data next]
  (data [node] "Returns data field of the node")
  (next [node] "Returns next field of the node"))

(defrecord NodeImpl[data next]
  "Implementation of the Node"
  (data [-] _data)
  (next [_] _next))

((defprotocol List
   "Returns linked list"
   []))