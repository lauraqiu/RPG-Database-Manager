package Models;

import javax.swing.*;

public class CharacterModel
{
    private String name, classString, race;
    private int age, height, weight, strength, intelligence, dexterity, charisma, luck;

    public CharacterModel( String name, String classString,
                           int age, int height, int weight,
                           int strength, int intelligence,
                           int dexterity, int charisma,
                           int luck, String race){
        this.name = name;
        this.classString = classString;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.strength = strength;
        this.intelligence = intelligence;
        this.dexterity = dexterity;
        this.charisma = charisma;
        this.luck = luck;
        this.race = race;
    }
    public String getName(){
        return this.name;
    }
    public String getClassString(){
        return this.classString;
    }
    public int getAge(){
        return this.age;
    }
    public int getHeight(){
        return this.height;
    }
    public int getWeight(){
        return this.weight;
    }
    public int getStrength(){
        return this.strength;
    }
    public int getIntelligence(){
        return this.intelligence;
    }
    public int getDexterity(){
        return this.dexterity;
    }
    public int getCharisma(){
        return this.charisma;
    }
    public int getLuck(){
        return this.luck;
    }
    public String getRace(){
        return this.race;
    }
}
