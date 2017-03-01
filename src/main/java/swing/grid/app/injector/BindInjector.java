package swing.grid.app.injector;

import com.google.inject.AbstractModule;
import swing.grid.app.bl.BusinessFunction;
import swing.grid.app.i18n.InternationalizationResourceBundle;
import swing.grid.app.i18n.InternationalizationResourceBundleImpl;
import swing.grid.app.model.Layout;
import swing.grid.app.ui.ButtonPanel;
import swing.grid.app.ui.ButtonPanelImpl;
import swing.grid.app.ui.DataTable;
import swing.grid.app.ui.DataTableImpl;

import java.util.List;
import java.util.Locale;

public class BindInjector extends AbstractModule {

    private List<Layout.Menu.Button> buttons;

    private Locale locale;

    public BindInjector(List<Layout.Menu.Button> buttons, Locale locale) {
        this.buttons = buttons;
        this.locale = locale;
    }

    @Override
    protected void configure() {
        InternationalizationResourceBundle bundle = new InternationalizationResourceBundleImpl(locale);
        bind(InternationalizationResourceBundle.class).toInstance(bundle);

        bind(DataTable.class).to(DataTableImpl.class);
        bind(ButtonPanel.class).to(ButtonPanelImpl.class);

        bindFirstButtonAction();
    }

    private void bindFirstButtonAction() {
        Layout.Menu.Button button = buttons.get(0);
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