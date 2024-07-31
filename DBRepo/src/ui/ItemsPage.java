package ui;

import ui_logic.AdminViewPageDBHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ItemsPage {

    AdminViewPageDBHandler adminViewPageDBHandler;

    public ItemsPage(AdminViewPageDBHandler adminViewPageDBHandler){

        this.adminViewPageDBHandler = adminViewPageDBHandler;

        JFrame itemsPageFrame = new JFrame();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        itemsPageFrame.setLayout(new GridBagLayout());

        JButton itemsButton = new JButton("Items");
        JButton equipmentButton = new JButton("Equipment");
        JButton consumablesButton = new JButton("Consumables");
        JButton resourcesButton = new JButton("Resources");

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        itemsPageFrame.add(itemsButton);
        gridBagConstraints.gridy = 1;
        itemsPageFrame.add(equipmentButton);
        gridBagConstraints.gridy = 2;
        itemsPageFrame.add(consumablesButton);
        gridBagConstraints.gridy = 3;
        itemsPageFrame.add(resourcesButton);

        itemsButton.addActionListener((t) ->{
            adminViewPageDBHandler.getItems();
        });
//        equipmentButton.addActionListener((t) ->{
//            adminViewPageDBHandler.getEquipments();
//        });
//        consumablesButton.addActionListener((t) ->{
//            adminViewPageDBHandler.getConsumables();
//        });
//        resourcesButton.addActionListener((t) ->{
//            adminViewPageDBHandler.getResources();
//        });
    }
}
