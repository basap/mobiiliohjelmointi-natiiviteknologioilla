## Mobiiliohjelmointi natiiviteknologioilla (viikko 3)

Viikkotehtävä 3, MVVM:
<br />https://github.com/basap/mobiiliohjelmointi-natiiviteknologioilla/tree/week3/app/src/main/java/com/example/viikko3

## MVVM ja StateFlow

### MVVM (Model-View-ViewModel)

MVVM-arkkitehtuurimallissa sovelluksen vastuualueet ovat jaettu selkeästi:
- Model: Sisältää sovelluksen datan ja liiketoimintalogiikan (repository, database, API)
- View: UI ja käyttäjän vuorovaikutus (Compose-komponentit). Vastaa näkymän piirtämisestä sekä käyttäjäsyötteiden välittämisestä eteenpäin.
- ViewModel: Hallitsee UI-logiikkaa ja tilaa. Välittää dataa Modelista Viewiin.

MVVM ja Compose toimivat erinomaisesti keskenään:

- Compose voi keskittyä UI:n piirtämiseen kun ViewModel hoitaa logiikan
- UI päivittyy automaattisesti kun ViewModelin tila muuttuu
- Logiikka ja UI ovat selkeästi erillään

### StateFlow

ViewModelissa on kaksi tapaa hallita tilaa: StateFlow ja MutableState. MutableState on Composen oma tilatyyppi, eli saatavilla natiivina, kun taas StateFlow on peräisin Kotlin Coroutines -kirjastosta.

StateFlow soveltuu monimutkaisemman logiikan toteuttamiseen ja se toimii coroutinien kanssa. Se on myös thread-safe eli koodia voidaan käyttää useammasta säikeestä yhtä aikaa, kun taas MutableState on tarkoitettu päivitettäväksi ainoastaan pääsäikeessä. Yleensä StateFlow on käytössä isommissa, monimutkaisissa Compose-sovelluksissa sen ollessa joustavampi, turvallisempi ja skaalautuvampi.


