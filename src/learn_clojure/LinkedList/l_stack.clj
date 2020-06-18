(ns learn-clojure.LinkedList.l-stack
  (:refer-clojure :exclude [empty? peek Stack pop push reverse])
  
  (:require 
   [learn-clojure.LinkedList.linked-list :as list]))

(defprotocol LStack
  "protocol for LStack"
  
  (empty? 
   [stack]
    "Returns true if stack is empty")
  
  (peek 
   [stack]
    "Returns the value at top of the stack")
  
  (push 
   [stack val]
    "Add value at the top of the stack")
  
  (pop 
   [stack]
    "Removes the element from the top of the stack")
  
  (traverse
   [stack]
   "traverses the stack")
  
  (reverse 
   [stack]
    "Returns a new stack by reversing the values of the stack")
  
  (stack->vector 
   [stack]
    "Returns the vector of the stack values"))


(defrecord LStackImpl [_list]
  LStack
  
  (empty?
    [_]
    (list/empty? _list))

  (peek
    [_]
    (let [head-node (list/head _list)]
      (if (nil? head-node)
        nil
        (list/data head-node))))

  (push
    [_ val]
    (->LStackImpl (list/prepend _list val)))

  (pop
    [_]
    (->LStackImpl (list/delete-head _list)))
  
  (traverse
   [_]
   (->LStackImpl (list/traverse _list)))

  (reverse
    [_]
    (->LStackImpl (list/reverselist _list)))

  (stack->vector
    [_]
    (list/list->vector _list)))


(defn stack
  [& args]
  (->LStackImpl (apply list/linkedlist args)))