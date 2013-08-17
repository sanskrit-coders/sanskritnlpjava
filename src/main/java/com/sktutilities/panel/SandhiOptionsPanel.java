package com.sktutilities.panel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import com.sktutilities.sandhi.SandhiJFrame;
import com.sktutilities.util.Log;

public class SandhiOptionsPanel extends JPanel  implements ItemListener
{
    JCheckBox                  padantaCheck, pragrhyaCheck;

    public SandhiOptionsPanel(){
        super();
        padantaCheck = new JCheckBox("Padanta");
        padantaCheck.setActionCommand("padanta");
        padantaCheck.addItemListener(this);
        padantaCheck.setToolTipText("Click if the first item entered is a Pada.");

        pragrhyaCheck = new JCheckBox("PragRRihya");
        pragrhyaCheck.setActionCommand("pragrhya");
        pragrhyaCheck.addItemListener(this);
        pragrhyaCheck.setToolTipText("Click if Item 1 is a pragRRihya.");
        
        this.add(padantaCheck);
        this.add(pragrhyaCheck);
    }

    @Override
    public void itemStateChanged(ItemEvent e)
    {
            Object source = e.getItemSelectable();
            
            if (source == padantaCheck)
            {

                if (e.getStateChange() == ItemEvent.DESELECTED)
                {
                    SandhiJFrame.padanta = false;
                }
                else if (e.getStateChange() == ItemEvent.SELECTED)
                {
                    SandhiJFrame.padanta = true;
                }
                Log.logInfo("Padanta has been Altered, padanta == " + SandhiJFrame.padanta);

            }

            if (source == pragrhyaCheck)
            {

                if (e.getStateChange() == ItemEvent.DESELECTED)
                {
                    SandhiJFrame.pragrhya = false;
                }
                else if (e.getStateChange() == ItemEvent.SELECTED)
                {
                    SandhiJFrame.pragrhya = true;
                }

                Log.logInfo("Pragrhya Option has been Altered, pragrhya == " + SandhiJFrame.pragrhya);

            }
        }

    public JCheckBox getPadantaCheck()
    {
        return padantaCheck;
    }

    public void setPadantaCheck(boolean val)
    {
        padantaCheck.setSelected(val);
        SandhiJFrame.padanta = val;
        
    }

    public JCheckBox getPragrhyaCheck()
    {
        return pragrhyaCheck;
    }

    public void setPragrhyaCheck(boolean val)
    {
        pragrhyaCheck.setSelected(val);
        SandhiJFrame.pragrhya = val;
    }
}
