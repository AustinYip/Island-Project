import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The base island on which the simulation takes place
 * 
 * Notes
 * 
 * Adjust values
 * Add waves (RANDOM_SPREAD each cycle, turn a little)
 * Camera system (position and resizing)
 */
public class IslandBase extends Graphic
{
    private static final double DISTANCE_BW_POINTS = 1, RADIUS = Island.MAX_RADIUS + 200, 
    RANDOM_SPREAD = 6, WAVE_LENGTH = 10, HEIGHT_WIDTH_RATIO = Island.HEIGHT_WIDTH_RATIO;
    
    private static final int WAVES = 8;
    private static final Color COLOR_SAND = new Color(253, 171, 36);
    
    private double[] randomPoints;
    public IslandBase(){
        randomPoints = new double[WAVES * 2];
        Calc.randomizeDoubleArray(randomPoints, 1, RANDOM_SPREAD);
    }
    public GreenfootImage resetImage(){
        return getIslandImage(RADIUS, randomPoints, WAVE_LENGTH, 360/WAVES);
    }
    /**
     * Get the image of the Island using a wave function
     * 
     * @author Nathan Jiang
     * @version October 2022
     */
    public GreenfootImage getIslandImage(double distanceBwPoints, double radius, double randomSpread[], double waveLength, int waves, int waveOffset, double heightWidthRatio){
        //Create base and set variables
        int overallRadius = (int)(radius+waveLength*Calc.maxOfArray(randomSpread));
        int min = (int)(radius-waveLength);
        GreenfootImage image = new GreenfootImage(2*overallRadius, 2*overallRadius);
        
        //Determine degree size
        double invCosValue = (2 * Math.pow(radius, 2) - Math.pow(distanceBwPoints, 2))/(2 * Math.pow(radius, 2));
       
        double degreeSize = Math.toDegrees(Math.acos(invCosValue));
        int numPoints = (int)(360 / degreeSize + 0.5);
        int[] xPoints = new int[numPoints];
        int[] yPoints = new int[numPoints];
        
        //Draw sand
        image.setColor(COLOR_SAND);
        getSplatPoints(xPoints, yPoints, numPoints, degreeSize, overallRadius, waveLength, min, randomSpread, waves, waveOffset, heightWidthRatio);
        image.fillPolygon(xPoints, yPoints, numPoints);

        return image;
    }
    public GreenfootImage getIslandImage(double radius, double[] randomSpread, double waveLength, int waveOffset){
        return getIslandImage(DISTANCE_BW_POINTS/getScrollableWorld().getZoom(), radius, randomSpread, waveLength, WAVES, waveOffset, HEIGHT_WIDTH_RATIO);
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
        }
    }
}
