package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class SurfaceShape extends Shape{
	private Color innerColor= Color.BLACK;
	
	public abstract void fill(Graphics g);
	public abstract double area();
	
	public String getInnerColorRGB() {
		int red = this.innerColor.getRed();
		int green = this.innerColor.getGreen();
		int blue = this.innerColor.getBlue();
		return "RGB(" + red + ", " + green + ", " + blue + ")";
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
}
