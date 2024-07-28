package ui;

import database.DbHandler;
import ui_logic.AccountCreationPageDBHandler;

import javax.swing.*;
import java.awt.*;

public class BackgroundFrame extends JFrame
{
    CardLayout cardLayout;
    JPanel panel;
//    DbHandler dbHandler;
    LoginWindow loginWindow;

    public BackgroundFrame(DbHandler dbHandler){
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        AccountCreationPageDBHandler accountCreationPageDBHandler  = new AccountCreationPageDBHandler(dbHandler);

        LoginPage loginPage = new LoginPage(this);
        AccountInfoPage accountInfoPage = new AccountInfoPage(this);
        AccountCreationPage accountCreationPage = new AccountCreationPage(this, accountInfoPage, accountCreationPageDBHandler);

        panel.add(loginPage, "loginPage");
        panel.add(accountCreationPage, "accountCreationPage");
        panel.add(accountInfoPage, "accountInfoPage");
        setSize(1020,800);
        add(panel);

        cardLayout.show(panel, "loginPage");

    }
    public void navigateToCreateAccountPage(){
        cardLayout.show(panel, "accountCreationPage");
    }
    public void navigateToLoginPage(){
        cardLayout.show(panel, "loginPage");
    }
    public void navigateToAccountInfoPage(){
        cardLayout.show(panel, "accountInfoPage");
    }



}