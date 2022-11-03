import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Stat class is used to show variables (such as fps) on the screen
 */
public class Stat extends UI
{
    //Constants
    private static final Font calibri = new Font("Calibri", false, true, 18);
    private static final Color color = new Color(0, 0, 0);
    private static final int TRANSPARENCY_CHANGE = 10,
    WIDTH = 200, HEIGHT = 25;
    
    //Instance variables
    private int transparency, stat;
    private String statName;
    //Basic constructor
    public Stat(String statName){
        transparency = 0;
        stat = 0;
        this.statName = statName;
    }
    //Constructor with transparency parameter
    public Stat(String statName, int transparency){
        this.transparency = transparency;
        stat = 0;
        this.statName = statName;
    }
    
    //Act method that updates the stat
    public void act()
    {
        setImage(updateStat());
    }
    
    //Method to redraw the stat with its current value
    public GreenfootImage updateStat(){
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);
        image.setColor(color);
        image.setFont(calibri);
        image.drawString(statName + stat, 0, HEIGHT);
        image.setTransparency(transparency);
        return image;
    }    
    
    //Methods to increase text opacity and reduce it
    public void fadeIn(){
        if(transparency < 255 - TRANSPARENCY_CHANGE){
            transparency += TRANSPARENCY_CHANGE;
        }
    }
    public void fadeOut(){
        if(transparency > TRANSPARENCY_CHANGE){
            transparency -= TRANSPARENCY_CHANGE;
        }
    }

    //Getters and setters for the stat
    public int getStat(){
        return stat;
    }
    public void resetStat(){
        stat = 0;
    }
    public void increaseStat(int increase){
        stat += increase;
    }
    public void setStat(int newScore){
        stat = newScore;
    }
}
