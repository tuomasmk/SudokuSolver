# Viikkoraportti 3

Tuntimäärä: 1 + 3 + 6 + 4 = 13 h.

Lisätty testit sudokun metodeille ja ratkaisulogiikoille. Aloitettu algoritmien nopeuden vertailu, ratkaistaan 1 - 1000000 sudokua ja lasketaan ratkaisemiseen käytetty aika. Tulosten perusteella ihmisratkaisija on aavistuksen nopeampi kuin backtrack-algoritmia käyttävä ratkaisija. Tosin ihmisratkaisija ei kykene ratkaisemaan 33 annetuista sudokuista. Reference Graph -ratkaisija on selvästi hitaampi, vaikka sen pitäisi olla jopa nopeampi kuin backtrack ratkaisija (Chatterjee, 2014). Algoritmin toteutusta voinee siis edelleen hioa ja saada aikaan parempia tuloksia. Backtrack algoritmi, joka ensin selvittää kuhunkin soluun mahdolliset numerot on todella hidas. Tässä on selvästi joku virhe, koska tämän pitäisi teoreettisesti olla nopeampi kuin backtrack-algoritmi.

## Pohdittavaa
Kuuluvatko aputietorakenteet sudokulle vai ratkaisijalle. Pitäisikö jokainen ratkaisija eristää omaksi luokakseen, kun niillä ei tällä hetkellä ole yhteisiä toiminnallisuuksia. Sudokulle voisi lisätä metodin clear, joka tyhjentäisi arvatut numerot.

## 21.3.

Lisätty testit sudokua varten. 1h.

## 22.3.

Eri algoritmien nopeuden testaaminen. Käytetään korkeintaa miljoonaa sudokua ja mitataan ratkaisemiseen kuluva aika. 3h.

## 25.3.

Toteutettu Reference Graph -algoritmi (Chakraborty, 2014). 6h.

## 27.3.

Lisätty testit logiikkaa varten. Korjattu logiikkaa. 4h.

Chakraborty, R.,  Paladhi, S., Chatterjee, S., Banerjee, S. An Optimized Algorithm for Solving Combinatorial Problems using Reference Graph. IOSR Journal of Computer Engineering, 16(3), pp 1-7. 2014.

Chatterjee, S., Paladhi, S., Chakraborty, R. A Comparative Study On The Performance Characteristics Of Sudoku Solving Algorithms. IOSR Journal of Computer Engineering, 16(5), pp 69-77. 2014.