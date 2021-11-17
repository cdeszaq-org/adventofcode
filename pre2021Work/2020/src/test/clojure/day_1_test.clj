(ns day-1-test
  (:require [clojure.test :refer :all]
            [day-1 :refer :all]))

(def example-input [1721 ,979 ,366 ,299 ,675 ,1456])

(deftest example-is-correct-for-pair
  (is (= 514579 (solve 2 example-input))))

(deftest example-is-correct-for-triplet
  (is (= 241861950 (solve 3 example-input))))
