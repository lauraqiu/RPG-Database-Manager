package ui;

import ui_logic.AdminViewPageDBHandler;
import ui_logic.CharacterCreationDBHandler;

import javax.swing.*;
import java.awt.*;

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

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        add(attribute, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(tableBox, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        add(searchButton, gridBagConstraints);
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
        });
    }
}
