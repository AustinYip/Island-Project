import java.util.ArrayList;
import java.util.List;
import greenfoot.*; 

/**
 * A Scrollable version of the World class
 */
public class ScrollableWorld extends Actor
{
    private static final double ZOOM_CHANGE = 0.1, ZOOM_CHANGE_DIVISOR = 5, MIN_ZOOM_CHANGE = 0.001;
    
    private int width, height;
    private double x, y, zoom, idealZoom, zoomChange, zoomChangeDivisor, startX, startY;
    private ArrayList<ScrollableActor> scrollableActorList, addQueue, removeQueue;
    private boolean smoothZoom, upPressed, downPressed;
    
    public ScrollableWorld(int width, int height, double zoomChange,  boolean smoothZoom, double zoomChangeDivisor){

        zoom = 1;
        idealZoom = zoom;
        this.zoomChange = zoomChange;
        this.zoomChangeDivisor = zoomChangeDivisor;
        this.smoothZoom = smoothZoom;
        
        upPressed = false;
        downPressed = false;
        
        x = width/2;
        y = height/2;
        this.width = width;
        this.height = height;
        
        scrollableActorList = new ArrayList<ScrollableActor>();
        addQueue = new ArrayList<ScrollableActor>();
        removeQueue = new ArrayList<ScrollableActor>();

        setImage(new GreenfootImage(1, 1));
    }
    public ScrollableWorld(int width, int height){
        this(width, height, ZOOM_CHANGE, true, ZOOM_CHANGE_DIVISOR);
    }
    
    public void act(){
        runActors();
        handleMovement();
        handleZoom();
        run();
        updateQueue();
    }
    public void run(){
        
    }
    public void runActors(){
        for(ScrollableActor s : scrollableActorList){
            s.run();
            s.render();
        }
    }
    public void handleMovement(){
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if(Greenfoot.mousePressed(null)){
            startX = mouseInfo.getX();
            startY = mouseInfo.getY();
        }
        if(Greenfoot.mouseDragged(null)){
            //Divide by zoom to move based on POV size
            x += (startX - mouseInfo.getX())/zoom;
            y += (startY - mouseInfo.getY())/zoom;
            startX = mouseInfo.getX();
            startY = mouseInfo.getY();
        }
    }
    public void handleZoom(){
        if(Greenfoot.isKeyDown("up")){
            if(!upPressed && idealZoom + ZOOM_CHANGE <= 1){
                idealZoom += ZOOM_CHANGE;
            
            }
            upPressed = true;
        }else{
            upPressed = false;
        }
        if(Greenfoot.isKeyDown("down")){
            if(!downPressed && idealZoom - ZOOM_CHANGE >= 0){
                idealZoom -= ZOOM_CHANGE;
                
            }
            downPressed = true;
        }else{
            downPressed = false;
        }
        
        if(smoothZoom && Math.abs(idealZoom - zoom) > MIN_ZOOM_CHANGE){
            zoom += (idealZoom - zoom)/zoomChangeDivisor;
            
        }else{
            zoom = idealZoom;
        }
        
    }
    
    public double getCameraX(){
        return x;
    }
    public double getCameraY(){
        return y;
    }
    public double getZoom(){
        return zoom;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    
    public void addObject(ScrollableActor s, double x, double y){
        addQueue.add(s);
        s.setScrollableWorld(this);
        s.setLocation(x, y);
    }
    public void removeObject(ScrollableActor s){
        removeQueue.add(s);
       
    }
    public void updateQueue(){
        for(int i = 0; i < addQueue.size(); i++){
            ScrollableActor s = addQueue.get(i);
            scrollableActorList.add(s);
            addQueue.remove(i);
            i--;
        }
        for(int i = 0; i < removeQueue.size(); i++){
            ScrollableActor s = removeQueue.get(i);
            
            scrollableActorList.remove(s);
            s.setScrollableWorld(null);
            if(s.getWorld() != null){
                s.getWorld().removeObject(s);
            }

            removeQueue.remove(i);
            i--;
        }
    }
    
  
}
