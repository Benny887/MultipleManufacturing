package Manufacture;

import org.apache.log4j.Logger;

public class Consumer implements Runnable {
    private static final Logger log = Logger.getLogger(Consumer.class);

    private final StoreHouse storeHouse;
    private final int sleepingTime;

    public Consumer(StoreHouse storage, int sleepingTime) {
        storeHouse = storage;
        this.sleepingTime = sleepingTime;
    }

    @Override
    public void run() {
        try {
            while (true) {
                storeHouse.get();
                Thread.sleep(sleepingTime);
            }
        } catch (InterruptedException e) {
            log.error("Поток прерван.");
            Thread.currentThread().interrupt();
        }
    }
}
