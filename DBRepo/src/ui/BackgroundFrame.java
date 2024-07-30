package ui;

import database.DbHandler;
import ui_logic.AccountCreationPageDBHandler;
import ui_logic.AccountInfoPageDBHandler;
import ui_logic.CharacterCreationDBHandler;
import ui_logic.CharacterViewPageDBHandler;
import ui_logic.LoginPageDBHandler;
import ui_logic.*;

import javax.swing.*;
import java.awt.*;

public class BackgroundFrame extends JFrame {
    CardLayout cardLayout;
    JPanel panel;
    AdminViewPage adminViewPage;

    public BackgroundFrame(DbHandler dbHandler){
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        AdminViewPageDBHandler adminViewPageDBHandler = new AdminViewPageDBHandler(dbHandler);
        adminViewPage = new AdminViewPage(this,adminViewPageDBHandler );
        setSize(1020, 800);
        panel.add(adminViewPage, "adminViewPage");

        add(panel);
        cardLayout.show(panel, "adminViewPage");
    }

    public void navigateToCreateAccountPage() {
        cardLayout.show(panel, "accountCreationPage");
    }

    public void navigateToAccountInfoPage() {
        cardLayout.show(panel, "accountInfoPage");
    }


}

