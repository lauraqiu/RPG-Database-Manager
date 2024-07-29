package delegates;

import Models.CharacterModel;

import java.util.List;

public interface CharacterViewPageDelegate {

    CharacterModel getCharacterInfo(String username, String id);
}
