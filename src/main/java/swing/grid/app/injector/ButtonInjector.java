package swing.grid.app.injector;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import swing.grid.app.bl.BusinessFunction;
import swing.grid.app.model.Layout;
import swing.grid.app.ui.ButtonPanel;
import swing.grid.app.ui.ButtonPanelImpl;
import swing.grid.app.ui.DataTable;
import swing.grid.app.ui.DataTableImpl;

import java.util.List;

public class ButtonInjector extends AbstractModule {

    private List<Layout.Menu.Button> buttons;

    public ButtonInjector(List<Layout.Menu.Button> buttons) {
        this.buttons = buttons;
    }

    @Override
    protected void configure() {
        bind(DataTable.class).to(DataTableImpl.class);
        bind(ButtonPanel.class).to(ButtonPanelImpl.class);

        bindAllButtons();
    }

    private void bindAllButtons() {
        String className = "";
        for(Layout.Menu.Button button: buttons) {
            Class clazz = null;
            try {
                clazz = Class.forName(button.getClazz());
                className =  button.getClazz().substring(button.getClazz().lastIndexOf(".") + 1);
            } catch (ClassNotFoundException e) {
                System.out.println("Cannot create class from string: " + button.getClazz());
                e.printStackTrace();
            }

            bind(BusinessFunction.class).annotatedWith(Names.named(className)).to(clazz);
        }

    }

}