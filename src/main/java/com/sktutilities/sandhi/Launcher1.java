package com.sktutilities.sandhi;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Launcher1 extends JFrame implements ActionListener {
	JLabel l1;
	JLabel l2;
	JTextField tf1;
	JButton b1,b2,b3,b4;
	
	JPanel p1,p2,p3,p4;	
	String prog_name= "My Sandhi";
	File src_file;
	public Launcher1()
		{
		super("Sandhi Program Installer");
	    setSize( 450,250);
		p1 = new JPanel();
		p2 = new JPanel();
		//p3 = new JPanel();
		//p4 = new JPanel();
		
		Container contentPane = getContentPane();
		l1 = new JLabel("Thank you for choosing " + prog_name + "Program.");
		
		
		b1 = new JButton("Cancel");
		b1.setActionCommand("cancel");
		b2 = new JButton("Next");
		b2.setActionCommand("next");
		//b3 = new JButton("Confirm");
//b3.setActionCommand("confirm");
	//	b4 = new JButton("Exit");
	//	b4.setActionCommand("Exit");
		
		b1.addActionListener(this) ;
		b2.addActionListener(this) ;
		//b3.addActionListener(this) ;
		//b4.addActionListener(this) ;
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		p1.add(l1);
		//p2.add(l2);
		//p2.add(tf1);
		p2.add(b1);
		p2.add(b2);
		//p3.add(b3);
		//p3.add(b4);
		contentPane.add(p1);
		contentPane.add(p2);
		//contentPane.add(p3);
		//contentPane.add(p4);
		}
	

//**********************End********************************///		

//*************************Beginning************************///			
public void actionPerformed(ActionEvent e)
	{
	//******************//
		
	if( (e.getActionCommand()).equals("cancel") )
	 {
		System.exit(0);		
	 }
	//******************//	
	
	
	
	
	//******************//
	else if( (e.getActionCommand()).equals("next") ) 
		{
		new Launcher2();	
		this.setVisible(false);		
		}
				
		//******************//	
	} //public void actionPerformed(ActionEvent e)
		


  public static void main( String[] args ) throws IOException 
	  {
		Launcher1 u1 = new Launcher1();
		u1.setVisible(true);
		
	  } //end of main

} // end of upgrader
