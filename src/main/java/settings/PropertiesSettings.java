package settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesSettings implements ISettings {

    @Override
    public Map<String, String> getSettings(String fileName) throws IOException {
        Properties settingsProperties = new Properties();

        String rootPath = System.getProperty("user.dir");
        File propertyFile = new File(rootPath + "src/java/resources/db.propertes");
        settingsProperties.load(new FileInputStream(propertyFile));

        Map<String, String> settings = new HashMap<>();
        for (Map.Entry entry: settingsProperties. entrySet()) {
            settings.put((String) entry.getKey(), (String) entry.getValue());
        }

        return settings;
    }
}
