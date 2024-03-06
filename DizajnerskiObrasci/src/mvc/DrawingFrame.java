package mvc;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;

public class DrawingFrame extends JFrame {
	private DrawingView view = new DrawingView();
	private DrawingController controller;

	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnLine = new JToggleButton("Line");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnDonut = new JToggleButton("Donut");
	private JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");
	private JToggleButton tglbtnSelection = new JToggleButton("Selection");
	private JButton btnModification = new JButton("Modification");
	private JButton btnDelete = new JButton("Delete");
	private JButton btnUndo = new JButton("Undo");
	private JButton btnRedo = new JButton("Redo");
	private JButton btnToFront = new JButton("ToFront");
	private JButton btnToBack = new JButton("ToBack");
	private JButton btnBringToFront = new JButton("BringToFront");
	private JButton btnBringToBack = new JButton("BringToBack");
	private JButton btnColor = new JButton("");
	private JButton btnInnerColor = new JButton("");
	private final JLabel label_9_1 = new JLabel("");
	private final JLabel label_10 = new JLabel("");
	private final JLabel label_11 = new JLabel("");
	private final JLabel label_12 = new JLabel("");
	private final JLabel label_13 = new JLabel("");
	private final JLabel label_14 = new JLabel("");
	private final JLabel label_15 = new JLabel("");
	private final JLabel label_16 = new JLabel("");
	private final JLabel label_17 = new JLabel("");
	private final JLabel label_18 = new JLabel("");
	private final JTextArea txtAreaLog = new JTextArea();
	private final JScrollPane scrollPane = new JScrollPane(txtAreaLog);
	private final JButton btnSaveDrawing = new JButton("SaveDrawing");
	private final JButton btnLoadDrawing = new JButton("LoadDrawing");
	private final JButton btnSaveLog = new JButton("SaveLog");
	private final JButton btnLoadLog = new JButton("LoadLog");
	private final JButton btnNext = new JButton("Next");
	
	public DrawingFrame() {
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		getContentPane().add(view, BorderLayout.CENTER);
		view.setBorder(new EmptyBorder(5, 5, 5, 5));
		view.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1000, 700);
		setTitle("IT 64-2018 Jovana Ostojic");
		
