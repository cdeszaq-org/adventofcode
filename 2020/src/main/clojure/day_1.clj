(ns day-1
  (:require [clojure.math.combinatorics :refer :all]
            [util :refer :all]))

(defn sum-to-2020?
  "Do the numbers sum to 2020?"
  [numbers]
  (= 2020 (reduce + numbers)))

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
  [n entries]
  (->> entries
       ((flip combinations) n)                              ; Get all n-element combinations
       (filter sum-to-2020?)                                ; Filter to combinations that sum to 2020
       first                                                ; Grab the first combination
       (reduce *)))                                         ; Get the product of the combination

(defn solve-puzzle
  [n]
  (->> "day_1"                                              ; Problem input
       deserialize
       (solve n)))

(defn -main
  "Print the solution in a human-friendly way"
  []
  (println "Day 1, puzzle 1:" (solve-puzzle 2))
  (println "Day 1, puzzle 2:" (solve-puzzle 3)))
