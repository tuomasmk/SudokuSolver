# Määrittelydokumentti
Työssä toteutetaan backtracking periaatteella toimiva sudokuiden ratkaisija. 

## Algoritmi
Toteutettava algoritmi käyttää syvyyssuuntaista hakua käydäkseen läpi eri ratkaisuvaihtoehtoja. Algoritmi sijoittaa tyhjään ruutuun seuraavan käytössä olevista numeroista. Algoritmi jatkaa sudokun täyttämistä rekursiivisesti, kunnes sudoku on täynnä tai havaitaan, että johonkin ruutuun ei ole mahdollista lisätä mitään numeroa. Mikäli havaittiin virhe, palataan edelliseen ruutuun ja muutetaan sen numeroa. Lopputuloksena on valmis sudoku tai tieto siitä, ettei annettuun sudokuun ole olemassa ratkaisua. 

Sudoku on kaksiulotteinen taulukko, joten tietorakenteena käytetään kaksiulotteista taulukkoa.

## Syötteet
Ohjelma lukee ratkaistavan sudokun tiedostosta. Sudoku on annettu riveittäin kirjaimet (A = 1, B = 2, ...) erotettuna puolipisteellä ";". Tuntemattomien numeroiden paikka jätetään tyhjäksi.

Tiedostosta luetaan sudoku kaksiulotteiseen taulukkoon.

## 9x9 sudoku
Algoritmille, joka kokeilee numeroita järjestyksessä (1, 2, 3, ...), haastavin ratkaistava sudoku olisi sellainen, jossa ratkaistavat numerot olisivat käänteisessä järjestyksessä (9, 8, 7, ...). Tällöin pitäisi käydä läpi valtava määrä vääriä ratkaisuja, ennen kuin ensimmäisessä ratkaistavassa ruudussa päästään oikeaan numeroon. Ratkaisun vaikeuteen vaikuttaa myös se, kuinka pitkälle sudokua pitää ratkoa, ennen kuin virhe ensimmäisessä ruudussa havaitaan.

Mahdollisia ratkaisuja sudokulle on noin 6,671 * 10^21 (Felgenhauer 2005). Tavallisessa 9x9 sudokussa täytyy olla vähintään 17 ruutua, joissa on vihje McGuire 2012. Tämä jättää 81 - 17 = 64 ratkaistavaa ruutua. Ratkaistavien ruutujen määrä ei kuitenkaan suoraan korreloi tehtään vaativuuden kanssa, vaan enemmän vihjeitä sisältävä sudoku voi olla algoritmille haastavampi ratkaistava, jos ratkaistavat numerot ovat järjestyneet yllä kuvatulla tavalla.

Pahimmassa tapauksessa jokaiseen ruutuun kokeiltaisiin jokaista mahdollista arvoa eli vaihtoehtoja olsi 9^(9 * 9). Yllä esitetyn perusteella 17 ruudussa on vähintään arvo, jolloin pahimman tapauksen aikavaativuus olisi 9^64.

## Aika- ja tilavaativuus

Aikavaativuus on O(n^(n * n)).

Sudokun tallettaminen vaatii sudokun kokoisen taulukon verran tilaa.

Tilavaativuus on O(n^2).

## Lähteet
Felgenhauer 2005. Felgenhauer, B., Jarvis, F. Enumerating possible sudoku grids. 2005
McGuire 2012. McGuire, G., Tungemann, B., Civario, G. There is no 16-Clue Sudoku: Solving the Sudoku Minimum Number of Clues Problem. 2012.
