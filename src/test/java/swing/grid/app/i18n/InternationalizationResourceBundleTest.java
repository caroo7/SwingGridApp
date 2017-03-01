package swing.grid.app.i18n;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Locale;

import static org.testng.Assert.assertEquals;

public class InternationalizationResourceBundleTest {

    private InternationalizationResourceBundle resourceBundle;

    private static final String GREETINGS_KEY = "greetings";

    @DataProvider(name = "dataProvider")
    private Object[][] dataProvider() {
        return new Object[][]{
                {new Locale("en", "UK"), "Hello"},
                {new Locale("de", "DE"), "Hallo"},
                {new Locale("pl", "PL"), "Witaj"},
                {new Locale("fr", "FR"), "Bonjour"}
        };
    }

    @Test(dataProvider = "dataProvider")
    public void messageOnLocaleTest(Locale actualLocale, String expectedMessage) {
        resourceBundle = new InternationalizationResourceBundleImpl(actualLocale);

        String actualMessage = resourceBundle.getMessage(GREETINGS_KEY);

        assertEquals(actualMessage, expectedMessage);
    }

}