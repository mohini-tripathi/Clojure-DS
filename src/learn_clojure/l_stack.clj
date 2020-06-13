(ns learn-clojure.l-stack
  (:refer-clojure :exclude [empty? peek Stack pop push reverse])
  
  (:require 
   [learn-clojure.linked-list :as list]))

(defprotocol Stack
  (empty? [stack]
    "Returns true if stack is empty")
  (peek [stack]
    "Returns the value at top of the stack")
  (push [stack val]
    "Add value at the top of the stack")
  (pop [stack]
    "Removes the element from the top of the stack")
  (reverse [stack]
    "Returns a new stack by reversing the values of the stack")
  (stack->vector [stack]
    "Returns the vector of the stack values"))

(defrecord StackImpl [_list]
  Stack
  (empty?
    [_]
    (list/empty? _list))

  (peek
    [_]
    (let [head-node (list/head _list)]
      (if (nil? head-node)
        nil
        (list/data (list/head _list)))))

  (push
    [_ val]
    (->StackImpl (list/prepend _list val)))

  (pop
    [_]
    (->StackImpl (list/delete-head _list)))

  (reverse
    [_]
    (->StackImpl (list/reverselist _list)))

  (stack->vector
    [_]
    (list/list->vector _list)))

(defn stack
  [& args]
  (->StackImpl (apply list/linkedlist args)))