package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command {
	private Shape shape;
	private DrawingModel model;
	private int i;
	
	public BringToBackCmd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
		this.i = model.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		model.getShapes().remove(i);
		model.getShapes().add(0, shape);
	}

	@Override
	public void unexecute() {
		model.getShapes().remove(0);
		model.getShapes().add(i, shape);
	}

}
