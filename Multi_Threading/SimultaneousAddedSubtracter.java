/*
 * This program only gives random values when running a multithreaded program without locks only once at the beginning.
 */


package Multi_Threading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimultaneousAddedSubtracter {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(10);

        // Value value2 = new Value(0);
        // AdderWithoutLock adder2 = new AdderWithoutLock(value2);
        // SubtracterWithoutLock subtracter2 = new SubtracterWithoutLock(value2);

        // Future<Void> adderFuture2 = es.submit(adder2);
        // Future<Void> subtracterFuture2 = es.submit(subtracter2);
        // adderFuture2.get();
        // subtracterFuture2.get();

        // System.out.println("Without lock Above1: " + value2.val);



        Value value4 = new Value(0);
        AdderWithoutLock adder4 = new AdderWithoutLock(value4);
        SubtracterWithoutLock subtracter4 = new SubtracterWithoutLock(value4);
        
        Future<Void> adderFuture4 = es.submit(adder4);
        Future<Void> subtracterFuture4 = es.submit(subtracter4);
        adderFuture4.get();
        subtracterFuture4.get();

        System.out.println("Without lock Above2: " + value4.val);



        // Value value = new Value(0);
        // // Lock lock = new ReentrantLock();
        // // We dont need the lock object as we can use the object itself as a lock of the shared resource

        // AdderWithSynchronisation adder = new AdderWithSynchronisation(value);
        // SubtracterWithSynchronisation subtracter = new SubtracterWithSynchronisation(value);

        // Future<Void> adderFuture = es.submit(adder);
        // Future<Void> subtracterFuture = es.submit(subtracter);
        // adderFuture.get();
        // subtracterFuture.get();
        
        // System.out.println("With lock " + value.val);




        Value value3 = new Value(0);
        AdderWithoutLock adder3 = new AdderWithoutLock(value3);
        SubtracterWithoutLock subtracter3 = new SubtracterWithoutLock(value3);

        Future<Void> adderFuture3 = es.submit(adder3);
        Future<Void> subtracterFuture3 = es.submit(subtracter3);
        adderFuture3.get();
        subtracterFuture3.get();

        System.out.println("Without lock Below " + value3.val);
        es.shutdown();
    }
}

class AdderWithSynchronisation implements Callable<Void>{
    Value value;
    public AdderWithSynchronisation(Value value){
        this.value = value;
    }
    @Override
    public Void call() {
        for(int i = 1; i <= 1000; i++){
            synchronized(value){
                value.val += i;
            }
        }
        return null;
    }

}

class AdderWithoutLock implements Callable<Void>{
    Value value;
    public AdderWithoutLock(Value value){
        this.value = value;
    }
    @Override
    public Void call() {
        for(int i = 1; i <= 1000; i++){
            value.val += i;
        }
        return null;
    }

}

class SubtracterWithSynchronisation implements Callable<Void>{
    Value value;
    public SubtracterWithSynchronisation(Value value){
        this.value = value;
    }
    
    @Override
    public Void call() {
        for(int i = 1; i <= 1000; i++){
            synchronized(value){
                value.val -= i;
            }
        }
        return null;
    }
}

class SubtracterWithoutLock implements Callable<Void>{
    Value value;
    public SubtracterWithoutLock(Value value){
        this.value = value;
    }
    
    @Override
    public Void call() {
        for(int i = 1; i <= 1000; i++){
            value.val -= i;
        }
        return null;
    }
}

class Value{
    int val;
    public Value(int value){
        this.val = value;
    }
}