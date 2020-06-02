(ns learn-clojure.stack
  (:refer-clojure :exclude [push pop peek empty?]))

(defprotocol Stack
  "Stack Protocol"
  ;; :extend-via-metadata true  //ASK 
  (push [stack value] "inserts a value in stack")
  (pop [stack] "pop the value from stack")
  (peek [stack] "returns the top calue of a stack")
  (s-count [stack] "counts element of the stack")
  (empty? [stack] "returns empty when the stack is empty"))


(defrecord DefaultStackImpl [coll]
  Stack
  (push [stack value]
    (DefaultStackImpl. (conj coll value)))
  (pop [stack]
    (DefaultStackImpl. (rest coll)))
  (peek [stack]
    (first coll))
  (s-count [stack]
         (count coll))
  (empty? [stack]
          (= (s-count stack) 0))
  )

(defn stack
  []
  (DefaultStackImpl. '()))

(def st1 (stack))
(def fstack (-> st1
                (push 1)
                (push 2)
                (push 3)
                (push 4)
                (pop)))
;; (def fstack  (println (= (s-count (push st1 1)) 0)))
(println fstack)
