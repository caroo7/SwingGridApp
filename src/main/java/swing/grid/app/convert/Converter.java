package swing.grid.app.convert;

import javax.xml.bind.JAXBException;
import java.io.File;

public class Converter<T> {

    private Class<T> clazz;

    private Parser<T> parser;

    public Converter(Class<T> clazz) {
        this.clazz = clazz;
        this.parser = new Parser<>();
    }

    public Object convert(String fileName) {
        Object convertedObject = null;
        try {
            File file = getFile(fileName);
            convertedObject = parser.unmarshall(file, clazz);
        } catch (JAXBException e) {
            System.out.println("Cannot unmarshall file");
            e.printStackTrace();
        }
        return convertedObject;
    }

    private File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

}