package swing.grid.app.injector;

import com.google.inject.AbstractModule;
import swing.grid.app.bl.BusinessFunction;
import swing.grid.app.config.PropertiesProvider;
import swing.grid.app.i18n.InternationalizationResourceBundle;
import swing.grid.app.i18n.InternationalizationResourceBundleImpl;
import swing.grid.app.model.Layout;
import swing.grid.app.ui.ButtonPanel;
import swing.grid.app.ui.ButtonPanelImpl;
import swing.grid.app.ui.DataTable;
import swing.grid.app.ui.DataTableImpl;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class MainInjector extends AbstractModule {

    private Locale locale;

    public MainInjector(Locale locale) {
        this.locale = locale;
    }

    @Override
    protected void configure() {
        InternationalizationResourceBundle bundle = new InternationalizationResourceBundleImpl(locale);
        bind(InternationalizationResourceBundle.class).toInstance(bundle);
        bind(Properties.class).toProvider(PropertiesProvider.class);
    }

}