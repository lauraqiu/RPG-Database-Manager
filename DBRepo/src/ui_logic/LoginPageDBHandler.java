package ui_logic;

import database.DbHandler;
import delegates.LoginWindowDelegate;
import utilities.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageDBHandler implements LoginWindowDelegate {
    private DbHandler dbHandler;

    public LoginPageDBHandler(DbHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @Override
    public void login(String username, String password) {
        // selects columns with matching user and pass
        String query = "SELECT * FROM ACCOUNTS WHERE USERNAME = ? AND PASSWORD = ?";

        try {
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            // checks if there's a matching row in db
            if (!rs.next()) {
                throw new RuntimeException("Invalid username or password");
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
