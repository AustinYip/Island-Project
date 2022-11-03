import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * The base island on which the simulation takes place
 * 
 * Notes
 * 
 * Adjust values
 * Add waves (RANDOM_SPREAD each cycle, turn a little)
 * Camera system (position and resizing)
 */
public class Island extends Graphic
{
    public static final double DISTANCE_BW_POINTS = 1, MAX_RADIUS = 600, MIN_RADIUS = 590, MIN_WAVE_LENGTH = 10,
    FOAM_THICKNESS = 50, RANDOM_SPREAD = 5, WAVE_LENGTH_DIVISOR = 20, HEIGHT_WIDTH_RATIO = 0.90;
    
    private static final int WAVES = 16;
    private static final Color COLOR_SAND = new Color(253, 171, 36), COLOR_FOAM = new Color(239, 254, 255);
    
    private boolean tideRising;
    private double tideSpeed, waveSpeed, islandRadius, tideAcceleration, waveLength, tideMaxSpeed, waveOffset;
    private double[] waveRandomSpread;
    private int frame;
    
    public Island(){
        tideRising = true;
        tideSpeed = 0;
        tideAcceleration = 0.01;
        tideMaxSpeed = 1;
        
        islandRadius = MAX_RADIUS;
        waveLength = 1;
        
        waveOffset = 0;
        waveRandomSpread = new double[WAVES * 2];
        Calc.randomizeDoubleArray(waveRandomSpread, 1, RANDOM_SPREAD);
    }
    public void run(){
        showWaves();
    }
    public GreenfootImage resetImage(){
        return getImage();
    }
    public void showWaves(){
        if(tideRising){
            if(islandRadius > MIN_RADIUS){
                islandRadius += tideSpeed;
                if(tideSpeed > -tideMaxSpeed)
                    tideSpeed -= tideAcceleration;                
            }else{
                tideRising = false;
            }

        }else{
            if(islandRadius < MAX_RADIUS){
                islandRadius += tideSpeed;
                if(tideSpeed < tideMaxSpeed)
                    tideSpeed += tideAcceleration;
            }else{
                tideRising = true;
                //Calc.randomizeDoubleArray(waveRandomSpread, 1, RANDOM_SPREAD);
                //waveOffset = Calc.randomizeDouble(0, 360/WAVES);
            }
        }
        
        waveLength = (Math.max(MAX_RADIUS - islandRadius, MIN_WAVE_LENGTH))/WAVE_LENGTH_DIVISOR;
        //waveLength = (Math.max(MAX_RADIUS - islandRadius, 0))/WAVE_LENGTH_DIVISOR
        setImage(getIslandImage(islandRadius, waveRandomSpread, waveLength, waveOffset));
       
    }
    /**
     * Get the image of the Island using a wave function
     * 
     * @author Nathan Jiang
     * @version October 2022
     */
    public GreenfootImage getIslandImage(double distanceBwPoints, double radius, double randomSpread[], double waveLength, double foamThickness, int waves, double waveOffset, double heightWidthRatio){
        
        
        //Create base and set variables
        int overallRadius = (int)(radius+waveLength*Calc.maxOfArray(randomSpread)+foamThickness);
        int min = (int)(radius-waveLength);
        GreenfootImage image = new GreenfootImage(2*overallRadius, 2*overallRadius);
        
        
        //Determine degree size
        double invCosValue = (2 * Math.pow(radius, 2) - Math.pow(distanceBwPoints, 2))/(2 * Math.pow(radius, 2));
       
        double degreeSize = Math.toDegrees(Math.acos(invCosValue));
        int numPoints = (int)(360 / degreeSize + 0.5);
        int[] xPoints = new int[numPoints];
        int[] yPoints = new int[numPoints];
        
        //Draw foam
        image.setColor(COLOR_FOAM);
        getSplatPoints(xPoints, yPoints, xPoints.length, degreeSize, overallRadius, waveLength, min+foamThickness, randomSpread, waves, waveOffset, heightWidthRatio);
        
        image.fillPolygon(xPoints, yPoints, xPoints.length);
        
        GreenfootImage sand = new GreenfootImage(2*overallRadius, 2*overallRadius);
        sand.setColor(COLOR_SAND);
        sand.fillPolygon(xPoints, yPoints, xPoints.length);
        int sandRadius = (int)(overallRadius-foamThickness);
        sand.scale(2*sandRadius, 2*sandRadius);
        image.drawImage(sand, overallRadius-sandRadius, overallRadius-sandRadius);
        
       
        return image;
    }
    public GreenfootImage getIslandImage(double radius, double[] randomSpread, double waveLength, double waveOffset){
        return getIslandImage(DISTANCE_BW_POINTS/getScrollableWorld().getZoom(), radius, randomSpread, waveLength, FOAM_THICKNESS, WAVES, waveOffset, HEIGHT_WIDTH_RATIO);
    }
   
    
    public void getSplatPoints(int[] xPoints, int[] yPoints, int numPoints, double degreeSize, double overallRadius, double waveLength, double min, double[] randomSpread, int waves, double waveOffset, double heightWidthRatio){  

        double spreadMultiplyer = randomSpread[0];
        int randomSpreadIndex = 0;
        
        for(int i = 0; i < numPoints; i++){
            //Add point using angle and length
            double radians = Math.toRadians(i * degreeSize);
            if(Math.abs((i * degreeSize - waveOffset) % (180.0/waves)) < degreeSize){
                spreadMultiplyer = randomSpread[randomSpreadIndex];
                if(randomSpreadIndex < randomSpread.length - 1){
                    randomSpreadIndex++;
                }else{
                    randomSpreadIndex = 0; 
                }
            }
            double length = (min + spreadMultiplyer*waveLength*Math.sin(Math.toRadians((i*degreeSize-waveOffset)*waves))); 
            //Waves is k value, length of period is 360/wave
            
            xPoints[i] = (int)(Math.cos(radians) * length + overallRadius);
            yPoints[i] = (int)(Math.sin(radians) * length * heightWidthRatio + overallRadius);    
            
            
            if(Greenfoot.getRandomNumber(300) == 0){
                double foamDistance = Calc.randomizeInt(10, 20);
                double particleX = getExactX() + Math.cos(radians) * (length-foamDistance);
                double particleY = getExactY() + Math.sin(radians) * (length-foamDistance) * heightWidthRatio;
                getScrollableWorld().addObject(new Particle(false, 40, 40, 1, Greenfoot.getRandomNumber(360), COLOR_FOAM, 2), particleX, particleY);
            }
         
        }
    
    }
}
