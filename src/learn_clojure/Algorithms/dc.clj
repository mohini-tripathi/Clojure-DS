;; ---------DYNAMIC CONNECTIVITY ALGORITHM-----------
;; **************************************************


(ns learn-clojure.Algorithms.dc)


(defprotocol DC
  (connect [this source target] "Connects source and target")
  (connected? [this source target] "Returns true if source and target are connected"))

(defrecord DCImpl [components]
  DC
  (connect 
    [_ source target]
    (let [new-set #{source target}
          new-list (remove (fn 
                             [item]
                             (or (= item #{source}) (= item #{target}))) components)]
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

(def c1 (connected-components 1 2 3 4 5))

(connect (connect (connect (connect c1 1 2) 3 4) 3 5) 5 4)
(connected? (connect (connect (connect (connect c1 1 2) 3 4) 3 5) 5 4) 3 4)