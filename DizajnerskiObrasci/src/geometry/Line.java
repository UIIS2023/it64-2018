package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;

public class Line extends Shape implements Cloneable{
	private Point startPoint = new Point();
	private Point endPoint = new Point();
	private boolean selected;
	
	public Line() {
		
	}
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		this.selected = selected;
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected, Color color) {
		this(startPoint, endPoint, selected);
		setColor(color);
	}
	
	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	
	public int compareTo(Object o) {
		if (o instanceof Line) {
			return (int) (this.length() - ((Line) o).length() );
		} 
		return 0;
	}
	
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			markPoint(g, this.startPoint.getX(), this.startPoint.getY());
			markPoint(g, this.endPoint.getX(), this.endPoint.getY());
		}	
	}
	
	public boolean contains(int x, int y) {
		if ((startPoint.distance(x, y) + endPoint.distance(x, y)) - length() <= 0.05) {
			return true;
		} else {
			return false;
		}
	}
	
	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
		endPoint.moveBy(byX, byY);
	}
	
	public void moveOn(int onX, int onY){
		startPoint.moveOn(onX, onY);
		endPoint.moveOn(onX, onY);		
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "Line [startPoint=" + startPoint.toStringPoint() + ", endPoint=" + endPoint.toStringPoint() + ", Color= " + this.getColorRGB() + "]";
	}
	
	@Override	
	public Line clone() {
		Line line = new Line();
		
		line.getStartPoint().setX(this.getStartPoint().getX());
		line.getStartPoint().setY(this.getStartPoint().getY());
		line.getEndPoint().setX(this.getEndPoint().getX());
		line.getEndPoint().setY(this.getEndPoint().getY());

		line.setColor(this.getColor());
		line.setSelected(this.isSelected());
		
		return line;	
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Line other = (Line) obj;
		return Objects.equals(endPoint, other.endPoint) && selected == other.selected
				&& Objects.equals(startPoint, other.startPoint);
	}
}
