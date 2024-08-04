package ui;

import ui_logic.AdminViewPageDBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class CharactersPage extends JFrame {
    private AdminViewPageDBHandler adminViewPageDBHandler;
    private JButton heightWeightButton;
    private JButton groupByButton;
    private JButton havingButton;
    private JPanel tablePanel;


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

        heightWeightButton = new JButton("Filter by Height/Weight");
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(heightWeightButton, gbc);
        heightWeightButton.addActionListener(e -> openHeightWeightFrame());

        groupByButton = new JButton("Filter by Race/Age");
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(groupByButton, gbc);
        groupByButton.addActionListener(e -> openRaceAgeFrame());

        havingButton = new JButton("Filter by Race/Class + Condition");
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(havingButton, gbc);
        havingButton.addActionListener(e -> openRaceClassFrame());

        add(topPanel, BorderLayout.NORTH);

        tablePanel = new JPanel(new BorderLayout());
        add(tablePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void openHeightWeightFrame() {
        CharacterHeightWeightFrame queryHeightWeight = new CharacterHeightWeightFrame(this, adminViewPageDBHandler);
        queryHeightWeight.setVisible(true);
    }

    private void openRaceAgeFrame() {
        CharacterRaceAgeFrame queryRaceAge = new CharacterRaceAgeFrame(this, adminViewPageDBHandler);
        queryRaceAge.setVisible(true);
    }

    private void openRaceClassFrame() {
        CharacterRaceClassFrame queryRaceClass = new CharacterRaceClassFrame(this, adminViewPageDBHandler);
        queryRaceClass.setVisible(true);
    }

    public void displayResults(ResultSet resultSet) {
        tablePanel.removeAll();

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable resultsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        try {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error displaying results: " + e.getMessage());
        }
    }
}
