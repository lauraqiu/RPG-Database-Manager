package ui_logic;

import Models.CharacterModel;
import database.DbHandler;
import delegates.CharacterCreationPageDelegate;
import utilities.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;

public class CharacterCreationDBHandler implements CharacterCreationPageDelegate {

    DbHandler dbHandler;
    public CharacterCreationDBHandler(DbHandler dbHandler) {
        this.dbHandler = dbHandler;
    }
    @Override
    public void uploadCharacter(CharacterModel character, String username) {
        String query = "INSERT INTO CHARACTERS VALUES ( ?, ? ,? ,? ,? ,?, ?, ? ,? ,? ,? ,?, ?, ?, ?, ?)";
        Connection connection = dbHandler.getConnection();

        PrintablePreparedStatement ps = null;
        try {
            ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);

            ps.setString(1, String.valueOf(System.currentTimeMillis()));
            ps.setString(2, character.getUsername());
            ps.setString(3, character.getName());
            ps.setString(4, character.getClassString());
            ps.setInt(5, character.getAge());
            ps.setInt(6, character.getHeight());
            ps.setInt(7, character.getWeight());
            ps.setString(8, character.getRace());
            ps.setInt(9, 0);
            ps.setInt(10, 1000);
            ps.setInt(11, character.getStrength());
            ps.setInt(12, character.getIntelligence());
            ps.setInt(13, character.getCharisma());
            ps.setInt(14, character.getDexterity());
            ps.setInt(15, character.getLuck());
            ps.setInt(16, 0);

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    }

