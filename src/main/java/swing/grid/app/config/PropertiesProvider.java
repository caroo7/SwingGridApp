package swing.grid.app.config;

import com.google.inject.Provider;
import swing.grid.app.util.FileUtil;

import javax.inject.Singleton;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesProvider implements Provider<Properties>{

    private static final String PROPERTIES_FILE_NAME = "application.properties";

    @Override
    public Properties get() {
        Properties properties = new Properties();
        try (FileInputStream propertiesFile = new FileInputStream(FileUtil.getFile(PROPERTIES_FILE_NAME))) {
            properties.load(propertiesFile);
        } catch (IOException e) {
            System.out.println("Cannot load application.properties file");
            e.printStackTrace();
        }
        return properties;
    }

}