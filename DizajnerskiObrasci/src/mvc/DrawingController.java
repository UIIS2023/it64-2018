package mvc;
import command.*;
import geometry.*;
import gui.*;
import observer.SelectedSizeObserver;
import strategy.DrawingFile;
import strategy.FileStrategy;
import strategy.LogFile;
import java.awt.event.MouseEvent;
import java.util.ListIterator;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import adapter.HexagonAdapter;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private Point startPoint;
	private Stack<Command> cmdHistory = new Stack<Command>();
	private Stack<Command> cmdUndoHistory = new Stack<Command>();
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		SelectedSizeObserver selectedSizeObserver = new SelectedSizeObserver(frame);
		model.addPropertyChangeListener(selectedSizeObserver);
	}

	public void mouseClicked(MouseEvent e) {
		Shape newShape=null;
		int v1=0,v2=0;
		if (frame.getTglbtnSelection().isSelected()){
			//selected=null;
			ListIterator<Shape> it=model.getShapes().listIterator(model.getShapes().size());
			while (it.hasPrevious()){
				Shape shape = it.previous();
				if(shape.contains(e.getX(), e.getY())) {
					if (!shape.isSelected()) {
						Command cmd = new SelectCmd(shape, model);
						cmd.execute();
						pushCmdHistory(cmd);
						frame.getTxtAreaLog().append("Select: " + shape.toString() + "\n");
					} else if (shape.isSelected()) {
						Command cmd = new UnselectCmd(shape, model);
						cmd.execute();
						pushCmdHistory(cmd);
						frame.getTxtAreaLog().append("Unselect: " + shape.toString() + "\n");
					}
					break;
				}
			}
		} else if (frame.getTglbtnPoint().isSelected()){
				Point p = new Point(e.getX(), e.getY());
				p.setColor(frame.getBtnColor().getBackground());
				newShape = p;
		} else if (frame.getTglbtnLine().isSelected()){
			if (startPoint==null) {
				startPoint = new Point (e.getX(), e.getY());
			} else {
				Line l = new Line(startPoint, new Point(e.getX(),e.getY()));
				l.setColor(frame.getBtnColor().getBackground());
				newShape = l;
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
				r = new Rectangle(new Point(e.getX(),e.getY()), v1, v2);
				if(dlg.isColorChosen())
					r.setColor(dlg.getC());
				else
					r.setColor(frame.getBtnColor().getBackground());
				if(dlg.isInnerColorChosen())
					r.setInnerColor(dlg.getInnerC());
				else
					r.setInnerColor(frame.getBtnInnerColor().getBackground());
				try {
					newShape = r;
				} catch (Exception ex){
					JOptionPane.showMessageDialog(frame, "Wrong data type.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		} else if (frame.getTglbtnCircle().isSelected()){
			DlgCircleHexagon dlg= new DlgCircleHexagon();
			Circle c= new Circle();
			dlg.setModal(true);
			dlg.getTxtX().setText("" + e.getX());
			dlg.getTxtY().setText("" + e.getY());
			dlg.setVisible(true);
			if(dlg.isOK()){
				v1=Integer.parseInt(dlg.getTxtRadius().getText());
				c = new Circle(new Point(e.getX(),e.getY()), v1);
				if(dlg.isColorChosen())
					c.setColor(dlg.getC());
				else
					c.setColor(frame.getBtnColor().getBackground());
				if(dlg.isInnerColorChosen())
					c.setInnerColor(dlg.getInnerC());
				else
					c.setInnerColor(frame.getBtnInnerColor().getBackground());
				try {
					newShape= c;
				} catch (Exception ex){
					JOptionPane.showMessageDialog(frame, "Wrong data type.", "Error", JOptionPane.ERROR_MESSAGE);
				}
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
				d = new Donut(new Point(e.getX(),e.getY()), v1,v2);
				if(dlg.isColorChosen())
					d.setColor(dlg.getC());
				else
					d.setColor(frame.getBtnColor().getBackground());
				if(dlg.isInnerColorChosen())
					d.setInnerColor(dlg.getInnerC());
				else
					d.setInnerColor(frame.getBtnInnerColor().getBackground());
				try {
					newShape= d;
				} catch (Exception ex){
					JOptionPane.showMessageDialog(frame, "Wrong data type.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		} else if (frame.getTglbtnHexagon().isSelected()) {
			DlgCircleHexagon dlg= new DlgCircleHexagon();
			HexagonAdapter h= new HexagonAdapter();
			dlg.setModal(true);
			dlg.getTxtX().setText("" + e.getX());
			dlg.getTxtY().setText("" + e.getY());
			dlg.setVisible(true);
			if(dlg.isOK()){
				v1=Integer.parseInt(dlg.getTxtRadius().getText());
				h = new HexagonAdapter(new Point(e.getX(),e.getY()), v1);
				if(dlg.isColorChosen())
					h.getHexagon().setBorderColor(dlg.getC());
				else
					h.getHexagon().setBorderColor(frame.getBtnColor().getBackground());
				if(dlg.isInnerColorChosen())
					h.getHexagon().setAreaColor(dlg.getInnerC());
				else
					h.getHexagon().setAreaColor(frame.getBtnInnerColor().getBackground());
				try {
					newShape= h;
				} catch (Exception ex){
					JOptionPane.showMessageDialog(frame, "Wrong data type.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
		if (newShape!=null) {
			Command cmd = new AddShapeCmd(newShape, model);
			cmd.execute();
			pushCmdHistory(cmd);
			frame.getTxtAreaLog().append("Add: " + newShape.toString() + "\n");
		}
		if(!getCmdHistory().isEmpty()) {
			frame.getBtnUndo().setEnabled(true);
		}
		checkPosition();
		disableRedo();
		frame.repaint();
	}
	
	protected void delete() {
		if (model.getSelectedShapes().size() != 0) {
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning message",
					JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				frame.getTxtAreaLog().append("Delete: " + model.getSelectedShapes().toString() + "\n");
				Command cmd = new RemoveShapesCmd(model.getSelectedShapes(), model);
				cmd.execute();
				pushCmdHistory(cmd);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You have not selected any shape!","Error", JOptionPane.WARNING_MESSAGE);
		}
		checkPosition();
		disableRedo();
		frame.repaint();
	}
	
	protected void modify(){
		int x=0,y=0;
		Command cmd;
		if (!model.getSelectedShapes().isEmpty()) {
			Shape selected = model.getSelectedShapes().get(0);
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
					if(dlg.isColorChosen()){
						p.setColor(dlg.getC());
					}else{
						p.setColor(dlg.getPc());
					}
					frame.getTxtAreaLog().append("Modify: " + ((Point)selected).toString() + " To: " + p.toString() + "\n");
					cmd = new UpdatePointCmd((Point)selected,p);
					cmd.execute();
					pushCmdHistory(cmd);
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
					if(dlg.isColorChosen()){
						l.setColor(dlg.getC());
					}else{
						l.setColor(dlg.getPc());
					}
					frame.getTxtAreaLog().append("Modify: " + ((Line)selected).toString() + " To: " + l.toString() + "\n");
					cmd = new UpdateLineCmd((Line)selected,l);
					cmd.execute();
					pushCmdHistory(cmd);
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
					frame.getTxtAreaLog().append("Modify: " + ((Rectangle)selected).toString() + " To: " + r.toString() + "\n");
					cmd = new UpdateRectangleCmd((Rectangle)selected,r);
					cmd.execute(); 
					pushCmdHistory(cmd);
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
					frame.getTxtAreaLog().append("Modify: " + ((Donut)selected).toString() + " To: " + d.toString() + "\n");
					cmd = new UpdateDonutCmd((Donut)selected,d);
					cmd.execute(); 
					pushCmdHistory(cmd);
				}
			} else if(selected instanceof Circle){
				Circle c= (Circle) selected;
				DlgCircleHexagon dlg= new DlgCircleHexagon();
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
					frame.getTxtAreaLog().append("Modify: " + ((Circle)selected).toString() + " To: " + c.toString() + "\n");
					cmd = new UpdateCircleCmd((Circle)selected,c);
					cmd.execute(); 
					pushCmdHistory(cmd);
				}
			} else if(selected instanceof HexagonAdapter){
				HexagonAdapter h= (HexagonAdapter) selected;
				DlgCircleHexagon dlg= new DlgCircleHexagon();
				dlg.getTxtX().setText("" + h.getHexagon().getX());
				dlg.getTxtY().setText("" + h.getHexagon().getY());
				dlg.getTxtRadius().setText(""+h.getHexagon().getR());
				dlg.setPc(h.getHexagon().getBorderColor());
				dlg.setInnerPc(h.getHexagon().getAreaColor());
				dlg.setModal(true);
				dlg.setVisible(true);
				if(dlg.isOK()){
					x=Integer.parseInt(dlg.getTxtX().getText());
					y=Integer.parseInt(dlg.getTxtY().getText());
					int r=Integer.parseInt(dlg.getTxtRadius().getText());
					Point p=new Point(x,y);
					h=new HexagonAdapter(p,r);
					if(dlg.isColorChosen()){
						h.getHexagon().setBorderColor(dlg.getC());
					}else{
						h.getHexagon().setBorderColor(dlg.getPc());
					}
					if(dlg.isInnerColorChosen()){
						h.getHexagon().setAreaColor(dlg.getInnerC());
					}else{
						h.getHexagon().setAreaColor(dlg.getInnerPc());
					}
					h.getHexagon().setSelected(true);
					frame.getTxtAreaLog().append("Modify: " + ((HexagonAdapter)selected).toString() + " To: " + h.toString() + "\n");
					cmd = new UpdateHexagonCmd((HexagonAdapter)selected,h);
					cmd.execute(); 
					pushCmdHistory(cmd);
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "You have not selected any shape!","Error", JOptionPane.WARNING_MESSAGE);
		}

		disableRedo();
		frame.repaint();
	}
	
	public void undo() {
		if(!getCmdHistory().isEmpty()) {
			frame.getTxtAreaLog().append("Undo-> ");
			Command cmd = popCmdHistory();
			cmd.unexecute();
			printCmd(cmd);
			pushCmdUndoHistory(cmd);
			frame.getBtnRedo().setEnabled(true);
			if(getCmdHistory().isEmpty()) {
				frame.getBtnUndo().setEnabled(false);
			}
		}
		checkPosition();
		frame.repaint();
	}
	
	public void redo() {
		if(!getCmdUndoHistory().isEmpty()) {
			frame.getTxtAreaLog().append("Redo-> ");
			Command cmd = popCmdUndoHistory();
			cmd.execute();
			printCmd(cmd);
			pushCmdHistory(cmd);
			frame.getBtnUndo().setEnabled(true);
			if(getCmdUndoHistory().isEmpty()) {
				frame.getBtnRedo().setEnabled(false);
			}
		}
		checkPosition();
		frame.repaint();
	}
	
	public void printCmd(Command cmd) {
		if(cmd instanceof AddShapeCmd)
			frame.getTxtAreaLog().append("Add\n");
		else if (cmd instanceof RemoveShapesCmd)
			frame.getTxtAreaLog().append("Delete\n");
		else if (cmd instanceof SelectCmd)
			frame.getTxtAreaLog().append("Select\n");
		else if (cmd instanceof UnselectCmd)
			frame.getTxtAreaLog().append("Unselect\n");
		else if (cmd instanceof BringToBackCmd)
			frame.getTxtAreaLog().append("BringToBack\n");
		else if (cmd instanceof BringToFrontCmd)
			frame.getTxtAreaLog().append("BringToFront\n");
		else if (cmd instanceof ToBackCmd)
			frame.getTxtAreaLog().append("ToBack\n");
		else if (cmd instanceof ToFrontCmd)
			frame.getTxtAreaLog().append("ToFront\n");
		else
			frame.getTxtAreaLog().append("Modify\n");
	}
	
	public void disableRedo() {
		getCmdUndoHistory().clear();
		frame.getBtnRedo().setEnabled(false);
	}
	
	public void checkPosition() {
		if(model.getSelectedShapes().size()==1) {
			int i = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			if (model.getShapes().size()==1) {
				frame.getBtnToFront().setEnabled(false);
				frame.getBtnToBack().setEnabled(false);
				frame.getBtnBringToFront().setEnabled(false);
				frame.getBtnBringToBack().setEnabled(false);
			} else if(i+1 == model.getShapes().size()) {
				frame.getBtnToFront().setEnabled(false);
				frame.getBtnToBack().setEnabled(true);
				frame.getBtnBringToFront().setEnabled(false);
				frame.getBtnBringToBack().setEnabled(true);
			} else if (i == 0) {
				frame.getBtnToFront().setEnabled(true);
				frame.getBtnToBack().setEnabled(false);
				frame.getBtnBringToFront().setEnabled(true);
				frame.getBtnBringToBack().setEnabled(false);
			} else if (i>0 && i+1<model.getShapes().size()) {
				frame.getBtnToFront().setEnabled(true);
				frame.getBtnToBack().setEnabled(true);
				frame.getBtnBringToFront().setEnabled(true);
				frame.getBtnBringToBack().setEnabled(true);
			}
		} else {
			frame.getBtnToFront().setEnabled(false);
			frame.getBtnToBack().setEnabled(false);
			frame.getBtnBringToFront().setEnabled(false);
			frame.getBtnBringToBack().setEnabled(false);
		}
	}
	
	public void toFront() {
		Shape s = model.getSelectedShapes().get(0);
		Command cmd = new ToFrontCmd(s, model);
		cmd.execute();
		pushCmdHistory(cmd);
		frame.getTxtAreaLog().append("ToFront: " + s.toString() + "\n");
		checkPosition();
		disableRedo();
		frame.repaint();
	}
	
	public void toBack() {
		Shape s = model.getSelectedShapes().get(0);
		Command cmd = new ToBackCmd(s, model);
		cmd.execute();
		pushCmdHistory(cmd);
		frame.getTxtAreaLog().append("ToBack: " + s.toString() + "\n");
		checkPosition();
		disableRedo();
		frame.repaint();
	}
	
	public void bringToFront() {
		Shape s = model.getSelectedShapes().get(0);
		Command cmd = new BringToFrontCmd(s, model);
		cmd.execute();
		pushCmdHistory(cmd);
		frame.getTxtAreaLog().append("BringToFront: " + s.toString() + "\n");
		checkPosition();
		disableRedo();
		frame.repaint();
	}
	
	public void bringToBack () {
		Shape s = model.getSelectedShapes().get(0);
		Command cmd = new BringToBackCmd(s, model);
		cmd.execute();
		pushCmdHistory(cmd);
		frame.getTxtAreaLog().append("BringToBack: " + s.toString() + "\n");
		checkPosition();
		disableRedo();
		frame.repaint();
	}

	public void saveDrawing() {
		JFileChooser jfcSaveDrawing = new JFileChooser();
		int userSelection = jfcSaveDrawing.showSaveDialog(frame);
		
		if(userSelection == JFileChooser.APPROVE_OPTION) {
			String path = jfcSaveDrawing.getSelectedFile().getPath();
			FileStrategy strategy = new DrawingFile(model);
			strategy.save(path);
		}
	}

	public void loadDrawing() {
		JFileChooser jfcLoadDrawing = new JFileChooser();
		int userSelection = jfcLoadDrawing.showOpenDialog(frame);
		
		if(userSelection == JFileChooser.APPROVE_OPTION) {
			frame.getTxtAreaLog().setText("");
			model.getShapes().clear();
			model.getSelectedShapes().clear();
			getCmdHistory().clear();
			getCmdUndoHistory().clear();
			String path = jfcLoadDrawing.getSelectedFile().getPath();
			FileStrategy strategy = new DrawingFile(model);
			strategy.load(path);
			frame.repaint();
		}		
	}

	public void saveLog() {
		JFileChooser jfcSaveLog = new JFileChooser();
		int userSelection = jfcSaveLog.showSaveDialog(frame);
		
		if(userSelection == JFileChooser.APPROVE_OPTION) {
			String path = jfcSaveLog.getSelectedFile().getPath();
			FileStrategy strategy = new LogFile(model, frame, this);
			strategy.save(path);
		}
		
	}

	public void loadLog() {
		JFileChooser jfcLoadLog = new JFileChooser();
		int userSelection = jfcLoadLog.showOpenDialog(frame);
		
		if(userSelection == JFileChooser.APPROVE_OPTION) {
			frame.getTxtAreaLog().setText("");
			model.getShapes().clear();
			model.getSelectedShapes().clear();
			getCmdHistory().clear();
			getCmdUndoHistory().clear();
			String path = jfcLoadLog.getSelectedFile().getPath();
			FileStrategy strategy = new LogFile(model, frame, this);
			strategy.load(path);
			frame.repaint();
		}
	}
	
	/*CmdHistory*/
	public Command popCmdHistory() {
		return cmdHistory.pop();
	}
	
	public void pushCmdHistory(Command c) {
		cmdHistory.add(c);
	}
	
	public Stack<Command> getCmdHistory() {
		return cmdHistory;
	}
	
	/*CmdUndoHistory*/
	public Command popCmdUndoHistory() {
		return cmdUndoHistory.pop();
	}
	
	public void pushCmdUndoHistory(Command c) {
		cmdUndoHistory.add(c);
	}
	
	public Stack<Command> getCmdUndoHistory() {
		return cmdUndoHistory;
	}
}
