; times out on last test

(letfn [
(ps1 [xs]
  (if (empty? xs)
    #{}
    (loop [now (first xs)
           left []
           right (vec (rest xs))
           so-far #{(set xs)}]
      (if (empty? right)
        (if (empty? left) so-far (conj so-far (set left)))
        (recur (first right)
               (into left [now])
               (rest right)
               (into so-far (ps1 (into left right))))))))]

    (fn check [& sets]
      (->> sets
        (map ps1)
        (map #(map (partial reduce +) %))
        (map set)
        (apply clojure.set/intersection)
        (empty?)
        (not)
        (boolean)
        )))