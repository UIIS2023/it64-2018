package strategy;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import geometry.Shape;
import mvc.DrawingModel;

public class DrawingFile implements FileStrategy {
	DrawingModel model;	
	
	public DrawingFile(DrawingModel model) {
		this.model = model;
	}

	@Override
	public void save(String path) {
		try {
			FileOutputStream file = new FileOutputStream(path);
			ObjectOutputStream obj = new ObjectOutputStream(file);
			obj.writeObject(model.getShapes());
			
			obj.close();
			file.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load(String path) {
		try {
			FileInputStream file = new FileInputStream(path);
			ObjectInputStream obj = new ObjectInputStream(file);
			ArrayList<Shape> shapes = new ArrayList<Shape>();
			shapes=(ArrayList<Shape>)obj.readObject();
			model.getShapes().addAll(shapes);

			obj.close();
			file.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}

}
