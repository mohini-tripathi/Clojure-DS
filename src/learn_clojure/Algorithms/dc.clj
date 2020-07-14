;; ---------DYNAMIC CONNECTIVITY ALGORITHM-----------
;; **************************************************


(ns learn-clojure.Algorithms.dc)


(defprotocol DC
  (connect [this source target] "Connects source and target")
  (connected? [this source target] "Returns true if source and target are connected"))

(defrecord DCImpl [components]
  DC
  (connect 
   [_ source target])

  (connected?
    [_ source]
    (loop [curr (first components)
           res (rest components)]
      (if (nil? curr)
        false
        (if (and (contains? curr source))
          true
          (recur (first res) (rest res)))))))


(defn connected-components
  [& args]
  (let [components (map (fn [item] #{item}) args)]
    (->DCImpl components)))

(def c1 (connected-components 1 2 3 4 5))

(connected? c1 1 2)