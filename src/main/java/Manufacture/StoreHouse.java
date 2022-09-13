package Manufacture;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class StoreHouse {

    private final int storageMaxSize;

    private final List<Resource> storage = new ArrayList<>();

    private static final Logger log = Logger.getLogger(StoreHouse.class);

    public StoreHouse(int storageMaxSize) {
        this.storageMaxSize = storageMaxSize;
    }

    public void put() throws InterruptedException {
        synchronized (storage) {
            while (storage.size() >= storageMaxSize) {
                log.info("Поток переходит в режим ожидания");
                storage.wait();
            }
            Resource res = new Resource(Producer.getId().getAndIncrement());
            storage.add(res);
            log.info("Поток произвел продукт номер " + res.getId() +
                    ". Количество ресурсов на складе состаляет " + storage.size());
            storage.notifyAll();
            log.info("Потоки-потребители выходят из режима ожидания");
        }
    }

    public void get() throws InterruptedException {
        synchronized (storage) {
            while (storage.size() == 0) {
                log.info("Поток переходит в режим ожидания");
                storage.wait();
            }
            log.info("Поток потребил продукт номер " + storage.get(0).getId());
            storage.remove(0);
            log.info("Количество ресурсов на складе состаляет " + storage.size());
            storage.notifyAll();
            log.info("Потоки-производители выходят из режима ожидания");
        }
    }

}
