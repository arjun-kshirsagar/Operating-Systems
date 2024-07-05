package Multi_Threading;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MergeSort {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(10000);
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < 10000; i++){
            list.add((int)(Math.random() * 1000));
        }
        // list.add(3); list.add(1); list.add(5); list.add(2); list.add(4); list.add(19); list.add(0); list.add(7);

        Sorter task = new Sorter(list);
        Future<ArrayList<Integer>> sortedList = es.submit(task);
        System.out.println(sortedList.get());   // this get function will wait, until the list is populated
        
    }
}

class Sorter implements Callable<ArrayList<Integer>>{
    ArrayList<Integer> listToSort;
    // constructor 
    public Sorter(ArrayList<Integer> listToSort){
        this.listToSort = listToSort;
    }

    @Override
    public ArrayList<Integer> call() throws InterruptedException, ExecutionException {
        if(listToSort.size() <= 1){
            return listToSort;
        }
        int mid = listToSort.size() / 2;
        ArrayList<Integer> leftHalfToSort = getSubArray(listToSort, 0, mid);
        ArrayList<Integer> rightHalfToSort = getSubArray(listToSort, mid, listToSort.size());

        // Executor service to sort the two halves in parallel
        ExecutorService es = Executors.newFixedThreadPool(20000);

        // Create two subtasks with two sublists to assign them to different threads
        Sorter lefSorter = new Sorter(leftHalfToSort);
        Sorter rightSorter = new Sorter(rightHalfToSort);

        // execute this in multithreaded way
        Future<ArrayList<Integer>> leftSortedFuture =  es.submit(lefSorter);
        Future<ArrayList<Integer>> rightSortedFuture =  es.submit(rightSorter);

        ArrayList<Integer> leftSorted = leftSortedFuture.get();
        ArrayList<Integer> rightSorted = rightSortedFuture.get();

        return merge(leftSorted, rightSorted);
    }

    private ArrayList<Integer> getSubArray(ArrayList<Integer> list, int start, int end){
        ArrayList<Integer> subArray = new ArrayList<>();
        for(int i = start; i < end; i++){
            subArray.add(list.get(i));
        }
        return subArray;
    }

    private ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right){
        ArrayList<Integer> merged = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;
        while(leftIndex < left.size() && rightIndex < right.size()){
            if(left.get(leftIndex) < right.get(rightIndex)){
                merged.add(left.get(leftIndex));
                leftIndex++;
            } else {
                merged.add(right.get(rightIndex));
                rightIndex++;
            }
        }
        while(leftIndex < left.size()){
            merged.add(left.get(leftIndex));
            leftIndex++;
        }
        while(rightIndex < right.size()){
            merged.add(right.get(rightIndex));
            rightIndex++;
        }
        return merged;
    }
    
}

// Multi-threaded merge sort is way slower than the sequential single threaded merge sort
// this is because it is creating exponential threads (2^N) it takes a lot of memory and also context switching is expensive
// hence its slower than the single threaded merge sort

// we use merge-sort because merging is much optimal process than sorting


// Write sequential megre srt algor
// Write multi-threaded merge sort using newFixedThreadPool
// Write multi-threaded merge sort using newCachedThreadPool
// Compare the performance of all the 3 merge sort

// calculate the execution time of all the 3 merge sort
// graph all the three, and decide which one is better