(ns com.cdeszaq.adventofcode.twenty21.day1
  (:require [clojure.string :as str]))

; Day 1, Part 1

(defn parse-depth-measurements
  [input]
  (->> (str/split-lines input)
       (map js/parseInt)
       (vec)))

(defn direction-change
  [[a b]]
  (let [delta (compare a b)]
    (cond
      (neg? delta) ::increase
      (pos? delta) ::decrease
      (zero? delta) ::unchanged)))

(defn count-depth-increases
  [depths]
  (->> depths
       ; Get the deltas
       (partition 2 1)
       (map direction-change)
       ; We only care aobut increase
       (filter #(= ::increase %))
       count))

