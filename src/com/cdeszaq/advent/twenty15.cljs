(ns com.cdeszaq.advent.twenty15)

; Day 1, Part 1
(defn floor-differential
  "Find the delta between the up and down indicators from a map of indicator to count"
  [{up "("
    down ")"
    :or {up 0 down 0}}]
  (- up down))

(defn elevator-wayfind
  "Given a set of up/down indicators as left/right parens, find the floor the elevator
   ends on when starting at 0"
  [directions]
  (->> directions
       ; Separate the up/down characters
       (group-by identity)
       ; Count how many of each there are
       (reduce-kv #(assoc %1 %2 (count %3)) {})
       ; Combine those to determine the difference in the ups vs. the downs
       (floor-differential)))

; Day 1, Part 2
(defn running-floor
  "Map the directions to a running indication of the current floor after each direction"
  [directions]
  (->> directions
       ; Convert the up/down characters to numeric floor change amounts
       (map {"(" 1 ")" -1})
       ; Convert those to the current floor at each step as a series of reductions
       (reductions +)))

(defn first-basement
  "Determine the which direction results in entering the basement (floor < 0) first.
   The first instruction is instruction 1."
  [directions]
  (->> directions
       (running-floor)
       (keep-indexed #(when (neg? %2) %1))
       (first)))

; Day 1, Part 1 Revised
(defn elevator-wayfind2
  "Given a set of up/down indicators as left/right parens, find the floor the elevator
   ends on when starting at 0. Implemented from a running indication of the floors"
  [directions]
  (->> directions
       (running-floor)
       (last)))