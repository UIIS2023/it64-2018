package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import mvc.DrawingFrame;

public class SelectedSizeObserver implements PropertyChangeListener {
	private int size;
	private DrawingFrame frame;
	
	public SelectedSizeObserver(DrawingFrame frame) {
		this.frame = frame;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("selected")) {
			this.size = (int) evt.getNewValue();
			changeEnabled();
		}
	}
	
	public void changeEnabled() {
		if (size==0) {
			frame.getBtnModification().setEnabled(false);
			frame.getBtnDelete().setEnabled(false);
		} else if (size==1) {
			frame.getBtnModification().setEnabled(true);
			frame.getBtnDelete().setEnabled(true);
		} else {
			frame.getBtnModification().setEnabled(false);
			frame.getBtnDelete().setEnabled(true);
		}		
	}
	
}
