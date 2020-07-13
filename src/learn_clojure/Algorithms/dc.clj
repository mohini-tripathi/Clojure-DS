;; ---------DYNAMIC CONNECTIVITY ALGORITHM-----------
;; **************************************************


(ns learn-clojure.Algorithms.dc
  (:require 
   [clojure.set :as set]))

;; (defn random-objects [n]
;;   (let [a-set (set (take n (repeatedly #(rand-int n))))]
;;     (concat a-set (set/difference (set (take n (range)))
;;                                   a-set))))

;; (defprotocol dca
;;   "Protocol for Dynamic Connectivity Algorithm"

(def n1 '(1 2 3 4 5 6 7 8 9))
  {1 2 3 4....} 
{1 2 3}
{4 5 6 7 {1 2 3} 1 2 3}
  
(defn connect
  [obj & args]
  (let [set-obj (set obj)
        set-args (set args)
        diff (set/difference set-obj set-args)]
    (if (= set-args (set/intersection set-obj set-args))
      (into '() (conj diff set-args))
      nil)))
  
;;   (connected?
;;    [& args]
;;    "Returns true if all the number is in a set"))

;; (defrecord dcaImpl [& args]
;;   dca
  
(connect n1 3 5 4)


;; (defn connect
;;   [& args]
;;   (let [obj (set (random-objects 10))
;;         new-set (set args)
;;         diff (set/difference obj new-set)]
;;     (if (= new-set (set/intersection obj new-set))
;;       (conj diff new-set)
;;       (print "object not found")
;;       )))


(connect n1 1 2 3)
