package ui_logic;

import Models.CharacterModel;
import Models.CharacterRetrievalInfo;
import database.DbHandler;
import delegates.CharacterViewPageDelegate;
import utilities.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CharacterViewPageDBHandler implements CharacterViewPageDelegate {
    DbHandler dbHandler;

    public CharacterViewPageDBHandler(DbHandler dbHandler){
        this.dbHandler = dbHandler;
    }

    @Override
    public CharacterModel getCharacterInfo(String username, String id) {
        String query = "SELECT * FROM CHARACTERS WHERE ACC_USER = ? AND ID = ?";

        try{
            Connection connection = dbHandler.getConnection();
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1,username);
            ps.setString(2, id);
            ResultSet rs = ps.executeQuery();


            rs.next();
            CharacterModel fetchedChar = new CharacterModel(rs.getString("ID"), rs.getString("ACC_USER"),
                    "None", rs.getString("NAME"), rs.getString("CLASS"),
                    rs.getString("RACE"), rs.getInt("AGE"), rs.getInt("HEIGHT"),
                    rs.getInt("LVL"), rs.getInt("STRENGTH"), rs.getInt("INTELLIGENCE"),
                    rs.getInt("DEXTERITY"), rs.getInt("CHARISMA"), rs.getInt("LUCK"),
                    rs.getInt("LUCK"), rs.getInt("MONEY"), rs.getInt("INVSLOTS")
            );
            ps.executeUpdate();
            connection.commit();
            ps.close();

            return fetchedChar;

        } catch (SQLException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }

    }
}
