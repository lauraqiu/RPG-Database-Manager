package ui;

import ui_logic.AdminViewPageDBHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MinAvgHeightFrame extends JDialog {
    private AdminViewPageDBHandler adminViewPageDBHandler;
    private JComboBox<String> filterComboBox;
    private JButton submitButton;
    private CharactersPage parentPage;

    public MinAvgHeightFrame(CharactersPage parentPage, AdminViewPageDBHandler adminViewPageDBHandler) {
        super(parentPage, "Find Class with Minimum Average Height", true);
        this.adminViewPageDBHandler = adminViewPageDBHandler;
        this.parentPage = parentPage;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel filterLabel = new JLabel("Select query:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(filterLabel, gbc);

        filterComboBox = new JComboBox<>(new String[] {
                "--Select Query--",
                "Find Class with Minimum Average Height"
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
        String selectedQuery = (String) filterComboBox.getSelectedItem();
        if (selectedQuery != null && selectedQuery.equals("Find Class with Minimum Average Height")) {
            try {
                ResultSet resultSet = adminViewPageDBHandler.queryMinAvgHeight();
                parentPage.displayResults(resultSet);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error executing query: " + ex.getMessage());
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a valid query.");
        }
    }
}
