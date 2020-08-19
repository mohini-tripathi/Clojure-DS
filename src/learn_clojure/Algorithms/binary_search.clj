(ns learn-clojure.Algorithms.binary-search)

(defprotocol BinarySearch 
 (b-search 
   [this num]
   "number supposed to be search"))

(defrecord BinarySearchImpl [vec n]
  BinarySearch
(b-search
 [_ num] 
   (loop [last (- n 1)
          mid (quot last 2)]
       (if (or (= 0 mid) (= last mid))
        false
         (cond
           (= num (vec mid)) true
           (< num (vec mid)) (recur last (quot mid 2))
           (> num (vec mid)) (recur last (quot (+ mid 1 last) 2)))))))

(defn sort-vector
  [& args]
  (let [vec (into [] (sort args))
        n (count args)]
    (->BinarySearchImpl vec n)))


(def l1 (sort-vector 12 13 14 15 16 17 18 19 20))
l1
(b-search l1 11)