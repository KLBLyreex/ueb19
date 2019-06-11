import org.omg.CORBA.UNSUPPORTED_POLICY;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Klasse in der die Doppelt Verkettete Liste generisch Eingefuegt wurde
 *
 * @author Aaron Betzholz / Jan Ehrhardt
 * @version 10.06.2019
 */

public class DoppeltVKListe<E> implements List<E> {

    private int size = 0; //Ist immer die aktuelle Laenge der Liste
    private ListElement<E> first = null; //Wird beim Compilieren gesetzt
    private ListElement<E> last = null;

    /* Hier stehen die Methoden die verwendet werden */

    /**
     * @return die aktuelle Laenge der Liste
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true wenn die Liste leer ist
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Prüft ob das uebergebene Objekt in der Liste enthalten ist
     * @param o das zu pruefende Objekt
     * @return das Objekt, wenn es in der Liste enthalten ist
     */
    @Override
    public boolean contains(Object o) {
        return (indexOf(o) > -1);
    }

    /**
     * Es wird ein Array uebergeben und in das Array
     * werden die Element der Liste sequentiell eingefuegt
     *
     * @param a das uebergebene Array
     * @param <T> der Typ
     * @return das gefuellte Array, welches uebergeben wurde
     */
    @Override
    public <T> T[] toArray(T[] a) {
        int index = 0;
        ListElement<E> le = first;

        while(le != null){
            a[index] = (T) le.data;
            le = le.next;
            index++;
        }

        return a;
    }

    /**
     * Fuegt das uebergeben Element an der Stelle des Index hinzu
     *
     * @param index die Stelle an der es hinzugefuegt werden soll
     * @param element das Element welches in die Liste eingefuegt werden soll
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
        }

        if (index == size) {
            add(element);
        } else {
            addBefore(element, entry(index));
        }
    }

    /**
     * Fuegt das uebergeben Element der Liste am Ende hinzu
     * @param e das uebergeben Element
     * @return True wenn es geklappt hat
     */
    @Override
    public boolean add(E e) {
        ListElement<E> listeNeu = new ListElement<>(e, null, null);

        if (first == null) {
            first = listeNeu;
            last = listeNeu;
            size = 1;
        } else {
            last.next = listeNeu;
            listeNeu.prev = last;
            last = listeNeu;
            size++;
        }
        return true;
    }

    /**
     * Loescht das Objekt aus der Liste
     * @param o das zu loeschende Objekt
     * @return True wenn es geklappt hat
     */
    @Override
    public boolean remove(Object o) {
        ListElement<E> le = entry(o);

        if (le != null) {
            remove(le);
            return true;
        }

        return false;
    }

    /**
     * Loescht ein Objekt an der Stelle index
     * @param index die stelle an der das Objekt geloescht werden soll
     * @return das Objekt welches entfernt wurde
     */
    @Override
    public E remove(int index) {
        ListElement<E> le = entry(index);

        remove(le);

        return le.data;
    }

    /**
     * Fuegt den Inhalt einer Collection der Liste hinzu
     * @param c die Collection
     * @return true wenn es geklappt hat
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E element : c) {
            add(element);
        }
        return true;
    }

    /**
     * Setzt die gesamte Liste zurueck
     */
    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Holt ein Element an der Stelle des index
     * @param index die Stelle an der das Element geholt werden soll
     * @return das Objekt
     */
    @Override
    public E get(int index) {
        return entry(index).data;
    }

    /**
     * Ueberschreibt ein Objekt an der Stelle Index
     * @param index die Stelle an der das Objekt ueberschrieben werden soll
     * @param element das neue Objekt
     * @return das Objekt wenn es geklappt hat
     */
    @Override
    public E set(int index, E element) {
        ListElement<E> le = entry(index);
        le.data = element;
        return le.data;
    }

    /**
     * Gibt die Stelle des Objektes aus an der das Objekt in der Liste liegt
     * @param o das zu suchende Objekt
     * @return die stelle, wenn -1 dann nicht gefunden
     */
    @Override
    public int indexOf(Object o) {
        int index = -1;

        if (o == null) {
            throw new NullPointerException("Objekt ist Null");
        }

        ListElement<E> le = first;

        while (le != null) {
            index++;

            if (le.data.equals(o)) return index;

            le = le.next;
        }

        return index;
    }

    /**
     * Gibt den Inhalt der Liste als String zurueck
     * @return String
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        ListElement<E> tmp = first;
        while(tmp != null){
            sb.append(tmp.data);
            sb.append("\n");
            tmp = tmp.next;
        }
        return sb.toString();
    }

    private void addBefore(E element, ListElement<E> le) {
        if (le == null) {
            throw new RuntimeException("Kein Element in der Liste");
        }

        if (size == 0) {
            add(element);
        } else {
            ListElement<E> neueListe;
            if(le.prev == null){
                neueListe = new ListElement<>(element, null, le);
                le.next = neueListe;
            } else {
                neueListe = new ListElement<>(element, le.prev, le);
                le.prev.next = neueListe;
            }
            le.prev = neueListe;
            last = neueListe;
            size++;
        }
    }

    private ListElement<E> entry(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
        }

        ListElement<E> liste = first;
        while (0 < index--) {
            liste = liste.next;
        }
        return liste;
    }

    private ListElement<E> entry(Object o) {
        if (o == null) {
            throw new NullPointerException("Object is null");
        }

        ListElement<E> le = first;

        while (le != null) {
            if (le.data.equals(o)) return le;

            le = le.next;
        }

        return null;
    }

    private void remove(ListElement<E> le) {
        if (le.prev == null) {
            first = le.next;
        } else {
            le.prev.next = le.next;
        }

        if (le.next == null) {
            last = le.prev;
        } else {
            le.next.prev = le.prev;
        }

        size--;
    }

    /* ######################################################### */


    /**
     * Innere Klasse welche die Doppelt Verkette Liste darstellt
     * @param <T>
     */
    private class ListElement<T> {
        private T data;
        private ListElement<T> prev;
        private ListElement<T> next;

        public ListElement(T neu, ListElement<T> vorheriger, ListElement<T> naechster) {
            data = neu;
            prev = vorheriger;
            next = naechster;
        }

    }

    /* Hier stehen die Methoden die nicht verwendet werden */

    private String NICHTSUPPORTET = "Nicht Supportete Methode";

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException(NICHTSUPPORTET);
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException(NICHTSUPPORTET);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException(NICHTSUPPORTET);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException(NICHTSUPPORTET);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException(NICHTSUPPORTET);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException(NICHTSUPPORTET);
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException(NICHTSUPPORTET);
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException(NICHTSUPPORTET);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException(NICHTSUPPORTET);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException(NICHTSUPPORTET);
    }
}
