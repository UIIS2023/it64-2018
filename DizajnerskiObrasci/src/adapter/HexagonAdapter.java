package adapter;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;
import geometry.Point;
import geometry.Shape;
import hexagon.Hexagon;

public class HexagonAdapter extends Shape implements Cloneable { //shape zato sto u surfaceShape imamo fill i area koje nam sada ne trebaju
	private  Hexagon hexagon = new Hexagon(0,0,0);
	
	public HexagonAdapter()
	{
		
	}
	
	public HexagonAdapter(Point center)
	{
		hexagon.setX(center.getX());
		hexagon.setY(center.getY());	
	}
	
	public HexagonAdapter(Point center, int radius)
	{
		this(center);
		hexagon.setR(radius);
	}
	
	public HexagonAdapter(Point center, int radius, boolean selected) {
		this(center, radius);
		hexagon.setSelected(selected);
	}
	
	public HexagonAdapter(Point center, int radius, boolean selected, Color color) {
		this(center, radius, selected);
		hexagon.setBorderColor(color);
	}
	
	public HexagonAdapter(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		hexagon.setAreaColor(innerColor);
	}
	
	@Override
	public void moveOn(int onX, int onY) {
		hexagon.setX(onX);
		hexagon.setY(onY);
	}

	@Override
	public void moveBy(int byX, int byY) {
		hexagon.setX(hexagon.getX()+byX);
		hexagon.setY(hexagon.getY()+byY);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof HexagonAdapter) {
			return this.hexagon.getR() - ((HexagonAdapter)o).hexagon.getR();
		}
		return 0;
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			markPoint(g, this.hexagon.getX(), this.hexagon.getY());
			
			markPoint(g, this.hexagon.getX()-this.hexagon.getR(), this.hexagon.getY());
			markPoint(g, this.hexagon.getX()+this.hexagon.getR(), this.hexagon.getY());
			
			markPoint(g, this.hexagon.getX()-this.hexagon.getR()/2, this.hexagon.getY()-(int)(this.hexagon.getR()*Math.sqrt(3)/2));
			markPoint(g, this.hexagon.getX()+this.hexagon.getR()/2, this.hexagon.getY()-(int)(this.hexagon.getR()*Math.sqrt(3)/2));
			
			markPoint(g, this.hexagon.getX()-this.hexagon.getR()/2, this.hexagon.getY()+(int)(this.hexagon.getR()*Math.sqrt(3)/2));
			markPoint(g, this.hexagon.getX()+this.hexagon.getR()/2, this.hexagon.getY()+(int)(this.hexagon.getR()*Math.sqrt(3)/2));
		}
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public String toString() {
		Point center = new Point(hexagon.getX(), hexagon.getY());
		return "Hexagon [center=" + center.toStringPoint() + ", radius=" + hexagon.getR() + ", Color= " + this.getColorRGB() + ", Color= " + this.getInnerColorRGB() +  "]";
	}
	
	public String getColorRGB() {
		int red = hexagon.getBorderColor().getRed();
		int green = hexagon.getBorderColor().getGreen();
		int blue = hexagon.getBorderColor().getBlue();
		return "RGB(" + red + ", " + green + ", " + blue + ")";
	}
	
	public String getInnerColorRGB() {
		int red = hexagon.getAreaColor().getRed();
		int green = hexagon.getAreaColor().getGreen();
		int blue = hexagon.getAreaColor().getBlue();
		return "(" + red + ", " + green + ", " + blue + ")";
	}

	@Override
	public HexagonAdapter clone() {
		HexagonAdapter hex=new HexagonAdapter();
		
		hex.hexagon.setX(this.hexagon.getX());
		hex.hexagon.setY(this.hexagon.getY());
		hex.hexagon.setR(this.hexagon.getR());
		
		hex.hexagon.setBorderColor(this.hexagon.getBorderColor());
		hex.hexagon.setAreaColor(this.hexagon.getAreaColor());
		hex.hexagon.setSelected(this.hexagon.isSelected());
		
		return hex;
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HexagonAdapter other = (HexagonAdapter) obj;
		return Objects.equals(hexagon.getX(), other.getHexagon().getX()) && hexagon.getY() == other.getHexagon().getY() && hexagon.getR() == other.getHexagon().getR() && hexagon.isSelected() == other.getHexagon().isSelected();
	}
	
}
