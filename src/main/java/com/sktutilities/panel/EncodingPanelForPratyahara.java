package com.sktutilities.panel;

import java.awt.event.ActionEvent;

import com.sktutilities.pratyahara.PratyahaarJFrame;

public class EncodingPanelForPratyahara extends EncodingPanel
{
    PratyahaarJFrame pratyaharaFrame;
    public EncodingPanelForPratyahara(PratyahaarJFrame pratyaharaFrame){
        super();
        this.pratyaharaFrame = pratyaharaFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        super.actionPerformed(e);
        pratyaharaFrame.clearTxtFields();
    }
}
