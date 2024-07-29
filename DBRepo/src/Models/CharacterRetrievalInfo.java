package Models;

public class CharacterRetrievalInfo {
    private final String name;
    private final int level;
    private final String classString;
    private final String server;
    private final String id;

    public CharacterRetrievalInfo(String name, int level, String classString, String server, String id) {
        this.name = name;
        this.level = level;
        this.classString = classString;
        this.server = server;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getLevel(){
        return level;
    }

    public String getClassString(){
        return classString;
    }
    public String getServer(){
        return server;
    }
    public String getId(){
        return id;
    }


}
