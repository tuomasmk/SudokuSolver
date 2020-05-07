# Toteutusdokumentti

Ohjelma ratkaisee sudokun kolmella eri tavalla:
* ihmisratkaisija
* backtrack
* dancing links
* (reference graph)

## Ihmisratkaisija

Ihmisratkaisija etsii oikeita vaihtoehtoja samoilla tavoilla, joilla ihmiset ratkovat sudokuita. Ratkaisija käyttää kahta eri taktiikkaa numeroiden löytämiseen. Ensimmäisessä etsitään soluja, joihin voi sijoittaa vain yhden numeron. Toisessa etsitään rivistä (, sarakkeesta tai neliöstä) numeroita, jotka sopivat vain yhteen soluun. Sudoku ratkaistaan näitä kahta menetelmää vuorottelemalla. Tällä algoritmilla ei pystytä ratkaisemaan kaikkia sudokuita.

Ihmisratkaisijaa saisi vielä parannettua käyttämällä ns. preemptive sets -menetelmää, jossa etsitään pienintä joukkoa soluja ja niihin sopivia numeroita. Tällaisen joukon löydyttyä, kyseiset numerot eivät voi esiintyä muissa rivin, sarakkeen tai neliön soluissa. Parannus ei vielä tee algoritmista kykenevää ratkaisemaan kaikkia sudokuita.

## Dancing Links

Algoritmi käyttää kehämäisiä, kahteen suuntaan linkitettyjä listoja. Algoritmin perusta on huomata kuinka solmun poistaminen:

L[R[x]] <- L[x], 	R[L[x]] <- R[x]

voidaan perua käänteisellä operaatiolla:

