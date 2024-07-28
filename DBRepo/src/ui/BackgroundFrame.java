package ui;

import database.DbHandler;
import ui_logic.AccountCreationPageDBHandler;
import ui_logic.AccountInfoPageDBHandler;

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
        AccountInfoPageDBHandler accountInfoPageDBHandler = new AccountInfoPageDBHandler(dbHandler);
        LoginPage loginPage = new LoginPage(this);
        AccountInfoPage accountInfoPage = new AccountInfoPage(this, accountInfoPageDBHandler);
        AccountCreationPage accountCreationPage = new AccountCreationPage(this, accountInfoPage, accountCreationPageDBHandler);
        CharacterCreationPage characterCreationPage = new CharacterCreationPage();
        panel.add(loginPage, "loginPage");
        panel.add(accountCreationPage, "accountCreationPage");
        panel.add(accountInfoPage, "accountInfoPage");
        panel.add(characterCreationPage, "characterCreationPage");
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
    public void navigateToCharacterCreationPage(){
        cardLayout.show(panel, "characterCreationPage");
    }
    public void navigateToAccountInfoPage(){
        cardLayout.show(panel, "accountInfoPage");
    }



}