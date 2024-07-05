package Multi_Threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IntroExecutorService {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        // newFixedThreadPool means that we want this specific number of threads to be created, and we want to reuse them
        for(int i = 0; i <= 100; i++){
            SingleNumberPrinterV2 task = new SingleNumberPrinterV2(i);
            es.submit(task);
        }
    }
}
