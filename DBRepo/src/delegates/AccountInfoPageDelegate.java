package delegates;

import java.util.List;

public interface AccountInfoPageDelegate {

    boolean isVerified(String username);

    void setIsVerified(Boolean bool, String username);

    List getUpdatedCharacterInfo(String username);
}
