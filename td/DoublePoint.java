/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package td;

/**
 *
 * @author David
 */
public class DoublePoint {
    private double x;
    private double y;

    public DoublePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public void setLocation(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public double squareDistance(double x0, double y0){
        return (x-x0)*(x-x0)+(y-y0)*(y-y0);
    }
    
}
