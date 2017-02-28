package swing.grid.app.ui;

import swing.grid.app.model.Data;
import swing.grid.app.model.Layout;

import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;

@Singleton
public class DataTableImpl implements DataTable {

    private JTable table;

    private SortingTableModel model;

    public DataTableImpl() {
        model = new SortingTableModel();
        table = new JTable(model);
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
        model.setColumnIdentifiers(layout.getGrid().getColumn().toArray());
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
    }

    private void createData(Data data) {
        String[] row;
        for (Data.Car car : data.getCar()) {
            row = new String[]{
                    car.getMake(), car.getModel(), String.valueOf(car.getYear())
            };
            model.addRow(row);
        }
    }

}