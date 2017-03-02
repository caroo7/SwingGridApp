package swing.grid.app.ui;

import swing.grid.app.i18n.InternationalizationResourceBundle;
import swing.grid.app.model.Data;
import swing.grid.app.model.Layout;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Properties;

@Singleton
public class DataTableImpl implements DataTable {

    private JTable table;

    private SortingTableModel model;

    private InternationalizationResourceBundle resourceBundle;

    private Properties properties;

    private static final String SORTED_COLUMN_NUMBER_STRING = "sortedColumnNumber";

    private static final String HEADER_FONT_SIZE_STRING = "headerFontSize";

    @Inject
    public DataTableImpl(InternationalizationResourceBundle resourceBundle, Properties properties) {
        this.properties = properties;
        int sortColumnNumber = Integer.valueOf(properties.getProperty(SORTED_COLUMN_NUMBER_STRING));

        model = new SortingTableModel(sortColumnNumber);
        table = new JTable(model);

        this.resourceBundle = resourceBundle;
    }

    public JPanel createDataTable(Layout layout, Data data) {
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table);

        createHeader(layout);
        createData(data);

        panel.add(scrollPane);
        return panel;
    }

    public void sort() {
        model.sort();
        model.fireTableDataChanged();
    }

    private void createHeader(Layout layout) {
        model.setColumnIdentifiers(prepareHeaderCaptions(layout.getGrid().getColumn()));
        int headerFontSize = Integer.valueOf(properties.getProperty(HEADER_FONT_SIZE_STRING));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, headerFontSize));
    }

    private String[] prepareHeaderCaptions(List<String> columnHeaders) {
        String[] captions = new String[columnHeaders.size()];
        for(int i=0; i<columnHeaders.size(); ++i) {
            captions[i] = resourceBundle.getMessage(columnHeaders.get(i));
        }
        return captions;
    }

    private void createData(Data data) {
        String[] row;
        for (Data.Car car : data.getCar()) {
            row = new String[]{
                    car.getMake(),
                    car.getModel(),
                    String.valueOf(car.getYear())
            };
            model.addRow(row);
        }
    }

}