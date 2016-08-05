(defn deletions [word]
    (set (into [(subs word 0 (dec (count word)))]
        (map
            #(str (subs word 0 (- % 1)) (subs word %))
            (range 1 (count word))))))

(defn adjacent [w1 w2]
    (let [d1 (deletions w1)
          d2 (deletions w2)]
      (some identity [(d1 w2) (d2 w1) (some identity (clojure.set/intersection d1 d2))])))