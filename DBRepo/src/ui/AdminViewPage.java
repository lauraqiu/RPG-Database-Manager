package ui;

import ui_logic.AdminViewPageDBHandler;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;
import java.util.ArrayList;

public class AdminViewPage extends JPanel {
    BackgroundFrame backgroundFrame;
    AdminViewPageDBHandler adminViewPageDBHandler;
    GridBagConstraints gridBagConstraints;

    public AdminViewPage(BackgroundFrame backgroundFrame, AdminViewPageDBHandler adminViewPageDBHandler) {
        this.backgroundFrame = backgroundFrame;
        this.adminViewPageDBHandler = adminViewPageDBHandler;

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();

        JLabel attribute = new JLabel("Select Table:");
        // add dropdown option for each table
        JComboBox<String> tableBox = new JComboBox<>(new String[]{
                "ACCOUNTS",
                "FRIENDS",
                "SERVERS",
                "CHARACTERS",
                "GLOBALLEADERBOARD",
                "LOCALLEADERBOARD",
                "ITEMS",
                "CONSUMABLES",
                "EQUIPMENTS",
                "RESOURCES",
                "EQUIPPED",
                "INVENTORY",
                "SHAREDINVENTORY"
        });
        JButton searchButton = new JButton("Search");

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        topPanel.add(attribute, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        topPanel.add(tableBox, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        topPanel.add(searchButton, gridBagConstraints);

        add(topPanel, BorderLayout.CENTER);

        searchButton.addActionListener(e -> showAttributesFrame((String) tableBox.getSelectedItem()));

        // bottom buttons to new page
        JPanel bottomPanel = new JPanel(new FlowLayout());
        add(bottomPanel, BorderLayout.SOUTH);

        JButton accountsButton = new JButton("Accounts");
        JButton charactersButton = new JButton("Characters");
        JButton equippedButton = new JButton("Equipped");
        JButton serversButton = new JButton("Servers");
        JButton itemsButton = new JButton("Items");
        JButton inventoriesButton = new JButton("Inventories");
        JButton equippedByCharacterButton = new JButton("Find equipment By Class");
        JButton seeFullyEquippedButton = new JButton("SeeFullyEquippedCharacters");

        bottomPanel.add(accountsButton);
        bottomPanel.add(charactersButton);
        bottomPanel.add(equippedButton);
        bottomPanel.add(serversButton);
        bottomPanel.add(itemsButton);
        bottomPanel.add(inventoriesButton);
        bottomPanel.add(equippedByCharacterButton);
        bottomPanel.add(seeFullyEquippedButton);
        accountsButton.addActionListener(e -> buildAccountPage(adminViewPageDBHandler));
        charactersButton.addActionListener(e -> new CharactersPage(adminViewPageDBHandler));
        equippedButton.addActionListener(e -> new EquippedPage(adminViewPageDBHandler));
        serversButton.addActionListener(e -> {});
        itemsButton.addActionListener(e -> {});
        inventoriesButton.addActionListener(e -> {});
        equippedByCharacterButton.addActionListener(e -> new EquipmentByCharacterPage(adminViewPageDBHandler));
        seeFullyEquippedButton.addActionListener(e ->  new FullyEquippedCharacterPage(adminViewPageDBHandler));
    }

    // select between tables to display
    private void showAttributesFrame(String selectedTable) {
        if ("ACCOUNTS".equals(selectedTable)) {
            showFrame("ACCOUNTS", new String[]{"username", "isVerified", "password", "email", "InvSlots"});
        } else if ("FRIENDS".equals(selectedTable)) {
            showFrame("FRIENDS", new String[]{"username", "friend"});
        } else if ("SERVERS".equals(selectedTable)) {
            showFrame("SERVERS", new String[]{"name", "location", "capacity"});
        } else if ("CHARACTERS".equals(selectedTable)) {
            showFrame("CHARACTERS", new String[]{"ID", "Acc_User", "name", "class", "age", "height", "weight", "race", "lvl", "money", "strength", "intelligence", "charisma", "dexterity", "luck", "InvSlots", "serverName", "serverLocation"});
        } else if ("GLOBALLEADERBOARD".equals(selectedTable)) {
            showFrame("GLOBALLEADERBOARD", new String[]{"id", "Fame", "CID", "Acc_User"});
        } else if ("LOCALLEADERBOARD".equals(selectedTable)) {
            showFrame("LOCALLEADERBOARD", new String[]{"id", "Fame", "CID", "Acc_User", "serverLocation", "serverName"});
        } else if ("ITEMS".equals(selectedTable)) {
            showFrame("ITEMS", new String[]{"name", "isKey", "description"});
        } else if ("CONSUMABLES".equals(selectedTable)) {
            showFrame("CONSUMABLES", new String[]{"itemName", "maxStack"});
        } else if ("EQUIPMENTS".equals(selectedTable)) {
            showFrame("EQUIPMENTS", new String[]{"itemName", "type", "strength", "intelligence", "charisma", "dexterity", "luck"});
        } else if ("RESOURCES".equals(selectedTable)) {
            showFrame("RESOURCES", new String[]{"itemName", "maxStack"});
        } else if ("EQUIPPED".equals(selectedTable)) {
            showFrame("EQUIPPED", new String[]{"EqName", "CID", "Acc_User", "EqType"});
        } else if ("INVENTORY".equals(selectedTable)) {
            showFrame("INVENTORY", new String[]{"CID", "Acc_User", "slotNum", "stacks", "itemName"});
        } else if ("SHAREDINVENTORY".equals(selectedTable)) {
            showFrame("SHAREDINVENTORY", new String[]{"userID", "slotNum", "stacks", "item"});
        }
    }

    // to display checkboxes
    private void showFrame(String tableName, String[] attributes) {
        JFrame frame = new JFrame();
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        frame.add(new JLabel("Select"), gbc);
        gbc.gridy++;

        // add a checkbox for each attribute
        JCheckBox[] checkBoxes = new JCheckBox[attributes.length];
        for (int i = 0; i < attributes.length; i++) {
            checkBoxes[i] = new JCheckBox(attributes[i]);
            frame.add(checkBoxes[i], gbc);
            gbc.gridy++;
        }

        JButton retrieveData = new JButton("Retrieve Data");
        frame.add(retrieveData, gbc);

        // on click, creates query based on selected attributes
        retrieveData.addActionListener(e -> {
            String query = buildQuery(tableName, checkBoxes);
            if (query != null) {
                adminViewPageDBHandler.retrieveData(query, checkBoxes);
            } else {
                JOptionPane.showMessageDialog(frame, "Error: Did not select any attributes", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
        frame.setSize(1020, 800);
    }

    // builds sql string query
    private String buildQuery(String tableName, JCheckBox[] checkBoxes) {
        StringBuilder query = new StringBuilder("SELECT ");
        boolean anySelected = false;

        // append checked attribute to query
        for (JCheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                query.append(checkBox.getText()).append(", ");
                anySelected = true;
            }
        }

        // remove space and comma
        if (anySelected) {
            query.setLength(query.length() - 2);
            query.append(" FROM ").append(tableName);
            return query.toString();
        }

        return null;
    }

    private boolean validateFields(boolean validateUsername, boolean validatePassword, boolean validateEmail,  String username, String password, String email){
        if(validateUsername && (username == null || username.isEmpty())) {
            JOptionPane.showMessageDialog(this, "UserName is empty");
            return false;
        }
        else if (validatePassword && (password == null || password.isEmpty())){
            JOptionPane.showMessageDialog(this, "Password is empty");
            return false;
        }
        else if (validateEmail && (email == null || email.isEmpty())){
            JOptionPane.showMessageDialog(this, "Email is empty");
            return false;
        }
        else if (validateEmail && !validateEmail(email)){
            JOptionPane.showMessageDialog(this, "Email is not valid");
            return false;
        }
        else if(validateUsername && userNameExists(username)){
            JOptionPane.showMessageDialog(this, "Username already exists");
            return false;
        }
        else if(validateEmail && emailExists(email)){
            JOptionPane.showMessageDialog(this, "Email already exists");
            return false;
        }
        else{
            JOptionPane.showMessageDialog(this, "Success!");
            return true;
        }
    }
    private boolean emailExists(String email){
        return adminViewPageDBHandler.getEmail(email);
    }
    private boolean userNameExists(String username){
        return adminViewPageDBHandler.getUsername(username);
    }
    /* This code was taken from stackOverflow
    * https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
    */
    private boolean validateEmail(String email){
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
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
            String userNameTextField = addUserNameTextField.getText();
            String passwordTextField = addPasswordTextField.getText();
            String emailTextField = addEmailTextField.getText();
            if (validateFields(true, true, true,
                    userNameTextField, passwordTextField, emailTextField)){
                adminViewPageDBHandler.addAccount(userNameTextField ,
                        passwordTextField,  emailTextField);

                accountPageFrame.dispose();
                buildAccountPage(adminViewPageDBHandler);
            }

        });
        updateEmailAccountButton.addActionListener(e -> {
            String emailTextField = updateEmailTextField.getText();
            if(validateFields(false, false, true, "", "", emailTextField)) {
                adminViewPageDBHandler.updateEmailAccount(updateUserEmailBox.getSelectedItem().toString(),
                        updateEmailTextField.getText());
            }
        });
        updatePasswordAccountButton.addActionListener( e -> {
            String passwordField = updatePasswordTextField.getText();
            if(validateFields(false, true, false, "", passwordField, "")) {
                adminViewPageDBHandler.updatePasswordAccount((String) updateUserPasswordBox.getSelectedItem(),
                        updatePasswordTextField.getText());
            }
        });
        deleteAccountButton.addActionListener(e -> {
            adminViewPageDBHandler.deleteAccount(usernameBox.getSelectedItem().toString());
            usernameBox.removeItemAt(usernameBox.getSelectedIndex());
            JOptionPane.showMessageDialog(null, "Account deleted!");
            accountPageFrame.dispose();
            buildAccountPage(adminViewPageDBHandler);
        });
    }
}
