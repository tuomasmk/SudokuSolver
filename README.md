# Sudoku solver
Backtracking, dancing links ja "ihmisratkaisija" periaatteilla toimiva sudokuiden ratkaisija.

## Käyttöohjeita


Ohjelma käynnistetään ohjelman kuvakkeesta. Ensin valitaan sudokun koko File-valikosta ja tämän jälkeen joko syötetään numerot ruudukkoon tai ladataan sudoku tiedostosta (File->open).

Sopivia testitiedostoja löytyy Alphabet sudokus kansiosta.

Ohjelma sisältää ratkaisun visualisoinnin backtrack algoritmille painamalla visualize-painiketta. Tämä on suositeltavaa vain 4x4 tai 9x9 kokoisille sudokuille.

Solve-painikkeella ohjelma ratkaisee sudokun valitulla algoritmilla (oletuksena dancing links).

Algoritmia voi vaihtaa Options valikosta.

### Testaus
Testit suoritetaan komennolla

`mvn test`

Testikattavuusraportti luodaan komennolla

`mvn test jacoco:report`

### Suoritettava versio
Ohjelmasta luodaan suoritettava versio komennolla

`mvn package`

ja se ajetaan komennolla

`java -jar target\SudokuSolver-1.0-SNAPSHOT.jar`

### Checkstyle
Chekckstyle tarkstuksen voi suorittaa komennolla

`mvn checkstyle:checkstyle`

### Javadoc
JavaDoc luodaan komennolla

`mvn javadoc:javadoc`

## Dokumentteja

[Määrittelydokumentti](https://github.com/tuomasmk/SudokuSolver/blob/master/Dokumentit/m%C3%A4%C3%A4rittely.md)

[Toteutusdokumentti](https://github.com/tuomasmk/SudokuSolver/blob/master/Dokumentit/toteutus.md)

[Testausdokumentti](https://github.com/tuomasmk/SudokuSolver/blob/master/Dokumentit/testaus.md)

[Viikkoraportti 1](https://github.com/tuomasmk/SudokuSolver/blob/master/Dokumentit/viikkoraportti1.md)

[Viikkoraportti 2](https://github.com/tuomasmk/SudokuSolver/blob/master/Dokumentit/viikkoraportti2.md)

[Viikkoraportti 3](https://github.com/tuomasmk/SudokuSolver/blob/master/Dokumentit/viikkoraportti3.md)

[Viikkoraportti 4](https://github.com/tuomasmk/SudokuSolver/blob/master/Dokumentit/viikkoraportti4.md)

[Viikkoraportti 5](https://github.com/tuomasmk/SudokuSolver/blob/master/Dokumentit/viikkoraportti5.md)

[Viikkoraportti 6](https://github.com/tuomasmk/SudokuSolver/blob/master/Dokumentit/viikkoraportti6.md)

[Viikkoraportti 7](https://github.com/tuomasmk/SudokuSolver/blob/master/Dokumentit/viikkoraportti7.md)