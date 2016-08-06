; Write a function that checks if a number is perfect

(letfn [(divisors [x] (filter #(= (mod x %) 0) (range 1 x)))
        (perfect? [x] (= (reduce + (divisors x)) x))]
  perfect?)

#(->>
  (range 1 %)
  (filter (comp zero? (partial mod %)))
  (reduce +)
  (= %))
