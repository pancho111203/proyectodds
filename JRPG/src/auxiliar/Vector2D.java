package auxiliar;

import java.awt.geom.Point2D;



public class Vector2D extends Point2D.Double {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Vector2D() {
        super();
    }
    public Vector2D(double x, double y) {
        super(x, y);
    }
    public Vector2D(Vector2D v) {
        x = v.x;
        y = v.y;
    }
    public double getR() {
        return Math.sqrt(x * x + y * y);
    }
    public double getTheta() {
        return Math.atan2(y, x);
    }
    public void set(double x, double y) {
        super.setLocation(x, y);
    }
    public void setPolar(double r, double t) {
        super.setLocation(r * Math.cos(t), r * Math.sin(t));
    }
    public void setR(double r) {
        double t = getTheta();
        setPolar(r, t);
    }
    public void setTheta(double t) {
        double r = getR();
        setPolar(r, t);
    }
    public Vector2D plus(Vector2D rhs) {
        return new Vector2D(x + rhs.x, y + rhs.y);
    }
    public Vector2D minus(Vector2D rhs) {
        return new Vector2D(x - rhs.x, y - rhs.y);
    }
    public boolean equals(Vector2D rhs) {
        return x == rhs.x && y == rhs.y;
    }
    public Vector2D scalarMult(double scalar) {
        return new Vector2D(scalar * x, scalar * y);
    }
    public double dotProduct(Vector2D rhs) {
        return x * rhs.x + y * rhs.y;
    }
    public double crossProduct(Vector2D rhs) {
        return x * rhs.y - y * rhs.x;
    }
    public double componentProduct() {
        return x * y;
    }
    public Vector2D componentwiseProduct(Vector2D rhs) {
        return new Vector2D(x * rhs.x, y * rhs.y);
    }
    public double length() {
        return getR();
    }
    public Vector2D unitVector() {
        if (getR() != 0) {
            return new Vector2D(x / getR(), y / getR());
        }
        return new Vector2D(0,0);
    }
    public Vector2D toPolar() {
        return new Vector2D(Math.sqrt(x * x + y * y), Math.atan2(y, x));
    }
    public Vector2D toRect() {
        return new Vector2D(x * Math.cos(y), x * Math.sin(y));
    }
    public String toString() {
        return "<" + x + ", " + y + ">";
    }
    
    public Vector2D normalizeToLength(double x){
    	double l = this.length();
    	return scalarMult((1/l)*x);
    }
}