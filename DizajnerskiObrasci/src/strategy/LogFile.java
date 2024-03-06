package strategy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import adapter.HexagonAdapter;
import command.*;
import geometry.*;
import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class LogFile implements FileStrategy {
	DrawingModel model;
	DrawingFrame frame;
	DrawingController controller;

	public LogFile(DrawingModel model, DrawingFrame frame, DrawingController controller) {
		this.model = model;
		this.frame = frame;
		this.controller = controller;
	}

	@Override
	public void save(String path) {
		try {
			PrintWriter print = new PrintWriter(path);
			print.write(frame.getTxtAreaLog().getText());

			print.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load(String path) {
		Scanner file = null;
		try {
			file = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Object[] options = { "Whole", "Step by step" };
		int n = JOptionPane.showOptionDialog(frame, "Do you want whole or step by step log preview?", "Question",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

		if (n == 0) {
			while (file.hasNextLine()) {
				String line = file.nextLine();
				executeLine(line);
			}
			file.close();
		} else if (n == 1) {
			Scanner file2=file;
			frame.getTxtAreaLog().append("Click NEXT for the first step!\n");
			frame.getBtnNext().setEnabled(true);
			frame.getBtnNext().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent a) {
					try {
						executeLine(file2.nextLine());
						if (!file2.hasNextLine()) {
							frame.getBtnNext().setEnabled(false);
							file2.close();
						}
					} catch (IllegalStateException e) {
						 e.printStackTrace();
					}
				}
			});
		}
	}

	public void executeLine(String line) {
		String[] splitLine = line.split(" ");
		Command cmd = null;

		if (splitLine[0].equals("Add:")) {
			Shape shape = getShape(line, splitLine[1], false);
			if (shape != null) {
				cmd = new AddShapeCmd(shape, model);
				cmd.execute();
				controller.pushCmdHistory(cmd);
				frame.getTxtAreaLog().append("Add: " + shape.toString() + "\n");
			}
			controller.checkPosition();
			controller.disableRedo();
			frame.repaint();

		} else if (splitLine[0].equals("Delete:")) {
			frame.getTxtAreaLog().append("Delete: " + model.getSelectedShapes().toString() + "\n");
			cmd = new RemoveShapesCmd(model.getSelectedShapes(), model);
			cmd.execute();
			controller.pushCmdHistory(cmd);
			controller.checkPosition();
			controller.disableRedo();
			frame.repaint();

		} else if (splitLine[0].equals("Modify:")) {
			Shape oldShape = getShape(line, splitLine[1], false);
			oldShape.setSelected(true);
			Shape newShape = getShape(line, splitLine[1], true);
			for (Shape s : model.getShapes())
				if (oldShape.equals(s))
					oldShape = s;

			if (splitLine[1].equals("Point"))
				cmd = new UpdatePointCmd((Point) oldShape, (Point) newShape);
			else if (splitLine[1].equals("Line"))
				cmd = new UpdateLineCmd((Line) oldShape, (Line) newShape);
			else if (splitLine[1].equals("Rectangle"))
				cmd = new UpdateRectangleCmd((Rectangle) oldShape, (Rectangle) newShape);
			else if (splitLine[1].equals("Circle"))
				cmd = new UpdateCircleCmd((Circle) oldShape, (Circle) newShape);
			else if (splitLine[1].equals("Donut"))
				cmd = new UpdateDonutCmd((Donut) oldShape, (Donut) newShape);
			else if (splitLine[1].equals("Hexagon"))
				cmd = new UpdateHexagonCmd((HexagonAdapter) oldShape, (HexagonAdapter) newShape);
			frame.getTxtAreaLog().append("Modify: " + oldShape.toString() + " To: " + newShape.toString() + "\n");
			cmd.execute();
			controller.pushCmdHistory(cmd);
			controller.disableRedo();
			frame.repaint();

		} else if (splitLine[0].equals("Select:")) {
			Shape shape = getShape(line, splitLine[1], false);
			for (Shape s : model.getShapes())
				if (shape.equals(s)) {
					shape = s;
				}
			cmd = new SelectCmd(shape, model);
			cmd.execute();

			frame.getTxtAreaLog().append("Select: " + shape.toString() + "\n");
			controller.pushCmdHistory(cmd);
			controller.checkPosition();
			controller.disableRedo();
			frame.repaint();

		} else if (splitLine[0].equals("Unselect:")) {
			Shape shape = getShape(line, splitLine[1], false);
			shape.setSelected(true); // zato sto pravi novi koji ima default-nu vrednost za selected false, a to nam
										// se ne slaze za sledeci korak - proveru
			for (Shape s : model.getShapes())
				if (shape.equals(s))
					shape = s;
			cmd = new UnselectCmd(shape, model);
			cmd.execute();

			frame.getTxtAreaLog().append("Unselect: " + shape.toString() + "\n");
			controller.pushCmdHistory(cmd);
			controller.checkPosition();
			controller.disableRedo();
			frame.repaint();

		} else if (splitLine[0].equals("BringToBack:")) {
			controller.bringToBack();
		} else if (splitLine[0].equals("BringToFront:")) {
			controller.bringToFront();
		} else if (splitLine[0].equals("ToBack:")) {
			controller.toBack();
		} else if (splitLine[0].equals("ToFront:")) {
			controller.toFront();
		} else if (splitLine[0].equals("Undo->")) {
			controller.undo();
		} else if (splitLine[0].equals("Redo->")) {
			controller.redo();
		}
	}

	private Shape getShape(String line, String stringShape, Boolean second) {
		String numbersOnly = line.replaceAll("[^0-9]", " ");
		String[] numbersWithBlank = numbersOnly.split(" ");
		ArrayList<Integer> num = new ArrayList<Integer>();
		for (String s : numbersWithBlank) {
			if (s.isBlank())
				continue;
			num.add(Integer.parseInt(s));
		}
		Shape shape = null;
		int pos = 0;
		if (second) {
			if (stringShape.equals("Point"))
				pos = 5;
			else if (stringShape.equals("Line"))
				pos = 7;
			else if (stringShape.equals("Circle") || stringShape.equals("Hexagon"))
				pos = 9;
			else if (stringShape.equals("Rectangle") || stringShape.equals("Donut"))
				pos = 10;
		}

		if (stringShape.equals("Point")) {
			Point p = new Point(num.get(0 + pos), num.get(1 + pos));
			p.setColor(new Color(num.get(2 + pos), num.get(3 + pos), num.get(4 + pos)));
			shape = p;

		} else if (stringShape.equals("Line")) {
			Line l = new Line(new Point(num.get(0 + pos), num.get(1 + pos)),
					new Point(num.get(2 + pos), num.get(3 + pos)));
			l.setColor(new Color(num.get(4 + pos), num.get(5 + pos), num.get(6 + pos)));
			shape = l;

		} else if (stringShape.equals("Rectangle")) {
			Rectangle r = new Rectangle(new Point(num.get(0 + pos), num.get(1 + pos)), num.get(2 + pos),
					num.get(3 + pos));
			r.setColor(new Color(num.get(4 + pos), num.get(5 + pos), num.get(6 + pos)));
			r.setInnerColor(new Color(num.get(7 + pos), num.get(8 + pos), num.get(9 + pos)));
			shape = r;

		} else if (stringShape.equals("Circle")) {
			Circle c = new Circle(new Point(num.get(0 + pos), num.get(1 + pos)), num.get(2 + pos));
			c.setColor(new Color(num.get(3 + pos), num.get(4 + pos), num.get(5 + pos)));
			c.setInnerColor(new Color(num.get(6 + pos), num.get(7 + pos), num.get(8 + pos)));
			shape = c;

		} else if (stringShape.equals("Donut")) {
			Donut d = new Donut(new Point(num.get(0 + pos), num.get(1 + pos)), num.get(2 + pos), num.get(3 + pos));
			d.setColor(new Color(num.get(4 + pos), num.get(5 + pos), num.get(6 + pos)));
			d.setInnerColor(new Color(num.get(7 + pos), num.get(8 + pos), num.get(9 + pos)));
			shape = d;

		} else if (stringShape.equals("Hexagon")) {
			HexagonAdapter h = new HexagonAdapter(new Point(num.get(0 + pos), num.get(1 + pos)), num.get(2 + pos));
			h.getHexagon().setBorderColor(new Color(num.get(3 + pos), num.get(4 + pos), num.get(5 + pos)));
			h.getHexagon().setAreaColor(new Color(num.get(6 + pos), num.get(7 + pos), num.get(8 + pos)));
			shape = h;
		}
		return shape;
	}

}
