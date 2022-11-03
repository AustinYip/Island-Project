import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Camfire here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Campfire extends Structure
{
    private boolean lit;
    private int saneHeal;
    private GreenfootImage campfireImage = new GreenfootImage("campfire.png");
    public GreenfootImage resetImage(){
        return new GreenfootImage (campfireImage);
    }
    
    /**
     * Act - do whatever the Camfire wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
