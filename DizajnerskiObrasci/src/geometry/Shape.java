package geometry;

import java.awt.Graphics;
import java.io.Serializable;
import adapter.HexagonAdapter;
import java.awt.Color;

public abstract class Shape implements Moveable, Comparable, Serializable{
	private boolean selected;
	private Color color= Color.BLACK;
	
	public abstract void draw(Graphics g);
	public abstract boolean contains(int x, int y);
	
	public void markPoint(Graphics g, int x, int y) {
		g.drawRect(x-3, y-3, 6, 6);
	}
	
	public String getColorRGB() {
		int red = this.color.getRed();
		int green = this.color.getGreen();
		int blue = this.color.getBlue();
		return "RGB(" + red + ", " + green + ", " + blue + ")";
	}
	
	public boolean isSelected() {
		if (this instanceof HexagonAdapter)
			return ((HexagonAdapter)this).getHexagon().isSelected();
		else
			return selected;
	}

	public void setSelected(boolean selected) {
		if (this instanceof HexagonAdapter)
			((HexagonAdapter)this).getHexagon().setSelected(selected);
		else
			this.selected = selected;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
