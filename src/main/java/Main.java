import swing.grid.app.convert.Converter;
import swing.grid.app.model.Data;
import swing.grid.app.model.Layout;
import swing.grid.app.render.UIRenderer;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        Converter converter = new Converter();
        final Layout layout = converter.convertLayout();
        final Data data = converter.convertData();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UIRenderer().createFrame(layout, data);
            }
        });
    }

}