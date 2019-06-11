import java.util.LinkedList;
import java.util.List;

/**
 * @author Aaron Betzholz / Jan Ehrhardt
 * @version 11.06.2019
 *
 * Zum Testen der implementierten Methoden von DoppeltVKliste
 */
public class Test {

    private DoppeltVKListe vkl = new DoppeltVKListe();

    public static void main(String[] args){
        new Test().run();
    }

    /**
     * Testet alle implementierten Methoden der DoppeltVKListe
     */
    private void run(){
        System.out.println("Gestet wird ob die Liste leer ist: ");
        testeEmpty();
        System.out.println("Füge Element der Liste hinzu");
        testeAdd("Jon", "Schnee");
        System.out.println("Gib aus wie viele Elemente in der Liste sind: ");
        testeSize();
        System.out.println("Entferne ein Element");
        testeRemove();
        System.out.println("Gib aus wie viele Elemente in der Liste sind: ");
        testeSize();
        System.out.println("Füge Elemente über den Index hinzu (2 Stück)");
        testeAddIndex(0, "Sansa", "Stark");
        testeAddIndex(1, "Arya", "Stark");
        System.out.println("Gib wieder die Anzahl aus: ");
        testeSize();
        System.out.println("Lösche die gesamte Liste: ");
        testeClear();
        System.out.println("Gib aus wie viele Elemente in der Liste sind: ");
        testeSize();
        System.out.println("Füge neue Elemente hinzu (5 Stück)");
        testeAdd("Tyrion", "Lannister");
        testeAdd("Jaime", "Lannister");
        testeAdd("Arya", "Stark");
        testeAdd("Sansa", "Stark");
        testeAdd("Ned", "Stark");
        System.out.println("Gib aus wie viele Elemente in der Liste sind: ");
        testeSize();
        System.out.println("Teste Get an der Stelle 0: ");
        testeGet(0);
        System.out.println("Es wird Set getestet an der Stelle 0: ");
        testeSet(0, "Jon", "Schnee");
        testeGet(0);
        System.out.println("Liste wird gelöscht");
        testeClear();
        System.out.println("Es wird eine Collection hinzugefuegt");
        testeAddAll();
        System.out.println("Gib aus wie viele Elemente in der Liste sind: ");
        testeSize();
        System.out.println("Gib das Zweite Element aus: ");
        testeIndexOf(2);
        System.out.println("Entferne das erste Element");
        testeRemoveIndex(1);
        System.out.println("Gib aus wie viele Elemente in der Liste sind: ");
        testeSize();
        System.out.println("Ist Jon Schnee in der Liste enthalten? ");
        testeContains("Jon", "Schnee");
        System.out.println("Teste toArray: ");
        testeToArray();
    }

    private void testeSize(){
        System.out.println("Size: " + vkl.size());
    }

    private void testeEmpty(){
        System.out.println("Empty: " + vkl.isEmpty());
    }

    private void testeContains(String vorname, String nachname){
        Person p = new Person(vorname, nachname);
        System.out.println(vkl.contains(p));
    }

    private void testeToArray(){
        Object[] pArray = new Person[vkl.size()];
        pArray = vkl.toArray(pArray);
        for(int i = 0; i < pArray.length; i++){
            System.out.println(pArray[i]);
        }
    }

    private void testeAdd(String vorname, String nachname){
        vkl.add(pp(vorname, nachname));
    }

    private void testeRemove(){
        Object o = vkl.get(0);
        vkl.remove(o);
    }

    private void testeAddAll(){
        List<Person> liste = new LinkedList<>();
        liste.add(pp("Jon", "Schnee"));
        liste.add(pp("Sansa", "Stark"));
        liste.add(pp("Arya", "Stark"));
        vkl.addAll(liste);
    }

    private void testeClear(){
        vkl.clear();
    }

    private void testeGet(int index){
        Person pers = (Person) vkl.get(index);
        System.out.println("Get an der Stelle " + index + ": " + pers.toString());
    }

    private void testeSet(int index, String vorname, String nachname){
        Object o = pp(vorname, nachname);
        vkl.set(index, o);
    }

    private void testeAddIndex(int index, String vorname, String nachname){
        vkl.add(index, pp(vorname, nachname));
    }

    private void testeRemoveIndex(int index){
        vkl.remove(index);
    }

    private void testeIndexOf(int index){
        Person o = (Person) vkl.get(index);
        System.out.println("Index Of " + index + ": " + o.toString());
    }

    private Person pp (String vorname, String nachname){
        return new Person(vorname, nachname);
    }
}
