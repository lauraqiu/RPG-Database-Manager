package ui_logic;

import database.DbHandler;
import delegates.AccountInfoPageDelegate;
import utilities.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountInfoPageDBHandler implements AccountInfoPageDelegate {
    DbHandler dbHandler;

    public AccountInfoPageDBHandler(DbHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @Override
    public boolean isVerified(String username){
        String query = "SELECT ISVERIFIED FROM ACCOUNTS WHERE USERNAME = ?";

        try{
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            int result  = rs.getInt(1);

            rs.close();
            ps.close();

            return result == 1;

        } catch (SQLException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }


}
