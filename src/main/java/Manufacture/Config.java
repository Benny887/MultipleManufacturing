package Manufacture;

import org.apache.log4j.Logger;

import java.util.Properties;

public class Config {
    private int producerCount;
    private int consumerCount;
    private int storageMaxSize;
    private int producerTime;
    private int consumerTime;
    private final Properties propertyFile = new PropertiesGetter().getProps();

    private static final Logger log = Logger.getLogger(Config.class);


    private int readProperties(String nameOfProperty, int defaultValue) {
        int valueOfProperty;
        try {
            valueOfProperty = Integer.parseInt(propertyFile.getProperty(nameOfProperty));
            if (valueOfProperty <= 0) {
                valueOfProperty = defaultValue;
                log.error("Некорректное значение параметра " + nameOfProperty + ". Установится значение по умолчанию равное " + defaultValue);
            }
        } catch (NumberFormatException e) {
            valueOfProperty = defaultValue;
            log.error("Ошибка при чтении параметра " + nameOfProperty + ". Установится значение по умолчанию равное " + defaultValue);
        }
        return valueOfProperty;
    }

    public void initFields() {
        setProducerCount();
        setProducerTime();
        setConsumerTime();
        setConsumerCount();
        setStorageMaxSize();
    }

    private void setProducerCount() {
        int defaultProducerCount = 5;
        this.producerCount = readProperties("producerCount", defaultProducerCount);
    }

    private void setProducerTime() {
        int defaultProducerTime = 1000;
        this.producerTime = readProperties("producerTime", defaultProducerTime);
    }

    private void setStorageMaxSize() {
        int defaultStorageMaxSize = 10;
        this.storageMaxSize = readProperties("storageSize", defaultStorageMaxSize);
    }

    private void setConsumerTime() {
        int defaultConsumerTime = 1000;
        this.consumerTime = readProperties("consumerTime", defaultConsumerTime);
    }

    private void setConsumerCount() {
        int defaultConsumerCount = 5;
        this.consumerCount = readProperties("consumerCount", defaultConsumerCount);
    }

    public int getProducerCount() {
        return producerCount;
    }

    public int getConsumerCount() {
        return consumerCount;
    }

    public int getStorageMaxSize() {
        return storageMaxSize;
    }

    public int getProducerTime() {
        return producerTime;
    }

    public int getConsumerTime() {
        return consumerTime;
    }
}
