package ui;

import Models.CharacterModel;
import ui_logic.CharacterViewPageDBHandler;

import javax.swing.*;
import java.awt.*;

public class CharacterViewPage extends JPanel {
    String username = null;
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

        JLabel nameString = new JLabel("Name: ");
        JLabel nameVal = new JLabel(character.getName());
        JLabel classString = new JLabel("Class: ");
        JLabel classVal = new JLabel(character.getClassString());
        JLabel raceString = new JLabel("Race: ");
        JLabel raceVal = new JLabel(character.getRace());
        JLabel ageString = new JLabel("Age: ");
        JLabel ageVal = new JLabel(String.valueOf(character.getAge()));
        JLabel heightString = new JLabel("Height: ");
        JLabel heightVal = new JLabel(String.valueOf(character.getHeight()));
        JLabel weightString = new JLabel("Weight: ");
        JLabel weightVal = new JLabel(String.valueOf(character.getWeight()));
        JLabel strengthString = new JLabel("Strength: ");
        JLabel strVal = new JLabel(String.valueOf(character.getStrength()));
        JLabel intelligenceString = new JLabel("Intelligence: ");
        JLabel intVal = new JLabel(String.valueOf(character.getIntelligence()));
        JLabel dexterityString = new JLabel("Dexterity: ");
        JLabel dexVal = new JLabel(String.valueOf(character.getDexterity()));
        JLabel charismaString = new JLabel("Charisma: " );
        JLabel chaVal  = new JLabel(String.valueOf(character.getCharisma()));
        JLabel luckString = new JLabel("Luck: " );
        JLabel luckVal = new JLabel(String.valueOf(character.getLuck()));
        JLabel moneyString = new JLabel("Money: " );
        JLabel moneyVal = new JLabel(String.valueOf(character.getMoney()));
        JLabel invslotsString = new JLabel("Open Inventory Slots: " );
        JLabel invslotsVal = new JLabel(String.valueOf(character.getInvslots()));

        JButton Inventory = new JButton("Inventory");

        add(nameString, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        add(classString, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        add(raceString, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        add(ageString, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        add(heightString, gridBagConstraints);
        gridBagConstraints.gridy = 5;
        add(weightString, gridBagConstraints);
        gridBagConstraints.gridy = 6;
        add(strengthString, gridBagConstraints);
        gridBagConstraints.gridy = 7;
        add(intelligenceString, gridBagConstraints);
        gridBagConstraints.gridy = 8;
        add(dexterityString, gridBagConstraints);
        gridBagConstraints.gridy = 9;
        add(charismaString, gridBagConstraints);
        gridBagConstraints.gridy = 10;
        add(luckString, gridBagConstraints);
        gridBagConstraints.gridy = 11;
        add(moneyString, gridBagConstraints);
        gridBagConstraints.gridy = 12;
        add(invslotsString, gridBagConstraints);

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 1;

        add(nameVal, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        add(classVal, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        add(raceVal, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        add(ageVal, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        add(heightVal, gridBagConstraints);
        gridBagConstraints.gridy = 5;
        add(weightVal, gridBagConstraints);
        gridBagConstraints.gridy = 6;
        add(strVal, gridBagConstraints);
        gridBagConstraints.gridy = 7;
        add(intVal, gridBagConstraints);
        gridBagConstraints.gridy = 8;
        add(dexVal, gridBagConstraints);
        gridBagConstraints.gridy = 9;
        add(chaVal, gridBagConstraints);
        gridBagConstraints.gridy = 10;
        add(luckVal, gridBagConstraints);
        gridBagConstraints.gridy = 11;
        add(moneyVal, gridBagConstraints);
        gridBagConstraints.gridy = 12;
        add(invslotsVal, gridBagConstraints);

        gridBagConstraints.gridy = 13;
        add(Inventory, gridBagConstraints);

        Inventory.addActionListener(e -> {
            backgroundFrame.getInventoryViewPage().setUserNameContext(this.username);
            backgroundFrame.getInventoryViewPage().updateCharacters();
            backgroundFrame.navigateToInventoryViewPage();
        });
    }

    public void setUserName(String userName){
        this.username = userName;
    }
    public void setCharacterID(String characterID){
        this.characterID = characterID;
    }
    public void setUserNameContext(String userName) {
        this.username = userName;
        revalidate();
        repaint();
    }

}
