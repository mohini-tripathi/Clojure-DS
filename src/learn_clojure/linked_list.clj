(ns learn-clojure.linked-list
  (:refer-clojure :exclude [next list]))

(defprotocol Node
  "Node for the list"
  (data [node] "Returns data field of the node")
  (next [node] "Returns next field of the node"))

(defrecord NodeImpl [_data _next]
  Node
  (data [_] _data)
  (next [_] _next))

(defprotocol List
  "Returns Linked List"
  (head [this] "Returns first node of the list")
  (tail [this] "Returns last node of the list")
  (prepend [list value] "Returns the new list after adding a node with value at the head of the list")
  (append [list value] "Returns the new list after adding a node with value at the tail of the list"))

(defn node
  ([]
   (->NodeImpl nil nil))
  ([data]
   (->NodeImpl data nil))
  ([data next]
   (->NodeImpl data next)))

(defrecord ListImpl [_head _tail]
  List
  (head [_] _head)
  (tail [_] _tail)
  (prepend [list value]
    (let [new-node (node value (head list))]
      (->ListImpl new-node (tail list))))
  (append [list value]
    (let [new-node (node value nil)]
      (->ListImpl (head list) new-node))))



(defn l-list 
  ([] 
  (->ListImpl nil nil))
  ([head tail]
   (->ListImpl head tail)))

(def l1 (l-list))
(def f-list (prepend l1 1))


(println f-list)

;; (defn _prepend [value _list]
;;   (let [new-node (node value (head list))]
;;     (->ListImpl new-node (tail list))))

;; (defn _append [value _list]
;; (let [new-node (node value nil)]
;;   (->ListImpl (head list) new-node)))

;; (_prepend 1 (l-list 2 3))
;; (_append 3 (l-list 1 2))

