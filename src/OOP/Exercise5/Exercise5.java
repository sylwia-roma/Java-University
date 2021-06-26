package Exercise5;

import java.util.ArrayList;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        List<Figura> figury = new ArrayList<Figura>();

        figury.add(new Kolo(1, 1, 5));
        //figury.add(new Kolo(10, 5, 10));
        figury.add(new Prostokat(1, 1, 4, 8));
        figury.add(new Prostokat(2, 2, 4, 8));


        System.out.println("Przed sortowaniem");

        for (Figura f : figury) {
            System.out.println(f);
   }

        System.out.println("***********");
        System.out.println("***********");
        System.out.println("***********");
        System.out.println("***********");
        System.out.println("Po sortowaniu");

        Collections.sort(figury);

        for (Figura f : figury) {
            System.out.println(f);

        }

    }
}
abstract class Figura implements Obliczenie, Comparable<Figura>{
    public int x;
    public int y;
    protected double pole;
    protected double obwód;
    protected static int globalIndex;
    protected int index;

    public Figura(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public double getPole() {
        return pole;
    }

    public double getObwód() {
        return obwód;
    }

    public int getIndex() {
        return index;
    }

    protected abstract String returnName();
    protected abstract void pozycja(int x, int y);

    @Override
    public String toString() {
        return returnName() + '\n';
    }


}

class Kolo extends Figura {
    protected int promien;

    public Kolo(int x, int y, int promien) {
        super(x, y);
        this.promien = promien;
        super.obwód = obliczObwód();
        super.pole = obliczPole();
        this.index = globalIndex++;
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

    @Override
    public int compareTo(Figura f) {
        if (!(this.obwód == f.obwód)) {
            return (int) (this.obwód - f.obwód);
        } else if (!(this.pole == f.pole)) {
            return (int) (this.pole - f.pole);
        } else {
            return this.index - f.index;
        }
    }
}

class Kolo2 extends Kolo implements Transformacja{
    int prevValueX;
    int prevValueY;

    public Kolo2(int x, int y, int promien) {
        super(x, y, promien);
    }

    @Override
    public void przesunDo(int x, int y) {
        prevValueX = this.x;
        prevValueY = this.y;
        this.x = x;
        this.y = y;
    }

    @Override
    public void powrot() {
        this.x = prevValueX;
        this.y = prevValueY;
    }
}

class Prostokat extends Figura{
    protected int szerokość;
    protected int wysokość;

    public Prostokat(int x, int y, int szerokość, int wysokość) {
        super(x, y);
        this.szerokość = szerokość;
        this.wysokość = wysokość;
        super.obwód = obliczObwód();
        super.pole = obliczPole();
        this.index = globalIndex++;
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
        return super.toString()
                + "Lewy górny - (" + x + "," + y + ") \n"
                + "Szerokość: " + szerokość + "," + "Wysokość: " + wysokość + "\n";
    }

    @Override
    public double obliczPole() {
        return szerokość * wysokość;
    }

    @Override
    public double obliczObwód() {
        return 2*szerokość + 2*wysokość;
    }

    @Override
    public int compareTo(Figura f) {
        if (!(this.obwód == f.obwód)) {
            return (int) (this.obwód - f.obwód);
        } else if (!(this.pole == f.pole)) {
            return (int) (this.pole - f.pole);
        } else {
            return this.index - f.index;
        }
    }
}

class Prostokat2 extends Prostokat implements Rysowanie{
    private char znak;


    public Prostokat2(int x, int y, int szerokość, int wysokość, char znak) {
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