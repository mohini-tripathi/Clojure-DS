(ns learn-clojure.Algorithms.quick-find)

(defprotocol NDC
  (connect [this source target] "Connects source and target")
  (connected? [this source target] "Returns true if source and target are connected"))

(defrecord QuickFind [ids]
  NDC
  (connect
    [_ source target]
    (let [sId (ids source)
          tId (ids target)]
      (loop [curr (first ids)
             res (rest ids)
             new-list ids
             iter 0]
        (if (nil? curr)
          (->QuickFind new-list)
          (if (= curr sId)
            (recur (first res) (rest res) (assoc new-list iter tId) (inc iter))
            (recur (first res) (rest res) new-list (inc iter)))))))

  (connected?
    [_ source target]
    (= (ids source) (ids target))))

(defn connected-ids
  [& args]
  (let [ids (into [] (map (fn [item] item) args))]
    (->QuickFind ids)))

(def obj (connected-ids 0 1 2 3 4 5 6 7 8))



(connected? (connect (connect(connect obj 1 2) 2 3) 3 7) 1 5)