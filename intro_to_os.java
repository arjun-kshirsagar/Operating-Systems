class HelloWorldPrinter extends Thread {
    // Naming Convention: Name of a class should be a noun
    @Override
    public void run() {
        System.out.println("Hello, World!" + " My Thread Name is: " + Thread.currentThread().getName());
    }
}

public class intro_to_os {
    public static void main(String[] args) {
        Thread currentThread = Thread.currentThread();
        System.out.println("Hello, World!");
        System.out.println("Current Thread Address: " + currentThread);
        System.out.println("Current Thread Name: " + currentThread.getName());
        // JVM => Java Vitual Machine

        // if we want to change the thread
        // we can create new class

        // thread is a unit of execution
        HelloWorldPrinter hwp = new HelloWorldPrinter();
        hwp.start();

        HelloWorldPrinter hwp1 = new HelloWorldPrinter();
        hwp1.start();
    }
}