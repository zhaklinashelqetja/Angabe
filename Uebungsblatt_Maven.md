# SEW 5 · E03 · Übungsblatt Maven

Name: Zhaklina Shelqetja  Gruppe: 5BW  Datum: ________

Ihr arbeitet im Projekt `sew5`, das ihr aus dem Ordner `Angabe` kopiert habt.
Die Übungen 1 bis 3 sind **Pflicht**, 4 und 5 sind Erweiterung, 6 ist für die,
die früher fertig sind. Die Hausübung macht jeder.

Nach jeder Übung: `mvn package` muss ohne roten Text durchlaufen.
Am Ende der Stunde: committen und pushen.

---

## Übung 1 · Das Projekt zum Laufen bringen — Stufe 1

In der `pom.xml` fehlt der `properties`-Block.

1. Ergänzt ihn so, dass mit **Java 21** übersetzt wird und die Quelldateien als
   **UTF-8** gelesen werden.
2. Ruft `mvn package` auf.
3. Sieht euch den Ordner `target/` an. Schreibt auf, welche Dinge darin
   entstanden sind: 
classes    maven-status    sew5-0.1.0.jar

   ______________________  ______________________  ______________________

4. Legt `target/` in die `.gitignore` — falls es noch nicht darinsteht und
   beschreibe in einem Satz, warum: 
target/ enthält automatisch erzeugte Dateien und muss deshalb nicht ins Git-Repository.

   _______________________________________________________________________

---

## Übung 2 · Die erste eigene Klasse — Stufe 1

Die Klasse `at.htl.shkodra.Schueler` ist nur ein Gerüst.

1. Gebt ihr die Felder **Name**, **Jahrgang** und **Gruppe**.
2. Die `main`-Methode legt drei Schülerobjekte an und gibt sie aus.
   ```java
   public record Schueler(String name, int jahrgang, String gruppe) {

       public static void main(String[] args) {
           Schueler s1 = new Schueler("Ana Hoxha", 5, "5as");
           Schueler s2 = new Schueler("Blerta Krasniqi", 5, "5aw");
           Schueler s3 = new Schueler("Dritan Berisha", 5, "5an");

           System.out.println(s1);
           System.out.println(s2);
           System.out.println(s3);
       }
   }
   ```
3. Baut das Projekt und startet das entstandene JAR von der Kommandozeile:

   ```bash
   mvn package
   java -cp target/sew5-0.1.0.jar at.htl.shkodra.Schueler
   ```

4. Ändert die `version` in der `pom.xml` auf `0.2.0` und baut erneut. Wie heißt
   das JAR jetzt, und warum ist der Befehl aus Punkt 3 damit kaputt?
   Das JAR heißt jetzt sew5-0.2.0.jar. Der alte Befehl funktioniert nicht mehr, weil er noch auf sew5-0.1.0.jar verweist.
   _______________________________________________________________________

---

## Übung 3 · Eine Datei, die mit ins JAR wandert

Im Projekt liegt `src/main/resources/schueler.csv`.

1. Baut das Projekt und sucht die Datei in `target/`. Wo ist sie gelandet?
   In `target/classes/schueler.csv` - direkt neben dem Package-Ordner `at/`. Maven kopiert alles aus `src/main/resources/` 1:1 nach `target/classes/`, und von dort wandert es unverändert ins JAR.
   _______________________________________________________________________

2. Öffnet das JAR mit einem Zip-Programm (ein JAR *ist* ein Zip). Ist die CSV
   drin?  ja 

3. Warum ist das der Grund, aus dem man eine solche Datei **nicht** über einen
   Pfad wie `C:\Users\...\schueler.csv` öffnet?
   Als JAR ist die CSV kein eigenständiges Element im Dateisystem mehr, sondern Teil des Archivs. Ein fester Pfad wie `C:\Users\...\schueler.csv` würde nur auf dem einen Rechner funktionieren, auf dem die Datei zufällig genau dort liegt - bei jedem anderen Nutzer, Betriebssystem oder Ordner bricht das. Ressourcen liest man stattdessen über den Classpath, z. B. `getClass().getResourceAsStream("/schueler.csv")`.
   _______________________________________________________________________

