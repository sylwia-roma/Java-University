package Exercise7;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        Śpiewak s1 = new Śpiewak("Dietrich"){
            @Override
            String spiewaj() {
                return "oooooooooooo";
            }
        };

        Śpiewak s2 = new Śpiewak("Piaf"){
            @Override
            String spiewaj() {
                return "a4iBBiii";
            }
            /*<-  kod */
        };

        Śpiewak s3 = new Śpiewak("Adele"){
            @Override
            String spiewaj() {
                return "aAa";
            }

        };

        Śpiewak sp[] = {s1, s2, s3};

        for (Śpiewak s : sp)
            System.out.println(s);

        System.out.println("\n" + Śpiewak.najglosniej(sp));
///////////////////// ZADANIE 7 TEST


        Śpiewak s4 = new Śpiewak("Darrey"){
            @Override
            String spiewaj() {
                return "eeae";
            }
        };

        Śpiewak s5 = new Śpiewak("Darrey"){
            @Override
            String spiewaj() {
                return "bebe";
            }
            /*<-  kod */
        };

        Śpiewak s6 = new Śpiewak("Houston"){
            @Override
            String spiewaj() {
                return "a4iBBiii";
            }

        };

        Śpiewak s7 = new Śpiewak("Carrey"){
            @Override
            String spiewaj() {
                return "oaooooooooooo";
            }

        };

        Śpiewak s8 = new Śpiewak("Madonna"){
            @Override
            String spiewaj() {
                return "aAa";
            }

        };

        ArrayList<Śpiewak> spiewak = new ArrayList<Śpiewak>();

        spiewak.add(s4);
        spiewak.add(s5);
        spiewak.add(s6);
        spiewak.add(s7);
        spiewak.add(s8);

        Collections.sort(spiewak);

        System.out.println("Posortowane");
        for (Śpiewak s: spiewak) {
            System.out.println(s);
        }
    }
}

abstract class Śpiewak implements Comparable<Śpiewak> {
    protected String nazwisko;
    protected int nrStartowy;
    private static int counter = 1;


    public Śpiewak(String nazwisko) {
        this.nazwisko = nazwisko;
        this.nrStartowy = counter++;
    }



    abstract String spiewaj();

    @Override
    public String toString() {
        return nazwisko + ": "
                + spiewaj();
    }

    //c. statyczną:... najglosniej(...) przyjmującą tablicęo biektów-śpiewaków oraz zwracającą obiekt-śpiewaka,
    // w którego śpiewanym tekście znajduje się najwięcej różnych liter (np. w tekście zwróconymprzez metodę spiewaj()
    // - "Bye bye 2020" występują 3 różne litery: B/b, y, e).

    public static Śpiewak najglosniej(Śpiewak [] śpiewacy){
        Śpiewak max = śpiewacy[0];
        int maxRóżneLitery =0;

        for(int i=0; i< śpiewacy.length; i++){

            int iloscRoznychLiter = getIloscRoznychLiter(śpiewacy[i]);

            if(iloscRoznychLiter>maxRóżneLitery) {
                max=śpiewacy[i];
                maxRóżneLitery=iloscRoznychLiter;

            }
        }
        return max;
    }

    private static int getIloscRoznychLiter(Śpiewak śpiewak) {
        int[] iloscWystapien = new int[200];
        String sp = śpiewak.spiewaj().toUpperCase();
        int iloscRoznychLiter = 0;
        for(int j=0; j<sp.length(); j++){
            char c = sp.charAt(j);
            iloscWystapien[c]++;
            if(c>='A'&&c<='Z'){
                if(iloscWystapien[c]==1) {
                    iloscRoznychLiter++;
                }

            }
        }
        return iloscRoznychLiter;
    }

    @Override
    public int compareTo(Śpiewak o) {
        int result = getIloscRoznychLiter(o) - getIloscRoznychLiter(this);
        if(result==0) {
            result=this.spiewaj().compareTo(o.spiewaj());
            if(result==0) {
                result=this.nrStartowy-o.nrStartowy;
            }
        }
        return result;
    }
}
