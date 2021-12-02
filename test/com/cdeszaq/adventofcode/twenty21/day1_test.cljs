(ns com.cdeszaq.adventofcode.twenty21.day1-test
  (:require [cljs.test :refer-macros [deftest testing is are]]
            [com.cdeszaq.adventofcode.twenty21.day1 :as aoc]
            [com.cdeszaq.adventofcode.twenty21.day1-input :as input :refer [puzzle-input]]))

(deftest day1
  (testing "part 1: "
    (testing "direction changes"
      (are [direction a b]
           (= direction (aoc/direction-change [a b]))
        ::aoc/increase 1 2
        ::aoc/decrease 2 1
        ::aoc/unchanged 1 1))
    (testing "sample problem"
      (is (= 7 (aoc/count-depth-increases [199 200 208 210 200 207 240 269 260 263]))))

    (testing "problem input"
      (is (= 1121 (aoc/count-depth-increases (aoc/parse-depth-measurements puzzle-input))))))
  
  (testing "part 2: "
    (testing "sample problem"
      (is (= 5 (aoc/count-depth-increases2 [199 200 208 210 200 207 240 269 260 263]))))

    (testing "problem input"
      (is (= 1065 (aoc/count-depth-increases2 (aoc/parse-depth-measurements puzzle-input)))))))
