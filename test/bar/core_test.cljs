(ns bar.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [bar.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 1))))

(deftest fake-test2
  (testing "fake description"
    (is (= 3 3))))