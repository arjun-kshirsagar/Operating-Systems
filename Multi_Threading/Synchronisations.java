package Multi_Threading;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Synchronisations {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<ArrayList<Integer>> doubledList = es.submit(new DoubleTheArrayList(list));
        // we need a promise from the thread, that what will be the output of the thread, it will fill it
        System.out.println(doubledList.get());
    }
    
}

// write a method, to double the values of a list of integers
class DoubleTheArrayList implements Callable<ArrayList<Integer>> {
    private ArrayList<Integer> list;
    public DoubleTheArrayList(ArrayList<Integer> list){
        this.list = list;
    }
    private ArrayList<Integer> doubledList = new ArrayList<>();
    @Override
    public ArrayList<Integer> call() throws Exception {
        for(int i = 0; i < list.size(); i++){
            doubledList.add(list.get(i) * 2);
        }
        return doubledList;
    }
}




// es.submit(task), whats the argument to the submit method?
// We were passing the data through the constructor of the task, and then we were submitting the task to the executor service.