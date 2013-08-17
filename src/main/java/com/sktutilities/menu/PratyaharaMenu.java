package com.sktutilities.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.sktutilities.pratyahara.PratyahaarJFrame;

public class PratyaharaMenu extends JMenu implements ActionListener
    
{
    JMenuItem pratyaharaItem;
    
    public PratyaharaMenu(){
        super("Pratyahara");
        
        pratyaharaItem = new JMenuItem("Find Pratyahara");
        pratyaharaItem.setActionCommand("pratyahara_item");
        pratyaharaItem.addActionListener(this);
        
        this.add(pratyaharaItem);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if ((e.getActionCommand()).equals("pratyahara_item"))
        {
            new PratyahaarJFrame(true);
        }        
    }

}
