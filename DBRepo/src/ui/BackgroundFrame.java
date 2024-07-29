package ui;

import database.DbHandler;
import ui_logic.AccountCreationPageDBHandler;
import ui_logic.AccountInfoPageDBHandler;
import ui_logic.CharacterCreationDBHandler;
import ui_logic.CharacterViewPageDBHandler;

import javax.swing.*;
import java.awt.*;

public class BackgroundFrame extends JFrame
{
    CardLayout cardLayout;
    JPanel panel;
//    DbHandler dbHandler;
    LoginWindow loginWindow;

    AccountInfoPage accountInfoPage;
    public BackgroundFrame(DbHandler dbHandler){
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        AccountCreationPageDBHandler accountCreationPageDBHandler  = new AccountCreationPageDBHandler(dbHandler);
        AccountInfoPageDBHandler accountInfoPageDBHandler = new AccountInfoPageDBHandler(dbHandler);
        CharacterCreationDBHandler characterCreationDBHandler = new CharacterCreationDBHandler(dbHandler);
        CharacterViewPageDBHandler characterViewPageDBHandler = new CharacterViewPageDBHandler(dbHandler);
        CharacterCreationPage characterCreationPage = new CharacterCreationPage(this, characterCreationDBHandler);
        CharacterViewPage characterViewPage = new CharacterViewPage(this, characterViewPageDBHandler);
        InventoryViewPage inventoryViewPage  = new InventoryViewPage();
        LoginPage loginPage = new LoginPage(this);
        accountInfoPage = new AccountInfoPage(this, characterCreationPage, accountInfoPageDBHandler);
        AccountCreationPage accountCreationPage = new AccountCreationPage(this, accountInfoPage, accountCreationPageDBHandler);
        panel.add(loginPage, "loginPage");
        panel.add(accountCreationPage, "accountCreationPage");
        panel.add(accountInfoPage, "accountInfoPage");
        panel.add(characterCreationPage, "characterCreationPage");
        panel.add(characterViewPage, "characterViewPage");
        panel.add(inventoryViewPage, "inventoryViewPage");
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
    public void navigateToCharacterViewPage(){
        cardLayout.show(panel, "characterViewPage");
    }
    public void navigateToInventoryViewPage(){
        cardLayout.show(panel, "inventoryViewPage");
    }
    public AccountInfoPage getAccountInfoPage(){
        return this.accountInfoPage;
    }


}