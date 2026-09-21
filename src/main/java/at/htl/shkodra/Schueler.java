package at.htl.shkodra;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;

/**
 * Ein Datensatz aus der Klassenliste.
 *
 * TODO Uebung 2: Felder name, jahrgang und gruppe ergaenzen.
 * Entweder als record (eine Zeile) oder als gewoehnliche Klasse mit
 * Konstruktor und toString. Beides ist richtig.
 */
public class Schueler {
        private String name;
        private int jahrgang;
        private String gruppe;

        public Schueler(String name, int jahrgang, String gruppe) {
            this.name = name;
            this.jahrgang = jahrgang;
            this.gruppe = gruppe;
        }
        @Override
        public String toString() {
            return name + " " + jahrgang + " " + gruppe;
        }

    public static void main(String[] args) {

        Schueler s1 = new Schueler("Anna", 5, "A");
        Schueler s2 = new Schueler("Ben", 5, "B");
        Schueler s3 = new Schueler("Clara", 5, "A");

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        List<Schueler> liste = List.of(
                new Schueler("Anna", 5, "A"),
                new Schueler("Ben", 5, "B"),
                new Schueler("Clara", 5, "A")
        );
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(liste));
    }
    }

