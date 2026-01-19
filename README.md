## Mobiiliohjelmointi natiiviteknologioilla (viikko 2)

Viikkotehtävä 2, ViewModel:
<br />https://github.com/basap/mobiiliohjelmointi-natiiviteknologioilla/tree/week2/app/src/main/java/com/example/viikko2

## Compose ja ViewModel

### Compose-tilanhallinta

Jetpack Composessa käyttöliittymä perustuu tilaan (state). Kun tila muuttuu, Compose piirtää käyttöliittymän automaattisesti uudelleen.

Aluksi tilaa voi hallita 'remember'-muuttujilla, mutta tämä toimii vain Composable-funktion elinkaaren ajan. Eli konfiguraatiomuutoksissa (esim. näytön kääntyminen) arvo katoaa.

### ViewModel

ViewModelia käytetään, koska:
- se säilyttää tilan konfiguraatiomuutoksissa (esim. näytön kääntö). 'remember'-muuttujan käyttö ei tätä mahdollista.
- erottaa käyttöliittymän ja sovelluslogiikan toisistaan
- tekee koodista testattavampaa ja selkeämpää

Tässä sovelluksessa ViewModel sisältää tehtävälistan tilan,
ja UI reagoi automaattisesti tilan muutoksiin.

<b>remember</b> sopii yksittäisten komponenttien tilan hallintaan lyhytaikaisesti, kun taas <b>ViewModel</b> soveltuu tuotantosovelluksiin, kun tila pitää säilyttää ja eriyttää logiikka UI:sta.
