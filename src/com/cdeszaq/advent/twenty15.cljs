(ns com.cdeszaq.advent.twenty15
  (:require [cljs.spec.alpha :as s]
            [clojure.math.combinatorics :as combo]
            [clojure.string :as str]))

(comment
  ; Play w/ specs in the cljs repl
  ; Strings have to be converted to seqs for specs to treat them as a seq: (seq "my string")
  (require '[cljs.spec.alpha :as s]
           '[cljs.spec.gen.alpha :as gen]
           '[cljs.spec.test.alpha :as stest]
           '[clojure.test.check.generators]))

; Day 1
(s/def ::up nat-int?)
(s/def ::down nat-int?)
(s/def ::direction (s/or ::up #{\(} ::down #{\)})) ; TODO: Should these be different than the above defs?
(s/def ::directions (s/* ::direction))
(s/def ::floor int?)

; Part 1
(defn floor-differential
  "Find the delta between the up and down indicators from a map of indicator to count"
  [{::keys [up down] :or {up 0 down 0}}]
  (- up down))

(s/fdef floor-differential
  :args (s/keys :opt [::up ::down])
  :ret int?
  :fn (s/and #(>= (:ret %) (-> % :args :start))
             #(< (:ret %) (-> % :args :end))))

(defn parse-elevator-directions
  "Parse elevator commands from a string into a known set of commands"
  [directions]
  (let [string-seq (seq directions)
        parsed (s/conform ::directions string-seq)]
    ; Blow up unsefully on bad input
    (when (s/invalid? parsed)
      (throw (ex-info "Invalid input" (s/explain-data :directions directions))))
    ; Take just the parsing labels, since the direction is what we care about
    (map first parsed)))

(defn elevator-wayfind
  "Given a series of up/down indicators, find the floor the elevator ends on starting at 0"
  [directions]
  (->> directions
       (parse-elevator-directions)
       ; Group the ups and downs
       (group-by identity)
       ; Count how many of each there are
       (reduce-kv (fn [acc key val] (assoc acc key (count val))) {})
       ; Combine those to determine the difference in the ups vs. the downs
       (floor-differential)))

; Day 1, Part 2
(def step-sizes {::up 1 ::down -1})

(defn running-floor
  "Map the directions to a running indication of the current floor after each direction"
  [directions]
  (->> directions
       ; Convert the up/down characters to numeric floor change amounts
       (map step-sizes)
       ; Convert those to the current floor at each step as a series of reductions
       (reductions +)))

(defn first-basement
  "Determine the which direction results in entering the basement (floor < 0) first.
   The first instruction is instruction 1."
  [directions]
  (some->> directions
           (parse-elevator-directions)
           (running-floor)
           ; Convert to indicies but only keep indicies for negative values
           (keep-indexed (fn [index val] (when (neg? val) index)))
           ; We only care about the first time we go negative (enter the basement)
           (first)
           ; Floor instructions are 1-indexed, not 0-indexed
           (inc)))

; Day 1, Part 1 Revised
(defn elevator-wayfind2
  "Given a set of up/down indicators as left/right parens, find the floor the elevator
   ends on when starting at 0. Implemented from a running indication of the floors"
  [directions]
  (->> directions
       (parse-elevator-directions)
       (running-floor)
       ; We only care about the the last floor since that is the end
       (last)))

; Day 2, Part 1
(defn parse-dimensions
  "Parse x-separated dimensions into just the dimensions"
  [input]
  (->> (str/split input #"x")
       (map js/parseInt)
       (vec)))

(defn rectangle-area
  "Compute the area of a rectangle given it's two dimensions"
  [[dimension1 dimension2]]
  (* dimension1 dimension2))

(defn all-combinations
  "All the ways of taking n different elements from items"
  [n items]
  (let [indicies (range (count items))
        combinations (combo/combinations indicies n)]
    ; Map the indicies in the combinations back to their items
    (map #(map items %) combinations)))

(defn required-wrapping
  "Compute the required wrapping for a box given it's three dimensions"
  [dimensions]
  (let [planes (all-combinations 2 dimensions)
        planar-areas (map rectangle-area planes)
        surface-area (* 2 (reduce + planar-areas))
        smallest-side (reduce min planar-areas)]
    (+ surface-area smallest-side)))

(defn total-wrapping
  ""
  [many-packages]
  (->> many-packages
       (str/split-lines)
       (map parse-dimensions)
       (map required-wrapping)
       (reduce +)))

; Day 2, Part 2
(defn rectangle-circumference
  "Compute the circumference of a rectangle given it's two dimensions"
  [[dimension1 dimension2]]
  (* 2 (+ dimension1 dimension2)))

(defn required-ribbon
  ""
  [dimensions]
  (let [planes (all-combinations 2 dimensions)
        planar-circumferences (map rectangle-circumference planes)    
        smallest-circumference (reduce min planar-circumferences)
        volume (reduce * dimensions)]
    (+ smallest-circumference volume)))

(defn total-ribbon
  ""
  [many-packages]
  (->> many-packages
       (str/split-lines)
       (map parse-dimensions)
       (map required-ribbon)
       (reduce +)
       ))
