(ns com.cdeszaq.adventofcode.twenty15.day3
  (:require [clojure.string :as str]))


; Day 3, Part 1
; ...This is the elevator problem, but in 2 dimensions... Perhaps generalize it later?
; Convert to directions
; Convert directions to grid deltas. This is the "path"
; Convert directions to running grid locations (reductions). This is the "map"
; Group by grid locations
; Count keys

(def input-to-directions {\^ ::up \> ::right \v ::down \< ::left})

(def directions-to-deltas {::up [0 1] ::right [1 0] ::down [0 -1] ::left [-1 0]})

(defn apply-delta
  ""
  [current delta]
  (map + current delta))

(defn house-visits
  ""
  [input]
  (->> input
       (map input-to-directions)
       (map directions-to-deltas)
       (reductions apply-delta [0 0])
       (group-by identity)))

(defn house-count
  ""
  [input]
  (->> input
       (house-visits)
       (keys)
       (count)))

; Day 3, Part 2
(defn group-by-kv
  "Returns a map of the elements of coll keyed by the result of f on 
   the key and value of each element. f should be a function of 2 
   arguments. The value at each key will be a vector of the 
   corresponding elements, in the order they appeared in coll. Note
   that group-by-kv is supported on vectors, where the keys will be 
   the ordinals."
  [f coll]
  (persistent!
   (reduce-kv
    (fn [ret key val]
      (let [grouping-key (f key val)
            old-grouping-val (get ret grouping-key [])
            new-grouping-val (conj old-grouping-val val)]
        (assoc! ret grouping-key new-grouping-val)))
    (transient {}) 
    coll)))

(defn combined-house-count
  ""
  [input]
  (->> input
       (vec) ; group-by-kv can't handle non-vec?
       (group-by-kv #(odd? %1))
       (vals)
       (map house-visits)
       (reduce merge)
       (keys)
       (count)
       ))
