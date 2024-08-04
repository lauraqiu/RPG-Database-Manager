package ui;

import ui_logic.AdminViewPageDBHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CharacterHeightWeightFrame extends JDialog {
    private AdminViewPageDBHandler adminViewPageDBHandler;
    private JTextField minHeightField;
    private JTextField maxHeightField;
    private JTextField minWeightField;
    private JTextField maxWeightField;

    public CharacterHeightWeightFrame(Frame owner, AdminViewPageDBHandler adminViewPageDBHandler) {
        super(owner, "Character Query", true);
        this.adminViewPageDBHandler = adminViewPageDBHandler;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel heightLabel = new JLabel("Height (min):");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(heightLabel, gbc);

        minHeightField = new JTextField(5);
        gbc.gridx = 1;
        add(minHeightField, gbc);

        JLabel maxHeightLabel = new JLabel("Height (max):");
        gbc.gridx = 2;
        add(maxHeightLabel, gbc);

        maxHeightField = new JTextField(5);
        gbc.gridx = 3;
        add(maxHeightField, gbc);

        JLabel weightLabel = new JLabel("Weight (min):");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(weightLabel, gbc);

        minWeightField = new JTextField(5);
        gbc.gridx = 1;
        add(minWeightField, gbc);

        JLabel maxWeightLabel = new JLabel("Weight (max):");
        gbc.gridx = 2;
        add(maxWeightLabel, gbc);

        maxWeightField = new JTextField(5);
        gbc.gridx = 3;
        add(maxWeightField, gbc);

        JButton queryButton = new JButton("Query");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        add(queryButton, gbc);

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onQuery();
            }
        });

        pack();
        setLocationRelativeTo(owner);
    }

    private void onQuery() {
        String minHeight = minHeightField.getText().trim();
        String maxHeight = maxHeightField.getText().trim();
        String minWeight = minWeightField.getText().trim();
        String maxWeight = maxWeightField.getText().trim();

        try {
            ResultSet resultSet = adminViewPageDBHandler.queryHeightWeight(
                    minHeight.isEmpty() ? null : minHeight,
                    maxHeight.isEmpty() ? null : maxHeight,
                    minWeight.isEmpty() ? null : minWeight,
                    maxWeight.isEmpty() ? null : maxWeight
            );
            ((CharactersPage) getOwner()).displayResults(resultSet);
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error querying characters: " + ex.getMessage());
        }
    }
}
