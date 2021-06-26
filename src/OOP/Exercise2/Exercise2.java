package Exercise2;


class Main {

    public static void main(String[] args) {


        Flyable f[] = {new Bird(), new Virus(), new Plane(), new UFO()};
        Speakable s[] = {new Bird(), new Plane(), new UFO()};


        System.out.println((shortest(f)));
        System.out.println((loudest(s)));
    }


    private static Flyable shortest(Flyable[] f) {
        double max = Double.MAX_VALUE;
        int tmp = 0;
        for (int i = 0; i < f.length; i++) {
            if (f[i].distance() > max) {
                max = f[i].distance();
                tmp = i;
            }
        }
        return f[tmp];
    }


    private static Speakable loudest(Speakable[] s) {
        int max = 0;
        int tmp = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i].speak().length() > max) {
                max = s[i].speak().length();
                tmp = i;
            }
        }
        return s[tmp];
    }


}


interface Flyable {

    public double distance();

    public String drive();

}

interface Speakable {
    public String speak();
}

class Bird implements Flyable, Speakable {

    //...
    @Override
    public String speak() {
        // TODO Auto-generated method stub
        return "Cwierkanie";
    }

    @Override
    public double distance() {
        // TODO Auto-generated method stub
        return 1000;
    }

    @Override
    public String drive() {
        // TODO Auto-generated method stub
        return "Skrzydła";
    }

    @Override
    public String toString() {

        return getClass().getName();
    }

}


class Virus implements Flyable {

    //...

    @Override
    public double distance() {
        // TODO Auto-generated method stub
        return 0.01;
    }

    @Override
    public String drive() {
        // TODO Auto-generated method stub
        return "Osłonka";
    }

    @Override
    public String toString() {
        return getClass().getName();
    }

}

class UFO implements Flyable, Speakable {

    //...
    @Override
    public String speak() {
        // TODO Auto-generated method stub
        return "Brzęk";
    }

    @Override
    public double distance() {
        // TODO Auto-generated method stub
        return 200000000;
    }

    @Override
    public String drive() {
        // TODO Auto-generated method stub
        return "Skrzydła ufa";
    }

    @Override
    public String toString() {
        return getClass().getName();
    }

}

class Plane implements Flyable, Speakable {

    //...
    @Override
    public String speak() {
        // TODO Auto-generated method stub
        return "Odgłos samolotu";
    }

    @Override
    public double distance() {
        // TODO Auto-generated method stub
        return 3000;
    }

    @Override
    public String drive() {
        // TODO Auto-generated method stub
        return "Skrzydła samolotu";
    }

    @Override
    public String toString() {
        return getClass().getName();

    }

}
