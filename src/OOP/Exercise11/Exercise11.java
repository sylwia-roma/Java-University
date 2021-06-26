package Exercise11;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        StringTask task = new StringTask("ABC", 50000);
        System.out.println("Task " + task.getState());
        task.start();
//        if (args.length > 0 && args[0].equals("abort")) {
//    /*<- tu zapisać kod przerywający działanie tasku po sekundzie
//         i uruchomic go (tj. kod) w odrębnym wątku (tzn. innym niż main)
//    */
//            new Thread(() -> {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                task.abort();
//            }).start();
//
//        }
        while (!task.isDone()) {
            Thread.sleep(500);
            switch (task.getState()) {
                case RUNNING:
                    System.out.print("R.");
                    break;
                case ABORTED:
                    System.out.println(" ... aborted.");
                    break;
                case READY:
                    System.out.println(" ... ready.");
                    break;
                default:
                    System.out.println("uknown state");
            }

        }
        System.out.println("Task " + task.getState());
        System.out.println(task.getResult().length());
        System.out.println(task.getResult().substring(0, task.getTxt().length()));


    }

}

class StringTask implements Runnable {
    private String napis;
    private int liczba;
    private String napisRezultat = "";
    private TaskState status;

    public StringTask(String napis, int liczba) {
        this.napis = napis;
        this.liczba = liczba;
        status = TaskState.CREATED;
    }

    @Override
    public void run() {
        status = TaskState.RUNNING;
        for (int j = 0; j < liczba; j++) {
            for (int i = napis.length() - 1; i >= 0; i--) {
                napisRezultat += napis.charAt(i);

                if (status == TaskState.ABORTED) {
                    return;
                }
            }

        }
        status = TaskState.READY;
    }

    public String getResult() {
        return napisRezultat;
    }

    public TaskState getState() {
        return status;
    }

    public void start() {
        new Thread(this).start();
    }

    public void abort() {
        status = TaskState.ABORTED;
    }

    public boolean isDone() {
        return status == TaskState.READY || status == TaskState.ABORTED;
    }

    public String getTxt() {
        return napis;
    }
}

enum TaskState {
    CREATED,
    RUNNING,
    ABORTED,
    READY;
}