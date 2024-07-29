package ui;

import oracle.net.ano.AuthenticationService;
import ui_logic.AccountInfoPageDBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ButtonCellEditor extends DefaultCellEditor {
    private final JButton button;
    private String label;
    private DefaultTableModel model;
    private String username;
    private AccountInfoPageDBHandler handler;
    public ButtonCellEditor(DefaultTableModel model, String username, AccountInfoPageDBHandler accountInfoPageDBHandler){
        super(new JCheckBox());
        this.model = model;
        this.button = new JButton();
        this.handler = accountInfoPageDBHandler;
        this.username = username;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (String) value;
        button.setText(label);
        button.addActionListener(e -> {
            this.handler.removeCharacter(model.getValueAt(row, 4).toString(), username);
            model.removeRow(row);
        });
        return button;
    }
}
