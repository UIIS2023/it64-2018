package command;

import geometry.Shape;
import mvc.DrawingModel;

public class UnselectCmd implements Command {
	private Shape shape;
	private DrawingModel model;
	
	public UnselectCmd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		shape.setSelected(false);
		model.removeSelected(shape);
	}

	@Override
	public void unexecute() {
		shape.setSelected(true);
		model.addSelected(shape);
	}

}
