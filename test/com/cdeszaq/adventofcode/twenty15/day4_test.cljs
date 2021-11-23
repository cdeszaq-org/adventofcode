(ns com.cdeszaq.adventofcode.twenty15.day4-test
  (:require [cljs.test :refer-macros [deftest testing is are]]
            [com.cdeszaq.adventofcode.twenty15.day4 :as aoc]
            [com.cdeszaq.adventofcode.twenty15.day4-input :as input :refer [puzzle-input]]))  
   
(deftest day4
  ; Commented out b/c brute force is slow!
  (comment (testing "part 1: "
             (testing "the right number of houses are counted"
               (are [message key]
                    (= message (aoc/lowest-message key))
                 609043 "abcdef"
                 1048970 "pqrstuv")

               (testing "puzzle input"
                 (is (= 117946 (aoc/lowest-message puzzle-input)))))))

  (comment (testing "part 2: "
             (testing "the right number of houses are counted"
               (are [houses directions]
                    (= houses (aoc/combined-house-count directions))
                 3 "^v"
                 3 "^>v<"
                 11 "^v^v^v^v^v")

               (testing "puzzle input"
                 (is (= 2341 (aoc/combined-house-count puzzle-input))))))
           ))
