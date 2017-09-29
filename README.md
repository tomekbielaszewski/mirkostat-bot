# mirkostat-bot [![Build Status](https://travis-ci.org/tomekbielaszewski/mirkostat-bot.svg)](https://travis-ci.org/tomekbielaszewski/mirkostat-bot)

Bot obliczający 24-godzinne statystyki aktywności na wykop.pl/mikroblog

## Zależności

Do działania wymaga:
- https://github.com/tomekbielaszewski/mirko-fetcher
- MongoDB
- klucz aplikacji wykop.pl z dostępem do dodawania nowych wpisów na mikroblogu

## Użycie

`java -jar Mirkostatbot-2.0.0.BUILD-SNAPSHOT.jar --fetch-last-x-hours 24`
`java -jar Mirkostatbot-2.0.0.BUILD-SNAPSHOT.jar -fetch 24`

Gdzie:
- `--fetch-last-x-hours` / `-fetch` - to liczba ostatnich godzin z których pobierane są wpisy. Na przykład `-fetch 5` wywołane o godz 23:30 pobierze wpisy do godziny 18:30