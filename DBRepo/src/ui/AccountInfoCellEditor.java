package ui;

import ui_logic.AccountInfoPageDBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AccountInfoCellEditor extends DefaultCellEditor {

    private final JButton button;
    private String label;
    private DefaultTableModel model;
    private String username;
    private AccountInfoPageDBHandler handler;
    private BackgroundFrame backgroundFrame;
    public AccountInfoCellEditor(DefaultTableModel model, String username, AccountInfoPageDBHandler accountInfoPageDBHandler, BackgroundFrame backgroundFrame){
        super(new JCheckBox());
        this.model = model;
        this.button = new JButton();
        this.handler = accountInfoPageDBHandler;
        this.username = username;
        this.backgroundFrame = backgroundFrame;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (String) value;
        button.setText(label);
        button.addActionListener(e -> {
            this.backgroundFrame.getCharacterViewPage().setCharacterID(model.getValueAt(row, 4).toString());
            this.backgroundFrame.getCharacterViewPage().setUserName(username);
            this.backgroundFrame.getCharacterViewPage().buildPage();
            this.backgroundFrame.navigateToCharacterViewPage();
        });
        return button;
    }

}
