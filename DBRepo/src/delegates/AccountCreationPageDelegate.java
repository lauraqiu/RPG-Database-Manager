package delegates;

public interface AccountCreationPageDelegate {
    void addAccount(String username, String password, String email);
    boolean checkUniqueUsername(String username);
    boolean checkUniqueEmail(String email);
}
