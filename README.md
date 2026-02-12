# Mobiiliohjelmointi natiiviteknologioilla (viikko 5)

WeatherApp hakee OpenWeatherMapin API:sta säätiedot käyttäjän syöttämän kaupungin mukaan.

https://github.com/basap/mobiiliohjelmointi-natiiviteknologioilla/tree/week5/app/src/main/java/com/example/weatherapp

## Tekniset ratkaisut

### Retrofit

Retrofit hallitsee HTTP-pyynnöt sovellukselta OpenWeatherMapin API:lle. Vastaukset tulevat JSON-muodossa.

### GSON

Muuntaa Retrofitin antamat JSON-vastaukset taustalla Kotlin-dataluokiksi.

### Coroutines

API-kutsut tehdään taustasäikeessä (`viewModelScope.launch`). UI pysyy responsiivisena ja se päivittyy automaattisesti datan saapuessa.

### UI-tila

`WeatherViewModel` hallitsee `WeatherUiState`-oliota. Jetpack Compose reagoi tilamuutoksiin automaattisesti. Tällä hetkellä UI näyttää reaaliaikaisen lämpötilan lisäksi päivän ylimmän ja alimman astemäärän, sään kuvauksen sekä tuulen nopeuden ja suunnan.

### API-key

API-key on tallennettu `local.properties`-tiedostoon. build.gradleen on määritelty niin, että buildin aikana se muutetaan `BuildConfig`-kentäksi. ViewModelissa avain haetaan kutsumalla `BuildConfig.OPENWEATHER_API_KEY` ja Retrofit käyttää sitä API-kutsujen yhteydessä.
