package com.sktutilities.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.sktutilities.transliteration.RTFDocsSwingDisplayer;
import com.sktutilities.util.Log;
import com.sktutilities.util.ProjectTypeEnum;

public class HelpMenu extends JMenu implements ActionListener
{
    JMenuItem        itransItem, aboutItem, notesItem, slpItem, hkItem, iastItem;
    
    public static final String DOC_PATH      = "";

    private ProjectTypeEnum projectTypeEnum;
    
    private String notesFile;
    
    private String projectName;

    private String projectJarName;
    
    public HelpMenu(ProjectTypeEnum projectTypeEnum){
        super("Help");
        this.projectTypeEnum = projectTypeEnum;
        setDefaults();
        aboutItem = new JMenuItem("About " + projectName);
        aboutItem.setActionCommand("about_item");
        aboutItem.addActionListener(this);

        itransItem = new JMenuItem("ITRANS ENCODING SCHEME");
        itransItem.setActionCommand("itrans_encoding");
        itransItem.addActionListener(this);

        slpItem = new JMenuItem("SLP ENCODING SCHEME");
        slpItem.setActionCommand("slp_encoding");
        slpItem.addActionListener(this);
        
        hkItem = new JMenuItem("HK ENCODING SCHEME");
        hkItem.setActionCommand("hk_encoding");
        hkItem.addActionListener(this);
        
        iastItem = new JMenuItem("IAST ENCODING SCHEME");
        iastItem.setActionCommand("iast_encoding");
        iastItem.addActionListener(this);

        notesItem = new JMenuItem("Notes on " + projectName);
        notesItem.setActionCommand("notes_item");
        notesItem.addActionListener(this);
        
        this.add(notesItem);
        this.add(aboutItem);
        this.add(itransItem);
        this.add(slpItem);
        this.add(hkItem);
        this.add(iastItem);
    }

    private void setDefaults()
    {
        System.out.println("projectTypeEnum: " + projectTypeEnum); 
        
       if(projectTypeEnum.equals(ProjectTypeEnum.TRANSLITERATOR)){
           projectName = "Devanagari Transliterator"; 
           projectJarName = "transliterator.jar";
           notesFile = "notes_on_transliterator.rtf"; 
       }
       
       else if(projectTypeEnum.equals(ProjectTypeEnum.PRATYAHARA)){
           projectName = "Pratyahara Decoder";
           projectJarName = "pratyahara.jar";
           notesFile = "siva.rtf"; 
       }
       
       else if(projectTypeEnum.equals(ProjectTypeEnum.PRATYAHARA_INSIDE_SANDHI)){
           projectName = "Pratyahara Decoder";
           projectJarName = "sandhi.jar";
           notesFile = "siva.rtf"; 
       }
       
       // default
       else {
          projectName = "Sandhi Engine";
          projectJarName = "sandhi.jar";
          notesFile = "notes_on_sandhi.rtf"; 
       }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Log.logInfo("Help Mnu Action Performed");
        if ((e.getActionCommand()).equals("itrans_encoding"))
        {
            new RTFDocsSwingDisplayer("Itrans Encoding Scheme", "itrans.rtf", projectJarName);
        } 

        else if ((e.getActionCommand()).equals("slp_encoding"))
        {
             new RTFDocsSwingDisplayer("SLP Encoding Scheme", DOC_PATH + "slp.rtf", projectJarName);
        } 

        else if ((e.getActionCommand()).equals("hk_encoding"))
        {
            new RTFDocsSwingDisplayer("HK Encoding Scheme", DOC_PATH + "hk.rtf", projectJarName);
        } 

        else if ((e.getActionCommand()).equals("iast_encoding"))
        {
            new RTFDocsSwingDisplayer("IAST Encoding Scheme", DOC_PATH + "iast.rtf", projectJarName);
        } 
        
        else if ((e.getActionCommand()).equals("notes_item"))
        {
            new RTFDocsSwingDisplayer("Notes on " + projectName , notesFile , projectJarName);
        } 
        
        else if ((e.getActionCommand()).equals("about_item"))
        {
            String copyright = "\u00A9 2010 All Rights Reserved. Chetan Pandey\n" + "Pls. Contact taddhita_priya@yahoo.com for questions and suggestions.";
            JOptionPane.showMessageDialog(this, copyright, "About " + projectName , JOptionPane.PLAIN_MESSAGE);
        }
        
    }
}
