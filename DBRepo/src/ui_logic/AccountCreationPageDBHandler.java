package ui_logic;

import database.DbHandler;
import delegates.AccountCreationPageDelegate;
import utilities.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import ui_logic.DBHandlerConstants;

import static ui_logic.DBHandlerConstants.IS_VERIFIED_DEFAULT;
import static ui_logic.DBHandlerConstants.NUM_SLOTS_DEFAULT;

public class AccountCreationPageDBHandler implements AccountCreationPageDelegate {
    DbHandler dbHandler;
    public AccountCreationPageDBHandler(DbHandler dbHandler) {
        this.dbHandler = dbHandler;
    }
    @Override
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


    @Override
    public boolean checkUniqueEmail(String email) {

        String query = "SELECT * FROM ACCOUNTS WHERE EMAIL = ?";
        try{
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            boolean notEmpty = !rs.isBeforeFirst();

            rs.close();
            ps.close();

            return notEmpty;

        } catch (SQLException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkUniqueUsername(String username) {
        String query = "SELECT * FROM ACCOUNTS WHERE USERNAME = ?";
        try{
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            boolean notEmpty = !rs.isBeforeFirst();

            rs.close();
            ps.close();

            return notEmpty;

        } catch (SQLException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }


    }
}
