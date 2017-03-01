package swing.grid.app.ui;

import swing.grid.app.i18n.InternationalizationResourceBundle;
import swing.grid.app.model.Data;
import swing.grid.app.model.Layout;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;
import java.util.List;

@Singleton
public class DataTableImpl implements DataTable {

    private JTable table;

    private SortingTableModel model;

    private InternationalizationResourceBundle resourceBundle;

    @Inject
    public DataTableImpl(InternationalizationResourceBundle resourceBundle) {
        model = new SortingTableModel();
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
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
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