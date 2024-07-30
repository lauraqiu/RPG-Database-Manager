//package ui;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//
//public class FriendsPage extends JPanel {
//    private final JLabel userNameLabel;
//    private final JTable friendsTable;
//    private final GridBagConstraints gridBagConstraints;
//    private final BackgroundFrame backgroundFrame;
//    private final AccountInfoPage accountInfoPage;
//
//    public FriendsPage(BackgroundFrame backgroundFrame, AccountInfoPage accountInfoPage) {
//        this.backgroundFrame = backgroundFrame;
//        this.accountInfoPage = accountInfoPage;
//        setLayout(new GridBagLayout());
//        gridBagConstraints = new GridBagConstraints();
//
//        userNameLabel = new JLabel(accountInfoPage.userName);
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 0;
//        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
//        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
//        add(userNameLabel, gridBagConstraints);
//
//        JButton homeButton = new JButton("Home");
//        gridBagConstraints.gridx = 1;
//        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
//        add(homeButton, gridBagConstraints);
//
//        // Add buttons
//        JButton settingsButton = new JButton("Settings");
//        JButton logoutButton = new JButton("Logout");
//        JButton addFriendButton = new JButton("Add Friend");
//
//        // Title label
//        JLabel titleLabel = new JLabel("Friends List");
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 1;
//        gridBagConstraints.gridwidth = 2;
//        add(titleLabel, gridBagConstraints);
//
//        // Create table model
//        String[] columnNames = {"Friend Name", "Level", "Class", "Server", "Date Added", "Remove"};
//        DefaultTableModel dtm = new DefaultTableModel(columnNames, 0);
//
//        // Sample data
//        dtm.addRow(new Object[]{"Friend1", "10", "Warrior", "Server1", "2024-07-28", "Remove"});
//        dtm.addRow(new Object[]{"Friend2", "20", "Mage", "Server2", "2024-07-27", "Remove"});
//
//        // Create table
//        friendsTable = new JTable(dtm);
//        friendsTable.getColumn("Remove").setCellRenderer(new ButtonCellRenderer());
//        friendsTable.getColumn("Remove").setCellEditor(new ButtonCellEditor(dtm, "", null));  // Adjust this as needed
//
//        // Add table to scroll pane
//        JScrollPane scrollPane = new JScrollPane(friendsTable);
//        gridBagConstraints.gridy = 2;
//        gridBagConstraints.weightx = 1.0;
//        gridBagConstraints.weighty = 1.0;
//        gridBagConstraints.fill = GridBagConstraints.BOTH;
//        gridBagConstraints.gridwidth = 2;
//        add(scrollPane, gridBagConstraints);
//
//        // Button panel for right alignment
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new GridBagLayout());
//        GridBagConstraints buttonConstraints = new GridBagConstraints();
//        buttonConstraints.gridx = 0;
//        buttonConstraints.gridy = 0;
//        buttonConstraints.insets = new Insets(10, 10, 10, 10);
//
//        buttonPanel.add(addFriendButton, buttonConstraints);
//        buttonConstraints.gridx++;
//        buttonPanel.add(settingsButton, buttonConstraints);
//        buttonConstraints.gridx++;
//        buttonPanel.add(logoutButton, buttonConstraints);
//
//        gridBagConstraints.gridx = 2;
//        gridBagConstraints.gridy = 3;
//        gridBagConstraints.weightx = 0;
//        gridBagConstraints.weighty = 0;
//        gridBagConstraints.fill = GridBagConstraints.NONE;
//        add(buttonPanel, gridBagConstraints);
//
//        // Button actions
//        homeButton.addActionListener(e -> backgroundFrame.navigateToAccountInfoPage());
//
//        settingsButton.addActionListener(e -> {
//            JFrame settingsFrame = new JFrame("Settings");
//            settingsFrame.setSize(100, 100);
//            settingsFrame.setLayout(new GridBagLayout());
//            GridBagConstraints newGridBagConstraints = new GridBagConstraints();
//            newGridBagConstraints.gridx = 0;
//            newGridBagConstraints.gridy = 0;
//            newGridBagConstraints.insets = new Insets(10, 10, 10, 10);
//            newGridBagConstraints.anchor = GridBagConstraints.LINE_START;
//            JCheckBox statusCheckBox = new JCheckBox("isVerified", null, accountInfoPage.getAccountInfoPageDBHandler().isVerified(accountInfoPage.userName));
//            settingsFrame.add(statusCheckBox, newGridBagConstraints);
//            newGridBagConstraints.gridy = 1;
//
//            statusCheckBox.addActionListener(t -> {
//                accountInfoPage.getAccountInfoPageDBHandler().setIsVerified(statusCheckBox.isSelected(), accountInfoPage.userName);
//            });
//
//            settingsFrame.add(new JButton("Delete Account"), newGridBagConstraints);
//            settingsFrame.setLocationRelativeTo(this);
//            settingsFrame.setVisible(true);
//            settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        });
//
//        logoutButton.addActionListener(e -> backgroundFrame.navigateToLoginPage());
//    }
//}
