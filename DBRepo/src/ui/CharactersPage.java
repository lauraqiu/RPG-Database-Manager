package ui;

import ui_logic.AdminViewPageDBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class CharactersPage extends JFrame {
    private AdminViewPageDBHandler adminViewPageDBHandler;
    private JButton heightWeightButton;
    private JButton groupByButton;
    private JButton havingButton;
    private JButton AvgHeightButton;
    private JButton CreateCharacterButton;
    private JButton GetCharacterButton;
    private JPanel tablePanel;

    public CharactersPage(AdminViewPageDBHandler adminViewPageDBHandler) {
        this.adminViewPageDBHandler = adminViewPageDBHandler;

        setTitle("Character Search");
        setSize(1020, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        heightWeightButton = new JButton("Filter by Height/Weight");
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(heightWeightButton, gbc);
        heightWeightButton.addActionListener(e -> openHeightWeightFrame());

        groupByButton = new JButton("Filter by Race/Age");
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(groupByButton, gbc);
        groupByButton.addActionListener(e -> openRaceAgeFrame());

        havingButton = new JButton("Filter by Race/Class + Condition");
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(havingButton, gbc);
        havingButton.addActionListener(e -> openRaceClassFrame());

        AvgHeightButton = new JButton("Find Class with Max/Min Avg Height");
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(AvgHeightButton, gbc);
        AvgHeightButton.addActionListener(e -> openAvgHeightFrame());

        CreateCharacterButton = new JButton("Create Character");
        gbc.gridy = 7;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(CreateCharacterButton, gbc);
        CreateCharacterButton.addActionListener(e -> createCharacterFrame());
        add(topPanel, BorderLayout.NORTH);

        GetCharacterButton = new JButton("Get Characters");
        gbc.gridy = 8;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(GetCharacterButton, gbc);
        GetCharacterButton.addActionListener(e -> getCharacterFrame());
        add(topPanel, BorderLayout.NORTH);

        tablePanel = new JPanel(new BorderLayout());
        add(tablePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void openHeightWeightFrame() {
        CharacterHeightWeightFrame queryHeightWeight = new CharacterHeightWeightFrame(this, adminViewPageDBHandler);
        queryHeightWeight.setVisible(true);
    }

    private void openRaceAgeFrame() {
        CharacterRaceAgeFrame queryRaceAge = new CharacterRaceAgeFrame(this, adminViewPageDBHandler);
        queryRaceAge.setVisible(true);
    }

    private void openRaceClassFrame() {
        CharacterRaceClassFrame queryRaceClass = new CharacterRaceClassFrame(this, adminViewPageDBHandler);
        queryRaceClass.setVisible(true);
    }

    private void openAvgHeightFrame() {
        AvgHeightFrame AvgHeightFrame = new AvgHeightFrame(this, adminViewPageDBHandler);
        AvgHeightFrame.setVisible(true);
    }
    private void getCharacterFrame(){

        adminViewPageDBHandler.showFullTable("CHARACTERS");
    }
    private void createCharacterFrame() {
        JFrame createCharacterFrame = new JFrame("Create Character");
        createCharacterFrame.setSize(1000, 1020);
        JLabel characterIDLabel = new JLabel("Character ID");
        JLabel accUserLabel = new JLabel("Acc User");
        JLabel nameLabel = new JLabel("Name");
        JLabel classLabel = new JLabel("Class");
        JLabel ageLabel = new JLabel("Age");
        JLabel heightLabel = new JLabel("Height");
        JLabel weightLabel = new JLabel("Weight");
        JLabel raceLabel = new JLabel("Race");
        JLabel levelLabel = new JLabel("Level");
        JLabel moneyLabel = new JLabel("Money");
        JLabel strengthLabel = new JLabel("Strength");
        JLabel intelligenceLabel = new JLabel("Intelligence");
        JLabel charismaLabel = new JLabel("Charisma");
        JLabel dexterityLabel = new JLabel("Dexterity");
        JLabel luckLabel = new JLabel("Luck");
        JLabel invSlots = new JLabel("invSlots");
        JLabel serverName = new JLabel("Server Name");

        JTextField characterIDTextField = new JTextField(10);
        JTextField accUserTextField = new JTextField(10);
        JTextField nameTextField = new JTextField(10);
        JTextField classTextField = new JTextField(10);
        JTextField ageTextField = new JTextField(10);
        JTextField heightTextField = new JTextField(10);
        JTextField weightTextField = new JTextField(10);
        JTextField raceTextField = new JTextField(10);
        JTextField levelTextField = new JTextField(10);
        JTextField moneyTextField = new JTextField(10);
        JTextField strengthTextField = new JTextField(10);
        JTextField intelligenceTextField = new JTextField(10);
        JTextField charismaTextField = new JTextField(10);
        JTextField dexterityTextField = new JTextField(10);
        JTextField luckTextField = new JTextField(10);
        JTextField invSlotsTextField = new JTextField(10);
        JTextField serverNameTextField = new JTextField(10);

        JButton createCharacterButton = new JButton("Create Character");

        createCharacterFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        createCharacterFrame.add(characterIDLabel, gbc);
        gbc.gridx = 1;
        createCharacterFrame.add(characterIDTextField, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        createCharacterFrame.add(accUserLabel,gbc);
        gbc.gridx = 1;
        createCharacterFrame.add(accUserTextField, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        createCharacterFrame.add(nameLabel,gbc);
        gbc.gridx = 1;
        createCharacterFrame.add(nameTextField, gbc);
        gbc.gridy = 3;
        gbc.gridx = 0;
        createCharacterFrame.add(classLabel,gbc);
        gbc.gridx = 1;
        createCharacterFrame.add(classTextField, gbc);
        gbc.gridy = 4;
        gbc.gridx = 0;
        createCharacterFrame.add(ageLabel,gbc);
        gbc.gridx = 1;
        createCharacterFrame.add(ageTextField, gbc);
        gbc.gridy = 5;
        gbc.gridx = 0;
        createCharacterFrame.add(heightLabel,gbc);
        gbc.gridx = 1;
        createCharacterFrame.add(heightTextField, gbc);
        gbc.gridy = 6;
        gbc.gridx = 0;
        createCharacterFrame.add(weightLabel,gbc);
        gbc.gridx = 1;
        createCharacterFrame.add(weightTextField, gbc);
        gbc.gridy = 7;
        gbc.gridx = 0;
        createCharacterFrame.add(raceLabel,gbc);
        gbc.gridx = 1;
        createCharacterFrame.add(raceTextField, gbc);
        gbc.gridy = 8;
        gbc.gridx = 0;
        createCharacterFrame.add(levelLabel,gbc);
        gbc.gridx = 1;
        createCharacterFrame.add(levelTextField, gbc);
        gbc.gridy = 9;
        gbc.gridx = 0;
        createCharacterFrame.add(moneyLabel,gbc);
        gbc.gridx = 1;
        createCharacterFrame.add(moneyTextField, gbc);
        gbc.gridy = 10;
        gbc.gridx = 0;
        createCharacterFrame.add(strengthLabel,gbc);
        gbc.gridx = 1;
        createCharacterFrame.add(strengthTextField, gbc);
        gbc.gridy = 11;
        gbc.gridx = 0;
        createCharacterFrame.add(intelligenceLabel,gbc);
        gbc.gridx = 1;
        createCharacterFrame.add(intelligenceTextField, gbc);
        gbc.gridy = 12;
        gbc.gridx = 0;
        createCharacterFrame.add(charismaLabel,gbc);
        gbc.gridx = 1;
        createCharacterFrame.add(charismaTextField, gbc);
        gbc.gridy = 13;
        gbc.gridx = 0;
        createCharacterFrame.add(dexterityLabel,gbc);
        gbc.gridx = 1;
        createCharacterFrame.add(dexterityTextField, gbc);
        gbc.gridy = 14;
        gbc.gridx = 0;
        createCharacterFrame.add(luckLabel,gbc);
        gbc.gridx = 1;
        createCharacterFrame.add(luckTextField, gbc);
        gbc.gridy = 15;
        gbc.gridx = 0;
        createCharacterFrame.add(invSlots,gbc);
        gbc.gridx = 1;
        createCharacterFrame.add(invSlotsTextField, gbc);
        gbc.gridy = 16;
        gbc.gridx = 0;
        createCharacterFrame.add(serverName,gbc);
        gbc.gridx = 1;
        createCharacterFrame.add(serverNameTextField, gbc);
        gbc.gridy = 17;
        gbc.gridx = 0;

        createCharacterFrame.add(createCharacterButton, gbc);
        createCharacterFrame.setVisible(true);
        createCharacterButton.addActionListener( e-> {
            try {
                String characterIDString = characterIDTextField.getText();
                String accUserString = accUserTextField.getText();
                String nameString = nameTextField.getText();
                String classString = classTextField.getText();
                String ageString = ageTextField.getText();
                String heightString =  heightTextField.getText();
                String weightString = weightTextField.getText();
                String raceString = raceTextField.getText();
                String levelString = levelTextField.getText();
                String moneyString = moneyTextField.getText();
                String strengthString = strengthTextField.getText();
                String intelligenceString = intelligenceTextField.getText();
                String charismaString = charismaTextField.getText();
                String dexterityString = dexterityTextField.getText();
                String luckString = luckTextField.getText();
                String invSlotsString = invSlotsTextField.getText();
                String serverNameString = serverNameTextField.getText();

                int age = ageString.isEmpty() ? 0 : Integer.parseInt(ageString);
                int height = heightString.isEmpty() ? 0 : Integer.parseInt(heightString);
                int weight = weightString.isEmpty() ? 0 : Integer.parseInt(weightString);
                int level = levelString.isEmpty() ? 0 : Integer.parseInt(levelString);
                int money = moneyString.isEmpty() ? 0 : Integer.parseInt(moneyString);
                int strength = strengthString.isEmpty() ? 0 : Integer.parseInt(strengthString);
                int intelligence = intelligenceString.isEmpty() ? 0 : Integer.parseInt(intelligenceString);
                int charisma = charismaString.isEmpty() ? 0 : Integer.parseInt(charismaString);
                int dexterity = dexterityString.isEmpty() ? 0 : Integer.parseInt(dexterityString);
                int luck = luckString.isEmpty() ? 0 : Integer.parseInt(luckString);
                int invSlotsNum = invSlotsString.isEmpty()? 0 : Integer.parseInt(invSlotsString);


                adminViewPageDBHandler.addCharacter(characterIDString, accUserString, nameString, classString, age,
                        height, weight, raceString, level, money, strength, intelligence, charisma,
                        dexterity, luck, invSlotsNum, serverNameString);

                JOptionPane.showMessageDialog(null, "Character created successfully!");
            }
            catch(Exception t){
                JOptionPane.showMessageDialog(null, "Some of the inputs are not filled in properly." +
                        "Mandatory inputs include: CharacterID, Acc User, Name, Race, Class, Server Name " +
                        "Where CharacterId must be unique for a given AccUser");
            }
        });

    }

    public void displayResults(ResultSet resultSet) {
        tablePanel.removeAll();

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable resultsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            Vector<String> columnNames = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }
            tableModel.setColumnIdentifiers(columnNames);

            Vector<Vector<Object>> data = new Vector<>();
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getObject(i));
                }
                data.add(row);
            }
            for (Vector<Object> rowData : data) {
                tableModel.addRow(rowData);
            }

            tablePanel.revalidate();
            tablePanel.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error displaying results: " + e.getMessage());
        }
    }
}
