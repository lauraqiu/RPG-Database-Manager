import javax.swing.*;
import java.awt.*;

public class BackgroundFrame extends JFrame
{
    public BackgroundFrame(){
        CardLayout cardLayout = new CardLayout();
        JPanel panel = new JPanel(cardLayout);

        LoginPage loginPage = new LoginPage(this);
        //AccountCreationPage accountCreationPage = new AccountCreationPage(this);

        panel.add(loginPage, "loginPage");
        setSize(1020,800);
        add(panel);

        cardLayout.show(panel, "loginPage");



    }
}
