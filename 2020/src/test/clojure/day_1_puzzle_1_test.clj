(ns day-1-puzzle-1-test
  (:require [clojure.test :refer :all]
            [day-1-puzzle-1 :refer :all]))

(deftest example-is-correct
  (is (= 514579 (solve [1721 ,979 ,366 ,299 ,675 ,1456]))))
