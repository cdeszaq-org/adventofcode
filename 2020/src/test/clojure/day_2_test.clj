(ns day-2-test
  (:require [clojure.test :refer :all]
            [day-2 :refer :all]))

(deftest examples-validate
  (is (true? (valid? {:min 1 :max 3 :letter \a :string "abcde"})))
  (is (false? (valid? {:min 1 :max 3 :letter \b :string "cdefg"})))
  (is (true? (valid? {:min 2 :max 9 :letter \c :string "ccccccccc"}))))

(deftest examples-parse
  (is (= {:min 1 :max 3 :letter \a :string "abcde"} (parse-policy "1-3 a: abcde")))
  (is (= {:min 1 :max 3 :letter \b :string "cdefg"} (parse-policy "1-3 b: cdefg")))
  (is (= {:min 2 :max 9 :letter \c :string "ccccccccc"} (parse-policy "2-9 c: ccccccccc"))))
