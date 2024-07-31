package ui;

import ui_logic.AdminViewPageDBHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminViewPage extends JPanel {
    BackgroundFrame backgroundFrame;
    AdminViewPageDBHandler adminViewPageDBHandler;
    GridBagConstraints gridBagConstraints;
    public AdminViewPage(BackgroundFrame backgroundFrame, AdminViewPageDBHandler adminViewPageDBHandler) {

        this.backgroundFrame = backgroundFrame;
        this.adminViewPageDBHandler = adminViewPageDBHandler;

        setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();

        JLabel attribute = new JLabel("Attribute View:");
        JComboBox tableBox = new JComboBox(new String[]{"ACCOUNTS", "CHARACTERS"});
        JButton searchButton = new JButton("SearchText");
        JButton accounts = new JButton("Accounts");

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        add(attribute, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(tableBox, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        add(searchButton, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        add(accounts, gridBagConstraints);

        accounts.addActionListener(t  -> {
            buildAccountPage(adminViewPageDBHandler);
        });
        setSearchButtonActionListener(adminViewPageDBHandler, searchButton, tableBox);
    }

    private void buildAccountPage(AdminViewPageDBHandler adminViewPageDBHandler) {
        JFrame accountPageFrame = new JFrame();
        accountPageFrame.setLayout(new GridBagLayout());

        JLabel addAccount = new JLabel("Add Account:");
        JLabel updateAccountEmail = new JLabel("Update Account Email");
        JLabel updateAccountPassword = new JLabel("Update Account Password");

        JLabel addUserName = new JLabel("UserName: ");
        JLabel addPasswordString  = new JLabel("Password: ");
        JLabel addEmailString = new JLabel("Email: ");
        JLabel updatedUserNameEmail = new JLabel("UserName: ");
        JLabel updatedUserNamePassword = new JLabel("UserName: ");
        JLabel updatePasswordString  = new JLabel("Password: ");
        JLabel updateEmailString = new JLabel("Email: ");
        JLabel deleteAccount = new JLabel("DeleteAccount: ");

        JComboBox<String> usernameBox = new JComboBox<String>();
        ArrayList<String> userNameResults = adminViewPageDBHandler.getUserNames();
        JComboBox<String> updateUserEmailBox  = new JComboBox<String>();
        JComboBox<String> updateUserPasswordBox  = new JComboBox<String>();
        JTextField updatePasswordTextField  = new JTextField(10);

        for (String userName : userNameResults) {
            usernameBox.addItem(userName);
            updateUserEmailBox.addItem(userName);
            updateUserPasswordBox.addItem(userName);
        }
        JButton addAccountButton = new JButton("Add Account");
        JButton updatePasswordAccountButton = new JButton("Update Account Password");
        JButton updateEmailAccountButton = new JButton("Update Account Email");
        JButton deleteAccountButton = new JButton("DeleteAccount");

        JTextField addUserNameTextField  = new JTextField(10);
        JTextField addPasswordTextField  = new JTextField(10);
        JTextField addEmailTextField  = new JTextField(10);

        JTextField updateEmailTextField  = new JTextField(10);

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        accountPageFrame.add(addAccount, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        accountPageFrame.add(addUserName, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        accountPageFrame.add(addUserNameTextField, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        accountPageFrame.add(addPasswordString, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        accountPageFrame.add(addPasswordTextField, gridBagConstraints);
        gridBagConstraints.gridx = 4;
        accountPageFrame.add(addEmailString, gridBagConstraints);
        gridBagConstraints.gridx = 5;
        accountPageFrame.add(addEmailTextField, gridBagConstraints);
        gridBagConstraints.gridx = 6;
        accountPageFrame.add(addAccountButton, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        accountPageFrame.add(updateAccountEmail, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        accountPageFrame.add(updatedUserNameEmail, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        accountPageFrame.add(updateUserEmailBox, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        accountPageFrame.add(updateEmailString, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        accountPageFrame.add(updateEmailTextField, gridBagConstraints);
        gridBagConstraints.gridx = 4;
        accountPageFrame.add(updateEmailAccountButton, gridBagConstraints);

        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;

        accountPageFrame.add(updateAccountPassword, gridBagConstraints);
        gridBagConstraints.gridy = 5;
        accountPageFrame.add(updatedUserNamePassword, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        accountPageFrame.add(updateUserPasswordBox, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        accountPageFrame.add(updatePasswordString, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        accountPageFrame.add(updatePasswordTextField, gridBagConstraints);
        gridBagConstraints.gridx = 4;
        accountPageFrame.add(updatePasswordAccountButton, gridBagConstraints);

        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridx = 0;

        accountPageFrame.add(deleteAccount, gridBagConstraints);
        gridBagConstraints.gridy = 7;
        accountPageFrame.add(usernameBox, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        accountPageFrame.add(deleteAccountButton, gridBagConstraints);
        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 7;

        accountPageFrame.setVisible(true);
        accountPageFrame.setSize(1020,800);

        addAccountButton.addActionListener(e -> {
            adminViewPageDBHandler.addAccount(addUserNameTextField.getText(),
                    addPasswordTextField.getText(),  addEmailTextField.getText());
            accountPageFrame.dispose();
            buildAccountPage(adminViewPageDBHandler);
        });
        updateEmailAccountButton.addActionListener(e -> {

            adminViewPageDBHandler.updateEmailAccount(updateUserEmailBox.getSelectedItem().toString(),
                    updateEmailTextField.getText());
        });
        updatePasswordAccountButton.addActionListener( e -> {
            adminViewPageDBHandler.updatePasswordAccount(updateUserPasswordBox.getSelectedItem().toString(),
                    updatePasswordTextField.getText());
        });
        deleteAccountButton.addActionListener(e -> {
            adminViewPageDBHandler.deleteAccount(usernameBox.getSelectedItem().toString());
            usernameBox.remove(usernameBox.getSelectedIndex());
            accountPageFrame.dispose();
            buildAccountPage(adminViewPageDBHandler);
        });
    }

    private void setSearchButtonActionListener(AdminViewPageDBHandler adminViewPageDBHandler, JButton searchButton, JComboBox tableBox) {
        searchButton.addActionListener(e -> {
            String selectedTable = (String) tableBox.getSelectedItem();
            if (selectedTable.equals("ACCOUNTS")) {
                JFrame accountFrame = new JFrame();
                JLabel jLabel = new JLabel("Select Attributes");
                JCheckBox username = new JCheckBox("UserName");
                JCheckBox isVerified = new JCheckBox("IsVerified");
                JCheckBox password = new JCheckBox("Password");
                JCheckBox email = new JCheckBox("Email");
                JCheckBox invSlots = new JCheckBox("InvSlots");
                JButton retrieveData = new JButton("RetrieveData");
                accountFrame.setLayout(new GridBagLayout());
                accountFrame.add(jLabel, gridBagConstraints);
                gridBagConstraints.gridy = 1;
                accountFrame.add(username, gridBagConstraints);
                gridBagConstraints.gridy = 2;
                accountFrame.add(isVerified, gridBagConstraints);
                gridBagConstraints.gridy = 3;
                accountFrame.add(password, gridBagConstraints);
                gridBagConstraints.gridy = 4;
                accountFrame.add(email, gridBagConstraints);
                gridBagConstraints.gridy = 5;
                accountFrame.add(invSlots, gridBagConstraints);
                gridBagConstraints.gridy = 6;
                accountFrame.add(retrieveData, gridBagConstraints);
                accountFrame.setVisible(true);
                accountFrame.setSize(1020,800);
                setRetrieveDataActionListener(adminViewPageDBHandler, retrieveData, username, isVerified, password, email, invSlots, accountFrame);
            }
        });
    }

    private static void setRetrieveDataActionListener(AdminViewPageDBHandler adminViewPageDBHandler, JButton retrieveData, JCheckBox username, JCheckBox isVerified, JCheckBox password, JCheckBox email, JCheckBox invSlots, JFrame accountFrame) {
        retrieveData.addActionListener(t  -> {
            StringBuilder query = new StringBuilder();
            query.append("SELECT");
            boolean userNameSelected = username.isSelected();
            boolean isVerifiedSelected = isVerified.isSelected();
            boolean passwordSelected = password.isSelected();
            boolean emailSelected = email.isSelected();
            boolean invSlotsSelected = invSlots.isSelected();
            if(userNameSelected) {
                query.append(" USERNAME,");
            }
            if(isVerifiedSelected) {
                query.append(" ISVERIFIED,");
            }
            if(passwordSelected) {
                query.append(" PASSWORD,");
            }
            if(emailSelected) {
                query.append(" EMAIL,");
            }
            if(invSlotsSelected) {
                query.append(" INVSLOTS,");
            }
            //We added an attribute we remove space and comma
            if (query.length() > 7){
                query.setLength(query.length()- 1);
                query.append(" FROM ACCOUNTS");
                adminViewPageDBHandler.retrieveData(query.toString(),
                        userNameSelected, isVerifiedSelected,passwordSelected,
                        emailSelected, invSlotsSelected);
            }
            //The user did not select anything
            else {
                JOptionPane.showMessageDialog(accountFrame, "Error: Did not select any attibutes", "ERROR" , JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
