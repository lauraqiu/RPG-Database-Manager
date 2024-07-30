package ui_logic;

import database.DbHandler;
import utilities.PrintablePreparedStatement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class AdminViewPageDBHandler {

    DbHandler dbHandler;

    public AdminViewPageDBHandler(DbHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    public void retrieveData(String query, boolean userNameSelected,
                             boolean isVerifiedSelected, boolean passwordSelected,
                             boolean emailSelected, boolean invSlotsSelected) {
        try {
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();
            JTable table = new JTable(tableModel);

            Vector<String> columnNames = new Vector<>();
            if (userNameSelected) {
                columnNames.add("USERNAME");
            }
            if (isVerifiedSelected) {
                columnNames.add("VERIFIED");
            }
            if (passwordSelected) {
                columnNames.add("PASSWORD");
            }
            if (emailSelected) {
                columnNames.add("EMAIL");
            }
            if (invSlotsSelected) {
                columnNames.add("INV_SLOTS");
            }
            tableModel.setColumnIdentifiers(columnNames);

            while (rs.next()) {

                Vector<Object> vectorList = new Vector<>();

                if (userNameSelected) {
                    vectorList.add(rs.getString("USERNAME"));
                }
                if (isVerifiedSelected) {
                    vectorList.add(rs.getString("ISVERIFIED"));
                }
                if (passwordSelected) {
                    vectorList.add(rs.getString("PASSWORD"));
                }
                if (emailSelected) {
                    vectorList.add(rs.getString("EMAIL"));
                }
                if (invSlotsSelected) {
                    vectorList.add(rs.getInt("INVSLOTS"));
                }
                tableModel.addRow(vectorList);
            }

            JScrollPane scrollPane  = new JScrollPane(table);
            JFrame frame = new JFrame();

            frame.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            frame.add(scrollPane, gbc);
            frame.setVisible(true);
            frame.setSize(1020,800);
            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }
}
