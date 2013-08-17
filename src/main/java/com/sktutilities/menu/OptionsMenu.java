package com.sktutilities.menu;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;

import com.sktutilities.sandhi.SandhiJFrame;

public class OptionsMenu extends JMenu implements ItemListener
{
    JCheckBoxMenuItem          dvnSutra;

    public OptionsMenu()
    {
        super("Options");
        dvnSutra = new JCheckBoxMenuItem("Sutra Text in DVN.");
        dvnSutra.setActionCommand("dvn_sutra");
        dvnSutra.setSelected(true);
        dvnSutra.addItemListener(this);
        // add menuitems to menu
        this.add(dvnSutra);

    }

    @Override
    public void itemStateChanged(ItemEvent e)
    {
            if (e.getStateChange() == ItemEvent.DESELECTED)
            {
                SandhiJFrame.romanSutra = true;
            }
            else if (e.getStateChange() == ItemEvent.SELECTED)
            {
                SandhiJFrame.romanSutra = false;
            }
    }

}
