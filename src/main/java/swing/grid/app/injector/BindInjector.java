package swing.grid.app.injector;

import com.google.inject.AbstractModule;
import swing.grid.app.bl.BusinessFunction;
import swing.grid.app.model.Layout;
import swing.grid.app.ui.DataTable;
import swing.grid.app.ui.DataTableImpl;

import java.util.List;

public class BindInjector extends AbstractModule {

    private List<Layout.Menu.Button> buttons;

    public BindInjector(List<Layout.Menu.Button> buttons) {
        this.buttons = buttons;
    }

    @Override
    protected void configure() {
        bind(DataTable.class).to(DataTableImpl.class);

        for(Layout.Menu.Button button: buttons) {
            Class clazz = null;
            try {
                clazz = Class.forName(button.getClazz());
            } catch (ClassNotFoundException e) {
                System.out.println("Cannot create class from string: " + button.getClazz());
                e.printStackTrace();
            }

            bind(BusinessFunction.class).to(clazz);
        }
    }

}