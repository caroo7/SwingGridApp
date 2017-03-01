package swing.grid.app.convert;

import javax.xml.bind.JAXBException;
import java.io.File;

public interface Parser<T> {

    Object unmarshall(File file, Class<T> clazz) throws JAXBException;

}