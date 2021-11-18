(ns com.cdeszaq.advent.twenty15)

; Day 1, Part 1
(defn floor-differential
  [{up "("
    down ")"
    :or {up 0 down 0}}]
  (- up down))

(defn elevator-wayfind
  [directions]
  (->> directions
       (group-by identity)
       (reduce-kv #(assoc %1 %2 (count %3)) {})
       (floor-differential)))

; Day 1, Part 2
(defn running-floor
  [directions]
  (->> directions
       (map {"(" 1 ")" -1})
       (reductions +)))

(defn first-basement
  [directions]
  (->> directions
       (running-floor)
       (keep-indexed #(when (neg? %2) %1))
       (first)))

; Day 1, Part 1 Revised
(defn elevator-wayfind2
  [directions]
  (->> directions
       (running-floor)
       (last)))