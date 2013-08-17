package com.sktutilities.transliteratorAsSwing;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sktutilities.util.EncodingUtil;
import com.sktutilities.util.Log;


public class AkrutiToeLatin extends JFrame implements ActionListener, KeyListener {
	
	JMenuBar menubar = new JMenuBar();
	JMenu file, help;
	JMenuItem exit_item, help_item, about_item;
	JLabel l1,l2 /*, plus, equal, plus1, equal1*/;
	//JTextField tf1, tf2, tf3,tf4, tf5, tf6;
	JButton b1,b2,b3, b4, b5, b6;	
	JPanel p1,p2,p3,p4,p45,p5,p6;
	JTextArea tb1,tb2,tb3;
	EncodingUtil encod;
	public AkrutiToeLatin()
		{
		super("Welcome to My Word Processor");
	    setSize( 650,450);
	    Log.logInfo("You are in akruti_to_e_latin.java");
	    encod = new EncodingUtil();
		//menus
		menubar = new JMenuBar();
		file = new JMenu("File");
		exit_item = new JMenuItem("Exit");
		file.add(exit_item);
		exit_item.setActionCommand("exit_item");
		exit_item.addActionListener(this);

		help = new JMenu("Help");
		
		help_item = new JMenuItem("Help");
		about_item = new JMenuItem("About");
		about_item.setActionCommand("about_item");
		about_item.addActionListener(this);

		help.add(help_item);
		help.add(about_item);
		menubar.add(file);
		menubar.add(help);
		//menus end
		
		//JPanel Initilization 
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();		
		p4 = new JPanel();
		p45 = new JPanel();
		p5 = new JPanel();
		
		//JLabel Initialization
		l1 = new JLabel("Please enter Words in ITRANS FORMAT only");
		l2 = new JLabel("Notes:");
		
		
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
		b4 = new JButton("Open");
		b4.setActionCommand("open");
		b4.setToolTipText("Open File");
		b5 = new JButton("Save");
		b5.setActionCommand("save");
		b5.setToolTipText("Save File.");	
		b6 = new JButton("Save As");
		b6.setActionCommand("save_as");
		b6.setToolTipText("Save File As.");
		
		b1.addActionListener(this) ;
		b2.addActionListener(this) ;
		b3.addActionListener(this) ;
		b4.addActionListener(this) ;
		b5.addActionListener(this) ;
		b6.addActionListener(this) ;
		
		
		Container contentPane = getContentPane();
		//JTextBox
		tb1 = new JTextArea(6,45);
		tb1.setLineWrap(true);
		tb1.setWrapStyleWord(true);
		tb1.addKeyListener(this);
	
		tb2 = new JTextArea(6,45);
		tb2.setLineWrap(true);
		tb2.setWrapStyleWord(true);
		tb2.addKeyListener(this);
		
		tb3 = new JTextArea(6,45);
		tb3.setLineWrap(true);
		tb3.setWrapStyleWord(true);
		tb3.addKeyListener(this);
		
		//Setting Fonts
		Font f = new Font("Kruti Dev 010", Font.PLAIN, 18);
		tb1.setFont(f);
		
		//Panel
		p1.add(l1);
		p2.add(b4);
		p2.add(b5);
		p2.add(b6);
		p3.add(new JScrollPane(tb1));
		p4.add(new JScrollPane(tb2));
		p45.add(new JScrollPane(tb3));
		p5.add(b1);
		p5.add(b2);
		p5.add(b3);
		this.setJMenuBar(menubar) ;
		
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		//contentPane.add(p);
		contentPane.add(p1);
		contentPane.add(p2);
		contentPane.add(p3);	
		contentPane.add(p4);	
		contentPane.add(p45);
		contentPane.add(p5);
	
		}

//*******************BEGINNING OF FUNCTION********************//
String input ="";
//The three events
public void keyTyped(KeyEvent e) {		
	char c = e.getKeyChar();
	input = (String) (new Character(c).toString());
	String result = EncodingUtil.convertSLPToIAST(input);
	tb3.append(result);
	
Log.logInfo("input: " + "\"" + input + "\"");
	}
public void keyPressed(KeyEvent e) {}
public void keyReleased(KeyEvent e) {
		//(tf1.getText().toString()).trim();
//	Log.logInfo("i came in e.getComponent() == this.tb1");	   
	if(e.getComponent() == this.tb1 ) 
		{	
		
		String str1 =  (tb1.getText().toString());	
		String result = EncodingUtil.convertRawItransToDevanagari(str1);
		tb2.setText(result);
//	Log.logInfo("i came in e.getComponent() == this.tb1");
//	Log.logInfo("Key Location is : " + e.getKeyLocation()	);
//	Log.logInfo("Key Code is : " + e.getKeyCode());
	String keytext = KeyEvent.getKeyText(e.getKeyCode() );
	
	if(keytext == "Backspace")
	;
	if(keytext == "Delete")
	;
	
	Log.logInfo("Key Text is : " + keytext );
//	String gkt = e.getKeyText(e.getKeyCode() );
	
//	String result = encod.elatin_view(gkt);
//	tb3.append(result);
	
	//	str1 = tb1.getText();
		//tb2.setText(encod.convertRawItransToDevanagari(str1));
	//	tb3.setText(encod.get_raw_to_latin_view(str1));
		
//	if (e.getKeyCode() == e.VK_ENTER )	Log.logInfo("Pressed Enter");
//	if (e.getKeyCode() == e.VK_A )	Log.logInfo("A");
//	if (e.getKeyCode() == e.VK_I )	Log.logInfo("Pressed I");
//	if (e.getKeyCode() == e.VK_L )	Log.logInfo("Pressed L");
//	if (e.getKeyCode() == e.VK_M )	Log.logInfo("Pressed M");
		}
		
	else if(e.getComponent() == this.tb2 ) 
	{
		/*Log.logInfo("i came in e.getComponent() == this.tb2");
		str1 = (tb2.getText().toString()).trim();
		tb2.setText(encod.convertRawItransToDevanagari(str1));
		//tf1.setText(encod.get_dvn_to_uniform_itrans(tf4.getText()));
		tb1.setText(input); */
	}

//Log.logInfo("keyReleased");
}

//*******************END OF FUNCTION**********************//



//*******************BEGINNING OF FUNCTION********************//
public void actionPerformed(ActionEvent e)
	{
	if( (e.getActionCommand()).equals("Enter") ) 
		{
			//OutputStreamWriter out = new OutputStreamWriter(new ByteArrayOutputStream());
			//Log.logInfo(out.getEncoding());

			setText(); // method setText();
		} 

	else if( (e.getActionCommand()).equals("clear") )
		{
			tb1.setText("");
			tb2.setText("");
			tb3.setText("");	
		}
		
	else if( (e.getActionCommand()).equals("Exit") ) 
		{
			System.exit(0);
		} 

	else if( (e.getActionCommand()).equals("open") ) 
		{
			JFileChooser chooser = new JFileChooser();
			chooser.setMultiSelectionEnabled(false);
			//chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
			int option = chooser.showOpenDialog(this);
			if( option == JFileChooser.APPROVE_OPTION)
				{
					if( chooser.getSelectedFile() != null )
					{
					Log.logInfo("You chose to open this file: " +
		            chooser.getSelectedFile().getPath());
					
					}
				} 
		} 

	else if( (e.getActionCommand()).equals("save") ) 
		{
			
		} 
		
		
	else if( (e.getActionCommand()).equals("save_as") ) 
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
			int option = chooser.showSaveDialog(this);
			if( option == JFileChooser.APPROVE_OPTION)
				{
					if( chooser.getSelectedFile() != null )
					{
					Log.logInfo("You chose to save this file as " +
		            chooser.getSelectedFile().getPath());
					}
				}
	} 

