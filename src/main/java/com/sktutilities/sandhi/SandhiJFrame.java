package com.sktutilities.sandhi;

import com.sktutilities.util.*;

import java.util.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

import com.sktutilities.frame.GenericJFrame;
import com.sktutilities.menu.FileMenu;
import com.sktutilities.menu.HelpMenu;
import com.sktutilities.menu.OptionsMenu;
import com.sktutilities.menu.PratyaharaMenu;
import com.sktutilities.panel.ControlPanel;
import com.sktutilities.panel.EncodingPanelForSandhi;
import com.sktutilities.panel.SandhiOptionsPanel;
import com.sktutilities.panel.TrackingPanel;

import java.awt.*;

public class SandhiJFrame extends GenericJFrame implements KeyListener
{

    JMenuBar                   menubar;

    FileMenu fileMenu;
    
    OptionsMenu optionsMenu;

    HelpMenu                   helpMenu;
    
    PratyaharaMenu pratyaharaMenu;
    
    JLabel                     l1a, l1b, l1c, l2, plus, equal, plus1, equal1;

    JTextField                 tf1, tf2, tf3, tf4, tf5, tf6;

    JTextArea                  tb1;

    ControlPanel               controlPanel;
    
    TrackingPanel             trackingPanel;
    
    EncodingPanelForSandhi     encodingPanel;
    
    SandhiOptionsPanel         sandhiOptionsPanel;

    private Vector<SandhiBean> beanVector;

    public Vector<SandhiBean> getBeanVector()
    {
        return beanVector;
    }

    public int getVectorTracker()
    {
        return vectorTracker;
    }

    public int preIncrementVectorTracker()
    {
       return ++vectorTracker;
    }
    
    public int preDeccrementVectorTracker()
    {
       return --vectorTracker;
    }

    private int                vectorTracker = -1;

    static boolean             internal      = false;

    public static boolean             padanta       = false;                        // sup

    // ti~Nantam
    // padam

    public static boolean             pragrhya      = false;

    public static boolean      romanSutra    = false;

    public static final String title         = "Sandhi Engine";

    public static final String DOC_PATH      = "";

    public SandhiJFrame()
    {
        super(title);
        setSize(650, 550);

        // Vector Initialization
        beanVector = new Vector<SandhiBean>();

        // menubar
        menubar = new JMenuBar();
        //
        fileMenu = new FileMenu();
        optionsMenu = new OptionsMenu();
        helpMenu = new HelpMenu(ProjectTypeEnum.SANDHI);
        pratyaharaMenu = new PratyaharaMenu();
        
        // add menus to menubar
        menubar.add(fileMenu);
        menubar.add(optionsMenu);
        menubar.add(pratyaharaMenu);
        menubar.add(helpMenu);
        // menus end

        controlPanel = new ControlPanel(this);
        trackingPanel = new TrackingPanel(this);
        encodingPanel = new EncodingPanelForSandhi(this);
        sandhiOptionsPanel = new SandhiOptionsPanel();
        
        // Jpanel initialization
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p2a = new JPanel();
        JPanel p2b = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();

        Container contentPane = getContentPane();
        l1a = new JLabel("Please enter Words in");
        l1b = new JLabel("ITRANS FORMAT");
        l1c = new JLabel("only");
        l1b.setToolTipText("ITRANS FORMAT ONLY.");

        l2 = new JLabel("Notes:");

        plus = new JLabel(" + ");
        equal = new JLabel(" = ");

        tf1 = new JTextField(10);
        tf1.addKeyListener(this);

        tf2 = new JTextField(10);
        tf2.addKeyListener(this);

        tf3 = new JTextField(20);
        tf3.setEditable(false);

        plus1 = new JLabel(" + ");
        equal1 = new JLabel(" = ");
        tf4 = new JTextField(10);

        tf5 = new JTextField(10);
        tf6 = new JTextField(20);
        tf4.setEditable(false);
        tf5.setEditable(false);
        tf6.setEditable(false);
        tf1.setText("shiva");
        tf2.setText("Alaya");
        tf4.setText(EncodingUtil.convertToDVN(tf1.getText(), EncodingUtil.ITRANS));
        tf5.setText(EncodingUtil.convertToDVN(tf2.getText(), EncodingUtil.ITRANS));




        // JTextArea
        tb1 = new JTextArea(12, 45);
        tb1.setLineWrap(true);
        tb1.setWrapStyleWord(true);
        // tb1.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        // panel additions
        p2.add(tf1);
        p2.add(plus);
        p2.add(tf2);
        p2.add(equal);
        p2.add(tf3);
        p2b.add(tf4);
        p2b.add(plus1);
        p2b.add(tf5);
        p2b.add(equal1);
        p2b.add(tf6);
        p4.add(l2);
        p5.add(new JScrollPane(tb1));

        this.setJMenuBar(menubar);

        contentPane.add(trackingPanel);
        contentPane.add(p2);
        contentPane.add(sandhiOptionsPanel);
        contentPane.add(p2b);
        contentPane.add(controlPanel);
        contentPane.add(encodingPanel);
        contentPane.add(p4);
        contentPane.add(p5);

    }
    
