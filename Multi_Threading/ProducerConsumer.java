package Multi_Threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumer {
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        Store store = new Store(5);
        for(int i = 1; i < 100; i++){
            Producer producer = new Producer(store, i);
            es.submit(producer);
        }
        for(int i = 1; i < 5; i++){
            Consumer consumer = new Consumer(store, i);
            es.submit(consumer);
        }
        es.shutdown();
    }
}

class Producer implements Runnable {
    Store store;
    int id;
    public Producer(Store store, int id){
        this.store = store;
        this.id = id;
    }
    @Override
    public void run() {
        while(true){
            // without using semaphore, only one consumer or producer can access the store at a time
            // but we want multiple consumers and producers to access the store at the same time if there conditions satisfy
            // to solve this we use a semaphore

            // in the output we can see that, only one producer or consumer keeps on producing or consuming
            // until the store is full or empty
            synchronized(store){
                if(store.curSize < store.maxSize){
                    store.curSize = store.curSize + 1;
                    System.out.println("Producer with id: " + id + " produced a product, Cur Size = : " + store.curSize);
                }
            }
        }
        
    }
}

class Consumer implements Runnable {
    Store store;
    int id;
    public Consumer(Store store, int id){
        this.store = store;
        this.id = id;
    }
    @Override
    public void run() {
        while(true){
            synchronized(store){
                if(store.curSize > 0){
                    store.curSize = store.curSize - 1;
                    System.out.println("Consumer with id: " + id + " consumed a product, Cur Size = : " + store.curSize);
                }
            }
        }
    }
}

class Store {
    int maxSize;
    int curSize;;
    public Store(int maxSize){
        this.maxSize = maxSize;
        this.curSize = 0;
    }
}
