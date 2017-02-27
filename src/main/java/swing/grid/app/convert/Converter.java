package swing.grid.app.convert;

import swing.grid.app.model.Data;
import swing.grid.app.model.Layout;

import javax.xml.bind.JAXBException;
import java.io.File;

public class Converter {

    private Parser<Layout> parser;

    public Converter() {
        this.parser = new Parser<>();
    }

    // make only one method (generics or polymorphism? - I think generics will be better but think about it)
    public Layout convertLayout() {
        Layout layout = null;
        try {
            File layoutFile = getFile("layout.xml");
            layout = (Layout) parser.unmarshall(layoutFile, Layout.class);
        } catch(JAXBException e) {
            System.out.println("Cannot unmarshall file");
            e.printStackTrace();
        }
        return layout;
    }

    public Data convertData() {
        Data data = null;
        try {
            File dataFile = getFile("data.xml");
            data = (Data) parser.unmarshall(dataFile, Data.class);
        } catch(JAXBException e) {
            System.out.println("Cannot unmarshall file");
            e.printStackTrace();
        }
        return data;
    }

    private File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

}