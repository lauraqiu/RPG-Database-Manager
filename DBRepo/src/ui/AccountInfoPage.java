package ui;

import Models.CharacterRetrievalInfo;
import ui_logic.AccountInfoPageDBHandler;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class AccountInfoPage extends JPanel {
    public String userName;
    public JTable charactersTable;
    private final JLabel userNameLabel;
    private final AccountInfoPageDBHandler accountInfoPageDBHandler;
    private GridBagConstraints gridBagConstraints;
    private BackgroundFrame backgroundFrame;
    private CharacterCreationPage characterCreationPage;

    public AccountInfoPage(BackgroundFrame backgroundFrame, CharacterCreationPage characterCreationPage, AccountInfoPageDBHandler accountInfoPageDBHandler) {
        this.accountInfoPageDBHandler = accountInfoPageDBHandler;
        this.backgroundFrame = backgroundFrame;
        this.characterCreationPage = characterCreationPage;

        userNameLabel = rebuildPage(this.backgroundFrame, this.characterCreationPage, this.accountInfoPageDBHandler);

    }

    private JLabel rebuildPage(BackgroundFrame backgroundFrame, CharacterCreationPage characterCreationPage, AccountInfoPageDBHandler accountInfoPageDBHandler) {
        final JLabel userNameLabel;
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
        return userNameLabel;
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
            String[] columnNames = {"CharacterName", "Level", "Class", "Server", "Date Created", "Delete" };
            ArrayList<CharacterRetrievalInfo> queryResult = accountInfoPageDBHandler.getUpdatedCharacterInfo(this.userName);

            DefaultTableModel dtm = new DefaultTableModel(columnNames,0);
            for ( CharacterRetrievalInfo characterRetrievalInfo : queryResult) {
                dtm.addRow(new Object[]{characterRetrievalInfo.getName(),
                        characterRetrievalInfo.getLevel(), characterRetrievalInfo.getClassString(),
                        characterRetrievalInfo.getServer(), characterRetrievalInfo.getId(), "delete" });
            }

            this.charactersTable = new JTable(dtm);
            this.charactersTable.removeColumn(this.charactersTable.getColumnModel().getColumn(4));
            this.charactersTable.getColumn("CharacterName").setCellRenderer(new ButtonCellRenderer());
            this.charactersTable.getColumn("CharacterName").setCellEditor(new AccountInfoCellEditor(dtm, this.userName, accountInfoPageDBHandler,backgroundFrame));
            this.charactersTable.getColumn("Delete").setCellRenderer(new ButtonCellRenderer());
            this.charactersTable.getColumn("Delete").setCellEditor(new ButtonCellEditor(dtm, this.userName, accountInfoPageDBHandler));
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 3;
            JScrollPane scrollPane = new JScrollPane(this.charactersTable);
            add(scrollPane, gridBagConstraints);
            revalidate();
            repaint();

        }
    }
    public void updateCharacterPage(){
        this.removeAll();
        rebuildPage(this.backgroundFrame, this.characterCreationPage, this.accountInfoPageDBHandler);
    }

}
