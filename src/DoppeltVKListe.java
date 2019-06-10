import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Aaron Betzholz / Jan Ehrhardt
 * @version 10.06.2019
 */

public class DoppeltVKListe<E> implements List<E> {

    private int size = 0;
    private ListElement<E> first = null; //Wird beim Compilieren gesetzt
    private ListElement<E> last = null;

    /* Hier stehen die Methoden die verwendet werden */

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return (indexOf(o) > -1);
    }

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

    @Override
    public boolean remove(Object o) {
        ListElement<E> le = entry(o);

        if (le != null) {
            remove(le);
            return true;
        }

        return false;
    }

    @Override
    public E remove(int index) {
        ListElement<E> le = entry(index);

        remove(le);

        return le.data;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E element : c) {
            add(element);
        }
        return false;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        return entry(index).data;
    }

    @Override
    public E set(int index, E element) {
        ListElement<E> le = entry(index);
        le.data = element;
        return le.data;
    }

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

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Iterator Methode");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Falsche toArray Methode");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
