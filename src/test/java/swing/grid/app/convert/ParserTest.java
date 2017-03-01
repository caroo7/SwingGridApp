package swing.grid.app.convert;

import org.testng.annotations.Test;
import swing.grid.app.model.Data;
import swing.grid.app.model.Layout;

import javax.xml.bind.JAXBException;
import java.io.File;

import static org.testng.Assert.*;

public class ParserTest {

    private ParserImpl<Layout> layoutParser;

    private ParserImpl<Data> dataParser;

    private static final String CORRECT_LAYOUT_FILE_NAME = "correct_layout.xml";
    private static final String INCORRECT_LAYOUT_FILE_NAME = "incorrect_layout.xml";
    private static final String CORRECT_DATA_FILE_NAME = "correct_data.xml";
    private static final String INCORRECT_DATA_FILE_NAME = "incorrect_data.xml";

    @Test
    public void correctLayoutUnmarshallTest() throws Exception {
        layoutParser = new ParserImpl<>();
        File file = loadFile(CORRECT_LAYOUT_FILE_NAME);

        Layout layout = (Layout) layoutParser.unmarshall(file, Layout.class);

        assertNotNull(layout);
    }

    @Test
    public void correctDataUnmarshallTest() throws Exception {
        dataParser = new ParserImpl<>();
        File file = loadFile(CORRECT_DATA_FILE_NAME);

        Data data = (Data) dataParser.unmarshall(file, Data.class);

        assertNotNull(data);
    }

    @Test(expectedExceptions = JAXBException.class)
    public void incorrectLayoutUnmarshallTest() throws Exception {
        layoutParser = new ParserImpl<>();
        File file = loadFile(INCORRECT_LAYOUT_FILE_NAME);

        layoutParser.unmarshall(file, Layout.class);
    }

    @Test(expectedExceptions = JAXBException.class)
    public void incorrectDataUnmarshallTest() throws Exception {
        dataParser = new ParserImpl<>();
        File file = loadFile(INCORRECT_DATA_FILE_NAME);

        dataParser.unmarshall(file, Data.class);
    }

    private File loadFile(String name) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(name).getFile());
    }

}