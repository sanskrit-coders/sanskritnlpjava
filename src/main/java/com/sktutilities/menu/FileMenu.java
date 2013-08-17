package com.sktutilities.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu implements ActionListener
{
    JMenuItem exitItem;

    boolean   disposeOnly = false;

    JFrame    frame       = null;

    public FileMenu()
    {
        super("File");
        addMenuItems();
    }

    public FileMenu(JFrame frame, boolean disposeOnly)
    {
        super("File");
        this.frame = frame;
        this.disposeOnly = disposeOnly;
        addMenuItems();
    }

    public void addMenuItems()
    {
        exitItem = new JMenuItem("Exit");
        exitItem.setActionCommand("exit_item");
        exitItem.addActionListener(this);
        exitItem.setMnemonic(java.awt.event.KeyEvent.VK_X);
        this.add(exitItem);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("exit_item"))
        {
            if (disposeOnly == false || frame == null)
            {
                System.exit(0);
            }
            else
            {
                frame.dispose();
            }
        }
    }

}
