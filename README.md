## Mobiiliohjelmointi natiiviteknologioilla (viikko 1)

Viikkotehtävä 1, Tasklist: “Domain + Kotlin-harjoitukset + ensimmäinen Compose-näkymä”
<br />https://github.com/basap/mobiiliohjelmointi-natiiviteknologioilla/tree/week1/app/src/main/java/com/example/viikko1

### Datamalli

Sovellukseen tehty Task data class tehtävien esittämiseen, missä seuraavat kentät:
- **id** (yksilöi tehtävän tunnisteella)
- **title** (tehtävän otsikko)
- **description** (tehtävän kuvaus)
- **priority** (tehtävän tärkeys 1-3 asteikolla)
- **dueDate** (tehtävän deadline, esim: 2026-01-09)
- **done** (tieto tehtävän suorituksesta, true/false)

### Kotlin-funktiot

Seuraavat funktiot käsittelevät tehtävälistaa:

- **addTask(list, task)**: Lisää uuden tehtävän ja palauttaa uuden listan.
- **toggleDone(list, id)**: Vaihtaa annetun id:n mukaisen tehtävän tilan. done = true/false.
- **filterByDone(list, done)**: Suodattaa tehtävät tehdyn tilan perusteella. Kun käytössä, niin sovellus listaa ensin ne tehtävät, jotka ovat jo tehty.
- **sortByDueDate(list)**: Järjestää tehtävät deadlinen mukaan niin, että lähin deadline tulee ensin.

## Compose-tilanhallinta

Jetpack Composessa käyttöliittymä perustuu tilaan (state). Kun tila muuttuu, Compose piirtää käyttöliittymän automaattisesti uudelleen.

Aluksi tilaa voi hallita 'remember'-muuttujilla, mutta tämä toimii vain Composable-funktion elinkaaren ajan.
