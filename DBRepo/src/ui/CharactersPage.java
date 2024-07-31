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
    private JComboBox<String> conditionComboBox;
    private JButton submitButton;
    private JPanel tablePanel;
    private JComboBox<String> aggregationComboBox;

    public CharactersPage(AdminViewPageDBHandler adminViewPageDBHandler) {
        this.adminViewPageDBHandler = adminViewPageDBHandler;

        setTitle("Character Search");
        setSize(1020, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("Character:");
        topPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        String[] conditions = {
                "--Select--",
                "HEIGHT > 130 AND WEIGHT < 90",
                "HEIGHT < 130",
                "WEIGHT > 90"
        };
        conditionComboBox = new JComboBox<>(conditions);
        topPanel.add(conditionComboBox, gbc);

        // Aggregation selection
        gbc.gridy = 2;
        String[] aggregationOptions = {
                "--Select Aggregation--",
                "Group by Race and Average Age"
        };
        aggregationComboBox = new JComboBox<>(aggregationOptions);
        topPanel.add(aggregationComboBox, gbc);

        add(topPanel, BorderLayout.NORTH);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> onSubmit());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH);

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
        String selectedCondition = (String) conditionComboBox.getSelectedItem();
        String selectedAggregation = (String) aggregationComboBox.getSelectedItem();

        if (selectedAggregation != null && selectedAggregation.equals("Group by Race and Average Age")) {
            handleAggregation();
            aggregationComboBox.setSelectedIndex(0);
        } else if (selectedCondition != null && !selectedCondition.equals("--Select--")) {
            try {
                ResultSet resultSet = adminViewPageDBHandler.queryCharacters(selectedCondition);
                displayResults(resultSet);
                conditionComboBox.setSelectedIndex(0);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error querying characters: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a condition or aggregation.");
        }
    }

    private void handleAggregation() {
        String aggregationQuery = "SELECT RACE, AVG(age) FROM CHARACTERS GROUP BY RACE";
        try {
            ResultSet resultSet = adminViewPageDBHandler.aggregationQuery(aggregationQuery);
            displayResults(resultSet);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error executing aggregation query: " + e.getMessage());
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
