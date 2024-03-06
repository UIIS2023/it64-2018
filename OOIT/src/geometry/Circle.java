package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape{
	private Point center;
	private int radius;
	private boolean selected;
	
	public Circle() {

	}
	
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		this.selected=selected;
	}
	
	public Circle(Point center, int radius, boolean selected, Color color) {
		this(center, radius, selected);
		setColor(color);
	}
	
	public Circle(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		setInnerColor(innerColor);
	}
	
	public double area() {
		return radius * radius * Math.PI;
	}
	
	public int compareTo(Object o) {
		if (o instanceof Circle) {
			return this.radius - ((Circle)o).radius;
		}
		return 0;
	}
	
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(this.center.getX() - radius, this.center.getY() - radius, radius * 2, radius * 2);
		
		fill(g);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.center.getX() - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() + radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() - radius - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() + radius - 3, 6, 6);
		}
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(this.center.getX() - radius + 1, this.center.getY() - radius + 1, (radius * 2) - 2, (radius * 2) - 2);	
	}
	
	public boolean contains(Point p) {
		return this.getCenter().distance(p.getX(), p.getY()) <= radius;
	}
	
	@Override
	public boolean contains(int x, int y) {
		return this.getCenter().distance(x, y) <= radius;
	}
	
	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);
	}
	
	public void moveOn(int onX, int onY){
		center.moveOn(onX, onY);
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
