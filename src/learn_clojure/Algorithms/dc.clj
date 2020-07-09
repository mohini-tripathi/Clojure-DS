;; ---------DYNAMIC CONNECTIVITY ALGORITHM-----------
;; **************************************************


(ns learn-clojure.Algorithms.dc
  (:require 
   [clojure.set :as set]))

(defn random-objects [n]
  (let [a-set (set (take n (repeatedly #(rand-int n))))]
    (concat a-set (set/difference (set (take n (range)))
                                  a-set))))

(defprotocol dca
  "Protocol for Dynamic Connectivity Algorithm"
  
  
  (union
   [_]
   "Returns a set of inputs")
  
  (connnected?
   [_]
   "Returns true if all the number is in a set"))



(random-objects 10)