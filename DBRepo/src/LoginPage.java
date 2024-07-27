import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class LoginPage extends JPanel {
    public LoginPage(BackgroundFrame backgroundFrame){
        setLayout(new GridBagLayout());

        JLabel userName = new JLabel("UserName:");
        JLabel password = new JLabel("Password:");
        JTextField userNameField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);
        JButton login = new JButton("Login");
        JButton createAccount = new JButton("Create Account");

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(15,15,15,15);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(userName, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(userNameField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(password, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        add(passwordField, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        add(createAccount, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        add(login, gridBagConstraints);

        createAccount.addActionListener(e -> {
            backgroundFrame.navigateToCreateAccountPage();
        });

        login.addActionListener(e -> {
            String username = userNameField.getText();
            String passwordString = new String(passwordField.getPassword());
            if (username.equals("")){
                showMessageDialog(this, "UserName is not in system", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (passwordString.equals("Tropical lulu")){
                showMessageDialog(this, "Password incorrect","Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                backgroundFrame.navigateToCreateAccountPage();
            }
            //TODO AE: check if userName is in the DB, if not return an error
            //TODO AE: check if password matches userName in DB if not return error
        });

    }
}
