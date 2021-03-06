package swing.grid.app.convert;

import swing.grid.app.util.FileUtil;

import javax.xml.bind.JAXBException;
import java.io.File;

public class ConverterImpl<T> implements Converter<T> {

    private Class<T> clazz;

    private ParserImpl<T> parser;

    public ConverterImpl(Class<T> clazz) {
        this.clazz = clazz;
        this.parser = new ParserImpl<>();
    }

    @Override
    public T convert(String fileName) {
        T convertedObject = null;
        try {
            File file = FileUtil.getFile(fileName);
            convertedObject = parser.unmarshall(file, clazz);
        } catch (JAXBException e) {
            System.out.println("Cannot unmarshall file");
            e.printStackTrace();
        }
        return convertedObject;
    }

}