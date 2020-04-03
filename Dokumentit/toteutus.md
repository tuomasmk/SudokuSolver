# Toteutusdokumentti

Ohjelma ratkaisee sudokun kolmella eri tavalla:
* ihmisratkaisija
* backtrack
* reference graph

## Ihmisratkaisija

Ihmisratkaisija etsii oikeita vaihtoehtoja samoilla tavoilla, joilla ihmiset ratkovat sudokuita. Ratkaisija käyttää kahta eri taktiikkaa numeroiden löytämiseen. Ensimmäisessä etsitään soluja, joihin voi sijoittaa vain yhden numeron. Toisessa etsitään rivistä (, sarakkeesta tai neliöstä) numeroita, jotka sopivat vain yhteen soluun. Sudoku ratkaistaan näitä kahta menetelmää vuorottelemalla. Tällä algoritmilla ei pystytä ratkaisemaan kaikkia sudokuita.

### TODO

Ihmisratkaisijalle voisi toteuttaa vielä kolmannen ratkaisutavan, jonka on esittänyt Crook (2009). Jokaiseen soluun sopivat numerot merkitään ylös ja rivin, sarakkeen tai neliön sisältä etsitään alijoukkoja eli numerojoukkoja, jotka sopivat vain yhtä moneen soluun kuin numeroita on yhteensä. Esimerkiksi numerot 2, 3 sopivat vain soluihin (1,1) ja (2,1). Tällöin mitään muuta numeroa ei voi sijoittaa näihin soluihin, koska toinen numeroista 2 tai 3 ei sopisi enää yhteenkään soluun rivillä, sarakkeessa tai neliössä.

## Backtrack

Algoritmi perustuu syvyyshakuun ja etsii oikeaa ratkaisua käymällä kaikki vaihtoehdot järjestyksessä läpi. Ensimmäiseen vapaaseen soluun sijoitetaan numero ja testataan rikkooko numero sudokun sääntöjä. Mikäli numero ei riko yhtään säännöistä siirrytään seuraavaan tyhjänä olevaan soluun ja sijoitetaan siihen numero. Numeron rikkoessa yhtäkin sääntöä, kokeillaan samaan soluun seuraavaa numeroa, tässä tapauksessa suuruusjärjestyksessä. Jos yhtään numero ei voida sijoittaa soluun rikkomatta sääntöjä palataan edelliseen täytettyyn soluun ja kokeillaan siihen yhtä suurempaa arvoa. Näin jatketaan kunnes sudokun viimeisessä tyhjässä solussa on kelvollinen arvo. Algoritmi pystyy ratkaisemaan kaikki (ratkaistavissa olevat) sudokut. Myös sudokut, joihin on useampia ratkaisuja. Näihin algoritmi palauttaa yhden toimivan ratkaisun. Periaatteessa on mahdollista, että annettu sudoku on mahdoton, jolloin algoritmi antaa paluuarvon epätosi.

### Vaihtoehtokirjanpinto

Backtrack algorimtin optimoitu versio, jossa uhrataan muistia laskentatehokkuuden nimissä. Tietorakenteessa pidetään kirjaa kaikista soluun mahdollisista vaihtoehdoista. Tietoa päivitetään aina kun soluun lisätään tai sieltä poistetaan arvoja (TODO). Optimointi pyrkii samalla tavalla vähentämään laskentaa kuten seuraavaksi esiteltävä reference graph -algoritmi. Erona on se, että vaihtoehtokirjanpidossa pidetään kirjaa potentiaalisista arvoista ja reference graph -algoritmissa vaihtoehdot lasketaan aina uudelleen.

## Reference graph

Algoritmi on optimoitu versio backtrack-algoritmista. Algoritmissa pidetään kirjaa soluun vaikuttavista muista soluista eli soluista samalla rivillä, samassa sarakkeessa tai samassa neliössä. Kuhunkin soluun yritetään sijoittaa vain arvoja, joita ei ole muissa siihen vaikuttavissa soluissa. Optimoinnila pyritään välttämään "turhia" kokeiluita ja nopeuttamaan laskentaa. Algoritmin ovat esittääneet Chakrabotry et al. (2014).

## Algoritmien nopeuksien vertailua

Ratkaistaan 1000000 sudokua ja lasketaan ratkaisemiseen käytetty aika. Tulosten perusteella ihmisratkaisija ja backtrack-algoritmia ovat käytännössä yhtä nopeita. "Optimoidut" backtrack algoritmit vaihtoehtokirjanpito ja Reference graph -ratkaisija ovat selvästi hitaampi, vaikka reference graph -algorimtin pitäisi olla jopa nopeampi kuin backtrack ratkaisija (Chatterjee, 2014).

Algoritmi       | Aika (ms) (10^4 sudokua) | Aika (ms) (10^5 sudokua)
--------------- | ------------------------ | -----------------------
Ihmis           | 497                      | 4580
Backtrack       | 464                      | 4644
+vaihtoehto     | 771                      | 7820
Reference graph | 971                      | 8654


## Lähteet

Chakraborty, R.,  Paladhi, S., Chatterjee, S., Banerjee, S. An Optimized Algorithm for Solving Combinatorial Problems using Reference Graph. IOSR Journal of Computer Engineering, 16(3), pp 1-7. 2014.

Chatterjee, S., Paladhi, S., Chakraborty, R. A Comparative Study On The Performance Characteristics Of Sudoku Solving Algorithms. IOSR Journal of Computer Engineering, 16(5), pp 69-77. 2014.

Crook, J. F. A Pencil-and-Paper Algorithm for Solving Sudoku Puzzles. Notices of the AMS, 2009, 56.4: 460-468. 