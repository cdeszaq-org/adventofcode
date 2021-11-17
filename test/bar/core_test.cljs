(ns bar.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [bar.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))
