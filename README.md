# Chatgram

A Chatgram egy nevű böngészős chat alkalmazás, mely rendelkezik asztali admin felülettel.

## Funkciók:

### Asztali (desktop modul):
Az asztali admin felületre csak admin jogosultságú felhasználó léphet be, itt lehetőség van a már létező chat szobák, valamint felhasználók törlésére.

### Webes (web modul):
A webes felületen lehetőség van regisztrálni. A bejelentkezett felhasználó a főoldalon tud szűrni a chat szobák között név, illetve kategória szerint. Lehetőség van más felhasználók keresésére is, őket név, illetve érdeklődési kör alapján szűrhetjük. Ha valamelyik keresőmezőbe nem írunk semmit és úgy nyomunk rá a keresőgombra, akkor minden kilistáz, attól függően, hogy a chat vagy pedig a felhasználó keresőgombjára nyomtunk.
A főoldalon tudunk új chatszobát létrehozni, melynél követelmény a név, szabályzat és kategória megadása.
Kilistázott chatszobáknál lehetőségünk van bármelyikbe belépni.
Belépéskor láthatjuk az elküldött üzeneteket, valamint azt, hogy ki írta és mi is hozzá tudunk szólni a beszélgetéshez. Minden üzenet elküldésekor frissül az üzenetek listája. Ha már meguntuk a beszélgetést, a 'Leave' gomb segítségével ki tudunk lépni újra főmenübe ahol beléphetünk másik szobába is akár.
'Logout' gomb segítségével pedig ki tudunk jelentkezni és felhasználót váltani.
A webes felületen nincsenek megkülönböztetve a sima illetve az admin jogú felhasználók.

## Fordítás/futtatáshoz szükséges tudnivalók:
A program Java 11-ben íródott, a webes rész futtatásához a Tomcat 9.0.45-ös verzióját használtam.
Az adatbázisok eléréséhez mindegyik DAOImpl fájl DB_NAME nevű konstansában található az útvonal, melyeket futtatás előtt mindenképp át kell írni. (Ezek az assets modulban találhatók.)

A belépéshez szükséges felhasználónvek megtalálhatók a cgdatabase.db nevű adatbázis állományban. (assets modul)







