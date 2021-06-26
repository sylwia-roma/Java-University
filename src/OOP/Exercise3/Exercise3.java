package Exercise3;

public class Main {

    public static void main(String[] args) {
        Figura fig[] = new Figura[2];
        fig[0] = new Koło(10, 10, 5);                    // położenie koła = srodek = (10,10), promień = 5
        fig[1] = new Prostokąt(20, 20, 15, 10);    // położenie prostokąta = lewy górny wierzchołek = (20,20), szerokość = 15, wysokość = 10

        // polimorficzne wywołanie metody toString() z klas Kolo/Prostokat,
        // a nie z klasy Figura
        for (Figura f : fig)              // pętla for-each
            System.out.println(f);    // System.out.println(f.toString());

        fig[0].pozycja(12, 12);
        fig[1].pozycja(25, 30);
    }
}

abstract class Figura {
    protected int x;
    protected int y;

    public Figura(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected abstract String returnName();
    protected abstract void pozycja(int x, int y);

    @Override
    public String toString() {
        return returnName() + "\n";
    }
}

class Koło extends Figura{

    private int promien;

    public Koło(int x, int y, int promien) {
        super(x, y);
        this.promien = promien;
    }

    @Override
    protected String returnName() {
        return "Koło";
    }

    @Override
    protected void pozycja(int x, int y) {
        double odleglosPunktuOdSrodkaPola = Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
        //Punkt (12,12) znajduje się wewnątrz koła
        if (odleglosPunktuOdSrodkaPola <= promien) {
            System.out.println("Punkt (" + x + "," + y + ") znajduje się wewnątrz koła");
        } else {
            System.out.println("Punkt (" + x + "," + y + ") nie znajduje się wewnątrz koła");
        }

    }

    @Override
    public String toString() {
        return super.toString() +
                "Środek - (" + x + "," + y + ") \n" +
                "Promień - " + promien + "\n";
    }
}

class Prostokąt extends Figura{
    private int szerokość;
    private int wysokość;

    public Prostokąt(int x, int y, int szerokość, int wysokość) {
        super(x, y);
        this.szerokość = szerokość;
        this.wysokość = wysokość;
    }

    @Override
    protected String returnName() {
        return "Prostokąt";
    }

    @Override
    protected void pozycja(int x, int y) {
        if(x<=szerokość&&y<=wysokość){
            System.out.println("Punkt (" + x + "," + y + ") znajduje się wewnątrz prostokąta");
        } else{
            System.out.println("Punkt (" + x + "," + y + ") znajduje się na zewnątrz prostokąta");
        }
    }

    @Override
    public String toString() {
        return super.toString()
                + "Lewy górny - (" + x + "," + y + ") \n"
                + "Szerokość: " + szerokość + "," + " Wysokość: " + wysokość + "\n";
    }
}