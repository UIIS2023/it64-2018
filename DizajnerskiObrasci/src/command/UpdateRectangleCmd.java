package command;

import geometry.Rectangle;

public class UpdateRectangleCmd implements Command {
	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle original = new Rectangle();
	
	public UpdateRectangleCmd(Rectangle oldState, Rectangle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		
		oldState.getUpperLeft().setX(newState.getUpperLeft().getX());
		oldState.getUpperLeft().setY(newState.getUpperLeft().getY());
		oldState.setHeight(newState.getHeight());
		oldState.setWidth(newState.getWidth());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
	}

	@Override
	public void unexecute() {
		oldState.getUpperLeft().setX(original.getUpperLeft().getX());
		oldState.getUpperLeft().setY(original.getUpperLeft().getY());
		oldState.setHeight(original.getHeight());
		oldState.setWidth(original.getWidth());
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());
	}

}
