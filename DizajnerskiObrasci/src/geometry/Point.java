package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape  implements Cloneable{
	private int x;
	private int y;
	private boolean selected;
	
	public Point() {
		
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, boolean selected) {
		this(x, y);
		setSelected(selected);
	}
	
	public Point(int x, int y, boolean selected, Color color) {
		this(x, y, selected);
		setColor(color);
	}
	
	public double distance(int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d; 
	}
	
	public int compareTo(Object o) {
		if (o instanceof Point) {
			Point start = new Point(0, 0);
			return (int) (this.distance(start.getX(), start.getY()) - ((Point) o).distance(start.getX(), start.getY()));
		}
		return 0;
	}
	
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.x-3, this.y, this.x+3, this.y);
		g.drawLine(this.x, this.y-3, this.x, this.y+3);
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			markPoint(g, this.x, this.y);
		}
	}
	
	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 3 ;
	}
	
	public void moveBy(int byX, int byY) {
		this.x += byX;
		this.y += byY;
	}
	
	public void moveOn(int onX, int onY){
		this.x=onX;
		this.y=onY;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "Point [x= " + x + ", y= " + y + ", Color= " + this.getColorRGB() + "]";
	}	
	
	public String toStringPoint() {
		return "(" + x + ", " + y + ")";
	}
	
	@Override
	public Point clone() {
		Point point = new Point();
		
		point.setX(this.getX());
		point.setY(this.getY());
		
		point.setColor(this.getColor());
		point.setSelected(this.isSelected());
		
		return point;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		return selected == other.selected && x == other.x && y == other.y;
	}
	
	
}
