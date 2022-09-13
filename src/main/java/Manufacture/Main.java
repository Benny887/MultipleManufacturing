package Manufacture;

public class Main {
    public static void main(String[] args) {
        Config config = new Config();
        config.initFields();
        StoreHouse storeHouse = new StoreHouse(config.getStorageMaxSize());
        for (int i = 0; i < config.getProducerCount(); i++) {
            Thread thread = new Thread(new Producer(storeHouse, config.getProducerTime()));
            thread.setName("Producer " + i);
            thread.start();
        }
        for (int i = 0; i < config.getConsumerCount(); i++) {
            Thread thread = new Thread(new Consumer(storeHouse, config.getConsumerTime()));
            thread.setName("Consumer " + i);
            thread.start();
        }
    }
}
