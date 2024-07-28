package delegates;

public interface AccountInfoPageDelegate {

    boolean isVerified(String username);

    void setIsVerified(Boolean bool, String username);

    Object[][] getUpdatedCharacterInfo(String username);
}
