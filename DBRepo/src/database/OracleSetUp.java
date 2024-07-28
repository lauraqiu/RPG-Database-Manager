package database;

import delegates.LoginWindowDelegate;
import ui.BackgroundFrame;
import ui.LoginWindow;

public class OracleSetUp implements LoginWindowDelegate {
    DbHandler dbHandler;
    LoginWindow loginWindow;
    public OracleSetUp(){
        dbHandler = new DbHandler();
    }
    @Override
    public void login(String username, String password) {
        boolean didConnect = dbHandler.login(username, password);

        if (didConnect) {
            // Once connected, remove login window and start text transaction flow
            loginWindow.dispose();
            BackgroundFrame backgroundFrame = new BackgroundFrame(dbHandler);
            backgroundFrame.setVisible(true);

        } else {
            loginWindow.handleLoginFailed();

            if (loginWindow.hasReachedMaxLoginAttempts()) {
                loginWindow.dispose();
                System.out.println("You have exceeded your number of allowed attempts");
                System.exit(-1);
            }
        }
    }

    public void start(){
        this.loginWindow = new LoginWindow();
        loginWindow.showFrame(this);

    }
}
