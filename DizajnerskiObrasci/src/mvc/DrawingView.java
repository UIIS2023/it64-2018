package mvc;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Iterator;
import geometry.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingView extends JPanel {
	private DrawingModel model = new DrawingModel();
	
	public DrawingView() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = model.getShapes().iterator();
		while (it.hasNext()) {
			it.next().draw(g);
		}
	}
	
	public void setModel(DrawingModel model) {
		this.model = model;
	}
}
