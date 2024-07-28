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
            rs.next();
            int result  = rs.getInt(1);

            rs.close();
            ps.close();

            return result == 1;

        } catch (SQLException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setIsVerified(Boolean bool, String username) {
        String query = "UPDATE ACCOUNTS SET ISVERIFIED = ? WHERE USERNAME = ?";
        try{
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1,bool ? 1 : 0);
            ps.setString(2,username);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet getUpdatedCharacterInfo(String username) {
        String query = "SELECT NAME,LVL,CLASS, FROM CHARACTERS WHERE USERNAME = ?";

        try{
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();

            ps.executeUpdate();
            connection.commit();
            ps.close();

            return rs;

        } catch (SQLException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }

    }


}
