import com.google.inject.Guice;
import com.google.inject.Injector;
import swing.grid.app.convert.Converter;
import swing.grid.app.convert.ConverterImpl;
import swing.grid.app.i18n.LocaleHandler;
import swing.grid.app.injector.ButtonInjector;
import swing.grid.app.injector.MainInjector;
import swing.grid.app.model.Data;
import swing.grid.app.model.Layout;
import swing.grid.app.ui.ButtonPanel;
import swing.grid.app.ui.DataTable;
import swing.grid.app.ui.MainFrame;

import javax.swing.*;
import java.util.Locale;
import java.util.Properties;

public class Main {

    private static final String LAYOUT_FILE_NAME_KEY = "layoutFileName";

    private static final String DATA_FILE_NAME_KEY = "dataFileName";

    public static void main(String[] args) {
        Locale locale = LocaleHandler.getLocale(args);
        Injector mainIjector = Guice.createInjector(new MainInjector(locale));
        final Properties properties = mainIjector.getInstance(Properties.class);

        Converter<Layout> layoutConverter = new ConverterImpl<>(Layout.class);
        final Layout layout = layoutConverter.convert(properties.getProperty(LAYOUT_FILE_NAME_KEY));
        Converter<Data> dataConverter = new ConverterImpl<>(Data.class);
        final Data data = dataConverter.convert(properties.getProperty(DATA_FILE_NAME_KEY));

        Injector buttonInjector = mainIjector.createChildInjector(new ButtonInjector(layout.getMenu().getButton()));
        final DataTable dataTable = buttonInjector.getInstance(DataTable.class);
        final ButtonPanel buttonpanel = buttonInjector.getInstance(ButtonPanel.class);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame(dataTable, buttonpanel).createFrame(layout, data);
            }
        });
    }

}