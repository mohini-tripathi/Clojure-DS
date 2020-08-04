(ns learn-clojure.Algorithms.quick-union)


(defprotocol QuickUnion
  
  (components
   [connected-components]
   "returns vector containing immediate parent of each index representing object")
  
  (sizes
   [connected-components]
   "returns sizes of each index representing an object")

  (connect
    [this source target])

  (connected?
    [this source target])

  (root
    [this item]))


(defrecord QuickUnionImpl [components sizes]
  QuickUnion
  
  (components
   [_]
    components)
  
  (sizes
   [_]
    sizes)

  (root
    [_ item]
    (loop [curr (components (components item))
           i 0]
      (if (= i curr)
        curr
        (recur (components curr) (inc i)))))

  (connect
    [this source target]

    (let [s-root (root this source)
          t-root (root this target)]
      (cond
        (> (sizes s-root) (sizes t-root)) (->QuickUnionImpl (assoc components t-root s-root) (assoc sizes s-root (+ (sizes t-root) (sizes s-root))))
        (<= (sizes s-root) (sizes t-root)) (->QuickUnionImpl (assoc components s-root t-root) (assoc sizes t-root (+ (sizes t-root) (sizes s-root)))))))

  (connected?
    [this source target]
    (= (root this source) (root this target))))


(defn connected-components
  [& args]
  (let [components (into [] (map (fn [item] item) args))
        sizes (into [] (map (fn [_] 1) args))]
    (->QuickUnionImpl components sizes)))

(def c1 (connected-components 0 1 2 3 4))
(components c1)
