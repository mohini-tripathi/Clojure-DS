(ns learn-clojure.Algorithms.quick-union)

(defprotocol QuickUnion
  (connect
    [this source target])

  (connected?
    [this source target])

  (root
    [this item]))


(defrecord QuickUnionImpl [components sizes]
  QuickUnion

  (root
    [_ item]
    (loop [curr (components item)
           i item]
      (if (= i curr)
        curr
        (recur (components curr) (inc i)))))
  ;; PROBLEM OF COUNTING ITERATIONS HERE
  ;; Let tree be 3<-2<-1
  ;; item as 1 would return 3
  ;;  (correct)
  ;; item as 2/3 would return 2/1 (incorrect)

  (connect
    [this source target]

    (let [s-root (root this source)
          t-root (root this target)]
      (cond
        (> (sizes s-root) (sizes t-root)) (->QuickUnionImpl (assoc components s-root t-root) (assoc sizes t-root (+ (sizes t-root) (sizes s-root))))
        (< (sizes s-root) (sizes t-root)) (->QuickUnionImpl (assoc components t-root s-root) (assoc sizes s-root (+ (sizes t-root) (sizes s-root))))
        (= (sizes s-root) (sizes t-root)) (->QuickUnionImpl (assoc components s-root t-root) (assoc sizes t-root (+ (sizes t-root) (sizes s-root)))))))

  (connected?
    [this source target]
    (= (root this source) (root this target))))




(defn connected-components
  [& args]
  (let [components (into [] (map (fn [item] item) args))
        sizes (into [] (map (fn [_] 1) args))]
    (->QuickUnionImpl components sizes)))

(def c1 (connected-components 0 1 2 3 4))
(def c2 (connect c1 1 2))
(def c3 (connect c2 1 3))
c3

