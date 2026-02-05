## Mobiiliohjelmointi natiiviteknologioilla (viikko 4)

https://github.com/basap/mobiiliohjelmointi-natiiviteknologioilla/tree/week3/app/src/main/java/com/example/viikko4

### Navigointi

Navigointi Jetpack Composessa tarkoittaa screenien välillä siirtymistä ilman perinteistä Fragment-pohjaista navigointia. Navigointi toteutetaan tilapohjaisesti eli UI reagoi navigaatiotilan muutoksiin.

Navigoinnin kaksi keskeistä komponenttia ovat NavHost ja NavController:

- **NavHost** on säiliö, joka näyttää nykyisen määränpään (destination).
- **NavController** hallitsee navigoinnin NavHostissa ja tarjoaa metodeja kuten navigate() ja popStackBack().

Yhdessä ne määrittelevät sovelluksen navigaatiopolut ja ruudut.

Tässä sovelluksessa navigointi on toteutettu kahdella päänäkymällä: **HomeScreen** ja **CalendarScreen**. HomeScreen näyttää tehtävälistan ja tehtävien hallinnan, CalendarScreen näyttää tehtävät kalenterityyliin, lähimmällä deadlinella oleva tehtävä näkyy ensin.

HomeScreenistä voidaan avata CalendarScreen ja CalendarScreenistä voidaan palata takaisin HomeScreeniin käyttäen samaa NavControlleria.

### Arkkitehtuuri (MVVM + navigointi)

Sovelluksen toteuttamisessa on käytetty MVVM-arkkitehtuuria:

* **Model**: Task-dataolio
* **View**: HomeScreen, CalendarScreen
* **ViewModel**: TaskViewModel

Sekä HomeScreen että CalendarScreen käyttävät samaa **TaskViewModelia**. Tämä mahdollistaa sen, että molemmat ruudut näkevät saman tehtävälistan sekä muutokset yhdessä ruudussa päivittyvät automaattisesti toiseen. ViewModel luodaan navigaation yläpuolella ja jaetaan molemmille ruuduille.

ViewModel sisältää tilan **StateFlow**-muodossa. UI siis kerää tilan **collectAsState()**-funktion avulla. Kun ViewModelin tila muuttuu, Compose päivittää molemmat ruudut automaattisesti.

### CalendarScreen

<img align="right" src="https://i.imgur.com/PhnBEHe.png">
CalendarScreen esittää tehtävät päivämäärän mukaan ryhmiteltynä. Tehtävät ryhmitellään `dueDate`-kentän perusteella. Jokaiselle päivälle näytetään otsikko ja sen alle kuuluvat tehtävät.

Kalenterissa on hyödynnetty Compose-listoja ja **groupBy**-funktiota.


### AlertDialog, addTask, editTask

Tehtävien lisääminen ja muokkaaminen tapahtuu **AlertDialogilla**.

* **AddTaskDialog** avautuu HomeScreenissä `+`-painikkeesta
* **DetailDialog** avautuu, kun olemassa olevaa tehtävää painetaan

Dialogit kutsuvat ViewModelin metodeja (**addTask**, **updateTask**, **removeTask**) jolloin tila päivittyy ja UI reagoi automaattisesti.

