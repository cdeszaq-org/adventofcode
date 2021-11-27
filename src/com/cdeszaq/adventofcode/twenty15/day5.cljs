(ns com.cdeszaq.adventofcode.twenty15.day5
  (:require [clojure.string :as str]))

; Any of these can be implemented the same way I think?
; 1. Sliding window (step 1, size n)
; 2. Filter
; 3. ???

(defn contains-vowels
  [n s]
  (->> (seq s)
       ; Strip to just vowels
       (filter #{\a \e \i \o \u})
       ; Count the vowels
;       (distinct)
       (count)
       ; Do we have enough distinct vowels
       (<= n)))

(defn contains-sequential-letter
  "times > 0, strlen > 0"
  [times s]
  (->> (seq s)
       ; Break the string into all length-times sub-lists of characters
       (partition times 1)
       ; Count the distinct letters in each
       (map distinct)
       (map count)
       ; Do any of them have just one distinct letter
       (some #(= 1 %))))

(def forbidden-strings #{"ab", "cd", "pq", "xy"})

(defn excludes-forbidden-strings
  [s]
  (->> (seq s)
       ; Break the string into all length-2 substrings
       (partition 2 1)
       (map str/join)
       ; False if we find any forbidden strings
       (filter forbidden-strings)
       (not-any? identity)))


(def nice? (every-pred 
            #(contains-vowels 3 %) 
            #(contains-sequential-letter 2 %) 
            excludes-forbidden-strings))

(defn count-nice
  [input]
  (->> input
       (str/split-lines)
       (filter nice?)
       (count)))
