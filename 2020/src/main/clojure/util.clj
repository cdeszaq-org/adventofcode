(ns util)

(defn slurp-input-resource
  "Slurp the given input from the resources directory"
  [input-name]
  (slurp (str "resources/" input-name ".input")))

(defn parse-csv
  "Parse a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(defn flip
  "Flip (reverse) the arguments on the function

  EXAMPLES:
  One argument doesn't change anything
  (:foo {:foo \"bar\"}) => \"bar\"
  ((flip :foo) {:foo \"bar\"}) => \"bar\"

  Two arguments reverses the order
  (- 1 2) => -1
  ((flip -) 1 2) => 1

  An arbitrary number of arguments are reversed in order
  (> 1 2 3 4 5) => false
  ((flip >) 1 2 3 4 5) => true
  "
  [function]
  (fn
    ([] (function))
    ([x] (function x))
    ([x y] (function y x))
    ([x y z] (function z y x))
    ([a b c d] (function d c b a))
    ([a b c d & rest]
     (->> rest
          (concat [a b c d])
          reverse
          (apply function)))))
