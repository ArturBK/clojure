(ns forca.core
  (:gen-class))

(def total-de-vidas 6)

(defn perdeu [] (print "você perdeu"))
(defn ganhou [] (print "voce ganhou"))

(defn letras-faltantes [palavra acertos]
	(remove (fn [letra] (contains? acertos (str letra))) palavra))

(defn acertou-a-palavra-toda? [palavra acertos]
	(empty? (letras-faltantes palavra acertos))
)

(defn acertou? [chute palavra] (.contains palavra chute))

(defn le-letra! [] (read-line))

(defn imprime-forca [vidas palavra acertos]
    (println "Vidas " vidas)
    (map (fn [letra] (if (contains? acertos (str letra))
        (print letra " ") (print "_" " "))) palavra)
    (println))

 (defn imprime-forca2 [vidas palavra acertos]
    (println "Vidas " vidas)
    (doseq [letra (seq palavra)]
        (if (contains? acertos (str letra))
            (print letra " ") (print "_" " ")))
    (println))

(defn jogo [vidas palavra acertos]
    (cond
        (= vidas 0) (perdeu)
        (acertou-a-palavra-toda? palavra acertos) (ganhou)
        :else   
        (let [chute (le-letra!)]
            (if (acertou? chute palavra)
                (do
                    (println "Acertou a letra!")
                    (recur vidas palavra (conj acertos chute)))
                (do
                    (println "Errou a letra! Perdeu vida!")
                    (recur (dec vidas) palavra acertos))))))

(def carros [50000.0, 60000.0])

(->> carros 
    (map (fn [x] (* x 2))) 
    (map (fn [x] (* x 3))) 
    (reduce (fn [acc n] (+ acc n))))

(def palavra-secreta "MELANCIA")

(defn comeca-o-jogo [] (jogo total-de-vidas palavra-secreta #{}))

(defn -main [& args]
    (comeca-o-jogo))
