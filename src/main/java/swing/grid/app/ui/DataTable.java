package swing.grid.app.ui;

import swing.grid.app.model.Data;
import swing.grid.app.model.Layout;

import javax.swing.*;

public interface DataTable {

    JPanel createDataTable(Layout layout, Data data);

    void sort();

}