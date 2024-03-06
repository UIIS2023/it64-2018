package command;

import geometry.Shape;
import mvc.DrawingModel;

public class ToFrontCmd implements Command {
	private Shape shape;
	private DrawingModel model;
	private int i;
	
	public ToFrontCmd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
		this.i = model.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		model.getShapes().remove(i);
		model.getShapes().add(i + 1, shape);
	}

	@Override
	public void unexecute() {
		model.getShapes().remove(i + 1);
		model.getShapes().add(i, shape);
	}

}
