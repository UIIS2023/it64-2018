package command;

import geometry.Shape;
import mvc.DrawingModel;

public class SelectCmd implements Command {
	private Shape shape;
	private DrawingModel model;
	
	public SelectCmd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		shape.setSelected(true);
		model.addSelected(shape);
	}

	@Override
	public void unexecute() {
		shape.setSelected(false);
		model.removeSelected(shape);
	}

}
