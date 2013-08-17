package com.sktutilities.pratyahara;

import com.sktutilities.sandhi.SandhiJFrame;
import com.sktutilities.transliteration.RTFDocsSwingDisplayer;
import com.sktutilities.util.*;

import java.awt.event.*;

import javax.swing.*;
import java.awt.*;

public class StandalonePratyahara extends JFrame implements ActionListener, KeyListener{
	
	JMenuBar menubar = new JMenuBar();
	JMenu file, help;
	JMenuItem exit_item, itrans_item, about_item, slp_item,	description;
	JLabel l1a, l1b, l1c, l2, plus, equal, plus1, equal1;
	JTextField tf1, tf2, tf3,tf4, tf5, tf6;
	JPanel p1, p2, p3, p4, p5, p6, p7;
	JButton b1;
	JButton b2; 
	JButton b3;	
	JTextArea tb1;
	JCheckBox marker_chk;
    JCheckBoxMenuItem use_itrans, use_slp;
	static boolean marker = false;
	static boolean padanta = false; // sup ti~Nantam padam
	static boolean slp = false;	
	EncodingUtil encod;
	PratyaharaDecoder pratyahara;
	public StandalonePratyahara()
		{
		super("Pratyahaara Decoder");
	    setSize( 650,550);
	    
	    //Initialization
	    pratyahara = new PratyaharaDecoder();
	    encod = new EncodingUtil();
		//menubar
		menubar = new JMenuBar();
		
		//menus
		file = new JMenu("File");		
		help = new JMenu("Help");
		
		//menuitems
		about_item = new JMenuItem("About ...");
		about_item.setActionCommand("copyright");
		about_item.addActionListener(this);
		
		itrans_item = new JMenuItem("Itrans Transliteration Scheme");
		itrans_item.setActionCommand("itrans_item");
		itrans_item.addActionListener(this);
		
		slp_item = new JMenuItem("SLP Transliteration Scheme");
		slp_item.setActionCommand("slp_item");
		slp_item.addActionListener(this);
		
		description = new JMenuItem("Siva Sutras of Panini");
		description.setActionCommand("description");
		description.addActionListener(this);
				
		exit_item = new JMenuItem("Exit");
		exit_item.setActionCommand("exit_item");
		exit_item.addActionListener(this);
		
		
		//add menuitems to menus
		file.add(exit_item);
		help.add(itrans_item);
		help.add(slp_item);
		help.add(description);
		help.add(about_item);
		
		
		//add menus to menubar
		menubar.add(file);
		menubar.add(help);
		//menus end
		
		//Panel Initialization
		 p1 = new JPanel();
		 p2 = new JPanel();
		 p3 = new JPanel();
		 p4 = new JPanel();
		 p5 = new JPanel();
		 p6 = new JPanel();
		 p7 = new JPanel();
		
		Container contentPane = getContentPane();
		l1a = new JLabel("Please enter a pratyahaara in");
		l1b = new JLabel("ITRANS FORMAT");
		l1c = new JLabel("only");
		l1b.setToolTipText("ITRANS FORMAT ONLY.");
		
		l2 = new JLabel("Notes:");
		
		plus = new JLabel(" + ");
		equal = new JLabel(" = ");

		tf1 = new JTextField(10);
		tf1.addKeyListener(this);
		tf2 = new JTextField(10);
		tf3 = new JTextField(20);

		plus1 = new JLabel(" + ");
		equal1 = new JLabel(" = ");
		tf4 = new JTextField(10);
		tf4.addKeyListener(this);
		tf5 = new JTextField(10);
		tf6 = new JTextField(20);
		tf4.setEditable(false);
		tf5.setEditable(false);
		tf6.setEditable(false);
		tf1.setText("al");
		tf4.setText(EncodingUtil.convertRawItransToDevanagari(tf1.getText()));
		
		//tf2.setText("Alaya");
		
		//jtf = new JTextField("Devanagari","\u0966 \u0967 \u0968 \u0969 \u096a \u096b \u096c \u096d \u096e \u096f",20, 0);

		//Check Boxes
		marker_chk  = new JCheckBox("Show Results with the 'it'-markers.");
		marker_chk.setActionCommand("marker");
		marker_chk.addActionListener(this);
		marker_chk.setToolTipText("Show Results with the 'it'-markers.");
	
	
		//Buttons
		b1 = new JButton("Enter");  
		b1.setActionCommand("Enter");
		b1.setToolTipText("Enter two words.");
		b2 = new JButton("Clear");
		b2.setActionCommand("clear");
		b2.setToolTipText("Clear all Text Fields");
		b3 = new JButton("Exit");
		b3.setActionCommand("Exit");
		b3.setToolTipText("Quit the Application.");

		tb1 = new JTextArea(14,45);
		tb1.setLineWrap(true);
		tb1.setWrapStyleWord(true);
		b1.addActionListener(this) ;
		b2.addActionListener(this) ;
		b3.addActionListener(this) ;
		
		
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		// adding Components to JPanel
		p1.add(l1a);
		p1.add(l1b);
		p1.add(l1c);
		p2.add(tf1);
		p3.add(marker_chk);
		p4.add(tf4);
		p5.add(b1);
		p5.add(b2);
		p5.add(b3);
		p6.add(l2);
		p7.add(new JScrollPane(tb1));
		
		this.setJMenuBar(menubar) ;
		
		// add panels to contentpane
		contentPane.add(p1);		
		contentPane.add(p2);
		contentPane.add(p3);
		contentPane.add(p4);				
		contentPane.add(p5);
		contentPane.add(p6);		
		contentPane.add(p7);
		
		this.setVisible(true);	
		}



//*******************END OF FUNCTION**********************//



//*******************BEGINNING OF FUNCTION********************//
//The three events
public void keyTyped(KeyEvent e) {	
}
public void keyPressed(KeyEvent e) {	
	//Log.logInfo("keyPressed");
	}
public void keyReleased(KeyEvent e) {
		String str1 = "";
		   
	if(e.getComponent() == this.tf1 ) 
		{
			
		// if User presses enter process String to generate pratyahaaras
		if (e.getKeyCode() == KeyEvent.VK_ENTER )
		setText();
		
		str1 = (tf1.getText().toString()).trim();
		
		// if user prefers ITRANS
		if(slp == false)
		tf4.setText(EncodingUtil.convertRawItransToDevanagari(str1)); // get raw to DVN			
		// if user chose scharfe
		else 
		tf4.setText(EncodingUtil.convertSLPToDevanagari(str1));;   // get scharfe to DVN		
		}
	

//Log.logInfo("keyReleased");
}

//*******************END OF FUNCTION**********************//




//*******************BEGINNING OF FUNCTION********************//
public void actionPerformed(ActionEvent e)
	{
	if( (e.getActionCommand()).equals("Enter") ) 
		{
			setText(); // method setText();
		} // end of if( (e.getActionCommand()).equals("Enter") ) 

	else if( (e.getActionCommand()).equals("clear") )
		{
			tf1.setText("");
			tf4.setText("");
			//tf6.setText("");
			tb1.setText("");
			marker_chk.setSelected(false);
			
			//Log.logInfo(" tf1 == " + tf1.getText() );
		}// end of else if( (e.getActionCommand()).equals("clear") )
		
 	else if( (e.getActionCommand()).equals("Exit") ) 
		{
			System.exit(0);
		} // end of else if( (e.getActionCommand()).equals("Exit") )

	else if( (e.getActionCommand()).equals("exit_item") ) 
		{
			System.exit(0);
			//this.setVisible(false);
		} // end of else if( (e.getActionCommand()).equals("exit_item") )
		
	else if( (e.getActionCommand()).equals("copyright") ) 
		{
			String copyright = "\u00A9 2005 All Rights Reserved. Chetan Pandey\n" 
								+ "Pls. Contact taddhita_priya@yahoo.com for questions and suggestions.";
			
			JOptionPane.showMessageDialog(this,copyright,"About My Pratyahaara Program", JOptionPane.PLAIN_MESSAGE );
			
		} // end of else if( (e.getActionCommand()).equals("exit_item") )
		
	else if( (e.getActionCommand()).equals("itrans_item") ) 
		{
			new RTFDocsSwingDisplayer("ITRANS ENCODING SCHEME", SandhiJFrame.DOC_PATH + "itrans.rtf", "pratyahara.jara");
			//new transliteration_scheme_display("ITRANS");
		} // end of else if( (e.getActionCommand()).equals("exit_item") )
		
	else if( (e.getActionCommand()).equals("slp_item") ) 
		{
			new RTFDocsSwingDisplayer("SLP ENCODING SCHEME", SandhiJFrame.DOC_PATH + "slp.rtf", "pratyahara.jar");
			//new transliteration_scheme_display("SLP");
		} // end of else if( (e.getActionCommand()).equals("exit_item") )
			
	else if( (e.getActionCommand()).equals("marker") ) 
		{
		
			//Log.logInfo("The 'it' markers will be displayed.");
			if(marker_chk.isSelected())
			{
				tb1.setText("The 'it' markers will be displayed.");
				marker = true;
			}
			else if (!marker_chk.isSelected())
			{
				tb1.setText("The 'it' markers will not be displayed.");
				marker = false;
			}
		

		} // end of else if( (e.getActionCommand()).equals("marker") )


	else if( (e.getActionCommand()).equals("description") ) 
		{
			new RTFDocsSwingDisplayer("The Siva Sutras of Panini",SandhiJFrame.DOC_PATH + "siva.rtf", "pratyahara.jar");
		} // end of else if( (e.getActionCommand()).equals("description") )


	}
//*******************END OF FUNCTION**********************//


//*******************BEGINNING OF FUNCTION********************//

public void setText()
	{
		String str1 = (tf1.getText().toString()).trim();
		String strInSLP = "";
		
		// if user prefers ITRANS
		if(slp == false)
		strInSLP = EncodingUtil.convertRawItransToSLP(str1); // get scharfe view			
		// if user prefers scharfe
		else 
		strInSLP = str1;   // string is already scharfe
		
		
	
		String result = pratyahara.decodePratyahara(strInSLP, StandalonePratyahara.marker );
	
		tb1.setText(result); 
	

	}
//*******************END OF FUNCTION**********************//		
	  public static void main( String[] args ) 
	  {
		@SuppressWarnings("unused")
        StandalonePratyahara prat = new StandalonePratyahara();
		//prat.setVisible(true);
		
	  } //end of main


} // end of pratyahaar