[L[R[x]] <- x, 		R[L[x]] <- x

Sudokusta muodostetaan ensin ykkösistä ja nollista koostuva matriisi. Matriisista muodostetaan edelleen linkitetyt listat, joide avulla etsitään ratkaisua.

(Knuth 2000).

## Backtrack

Algoritmi perustuu syvyyshakuun ja etsii oikeaa ratkaisua käymällä kaikki vaihtoehdot järjestyksessä läpi. Ensimmäiseen vapaaseen soluun sijoitetaan numero ja testataan rikkooko numero sudokun sääntöjä. Mikäli numero ei riko yhtään säännöistä siirrytään seuraavaan tyhjänä olevaan soluun ja sijoitetaan siihen numero. Numeron rikkoessa yhtäkin sääntöä, kokeillaan samaan soluun seuraavaa numeroa, tässä tapauksessa suuruusjärjestyksessä. Jos yhtään numero ei voida sijoittaa soluun rikkomatta sääntöjä palataan edelliseen täytettyyn soluun ja kokeillaan siihen yhtä suurempaa arvoa. Näin jatketaan kunnes sudokun viimeisessä tyhjässä solussa on kelvollinen arvo. Algoritmi pystyy ratkaisemaan kaikki (ratkaistavissa olevat) sudokut.

### Optimointia

Työn aikana algoritmia yritettiin optimoida erilaisilla tavoilla, joista alla esitettynä kaksi tapaa. Ensimmäinen nopeutti ratkaisua 16x16 sudokuilla noin puolella. Jälkimmäinen jäi aina hitaammaksi eli optimointi ei sen kohdalla toiminut. Jälkimmäisen algoritmin kehittäjät raportoivat hieman paremmasta suorituskyvystä kuin backtrack algoritmin kohdalla testatuilla sudokuilla.

#### Vaihtoehtokirjanpito

Backtrack algorimtin optimoitu versio, jossa uhrataan muistia laskentatehokkuuden nimissä. Kolmiulotteisessa taulukossa pidetään kirjaa jokaiseen soluun mahdollisista arvoista. Vaihtoehdot lasketaan suorituksen alkaessa, eikä niitä päivitetä ajon aikana. Arvojen pitäminen ajantasalla saattaisi nopeuttaa algoritmia entisestään.

#### Reference graph

Algoritmi on optimoitu versio backtrack-algoritmista. Algoritmissa pidetään kirjaa soluun vaikuttavista muista soluista eli soluista samalla rivillä, samassa sarakkeessa tai samassa neliössä. Kuhunkin soluun yritetään sijoittaa vain arvoja, joita ei ole muissa siihen vaikuttavissa soluissa. Optimoinnila pyritään välttämään "turhia" kokeiluita ja nopeuttamaan laskentaa. Algoritmin ovat esittäneet Chakrabotry et al. (2014).

## Aika- ja tilavaativuudet

|               | aikavaativuus  | tilavaativuus |
| ------------- | -------------- | ------------- |
| backtrack     | n^(n * n)      | n^2           |
| dancing links | n^(n * n)      | n^5           |
| human         | n^5            | n^2           |

Käytännössä Dancing links on vain vähän hitaampi kuin ihmisratkaisija ja ihmisratkaisijan aikavaativuus on pienempi kuin yllä esitetty.

### Backtrack

Algoritmin aikavaativuus riippuu ratkaistavan sudokun koosta:

O(n) = n^(n * n)

Tilavaativuus tulee sudokun tallentamiseen käytetystä taulukosta ja on:

O(n * n)

### Dancing links

Algoritmin aikavaativuus on sama kuin backtrack algoritmilla, mutta optimointien takia huonoimpaan aikavaativuuteen joudutaan hyvin harvoin.

Tilavaativuudessa merkittävä on käytetty datamatriisi: 

O((n * n * n) * (n * n * 4)) = O(n^5)

### Ihmisratkaisija

Ihmisratkaisija etsii soluja, joihin sopii vain yksi numero tai numeroja, jotka sopivat vain yhteen soluun. Tarkastellaan näiden vaativuuksia erikseen.

Ensimmäinen käy läpi soluja solu kerrallaan ja hakee kaikki soluun käyvät numerot tutkimalla mitä numeroita solun kanssa samalla rivillä, sarakkeessa tai neliössä on. Tämä vaatii 3 * (n - 1) hakua, jokaista tyhjää solua kohti eli pahimmillaan n * n * 3 * (n - 1). Jotta sudokun ratkaiseminen on mahdollsita, täytyy jokaisella kierroksella saadaan sijoitettua vähintään yksi arvo paikalleen. Tällöin joudutaan algoritmia ajamaan n * n kierrosta eli n * n * n * n * 3 * (n - 1). Aikavaativuudeksi saadaan O(n^5).

Toinen osa hakee kaikki mahdolliset paikat kullekin numerolle rivettäin, sarakkeittain tai neliöittäin. Koko ruudukon läpikäyminen menee 3 * n * n * (3 * (n - 1)) aikaa. Kuten yllä pahimmassa tapauksessa tämä joudutaan ajamaan n * n kertaa eli pahimman tapauksen aikavaativuus olisi n * n * 3 * n * n * (3 * (n -1)), jolloin aikavaativuudeksi saadaan O(n^5).

Myös algoritmien yhdistetty aikavaativuus on O(n^5).

Algoritmi käyttää kaksiulotteista taulukkoa sudokua varten (n * n) ja hakemistoa, jonka koko on noin n * n.Tilavaativuus on siis O(n^2).

## Parannuksia

Suorituskykytestausta voisi virtaviivaistaa. Suorituskykytestauksen voisi ottaa osaksi graafista käyttöliittymää. Käyttöliittymä ei ole responsiivinen, vaan jää jumiin, kun ongelmaa ratkaistaan. Graafinen liittymä lukee vain yhdentyyppisiä tiedostoja.

Työhön on toteutettu monta optimoitua backtrack algoritmia, jotka eivät kuitenkaan olleet juuri parempia kuin alkuperäinen.

Tehdyt tietorakenteet eivät ole yleiskäyttöisiä.

## Lähteet

Chakraborty, R.,  Paladhi, S., Chatterjee, S., Banerjee, S. An Optimized Algorithm for Solving Combinatorial Problems using Reference Graph. IOSR Journal of Computer Engineering, 16(3), pp 1-7. 2014.

Chatterjee, S., Paladhi, S., Chakraborty, R. A Comparative Study On The Performance Characteristics Of Sudoku Solving Algorithms. IOSR Journal of Computer Engineering, 16(5), pp 69-77. 2014.

Crook, J. F. A Pencil-and-Paper Algorithm for Solving Sudoku Puzzles. Notices of the AMS, 2009, 56.4: 460-468. 

Knuth, D. Dancing links. arXiv preprint cs/0011047, 2000.