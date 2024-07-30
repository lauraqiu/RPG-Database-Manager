package ui;

import ui_logic.AdminViewPageDBHandler;

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
        add(attribute, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(tableBox, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        add(searchButton, gridBagConstraints);

        searchButton.addActionListener(e -> showAttributesFrame((String) tableBox.getSelectedItem()));
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
}
