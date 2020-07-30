(ns learn-clojure.Algorithms.quick-union)

(defprotocol QuickUnion
  (connect
    [this source target])

  (connected?
    [this source target])

  (root
    [this item]))


(defrecord QuickUnionImpl [components]
  QuickUnion

 (root
  [_ item]
  (loop [curr (components item)]
    (if (= curr item)
      curr
      (recur (components curr)))))
  
  



  (connect
    [this source target]
   
    (let [s-root (root this source)
          t-root (root this target)]
      (->QuickUnionImpl (assoc components s-root t-root))))

  (connected?
    [this source target]
    (= (root this source) (root this target))))



(defn connected-components
  [& args]
  (let [components (into [] (map (fn [item] item) args))]
    (->QuickUnionImpl components)))


(def c1 (connected-components 0 1 2 3 4))
(def c2 (connect (connect (connect c1 1 2) 2 3) 2 4))
c2

