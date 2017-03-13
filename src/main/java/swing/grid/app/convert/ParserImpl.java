package swing.grid.app.convert;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ParserImpl<T> implements Parser<T> {

    @Override
    public T unmarshall(File file, Class<T> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return clazz.cast(unmarshaller.unmarshal(file));
    }

}