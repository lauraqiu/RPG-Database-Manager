import javax.swing.*;
import java.awt.*;

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


    }
}
