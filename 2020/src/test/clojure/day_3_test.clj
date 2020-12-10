(ns day-3-test
  (:require [clojure.test :refer :all]
            [day-3 :refer :all]))

(def example-input "..##.......
#...#...#..
.#....#..#.
..#.#...#.#
.#...##..#.
..#.##.....
.#.#.#....#
.#........#
#.##...#...
#...##....#
.#..#...#.#
")

(def example-input2 "#23456789
abc#efghi
jklmno#qr
#tuvwxyz0
ABC#EFGHI
JKLMNO#QR
#TUVWXYZ~
!@##%^&*(
")

(deftest example-is-correct-for-pair
  (is (= 7 (solve example-input))))
