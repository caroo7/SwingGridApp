import com.google.inject.Guice;
import com.google.inject.Injector;
import swing.grid.app.bl.BusinessFunction;
import swing.grid.app.convert.Converter;
import swing.grid.app.injector.BindInjector;
import swing.grid.app.model.Data;
import swing.grid.app.model.Layout;
import swing.grid.app.ui.DataTable;
import swing.grid.app.ui.UIRenderer;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Converter<Layout> layoutConverter = new Converter<>(Layout.class);
        final Layout layout = (Layout) layoutConverter.convert("layout.xml");

        Converter<Data> dataConverter = new Converter<>(Data.class);
        final Data data = (Data) dataConverter.convert("data.xml");

        Injector injector = Guice.createInjector(new BindInjector(layout.getMenu().getButton()));
        final DataTable dataTable = injector.getInstance(DataTable.class);
        final JPanel dataPanel = dataTable.createDataTable(layout, data);
        final BusinessFunction function = injector.getInstance(BusinessFunction.class);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UIRenderer(function, dataTable).createFrame(layout, dataPanel);
            }
        });
    }

}