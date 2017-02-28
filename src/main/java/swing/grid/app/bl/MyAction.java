package swing.grid.app.bl;

import swing.grid.app.ui.SortingTableModel;

import javax.inject.Inject;


public class MyAction implements BusinessFunction {

    private SortingTableModel sortingTableModel;

    @Inject
    public MyAction(SortingTableModel sortingTableModel) {
        this.sortingTableModel = sortingTableModel;
    }

    @Override
    public void doAction() {
        sortingTableModel.sort();
        System.out.println("Table sorted!");
    }

}