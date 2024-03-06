package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command {
	private Shape shape;
	private DrawingModel model;
	private int i;
	
	public BringToFrontCmd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
		this.i = model.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		model.getShapes().remove(i);
		model.getShapes().add(shape);
	}

	@Override
	public void unexecute() {
		model.getShapes().remove(model.getShapes().size()-1);
		model.getShapes().add(i, shape);
	}
}
