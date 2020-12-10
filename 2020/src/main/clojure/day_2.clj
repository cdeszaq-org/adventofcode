(ns day-2
  (:require [util :refer :all]))

(defn valid-count?
  "Determine if the password in the policy is valid. Valid means that the letter appears between
  min and max times in the password (inclusive)"
  [{:keys [min max letter string]}]                         ; Unpack the map to validate
  (->> string
       (filter #(= letter %))                               ; Strip non-chars
       count
       (#(<= min % max))))                                  ; Is count within bounds?

(defn valid-positions?
  "Determine if the password in the policy is valid. Valid means that exactly one letter in the
  min and max positions (1-indexed) matches the policy's letter"
  [{:keys [min max letter string]}]                         ; Unpack the map to validate
  (let [first (get string (dec min))                        ; Get the letters at min and max positions (1-indexed)
        second (get string (dec max))]
    (and (not (= first second))                             ; If they are the same, invalid since more than 1 might match
         (or (= first letter)                               ; See if one of them matches
             (= second letter)))))

(defn- parse-min-max
  "Parse the min-max component of a policy into a min and max component"
  [min-max]
  (map #(Integer/parseInt %) (clojure.string/split min-max #"-")))

(defn parse-policy
  "Parse a password policy string into a policy shape. This takes something like '1-3 a: abcde'
  and converts it into a map with min, max, letter, and the password (string) to which the policy
  applies"
  [policy]
  (let [space-split (clojure.string/split policy #" ")
        dash-split (parse-min-max (first space-split))]
    {:min (first dash-split)
     :max (last dash-split)
     :letter (first (second space-split))
     :string (last space-split)}))

(defn- deserialize
  "Parse the input resource into the shape the solver requires"
  [input-name]
  (->> input-name
       slurp-input-resource                                 ; Pull the input into a string
       parse-rows                                           ; Parse the file into rows
       (map parse-policy)))                                 ; Map the rows to policy shapes

(defn solve-puzzle
  [validity]
  (->> "day_2"                                              ; Problem input
       deserialize
       (filter validity)
       count))

(defn -main
  "Print the solution in a human-friendly way"
  []
  (println "Day 2, puzzle 1:" (solve-puzzle valid-count?))
  (println "Day 2, puzzle 1:" (solve-puzzle valid-positions?)))
