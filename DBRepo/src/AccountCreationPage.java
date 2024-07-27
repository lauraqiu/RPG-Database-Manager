import javax.swing.*;
import java.awt.*;

public class AccountCreationPage extends JPanel {
    public AccountCreationPage(BackgroundFrame backgroundFrame) {
        setLayout(new GridBagLayout());

        JLabel userName = new JLabel("UserName:");
        JLabel password = new JLabel("Password:");
        JLabel email = new JLabel("Email:");
        JTextField userNameField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);
        JTextField emailField = new JTextField(10);
        JButton enter= new JButton("Enter");

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
        add(email, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        add(emailField, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        add(enter, gridBagConstraints);

        enter.addActionListener(e -> {
            backgroundFrame.navigateToLoginPage();
        });
    }
}