    // The three events
    public void keyTyped(KeyEvent e)
    {
    }// Log.logInfo("keyTyped");

    public void keyPressed(KeyEvent e)
    {
    }// Log.logInfo("keyPressed");

    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            setText();
        else if (e.getComponent() == this.tf1)
        {
            tf4.setText(EncodingUtil.convertToDVN(tf1.getText(), encodingPanel.getEncoding()));
        }

        else if (e.getComponent() == this.tf2)
        {
            tf5.setText(EncodingUtil.convertToDVN(tf2.getText(), encodingPanel.getEncoding()));
        }
    }


    


    public void setText()
    {
        System.out.println("came in setText()");
        String anta = (tf1.getText().toString()).trim();
        String adi = (tf2.getText().toString()).trim();

        // SandhiBean bean = new SandhiBean(anta,adi);
        SandhiBean bean = new SandhiBean(anta, adi, padanta, pragrhya, encodingPanel.getEncoding());
        // Set values for the Textfields and TextAreas
        tf3.setText(bean.getRomanInput3());
        tf4.setText(bean.getDvnInput1());
        tf5.setText(bean.getDvnInput2());
        tf6.setText(bean.getDvnInput3());
        tb1.setText(bean.getNotes());
        tb1.setCaretPosition(0);

        beanVector.add(bean);
        if (beanVector.size() > 1) trackingPanel.getPreviousItem().setEnabled(true);
        vectorTracker = beanVector.size() - 1;

        // Disable "next Button" if vector_tracker points to the last element
        if (vectorTracker >= beanVector.size() - 1) trackingPanel.getNextItem().setEnabled(false);
    }

    public void setText(SandhiBean bean)
    {
        tf1.setText(bean.getRomanInput1());
        tf2.setText(bean.getRomanInput2());
        tf3.setText(bean.getRomanInput3());
        tf4.setText(bean.getDvnInput1());
        tf5.setText(bean.getDvnInput2());
        tf6.setText(bean.getDvnInput3());
        
        sandhiOptionsPanel.setPadantaCheck(bean.isPadanta());
        sandhiOptionsPanel.setPragrhyaCheck(bean.isPragrhya());
        tb1.setText(bean.getNotes());
        tb1.setCaretPosition(0);

    }

    public void clear()
    {
        sandhiOptionsPanel.setPadantaCheck(false);
        sandhiOptionsPanel.setPragrhyaCheck(false);
        clearTextFields();
        encodingPanel.setEncodingToDefault();
    }

    public void clearTextFields()
    {
        tf1.setText("");
        tf2.setText("");
        tf3.setText("");
        tf4.setText("");
        tf5.setText("");
        tf6.setText("");
        tb1.setText("");

    }

    public static void main(String[] args) throws IOException
    {
        SandhiJFrame san = new SandhiJFrame();
        san.setVisible(true);

    } // end of main

} // end of sandhi
