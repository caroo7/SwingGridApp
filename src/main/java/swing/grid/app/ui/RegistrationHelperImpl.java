package swing.grid.app.ui;

import javax.swing.*;

public class RegistrationHelperImpl implements RegistrationHelper {

    @Override
    public void displayPassword(String password) {
        JOptionPane.showMessageDialog(null, "User password is: " + password);
    }

}