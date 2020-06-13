(ns learn-clojure.stack
  (:refer-clojure :exclude [push pop peek empty?]))

(defprotocol Stack
  "Stack Protocol"
  
  (push 
   [stack value] 
   "inserts a value in stack")
  
  (pop 
   [stack] 
   "pop the value from stack")
  
  (peek 
   [stack] 
   "returns the top calue of a stack")
  
  (s-count 
   [stack] 
   "counts element of the stack")
  
  (empty? 
   [stack] 
   "returns empty when the stack is empty"))


(defrecord StackImpl [coll]
  Stack
  
  (push 
   [stack value]
   (->StackImpl (conj coll value)))
  
  (pop 
   [stack]
   (->StackImpl (rest coll)))
  
  (peek 
   [stack]
   (first coll))
  
  (s-count 
   [stack]
   (count coll))
  
  (empty? 
   [stack]
   (= (s-count stack) 0)))


(defn stack
  []
  (->StackImpl '()))
