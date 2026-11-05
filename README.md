# Expense Tracker

## 1. O projektu

Expense Tracker je spletna aplikacija za spremljanje stroškov, ki sem jo naredil pri vajah.

V aplikaciji lahko dodam nov strošek, pogledam vse stroške ali posamezen strošek, spremenim podatke in strošek izbrišem. Pri vsakem strošku vnesem naziv, znesek in kategorijo.

Projekt je razdeljen na frontend in backend. Frontend je narejen v Reactu, backend pa v Spring Bootu. Podatki se shranjujejo v PostgreSQL.

## 2. Uporabljene tehnologije

### Backend

- Java 21 (nastavljena različica v `pom.xml`)
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- PostgreSQL (pri razvoju sem uporabljal PostgreSQL 18)
- PostgreSQL JDBC gonilnik
- Lombok
- Springdoc OpenAPI 3.1.1 (Swagger UI)
- Maven (za odvisnosti in zagon projekta)

Različice odvisnosti, pri katerih ni posebej navedene številke, upravlja Spring Boot.

### Frontend

- React `^19.1.0`
- React DOM `^19.1.0`
- Vite `^6.3.0`
- `@vitejs/plugin-react` `^4.7.0`
- JavaScript, HTML in CSS

Znak `^` pomeni, da se lahko namesti tudi novejša združljiva različica. To so različice, zapisane v `package.json`.

### Orodja

- IntelliJ IDEA
- Docker Desktop
- Git in GitHub
- Swagger UI za preverjanje REST API-ja

## 3. Struktura projekta

```text
ExpenseTracker/
├── backend/
│   ├── src/
│   │   └── main/
│   │       ├── java/         # Java koda: entitete, DAO in REST
│   │       └── resources/    # application.properties
│   └── pom.xml               # Maven odvisnosti
├── frontend/
│   ├── src/                  # React komponente in CSS
│   ├── index.html
│   └── package.json          # Knjižnice in ukazi za frontend
├── database/                 # Datoteke, povezane z bazo
├── .gitignore                # Datoteke, ki jih ne nalagamo na GitHub
└── README.md                 # Navodila za projekt
```

V `backend` imam Spring Boot aplikacijo in REST API. V `frontend` je uporabniški vmesnik. Mapa `database` vsebuje datoteke, povezane s podatkovno bazo.

## 4. Namestitev in zagon

### Kaj potrebujem

Pred zagonom morajo biti nameščeni:

- JDK 21 ali združljiv novejši JDK
- Node.js in npm
- Docker Desktop s PostgreSQL (ali že nameščen PostgreSQL)
- Git
- IntelliJ IDEA ali drug urejevalnik

### Prenos projekta

V terminalu izvedem:

```bash
git clone https://github.com/ivankretov999-code/ExpenseTracker.git
cd ExpenseTracker
```

### PostgreSQL

1. Zaženem Docker Desktop in PostgreSQL vsebnik.
2. Preverim, da PostgreSQL posluša na portu `5432`.
3. V PostgreSQL ustvarim bazo `expense_tracker`, če je še ni.
4. Uporabniško ime za povezavo je `postgres`, geslo pa je nastavljeno lokalno in ni shranjeno v GitHubu.

### Backend

V datoteki `backend/src/main/resources/application.properties` preverim povezavo do baze. Pri svojem projektu uporabljam:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/expense_tracker
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD:postgres}
```

Spremenljivko `DB_PASSWORD` nastavim lokalno na geslo svojega PostgreSQL uporabnika. V IntelliJ to naredim v **Run → Edit Configurations → Environment variables**. Gesla ne dodajam v Git.

Nato v IntelliJ odprem backend kot Maven projekt in zaženem razred `ExpenseTrackerApplication`.

Backend deluje na naslovu `http://localhost:8080`.

Swagger UI: `http://localhost:8080/swagger-ui/index.html`

### Frontend

V drugem terminalu izvedem:

```bash
cd frontend
npm install
npm run dev
```

Nato v brskalniku odprem `http://localhost:5173`.

**Vrstni red zagona:** najprej PostgreSQL, nato backend, na koncu frontend.

## 5. Navodila za razvijalce

Za delo s projektom uporabljam Git in GitHub. Pred začetkom dela preverim spremembe in prenesem zadnjo različico kode:

```bash
git status
git pull
```

Ko nekaj spremenim, naredim:

```bash
git add .
git commit -m "Opis spremembe"
git push
```

Pomen ukazov:

- `git status` pokaže, katere datoteke so spremenjene.
- `git pull` prenese nove spremembe z GitHuba.
- `git add .` pripravi spremembe za commit.
- `git commit` shrani spremembe v zgodovino projekta.
- `git push` pošlje commite na GitHub.

Pred commitom preverim, da ne dodajam gesel ali nepotrebnih datotek. Če na projektu dela več ljudi, je priporočljivo za večje spremembe ustvariti svojo vejo in spremembe združiti prek pull requesta.

## 6. Standardi kodiranja

- V Javi za imena razredov uporabljam `PascalCase` (na primer `ExpenseRestEndpoint`).
- Za metode in spremenljivke uporabljam `camelCase` (na primer `getExpenses`).
- V Reactu so imena komponent zapisana z veliko začetnico (na primer `App`).
- Kodo zamikam enotno in uporabljam razumljiva imena.
- Frontend in backend imam ločena.
- Ko spreminjam REST API, preverim delovanje v Swaggerju in v brskalniku.
- Gesel, mape `node_modules`, mape `target` in nastavitev IntelliJ ne nalagam v repozitorij.
