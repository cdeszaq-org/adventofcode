(ns com.cdeszaq.adventofcode.twenty15.day3-test
  (:require [cljs.test :refer-macros [deftest testing is are]]
            [com.cdeszaq.adventofcode.twenty15.day3 :as aoc]
            [com.cdeszaq.adventofcode.twenty15.day3-input :as input :refer [puzzle-input]]))  
   
(deftest day3
  (testing "part 1: "
    (testing "the right number of houses are counted"
      (are [houses directions]
           (= houses (aoc/house-count directions))
        2 ">"
        4 "^>v<"
        2 "^v^v^v^v^v")

      (testing "puzzle input"
        (is (= 2081 (aoc/house-count puzzle-input))))))

  (testing "part 2: "
    (testing "the right number of houses are counted"
      (are [houses directions]
           (= houses (aoc/combined-house-count directions))
        3 "^v"
        3 "^>v<"
        11 "^v^v^v^v^v")

      (testing "puzzle input"
        (is (= 2341 (aoc/combined-house-count puzzle-input)))))))
