package com.sktutilities.pratyahara;

import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sktutilities.frame.GenericJFrame;
import com.sktutilities.menu.FileMenu;
import com.sktutilities.menu.HelpMenu;
import com.sktutilities.panel.ControlPanel;
import com.sktutilities.panel.EncodingPanelForPratyahara;
import com.sktutilities.util.EncodingUtil;
import com.sktutilities.util.ProjectTypeEnum;

public class PratyahaarJFrame extends GenericJFrame implements KeyListener, ItemListener
{

    JMenuBar          menubar  = new JMenuBar();

    FileMenu          fileMenu;
    
    HelpMenu          helpMenu;

    JLabel            l1a, l1b, l1c, l2, plus, equal, plus1, equal1;

    JTextField        tf1, tf4;

    JPanel            p1, p2, p3, p4, p6, p7;

    ControlPanel      controlPanel;

    JTextArea         tb1;

    JCheckBox         markerChkBox;

    EncodingUtil      encod;

    PratyaharaDecoder pratyahara;
    
    EncodingPanelForPratyahara encodingPanel;
    
    public PratyahaarJFrame(boolean insideSandhi)
    {
        super("Pratyahara Decoder");
        setSize(650, 550);

        // Initialization
        pratyahara = new PratyaharaDecoder();
        encod = new EncodingUtil();
        
        // menubar
        menubar = new JMenuBar();
        controlPanel = new ControlPanel(this);
        encodingPanel = new EncodingPanelForPratyahara(this);
        // menus
        fileMenu = new FileMenu(this, insideSandhi);
        helpMenu = new HelpMenu(insideSandhi == true ? ProjectTypeEnum.PRATYAHARA_INSIDE_SANDHI: ProjectTypeEnum.PRATYAHARA);
        
        // menuitems
//        description = new JMenuItem("Siva Sutras of Panini");
//        description.setActionCommand("description");
//        description.addActionListener(this);


        // add menus to menubar
        menubar.add(fileMenu);
        menubar.add(helpMenu);
        // menus end

        // Panel Initialization
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p6 = new JPanel();
        p7 = new JPanel();

        Container contentPane = getContentPane();

        // JLabels
        l1a = new JLabel("Please enter a pratyahAra");
        l2 = new JLabel("Notes:");
        plus = new JLabel(" + ");
        equal = new JLabel(" = ");

        // JTextArea
        tf1 = new JTextField(4);
        tf1.addKeyListener(this);
        tf1.setText("al");
        tf4 = new JTextField(4);
        // tf4.addKeyListener(this);
        tf4.setEditable(false);
        tf4.setText(EncodingUtil.convertRawItransToDevanagari(tf1.getText()));

        // Check Boxes
        markerChkBox = new JCheckBox("Hide Markers.");
        markerChkBox.setActionCommand("marker_chk");
        markerChkBox.setSelected(false);
        markerChkBox.setToolTipText("Do not display the 'it'-markers.");
        markerChkBox.addItemListener(this);

        tb1 = new JTextArea(14, 45);
        tb1.setLineWrap(true);
        tb1.setWrapStyleWord(true);

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        // adding Components to JPanel
        p1.add(l1a);
        p2.add(tf1);
        p2.add(tf4);
        p2.add(markerChkBox);
        p6.add(l2);
        p7.add(new JScrollPane(tb1));

        this.setJMenuBar(menubar);

        // add panels to contentpane
        contentPane.add(p1);
        contentPane.add(p2);
        contentPane.add(p3);
        contentPane.add(controlPanel);
        contentPane.add(encodingPanel);
        contentPane.add(p6);
        contentPane.add(p7);

        this.setVisible(true);
    }

    public void itemStateChanged(ItemEvent e)
    {

        Object source = e.getItemSelectable();
        if (source == markerChkBox)
        {
            if (e.getStateChange() == ItemEvent.SELECTED)
            {
                tb1.setText("The 'it' markers will not be displayed.");
            }
            else if (e.getStateChange() == ItemEvent.DESELECTED)
            {
                tb1.setText("The 'it' markers will be displayed.");
            }

        }
    } // end of itemStateChanged

    // The three events
    public void keyTyped(KeyEvent e)
    {
    }

    public void keyPressed(KeyEvent e)
    {
    }

    public void keyReleased(KeyEvent e)
    {
        if (e.getComponent() == this.tf1)
        {
            
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                tb1.setText(pratyahara.decodePratyahara(EncodingUtil.convertToSLP(tf1.getText().trim(), encodingPanel.getEncoding()), markerChkBox.isSelected()));
            }
            else
            {
                tf4.setText(EncodingUtil.convertToDVN(tf1.getText().trim(), encodingPanel.getEncoding()));
            }
        }

    }


    public void setText()
    {
        tb1.setText(pratyahara.decodePratyahara(EncodingUtil.convertToSLP(tf1.getText().trim(), encodingPanel.getEncoding()), markerChkBox.isSelected()));
        
    }
    public void clear()
    {
        encodingPanel.setEncodingToDefault();
        markerChkBox.setSelected(false);
        clearTxtFields();
    }
    
    public void clearTxtFields()
    {
        tf1.setText("");
        tf4.setText("");
        tb1.setText("");
    }

    public static void main(String args[]){
        PratyahaarJFrame frame = new PratyahaarJFrame(false);
        frame.setVisible(true);
    }
} // end of pratyahaar
