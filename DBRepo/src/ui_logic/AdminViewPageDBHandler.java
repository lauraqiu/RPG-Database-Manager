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

import static ui_logic.DBHandlerConstants.IS_VERIFIED_DEFAULT;
import static ui_logic.DBHandlerConstants.NUM_SLOTS_DEFAULT;

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
    public ArrayList<String> getUserNames(){
        String query = "SELECT USERNAME FROM ACCOUNTS";

        Connection connection = dbHandler.getConnection();
        PrintablePreparedStatement ps = null;
        ArrayList<String> userNames = new ArrayList<>();
        try {
            ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                userNames.add(rs.getString("USERNAME"));
            }
            return userNames;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteAccount(String userName){
        String query = "DELETE FROM ACCOUNTS WHERE USERNAME = ?";
        Connection connection = dbHandler.getConnection();
        PrintablePreparedStatement ps = null;

        try {
            ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1,userName);
            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }}

    public void addAccount(String username, String password, String email) {
        String query = 	"INSERT INTO ACCOUNTS VALUES(?, ?, ?, ?, ?)";

        try {
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, username);
            ps.setInt(2, IS_VERIFIED_DEFAULT);
            ps.setString(3, password);
            ps.setString(4,email);
            ps.setInt(5, NUM_SLOTS_DEFAULT);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }
    public void updateEmailAccount(String username, String email ){
        String query = "UPDATE ACCOUNTS SET EMAIL = ? WHERE USERNAME = ?";

        try{
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1,email);
            ps.setString(2,username);
            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }
    public void updatePasswordAccount(String username, String password ){

        String query = "UPDATE ACCOUNTS SET PASSWORD = ? WHERE USERNAME = ?";

        try{
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1,password);
            ps.setString(2,username);
            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }
}
