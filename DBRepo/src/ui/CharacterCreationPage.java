package ui;

import Models.CharacterModel;
import ui_logic.CharacterCreationDBHandler;

import javax.swing.*;
import java.awt.*;

public class CharacterCreationPage extends JPanel {
    String username;

    public CharacterCreationPage(CharacterCreationDBHandler characterCreationDBHandler) {
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

        JTextField nameField = new JTextField(10);
        JComboBox classOptions = new JComboBox(new String[]{"Mage", "Warrior", "Thief", "Pirate", "Ranger"});
        JComboBox raceOptions = new JComboBox(new String[]{"Human", "Elf", "Dwarf", "Halfling"});
        JSlider ageSlider = new JSlider(JSlider.HORIZONTAL, 18, 80, 40);
        JSlider heightSlider = new JSlider(JSlider.HORIZONTAL, 18, 80, 40);
        JSlider weightSlider = new JSlider(JSlider.HORIZONTAL, 18, 80, 40);
        JSlider strengthSlider = new JSlider(JSlider.HORIZONTAL, 18, 80, 40);
        JSlider intelligenceSlider = new JSlider(JSlider.HORIZONTAL, 18, 80, 40);
        JSlider dexteritySlider = new JSlider(JSlider.HORIZONTAL, 18, 80, 40);
        JSlider charismaSlider = new JSlider(JSlider.HORIZONTAL, 18, 80, 40);
        JSlider luckSlider = new JSlider(JSlider.HORIZONTAL, 18, 80, 40);
        JButton createCharacterButton = new JButton("Create Character");

        add(nameString, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        add(classString, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        add(ageString, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        add(heightString, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        add(weightString, gridBagConstraints);
        gridBagConstraints.gridy = 5;
        add(strengthString, gridBagConstraints);
        gridBagConstraints.gridy = 6;
        add(intelligenceString, gridBagConstraints);
        gridBagConstraints.gridy = 7;
        add(dexterityString, gridBagConstraints);
        gridBagConstraints.gridy = 8;
        add(charismaString, gridBagConstraints);
        gridBagConstraints.gridy = 9;
        add(luckString, gridBagConstraints);
        gridBagConstraints.gridy = 10;
        add(raceString, gridBagConstraints);

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 1;

        add(nameField, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        add(classOptions, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        add(ageSlider, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        add(heightSlider, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        add(weightSlider, gridBagConstraints);
        gridBagConstraints.gridy = 5;
        add(strengthSlider, gridBagConstraints);
        gridBagConstraints.gridy = 6;
        add(intelligenceSlider, gridBagConstraints);
        gridBagConstraints.gridy = 7;
        add(dexteritySlider, gridBagConstraints);
        gridBagConstraints.gridy = 8;
        add(charismaSlider, gridBagConstraints);
        gridBagConstraints.gridy = 9;
        add(luckSlider, gridBagConstraints);
        gridBagConstraints.gridy = 10;
        add(raceOptions, gridBagConstraints);
        gridBagConstraints.gridy = 11;
        add(createCharacterButton, gridBagConstraints);

        //TODO AE: Add image

        createCharacterButton.addActionListener(e -> {
            String name = nameField.getText();
            String className = classOptions.getSelectedItem().toString();
            String race = raceOptions.getSelectedItem().toString();
            int age = ageSlider.getValue();
            int height = heightSlider.getValue();
            int weight = weightSlider.getValue();
            int strength = strengthSlider.getValue();
            int intelligence = intelligenceSlider.getValue();
            int dexterity = dexteritySlider.getValue();
            int charisma = charismaSlider.getValue();
            int luck = luckSlider.getValue();

            CharacterModel characterUpdate = new CharacterModel(name,
                    className,this.username, age, height, weight, strength,
                    intelligence, dexterity, charisma, luck, race);

            characterCreationDBHandler.uploadCharacter(characterUpdate, this.username);
        });
    }
    public void setUserName(String userName){
        this.username = userName;
        revalidate();
        repaint();
    }
}
