package swing.grid.app.bl;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import swing.grid.app.ui.DataTable;

import java.util.Properties;

public class MyActionTest {

    private Mockery context;

    private MyAction action;

    private DataTable dataTable;

    @BeforeClass
    private void globalSetUp() {
        context = new Mockery();
    }

    @BeforeMethod
    private void testSetUp() {
        dataTable = context.mock(DataTable.class);
        action = new MyAction(dataTable);
    }

    @Test
    public void actionTest() {
        context.checking(new Expectations() {{
            oneOf(dataTable).sort();
        }});

        action.doAction();

        context.assertIsSatisfied();
    }

}