package swing.grid.app.i18n;

import java.util.Locale;

public class LocaleHandler {

    public static Locale getLocale(String[] args) {
        if(args.length != 2) {
            return Locale.getDefault();
        }
        String language = args[0];
        String country = args[1];
        return new Locale(language, country);
    }

}