import java.util.ArrayList;
/**
 * Calc - A class for calculations and randomizing numbers.
 * 
 * @author Nathan Jiang
 * @version October 2022
 */
public class Calc  
{
    /**
     * Method to randomize an int based on an inclusive range
     * 
     * @param min   Inclusive minimum of the int
     * @param max   Inclusive maximum of the int
     * @return int  The randomized int
     */
    public static int randomizeInt(int min, int max){
        return (int)(Math.random() * (max - min + 1)) + min;
    }
    
    /**
     * Method to randomize doubles in an array based on an inclusive range
     * 
     * @param array The array to be randomized
     * @param min   Inclusive minimum of each double
     * @param max   Inclusive maximum of each double
     */
    public static void randomizeDoubleArray(double[] array, double min, double max){
        for(int i = 0; i < array.length; i++){
            array[i] = randomizeDouble(min, max);
        }
    }
    
    
    /**
     * Method to randomize a double based on an inclusive range
     * 
     * @param min   Inclusive minimum of the double
     * @param max   Inclusive maximum of the double
     * @return double  The randomized double
     */
    public static double randomizeDouble(double min, double max){
        return (Math.random() * (max - min)) + min;
    }
    
    /**
     * Method to randomize the sign of a number
     * 
     * @param num   The number to randomize the sign of
     * @return int  The number with a randomized sign
     */
    public static int randomizeSign(int num){
        if(randomizeInt(0, 1) == 0){
            return num;
        }
        return -num;
    }
    /**
     * Method to get the sign of a number
     * 
     * @param num   The number to get the sign of
     * @return int  1 for positive and -1 for negative
     */
    public static int getSign(double num){
        if(num >= 0){
            return 1;
        }else{
            return -1;
        }
    }
    /**
     * Method to return the inverse tan of a slope
     * 
     * @param slope     The slope
     * @return double   The inverse tan of the slope
     */
    public static double invTan(double slope){
        double rad = Math.atan(slope);
        return rad/(Math.PI/180);
    }
    /**
     * Method to return the angle of a line segment
     * 
     * @param startX    X of point A
     * @param startY    Y of point A
     * @param endX      X of point B
     * @param endY      Y of point B
     * @return double   The angle of the line segment
     */
    public static double pointsToAngle(double startX, double startY, double endX, double endY){
        double angle;
        //Check for vertical lines
        if(startX == endX){
            if(endY > startY){
                return 90;
            }
            return -90;
        }else{
            angle = invTan((startY - endY)/(startX - endX));
            if(endX < startX){
                angle -= 180;
            }
        }
        return angle;
    }
    /**
     * Method to return the angle of a line segment, starting from (0, 0)
     * 
     * @param x    X of endpoint
     * @param y    Y of endpoint
     * @return double   The angle of the line segment
     */
    public static double pointsToAngle(double x, double y){
        return pointsToAngle(0, 0, x, y);
    }
    
    //Gets the distance between two points
    public static double getDistance(double x1, double y1, double x2, double y2){
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    //Checks if a number is in an inclusive range
    public static boolean isBetween(double test, double a, double b){
        return (test >= a && test <= b) || (test <= a && test >= b);
    }
    //Gets the midpoint between two numbers
    public static double[] getMidpoint(double x1, double y1, double x2, double y2){
        double[] midpoint = new double[2];
        midpoint[0] = (x1 + x2)/2;
        midpoint[1] = (y1 + y2)/2;
        return midpoint;
    }
    //Converts a number to a point in the form of an ARray
    public static double[] convertToPoint(double x, double y){
        double[] point = {x, y};
        return point;
    }
    //Computes the average of an Array of numbers
    public static double average(double[] nums){
        double sum = 0;
        for(double num : nums){
            sum += num;
        }
        return sum/nums.length;
    }
    //Gets the average from a list of points
    public static double[] averagePoint(ArrayList<double[]> pointList){
        double[] xList = new double[pointList.size()];
        double[] yList = new double[pointList.size()];
        for(int i = 0; i < pointList.size(); i++){
            xList[i] = pointList.get(i)[0];
            yList[i] = pointList.get(i)[1];
        }
        double[] averagePoint = {average(xList), average(yList)};
        return averagePoint;
    }
    //Converts a number to an angle (0-360)
    public static double convertToAngle(double num){
        num = num % 360;
        if(num < 0){
            num = 360 + num;
        }
        return num;
    }
    //Gets the difference between two angles
    public static double getAngleDifference(double ang1, double ang2){
        return Math.min(Math.abs(convertToAngle(ang1) - convertToAngle(ang2)), Math.abs(convertToAngle(ang2) - convertToAngle(ang1)));
    }
    //Maps a value on one scale to another
    public static double map(double num, double inMin, double inMax, double outMin, double outMax){
        return (num - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
    }
    //Method that returns a list of numbers that add to a sum
    public static int[] getRandomNumsThatSumTo(int numNumbers, int sum){
        int[] numsThatSumTo = new int[numNumbers];
        for(int i = 0; i < numNumbers - 1; i++){
            //Add num to the list and subtract sum
            numsThatSumTo[i] = randomizeInt(0, sum);
            sum -= numsThatSumTo[i];
        }
        numsThatSumTo[numNumbers - 1] = sum;
        return numsThatSumTo;
    }
    //Randomizes and array by swapping indices
    public static int[] randomizeArray(int[] array){
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = randomizeInt(0, array.length - 1);
            int temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
        return array;
    }
    public static double maxOfArray(double[] array){
        if(array.length > 0){
            double max = array[0];
            for(double num : array){
                if(num > max){
                    max = num;
                }
            }
            return max;
        }
        return -1;
    }
}
