package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle implements Cloneable{
	private int innerRadius;
	
	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) { 
		this(center, radius, innerRadius, selected);
		setColor(color);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) { 
		this(center, radius, innerRadius, selected, color);
		setInnerColor(innerColor);
	}
	
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(getColor());
		g.drawOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, getInnerRadius() * 2, getInnerRadius() * 2);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			super.markCirclePoints(g, this.getRadius());
			super.markCirclePoints(g, innerRadius);
		}
	}
	
	public void fill(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(getInnerColor());

		Ellipse2D whole = new Ellipse2D.Double(this.getCenter().getX()-this.getRadius(), this.getCenter().getY()-this.getRadius(), 2* this.getRadius(), 2* this.getRadius());
		Ellipse2D inner = new Ellipse2D.Double(this.getCenter().getX()-this.innerRadius, this.getCenter().getY()-this.innerRadius, 2* this.innerRadius, 2* this.innerRadius);
		
		Area area = new Area(whole);
		Area areaI = new Area(inner);
		area.subtract(areaI);
		
		g2d.fill(area);
	}
	
	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}
	
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p) && dFromCenter > innerRadius;
	}

	public void moveBy(int byX, int byY) {
		super.moveBy(byX, byY);
	}
	
	public void moveOn(int onX, int onY){
		super.moveOn(onX, onY);
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) throws Exception{
		if (innerRadius < this.getRadius())
			this.innerRadius = innerRadius;
		else
			throw new Exception();
	}

	@Override
	public String toString() {
		return "Donut [center=" + this.getCenter().toStringPoint() + ", outerRadius=" + this.getRadius() + ", innerRadius=" + innerRadius +  ", Color= " + this.getColorRGB() + ", innerColor= " + this.getInnerColorRGB() + "]";
	}
	
	@Override
	public Donut clone() {
		Donut donut = new Donut();
		
		donut.setCenter(this.getCenter().clone());
		donut.setRadius(this.getRadius());
		
		donut.setColor(this.getColor());
		donut.setInnerColor(this.getInnerColor());
		donut.setSelected(this.isSelected());
		
		//super.clone();
		
		try {
			donut.setInnerRadius(this.getInnerRadius());
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return donut;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Donut other = (Donut) obj;
		return innerRadius == other.innerRadius;
	}
	
	
}
