//package ui;
//
//import ui_logic.InventoryViewPageDBHandler;
//import ui_logic.ItemViewDBHandler;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//
//public class ItemsView extends JFrame {
//
//    String username;
//    ItemViewDBHandler itemViewDBHandler;
//    JTable inventoryTable;
//    GridBagConstraints gridBagConstraints;
//
//    public ItemsView(BackgroundFrame backgroundFrame, ItemViewDBHandler itemViewDBHandler) {
//        this.itemViewDBHandler = itemViewDBHandler;
//
//        setLayout(new GridBagLayout());
//        gridBagConstraints = new GridBagConstraints();
//
//        JLabel search = new JLabel("Search:");
//        JTextField searchField = new JTextField(10);
//        JButton searchButton = new JButton("SearchText");
//
//
//        JButton addToCharacterButton = new JButton("Add To Character");
//
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 0;
//        add(search, gridBagConstraints);
//
//        gridBagConstraints.gridx = 1;
//        add(searchField, gridBagConstraints);
//        gridBagConstraints.gridx = 2;
//        add(searchButton, gridBagConstraints);
//
//        gridBagConstraints.gridx = 3;
//        add(itemsButton, gridBagConstraints);
//    }
//    public void updateCharacters() {
//        if (this.username != null) {
//            String[] columnNames = {"Slot#", "Name", "Stack", "Equipped", "Delete"};
//            //ArrayList<CharacterRetrievalInfo> queryResult = inventoryViewPageDBHandler.getUpdatedCharacterInfo(this.userName);
//
//            DefaultTableModel dtm = new DefaultTableModel(columnNames, 0);
////
////            for (CharacterRetrievalInfo characterRetrievalInfo : queryResult) {
////                dtm.addRow(new Object[]{characterRetrievalInfo.getName(),
////                        characterRetrievalInfo.getLevel(), characterRetrievalInfo.getClassString(),
////                        characterRetrievalInfo.getServer(), characterRetrievalInfo.getId(), "delete"});
////            }
//
//            dtm.addRow(new Object[]{"1",
//                    "2", "3",
//                    "4", "5", "delete" });
//
//            this.inventoryTable = new JTable(dtm);
////            this.inventoryTable.removeColumn(this.charactersTable.getColumnModel().getColumn(4));
////            this.inventoryTable.getColumn("CharacterName").setCellRenderer(new ButtonCellRenderer());
////            this.inventoryTable.getColumn("CharacterName").setCellEditor(new AccountInfoCellEditor(dtm, this.userName, accountInfoPageDBHandler, backgroundFrame));
////            this.inventoryTable.getColumn("Delete").setCellRenderer(new ButtonCellRenderer());
////            this.inventoryTable.getColumn("Delete").setCellEditor(new ButtonCellEditor(dtm, this.userName, accountInfoPageDBHandler));
//            gridBagConstraints.gridx = 0;
//            gridBagConstraints.gridy = 5;
//            gridBagConstraints.anchor = GridBagConstraints.SOUTH;
//            JScrollPane scrollPane = new JScrollPane(this.inventoryTable);
//            add(scrollPane, gridBagConstraints);
//            revalidate();
//            repaint();
//
//        }
//    }
//
//    public void setUserNameContext (String username){
//        this.username = username;
//        revalidate();
//        repaint();
//    }
//
//}
