(ns day-3
  (:require [util :refer :all]))

(defn travel-right
  ""
  [distance rows]
  (map #(nth %2 %1 nil)
       (iterate (partial + distance) 0)
       rows))

(defn travel-down
  ""
  [distance rows]
  (take-nth distance rows))

(defn build-forest
  ""
  [tree-map]
  (->> tree-map
       parse-rows
       (map cycle)))

(defn count-trees
  ""
  [rows]
  (->> rows
       (filter #(= \# %))
       count))

(defn solve
  [right down example-input]
  (->> example-input
       build-forest
       (travel-down down)
       (travel-right right)
       count-trees))

(defn solve-puzzle
  [right down]
  (->> "day_3"                                              ; Problem input
       slurp-input-resource                                 ; Pull the input into a string
       (solve right down)))

(defn -main
  "Print the solution in a human-friendly way"
  []
  (println "Day 3, puzzle 1 (187):" (solve-puzzle 3 1))
  (println "Day 3, puzzle 2-1-1:" (solve-puzzle 1 1))
  (println "Day 3, puzzle 2-3-1:" (solve-puzzle 3 1))
  (println "Day 3, puzzle 2-5-1:" (solve-puzzle 5 1))
  (println "Day 3, puzzle 2-7-1:" (solve-puzzle 7 1))
  (println "Day 3, puzzle 2-1-2:" (solve-puzzle 1 2)))
;; Multiply them == 4723283400
