(ns com.cdeszaq.adventofcode.twenty15.day5-test
  (:require [cljs.test :refer-macros [deftest testing is are]]
            [com.cdeszaq.adventofcode.twenty15.day5 :as aoc]
            [com.cdeszaq.adventofcode.twenty15.day5-input :as input :refer [puzzle-input]]))  
   
(deftest day5
  (testing "part 1: "
    (testing "contains-vowels is correct"
      (are [does n s]
           (= does (aoc/contains-vowels n s))
        true 0 ""
        true 0 "f"
        true 0 "Ffsgwf"
        true 0 " "
        true 1 "a"
        true 1 "aa"
        true 1 "e"
        true 1 "eeee"
        true 1 " a"
        true 1 "ba"
        true 1 "sdadsghj"
        true 1 " a "
        true 2 "aa"
        true 2 "ae"
        true 2 "aae"
        true 2 "aea"
        true 2 "aeaeaea"
        true 2 "adgdesghdfedfa"
        true 5 "aeiou"
        true 5 "aaeeiioouu"
        true 5 "fhafgajghehjefghijghijjoghjofhjuhjuh"
        true 10 "aaeeiioouu"))

    (testing "contains-sequential-letter is correct"
      (are [does times s]
           (= does (true? (aoc/contains-sequential-letter times s)))
        true 1 "a"
        true 1 "aa"
        false 2 "a"
        true 1 "sg"
        true 1 "sgs"
        false 2 "sgs"
        true 2 "dd"
        true 2 "ddff"
        false 3 "ddfd"
        true 3 "ddfdfff"
        true 3 "ddfdfffffff"))

    (testing "excludes-forbidden-strings is correct"
      (are [does s]
           (= does (aoc/excludes-forbidden-strings s))
        true ""
        true "a"
        true "bcssersdfweg"
        false "ab"
        false "abcd"
        false "dabd"
        false "bcsxy"))

    (testing "strings are correctly naughty and nice"
      (are [is-nice s]
           (= is-nice (aoc/nice? s))
        true "ugknbfddgicrmopn"
        true "aaa"
        false "jchzalrnumimnmhp"
        false "haegwjzuvuyypxyu"
        false "dvszwmarrgswjxmb"))

    (testing "puzzle input"
      (is (= 236 (aoc/count-nice puzzle-input))))
    )

  (comment (testing "part 2: "
             (testing "puzzle input"
               (is (= 0 (aoc/lowest-message puzzle-input "000000")))))
           ))
