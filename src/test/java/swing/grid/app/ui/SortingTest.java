package swing.grid.app.ui;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SortingTest {

    private SortingTableModel model;

    @BeforeClass
    private void globalSetUp() {
        model = new SortingTableModel();
        String[] columns = prepareSampleColumns();

        model.setColumnIdentifiers(columns);
        prepareSampleRows();
    }

    @Test
    public void sortTest() {
        model.sort();

        assertEquals(model.getValueAt(0, 0), "aaa");
        assertEquals(model.getValueAt(1, 0), "ddd");
        assertEquals(model.getValueAt(2, 0), "ttt");
    }


    private String[] prepareSampleColumns() {
        return new String[] {
                "A",
                "B"
        };
    }

    private void prepareSampleRows() {
        model.addRow(new String[] {
                "ttt",
                "bbb"
        });
        model.addRow(new String[] {
                "aaa",
                "ccc"
        });
        model.addRow(new String[] {
                "ddd",
                "ggg"
        });
    }

}