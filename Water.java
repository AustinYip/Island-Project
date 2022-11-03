import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Water here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Water extends Graphic
{
    public Water(){
        setImage(resetImage());
    }
    public GreenfootImage resetImage(){
        GreenfootImage image = new GreenfootImage("water_plain.jpg");
        image.setTransparency(200);
        return image;
    }
}
