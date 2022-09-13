package Manufacture;

import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    private static final AtomicInteger id = new AtomicInteger(0);
    private static final Logger log = Logger.getLogger(Producer.class);

    private final StoreHouse storeHouse;
    private final int sleepingTime;

    public Producer(StoreHouse storage, int sleepingTime) {
        storeHouse = storage;
        this.sleepingTime = sleepingTime;
    }

    public static AtomicInteger getId() {
        return id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                storeHouse.put();
                Thread.sleep(sleepingTime);
            }
        } catch (InterruptedException e) {
            log.error("Поток прерван.");
            Thread.currentThread().interrupt();
        }
    }

}
