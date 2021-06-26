package Exercise1;

public class Main {
    public static void main(String[] args) {
        Muzyk[] muzycy = {new Skrzypek("Aleks", 2),                // Imie, czas wystapienia (w godz.)
                new Wiolonczelista("Bartek", 1),
                new Flecista("Czarek", 0.5)};
        for (Muzyk m : muzycy)
            System.out.println("Muzyk: " + m.nazwisko() + '\n' +
                    "Instrument: " + m.instrument() + '\n' +
                    "Czas wystąpienia: " + m.czas() + " godz. " + '\n' +
                    "Stawka godzinowa: " + m.stawka() + '\n');

        System.out.println(Muzyk.maxHonorarium(muzycy));        // muzyk otrzymujący najwyższe honorarium za występ
    }
}

abstract class Muzyk {

    private String nazwisko;
    private double czas;

    // konstruktor
    protected Muzyk(String nazwisko, double czas) {
        this.nazwisko = nazwisko;
        this.czas = czas;
    }

    // metoda getter
    protected String nazwisko() {
        return nazwisko;
    }


    // metoda getter
    protected double czas() {
        return czas;
    }



    // metody abstrakcyjne
    abstract protected String instrument();
    abstract protected int stawka();


    @Override
    public String toString() {
        return "Muzyk{" +
                "nazwisko='" + nazwisko + '\'' +
                ", czas=" + czas +
                '}';
    }



    public static Muzyk maxHonorarium(Muzyk[] muzycy)
    {
        double max = 0;
        int tmp = 0;
        for(int i =0; i < muzycy.length; i++) {
            if((muzycy[i].stawka() * muzycy[i].czas) > max) {
                tmp=i;
                max=(muzycy[i].stawka() * muzycy[i].czas);
            }
        }

        return muzycy[tmp];



    }

}

// podklasa dziedzicząca po klasie abstrakcyjnej
class Flecista extends Muzyk {

    // konstruktor
    public Flecista(String nazwisko, double czas) {
        super(nazwisko, czas);
    }

    @Override
    public String instrument() {
        return "Flet";
    }

    @Override
    public int stawka() {
        return 300;
    }
}

class Skrzypek extends Muzyk {

    // konstruktor
    public Skrzypek(String nazwisko, double czas) {
        super(nazwisko, czas);
    }

    @Override
    public String instrument() {
        return "Skrzypce";
    }

    @Override
    public int stawka() {
        return 200;
    }
}

class Wiolonczelista extends Muzyk {

    // konstruktor
    public Wiolonczelista(String nazwisko, double czas) {
        super(nazwisko, czas);
    }

    @Override
    public String instrument() {
        return "Wiolonczela";
    }

    @Override
    public int stawka() {
        return 250;
    }
}

