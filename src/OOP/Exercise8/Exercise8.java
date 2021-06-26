package Exercise8;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Main {

    public static void main(String [] args)
    {
        IterNap napis = new IterNap("prOgrAmoWanIe ObiEktOwe i Gui");

        // iteracja po znakach napisu,
        // domyślnie zaczynając od pierwszego znaku (o indeksie 0)
        // i z krokiem iteracji = 1
        for (char z: napis)
            System.out.print(z + " ");

        System.out.println();

        napis.ustawPoczatek(2);     // ustawienie początku iteracji (tu: 2-gi znak, o indeksie 2)
        napis.ustawKrok(3);         // ustawienie kroku iteracji (tu: co 3-ci znak)

        // iteracja po znakach napisu,
        // od ustalonego znaku, z określonym krokiem
        for (char z: napis)
            System.out.print(z + " ");


	/*<-  co tu trzeba napisać w wywołaniu metody forEach z argumentem będącym lambda-wyrażeniem
	      w celu wyświetlenia znaków napisu (w postaci małych liter) zgodnie z iteracją

	napis.forEach(...)
	*/

        napis.ustawPoczatek(2);     // ustawienie początku iteracji (tu: 2-gi znak, o indeksie 2)
        napis.ustawKrok(3);         // ustawienie kroku iteracji (tu: co 3-ci znak)
        System.out.println();

        napis.forEach(c -> System.out.print(Character.toLowerCase(c) + " "));



        /*
        Consumer - przyjmuje argument i cos z nim robi          accept()
        Function - przyjmuje argument i zwraca obiekt           apply()
        Predicate - ktory przyjmuje obiekt i zwraca booleana    test()
        Supplier - nic nie przyjmuje i zwraca obiekt            get()
         */



    }
}


class IterNap implements Iterable<Character> {
    protected String napis;

    private int wskaznik = 0;
    private int krok = 1;

    public IterNap(String napis) {
        this.napis = napis;
    }

    @Override
    public void forEach(Consumer<? super Character> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Character> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public Iterator<Character> iterator() {
        return new Iterator<Character>() {
            @Override
            public boolean hasNext() {
                return wskaznik < napis.length();
            }

            @Override
            public Character next() {
                char zwracanyChar = napis.charAt(wskaznik);
                wskaznik += krok;
                return zwracanyChar;
            }
        };
    }

    public void ustawPoczatek(int poczatek) { // zmienia pozycje wskaznika
        this.wskaznik = poczatek;
    }

    public void ustawKrok(int krok) {
        this.krok = krok;
    }
}
