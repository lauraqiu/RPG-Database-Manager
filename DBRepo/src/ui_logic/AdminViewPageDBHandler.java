package ui_logic;

import database.DbHandler;
import utilities.PrintablePreparedStatement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.sql.ResultSetMetaData;


import static ui_logic.DBHandlerConstants.IS_VERIFIED_DEFAULT;
import static ui_logic.DBHandlerConstants.NUM_SLOTS_DEFAULT;

public class AdminViewPageDBHandler {
    private DbHandler dbHandler;

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

    public ArrayList<String> getUserNames() {
        String query = "SELECT USERNAME FROM ACCOUNTS";

        Connection connection = dbHandler.getConnection();
        PrintablePreparedStatement ps = null;
        ArrayList<String> userNames = new ArrayList<>();
        try {
            ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userNames.add(rs.getString("USERNAME"));
            }
            return userNames;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAccount(String userName) {
        String query = "DELETE FROM ACCOUNTS WHERE USERNAME = ?";
        Connection connection = dbHandler.getConnection();
        PrintablePreparedStatement ps = null;

        try {
            ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, userName);
            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAccount(String username, String password, String email) {
        String query = "INSERT INTO ACCOUNTS VALUES(?, ?, ?, ?, ?)";

        try {
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, username);
            ps.setInt(2, IS_VERIFIED_DEFAULT);
            ps.setString(3, password);
            ps.setString(4, email);
            ps.setInt(5, NUM_SLOTS_DEFAULT);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }

    public void updateEmailAccount(String username, String email) {
        String query = "UPDATE ACCOUNTS SET EMAIL = ? WHERE USERNAME = ?";

        try {
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, email);
            ps.setString(2, username);
            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }

    public void updatePasswordAccount(String username, String password) {

        String query = "UPDATE ACCOUNTS SET PASSWORD = ? WHERE USERNAME = ?";

        try {
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, password);
            ps.setString(2, username);
            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }

    public void getItems() {
        String query = "SELECT * FROM ITEMS";

        Connection connection = dbHandler.getConnection();
        PrintablePreparedStatement ps = null;
        ArrayList<String> userNames = new ArrayList<>();

        JFrame itemsFrame = new JFrame();
        itemsFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);

        try {
            ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getString("NAME"),
                        rs.getInt("ISKEY") == 1 ? "True" : "False",
                        rs.getString("DESCRIPTION")}
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet queryCharacters(String condition) throws SQLException {
        String query = "SELECT * FROM Characters WHERE " + condition;
        Connection connection = dbHandler.getConnection();
        PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
        return ps.executeQuery();
    }

    public ResultSet aggregationQuery(String query) throws SQLException {
        Connection connection = dbHandler.getConnection();
        PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
        return ps.executeQuery();
    }

    // need to fix this
    public void equipItem(String equipment, String character, String username) {
        String query = "INSERT INTO EQUIPPED (eqname, cid, acc_user) VALUES (?, ?, ?)";
        try {
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);

            ps.setString(1, equipment);
            ps.setString(2, character);
            ps.setString(3, username);

            ps.executeUpdate();
            connection.commit();
            ps.close();

            JOptionPane.showMessageDialog(null, "Item equipped successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error equipping item.");
        }
    }

    public void showFullTable(String tableName) {
        String query = "SELECT * FROM " + tableName;

        try {
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            Vector<String> columnNames = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }

            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            JTable table = new JTable(tableModel);

            while (rs.next()) {
                Vector<Object> rowData = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.add(rs.getObject(i));
                }
                tableModel.addRow(rowData);
            }

            showResultTable(table);

            ps.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to fetch data from table " + tableName);
            e.printStackTrace();
        }
    }

    public String[] getData(String columnName, String tableName) {
        String[] data = new String[0];
        try {
            Connection connection = dbHandler.getConnection();
            String query = "SELECT " + columnName + " FROM " + tableName;
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            Vector<String> dataList = new Vector<>();
            while (rs.next()) {
                dataList.add(rs.getString(columnName));
            }

            data = dataList.toArray(new String[0]);

            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to fetch data from " + tableName);
            e.printStackTrace();
        }
        return data;
    }

    public void getEquipmentByCharacter(String character) {
        String query = "SELECT c.ID, NAME, CLASS, EQTYPE, EQNAME " +
                "FROM EQUIPPED e, CHARACTERS c " +
                "WHERE c.ID = e.CID and c.CLASS = ? ";

        try {
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, character);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.setColumnIdentifiers(new String[]{"ID", "NAME", "CLASS", "EQTYPE", "EQNAME"});
            JTable jtable = new JTable(tableModel);
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getString(1),
                        rs.getString("NAME"),
                        rs.getString("CLASS"),
                        rs.getString("EQTYPE"),
                        rs.getString("EQNAME")}
                );
            }
            showResultTable(jtable);
        } catch (SQLException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }

    public boolean getEmail(String email) {
        String query = "SELECT COUNT(*) " +
                "FROM ACCOUNTS " +
                "WHERE EMAIL = ?";

        try {
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            int result = 0;
            while(rs.next()){
                result = rs.getInt(1);
            }
            return result != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getUsername(String userName) {
        String query = "SELECT COUNT(*) " +
                "FROM ACCOUNTS " +
                "WHERE USERNAME = ?";

        try {
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            int result = 0;
            while(rs.next()){
                result = rs.getInt(1);
            }
            return result != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
