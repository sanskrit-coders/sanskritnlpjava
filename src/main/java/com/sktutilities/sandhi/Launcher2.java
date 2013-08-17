package com.sktutilities.sandhi;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sktutilities.util.Log;

public class Launcher2 extends JFrame implements ActionListener {
	JLabel l1;
	JLabel l2;
	JTextField tf1;
	JButton b1,b2,b3,b4;
	
	JPanel p1,p2,p3,p4;
	File src_file;
	public Launcher2()
		{
		super("Sandhi Program Installer");
	    setSize( 450,250);
	    src_file = new File("sandhi.jar");
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		//p4 = new JPanel();
		
		Container contentPane = getContentPane();
		l1 = new JLabel("Please enter Folder name, where you would like to install Sandhi.");
		l2 = new JLabel("Install Program in");
		tf1 = new JTextField(20);
		tf1.setText("C:\\sandhi\\sandhi");
		b1 = new JButton("Choose");
		b1.setActionCommand("browse");
		b2 = new JButton("Clear");
		b2.setActionCommand("clear");
		b3 = new JButton("Next");
		b3.setActionCommand("next");
		b4 = new JButton("Cancel");
		b4.setActionCommand("cancel");
		
		b1.addActionListener(this) ;
		b2.addActionListener(this) ;
		b3.addActionListener(this) ;
		b4.addActionListener(this) ;
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		p1.add(l1);
		p2.add(l2);
		p2.add(tf1);
		p2.add(b1);
		
		p3.add(b4);
		p3.add(b2);
		p3.add(b3);
		contentPane.add(p1);
		contentPane.add(p2);
		contentPane.add(p3);
		this.setVisible(true);
		}

//**************************************************************///		
public static void copyFile(File src,File dest) throws IOException
 {
 Log.logInfo("entering copyFile() ") ;
  
  Log.logInfo(" src.getPath() ==" + src.getAbsolutePath()) ;
  Log.logInfo(" src.getPath() ==" + src.getAbsolutePath()) ;
  Log.logInfo(" dest.getPath() ==" + dest.getAbsolutePath()) ;
  
  FileChannel in = new FileInputStream(src).getChannel();
  FileChannel out = new FileOutputStream(dest).getChannel();
  long transBytes = 0;
  while(transBytes < in.size())
  {
   transBytes += out.transferFrom(in,0,in.size() - transBytes);   
  }
  in.close();
  out.close();
 Log.logInfo("exiting copyFile() ") ;
 }		

//*******************Begining******************************///			
public void create_Dir(File f1)
{
try { 
f1.mkdirs();
File dest_file = new File(f1.getAbsolutePath()+ File.separator + src_file.getName());
copyFile(src_file,dest_file);
Log.logInfo("Created " + f1.getName() );

}
catch( Exception ex )
  {
    Log.logInfo("In File Creation Exception: " );
  }

}
//**********************End********************************///		
		
public void actionPerformed(ActionEvent e)
	{
	if( (e.getActionCommand()).equals("browse") ) {

	JFileChooser chooser = new JFileChooser();
	chooser.setMultiSelectionEnabled(false);
	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

	int option = chooser.showOpenDialog(this);
	
	
	//******************//
	if( option == JFileChooser.APPROVE_OPTION)
		{
			if( chooser.getSelectedFile() != null )
			{
			Log.logInfo("You chose to open this file: " +
            chooser.getSelectedFile().getPath());
			String path = chooser.getSelectedFile().getPath();
			tf1.setText(path);
			}
		}

	} // end of if( (e.getActionCommand()).equals("browse") ) 
	//******************//
	
	
	
	//******************//
	else if( (e.getActionCommand()).equals("clear") ) {
		tf1.setText("");		
		}
	//******************//	
	
	
	
	
	//******************//
	else if( (e.getActionCommand()).equals("next") ) 
		{
		String dest_path = tf1.getText();
		
		Log.logInfo("dest_path = " + dest_path);
		
		File f = new File(dest_path);
		
		// if the chosen file name entered does not exist, then create one
		if( !f.exists() )
			{
				
				Log.logInfo("File " + f.getPath() + " does not exist");
				
				String saval = "File " + f.getPath() + " does not exist." +
								"\nWould you like to create instead?";
				int n = JOptionPane.showConfirmDialog(this,saval,"Create Folder", JOptionPane.YES_NO_CANCEL_OPTION);
				// ask User whether he will like us to create the Directory for him.
				if(n == 0)
				{
				create_Dir(f);
				Log.logInfo("after creating directory");
			
				this.setVisible(false);
				new Launcher3();
				}
				
		//******************//	
			} // end of main if
		
		// if file name already exists, we have to overwrite.Get permission
		else 
		{
			String saval = "Folder " + f.getPath() + " already exists." +
							"\nWould you like us to over-write it?";
			int n = JOptionPane.showConfirmDialog(this,saval,"Overwrite Folder", JOptionPane.YES_NO_CANCEL_OPTION);
			// ask User whether he will like us to create the Directory for him.
			if(n == 0)
			{
				create_Dir(f);
				
				Log.logInfo("after creating directory");
				this.setVisible(false);
				new Launcher3();
			}	
		
		Log.logInfo(dest_path + " is a valid file name" );
		}


		}
		
	
	else if( (e.getActionCommand()).equals("cancel") ) 
		{
			System.exit(0);
		} // end of else if( (e.getActionCommand()).equals("Exit") )
	}


} // end of upgrader
