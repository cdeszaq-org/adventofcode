(ns com.cdeszaq.adventofcode.twenty15.day4
  (:require [md5.core :as md5]
            [clojure.string :as str]))

(defn lowest-message
  ""
  [secret matching-prefix]
  (->> (range 0 10000000)
       ; Tack the integers to the end of the secred
       (map #(str secret %))
       (map md5/string->md5-hex)
       ; Keep the indexes (the integers) for the matching hashes
       (keep-indexed (fn [index val] (when (str/starts-with? val matching-prefix) index)))
       (first)))
