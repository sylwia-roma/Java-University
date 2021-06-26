package Exercise10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//	ArrayBox<Integer> box1 = new ArrayBox<>(2);
//        System.out.println(box1.add(1));
//        System.out.println(box1.add(2));
//        System.out.println(box1.add(2));
//        System.out.println(box1.add(3));
//        Integer [] integers = new Integer[5];
//        integers[0] = 1;
//        integers[1] = 2;
//        integers[2] = 3;
//        integers[3] = 3;
//        integers[4] = 2;
//        System.out.println(box1.addAll(integers));
//        box1.print();
//        box1.swap(0, 2);
//        box1.print();
//        System.out.println(box1.search(2));
//        System.out.println(box1.search(4));
////        System.out.println(box1.min());
//        System.out.println("Przed usunieciem");
//        box1.print();
//        System.out.println(box1.delete(3));
//        box1.print();
//        System.out.println(box1.delete(99));

        ArrayBox<Osoba> bo = new ArrayBox<>(2);

        bo.add(new Osoba("Kowalski", 19));                        // nazwisko = "Kowalki", wiek = 19
        bo.add(new Student("Kowalska", 18, 100));                    // nazwisko, wiek, grupa = 100
        bo.add(new Student("Kowalska", 20, 200));

        Osoba[] to = new Osoba[]{new Osoba("Nowak", 21), new Student("Nowak", 22, 200)};
        bo.addAll(to);

        System.out.println(bo.min());                                           // Student 1: Kowalska, 18, 100

        ArrayBox<Student> bst = new ArrayBox<>(1);
        bst.add(new Student("Nowacka", 24, 100));
        bst.add(new Student("Nowacka", 24, 300));
        bst.add(new Student("Nowacka", 24, 200));

        System.out.println(bst.max());                                          // Student 5: Nowacka, 24, 300

        ArrayBox<Integer> bi = new ArrayBox<>(2);
        Integer[] ti = new Integer[]{Integer.valueOf(1), 2, 3};
        bi.addAll(ti);

        bi.print();                                                                          // 1, 2, 3

        ArrayBox<String> bs = new ArrayBox<>(1);
        String[] ts = new String[]{"cpp", new String("java")};
        bs.addAll(ts);
        bs.swap(0, 1);
        bs.print();
    }
}

class ArrayBox<T extends Comparable<? super T>> {
    private Object array[];
    private int aktualnyIndex = 0;

    public ArrayBox(int początkowaPojemność) {
        this.array = new Object[początkowaPojemność];
    }

    public boolean add(T element) {
        for (int i = 0; i < array.length; i++) {
            if (element == array[i]) {
                return false;
            }
        }
        sprawdzCzyZwiekszycTablice();
        array[aktualnyIndex++] = element;
        return true;
    }

    public boolean addAll(T[] elementy) {
        List<Boolean> rezultaty = new ArrayList<>();
        for (T element : elementy) {
            boolean result = add(element);
            rezultaty.add(result);
        }
        return rezultaty.contains(true);
    }

    public boolean delete(T element) {
        for (int i = 0; i < aktualnyIndex; i++) {
            if (array[i] == element) {
                for (int j = i; j < aktualnyIndex; j++) {

                    if (j + 1 < array.length) {
                        array[j] = array[j + 1];
                    }

                }
                array[--aktualnyIndex] = null;
                return true;
            }
        }
        return false;
    }

    public boolean swap(int index1, int index2) {
        if (index1 < 0 || index2 < 0 || index1 > aktualnyIndex || index2 > aktualnyIndex) {
            return false;
        }
        Object tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
        return true;

    }

    public void print() {
        System.out.println(Arrays.toString(array));
    }

    public int search(T szukanyElement) {
        for (int i = 0; i < aktualnyIndex; i++) {
            if (array[i] == szukanyElement) {
                return i;
            }
        }
        return -1;
    }

    public T min() {
        Object min = array[0];
        for (int i = 0; i < aktualnyIndex; i++) {
            if (((T) (min)).compareTo((T) (array[i])) > 0) {
                /*
                L > P --> +
                L == P --> 0
                L < P --> -
                 */
                min = array[i];
            }
        }

        return (T) (min);

    }

    public T max() {
        Object max = array[0];
        for (int i = 0; i < aktualnyIndex; i++) {
            if (((T) (max)).compareTo((T) (array[i])) < 0) {
                /*
                L > P --> +
                L == P --> 0
                L < P --> -
                 */
                max = array[i];
            }
        }
        return (T) (max);
    }

    private void sprawdzCzyZwiekszycTablice() {
        if (aktualnyIndex >= array.length) {
            int rozmiarNowejTablicy = (int) (2.5 * array.length);
            Object array2[] = new Object[rozmiarNowejTablicy];
            for (int i = 0; i < array.length; i++) {
                array2[i] = array[i];
            }
            array = array2;
        }
    }

}

class Osoba implements Comparable<Osoba> {
    String nazwisko;
    int wiek;

    public Osoba(String nazwisko, int wiek) {
        this.nazwisko = nazwisko;
        this.wiek = wiek;
    }

    @Override
    public int compareTo(Osoba o) {
        int result = this.nazwisko.compareTo(o.nazwisko);
        if (result == 0) {
            result = this.wiek - o.wiek;
        }

        return result;

    }

    @Override
    public String toString() {
        return "Osoba{" +
                "nazwisko='" + nazwisko + '\'' +
                ", wiek=" + wiek +
                '}';
    }
}

class Student extends Osoba {
    int numerGrupy;

    public Student(String nazwisko, int wiek, int numerGrupy) {
        super(nazwisko, wiek);
        this.numerGrupy = numerGrupy;
    }


    @Override
    public int compareTo(Osoba o) {
        int result = this.nazwisko.compareTo(o.nazwisko);
        if (result == 0) {
            result = this.wiek - o.wiek;
            if (result == 0) {
                result = this.numerGrupy - ((Student) (o)).numerGrupy;
            }
        }
        return result;

        // porównujemy 2 obiekty: this, o
//        if(o == Student)
        // jeśli o jest typu Student to
        // Student s = (Student)o;
        // ... s.nrGrupy()...

        // jak sprawdzić typ:
        // if (o instanceof Student)...
        // lub
        // if (o.getClass() == Student.class)

        // ...
        // jeśli o jest tylko typu Osoba
        // ...
        // ... super.compareTo(o)
    }

    @Override
    public String toString() {
        return "Student{" +
                "nazwisko='" + nazwisko + '\'' +
                ", wiek=" + wiek +
                ", numerGrupy=" + numerGrupy +
                '}';
    }
}
