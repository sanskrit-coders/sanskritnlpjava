package com.sktutilities.sandhi;

import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class InstallComplete extends JFrame implements ActionListener {
	JLabel l1;
	JLabel l2;
	JTextField tf1;
	JButton b1,b2,b3,b4;
	JPanel p1,p2,p3,p4;
	File src_file;
	String prog_name;
	public InstallComplete()
		{
			super("Sandhi Program Installation Complete");
			setSize( 450,250);
			prog_name = "My Sandhi";
			JLabel l1 = new JLabel("Congratulations!!! You have successfully installed " + prog_name);
			b1 = new JButton("Finish");
			b1.setActionCommand("finish");		
			b1.addActionListener(this) ;
			
			Container contentPane = getContentPane();
			contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
			p1 = new JPanel();
			p1.add(l1);
			p1.add(b1);
			
			contentPane.add(p1);
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