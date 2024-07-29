package ui;

import ui_logic.CharacterCreationDBHandler;
import ui_logic.CharacterViewPageDBHandler;

import javax.swing.*;
import java.awt.*;

public class CharacterViewPage extends JPanel {
    CharacterViewPage(BackgroundFrame backgroundFrame, CharacterViewPageDBHandler characterCreationDBHandler) {
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;

        JLabel nameString = new JLabel("Name: ");
        JLabel classString = new JLabel("Class:");
        JLabel ageString = new JLabel("Age:");
        JLabel heightString = new JLabel("Height:");
        JLabel weightString = new JLabel("Weight:");
        JLabel strengthString = new JLabel("Strength:");
        JLabel intelligenceString = new JLabel("Intelligence:");
        JLabel dexterityString = new JLabel("Dexterity:");
        JLabel charismaString = new JLabel("Charisma:");
        JLabel luckString = new JLabel("Luck:");
        JLabel raceString = new JLabel("Race:");


    }



}
