package com.sktutilities.frame;

import javax.swing.JFrame;

public class GenericJFrame extends JFrame
{
    public GenericJFrame(String title){
        super(title);
    }
    public void setText()
    {
        System.out.println("came in GenericJFrame.setText()");
    }
    
    public void clear()
    {
    }
}