		JPanel pnlNorth = new JPanel();
		getContentPane().add(pnlNorth, BorderLayout.NORTH);
		JPanel pnlEast = new JPanel();
		getContentPane().add(pnlEast, BorderLayout.EAST);
		JPanel pnlWest = new JPanel();
		getContentPane().add(pnlWest, BorderLayout.WEST);
		JPanel pnlSouth = new JPanel();
		getContentPane().add(pnlSouth, BorderLayout.SOUTH);
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		btnModification.setEnabled(false);
		btnModification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.modify();
			}
		});
		
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.delete();
			}
		});
		pnlNorth.setLayout(new GridLayout(0, 7, 5, 0));
		
		JLabel label = new JLabel("");
		JLabel label_1 = new JLabel("");
		JLabel label_2 = new JLabel("");
		JLabel label_3 = new JLabel("");
		JLabel label_4 = new JLabel("");
		JLabel label_5 = new JLabel("");
		JLabel label_6 = new JLabel("");
		JLabel innerColor = new JLabel("InnerColor");
		innerColor.setHorizontalAlignment(SwingConstants.RIGHT);
		innerColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel color = new JLabel("Color");
		color.setFont(new Font("Tahoma", Font.PLAIN, 14));
		color.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_9 = new JLabel("");
		
		pnlNorth.add(label_6);
		pnlNorth.add(tglbtnPoint);
		pnlNorth.add(tglbtnLine);
		pnlNorth.add(tglbtnRectangle);
		pnlNorth.add(color);
		pnlNorth.add(btnColor);
		pnlNorth.add(label_9);
		pnlNorth.add(label_9_1);
		pnlNorth.add(tglbtnCircle);
		pnlNorth.add(tglbtnDonut);	
		pnlNorth.add(tglbtnHexagon);
		pnlNorth.add(innerColor);
		pnlNorth.add(btnInnerColor);
		
		pnlEast.setLayout(new GridLayout(0, 1, 0, 0));
		pnlEast.add(label_10);
		pnlEast.add(label_11);
		pnlEast.add(label_12);
		pnlEast.add(label_13);
		pnlEast.add(tglbtnSelection);
		pnlEast.add(btnModification);
		pnlEast.add(btnDelete);
		pnlEast.add(label_14);	
		pnlEast.add(label_15);
		pnlEast.add(label_16);
		pnlEast.add(label_17);
		pnlEast.add(label_18);
		
		pnlWest.setLayout(new GridLayout(0, 1, 0, 0));
		pnlWest.add(label);
		pnlWest.add(label_1);
		pnlWest.add(btnUndo);
		pnlWest.add(btnRedo);
		pnlWest.add(label_2);
		pnlWest.add(btnToFront);
		pnlWest.add(btnToBack);
		pnlWest.add(label_3);
		pnlWest.add(btnBringToFront);
		pnlWest.add(btnBringToBack);
		pnlWest.add(label_4);
		pnlWest.add(label_5);
		
		btnGroup.add(tglbtnPoint);
		btnGroup.add(tglbtnLine);
		btnGroup.add(tglbtnRectangle);
		btnGroup.add(tglbtnCircle);
		btnGroup.add(tglbtnDonut);
		btnGroup.add(tglbtnHexagon);
		btnGroup.add(tglbtnSelection);
		pnlSouth.setLayout(new BoxLayout(pnlSouth, BoxLayout.Y_AXIS));
		
		JPanel pnlTop = new JPanel();
		JPanel pnlBottom = new JPanel();
		pnlSouth.add(pnlTop);
		pnlSouth.add(pnlBottom);
		
		pnlTop.add(btnSaveDrawing);
		pnlTop.add(btnLoadDrawing);	
		pnlTop.add(btnSaveLog);	
		pnlTop.add(btnLoadLog);	
		btnNext.setEnabled(false);
		pnlTop.add(btnNext);		
		
		pnlBottom.add(scrollPane);
		
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JColorChooser();
				btnColor.setBackground(JColorChooser.showDialog(null, "Choose color", null));
			}
		});
		btnColor.setBackground(Color.BLACK);
		
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JColorChooser();
				btnInnerColor.setBackground(JColorChooser.showDialog(null, "Choose color", null));
			}
		});
		btnInnerColor.setBackground(Color.WHITE);
		
		btnUndo.setEnabled(false);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.undo();
			}
		});
		
		btnRedo.setEnabled(false);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.redo();
			}
		});
		
		btnToFront.setEnabled(false);
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		
		btnToBack.setEnabled(false);
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		
		btnBringToFront.setEnabled(false);
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		
		btnBringToBack.setEnabled(false);
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		
		btnSaveDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveDrawing();
			}
		});

		
		btnLoadDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadDrawing();
			}
		});
		
		btnSaveLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveLog();
			}
		});
			
		btnLoadLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadLog();
			}
		});
		
		txtAreaLog.setEnabled(false);
		txtAreaLog.setEditable(false);
		txtAreaLog.setTabSize(10);
		txtAreaLog.setColumns(50);
		txtAreaLog.setRows(5);
		txtAreaLog.setDisabledTextColor(Color.BLACK);
		
	}	

	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}
	
	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}


	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
	}

	public JToggleButton getTglbtnSelection() {
		return tglbtnSelection;
	}

	public void setTglbtnSelection(JToggleButton tglbtnSelection) {
		this.tglbtnSelection = tglbtnSelection;
	}


	public JButton getBtnUndo() {
		return btnUndo;
	}


	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}


	public JButton getBtnRedo() {
		return btnRedo;
	}


	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}


	public JButton getBtnToFront() {
		return btnToFront;
	}


	public void setBtnToFront(JButton btnToFront) {
		this.btnToFront = btnToFront;
	}


	public JButton getBtnToBack() {
		return btnToBack;
	}


	public void setBtnToBack(JButton btnToBack) {
		this.btnToBack = btnToBack;
	}


	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}


	public void setBtnBringToFront(JButton btnBringToFront) {
		this.btnBringToFront = btnBringToFront;
	}


	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}


	public void setBtnBringToBack(JButton btnBringToBack) {
		this.btnBringToBack = btnBringToBack;
	}


	public JButton getBtnColor() {
		return btnColor;
	}


	public void setBtnColor(JButton btnColor) {
		this.btnColor = btnColor;
	}


	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}


	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}


	public JButton getBtnModification() {
		return btnModification;
	}


	public void setBtnModification(JButton btnModification) {
		this.btnModification = btnModification;
	}


	public JButton getBtnDelete() {
		return btnDelete;
	}


	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JTextArea getTxtAreaLog() {
		return txtAreaLog;
	}

	public JButton getBtnNext() {
		return btnNext;
	}	
	
	
}
