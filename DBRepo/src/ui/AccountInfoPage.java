package ui;

import ui_logic.AccountInfoPageDBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountInfoPage extends JPanel {
    public String userName;
    public JTable charactersTable;
    private final JLabel userNameLabel;
    private final AccountInfoPageDBHandler accountInfoPageDBHandler;
    private GridBagConstraints gridBagConstraints;
    public AccountInfoPage(BackgroundFrame backgroundFrame, CharacterCreationPage characterCreationPage, AccountInfoPageDBHandler accountInfoPageDBHandler) {
        this.accountInfoPageDBHandler = accountInfoPageDBHandler;

        setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();

        JButton settingsButton = new JButton("Settings");
        JButton friendsButton = new JButton("Friends");
        JButton logoutButton = new JButton("Logout");
        JButton createCharacterButton = new JButton("Create Character");
        userNameLabel = new JLabel("User Name:" + this.userName);


        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        add(userNameLabel, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        add(createCharacterButton, gridBagConstraints);
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 1;
        add(friendsButton, gridBagConstraints);
        gridBagConstraints.anchor = GridBagConstraints.NORTHEAST;
        gridBagConstraints.gridx = -1;
        add(logoutButton, gridBagConstraints);
        gridBagConstraints.gridx = -2;
        add(settingsButton, gridBagConstraints);
        this.updateCharacters();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        add(new JPanel(), gridBagConstraints);

        createCharacterButton.addActionListener(t -> {
            characterCreationPage.setUserName(userName);
            backgroundFrame.navigateToCharacterCreationPage();
        });

        logoutButton.addActionListener(v -> {
            backgroundFrame.navigateToLoginPage();
        });

        settingsButton.addActionListener(e -> {
            JFrame settingsFrame = new JFrame("Settings");
            settingsFrame.setSize(100, 100);
            settingsFrame.setLayout(new GridBagLayout());
            GridBagConstraints newGridBagConstraints = new GridBagConstraints();
            newGridBagConstraints.gridx = 0;
            newGridBagConstraints.gridy = 0;
            newGridBagConstraints.insets = new Insets(10, 10, 10, 10);
            newGridBagConstraints.anchor = GridBagConstraints.LINE_START;
            JCheckBox statusCheckBox = new JCheckBox("isVerified", null , accountInfoPageDBHandler.isVerified(this.userName));
            settingsFrame.add(statusCheckBox, newGridBagConstraints);
            newGridBagConstraints.gridy = 1;

            statusCheckBox.addActionListener(t -> {
                accountInfoPageDBHandler.setIsVerified(statusCheckBox.isSelected(), userName);
            });

            settingsFrame.add(new JButton("Delete Account"), newGridBagConstraints);
            settingsFrame.setLocationRelativeTo(this);
            settingsFrame.setVisible(true);
            settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        });

    }

    public void setUserNameContext(String userName) {
        this.userName = userName;
    }

    public void updateContent() {
        this.userNameLabel.setText("UserName: " + this.userName);
        revalidate();
        repaint();
    }
    public void updateCharacters(){
        if (this.userName != null) {
            ResultSet resultset = accountInfoPageDBHandler.getUpdatedCharacterInfo(this.userName);
            String[] columnNames = {"CharacterName", "Level", "Class", "Server", "Delete"};
            DefaultTableModel dtm = new DefaultTableModel(columnNames,0);
            try {
                while (resultset.next()) {
                    String characterName = resultset.getString("CharacterName");
                    int level = resultset.getInt("Level");
                    String className = resultset.getString("Class");
                    String server = "Default";
                    JButton delete = new JButton("Delete");
                    dtm.addRow(new Object[]{characterName,level,className,server,delete});
                }
            }
            catch (SQLException e) {
                System.out.println("uwu");
            }
            this.charactersTable = new JTable(dtm);

            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 3;

            add(this.charactersTable, gridBagConstraints);

        }

    }
}
