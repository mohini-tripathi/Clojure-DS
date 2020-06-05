(ns learn-clojure.linked-list
  (:refer-clojure :exclude [next list reverse]))

(defprotocol Node
  "Node for the list"
  (data [node] "Returns data field of the node")
  (next [node] "Returns next field of the node")
  (node-empty? [node] "Returns true when the node is empty"))

(defrecord NodeImpl [_data _next]
  Node
  (data [_] _data)
  (next [_] _next))

(defn node?
  [n]
  (satisfies? Node n))

(defn node
  ([]
   (node nil nil))
  
  ([dat]
   (node dat nil))
  
  ([dat nxt]
  (if (node? dat)
      (if (nil? (next dat))
        (->NodeImpl (data dat) nxt)  ;using 'dat' as an argument will pass a node itself where as (data dat) will pass just the values
        (->NodeImpl dat (node (next dat) nxt)))
    (->NodeImpl dat nxt))))

;; [dat nxt]
;;    (if (node? dat)
;;      (if (nil? (next dat))
;;        (->NodeImpl (data dat) nxt)
;;        (->NodeImpl (data dat) (node (next dat) nxt)))
;;      (->NodeImpl dat nxt)


(defprotocol List
  "Returns Linked List"
  (head [this] "Returns first node of the list")
  (tail [this] "Returns last node of the list")
  (prepend [list value] "Returns the new list after adding a node with value at the head of the list")
  (append [list value] "Returns the new list after adding a node with value at the tail of the list")
  (insert [list value] "Reutns the new list after inserting a node with value in between")
  (delete [list value] "Deletes the node in the list by it's value")
  (traverse [list] "Traverse through the list")
  (search [list value] "Returns true is the value is in the list")
  (delete-all [list] "Deletes the list")
  (list->vector [list] "Converts list to vectors")
  (vector->list [vector] "Converts vectors to list")
  (reverse [list] "Returns a reversed list"))

(defrecord ListImpl [_head _tail]
  List
  (head [_] _head)
  
  (tail [_] _tail)
  
  (prepend [list value]
    (let [new-node (node value (head list))]
      (->ListImpl new-node (tail list))))
  
  (append [list value]
    (let [new-node (node value nil)]
      (->ListImpl new-node (tail list)))))

(defn l-list
  ([]
   (->ListImpl nil nil))
  ([head tail]
   (->ListImpl head tail)))


;; (def l1 (l-list))
;; (def f-list (prepend l1 1))


;; (println f-list)

(def n1 (node 3))
(def n2 (node 4))
(def n3 (node n1 n2))

; 1->2 apple banana kuku 
; Write append & traversal
(node n3)
;; (data n3 )

;; (println n3)

;; (defn _prepend [value _list]
;;   (let [new-node (node value (head list))]
;;     (->ListImpl new-node (tail list))))

;; (defn _append [value _list]
;; (let [new-node (node value nil)]
;;   (->ListImpl (head list) new-node)))

;; (_prepend 1 (l-list 2 3))
;; (_append 3 (l-list 1 2))

