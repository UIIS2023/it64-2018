package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DlgCircleHexagon extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private Color c=Color.BLACK, innerC=new Color(0f,0f,0f,0f);
	private Color pc, innerPc;
	private boolean isOK;
	private boolean colorChosen=false, innerColorChosen=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCircleHexagon dialog = new DlgCircleHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircleHexagon() {
		setBounds(100, 100, 300, 275);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		{
			JLabel lblXCoordinate = new JLabel("X coordinate:");
			lblXCoordinate.setHorizontalAlignment(SwingConstants.LEFT);
			contentPanel.add(lblXCoordinate);
			lblXCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		{
			txtX = new JTextField();
			contentPanel.add(txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblYCoordinate = new JLabel("Y coordinate:");
			contentPanel.add(lblYCoordinate);
			lblYCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		{
			txtY = new JTextField();
			contentPanel.add(txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			contentPanel.add(lblRadius);
			lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		{
			txtRadius = new JTextField();
			contentPanel.add(txtRadius);
			txtRadius.setColumns(10);
		}
		{
			JButton btnColor = new JButton("Color");
			btnColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
			contentPanel.add(btnColor);
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c = JColorChooser.showDialog(null, "Choose color",pc);
					colorChosen=true;
				}
			});
		}
		{
			JButton btnInnerColor = new JButton("Inner color");
			btnInnerColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
			contentPanel.add(btnInnerColor);
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					innerC = JColorChooser.showDialog(null, "Choose color",innerPc);
					innerColorChosen=true;
				}
			});
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtX.getText().isEmpty() || txtY.getText().isEmpty() || txtRadius.getText().isEmpty()) {
							isOK = false;
							setVisible(true);
							JOptionPane.showMessageDialog(null, "All fields are required!","Error", JOptionPane.WARNING_MESSAGE);
						} else {
							isOK = true;
							dispose();
						}
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}
		}
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public Color getInnerC() {
		return innerC;
	}

	public void setInnerC(Color innerC) {
		this.innerC = innerC;
	}

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	public Color getPc() {
		return pc;
	}

	public void setPc(Color pc) {
		this.pc = pc;
	}

	public Color getInnerPc() {
		return innerPc;
	}

	public void setInnerPc(Color innerPc) {
		this.innerPc = innerPc;
	}

	public boolean isColorChosen() {
		return colorChosen;
	}

	public void setColorChosen(boolean colorChosen) {
		this.colorChosen = colorChosen;
	}

	public boolean isInnerColorChosen() {
		return innerColorChosen;
	}

	public void setInnerColorChosen(boolean innerColorChosen) {
		this.innerColorChosen = innerColorChosen;
	}

}
