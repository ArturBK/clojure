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

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
