# Viikkoraportti 2

Tuntimäärä: 3+6+4+1 = 14h.

Luotu ratkaisijalle kolme eri logiikkaa ja näiden yhdistelmät. Luotu apuluokka, joka lukee sudokun tiedostosta.

Haasteena saada sudoku tulostumaan kauniisti. Unicoden "box drawing" merkit, eivät tulostu oikein, vaan niiden paikalle tulostuu kysymysmerkkejä. Kaksinumeroiset solut rikkovat tulostuksen muodon (16x16 sudoku ja isommat).

Seuraavaksi aloitetaan testaus ja toteutetaan mahdollisesti sudokugeneraattori. Lisäksi toteutetaan ainakin yksi uusi ratkaisualgoritmi, joka laskee ensin mitkä numerot soluihin käyvät ja testaa vain niitä, ei kaikkia.

## 15.3. 

Toteutettu luokka sudokun esittämistä varten. Haasteena saada sudoku tulostettua kauniisti. 3h.

## 16.3. 

Toteutettu ratkaisija, joka ratkaisee sudokun backtracking-menetelmällä. Lisäksi ratkaisija, joka ratkaisee sudokun täyttämällä solut, joihin on vain yksi mahdollinen numero (candidate-checking method). Aloitettu ratkaisija, joka tutkii yhtä riviä, saraketta tai neliötä kerrallaan ja täyttää vain yhteen soluun sopivat numerot (place-finding method). 6h.

## 17.3. 

Toteutettu place-finding method ratkaisija. Lisätty file_reader apuluokka, jolla voi lukea sudokuja eri tyyppisistä tiedostomuodoista. 4h.

## 20.3. 

Lisätty checkstyle tiedosto. Palautuksen viimeistely 1h.