package com.sktutilities.panel;

import java.awt.event.ActionEvent;

import com.sktutilities.sandhi.SandhiJFrame;

public class EncodingPanelForSandhi extends EncodingPanel
{
    SandhiJFrame sandhiFrame;
    public EncodingPanelForSandhi(SandhiJFrame sandhiFrame){
        super();
        this.sandhiFrame = sandhiFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        super.actionPerformed(e);
        sandhiFrame.clearTextFields();
    }
}
