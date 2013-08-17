package com.sktutilities.sandhi;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpFrame extends JFrame implements ActionListener, MouseListener {
	
 private JEditorPane edit1;
 JPanel p1,p2,p3,p4;
 JLabel l1,l2,l3,l4,l5,l6;
 JButton b1,b2,b3;
	
	
	
public 	HelpFrame()	{
		super("Help Topics");
	    setSize( 650,550);
		
		//Buttons
		b1 = new JButton("Display");
		b1.setActionCommand("display");
		b1.addActionListener(this) ;
		
		b2 = new JButton("Cancel");
		b2.setActionCommand("cancel");
		b2.addActionListener(this) ;
		//b3 = new JButton();
		//b3.addActionListener(this) ;
		
		//JEditorPane
		edit1 = new JEditorPane();
		edit1.setBackground(Color.green);
		edit1.setText("All about Vowel Sandhi.\nVowel Sandhi occurs between " + 
		"two words which respectively ends in a vowel and starts with a Vowel");
		edit1.setSize(300,400);
		
		//JLabels
		ImageIcon icon = new ImageIcon("quesmark.gif");

		l1 = new JLabel("About This Program\n",icon,JLabel.LEFT);
		l1.addMouseListener(this);
		l2 = new JLabel("How it Works",icon,JLabel.LEFT);
		//l1.setVerticalTextPosition(JLabel.BOTTOM);
		//l1.setHorizontalTextPosition(JLabel.CENTER);
		l2.addMouseListener(this);
		
		l3 = new JLabel("ITRANS Transliteration Scheme",icon,JLabel.LEFT);
		l3.addMouseListener(this);
		l4 = new JLabel("Notes on Vowel Sandhi",icon,JLabel.LEFT);
		l4.addMouseListener(this);
		l5 = new JLabel("Notes on Consonant Sandhi",icon,JLabel.LEFT);
		l5.addMouseListener(this);
		l6 = new JLabel("Notes on Visarga Sandhi",icon,JLabel.LEFT);
		l6.addMouseListener(this);
		
		//Panels
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();	
				
		p1.add(edit1);
		p2.setLayout(new BoxLayout(this.p2, BoxLayout.Y_AXIS));
		p2.add(l1);
		p2.add(l2);
		p2.add(l3);
		p2.add(l4);
		p2.add(l5);
		p2.add(l6);
		p3.add(b1);
		p3.add(b2);
		
		Container contentPane = getContentPane();
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.add(p1);
		contentPane.add(p2);
		contentPane.add(p3);
		setVisible(true);
		
		}
//*******************END OF FUNCTION**********************//


//*******************BEGINNING OF FUNCTION********************//
  public void mouseClicked(MouseEvent e) {
   if(e.getComponent() == this.l1)
   	  edit1.setText("I clicked on JLabel1");
   if(e.getComponent() == this.l2)
   	  edit1.setText("I clicked on JLabel2");
   if(e.getComponent() == this.l3)
   	  {
   	  	edit1.setText("I clicked on JLabel3");
   	  	new TransliterationSchemeDisplay("ITRANS");		
		}
   if(e.getComponent() == this.l4)
   	  edit1.setText("I clicked on JLabel4");
   if(e.getComponent() == this.l5)
   	  edit1.setText("I clicked on JLabel5");
   if(e.getComponent() == this.l6)
   	  edit1.setText("I clicked on JLabel6");
   	}  
   public void mouseEntered(MouseEvent e) {} 
   public void mouseExited(MouseEvent e) {} 
   public void mousePressed(MouseEvent e) {} 
   public void mouseReleased(MouseEvent e) {}  

//*******************END OF FUNCTION**********************//



//*******************BEGINNING OF FUNCTION********************//



public void actionPerformed(ActionEvent e)
	{
	if( (e.getActionCommand()).equals("display") ) 
		{
		
		} // end of if( (e.getActionCommand()).equals("display") ) 


	if( (e.getActionCommand()).equals("cancel") ) 
		{
			this.setVisible(false);
			
		} // end of if( (e.getActionCommand()).equals("cancel") ) 
		
	}	
//*******************END OF FUNCTION**********************//

		
}