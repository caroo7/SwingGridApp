package swing.grid.app.i18n;

import javax.inject.Singleton;
import java.util.Locale;
import java.util.ResourceBundle;

@Singleton
public class InternationalizationResourceBundleImpl implements InternationalizationResourceBundle {

    private ResourceBundle resourceBundle;

    private static final String BUNDLE_NAME = "layout";

    public InternationalizationResourceBundleImpl(Locale actualLocale) {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, actualLocale);
    }

    @Override
    public String getMessage(String key) {
        return resourceBundle.getString(key);
    }

}