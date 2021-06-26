package Exercise12;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Buffer buffer = new Buffer(10);
        Consumer consumer = new Consumer(buffer);
        Producer producer = new Producer(buffer);
        Thread consumerThread = new Thread(consumer);
        Thread producerThread = new Thread(producer);
        System.out.println("Consumer thread STARTED");
        consumerThread.start();
        System.out.println("Producer thread STARTED");
        producerThread.start();
        try {
            Thread.sleep(15_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consumerThread.interrupt();
        System.out.println("Consumer thread STOPPED");
        producerThread.interrupt();
        System.out.println("Producer thread STOPPED");
    }
}

class Buffer {
    private int[] tablica;
    int aktualnyWskaznik=0;

    public Buffer(int rozmiar) {

        this.tablica = new int[rozmiar];
    }

    int get(){
        for(int i=0; i< tablica.length-1; i++) {
            tablica[i]=tablica[i+1];
        }
        aktualnyWskaznik--;

        return tablica[0];
    }

    void put(int liczba){

        tablica[aktualnyWskaznik++]=liczba;
    }

    boolean czyBuforPusty(){
        return aktualnyWskaznik==0;
    }

    boolean czyBuforPelny(){
        return aktualnyWskaznik>=tablica.length;
    }
}
class Producer implements Runnable{
    private Buffer b;


    public Producer(Buffer b) {
        this.b = b;
    }

    @Override
    public void run() {

        while(true){
            synchronized (b) {
                if (b.czyBuforPelny()) {
                    System.out.println("Bufer jest pełny. Producer czeka na dodanie liczby");
                }

                int liczba = new Random().nextInt(1_000);

                b.put(liczba);
                System.out.println("Producer dostarczył liczbę " + liczba);
            }
            int czasUśpienia = new Random().nextInt(2_000);
            try {
                System.out.println("Producer usypia na " + czasUśpienia);
                Thread.sleep(czasUśpienia);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable{
    private Buffer b;

    public Consumer(Buffer b) {
        this.b = b;
    }


    @Override
    public void run() {
        while(true){
            synchronized (b) {
                if (b.czyBuforPusty()) {
                    System.out.println("Bufer jest pusty. Konsumer czeka na pobranie liczby");
                }
                int pobranaLiczba = b.get();
                System.out.println("Consumer pobrał liczbę");
            }

            int czasUśpienia = new Random().nextInt(2_000);
            try {
                System.out.println("Consumer usypia na " + czasUśpienia);
                Thread.sleep(czasUśpienia);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
