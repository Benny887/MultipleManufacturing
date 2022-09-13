package Manufacture;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesGetter {
    private final String pathToProp = getClass().getClassLoader().getResource("valuesForStoreHouse.properties").getPath();

    public Properties getProps() {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(pathToProp)) {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
