package Exercise4;

public class Main {

    public static void main(String[] args)
    {
//        Figura fig[] = new Figura[2];
//        fig[0] = new Koło(10, 10, 5);                    // położenie koła = srodek = (10,10), promień = 5
//        fig[1] = new Prostokąt(20, 20, 15, 10);    // położenie prostokąta = lewy górny wierzchołek = (20,20), szerokość = 15, wysokość = 10
//
//        // polimorficzne wywołanie metody toString() z klas Kolo/Prostokat,
//        // a nie z klasy Figura
//        for (Figura f : fig)              // pętla for-each
//            System.out.println(f);    // System.out.println(f.toString());
//
//        fig[0].pozycja(12, 12);
//        fig[1].pozycja(25, 30);
//
//        double obwódFig0 = fig[0].obliczObwód();
//        double poleFig0 = fig[0].obliczPole();
//
//        double obwódFig1 = fig[1].obliczObwód();
//        double poleFig1 = fig[1].obliczPole();
//
//        System.out.println("Obwód fig 0 = " + obwódFig0);
//        System.out.println("Pole fig 0 = " + poleFig0);
//        System.out.println("Obwód fig 1 = " + obwódFig1);
//        System.out.println("Pole fig 1 = " + poleFig1);

//        Kolo2 k2 = new Kolo2(15, 20, 5);
//
//        k2.przesunDo(50, 40);    // przesunięcie środka koła do punktu (50, 40)
//        System.out.println(k2);
//
//        k2.powrot();                    // powrót do poprzedniej pozycji (bezpośrednio przed przesunięciem) środka koła
//        System.out.println(k2);

        Koło2 k2 = new Koło2(15, 20, 5);

        k2.przesunDo(50, 40);    // przesunięcie środka koła do punktu (50, 40)
        System.out.println(k2);

        k2.powrot();                    // powrót do poprzedniej pozycji (bezpośrednio przed przesunięciem) środka koła
        System.out.println(k2);

//        Figura p2 = new Prostokąt2(20, 20, 10, 5, '*');       // prostokąt rozmiaru 10 x 5 będzie "rysowany" na konsoli za pomocą znaku '*'
//        ((Prostokąt2)p2).rysuj();                                          // Wyjaśnić dlaczego p2.rysuj() nie działa?

    }
}

abstract class Figura implements Obliczenie{
    public int x;
    public int y;

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
    protected int promien;

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
            System.out.println("Punkt (" + x + "," + y + ")znajduje się wewnątrz koła");
        } else {
            System.out.println("Punkt (" + x + "," + y + ") nie znajduje się wewnątrz koła");
        }

    }

    @Override
    public String toString() {
        return super.toString() +
                "Środek - (" + x + "," + y + ") \n " +
                "Promień - " + promien + "\n";
    }

    @Override
    public double obliczPole() {
        return Math.PI * Math.pow(promien, 2);
    }

    @Override
    public double obliczObwód() {
        return 2*Math.PI * promien;
    }


    //Koło
    //Środek - (10,10)
    //Promień - 5
}

class Koło2 extends Koło implements Transformacja{
    public Koło2(int x, int y, int promien) {
        super(x, y, promien);
    }

    @Override
    public void przesunDo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void powrot() {


    }
}

interface Obliczenie {

    public abstract double obliczPole();
    public abstract double obliczObwód();
}

interface Rysowanie {
    public abstract void rysuj();
}

interface Transformacja {

    public abstract void przesunDo(int x, int y);
    public abstract void powrot();
}

class Prostokąt extends Figura{
    protected int szerokość;
    protected int wysokość;

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
            System.out.println("Punkt (" + x + "," + y + ")znajduje się wewnątrz prostokąta");
        } else{
            System.out.println("Punkt (" + x + "," + y + ")znajduje się na zewnątrz prostokąta");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n"
                + "Lewy górny - (" + x + "," + y + ") \n"
                + "Szerokość: " + szerokość + "," + "Wysokość: " + wysokość;
    }

    @Override
    public double obliczPole() {
        return szerokość * wysokość;
    }

    @Override
    public double obliczObwód() {
        return 2*szerokość + 2*wysokość;
    }
}

class Prostokąt2 extends Prostokąt implements Rysowanie{
    private char znak;


    public Prostokąt2(int x, int y, int szerokość, int wysokość, char znak) {
        super(x, y, szerokość, wysokość);
        this.znak = znak;
    }



    @Override
    public void rysuj() {

        for(int i=0; i<wysokość; i++)
        {
            for(int j=0; j<=szerokość; j++)
            {
                System.out.print(znak);
            }
            System.out.println();
        }
    }
}
