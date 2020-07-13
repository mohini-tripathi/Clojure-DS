(ns learn-clojure.LinkedList.linked-list 
  (:refer-clojure :exclude [next list empty?]))


(defprotocol Node
  "Node for the list"
  (data [node] "Returns data field of the node")
  (next [node] "Returns next field of the node"))


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
        (->NodeImpl (data dat) nxt)  
        (->NodeImpl (data dat) (node (next dat) nxt)))
    (->NodeImpl dat nxt))))


(defprotocol List

  (head 
   [this] 
   "Returns first node of the list")
  
  (tail 
   [this] 
   "Returns last node of the list")
  
  (empty? 
   [list]
   "Returns true if the list is empty")
  
  (prepend 
   [list value] 
   "Returns the new list after adding a node with value at the head of the list")
  
  (append 
   [list value] 
   "Returns the new list after adding a node with value at the tail of the list")
  
  (delete-head
   [list]
   "Deletes head of the list")
  
  (delete-tail
   [list](list/traverse list-1)
   "Deletes tail of the list")
  
  (delete 
   [list value] 
   "Deletes the node in the list by it's value")
  
  (delete-all
   [list]
   "Deletes list")
  
  (traverse 
   [list] 
   "Traverse through the list")
  
  (reverselist
   [list]
   "Reverse the list")
  
  (search 
   [list value] 
   "Returns true is the value is in the list")
  
  (list->vector
   [list] 
   "Converts list to vectors")
  
  (vector->list 
   [vector] 
   "Converts vectors to list"))


(defrecord ListImpl [_head _tail]
  List
  
  (head 
   [_]
   _head)
   
  (tail 
   [_]
  _tail) 
  
  (empty? 
   [list]
   (nil? (head list)))
           
  (prepend 
   [list value]
   (let [new-node (node value (head list))]
     (if (nil? (head list))
       (->ListImpl new-node new-node)
       (->ListImpl new-node (tail list)))))

  (append
   [list val]
   (let [new-node (node val)]
     (if (nil? (head list))
       (->ListImpl new-node new-node)
       (->ListImpl (node (head list) new-node) new-node))))
  
  (delete-head
   [list]
   (let [new-head (next (head list))
         new-tail (tail list)]
     (->ListImpl new-head new-tail)))
  
  (delete-tail 
   [list]
   (let [rev-list (reverselist list)
         new-list (delete-head rev-list)
         final-list (reverselist new-list)]
     final-list))
  
  (delete-all
   [list]
   (let [new-list (->ListImpl nil nil)]
     new-list))

  (traverse 
   [list]
   (loop [new-node (head list)]
     (println (data new-node))
     (if (= (next new-node) nil)
       nil
       (recur (next new-node)))))
  
  (reverselist
   [list]
   (loop [curr (head list)
          flist (->ListImpl nil nil)]
     (if (nil? curr)
       flist
       (recur (next curr) (prepend flist (data curr))))))

  (list->vector 
   [list]
   (loop [new-node (head list)
          new-vector []]
     (if (nil? new-node)
       new-vector
       (recur (next new-node) 
              (conj new-vector 
                    (data new-node)))))))


(defn empty-list
  ([]
   (->ListImpl nil nil)))

(defn linkedlist ([& args]
(let [rev-args (reverse args)]                  
  (loop [curr (first rev-args)
         nxt (rest rev-args)
         new-lst (->ListImpl nil nil)]
    (if (nil? curr)
      new-lst
      (recur (first nxt)
             (rest nxt)
             (prepend new-lst curr)))))))

(def n1 (node 1))
(def n2 (node 2))
(def n3 (node 3))
(def f1 (linkedlist 1 2 3))

(append (append f1 n1) n3)

(defn test-me
  [obj & args]
  (set args))
(def n [1 2 3])
(test-me n 4 5 6)
