package ui_logic;

import database.DbHandler;
import delegates.AccountCreationPageDelegate;
import utilities.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class AccountCreationPageDBHandler implements AccountCreationPageDelegate {
    DbHandler dbHandler;
    Integer IS_VERIFIED_DEFAULT = 0;
    Integer NUM_SLOTS_DEFAULT = 0;
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
}
