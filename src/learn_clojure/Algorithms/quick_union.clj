(ns lean-clojure.Algorithms.quick-union)

(defprotocol QF
  (connect [this source target])
  (connected? [this source target])
  (root [this item]))

(defrecord QFImpl [components]
  QF
  (connect
    [this source target]
    (let [s-root (root this source)
          t-root (root this target)]
      (->QFImpl (assoc components s-root t-root))))
  ; Input [1 2 3 4]
  ; output [2 2 3 4]

  (root
   [_ idx]
   (loop [iter 0
          curr (first components)
          res (rest components)]
     (if )))

  (connected?
    [this source target]
    (= (root this source) (root this target))))

(defn connected-components
  [& args]
  (let [components (into [] (map (fn [item] item) args))]
    (->QFImpl components)))


(def c1 (connected-components 0 1 2 3 4))


(connected? c1 1 2)
(connected? (connect c1 1 2) 1 2)
(root c1 0)
(connect (connect c1 0 1) 1 2)