---

## Übung 4 · Eine fremde Bibliothek einbinden

1. Sucht auf `search.maven.org` die Bibliothek **Gson** von
   `com.google.code.gson` und tragt sie als Abhängigkeit in die `pom.xml` ein.
   ```xml
   <dependency>
       <groupId>com.google.code.gson</groupId>
       <artifactId>gson</artifactId>
       <version>2.14.0</version>
   </dependency>
   ```
2. Gebt eure drei Schülerobjekte als JSON aus. Zwei Zeilen genügen:

   ```java
   Gson gson = new GsonBuilder().setPrettyPrinting().create();
   System.out.println(gson.toJson(liste));
   ```

3. **Nehmt die Abhängigkeit wieder weg** und baut erneut. Notiert die
   Fehlermeldung wörtlich: cannot find symbol
   ```
   [ERROR] .../Schueler.java:[3,23] package com.google.gson does not exist
   [ERROR] .../Schueler.java:[4,23] package com.google.gson does not exist
   [ERROR] .../Schueler.java:[16,9] cannot find symbol
     symbol:   class Gson
     location: class at.htl.shkodra.Schueler
   [ERROR] .../Schueler.java:[16,25] cannot find symbol
     symbol:   class GsonBuilder
     location: class at.htl.shkodra.Schueler
   [INFO] 4 errors
   BUILD FAILURE
   ```

   _______________________________________________________________________

   4. Tragt sie wieder ein. Wo auf eurem Rechner liegt die heruntergeladene
      Gson-Datei? 
   `~/.m2/repository/com/google/code/gson/gson/2.14.0/gson-2.14.0.jar` - das lokale Maven-Repository, in dem jede jemals heruntergeladene Abhängigkeit nach `groupId/artifactId/version` sortiert liegt.

      _______________________________________________________________________

---

## Übung 5 · Fehlersuche — Stufe 2

Im Ordner `Beispiele` liegt `pom_kaputt.xml`. Darin stecken **vier** Fehler.

Findet sie die vier Fehler und schreibt sie auf.
Danach prüft ihr eure Liste, indem ihr die Datei als `pom.xml` in ein leeres
Projekt legt und `mvn package` aufruft.

| # | Zeile | Was ist falsch |
|---|-------|---|
| 1 | 9     | Bei <version> fehlen " und </version>|
| 2 | 11    | <modelVersion>4.0.0</modelVersion> fehlt|
| 3 | 17/21 | dependancy ist falsch geschrieben. Es muss dependency heißen|
| 4 | 19    | artifactId muss gson statt Gson heißen|


## Übung 6 · Für die Schnellen — Stufe 3

Zuerst die Änderungen von Übung 4 auskommentieren. 
Dann sorgt dafür, dass `java -jar target/sew5-0.1.0.jar` ohne weitere Angaben
funktioniert. Hinweis: die Hauptklasse muss im Manifest des JAR stehen, und
dafür gibt es in der `pom.xml` einen `build`-Block.

Zweite Frage: Startet euer JAR danach auch dann, wenn es Gson (Übung 4 ) verwendet?
Probiert es aus und erklärt das Ergebnis.

_________________________________________________________________________

---

## Hausübung

Sucht auf `search.maven.org` die Bibliothek `org.apache.commons:commons-lang3`,
tragt sie in die `pom.xml` ein und ruft eine beliebige Methode daraus auf —
zum Beispiel `StringUtils.reverse("Shkodra")`.

``xml
<dependency>
<groupId>org.apache.commons</groupId>
<artifactId>commons-lang3</artifactId>
<version>3.20.0</version>
</dependency>
```

```java
import org.apache.commons.lang3.StringUtils;
// ...
System.out.println(StringUtils.reverse("Shkodra"));
```

Ausgabe: `ardokhS`
Committen und pushen. Fällig zur nächsten Einheit.
