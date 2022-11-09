import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Hut here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hut extends Structure
{
    private int peopleInHut;
    private GreenfootImage hutImage = new GreenfootImage("hut.png");
    private ArrayList residence;
    private int adultCount;
    private boolean reproducable;
    private int birthChance;
    public GreenfootImage resetImage(){
        return new GreenfootImage (hutImage);
    }

    public Hut(){
        peopleInHut = 0;
        ArrayList<Person> residence = new ArrayList<Person>();
    }

    public void run(){
        for (int i = 0; i < residence.size(); i++){
            if (residence.get(i) instanceof Adult){
                adultCount++;
            }
        }
    }

    private void enterHouse(Person p){
        residence.add(p);
    }

    private void procreate(){
        if (adultCount>=2){
            birthChance = Greenfoot.getRandomNumber(10);
            if (birthChance == 3 || birthChance == 8 || birthChance ==10){
                Child c = new Child();
                residence.add(c);
            }
        }
    }
}
