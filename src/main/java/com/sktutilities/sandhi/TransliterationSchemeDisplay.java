package com.sktutilities.sandhi;
import com.sktutilities.transliteration.*;
import com.sktutilities.util.Log;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class TransliterationSchemeDisplay extends JFrame implements ActionListener{
	JTextArea tb1;
	JPanel p1, p2; 
	JButton b1;
	String text;
	public TransliterationSchemeDisplay(String scheme )
	{ 			// schemeis either SLEncoding or Itrans
		super(scheme + " ENCODING SCHEME");
	    setSize( 650,550);
	    
	    
		//JPanel Initializations
		p1 = new JPanel();
		p2 = new JPanel();
		//JButton Initialization
		b1 = new JButton("Back");
		b1.setActionCommand("back");
		b1.setToolTipText("Back.");

		//Adding ActionListener
		b1.addActionListener(this); 
		
		//JTextARea Initializations
		tb1 = new JTextArea(24,45);
		tb1.setLineWrap(true);
		tb1.setWrapStyleWord(true);
		text = (new ItransScheme(scheme)).getData();
		tb1.setBackground(Color.yellow);
		tb1.setText(text);
		tb1.setCaretPosition(0);
		tb1.setEditable(false);
		Log.logInfo("display contents == " + text);
		
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

	    p1.add(new JScrollPane(tb1));
	    p2.add(b1);	  
	    
	    //add panel to Contentpane
		contentPane.add(p1);
		contentPane.add(p2);
	    this.setVisible(true);
	    
} // end of constructor


 

	    
public void actionPerformed(ActionEvent e)
	{
	if( (e.getActionCommand()).equals("back") ) 
		{
			this.setVisible(false);			
		}
	}
}
