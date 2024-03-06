package command;

import java.util.ArrayList;
import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapesCmd implements Command {
	private ArrayList<Shape> shapes;
	private DrawingModel model;
	private ArrayList<Shape> tempList = new ArrayList<Shape>();

	public RemoveShapesCmd(ArrayList<Shape> shapes, DrawingModel model) {
		this.shapes = new ArrayList<Shape>(shapes);
		this.model = model;
	}

	@Override
	public void execute() {
		tempList.addAll(model.getShapes());
		for (Shape s : shapes) {
			model.remove(s);
			model.removeSelected(s);
		}
	}

	@Override
	public void unexecute() {
		model.getShapes().clear();
		model.getShapes().addAll(tempList);
		for (Shape s : shapes) {
			model.addSelected(s);
		}	
	}
}
