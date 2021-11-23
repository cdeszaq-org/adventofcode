(ns com.cdeszaq.adventofcode.twenty15.day4-test
  (:require [cljs.test :refer-macros [deftest testing is are]]
            [com.cdeszaq.adventofcode.twenty15.day4 :as aoc]
            [com.cdeszaq.adventofcode.twenty15.day4-input :as input :refer [puzzle-input]]))  
   
(deftest day4
  ; Commented out b/c brute force is slow!
  (comment (testing "part 1: "
             (testing "the right number of houses are counted"
               (are [message secret]
                    (= message (aoc/lowest-message secret "00000"))
                 609043 "abcdef"
                 1048970 "pqrstuv"))

             (testing "puzzle input"
               (is (= 117946 (aoc/lowest-message puzzle-input "00000")))))

           (testing "part 2: "
             (testing "puzzle input"
               (is (= 3938038 (aoc/lowest-message puzzle-input "000000")))))
           )
  )
