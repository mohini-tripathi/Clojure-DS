;; ---------DYNAMIC CONNECTIVITY ALGORITHM-----------
;; **************************************************


(ns learn-clojure.Algorithms.dc
  (:require
   [clojure.set :as set]))


(defprotocol DC
  (connect [this source target] "Connects source and target")
  (connected? [this source target] "Returns true if source and target are connected"))

(defrecord DCImpl [components]
  DC
  (connect
    [_ source target]
    (let [new-set (loop [curr (first components)
                         res (rest components)
                         new-lst nil]
                    (if (nil? curr)
                      new-lst
                      (if (or (contains? curr source) (contains? curr target))
                        (recur (first res) (rest res) (set/union new-lst curr))
                        (recur (first res) (rest res) new-lst))))

          new-list (filter (fn
                             [item]
                             (empty? (set/intersection item new-set))) components)]

      (->DCImpl (conj new-list new-set))))


  (connected?
    [_ source target]
    (loop [curr (first components)
           res (rest components)]
      (if (nil? curr)
        false
        (if (and (contains? curr source) (contains? curr target))
          true
          (recur (first res) (rest res)))))))


(defn connected-components
  [& args]
  (let [components (map (fn [item] #{item}) args)]
    (->DCImpl components)))

(def c1 (connected-components 0 1 2 3 4 5))
(def c2 (connect c1 1 2))
(def c3 (connect c2 2 3))
(def c4 (connect c3 0 2))
c4
(connected? c3 1 3)

