## Mobiiliohjelmointi natiiviteknologioilla (viikko 6)

Viikkotehtävä 6, Room:
<br />https://github.com/basap/mobiiliohjelmointi-natiiviteknologioilla/tree/week2/app/src/main/java/com/example/viikko2

## Room

Room toimii sovelluksen pysyvänä tietokantakerroksena. Se tallentaa tehtävät SQLite-tietokantaan ja mahdollistaa niiden lukemisen reaktiivisesti Flow:n avulla. Arkkitehtuuri etenee seuraavasti:

**Entity → DAO → Database → Repository → ViewModel → UI**

- **Entity**: `TaskEntity`määrittelee tietokantataulun rakenteen (tasks).
- **DAO (Data Access Object)**: `TaskDao` sisältää SQL-kyselyt (insert, update, delete, getAll).
- **RoomDatabase**: `AppDatabase` luo ja hallinnoi tietokantaa.
- **Repository**: `TaskRepository` toimii välikerroksena DAO:n ja ViewModelin välillä.
- **ViewModel**: `TaskViewModel` kerää Flow-datan ja kapseloi CRUD-toiminnot.
- **UI**: Näkymät (HomeScreen ja CalendarScreen) kuuntelevat ViewModelin tilaa `collectAsState()` avulla.

### Projektin rakenne

```
data/
   ├── model/
   │     └── TaskEntity.kt
   ├── local/
   │     ├── TaskDao.kt
   │     └── AppDatabase.kt
   └── repository/
         └── TaskRepository.kt

view/
   ├── HomeScreen.kt
   ├── CalendarScreen.kt
   ├── DetailDialog.kt
   ├── AddTaskDialog.kt
   └── TaskRow.kt

viewmodel/
   ├── TaskViewModel.kt
   └── TaskViewModelFactory.kt

navigation/
   └── Routes.kt

MainActivity.kt
```

### Datavirran kulkureitti

- Käyttäjä lisää, muokkaa tai poistaa tehtävän UI:ssa.
- UI kutsuu ViewModelin funktiota.
- ViewModel käynnistää coroutine-kutsun Repositoryyn.
- Repository kutsuu DAO:ta.
- DAO suorittaa muutoksen Room-tietokantaan.
- Room päivittää Flow:n automaattisesti.
- ViewModelin StateFlow päivittyy.
- Compose UI tekee recomposen automaattisesti.
