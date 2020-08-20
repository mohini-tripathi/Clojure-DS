(ns learn-clojure.Algorithms.binary-search)

(defprotocol BinarySearch 
 (b-search 
   [_ num]
   "number supposed to be searched"))

(defrecord BinarySearchImpl [vec]
  BinarySearch
(b-search
 [_ num] 
   (let [n (count vec)]
     (loop [start 0
            last (- n 1)
            mid (quot last 2)]
         (if (<= start last)
           (cond
           (= num (vec mid)) true
           (< num (vec mid)) (recur start (- mid 1) (quot (+ start last) 2))
           (> num (vec mid)) (recur (+ mid 1) last (quot (+ start last) 2)))
           false)))
 ))

(defn sort-vector
  [& args]
  (let [vec (into [] (sort args))]
    (->BinarySearchImpl vec)))


(def l1 (sort-vector 12 32 43 54 65 78 98 9 12))
l1
(b-search l1 98)