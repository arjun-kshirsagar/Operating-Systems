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
        Value value = new Value(0);
        ExecutorService es = Executors.newFixedThreadPool(10);

        Lock lock = new ReentrantLock();
        Adder adder = new Adder(value, lock);
        Subtracter subtracter = new Subtracter(value, lock);
        Future<Void> adderFuture = es.submit(adder);
        Future<Void> subtracterFuture = es.submit(subtracter);
        adderFuture.get();
        subtracterFuture.get();
        
        System.out.println(value.val);
        
    }
}

class Adder implements Callable<Void>{
    Lock lock;
    Value value;
    public Adder(Value value, Lock lock){
        this.lock = lock;
        this.value = value;
    }
    @Override
    public Void call() {
        for(int i = 1; i <= 100; i++){
            lock.lock();
            value.val += i;
            lock.unlock();
        }
        return null;
    }

}

class Subtracter implements Callable<Void>{
    Value value;
    Lock lock;
    public Subtracter(Value value, Lock lock){
        this.value = value;
        this.lock = lock;
    }
    
    @Override
    public Void call() {
        for(int i = 1; i <= 100; i++){
            lock.lock();
            value.val -= i; 
            lock.unlock();
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