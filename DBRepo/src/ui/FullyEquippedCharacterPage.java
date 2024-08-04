package ui;

import ui_logic.AdminViewPageDBHandler;

import javax.swing.*;
import java.awt.*;

public class FullyEquippedCharacterPage extends JFrame {
    AdminViewPageDBHandler adminViewPageDBHandler;

    public FullyEquippedCharacterPage(AdminViewPageDBHandler adminViewPageDBHandler) {
        this.adminViewPageDBHandler = adminViewPageDBHandler;
        JLabel jlabel = new JLabel("Click search button to see all fully equipped characters");

        JButton searchByCharacterButton = new JButton("Search");

        searchByCharacterButton.addActionListener(e -> {
            adminViewPageDBHandler.getAllFullyEquippedCharacters();

        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(jlabel, gbc);
        gbc.gridx = 1;
        add(searchByCharacterButton, gbc);

        setSize(800,600);
        setVisible(true);



    }


}
