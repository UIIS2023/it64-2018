package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmSort extends JFrame {

	private JPanel contentPane;
	private JList<Rectangle> list;
	private DefaultListModel<Rectangle> dlm = new DefaultListModel<Rectangle>();
	private ArrayList<Rectangle> rects=new ArrayList<Rectangle>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSort frame = new FrmSort();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmSort() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("IT 64-2018 Jovana Ostojic");
		
		JButton btnAddRectangle = new JButton("A          D          D");
		btnAddRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DlgRectangle dlg= new DlgRectangle();
				dlg.getBtnColor().setVisible(false);
				dlg.getBtnInnerColor().setVisible(false);
				dlg.setModal(true);
				dlg.setVisible(true);
				
				if(dlg.isOK()){
					int x=Integer.parseInt(dlg.getTxtX().getText());
					int y=Integer.parseInt(dlg.getTxtY().getText());
					int h=Integer.parseInt(dlg.getTxtHeight().getText());
					int w=Integer.parseInt(dlg.getTxtWidth().getText());
					Point p=new Point(x,y);
					Rectangle r=new Rectangle(p,h,w);
					dlm.addElement(r);
					rects.add(r);
				}
			}
		});
		contentPane.add(btnAddRectangle, BorderLayout.NORTH);
		btnAddRectangle.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		list = new JList<Rectangle>();
		scrollPane.setViewportView(list);
		list.setModel(dlm);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		//contentPane.add(list, BorderLayout.CENTER);
		
		JButton btnSort = new JButton("S          O          R          T");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rects.isEmpty()){
					JOptionPane.showMessageDialog(null, "List is empty!", "Erorr", JOptionPane.WARNING_MESSAGE);
				} else{
					rects.sort(null);
					dlm.clear();
					for (int i = 0; i < rects.size(); i++)
					{
					    dlm.addElement(rects.get(i));
					}
				}
			}
		});
		contentPane.add(btnSort, BorderLayout.SOUTH);
		btnSort.setFont(new Font("Tahoma", Font.BOLD, 15));
		
	}
}
