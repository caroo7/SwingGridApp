package swing.grid.app.injector;

import com.google.inject.AbstractModule;
import swing.grid.app.registration.RegistrationServiceClient;
import swing.grid.app.registration.RegistrationServiceClientImpl;
import swing.grid.app.config.PropertiesProvider;
import swing.grid.app.i18n.InternationalizationResourceBundle;
import swing.grid.app.i18n.InternationalizationResourceBundleImpl;
import swing.grid.app.ui.RegistrationHelper;
import swing.grid.app.ui.RegistrationHelperImpl;

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
        bind(RegistrationServiceClient.class).to(RegistrationServiceClientImpl.class);
        bind(RegistrationHelper.class).to(RegistrationHelperImpl.class);
    }

}