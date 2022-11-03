import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Sound class stores information about a sound and multiple copies to play.
 * 
 * @author Nathan Jiang
 * @version October 2022
 */

public class Sound  
{
    //Constants
    private static final int COPIES_PER_SOUND = 32;
    
    //Each Sound contains an iterator and copies of the sound
    private int iterator;
    private GreenfootSound[] soundCopies;
    
    /**
     * Constructor for a sound
     * 
     * @param name  The name of the file
     */
    public Sound(String name){
        //Based on the name, multiple copies are made
        iterator = 0;
        
        soundCopies = new GreenfootSound[COPIES_PER_SOUND];
        GreenfootSound sound;
        for(int i = 0; i < COPIES_PER_SOUND; i++){
            sound = new GreenfootSound(name);
            soundCopies[i] = sound;
        }
        
    }
    
    /**
     * Method to play a sound based on the iterator (this way multiple sounds can play at the same time)
     * 
     * @param volume    The volume of the sound
     */
    public void playSound(int volume){
        soundCopies[iterator].setVolume(volume);
        soundCopies[iterator].play();
        iterator++;
        if(iterator >= COPIES_PER_SOUND){
            iterator = 0;
        }
    }
        
}
