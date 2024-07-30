package ui_logic;

import database.DbHandler;
import utilities.PrintablePreparedStatement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class AdminViewPageDBHandler {
    DbHandler dbHandler;

    public AdminViewPageDBHandler(DbHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    public void retrieveData(String query, JCheckBox[] checkBoxes) {
        try {
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();
            JTable table = new JTable(tableModel);

            Vector<String> columnNames = new Vector<>();
            for (JCheckBox checkBox : checkBoxes) {
                // get column names of selected checkboxes
                if (checkBox.isSelected()) {
                    columnNames.add(checkBox.getText().toUpperCase().replace(" ", "_"));
                }
            }
            tableModel.setColumnIdentifiers(columnNames);

            // populate rows with data returned from query
            while (rs.next()) {
                Vector<Object> rowData = new Vector<>();
                for (String columnName : columnNames) {
                    rowData.add(rs.getObject(columnName));
                }
                tableModel.addRow(rowData);
            }

            showResultTable(table);

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }

    // moved view table stuff to separate function
    private void showResultTable(JTable table) {
        JScrollPane scrollPane = new JScrollPane(table);
        JFrame frame = new JFrame();

        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(scrollPane, gbc);
        frame.setVisible(true);
        frame.setSize(1020, 800);
    }
}