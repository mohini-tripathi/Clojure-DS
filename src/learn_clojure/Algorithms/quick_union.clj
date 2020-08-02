(ns learn-clojure.Algorithms.quick-union)

(defprotocol QuickUnion
  (connect
    [this source target])

  (connected?
    [this source target])
  
  (size
   [this item])

  (root
    [this item]))


(defrecord QuickUnionImpl [components]
  QuickUnion

  (root
    [_ item]
    (loop [curr (components item)
           i item]
      (if (= i curr)
        curr
        (recur (components curr) (inc i)))))
  ;; (size
  ;;  [_ item]
  ;;  (loop [curr (first components)
  ;;         res (rest components)
  ;;         iter 1]
  ;;    (if (nil? curr)
  ;;      iter
  ;;      (if (= curr (root components item))
  ;;      (recur (first res) (rest res) (inc iter))
  ;;      (recur (first res) (rest res) iter)))))
  
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
(connect c1 1 2)
  (defn size?
   [vec item]
   (loop [curr (first vec)
          res (rest vec)
          freq 0
          id 0]
     (if (nil? curr)
       freq
       (if (= (root vec id) (root vec item))
         (recur (first res) (rest res) (inc freq) (inc id))
         (recur (first res) (rest res) freq (inc id))))))
(size? c1 2)
