# Testausdokumentti

Testaus on suoritettu yksikkötestaamalla ja vertailemalla algoritmien nopeuksia.

## Algoritmien nopeuksien vertailua

Algoritmien nopeuksia verailtiin ratkaisemalla eri kokoisia ja vaikeuksisia sudokuita. Nopeudet on saatu ottamalla keskiarvo useammasta ratkaisusta. Pienemmillä sudokuilla 9x9 ja 16x16 (paitsi backtrack) on käytetty sadan ratkaisun keskiarvoa. Suuremmilla syötteillä tulos on yhden (backtrack) tai muutaman suorituksen (dancing links) keskiarvo. Dancing links algoritmilla yritettiin ratkaista 36x36 sudokua, mutta se ei ratkennut järjellisessä ajassa.

![Performance graph](https://github.com/tuomasmk/SudokuSolver/blob/master/Dokumentit/solver_performances.png "Performance graph")

Algoritmi       | Aika (ms) (9x9) | Aika (ms) (16x16) | Aika (ms) (25x25)
--------------- | --------------- | ----------------- | -----------------------
Ihmis + dl      | 6 * 10^-2      | 2                 | -
Ihmis + bt      | 5 * 10^-2      | 2                 | -
Backtrack       | 5 * 10^-2      | 7 * 10^6          | -
+vaihtoehto     | 8 * 10^-2      | 4 * 10^6          | -
Reference graph | 9 * 10^-2      | 10 * 10^6          | -
Dancing Links   | 5 * 10^-2      | 14                | 8,5 * 10^4

Main luokka sisältää testaukseen soveltuvia metodeja. Tällöin ohjelmaa pitää ajaa kehitysympäristöstä.

## Testaus kehitysympäristössä

Apuluokka FileReader lukee sudokuja erimuotoisista tiedostoista. Tuettuja tiedostomuotoja ovat erilaiset tekstitiedostot, joihin sudoku on talletettu:
* pilkulla erotettuna riveittäin (nollalla tai ilman ks. esimerkit)
* lukujonona

### Taulukkona riveittäin, tyhjät kohdat merkitty nollalla

Luetaan metodilla readCommaSeparated().

```
0, 1, 0, 0, 0, 4, 5, 0, 9
0, 0, 4, 9, 0, 0, 0, 0, 6
6, 0, 7, 0, 2, 3, 0, 4, 0
0, 3, 0, 0, 0, 0, 0, 0, 0
0, 6, 0, 1, 7, 5, 0, 0, 0
5, 7, 0, 8, 0, 0, 0, 0, 0
0, 8, 0, 0, 0, 7, 9, 0, 0
0, 0, 5, 3, 0, 0, 4, 6, 0
3, 0, 0, 2, 1, 6, 0, 0, 7
```

### Taulukkona riveittäin

Luetaan metodilla readCommaSeparated().

```
1, , , 2, 3, 4, , , 12, , 6, , , , 7, 
, , 8, , , , 7, , , 3, , , 9, 10, 6, 11
, 12, , , 10, , , 1, , 13, , 11, , , 14, 
3, , , 15, 2, , , 14, , , , 9, , , 12, 
13, , , , 8, , , 10, , 12, 2, , 1, 15, , 
, 11, 7, 6, , , , 16, , , , 15, , , 5, 13
, , , 10, , 5, 15, , , 4, , 8, , , 11, 
16, , , 5, 9, 12, , , 1, , , , , , 8, 
, 2, , , , , , 13, , , 12, 5, 8, , , 3
, 13, , , 15, , 3, , , 14, 8, , 16, , , 
5, 8, , , 1, , , , 2, , , , 13, 9, 15, 
, , 12, 4, , 6, 16, , 13, , , 7, , , , 5
, 3, , , 12, , , , 6, , , 4, 11, , , 16
, 7, , , 16, , 5, , 14, , , 1, , , 2, 
11, 1, 15, 9, , , 13, , , 2, , , , 14, , 
, 14, , , , 11, , 2, , , 13, 3, 5, , , 12
```

### Lukujonona

Luetaan metodilla readNumbersOnly(). Ensimmäisenä parametrina sudokussa käytettävien numeroiden määrä (normaalissa sudokussa 9).

Tai vaihtoehtoisesti samasta tiedostosta voi lukea useamman sudokun kutsumalla toistuvati metodia readMultipleNumbersOnly(), kunhan sudokut ovat omilla riveillään.

`004300209005009001070060043006002087190007400050083000600000105003508690042910300`


Lisää käyttöohjeita löytyy [readmesta](https://github.com/tuomasmk/SudokuSolver/blob/master/README.md).