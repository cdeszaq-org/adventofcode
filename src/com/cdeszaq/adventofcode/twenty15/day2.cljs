(ns com.cdeszaq.adventofcode.twenty15.day2
  (:require [clojure.math.combinatorics :as combo]
            [clojure.string :as str]))

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
