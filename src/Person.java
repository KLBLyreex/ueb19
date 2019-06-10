
public class Person {

    private String vorname;
    private String nachname;

    public Person(String vorname, String nachname){
        check(vorname.isEmpty(), "Vorname ist leer");
        check(nachname.isEmpty(), "Nachname ist leer");

        this.vorname = vorname;
        this.nachname = nachname;
    }

    private void check(boolean bedingung, String msg){
        if(bedingung){
            throw new IllegalArgumentException(msg);
        }
    }

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
