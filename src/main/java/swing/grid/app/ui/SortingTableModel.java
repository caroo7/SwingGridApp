package swing.grid.app.ui;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.Comparator;
import java.util.Properties;
import java.util.Vector;

@Singleton
public class SortingTableModel extends DefaultTableModel implements Comparator {

    int columnNumber;

    public SortingTableModel(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    void sort() {
        Collections.sort(dataVector, this);
    }

    @Override
    public int compare(Object first, Object second) {
        if (first == null) return -1;
        if (second == null) return 1;

        Vector firstVector = (Vector) first;
        Vector secondVector = (Vector) second;

        int result;
        Comparable firstColumn = (Comparable) firstVector.get(columnNumber);
        Comparable secondColumn = (Comparable) secondVector.get(columnNumber);
        result = firstColumn.compareTo(secondColumn);
        if (result != 0)
            return result;

        return 0;
    }

}