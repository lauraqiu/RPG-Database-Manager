import javax.swing.*;
import java.awt.*;

public class BackgroundFrame extends JFrame
{
    CardLayout cardLayout;
    JPanel panel;
    public BackgroundFrame(){
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        LoginPage loginPage = new LoginPage(this);
        AccountCreationPage accountCreationPage = new AccountCreationPage(this);

        panel.add(loginPage, "loginPage");
        panel.add(accountCreationPage, "accountCreationPage");
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
}