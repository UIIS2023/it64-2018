package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;


public class Rectangle extends SurfaceShape implements Cloneable{
	private Point upperLeft = new Point();
	private int height;
	private int width;
	private boolean selected;
	
	public Rectangle() {

	}
	
	public Rectangle(Point upperLeftPoint, int height, int width) {
		this.upperLeft = upperLeftPoint;
		this.height = height;
		this.width = width;
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected) {
		this(upperLeftPoint, height, width);
		this.selected = selected;
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color color) {
		this(upperLeftPoint, height, width, selected);
		setColor(color);
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color color, Color innerColor) {
		this(upperLeftPoint, height, width, selected, color);
		setInnerColor(innerColor);
	}
	
	public double area() {
		return width * height;
	}
	
	public int compareTo(Object o) {
		if (o instanceof Rectangle) {
			return (int) (this.area() - ((Rectangle) o).area());	
		}
		return 0;
	}

	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(this.upperLeft.getX(), this.upperLeft.getY(), this.width, this.height);
		
		this.fill(g);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			markPoint(g, this.upperLeft.getX(), this.upperLeft.getY());
			markPoint(g, this.upperLeft.getX() + this.width, this.upperLeft.getY());
			markPoint(g, this.upperLeft.getX(), this.upperLeft.getY() + this.height);
			markPoint(g, this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
		}
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeft.getX()+1, this.getUpperLeft().getY()+1, width-1, height-1);
	}
	
	public boolean contains(Point p) {
		if (upperLeft.getX() <= p.getX() &&
				this.getUpperLeft().getY() <= p.getY() &&
				p.getX() <= upperLeft.getX() + width &&
				p.getY() <= upperLeft.getY() + height) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean contains(int x, int y) {
		if (upperLeft.getX() <= x &&
				this.getUpperLeft().getY() <= y &&
				x <= upperLeft.getX() + width &&
				y <= upperLeft.getY() + height) {
			return true;
		} else {
			return false;
		}
	}
	
	public void moveBy(int byX, int byY) {
		upperLeft.moveBy(byX, byY);
	}
	
	public void moveOn(int onX, int onY){
		upperLeft.moveOn(onX, onY);
	}
	
	public Point getUpperLeft() {
		return upperLeft;
	}
	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "Rectangle [upperLeft=" + upperLeft.toStringPoint() + ", height=" + height + ", width=" + width + ", Color= " + this.getColorRGB() + ", Color= " + this.getInnerColorRGB() + "]";
	}
	
	@Override	
	public Rectangle clone() {
		Rectangle rect = new Rectangle();
		
		rect.setUpperLeft(this.getUpperLeft().clone());
		rect.setHeight(this.getHeight());
		rect.setWidth(this.getWidth());
		
		rect.setInnerColor(this.getInnerColor());
		rect.setColor(this.getColor());
		rect.setSelected(this.isSelected());
		
		return rect;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rectangle other = (Rectangle) obj;
		return height == other.height && selected == other.selected && Objects.equals(upperLeft, other.upperLeft)
				&& width == other.width;
	}
}
