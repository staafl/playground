; Find out if a set of words can form a word chain

(letfn

[
( deletions [word]
    (set (into [(subs word 0 (dec (count word)))]
        (map
            #(str (subs word 0 (- % 1)) (subs word %))
            (range 1 (count word))))))

(adjacent [w1 w2]
    (let [d1 (deletions w1)
          d2 (deletions w2)]
      (some boolean [(d1 w2) (d2 w1)
        (and
            (= (count w1) (count w2))
            (= 1 (count (filter (partial apply #(not= %1 %2)) (map vector w1 w2)))))])))

( permutations [xs]
    (if (empty? xs)
        []
    (if (empty? (rest xs))
        [[(first xs)]]
    (loop [back (first xs)
           left []
           right (vec (rest xs))
           so-far []]
        (if (empty? right)
            (into so-far (map #(conj % back) (permutations left)))
            (recur
                (first right)
                (into left [back])
                (rest right)
                (into so-far (vec (map #(conj % back) (permutations (into left right)))))))))))

( is-chain [words]
    (first
        (reduce
            (fn [[so-far last] now]
                (if-not so-far
                    [so-far now]
                    [(adjacent last now) now]))
            [true (first words)]
            (rest words))))

( word-chain [words]
    (boolean (some
        is-chain
        (permutations words))))]
    word-chain)
