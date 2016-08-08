
; times out
(fn parens ([n o]
  (let [lp "("
        rp ")"
        str-repeat  (fn [i s] (apply str (repeat i s)))
        ]
  (if (= n o)
    #{(apply str (repeat o rp))}
    (let [
        open        (conj (map #(str-repeat % lp) (range 1 (inc (- n o)))) "")
        closed      (conj (map #(str-repeat % rp) (range 1 (inc (- n o)))) "")
      ]
      (set (
        for [op open
             cl closed
             :let [next-n (- n (count cl))
                   next-o (+ o (- (count op) (count cl)))]
             :when (>= next-o 0)
             :when (> (+ (count op) (count cl)) 0)
             r (parens next-n next-o)
            ]
            (str op cl r)))))))
  ([n] (parens n 0)))