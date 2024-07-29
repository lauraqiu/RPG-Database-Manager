package Models;


public class CharacterModel
{
    private String id, name, classString, race, username, server;
    private int lvl, age, height, weight, strength, intelligence, dexterity, charisma, luck, money, invslots;

    public CharacterModel(
            String id, String username, String server,
            String name, String classString, String race,
            int age, int height, int weight, int lvl,
            int strength, int intelligence, int dexterity, int charisma, int luck,
            int money, int invslots
    )
    {
        this.id = id;
        this.username = username;
        this.server = server;
        this.name = name;
        this.classString = classString;
        this.race = race;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.lvl = lvl;
        this.strength = strength;
        this.intelligence = intelligence;
        this.dexterity = dexterity;
        this.charisma = charisma;
        this.luck = luck;
        this.money = money;
        this.invslots = invslots;
    }
    public String getId(){return this.id;}
    public String getUsername(){return this.username;}
    public String getServer(){return this.server;}
    public String getName(){return this.name;}
    public String getClassString(){return this.classString;}
    public String getRace(){return this.race;}
    public int getAge(){return this.age;}
    public int getHeight(){return this.height;}
    public int getWeight(){return this.weight;}
    public int getLvl() {return lvl;}
    public int getStrength(){return this.strength;}
    public int getIntelligence(){return this.intelligence;}
    public int getDexterity(){return this.dexterity;}
    public int getCharisma(){return this.charisma;}
    public int getLuck(){return this.luck;}
    public int getMoney(){return this.money;}
    public int getInvslots() {return invslots;}
}
