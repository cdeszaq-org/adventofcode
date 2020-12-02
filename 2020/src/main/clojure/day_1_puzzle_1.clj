(ns day-1-puzzle-1
  (:require [clojure.math.combinatorics :refer :all]
            [util :refer :all]))

(defn sum-to-2020?
  "Do the numbers sum to 2020?"
  [[first second]]
  (= 2020 (+ first second)))

(defn- deserialize
  "Parse the input resource into the shape the solver requires"
  [input-name]
  (->> input-name
       slurp-input-resource                                 ; Pull the input into a string
       parse-csv                                            ; Parse the file as a CSV into rows of columns
       (map first)                                          ; Extract the 1st column
       (map #(Integer/parseInt %))))                        ; Convert the values to ints

(defn solve
  "Find two entries that sum to 2020 and then multiply those two numbers together"
  [entries]
  (->> entries
       ((flip combinations) 2)                              ; Get all 2-element combinations
       (filter sum-to-2020?)                                ; Filter to pairs that sum to 2020
       first                                                ; Grab the first pair
       (reduce *)))                                         ; Get the product the pair

(defn solve-puzzle
  []
  (->> "day_1_puzzle_1"                                     ; Problem input
       deserialize
       solve))

(defn -main
  "Print the solution in a human-friendly way"
  []
  (println "Day 1, puzzle 1:" (solve-puzzle)))
