package ui;

import ui_logic.AccountInfoPageDBHandler;

import javax.swing.*;
import java.awt.*;

public class AccountInfoPage extends JPanel {
    public String userName;
    private final JLabel userNameLabel;

    public AccountInfoPage(BackgroundFrame backgroundFrame, AccountInfoPageDBHandler accountInfoPageDBHandler) {
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        userNameLabel = new JLabel("User Name:" + this.userName);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        add(userNameLabel, gridBagConstraints);

        JButton friendsButton = new JButton("Friends");
        JButton logoutButton = new JButton("Logout");
        JButton settingsButton = new JButton("Settings");
        gridBagConstraints.gridx = 1;
        add(friendsButton, gridBagConstraints);
        gridBagConstraints.anchor = GridBagConstraints.NORTHEAST;
        gridBagConstraints.gridx = -1;
        add(logoutButton, gridBagConstraints);
        gridBagConstraints.gridx = -2;
        add(settingsButton, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        add(new JPanel(), gridBagConstraints);

        settingsButton.addActionListener(e -> {
            JFrame settingsFrame = new JFrame("Settings");
            settingsFrame.setSize(100, 100);
            settingsFrame.setLayout(new GridBagLayout());
            GridBagConstraints newGridBagConstraints = new GridBagConstraints();
            newGridBagConstraints.gridx = 0;
            newGridBagConstraints.gridy = 0;
            newGridBagConstraints.insets = new Insets(10, 10, 10, 10);
            newGridBagConstraints.anchor = GridBagConstraints.LINE_START;

            settingsFrame.add(new JLabel("Verified: " + accountInfoPageDBHandler.isVerified(this.userName)), newGridBagConstraints);


            newGridBagConstraints.gridy = 1;

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
}
