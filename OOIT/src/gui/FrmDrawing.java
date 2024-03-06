package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Line;
import geometry.Rectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Shape;

public class FrmDrawing extends JFrame {

	private PnlDrawing pnlDrawing= new PnlDrawing(this);
	private JPanel contentPane;
	
	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnLine = new JToggleButton("Line");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnDonut = new JToggleButton("Donut");
	private JToggleButton tglbtnSelection = new JToggleButton("Selection");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmDrawing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		setResizable(false);
		setTitle("IT 64-2018 Jovana Ostojic");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(pnlDrawing, BorderLayout.CENTER);
		
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		JButton btnModification = new JButton("Modification");
		btnModification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modify();
			}
		});
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delete();
			}
		});
		
		pnlNorth.add(tglbtnPoint);
		pnlNorth.add(tglbtnLine);
		pnlNorth.add(tglbtnRectangle);
		pnlNorth.add(tglbtnCircle);
		pnlNorth.add(tglbtnDonut);	
		pnlSouth.add(tglbtnSelection);
		pnlSouth.add(btnModification);
		pnlSouth.add(btnDelete);	
		
		btnGroup.add(tglbtnPoint);
		btnGroup.add(tglbtnLine);
		btnGroup.add(tglbtnRectangle);
		btnGroup.add(tglbtnCircle);
		btnGroup.add(tglbtnDonut);
		btnGroup.add(tglbtnSelection);
		
		pnlDrawing.repaint();
	}

	protected void delete() {
		Shape selected = pnlDrawing.getSelected();
		if (selected != null) {
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning message",
					JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				pnlDrawing.getShapes().remove(selected);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You have not selected any shape!","Error", JOptionPane.WARNING_MESSAGE);
		}
		pnlDrawing.setSelected(null);
		pnlDrawing.repaint();
	}
	
	protected void modify(){
		int x=0,y=0;
		Shape selected = pnlDrawing.getSelected();
		if (selected != null) {
			if (selected instanceof Point) {
				Point p = (Point) selected;
				DlgPoint dlg = new DlgPoint();
				dlg.getTxtX().setText("" + p.getX());
				dlg.getTxtY().setText("" + p.getY());
				dlg.setPc(p.getColor());
				dlg.setModal(true);
				dlg.setVisible(true);
				if (dlg.isOK()) {
					x=Integer.parseInt(dlg.getTxtX().getText());
					y=Integer.parseInt(dlg.getTxtY().getText());
					p = new Point(x, y);
					p.setColor(dlg.getC());			
					pnlDrawing.getShapes().set(pnlDrawing.getShapes().indexOf(selected), p);	
				}
			} else if(selected instanceof Line){
				Line l= (Line)selected;
				DlgLine dlg=new DlgLine();
				dlg.getTxtXs().setText("" + l.getStartPoint().getX());
				dlg.getTxtYs().setText("" + l.getStartPoint().getY());
				dlg.getTxtXe().setText("" + l.getEndPoint().getX());
				dlg.getTxtYe().setText("" + l.getEndPoint().getY());
				dlg.setPc(l.getColor());
				dlg.setModal(true);
				dlg.setVisible(true);
				if(dlg.isOK()){
					x=Integer.parseInt(dlg.getTxtXs().getText());
					y=Integer.parseInt(dlg.getTxtYs().getText());
					int xe=Integer.parseInt(dlg.getTxtXe().getText());
					int ye=Integer.parseInt(dlg.getTxtYe().getText());
					Point p1 = new Point(x, y);
					Point p2= new Point(xe,ye);
					l=new Line(p1,p2);
					l.setColor(dlg.getC());
					pnlDrawing.getShapes().set(pnlDrawing.getShapes().indexOf(selected), l);
				}
			} else if (selected instanceof Rectangle){
				Rectangle r= (Rectangle) selected;
				DlgRectangle dlg=new DlgRectangle();
				dlg.getTxtX().setText("" + r.getUpperLeft().getX());
				dlg.getTxtY().setText("" + r.getUpperLeft().getY());
				dlg.getTxtHeight().setText(""+r.getHeight());
				dlg.getTxtWidth().setText(""+r.getWidth());
				dlg.setPc(r.getColor());
				dlg.setInnerPc(r.getInnerColor());
				dlg.setModal(true);
				dlg.setVisible(true);
				if(dlg.isOK()){
					x=Integer.parseInt(dlg.getTxtX().getText());
					y=Integer.parseInt(dlg.getTxtY().getText());
					int h=Integer.parseInt(dlg.getTxtHeight().getText());
					int w=Integer.parseInt(dlg.getTxtWidth().getText());
					Point p=new Point(x,y);
					r=new Rectangle(p,h,w);
					if(dlg.isColorChosen()){
						r.setColor(dlg.getC());
					}else{
						r.setColor(dlg.getPc());
					}
					if(dlg.isInnerColorChosen()){
						r.setInnerColor(dlg.getInnerC());
					}else{
						r.setInnerColor(dlg.getInnerPc());
					}
					pnlDrawing.getShapes().set(pnlDrawing.getShapes().indexOf(selected), r);
				}
			}else if(selected instanceof Donut){
				Donut d= (Donut) selected;
				DlgDonut dlg = new DlgDonut();
				dlg.getTxtX().setText("" + d.getCenter().getX());
				dlg.getTxtY().setText("" + d.getCenter().getY());
				dlg.getTxtRadius().setText(""+d.getRadius());
				dlg.getTxtInnerRadius().setText(""+d.getInnerRadius());
				dlg.setPc(d.getColor());
				dlg.setInnerPc(d.getInnerColor());
				dlg.setModal(true);
				dlg.setVisible(true);
				if(dlg.isOK()){
					x=Integer.parseInt(dlg.getTxtX().getText());
					y=Integer.parseInt(dlg.getTxtY().getText());
					int r=Integer.parseInt(dlg.getTxtRadius().getText());
					int iR=Integer.parseInt(dlg.getTxtInnerRadius().getText());
					Point p=new Point(x,y);
					d=new Donut(p,r,iR);
					if(dlg.isColorChosen()){
						d.setColor(dlg.getC());
					}else{
						d.setColor(dlg.getPc());
					}
					if(dlg.isInnerColorChosen()){
						d.setInnerColor(dlg.getInnerC());
					}else{
						d.setInnerColor(dlg.getInnerPc());
					}
					pnlDrawing.getShapes().set(pnlDrawing.getShapes().indexOf(selected), d);
				}
			} else if(selected instanceof Circle){
				Circle c= (Circle) selected;
				DlgCircle dlg= new DlgCircle();
				dlg.getTxtX().setText("" + c.getCenter().getX());
				dlg.getTxtY().setText("" + c.getCenter().getY());
				dlg.getTxtRadius().setText(""+c.getRadius());
				dlg.setPc(c.getColor());
				dlg.setInnerPc(c.getInnerColor());
				dlg.setModal(true);
				dlg.setVisible(true);
				if(dlg.isOK()){
					x=Integer.parseInt(dlg.getTxtX().getText());
					y=Integer.parseInt(dlg.getTxtY().getText());
					int r=Integer.parseInt(dlg.getTxtRadius().getText());
					Point p=new Point(x,y);
					c=new Circle(p,r);
					if(dlg.isColorChosen()){
						c.setColor(dlg.getC());
					}else{
						c.setColor(dlg.getPc());
					}
					if(dlg.isInnerColorChosen()){
						c.setInnerColor(dlg.getInnerC());
					}else{
						c.setInnerColor(dlg.getInnerPc());
					}
					pnlDrawing.getShapes().set(pnlDrawing.getShapes().indexOf(selected), c);
				}
			} 	
			pnlDrawing.repaint();
			}
		}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}

	public JToggleButton getTglbtnSelection() {
		return tglbtnSelection;
	}

	public void setTglbtnSelection(JToggleButton tglbtnSelection) {
		this.tglbtnSelection = tglbtnSelection;
	}

}
