package swing.grid.app.registration;

import java.net.MalformedURLException;

public interface RegistrationServiceClient {

    void connectToService();

    String registerUser(String username);

}