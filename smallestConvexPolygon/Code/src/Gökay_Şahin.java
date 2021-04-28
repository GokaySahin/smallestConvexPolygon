/**
 * @author Gökay_Şahin_041901032
 */

import java.awt.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Gökay_Şahin {
    public static void main(String[] args) {
        Stack<Point> convexHull = new Stack<Point>();
        System.out.println("Enter the number of points");
        Scanner scanner = new Scanner(System.in);
        // declaration of circle numbers
        int numberOfPoints = scanner.nextInt(); // Taking user input for point numbers
        Point[] pointArray = new Point[numberOfPoints];
        double[] angleArray = new double[numberOfPoints];
        double max = 8.5;
        double min = -8.5;
/**
 * Assigning random values to points
 */
        for (int i = 0; i < numberOfPoints; i++) {
            Random random = new Random();
            pointArray[i] = new Point(Math.random() * (max - min) + min, Math.random() * (max - min) + min);
        }
        double minY = 0;
        /**
         * Finding the point with lowest Y value
         */
        int bottomPointIndex = 0;
        for (int i = 0; i < numberOfPoints; i++) {
            if (pointArray[i].y <= minY) {
                minY = pointArray[i].y;
                bottomPointIndex = i;
            }
        }
        convexHull.push(pointArray[bottomPointIndex]);
        /**
         * Assigning angle values to points
         */
        for (int i = 0; i < numberOfPoints; i++) {
            double angle = angleCalculator(convexHull.get(0), pointArray[i]);
            if (angle == 0) {
            }
            pointArray[i].angleBetweenP0 = angle;
        }
        bubbleSort(pointArray);
        convexHull.push(pointArray[1]);
        convexHull.push(pointArray[2]);
        int i = 3 ;
        while(i < pointArray.length) {
            int t1 = convexHull.size()-1;
            int t2 = convexHull.size() - 2;
            double a = (convexHull.get(t1).x - convexHull.get(t2).x) * (pointArray[i].y - convexHull.get(t2).y) - (pointArray[i].x - convexHull.get(t2).x) * (convexHull.get(t1).y - convexHull.get(t2).y);
            if (Double.compare(a, 0) >= 0) {
                convexHull.push(pointArray[i]);
                i++;
            } else {
                convexHull.pop();
            }
        }

        convexHull.push(convexHull.get(0));
        printer(pointArray,convexHull);

    }

    /**
     *
     * @param firstPoint First point to calculate angle between
     * @param secondPoint First point to calculate angle between
     * @return returns the angle as double
     */
    public static double angleCalculator(Point firstPoint, Point secondPoint) {
        double angle = Math.toDegrees(Math.atan2(secondPoint.y - firstPoint.y, secondPoint.x - firstPoint.x));
        return angle;
    }

    /**
     *
     * @param inputArray Array to sorted with bubble sort algorithm
     */

    public static void bubbleSort(Point[] inputArray) {
        int n = inputArray.length;
        for (int i = 0; i <n-1 ; i++) {
            for (int j = 0; j <n-i-1 ; j++) {
                if (Double.compare((inputArray[j].angleBetweenP0) , inputArray[j+1].angleBetweenP0) > 0) {
                    Point temp = inputArray[j];
                    inputArray[j] = inputArray[j+1];
                    inputArray[j+1] = temp;
                }

            }

        }
    }

    /**
     *
     * @param inputArray Normal points to be printed in canvas  which they are stored in a array
     * @param inputConvex Convex points to be printed in canvas which they are stored in a stack
     */
    public static void printer (Point[] inputArray, Stack<Point> inputConvex) {
        // sets the scale of drawing canvas
        StdDraw.setCanvasSize(500, 500);
        // sets the scale of coordinate system
        StdDraw.setXscale(-10, 10);
        StdDraw.setYscale(-10, 10);
        StdDraw.setPenColor(Color.black);
        for (int i = 0; i <inputArray.length ; i++) {
            StdDraw.filledCircle(inputArray[i].x,inputArray[i].y,inputArray[i].radius);

        }
        StdDraw.setPenColor(Color.red);
        for (int i = 0; i <inputConvex.size()-1 ; i++) {
            StdDraw.filledCircle(inputConvex.get(i).x,inputConvex.get(i).y,inputConvex.get(i).radius);
            StdDraw.line(inputConvex.get(i).x,inputConvex.get(i).y,inputConvex.get(i+1).x,inputConvex.get(i+1).y);
        }



    }
}


