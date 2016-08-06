; Intersection between two sets

#(clojure.set/difference %1 (clojure.set/difference %1 %2))

#((comp set filter) %1 %2)
