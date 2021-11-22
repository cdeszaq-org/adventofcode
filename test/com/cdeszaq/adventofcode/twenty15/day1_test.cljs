(ns com.cdeszaq.adventofcode.twenty15.day1-test
  (:require [cljs.test :refer-macros [deftest testing is are]]
            [com.cdeszaq.adventofcode.twenty15.day1 :as aoc]
            [com.cdeszaq.adventofcode.twenty15.day1-input :as input :refer [puzzle-input]]))

(deftest day1
  (testing "part 1: "
    (testing "elevator instructions go where they are supposed to"
      (are [floor directions]
           (= floor (aoc/elevator-wayfind directions))
        0 ""
        0 "(())"
        0 "()()"
        3 "((("
        3 "(()(()("
        3 "))((((("
        -1 "())"
        -1 "))("
        -3 ")))"
        -3 ")())())"))
    
    (testing "problem input"
      (is (= 280 (aoc/elevator-wayfind puzzle-input)))))
  
  (testing "part 2: "
    (testing "running floor calculates correctly"
      (are [floors directions]
           (= floors (aoc/running-floor (aoc/parse-elevator-directions directions)))
        [0] ""
        [1 2 1 0] "(())"
        [1 0 1 0] "()()"
        [1 2 3] "((("
        [1 2 1 2 3 2 3] "(()(()("
        [-1 -2 -1 0 1 2 3] "))((((("
        [1 0 -1] "())"
        [-1 -2 -1] "))("
        [-1 -2 -3] ")))"
        [-1 0 -1 -2 -1 -2 -3] ")())())"))
    
    (testing "first basement finds the correct index"
      (are [floors directions]
           (= floors (aoc/first-basement directions))
        nil ""
        nil "(())"
        nil "()()"
        nil "((("
        nil "(()(()("
        1 "))((((("
        3 "())"
        1 "))("
        1 ")))"
        1 ")())())"))
    
    (testing "problem input"
      (is (= 1797 (aoc/first-basement puzzle-input))))))
