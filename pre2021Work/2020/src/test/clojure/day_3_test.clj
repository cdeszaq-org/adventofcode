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

(deftest example-is-correct-for-pair
  (is (= 2 (solve 1 1 example-input)))
  (is (= 7 (solve 3 1 example-input)))
  (is (= 3 (solve 5 1 example-input)))
  (is (= 4 (solve 7 1 example-input)))
  (is (= 2 (solve 1 2 example-input))))
