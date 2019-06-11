/**
 * Personen Klasse
 * @author Aaron Betzholz / Jan Ehrhardt
 * @version 10.06.2019
 */
public class Person {

    private String vorname;
    private String nachname;

    /**
     * Konstruktor um eine Person anzulegen
     * @param vorname der Vorname der Person (darf nicht leer sein)
     * @param nachname der Nachname der Person (darf nicht leer sein)
     */
    public Person(String vorname, String nachname){
        check(vorname.isEmpty(), "Vorname ist leer");
        check(nachname.isEmpty(), "Nachname ist leer");

        this.vorname = vorname;
        this.nachname = nachname;
    }

    /**
     * Allgemeine Check Methode
     * @param bedingung wenn Bedingung erfuellt ist fliegt eine Exception
     * @param msg Die Nachricht die in der Exception enthalten ist
     */
    private void check(boolean bedingung, String msg){
        if(bedingung){
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Überschreiben der ToString-Methode
     * @return den Nachnamen und Vornamen der Person
     */
    @Override
    public String toString(){
        return this.nachname + ", " + this.vorname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }
}
