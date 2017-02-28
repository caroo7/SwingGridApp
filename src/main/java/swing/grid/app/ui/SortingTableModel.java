package swing.grid.app.ui;

import javax.inject.Singleton;
import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

@Singleton
public class SortingTableModel extends DefaultTableModel implements Comparator {

    private static final int SORTED_COLUMN_NUMBER = 0;

    public void sort() {
        Collections.sort(dataVector, this);
    }

    @Override
    public int compare(Object first, Object second) {
        if (first == null) return -1;
        if (second == null) return 1;

        Vector firstVector = (Vector) first;
        Vector secondVector = (Vector) second;

        int result;

        Comparable firstColumn = (Comparable) firstVector.get(SORTED_COLUMN_NUMBER);
        Comparable secondColumn = (Comparable) secondVector.get(SORTED_COLUMN_NUMBER);
        result = firstColumn.compareTo(secondColumn);
        if (result != 0)
            return result;

        return 0;
    }

}