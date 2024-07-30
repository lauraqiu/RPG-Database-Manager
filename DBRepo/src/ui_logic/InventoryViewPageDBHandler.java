package ui_logic;

import Models.CharacterRetrievalInfo;
import database.DbHandler;
import utilities.PrintablePreparedStatement;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryViewPageDBHandler {

    DbHandler dbHandler;
    public InventoryViewPageDBHandler(DbHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

//    @Override
//    public ArrayList<CharacterRetrievalInfo> getUpdatedInventoryInfo(String username) {
//        String query = "SELECT NAME,LVL,CLASS,ID FROM CHARACTERS WHERE ACC_USER = ?";
//
//        try{
//            Connection connection = dbHandler.getConnection();
//            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
//            ps.setString(1,username);
//            ResultSet rs = ps.executeQuery();
//            ArrayList<CharacterRetrievalInfo> queryResults = new ArrayList<>();
//
//            int i = 0;
//            while (rs.next()) {
//                queryResults.add(
//                        new CharacterRetrievalInfo(rs.getString("NAME"),
//                                rs.getInt("LVL"),
//                                rs.getString("CLASS"),
//                                "NONE",
//                                rs.getString("ID")
//                        ));
//
//                i++;
//            }
//
//            ps.executeUpdate();
//            connection.commit();
//            ps.close();
//
//            return queryResults;
//
//        } catch (SQLException e) {
//            System.out.println("ERROR");
//            throw new RuntimeException(e);
//        }
//
//    }

}
