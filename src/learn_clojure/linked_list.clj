;;  //// Difference between ->Blabla and Blabla.
;;  generally '->' is used to define conversions or type casting
;;  here '->Blabla' defines a function '->Blabla' which eventually 
;;  calls  Blabla constructor.
;;  Whereas, Blabla. is responsible for directly calling 
;;  the java constructor
;;  
;;  // ProTip: it's better to use -> instead of . since it can 
;;  be passed to other functions as an arguement
(ns learn-clojure.linked-list
  (:refer-clojure :exclude [next]))

(defprotocol Node
  "Single unit of the linked list"
  (data [node] "Returns value of the node")
  (next [node] "Returns next node of the list"))


(defrecord NodeImpl [data next]
  Node
  (data [_] data)
  (next [_] next))

(defrecord List [data size])


(defn node
  ([data]
   (node data nil))
  ([data next]
   (NodeImpl. data next)))

(defn linked-list
  "Empty List"
  []
  {})

(defn append
  "adds element in front of linked list"
  [])

(defn prepend
  "adds element in the last of linked list"
  [])

(defn traverse
  "traverse the linked list"
  [])





;; (defprotocol Node
;;   (data [node] "Returns the value of the node")
;;   (next [node] "Returns next node in the list"))

;; (defrecord NodeImpl [data next] //ASK
;;   Node
;;   (data [_] data)
;;   (next [_] next))

;; (defn node
;;   ([data]
;;    (node data nil))
;;   ([data next]
;;    (NodeImpl. data next)))

;; (defprotocol LinkedList
;;   )
;;   
