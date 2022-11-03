import greenfoot.*;

/**
 * Write a description of class Region here.
 * 
 * @author Nathan Jiang
 * @version (a version number or a date)
 */
public class Region extends ScrollableWorld 
{
    public Region(){
        super(SimWorld.SCREEN_WIDTH, SimWorld.SCREEN_HEIGHT);
        addObject(new IslandBase(), SimWorld.SCREEN_WIDTH/2, SimWorld.SCREEN_HEIGHT/2);

                
        addObject(new Island(), SimWorld.SCREEN_WIDTH/2, SimWorld.SCREEN_HEIGHT/2);

        addObject(new Water(), SimWorld.SCREEN_WIDTH/2, SimWorld.SCREEN_HEIGHT/2);
    }
}
