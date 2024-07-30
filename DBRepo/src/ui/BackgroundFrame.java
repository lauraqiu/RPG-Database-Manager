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
    AccountInfoPage accountInfoPage;
    CharacterViewPage characterViewPage;
    InventoryViewPage inventoryViewPage;

    public BackgroundFrame(DbHandler dbHandler){
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        AccountCreationPageDBHandler accountCreationPageDBHandler = new AccountCreationPageDBHandler(dbHandler);
        AccountInfoPageDBHandler accountInfoPageDBHandler = new AccountInfoPageDBHandler(dbHandler);
        CharacterCreationDBHandler characterCreationDBHandler = new CharacterCreationDBHandler(dbHandler);
        CharacterViewPageDBHandler characterViewPageDBHandler = new CharacterViewPageDBHandler(dbHandler);
        LoginPageDBHandler loginPageDBHandler = new LoginPageDBHandler(dbHandler);

        // Create pages
        InventoryViewPageDBHandler inventoryViewPageDBHandler = new InventoryViewPageDBHandler(dbHandler);
        CharacterCreationPage characterCreationPage = new CharacterCreationPage(this, characterCreationDBHandler);
        characterViewPage = new CharacterViewPage(this, characterViewPageDBHandler);
        LoginPage loginPage = new LoginPage(this, loginPageDBHandler);
        characterViewPage = new CharacterViewPage(this, characterViewPageDBHandler);
        inventoryViewPage = new InventoryViewPage(this, inventoryViewPageDBHandler);
        accountInfoPage = new AccountInfoPage(this, characterCreationPage, accountInfoPageDBHandler);
        AccountCreationPage accountCreationPage = new AccountCreationPage(this, accountInfoPage, accountCreationPageDBHandler);
        FriendsPage friendsPage = new FriendsPage(this, accountInfoPage); // Add FriendsPage

        // Add pages to panel
        panel.add(loginPage, "loginPage");
        panel.add(accountCreationPage, "accountCreationPage");
        panel.add(accountInfoPage, "accountInfoPage");
        panel.add(characterCreationPage, "characterCreationPage");
        panel.add(characterViewPage, "characterViewPage");
        panel.add(inventoryViewPage, "inventoryViewPage");
        setSize(1020, 800);
        panel.add(friendsPage, "friendsPage"); // Add FriendsPage to layout

        // Set up frame
        setSize(1020, 800);
        add(panel);

        cardLayout.show(panel, "loginPage");
    }

    public void navigateToCreateAccountPage() {
        cardLayout.show(panel, "accountCreationPage");
    }

    public void navigateToLoginPage() {
        cardLayout.show(panel, "loginPage");
    }

    public void navigateToCharacterCreationPage() {
        cardLayout.show(panel, "characterCreationPage");
    }

    public void navigateToAccountInfoPage() {
        cardLayout.show(panel, "accountInfoPage");
    }

    public void navigateToCharacterViewPage() {
        cardLayout.show(panel, "characterViewPage");
    }

    public void navigateToInventoryViewPage() {
        cardLayout.show(panel, "inventoryViewPage");
    }

    public AccountInfoPage getAccountInfoPage() {
        return this.accountInfoPage;
    }
    public CharacterViewPage getCharacterViewPage(){
        return this.characterViewPage;
    }

    public void navigateToFriendsPage() {
        cardLayout.show(panel, "friendsPage");
    }

    public InventoryViewPage getInventoryViewPage() {
        return this.inventoryViewPage;
    }
}

