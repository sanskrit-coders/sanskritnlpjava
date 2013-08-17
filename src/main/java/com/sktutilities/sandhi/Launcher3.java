package com.sktutilities.sandhi;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Launcher3 extends JFrame implements ActionListener {
	JLabel l1;
	JLabel l2;
	JTextField tf1;
	JButton b1,b2,b3,b4;
	JPanel p1,p2,p3,p4;
	File src_file;
	String prog_name= "My Sandhi";
	public Launcher3()
		{
			super("Sandhi Program Installation Complete");
			setSize( 450,250);
			//super("Sandhi Program Installation Complete");
			JLabel l1 = new JLabel("Congratulations!!! You have successfully installed " + prog_name);
			b1 = new JButton("Finish");
			b1.setActionCommand("finish");		
			b1.addActionListener(this) ;
			
			Container contentPane = getContentPane();
			contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
			p1 = new JPanel();
			p2 = new JPanel();
			p1.add(l1);
			p2.add(b1);
			
			contentPane.add(p1);
			contentPane.add(p2);
			this.setVisible(true);
		}
		
		
	public void actionPerformed(ActionEvent e)
	{
	if( (e.getActionCommand()).equals("finish") ) 
		{
			System.exit(0);
		} // end of if( (e.getActionCommand()).equals("Enter") ) 
	}
	
}