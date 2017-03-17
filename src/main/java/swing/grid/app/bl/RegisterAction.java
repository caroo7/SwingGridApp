package swing.grid.app.bl;

import swing.grid.app.registration.RegistrationServiceClient;
import swing.grid.app.ui.RegistrationHelper;

import javax.inject.Inject;
import java.util.Properties;

public class RegisterAction implements BusinessFunction {

    private RegistrationServiceClient service;

    private RegistrationHelper registrationHelper;

    private Properties properties;

    private static final String REGISTRATION_SAMPLE_USERNAME_PROPS = "registration.sample.username";

    @Inject
    public RegisterAction(RegistrationServiceClient service, RegistrationHelper registrationHelper, Properties properties) {
        this.service = service;
        this.registrationHelper = registrationHelper;
        this.properties = properties;
    }

    @Override
    public void doAction() {
        service.connectToService();
        String password = service.registerUser(properties.getProperty(REGISTRATION_SAMPLE_USERNAME_PROPS));
        registrationHelper.displayPassword(password);
    }

}