	else if( (e.getActionCommand()).equals("exit_item") ) 
		{
			System.exit(0);
		} // end of else if( (e.getActionCommand()).equals("exit_item") )
		
		else if( (e.getActionCommand()).equals("about_item") ) 
		{
			JOptionPane.showMessageDialog(this,"\u00A9 2005 All Rights Reserved. Chetan Pandey ","About My Sandhi Program", JOptionPane.PLAIN_MESSAGE );

		} // end of else if( (e.getActionCommand()).equals("exit_item") )
		
		else if( (e.getActionCommand()).equals("tb1") ) 
		{
			setText();
		} // end of else if( (e.getActionCommand()).equals("Exit") )
		
		else if( (e.getActionCommand()).equals("tb2") ) 
		{
			;
		} // end of else if( (e.getActionCommand()).equals("Exit") )


	
	}


//*******************END OF FUNCTION**********************//


//*******************BEGINNING OF FUNCTION********************//

public void setText()
	{
		String str1 = (tb1.getText().toString()).trim();	
		String result = EncodingUtil.convertRawItransToDevanagari(str1);
		//String result = encod.elatin_view(str1);
		String result1 = EncodingUtil.convertRawItransToIAST(str1);
		tb2.setText(result)	;	
		tb3.setText(result1);
	 //remove thse later		
	}
//*******************END OF FUNCTION**********************//		
	


//*******************END OF FUNCTION**********************//

  public static void main( String[] args ) throws IOException 
	  {
		AkrutiToeLatin u1 = new AkrutiToeLatin();
		u1.setVisible(true);
		
	  } //end of main

} // end of sandhi
