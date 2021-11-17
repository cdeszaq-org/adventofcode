(ns day-2-test
  (:require [clojure.test :refer :all]
            [day-2 :refer :all]))

(deftest examples-validate-positions
  (is (true? (valid-positions? {:min 1 :max 3 :letter \a :string "abcde"})))
  (is (false? (valid-positions? {:min 1 :max 3 :letter \b :string "cdefg"})))
  (is (false? (valid-positions? {:min 2 :max 9 :letter \c :string "ccccccccc"}))))

(deftest examples-validate-count
  (is (true? (valid-count? {:min 1 :max 3 :letter \a :string "abcde"})))
  (is (false? (valid-count? {:min 1 :max 3 :letter \b :string "cdefg"})))
  (is (true? (valid-count? {:min 2 :max 9 :letter \c :string "ccccccccc"}))))

(deftest examples-parse
  (is (= {:min 1 :max 3 :letter \a :string "abcde"} (parse-policy "1-3 a: abcde")))
  (is (= {:min 1 :max 3 :letter \b :string "cdefg"} (parse-policy "1-3 b: cdefg")))
  (is (= {:min 2 :max 9 :letter \c :string "ccccccccc"} (parse-policy "2-9 c: ccccccccc"))))
