(fn partflat [xs] (mapcat #(if (some sequential? %) (partflat %) [%]) xs))
