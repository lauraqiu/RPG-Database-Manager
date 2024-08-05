package ui;

import ui_logic.AdminViewPageDBHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacterRaceAgeFrame extends JDialog {
    private AdminViewPageDBHandler adminViewPageDBHandler;
    private JComboBox<String> filterComboBox;
    private JButton submitButton;
    private CharactersPage parentPage;

    public CharacterRaceAgeFrame(CharactersPage parentPage, AdminViewPageDBHandler adminViewPageDBHandler) {
        super(parentPage, "Filter Characters", true);
        this.adminViewPageDBHandler = adminViewPageDBHandler;
        this.parentPage = parentPage;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel filterLabel = new JLabel("Select aggregation:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(filterLabel, gbc);

        filterComboBox = new JComboBox<>(new String[]{
                "--Select Aggregation--",
                "Find Average Age for Every Race"
        });
        gbc.gridx = 1;
        add(filterComboBox, gbc);

        submitButton = new JButton("Submit");
        gbc.gridy = 1;
        gbc.gridx = 1;
        add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        pack();
        setLocationRelativeTo(parentPage);
    }

    private void handleSubmit() {
        String selectedFilter = (String) filterComboBox.getSelectedItem();
        if (selectedFilter != null && selectedFilter.equals("Find Average Age for Every Race")) {
            try {
                String condition = "1=1"; // default
                Object[] params = {};

                ResultSet resultSet = adminViewPageDBHandler.queryRaceAge(condition, params);
                parentPage.displayResults(resultSet);
                JOptionPane.showMessageDialog(this, "Success!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error executing query: " + ex.getMessage());
            }
            dispose();
        }
    }
}
