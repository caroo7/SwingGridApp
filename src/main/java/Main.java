import com.google.inject.Guice;
import com.google.inject.Injector;
import swing.grid.app.convert.ConverterImpl;
import swing.grid.app.i18n.LocaleHandler;
import swing.grid.app.injector.BindInjector;
import swing.grid.app.model.Data;
import swing.grid.app.model.Layout;
import swing.grid.app.ui.ButtonPanel;
import swing.grid.app.ui.DataTable;
import swing.grid.app.ui.MainFrame;

import javax.swing.*;
import java.util.Locale;

public class Main {

    private static final String LAYOUT_FILE_NAME = "layout.xml";

    private static final String DATA_FILE_NAME = "data.xml";

    public static void main(String[] args) {
        Locale locale = LocaleHandler.getLocale(args);

        ConverterImpl<Layout> layoutConverter = new ConverterImpl<>(Layout.class);
        final Layout layout = (Layout) layoutConverter.convert(LAYOUT_FILE_NAME);

        ConverterImpl<Data> dataConverter = new ConverterImpl<>(Data.class);
        final Data data = (Data) dataConverter.convert(DATA_FILE_NAME);

        Injector injector = Guice.createInjector(new BindInjector(layout.getMenu().getButton(), locale));
        final DataTable dataTable = injector.getInstance(DataTable.class);
        final ButtonPanel buttonpanel = injector.getInstance(ButtonPanel.class);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame(dataTable, buttonpanel).createFrame(layout, data);
            }
        });
    }

}