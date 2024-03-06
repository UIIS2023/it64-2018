package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import geometry.Point;
import geometry.Rectangle;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmStack extends JFrame {

	private JPanel contentPane;
	private JList<Rectangle> list;
	private DefaultListModel<Rectangle> dlm = new DefaultListModel<Rectangle>();
	private Stack<Rectangle> rects=new Stack<Rectangle>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStack frame = new FrmStack();
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
	public FrmStack() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("IT 64-2018 Jovana Ostojic");
		
		JButton btnAdd = new JButton("A          D          D");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					dlm.add(0,r);
					rects.push(r);
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(btnAdd, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		list = new JList<Rectangle>();
		scrollPane.setViewportView(list);
		list.setModel(dlm);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JButton btnDelete = new JButton("D          E          L          E          T          E");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rects.isEmpty()){
					JOptionPane.showMessageDialog(null, "List is empty!", "Erorr", JOptionPane.WARNING_MESSAGE);
				} else{
					DlgRectangle dlg=new DlgRectangle();
					dlg.getBtnColor().setVisible(false);
					dlg.getBtnInnerColor().setVisible(false);
					Rectangle r= new Rectangle();
					r=dlm.get(0);
					dlg.getTxtX().setText("" + r.getUpperLeft().getX());
					dlg.getTxtY().setText("" + r.getUpperLeft().getY());
					dlg.getTxtHeight().setText(""+r.getHeight());
					dlg.getTxtWidth().setText(""+r.getWidth());
					dlg.setModal(true);
					dlg.setVisible(true);
					if(dlg.isOK())
					{
						dlm.remove(0);
						rects.pop();
					}
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(btnDelete, BorderLayout.SOUTH);
		
		
	}

}
