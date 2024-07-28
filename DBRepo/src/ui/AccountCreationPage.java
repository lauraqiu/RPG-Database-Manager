package ui;

import ui_logic.AccountCreationPageDBHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class AccountCreationPage extends JPanel {
    AccountCreationPageDBHandler dbHandler;
    public AccountCreationPage(BackgroundFrame backgroundFrame, AccountInfoPage accountInfoPage, AccountCreationPageDBHandler accountCreationPageDBHandler) {

        this.dbHandler = accountCreationPageDBHandler;

        setLayout(new GridBagLayout());

        JLabel userName = new JLabel("UserName:");
        JLabel password = new JLabel("Password:");
        JLabel email = new JLabel("Email:");
        JTextField userNameField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);
        JTextField emailField = new JTextField(10);
        JButton enter= new JButton("Enter");
        JButton returnToLogin= new JButton("ReturnToLogin");

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
        add(returnToLogin, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        add(enter, gridBagConstraints);

        returnToLogin.addActionListener(e -> {
            backgroundFrame.navigateToLoginPage();
        });
        enter.addActionListener(e -> {
            String emailString = emailField.getText();
            String passwordString = new String(passwordField.getPassword());
            String usernameString = userNameField.getText();

            if (!isValidEmailAddress(emailString))
                showMessageDialog(this, "Email not valid", "Error", JOptionPane.ERROR_MESSAGE);
            else if(!accountCreationPageDBHandler.checkUniqueUsername(usernameString)  ) {
                showMessageDialog(this, "UserName already exists try logging in", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if( !accountCreationPageDBHandler.checkUniqueEmail(emailString) ){
                showMessageDialog(this, "Email already exists try logging in", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(passwordString.isEmpty()){
                showMessageDialog(this, "Password is empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(usernameString.isEmpty()){
                showMessageDialog(this, "Username is empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                accountCreationPageDBHandler.addAccount(usernameString, passwordString, emailString);
                accountInfoPage.setUserNameContext(usernameString);
                accountInfoPage.updateContent();

                showMessageDialog(this, "Success! Lets Add your information!");
                backgroundFrame.navigateToAccountInfoPage();
            }
        });
    }
    //Is this allowed? I took this from stackOverflow Source: https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

}
