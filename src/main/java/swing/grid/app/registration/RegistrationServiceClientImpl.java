package swing.grid.app.registration;

import registration.RegistrationService;

import javax.inject.Inject;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class RegistrationServiceClientImpl implements RegistrationServiceClient {

    private Properties properties;

    private static final String SERVICE_ENDPOINT_PROPS = "registration.service.endpoint";

    private static final String SERVICE_TARGET_NAMESPACE_PROPS = "registration.service.target.namespace";

    private static final String SERVICE_NAME_PROPS = "registration.service.name";

    private RegistrationService registrationService;

    @Inject
    public RegistrationServiceClientImpl(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void connectToService() throws WebServiceException {
        URL url = null;
        try {
            url = new URL(properties.getProperty(SERVICE_ENDPOINT_PROPS));
        } catch (MalformedURLException e) {
            System.out.println("Cannot create an URL:");
            e.printStackTrace();
        }

        QName name = new QName(properties.getProperty(SERVICE_TARGET_NAMESPACE_PROPS), properties.getProperty(SERVICE_NAME_PROPS));
        Service service = null;
        try {
            service = Service.create(url, name);
        } catch(WebServiceException e) {
            System.out.println("Cannot connect to web service:");
            e.printStackTrace();
        }

        registrationService = service.getPort(RegistrationService.class);
    }


    @Override
    public String registerUser(String username) {
        return registrationService.registerUser(username);
    }

}