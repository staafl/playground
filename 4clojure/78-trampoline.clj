; Reimplement Trampoline
; https://www.4clojure.com/problem/78

; solution 1
(fn [f & args]
  (first
    (drop-while
      fn?
      (reductions
        (fn [a _] (a))
        (apply f args)
        (range)))))

; solution 2
(fn [f & args]
  (let [f1 (apply f args)]
    (if (fn? f1)
      (recur f1 nil)
      f1)))