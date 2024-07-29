package ui;

import Models.CharacterModel;
import ui_logic.CharacterViewPageDBHandler;

import javax.swing.*;
import java.awt.*;

public class CharacterViewPage extends JPanel {
    String username;
    String characterID;
    private BackgroundFrame backgroundFrame;
    private GridBagConstraints gridBagConstraints;
    private final CharacterViewPageDBHandler characterViewPageDBHandler;

    CharacterViewPage(BackgroundFrame backgroundFrame, CharacterViewPageDBHandler characterViewPageDBHandler) {
        this.backgroundFrame = backgroundFrame;
        this.characterViewPageDBHandler = characterViewPageDBHandler;
    }

    public void buildPage(){
        CharacterModel character = characterViewPageDBHandler.getCharacterInfo(username,characterID);

        setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;

        JLabel nameString = new JLabel(character.getName());
        JLabel classString = new JLabel(character.getClassString());
        JLabel raceString = new JLabel(character.getRace());
        JLabel ageString = new JLabel(String.valueOf(character.getAge()));
        JLabel heightString = new JLabel(String.valueOf(character.getHeight()));
        JLabel weightString = new JLabel(String.valueOf(character.getWeight()));
        JLabel strengthString = new JLabel(String.valueOf(character.getStrength()));
        JLabel intelligenceString = new JLabel(String.valueOf(character.getIntelligence()));
        JLabel dexterityString = new JLabel(String.valueOf(character.getDexterity()));
        JLabel charismaString = new JLabel("Charisma: " + String.valueOf(character.getCharisma()));
        JLabel luckString = new JLabel("Luck: " + String.valueOf(character.getLuck()));
        JLabel moneyString = new JLabel("Money: " + String.valueOf(character.getMoney()));

        JButton Inventory = new JButton("Inventory");

        add(Inventory, gridBagConstraints);
        Inventory.addActionListener(e -> {
            backgroundFrame.navigateToInventoryViewPage();
        });
    }

    public void setUserName(String userName){
        this.username = userName;
    }
    public void setCharacterID(String characterID){
        this.characterID = characterID;
    }


}
