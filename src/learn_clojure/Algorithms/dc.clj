;; ---------DYNAMIC CONNECTIVITY ALGORITHM-----------
;; **************************************************


(ns learn-clojure.Algorithms.dc
  (:require 
   [clojure.set :as set]))

(defn random-objects [n]
  (let [a-set (set (take n (repeatedly #(rand-int n))))]
    (concat a-set (set/difference (set (take n (range)))
                                  a-set))))

;; (defprotocol dca
;;   "Protocol for Dynamic Connectivity Algorithm"
  
  
;;   (connect
;;    [& args]
;;    "Returns a set of inputs")
  
;;   (connected?
;;    [& args]
;;    "Returns true if all the number is in a set"))

;; (defrecord dcaImpl [& args]
;;   dca
  
;;   (connect
;;    [& args])
  
;;   (connected?
;;    [& args]))

(defn connect
  [& args]
  (let [obj (set (random-objects 10))
        new-set (set args)
        diff (set/difference obj new-set)]
    (if (= new-set (set/intersection obj new-set))
      (conj diff new-set)
      (print "object not found")
      )))


(connect 1 2 3)
