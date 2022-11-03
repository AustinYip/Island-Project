import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hut here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hut extends Structure
{
    private int peopleInHut;
    private boolean hutFull;
    private GreenfootImage hutImage = new GreenfootImage("hut.png");

    public GreenfootImage resetImage(){
        return new GreenfootImage (hutImage);
    }

    public Hut(){
        peopleInHut = 0;
        hutFull = false;
    }

    public void run(){
        if(peopleInHut >= 2){
            hutFull = true;
        }
    }
    
    private void addPeople(){
        if (hutFull == false){
            peopleInHut++;
            if (peopleInHut == 2){
                hutFull = true;
            }
        }
    }
    
    private void procreate(){
        
}
