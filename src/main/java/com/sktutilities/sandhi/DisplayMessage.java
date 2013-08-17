package com.sktutilities.sandhi;

import javax.swing.*;
import java.awt.*;

public class DisplayMessage
{
	final private String program_name = "File-Copier";
	int return_val ;
	public int getVal() { return return_val;}
	
	
	public DisplayMessage()
	{		
		return_val = -1;
	}
	
	public void dialog(String msg, Component cmp){
	JOptionPane.showMessageDialog(cmp, msg, program_name, JOptionPane.PLAIN_MESSAGE );	
	}
	
	public int yes_no_dialog(JPanel pp, String msg)
	{
		return_val = JOptionPane.showConfirmDialog(pp,msg,program_name,
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		return return_val;
	}
}