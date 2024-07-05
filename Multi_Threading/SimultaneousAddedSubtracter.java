package Multi_Threading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SimultaneousAddedSubtracter {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Value value = new Value(0);
        ExecutorService es = Executors.newFixedThreadPool(10);
        Adder adder = new Adder(value);
        Subtracter subtracter = new Subtracter(value);
        Future<Void> adderFuture = es.submit(adder);
        Future<Void> subtracterFuture = es.submit(subtracter);
        adderFuture.get();
        subtracterFuture.get();
        
        System.out.println(value.val);
        
    }
}

class Adder implements Callable<Void>{
    Value value;
    public Adder(Value value){
        this.value = value;
    }
    @Override
    public Void call() {
        for(int i = 1; i <= 100; i++) value.val += i;
        return null;
    }

}

class Subtracter implements Callable<Void>{
    Value value;
    public Subtracter(Value value){
        this.value = value;
    }
    
    @Override
    public Void call() {
        for(int i = 1; i <= 100; i++) value.val -= i;
        return null;
    }
}

class Value{
    int val;
    public Value(int value){
        this.val = value;
    }
}