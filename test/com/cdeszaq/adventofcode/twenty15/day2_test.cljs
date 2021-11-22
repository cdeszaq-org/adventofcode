(ns com.cdeszaq.adventofcode.twenty15.day2-test
  (:require [cljs.test :refer-macros [deftest testing is are]]
            [com.cdeszaq.adventofcode.twenty15.day2 :as aoc]
            [com.cdeszaq.adventofcode.twenty15.day2-input :as input :refer [puzzle-input]]))  
   
(deftest day2
  (testing "part 1: "
    (testing "just enough wrapping paper is computed"
      (are [square-feet dimensions]
           (= square-feet (aoc/total-wrapping dimensions))
        58 "2x3x4"
        43 "1x1x10")

      (testing "puzzle input"
        (is (= 1606483 (aoc/total-wrapping puzzle-input))))))

  (testing "part 2: "
    (testing "just enough ribbon is computed"
      (are [square-feet dimensions]
           (= square-feet (aoc/total-ribbon dimensions))
        34 "2x3x4"
        14 "1x1x10"))

    (testing "puzzle input"
      (is (= 3842356 (aoc/total-ribbon puzzle-input))))))
