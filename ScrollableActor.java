import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public abstract class ScrollableActor extends Actor
{
    private double x, y, rotation, previousZoom;
    private ScrollableWorld scrollableWorld;
    private GreenfootImage previousImage;
    
    
    public abstract GreenfootImage resetImage();
    
    public void run(){
        
    }
    
    public void render(){
        load();
        position();
        resize();
        unload();
    }
    public void position(){
        if(getWorld() != null){
            int screenXPos = (int) ((x - scrollableWorld.getCameraX())*scrollableWorld.getZoom() + scrollableWorld.getX() + scrollableWorld.getWidth()/2);
            int screenYPos = (int) ((y - scrollableWorld.getCameraY())*scrollableWorld.getZoom() + scrollableWorld.getY()+ scrollableWorld.getHeight()/2);
            super.setLocation(screenXPos, screenYPos);
        }
    }
    public void resize(){
        boolean zoomChange = previousZoom != scrollableWorld.getZoom();
        boolean imageUpdate = previousImage != getImage();
        if((zoomChange || imageUpdate) && resetImage() != null){
            setImage(resetImage());
            int width = (int)Math.max((getImage().getWidth()*scrollableWorld.getZoom()), 1);
            int height = (int)Math.max((getImage().getHeight()*scrollableWorld.getZoom()), 1);
            getImage().scale(width, height);

        }
        previousZoom = scrollableWorld.getZoom();
        previousImage = getImage();

    }
    
    public boolean load(){
        if(getWorld() == null){
            int screenXPos = (int) ((x - scrollableWorld.getCameraX())*scrollableWorld.getZoom() + scrollableWorld.getX() + scrollableWorld.getWidth()/2);
            int screenYPos = (int) ((y - scrollableWorld.getCameraY())*scrollableWorld.getZoom() + scrollableWorld.getY()+ scrollableWorld.getHeight()/2);
            
            boolean onScreenXLeft = screenXPos + getImage().getWidth()/2 > 0;
            boolean onScreenXRight = screenXPos - getImage().getWidth()/2 < scrollableWorld.getWidth();
            if(onScreenXLeft && onScreenXRight){
                boolean onScreenYDown = screenYPos + getImage().getHeight()/2 > 0;
                boolean onScreenYTop = screenYPos - getImage().getHeight()/2 < scrollableWorld.getHeight();
                if(onScreenYDown && onScreenYTop){
                    scrollableWorld.getWorld().addObject(this, screenXPos, screenYPos);
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    public boolean unload(){
        if(getWorld() != null){
            boolean onScreenXLeft = getX() + getImage().getWidth()/2 > scrollableWorld.getX();
            boolean onScreenXRight = getX() - getImage().getWidth()/2 < scrollableWorld.getX() + scrollableWorld.getWidth();
            if(!(onScreenXLeft && onScreenXRight)){
                getWorld().removeObject(this);
                return true;
            }
            boolean onScreenYDown = getY() + getImage().getHeight()/2 > scrollableWorld.getY() ;
            boolean onScreenYTop = getY() - getImage().getHeight()/2 < scrollableWorld.getY() + scrollableWorld.getHeight();
            if(!(onScreenYDown && onScreenYTop)){
                getWorld().removeObject(this);
                return true;
            }
        }
        return false;
    }
    
    public double getExactX(){
        return x;
    }
    public double getExactY(){
        return y;
    }
    public double getExactRotation(){
        return rotation;
    }
    public void setLocation(double x, double y){
        this.x = x;
        this.y = y;
    }
    public ScrollableWorld getScrollableWorld(){
        return scrollableWorld;
    }
    @Override()
    public void setRotation(int rotation){
        setRotation(rotation, false);
    }
    public void setRotation(double rotation){
        setRotation(rotation, true);
    }
    public void setRotation(double rotation, boolean showTurn){
        this.rotation = rotation;
        if(showTurn)
            super.setRotation((int)(rotation+0.5));
    }
    
    public void move(){
        x += Math.cos(Math.toRadians(rotation));
        y += Math.sin(Math.toRadians(rotation));
    }
    public void move(double speed){
        x += speed * Math.cos(Math.toRadians(rotation));
        y += speed * Math.sin(Math.toRadians(rotation));
    }
    
    
    public void setScrollableWorld(ScrollableWorld scrollableWorld){
        this.scrollableWorld = scrollableWorld;
    }
}
