import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * The SimWorld class renders Actors and UI Elements
 * 
 * @author Nathan Jiang
 * @version October 2022
 */
public class SimWorld extends World
{
    public static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;
    private Region region;
    
    private Stat fps;
    private double time, previousTime;  
    
    /**
     * Constructor for SimWorld
     * 
     */
    public SimWorld()
    {    
        //Set paint order and background
        super(SCREEN_WIDTH, SCREEN_HEIGHT, 1, false); 
        setBackground("water_plain.jpg");
        setPaintOrder(Stat.class, Particle.class, Island.class, Water.class, IslandBase.class);
        
        //Create the scrollabelWorld
        region = new Region();
        addObject(region, 0, 0);
        
        //Initialize stats for FPS
        time = 0;
        previousTime = 0;
        fps = new Stat("FPS: ", 255);
        addObject(fps, SCREEN_WIDTH + 25, 25);
    }
    /**
     * Act loop
     */
    public void act(){
        uiUpdates();
    }
    
    
    /**
     * Method to update FPS counter
     */
    public void uiUpdates(){
        previousTime = time;
        time = System.nanoTime();
        int framesPerSecond = (int)(1000000000.0/(time - previousTime));
        fps.setStat(framesPerSecond);
        
        
    }
}

