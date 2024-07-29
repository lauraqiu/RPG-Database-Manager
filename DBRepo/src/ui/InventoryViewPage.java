package ui;

import Models.CharacterRetrievalInfo;
import ui_logic.InventoryViewPageDBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class InventoryViewPage extends JPanel {
    String username;
    InventoryViewPageDBHandler inventoryViewPageDBHandler;
    JTable inventoryTable;
    GridBagConstraints gridBagConstraints;

    public InventoryViewPage(BackgroundFrame backgroundFrame, InventoryViewPageDBHandler inventoryViewPageDBHandler) {
        this.inventoryViewPageDBHandler = inventoryViewPageDBHandler;
        setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();

        JLabel search = new JLabel("Search:");

        JButton itemsButton = new JButton("Items");

        JTextField searchField = new JTextField(10);

        JButton searchButton = new JButton("SearchText");

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(search, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(searchField, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        add(searchButton, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        add(itemsButton, gridBagConstraints);
    }
    public void updateCharacters() {
        if (this.username != null) {
            String[] columnNames = {"Slot#", "Name", "Stack", "Equipped", "Delete"};
            //ArrayList<CharacterRetrievalInfo> queryResult = inventoryViewPageDBHandler.getUpdatedCharacterInfo(this.userName);

            DefaultTableModel dtm = new DefaultTableModel(columnNames, 0);
//
//            for (CharacterRetrievalInfo characterRetrievalInfo : queryResult) {
//                dtm.addRow(new Object[]{characterRetrievalInfo.getName(),
//                        characterRetrievalInfo.getLevel(), characterRetrievalInfo.getClassString(),
//                        characterRetrievalInfo.getServer(), characterRetrievalInfo.getId(), "delete"});
//            }

            dtm.addRow(new Object[]{"1",
                    "2", "3",
                    "4", "5", "delete" });

            this.inventoryTable = new JTable(dtm);
//            this.inventoryTable.removeColumn(this.charactersTable.getColumnModel().getColumn(4));
//            this.inventoryTable.getColumn("CharacterName").setCellRenderer(new ButtonCellRenderer());
//            this.inventoryTable.getColumn("CharacterName").setCellEditor(new AccountInfoCellEditor(dtm, this.userName, accountInfoPageDBHandler, backgroundFrame));
//            this.inventoryTable.getColumn("Delete").setCellRenderer(new ButtonCellRenderer());
//            this.inventoryTable.getColumn("Delete").setCellEditor(new ButtonCellEditor(dtm, this.userName, accountInfoPageDBHandler));
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 5;
            gridBagConstraints.anchor = GridBagConstraints.SOUTH;
            JScrollPane scrollPane = new JScrollPane(this.inventoryTable);
            add(scrollPane, gridBagConstraints);
            revalidate();
            repaint();

        }
    }

    public void setUserNameContext (String username){
        this.username = username;
        revalidate();
        repaint();
    }
}