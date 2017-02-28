package swing.grid.app.bl;

import swing.grid.app.ui.DataTable;

import javax.inject.Inject;

public class MyAction implements BusinessFunction {

    private DataTable dataTable;

    @Inject
    public MyAction(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    @Override
    public void doAction() {
        dataTable.sort();
    }

}