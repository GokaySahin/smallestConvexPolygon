/**
 * @author Gökay_Şahin_041901032
 */

/**
 * x for X coordinate values
 * y for Y coordinate values
 * radius for Radius of points and It's constant
 * angleBetweenP0 is value to come from and required for Algorithm
 *
 */
public class Point {
    public double x;
    public double y;
    final double radius = 0.1;
    public double angleBetweenP0 = 0;

    /**
     *
     * @param inputX X coordinate
     * @param inputY Y coordinate
     *               Constructor with 2 param which is default
     */
    public Point(double inputX , double inputY) {
        this.x = inputX;
        this.y = inputY;

    }
}
