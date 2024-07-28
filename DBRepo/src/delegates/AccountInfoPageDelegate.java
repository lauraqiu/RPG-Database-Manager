package delegates;

import java.sql.ResultSet;

public interface AccountInfoPageDelegate {

    boolean isVerified(String username);

    void setIsVerified(Boolean bool, String username);

    ResultSet getUpdatedCharacterInfo(String username);
}
