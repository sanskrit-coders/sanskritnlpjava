package com.sktutilities.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.sktutilities.util.EncodingUtil;
import com.sktutilities.util.Log;

public class EncodingPanel extends JPanel implements ActionListener
{
    JRadioButton               itransRB, slpRB, hkRB, dvnRB, iastRB;
    
    private String encoding = EncodingUtil.DEFAULT_ENCODING;
    
    public EncodingPanel(){
        super();
        // JRadioButtons
        itransRB = new JRadioButton("ITRANS");
        itransRB.setSelected(true);
        itransRB.setActionCommand("itransRB");
        itransRB.addActionListener(this);

        slpRB = new JRadioButton("SLP");
        slpRB.setActionCommand("slpRB");
        slpRB.addActionListener(this);

        hkRB = new JRadioButton("HK");
        hkRB.setActionCommand("hkRB");
        hkRB.addActionListener(this);

        dvnRB = new JRadioButton("Devanagari");
        dvnRB.setActionCommand("dvnRB");
        dvnRB.addActionListener(this);

        iastRB = new JRadioButton("IAST");
        iastRB.setActionCommand("iastRB");
        iastRB.addActionListener(this); 

        // Group the radio buttons.
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(itransRB);
        buttonGroup.add(slpRB);
        buttonGroup.add(hkRB);
        buttonGroup.add(dvnRB);
        buttonGroup.add(iastRB);
        
        this.add(itransRB);
        this.add(slpRB);
        this.add(hkRB);
        this.add(dvnRB);
        this.add(iastRB);
        
        
    }

   
    public void setEncodingRB(String encoding){
        if(encoding.equals(EncodingUtil.SLP)){
            slpRB.setSelected(true);
        }
        else if(encoding.equals(EncodingUtil.HK)){
            hkRB.setSelected(true);
        }
        else if(encoding.equals(EncodingUtil.UNICODE_DVN)){
            dvnRB.setSelected(true);
        }
        else if(encoding.equals(EncodingUtil.IAST)){
            iastRB.setSelected(true);
        }
        else{
            itransRB.setSelected(true);
        }
        Log.logInfo("Encoding set to " + encoding);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
      //clearTextFields();
        if ((e.getActionCommand()).equals("itransRB"))
        {
            encoding = EncodingUtil.ITRANS;
        }
        else if ((e.getActionCommand()).equals("slpRB"))
        {
            encoding = EncodingUtil.SLP;
        }
        else if ((e.getActionCommand()).equals("hkRB"))
        {
            encoding = EncodingUtil.HK;
        }
        else if ((e.getActionCommand()).equals("dvnRB"))
        {
            encoding = EncodingUtil.UNICODE_DVN;
        }
        else if ((e.getActionCommand()).equals("iastRB"))
        {
            encoding = EncodingUtil.IAST;
        }        
    }

    public String getEncoding()
    {
        return encoding;
    }


    public void setEncoding(String encoding)
    {
        this.encoding = encoding;
    }
    
    public void setEncodingToDefault(){
        encoding = EncodingUtil.DEFAULT_ENCODING;
        itransRB.setSelected(true);
    }
}
