import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;
import java.util.ArrayList;

/**
 * The Particle class is used to create several effects for Vehicles and Pedestrians (smoke, blood)
 */
public class Particle extends Effect
{
    //Constants
    private static double DECELERATION_RATE = 0.1;
    private static int ROTATION_RANGE = 180,
    SPEED_RANGE = 2,
    PARTICLE_WIDTH = 7,
    PARTICLE_HEIGHT = 7;
    
    //Instance variables
    private int particleGreenValue,
    particleOpacity,
    opacityChange,
    width,
    height;
    private double speed;
    private Color color;
    
    //Constructor
    public Particle(boolean dynamic, int width, int height, double speed, int rotation, Color color, int opacityChange){
        //Set dimensions and speed
        this.width = width;
        this.height = height;
        this.speed = speed;
        
        setRotation(rotation);
        if (dynamic){
            speed += Calc.randomizeInt(-SPEED_RANGE, SPEED_RANGE);
            setRotation(rotation + Calc.randomizeInt(-ROTATION_RANGE, ROTATION_RANGE));
        }
                
        particleOpacity = 255; //Opacity will also reduce
        
        //The amount of change each frame
        this.color = color;
        this.opacityChange = opacityChange;
        setImage(getParticleImage());
    }
    public Particle(Color color, int opacityChange){
        this(false, PARTICLE_WIDTH, PARTICLE_HEIGHT, 0, 0, color, opacityChange);
    }
    

    public GreenfootImage resetImage(){
        return getParticleImage();
    }
    
    //Run method, where the particle moves and is drawn on screen
    public void run()
    {
        //Move, decelerate and update image
        move(speed);
        decelerate(DECELERATION_RATE);
        setImage(getParticleImage());
        optimizedUnload();
    }
    
    public void decelerate(double decelerationRate){
        if(speed > 0){
            speed -= decelerationRate;
        }
        if(speed < 0){
            speed = 0;
        }
    }
    
    //Method to change the particle image
    private GreenfootImage getParticleImage(){
        //Create base
        GreenfootImage image = new GreenfootImage(width, height);
        
        image.setColor(color);
        image.fillOval(0, 0, width, height);
        
        //Set transparency
        image.setTransparency(particleOpacity);
        
        //Change variables
        if(particleOpacity >= opacityChange){
            particleOpacity -= opacityChange;
        }
        return image;
    }
    
    public boolean optimizedUnload(){
        //If the opacity change is complete, remove it
        if(particleOpacity < opacityChange){
            getScrollableWorld().removeObject(this);
            return true;
        }else if(!load()){
            getScrollableWorld().removeObject(this);
            return true;
        }
        return false;
    }
    
}