(ns day-3
  (:require [util :refer :all]))






(defn solve [example-input]
  (->> example-input
       parse-rows
       (map cycle)
       ;(map #(nth %2 %1 nil) (iterate (partial + 3) 0))
       (map #(take 1 (drop %1 %2)) (iterate (partial + 3) 0))
       ;(take 20)
       (map first)
       (filter #(= \# %))
       count
       ))

;(defn- deserialize
;  "Parse the input resource into the shape the solver requires"
;  [input-name]
;  (->> input-name
;       slurp-input-resource                                 ; Pull the input into a string
;       parse-rows                                           ; Parse the file into rows
;       (map parse-policy)))                                 ; Map the rows to policy shapes
;
;
;
(defn solve-puzzle
  []
  (->> "day_3"                                              ; Problem input
       slurp-input-resource                                 ; Pull the input into a string
       solve))

(defn -main
  "Print the solution in a human-friendly way"
  []
  (println "Day 3, puzzle 1:" (solve-puzzle)))
