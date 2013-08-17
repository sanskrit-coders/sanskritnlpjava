package com.sktutilities.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sktutilities.frame.GenericJFrame;

public class ControlPanel extends JPanel implements ActionListener
{
    JButton                    enterButton;

    JButton                    clearButton;

    JButton                    exitButton;
    
    GenericJFrame frame;
    
    public ControlPanel(GenericJFrame frame){
        super();
        this.frame = frame;
        
        // Buttons
        enterButton = new JButton("Enter");
        enterButton.setActionCommand("enter");
        enterButton.setToolTipText("Enter two words.");
        enterButton.addActionListener(this);

        clearButton = new JButton("Clear");
        clearButton.setActionCommand("clear");
        clearButton.setToolTipText("Clear all Text Fields");
        clearButton.addActionListener(this);
        
        exitButton = new JButton("Exit");
        exitButton.setActionCommand("exit");
        exitButton.setToolTipText("Quit the Application.");
        exitButton.addActionListener(this);
        
        this.add(enterButton);
        this.add(clearButton);
        this.add(exitButton);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("came in actionPerformed of ControlPanel");
        if ((e.getActionCommand()).equals("enter"))
        {
            System.out.println("came in actionPerformed of enter");
            frame.setText();
        }

        else if ((e.getActionCommand()).equals("clear"))
        {
            frame.clear();
        }
        
        else if ((e.getActionCommand()).equals("exit"))
        {
            frame.dispose();
        }

   }

}
