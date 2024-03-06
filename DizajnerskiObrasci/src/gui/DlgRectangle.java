package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private Color c = Color.BLACK, innerC = new Color(0f,0f,0f,0f);
	private Color pc, innerPc;
	private boolean isOK;
	private boolean colorChosen=false, innerColorChosen=false;
	private JButton btnColor = new JButton("Color");
	private JButton btnInnerColor = new JButton("Inner color");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setBounds(100, 100, 300, 300);
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
			JLabel lblHeight = new JLabel("Height:");
			contentPanel.add(lblHeight);
			lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		{
			txtHeight = new JTextField();
			contentPanel.add(txtHeight);
			txtHeight.setColumns(10);
		}
		{
			JLabel lblWidth = new JLabel("Width:");
			contentPanel.add(lblWidth);
			lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		{
			txtWidth = new JTextField();
			contentPanel.add(txtWidth);
			txtWidth.setColumns(10);
		}
		{
			
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
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtX.getText().isEmpty() || txtY.getText().isEmpty() || txtHeight.getText().isEmpty() || txtWidth.getText().isEmpty()) {
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

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
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

}
