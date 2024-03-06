package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class PnlDrawing extends JPanel{
	
	private FrmDrawing frame;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private Shape selected;
	private Point startPoint;

	public PnlDrawing(FrmDrawing frame) {
		this.frame = frame;
		setBackground(Color.WHITE);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				thisMouseClicked(arg0); 
			}
		});
	}
	
	protected void thisMouseClicked(MouseEvent e){
		Shape newShape=null;
		int v1=0,v2=0;
		if (frame.getTglbtnSelection().isSelected()){
			selected=null;
			Iterator<Shape> it=shapes.iterator();
			while (it.hasNext()){
				Shape shape = it.next();
				shape.setSelected(false);
				if(shape.contains(e.getX(), e.getY()))
					selected= shape;
			}
			if (selected!=null)
				selected.setSelected(true);
		} else if(frame.getTglbtnPoint().isSelected()){
				newShape=new Point(e.getX(), e.getY());
			} else if (frame.getTglbtnLine().isSelected()){
				if (startPoint==null)
					startPoint = new Point (e.getX(), e.getY());
				else {
					newShape = new Line(startPoint, new Point(e.getX(),e.getY()));
					startPoint=null;
				}
		} else if (frame.getTglbtnRectangle().isSelected()){
			DlgRectangle dlg = new DlgRectangle();
			Rectangle r= new Rectangle();
			dlg.setModal(true);
			dlg.getTxtX().setText("" + e.getX());
			dlg.getTxtY().setText("" + e.getY());
			dlg.setVisible(true);
			if(dlg.isOK()){
				v1=Integer.parseInt(dlg.getTxtHeight().getText());
				v2=Integer.parseInt(dlg.getTxtWidth().getText());
			}
			r = new Rectangle(new Point(e.getX(),e.getY()), v1, v2);
			r.setColor(dlg.getC());
			r.setInnerColor(dlg.getInnerC());
			try {
				newShape = r;
			} catch (Exception ex){
				JOptionPane.showMessageDialog(frame, "Wrong data type.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (frame.getTglbtnCircle().isSelected()){
			DlgCircle dlg= new DlgCircle();
			Circle c= new Circle();
			dlg.setModal(true);
			dlg.getTxtX().setText("" + e.getX());
			dlg.getTxtY().setText("" + e.getY());
			dlg.setVisible(true);
			if(dlg.isOK()){
				v1=Integer.parseInt(dlg.getTxtRadius().getText());
			}
			c = new Circle(new Point(e.getX(),e.getY()), v1);
			c.setColor(dlg.getC());
			c.setInnerColor(dlg.getInnerC());
			try {
				newShape= c;
			} catch (Exception ex){
				JOptionPane.showMessageDialog(frame, "Wrong data type.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (frame.getTglbtnDonut().isSelected()){
			DlgDonut dlg= new DlgDonut();
			Donut d = new Donut();
			dlg.setModal(true);
			dlg.getTxtX().setText("" + e.getX());
			dlg.getTxtY().setText("" + e.getY());
			dlg.setVisible(true);
			if(dlg.isOK()){
				v1=Integer.parseInt(dlg.getTxtRadius().getText());
				v2=Integer.parseInt(dlg.getTxtInnerRadius().getText());
			}
			d = new Donut(new Point(e.getX(),e.getY()), v1,v2);
			d.setColor(dlg.getC());
			d.setInnerColor(dlg.getInnerC());
			try {
				newShape= d;
			} catch (Exception ex){
				JOptionPane.showMessageDialog(frame, "Wrong data type.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} 
		if (newShape!=null)
			shapes.add(newShape);
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = shapes.iterator();
		while(it.hasNext())
			it.next().draw(g);
	}
	
	public Shape getSelected() {
		return selected;
	}

	public void setSelected(Shape selected) {
		this.selected = selected;
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}	
}
