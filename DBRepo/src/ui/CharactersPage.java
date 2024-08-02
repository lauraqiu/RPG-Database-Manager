package ui;

import ui_logic.AdminViewPageDBHandler;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class CharactersPage extends JFrame {
    private AdminViewPageDBHandler adminViewPageDBHandler;
    private JButton submitButton;
    private JPanel tablePanel;
    private JComboBox<String> aggregationComboBox;
    private JTextField minHeightField;
    private JTextField maxHeightField;
    private JTextField minWeightField;
    private JTextField maxWeightField;

    public CharactersPage(AdminViewPageDBHandler adminViewPageDBHandler) {
        this.adminViewPageDBHandler = adminViewPageDBHandler;

        setTitle("Character Search");
        setSize(1020, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel instructionsPanel = new JPanel();
        JLabel instructionsLabel = new JLabel("Specify the height and/or weight constraints to filter characters by:");
        instructionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionsPanel.add(instructionsLabel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(instructionsPanel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        JLabel heightLabel = new JLabel("Height (min):");
        topPanel.add(heightLabel, gbc);

        gbc.gridx = 1;
        minHeightField = new JTextField(5);
        topPanel.add(minHeightField, gbc);

        gbc.gridx = 2;
        JLabel maxHeightLabel = new JLabel("Height (max):");
        topPanel.add(maxHeightLabel, gbc);

        gbc.gridx = 3;
        maxHeightField = new JTextField(5);
        topPanel.add(maxHeightField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel weightLabel = new JLabel("Weight (min):");
        topPanel.add(weightLabel, gbc);

        gbc.gridx = 1;
        minWeightField = new JTextField(5);
        topPanel.add(minWeightField, gbc);

        gbc.gridx = 2;
        JLabel maxWeightLabel = new JLabel("Weight (max):");
        topPanel.add(maxWeightLabel, gbc);

        gbc.gridx = 3;
        maxWeightField = new JTextField(5);
        topPanel.add(maxWeightField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel aggregationInstructionLabel = new JLabel("Filter by aggregation:");
        aggregationInstructionLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center text in the label
        topPanel.add(aggregationInstructionLabel, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 1;
        JLabel aggregationLabel = new JLabel("Select Aggregation:");
        topPanel.add(aggregationLabel, gbc);

        gbc.gridx = 1;
        String[] aggregationOptions = {
                "--Select Aggregation--",
                "Group by Race and Average Age"
        };
        aggregationComboBox = new JComboBox<>(aggregationOptions);
        topPanel.add(aggregationComboBox, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 2;
        submitButton = new JButton("Search");
        submitButton.addActionListener(e -> onSubmit());
        topPanel.add(submitButton, gbc);

        add(topPanel, BorderLayout.NORTH);

        tablePanel = new JPanel(new BorderLayout());
        add(tablePanel, BorderLayout.CENTER);

        displayDefaultCharactersTable();

        setVisible(true);
    }

    private void displayDefaultCharactersTable() {
        try {
            ResultSet resultSet = adminViewPageDBHandler.queryCharacters("1=1");
            displayResults(resultSet);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error displaying characters table: " + e.getMessage());
        }
    }

    private void onSubmit() {
        String minHeight = minHeightField.getText().trim();
        String maxHeight = maxHeightField.getText().trim();
        String minWeight = minWeightField.getText().trim();
        String maxWeight = maxWeightField.getText().trim();

        StringBuilder condition = new StringBuilder("1=1");
        if (!minHeight.isEmpty()) {
            condition.append(" AND HEIGHT >= ").append(minHeight);
        }
        if (!maxHeight.isEmpty()) {
            condition.append(" AND HEIGHT <= ").append(maxHeight);
        }
        if (!minWeight.isEmpty()) {
            condition.append(" AND WEIGHT >= ").append(minWeight);
        }
        if (!maxWeight.isEmpty()) {
            condition.append(" AND WEIGHT <= ").append(maxWeight);
        }

        String selectedAggregation = (String) aggregationComboBox.getSelectedItem();

        try {
            if (selectedAggregation != null && selectedAggregation.equals("Group by Race and Average Age")) {
                String aggregationQuery = "SELECT RACE, AVG(age) FROM CHARACTERS WHERE " + condition + " GROUP BY RACE";
                ResultSet resultSet = adminViewPageDBHandler.aggregationQuery(aggregationQuery);
                displayResults(resultSet);
                aggregationComboBox.setSelectedIndex(0);
            } else {
                ResultSet resultSet = adminViewPageDBHandler.queryCharacters(condition.toString());
                displayResults(resultSet);
            }
            JOptionPane.showMessageDialog(this, "Query successful!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error querying characters: " + e.getMessage());
        }
    }

    private void displayResults(ResultSet resultSet) throws SQLException {
        tablePanel.removeAll();

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable resultsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        Vector<String> columnNames = new Vector<>();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(metaData.getColumnName(i));
        }
        tableModel.setColumnIdentifiers(columnNames);

        Vector<Vector<Object>> data = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> row = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                row.add(resultSet.getObject(i));
            }
            data.add(row);
        }
        for (Vector<Object> rowData : data) {
            tableModel.addRow(rowData);
        }

        tablePanel.revalidate();
        tablePanel.repaint();
    }
}
