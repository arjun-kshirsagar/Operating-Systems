package Multi_Threading;
// A java program will run on a single thread by default
// JVM => Java Vitual Machine

import java.util.concurrent.ThreadFactory;

public class Multi_Threading {
    public static void main(String[] args) {
        // Thread currentThread = Thread.currentThread();
        // System.out.println("Hello, World!");
        // System.out.println("Current Thread Address: " + currentThread);
        // System.out.println("Current Thread Name: " + Thread.currentThread().getName());
        // This will return the 'main'  thread.

        // If we want to assign a different thread, we can create a new thread
        // We need to create a task, to create a new thread, and assign the task to the thread
        // if we want to change the thread
        // we can create new class

        // thread is a unit of execution
        // HelloWorldPrinter hwp = new HelloWorldPrinter();
        // hwp.run();  // this will not create a new thread, it will run on the same thread
        // here it is directly executing the code in the main thread, it is the main thread, which is calling that function and executing it

        // hwp.start(); // this will create a new thread and run the code which is inside the run method

        // If we want to print hello world two time with two different threads, then we can create another object for it
        // HelloWorldPrinter hwp1 = new HelloWorldPrinter();
        // hwp1.start();

        // NumberPrinter np1 = new NumberPrinter();
        // np1.start();

        // NumberPrinter np2 = new NumberPrinter();
        // np2.start(); // now the output when both np1 and np2 are running, will be mixed up and can be in any order
        // this depends on the scheduler, which thread will run first, and which will run next

        // for(int i = 1; i <= 100; i++){
        //     // SingleNumberPrinter snp = new SingleNumberPrinter();
        //     SingleNumberPrinter snp = new SingleNumberPrinter(i);
        //     snp.start();
        // }

        for(int i = 0; i <= 100; i++){
            SingleNumberPrinterV2 task = new SingleNumberPrinterV2(i);
            Thread th = new Thread(task); // as a parameter of the thread, we can pass an object of a class which implements the Runnable interface
            th.start();
        }

    }
}


// Google to find and write a code, to print hello world and the name of the thread
class HelloWorldPrinter extends Thread {
    // Naming Convention: Name of a class should be a noun
    @Override
    public void run() {
        System.out.println("Hello, World!" + " My Thread Name is: " + Thread.currentThread().getName());
        // There are two types of threads: User Thread and Kernel Thread
        // There both have many to many relationship 
    }
}


// Print numbers from 1 to 10 using a different thread
class NumberPrinter extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(i + " from thread: " + Thread.currentThread().getName());
        }
    }
}


// Print numbers from 1 to 100 each with a diffrent thread
class SingleNumberPrinter extends Thread{
    // to solve this problem, we can create a constructor of this object and pass the value to be printed and store that value
    int num;
    public SingleNumberPrinter(int num){
        // now the numbers all the nums from 1 to 100 will be printed but their ordering is still not guraanteed
        // you will go the multi threading way only when the ordering doesnt matter, 
        // in the merge sort case, it doesnt matter if we sort the left half first or the right half first,
        this.num = num;
    }


    // static int num = 0; // this variable will be shared by all the objects of this class,
                        // every time we call the run method, it will increment the value of num, no matter what object is calling it
    @Override
    public void run(){
        // num++;  // this code will work, but if we make this num++, below the print statement then it wont work
        // we cant even trust keeping num above the print statement, 

        // the issue here is that, multiple(100) threads are working at the same time on the same variable, 
        // so when num was below, all came on this line, they saw num to be 0 and then they moved to 100, even before
        // any of them could print the value of num and increment it
        System.out.println(num + " from thread: " + Thread.currentThread().getName());
        // num++;  // this returns always 0 from each thread

        // if we add a sleep statement, then it will not work, it will print some numbers extra
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


// Create a student class with a run method
class Student extends Thread {      // in java, we can only extend one class, but we can implement multiple interfaces
    // in java we genrally inherit propeties and methods from a parent class, we cant do this here
    // we cant extend it to a User class, bcoz its already extending Thread class

    // this will cause a class Diamond inheritance problem, which is not allowed in java
    @Override
    // we are overriding the run method of the Thread class,
    // the run method in the Thread class is empty, it doesnt do anything
    public void run() {
        System.out.println("Student is studying");
    }
}


class SingleNumberPrinterV2 implements Runnable{
    // when this is empty there is a red line under the classname, bcoz we must implement the run method
    // interface is a contract, if we implement an interface, we must implement all the methods of that interface
    int num;
    public SingleNumberPrinterV2(int num){
        this.num = num;
    }

    @Override
    public void run() {
        if(num == 0) throw new RuntimeException("Number cant be 0");
            // one of the main advantages of multi threading is that,
            // if one of the threads break, it wont stop the complete program
            // this did not break the code, only one of the threads broke at num = 0
        System.out.println(100 + " from thread: " + Thread.currentThread().getName());
    }

}




/*
 * DOUBTS
 * Hyper threading: Two different threads in a single core (processor)
 */

// you will go the multi threading way only when the ordering doesnt matter, 
// in the merge sort case, it doesnt matter if we sort the left half first or the right half first,
// most of the times, when we want a sequential ordering the we will go the single threading way

