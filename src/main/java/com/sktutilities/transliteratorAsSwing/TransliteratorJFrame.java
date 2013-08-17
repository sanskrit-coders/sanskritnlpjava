package com.sktutilities.transliteratorAsSwing;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.PlainDocument;
import javax.swing.text.rtf.RTFEditorKit;

import com.sktutilities.menu.HelpMenu;
import com.sktutilities.sandhi.DisplayMessage;
import com.sktutilities.util.EncodingUtil;
import com.sktutilities.util.Log;
import com.sktutilities.util.ProjectTypeEnum;

public class TransliteratorJFrame extends JFrame implements ActionListener, KeyListener, ClipboardOwner
{

    //private static final Icon CLIPBOARD = null;
    private static String CLIPBOARD = "Clipboard";
    JMenuBar menubar = new JMenuBar();

    JMenu    fileMenu;
    
    HelpMenu                   helpMenu;

    JMenuItem exitItem, save_1, save_2, save_3, open_1, open_2, open_3;

    JLabel    label1, label2, label3;

    //JCheckBoxMenuItem useItrans, useSLP, useHK;

    JButton           refreshButton, clearButton, exitButton, clipboardButton1, clipboardButton2, clipboardButton3;
    
    JButton           saveButton1, saveButton2, saveButton3, openButton;
    
    ButtonGroup      encodingButtonGroup;
    
    JRadioButton     itransRadioButton, slpRadioButton, hkRadioButton;

    JPanel            p1, p2, p3, p4, p5, p6, p7, encodingPanel;

    JTextArea         tb1, tb2, tb3;

    static String     encoding = EncodingUtil.ITRANS;

    JTextPane         textPane;

    String dialogHeader = "Encoding Switch";
    String dialog = "Changing Encoding will clear all Text.\n " + "Do you want to continue?";

    public TransliteratorJFrame()
    {
        super("Devanagari Transliterator");

        PrintWriter pw = new PrintWriter(System.out, true);
        setSize(650, 650);

        // menubar
        menubar = new JMenuBar();

        // menus
        fileMenu = new JMenu("File");
        helpMenu = new HelpMenu(ProjectTypeEnum.TRANSLITERATOR);

        // JMenuItem
        save_1 = new JMenuItem("Save Roman Text");
        save_1.setActionCommand("save_1");
        save_1.addActionListener(this);

        save_2 = new JMenuItem("Save Devanagari Text");
        save_2.setActionCommand("save_2");
        save_2.addActionListener(this);

        save_3 = new JMenuItem("Save Extended Latin Text");
        save_3.setActionCommand("save_3");
        save_3.addActionListener(this);

        open_1 = new JMenuItem("Open Roman Text in Area 1");
        open_1.setActionCommand("open_1");
        open_1.addActionListener(this);

        open_2 = new JMenuItem("Open Devanagari Text in Area 2");
        open_2.setActionCommand("open_2");
        open_2.addActionListener(this);

        open_3 = new JMenuItem("Open Extended Latin Text in Area 3");
        open_3.setActionCommand("open_3");
        open_3.addActionListener(this);

        exitItem = new JMenuItem("Exit");
        exitItem.setActionCommand("exit_item");
        exitItem.addActionListener(this);

        String clipBooardString = "Clipboard Contents from Below";
        Icon clipBoardIcon = new ImageIcon("../icon_clipboard.gif");
        
        clipboardButton1 = new JButton(CLIPBOARD);
        //clipboardButton1.setIcon(clipBoardIcon);
        clipboardButton1.setToolTipText(clipBooardString);
        clipboardButton1.setActionCommand("clipboard1");
        clipboardButton1.addActionListener(this);

        clipboardButton2 = new JButton(CLIPBOARD);
        clipboardButton2.setToolTipText(clipBooardString);
        clipboardButton2.setActionCommand("clipboard2");
        clipboardButton2.addActionListener(this);

        clipboardButton3 = new JButton(CLIPBOARD);
        clipboardButton3.setToolTipText(clipBooardString);
        clipboardButton3.setActionCommand("clipboard3");
        clipboardButton3.addActionListener(this);

        saveButton1 = new JButton("Save");
       // saveButton1.setIcon(clipBoardIcon);
        saveButton1.setToolTipText("Save Roman Text");
        saveButton1.setActionCommand("save_1");
        saveButton1.addActionListener(this);

        saveButton2 = new JButton("Save");
        saveButton2.setToolTipText("Save Devanagari Text");
        saveButton2.setActionCommand("save_2");
        saveButton2.addActionListener(this);

        saveButton3 = new JButton("Save");
        saveButton3.setToolTipText("Save eLatin Text");
        saveButton3.setActionCommand("save_3");
        saveButton3.addActionListener(this);
        
        openButton = new JButton("Open");
        openButton.setToolTipText("Open");
        openButton.setActionCommand("open_1");
        openButton.addActionListener(this);
        
        
        //JRadio Button
        itransRadioButton = new JRadioButton("USe ITRANS");
        itransRadioButton.setActionCommand("useItrans");
        itransRadioButton.setEnabled(false);
        itransRadioButton.setSelected(true);
        itransRadioButton.addActionListener(this);

        slpRadioButton = new JRadioButton("USE SLP");
        slpRadioButton.setActionCommand("useSLP");
        slpRadioButton.setSelected(false);
        slpRadioButton.addActionListener(this);

        hkRadioButton = new JRadioButton("USE Harvard Kyoto");
        hkRadioButton.setActionCommand("useHK");
        hkRadioButton.setSelected(false);
        hkRadioButton.addActionListener(this);
        
        //ButtonGroup
        encodingButtonGroup = new ButtonGroup();
        
        encodingButtonGroup.add(itransRadioButton);
        encodingButtonGroup.add(slpRadioButton);
        encodingButtonGroup.add(hkRadioButton);
        
        // add menuitems to menu
        fileMenu.add(open_1);
        fileMenu.add(save_1);
        fileMenu.add(save_2);
        fileMenu.add(save_3);
        // file.add(open_2);
        // file.add(open_3);
        fileMenu.add(exitItem);

        // add menus to menubar
        menubar.add(fileMenu);
        menubar.add(helpMenu);
        // menus end

        // JPanel Initilization
        p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2 = new JPanel();
        p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));;
        p4 = new JPanel();
        p5 = new JPanel(new FlowLayout(FlowLayout.LEFT));;
        p6 = new JPanel();
        p7 = new JPanel();
        encodingPanel = new JPanel();

        // JLabel Initialization
        label1 = new JLabel("Roman Input");
        label2 = new JLabel("For Devanagari Output");
        label3 = new JLabel("For Extended Latin Output");

        // Buttons
        clearButton = new JButton("Clear");
        clearButton.setActionCommand("clear");
        clearButton.setToolTipText("Clear all Fields");
        clearButton.addActionListener(this);
        
        refreshButton = new JButton("Refresh");
        refreshButton.setActionCommand("refresh");
        refreshButton.setToolTipText("Refesh the View");
        refreshButton.addActionListener(this);
        
        exitButton = new JButton("Exit");
        exitButton.setActionCommand("Exit");
        exitButton.setToolTipText("Quit the Application.");
        exitButton.addActionListener(this);
        
        Container contentPane = getContentPane();
        // JTextBox
        tb1 = new JTextArea(new PlainDocument(), null, 6, 45);
        tb1.setLineWrap(true);
        tb1.setWrapStyleWord(true);
        tb1.addKeyListener(this);

        tb2 = new JTextArea(new PlainDocument(), null, 6, 45);
        tb2.setLineWrap(true);
        tb2.setWrapStyleWord(true);
        tb2.addKeyListener(this);

        tb3 = new JTextArea(new PlainDocument(), null, 6, 45);
        tb3.setLineWrap(true);
        tb3.setWrapStyleWord(true);
        tb3.addKeyListener(this);

        // Setting Fonts
        Font f1 = new Font("Arial", Font.PLAIN, 18);
        tb1.setFont(f1);

        Font f2 = new Font("Unicode", Font.PLAIN, 18);
        tb2.setFont(f2);

        Font f3 = new Font("Unicode", Font.PLAIN, 18);
        tb3.setFont(f3);

        /** *EXPERIMENT*** */
        textPane = new JTextPane();
        RTFEditorKit rtfkit = new RTFEditorKit();
        // HTMLEditorKit htmlkit = new HTMLEditorKit();
        textPane.setEditorKit(rtfkit); // set Kit which will read RTF Doc
        // textPane.setEditorKit(htmlkit);
        textPane.setEditable(false); // make uneditable
        textPane.setPreferredSize(new Dimension(200, 200));
        textPane.setText(" I am very very sorry, mujh ko thA kucch kaam/n vo kaam bhoola gai"); // set
        // the
        // Text
        // textPane.setCaretPosition(0); // set Cret position to 0
        /** *EXPERIMENT*** */

  
        p1.add(label1);
        p1.add(clipboardButton1);
        p1.add(saveButton1);
        p1.add(openButton);
        
        p2.add(new JScrollPane(tb1));
        p3.add(label2);
        p3.add(clipboardButton2);
        p3.add(saveButton2);
        
        p4.add(new JScrollPane(tb2));
        p5.add(label3);
        p5.add(clipboardButton3);
        p5.add(saveButton3);
        p6.add(new JScrollPane(tb3));
        encodingPanel.add(itransRadioButton);
        encodingPanel.add(slpRadioButton);
        encodingPanel.add(hkRadioButton);
        p7.add(clearButton);
        p7.add(refreshButton);
        p7.add(exitButton);
        this.setJMenuBar(menubar);

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        contentPane.add(p1);
        contentPane.add(p2);
        contentPane.add(p3);
        contentPane.add(p4);
        contentPane.add(p5);
        contentPane.add(p6);
        contentPane.add(encodingPanel);
        contentPane.add(p7);

        /** **Experiment**** */
        /***********************************************************************
         * try{
         * 
         * PrintStream console = System.out;
         * 
         * PrintStream out = new PrintStream( new BufferedOutputStream( new
         * FileOutputStream("test.out")));
         * 
         * System.setOut(out); /****Experiment
         **********************************************************************/
        /*
         * }
         * 
         * catch(IOException ioe) {}
         */

    }

    // *******************BEGINNING OF FUNCTION********************//
    String input = "";

    // The three events
    public void keyTyped(KeyEvent e)
    {
    }

    public void keyPressed(KeyEvent e)
    {
    }

    public void keyReleased(KeyEvent e)
    {
        if (e.getComponent() == this.tb1)
        {
            setText();
        }

    }

    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        
        if (actionCommand.equals("clear"))
        {
            clear();
        }

        else if (actionCommand.equals("Exit"))
        {
            System.exit(0);
        }

        else if (actionCommand.equals("clipboard1"))
        {
            copyToClipBoard("cb1");
        }

        else if (actionCommand.equals("clipboard2"))
        {
            copyToClipBoard("cb2");
        }

        else if (actionCommand.equals("clipboard3"))
        {
            copyToClipBoard("cb3");
        }

        else if (actionCommand.equals("convert"))
        {
            setText();
        }

        else if (actionCommand.equals("refresh"))
        {
            this.repaint();
        }

        // /********MENU ITEM***********/////

        else if (actionCommand.equals("exit_item"))
        {
            System.exit(0);
        }

        // //*******MENU ITEM**********/////
        else if (actionCommand.equals("save_1") || actionCommand.equals("save1") )
        {
            saveText(tb1);
        }
        else if (actionCommand.equals("save_2"))
        {
            saveText(tb2);
        }

        else if (actionCommand.equals("save_3"))
        {
            saveText(tb3);
        }

        else if (actionCommand.equals("open_1"))
        {
            chooseFile(tb1);
            setText();
        }
        else if (actionCommand.equals("open_2"))
        {
            chooseFile(tb2);
        }

        else if (actionCommand.equals("open_3"))
        {
            chooseFile(tb3);
        }

        else if( actionCommand.equals("useItrans") || actionCommand.equals("useSLP") || actionCommand.equals("useHK"))
        {
            int confirmation = JOptionPane.showConfirmDialog(tb1, dialog, dialogHeader, JOptionPane.OK_CANCEL_OPTION);
            Log.logInfo("confirmation: " + confirmation);
            if ( actionCommand.equals("useItrans"))
            {
                if (confirmation == 0)
                {
                    clear();
                    encoding = EncodingUtil.ITRANS;
                    itransRadioButton.setEnabled(false);
                    slpRadioButton.setEnabled(true);
                    hkRadioButton.setEnabled(true);

                    itransRadioButton.setSelected(true);
                    slpRadioButton.setSelected(false);
                    hkRadioButton.setSelected(false);
                }

                else
                {
                    itransRadioButton.setSelected(false);
                }
            }

            else if ( "useHK".equals(actionCommand))
            {
                if (confirmation == 0)
                {
                    clear();
                    encoding = EncodingUtil.HK;
                    hkRadioButton.setEnabled(false);
                    slpRadioButton.setEnabled(true);
                    itransRadioButton.setEnabled(true);

                    itransRadioButton.setSelected(false);
                    slpRadioButton.setSelected(false);
                    hkRadioButton.setSelected(true);
                }
                else
                {
                    hkRadioButton.setSelected(false);
                }
            }

            else if ( "useSLP".equals(actionCommand))
            {
                if (confirmation == 0)
                {
                    clear();
                    encoding = EncodingUtil.SLP;
                    slpRadioButton.setEnabled(false);
                    itransRadioButton.setEnabled(true);
                    hkRadioButton.setEnabled(true);

                    itransRadioButton.setSelected(false);
                    slpRadioButton.setSelected(true);
                    hkRadioButton.setSelected(false);
                }
                else
                {
                    slpRadioButton.setSelected(false);
                }
            }
        }
        }
        

    // *******************END OF FUNCTION**********************//

    public void saveText(JTextArea jta)
    {
        JFileChooser chooser = new JFileChooser();

        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Ask User to choose File
        int option = chooser.showSaveDialog(this);

        Log.logInfo("JFileChooser.APPROVE_OPTION  = " + JFileChooser.APPROVE_OPTION);

        // If User chooses a to open a File
        if (option == JFileChooser.APPROVE_OPTION)
        {
            // If the User chooses a File
            if (chooser.getSelectedFile() != null)
            {
                Log.logInfo("You chose to save this file as " + chooser.getSelectedFile().getPath());

                // Complete Name of File Chosen
                String path = chooser.getSelectedFile().getAbsolutePath();
                Log.logInfo("File name chosen for saving is " + path);

                // Short Name of File Chosen
                String shortpath = chooser.getSelectedFile().getName();
                Log.logInfo("Short File name chosen for saving is " + shortpath);

                // If File doesnt end with .rtf then automatically provide it
                if (!path.endsWith(".txt")) path += ".txt";

                File file = new File(path);

                // If their is no File by that Name
                if (!file.exists())
                {
                    try
                    {
                        file.createNewFile();
                        writeFile(file, jta);
                        /*
                         * if(writeFile(file,jta)) { CURRENT_FILE =
                         * file.getAbsolutePath(); save_button.setEnabled(true); }
                         */
                    } // end of try

                    // Catch handles the situation when a filename entered is
                    // errorneous
                    catch (IOException ioe)
                    {
                        // ioe.printStackTrace();
                        Log.logInfo("IOException");
                        String err_msg = "The filename, directory name, or volume label syntax of file name specified \n\"" + file.getName() + "\" \n" + "in" + "\n" + "\"" + file.getParent() + "\"\nis incorrect.\nPls try again";
                        JOptionPane.showMessageDialog(jta, err_msg, "Input/Output Error", JOptionPane.ERROR_MESSAGE);
                    } // end of catch

                }// end of if (!file.exists())

                // If a File of that name exists already.
                else
                { // File Already Exists...Permission to Overwrite
                    String overwrite = "File \n\"" + file.getName() + "\" \n" + "in" + "\n\"" + file.getParent() + "\"\n will be overwritten. Continue?";

                    int resp = (new DisplayMessage()).yes_no_dialog(p2, overwrite);
                    if (resp == 0) writeFile(file, jta);
                } // end of else

            } // end of if (chooser.getSelectedFile() != null

        }

    } // end of function

    public void chooseFile(JTextArea jta)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);

        int option = chooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION)
        {
            if (chooser.getSelectedFile() != null)
            {
                Log.logInfo("You chose to open this file: " + chooser.getSelectedFile().getPath());
                String path = chooser.getSelectedFile().getPath();
                readFile(new File(path), jta);
            }
        }
    }

    public void clear()
    {
        tb1.setText("");
        tb2.setText("");
        tb3.setText("");
    }


    public boolean readFile(File file, JTextArea jta)
    {

        FileInputStream fist = null;
        InputStreamReader isre = null;
        BufferedReader bre = null;

        try
        {
            // read a Unicode string from a file
            fist = new FileInputStream(file);

            Log.logInfo("jta == tb1 " + (jta == tb1));
            if (jta == tb1)
                isre = new InputStreamReader(fist);
            else
                isre = new InputStreamReader(fist, "UNICODE");
            bre = new BufferedReader(isre);

            Log.logInfo("getEncoding == " + isre.getEncoding());
            String txt = "";
            int filereader = 0;

            while ((filereader = fist.read()) != -1)
            {
                Log.logInfo(filereader);
                txt += (char) filereader;
            }

            OutputStreamWriter osw = new OutputStreamWriter(System.out, "Unicode");
            osw.write("\nTEST TO CHECK OSW ****\u0915****");

            jta.setText(txt);
            bre.close();
            return true;
        }

        catch (FileNotFoundException fnf)
        {
            Log.logInfo("FileNotFoundException");
            String err_msg = "Filename you entereded \n" + "\"" + file.getName() + "\"" + "\n" + "in" + "\n" + "\"" + file.getParent() + "\"" + "\nwas not found.\nPls try again";
            JOptionPane.showMessageDialog(jta, err_msg, "File Not Found Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        catch (IOException ioe)
        {
            Log.logInfo("IOException");
            String err_msg = "Input/Output Error when reading from file\n" + "\"" + file.getName() + "\"" + "\n" + "in" + "\n" + "\"" + file.getParent() + "\"" + "\nPls try again";
            JOptionPane.showMessageDialog(jta, err_msg, "Input/Output Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public boolean writeFile(File file, JTextArea jta)
    {

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try
        {
            fos = new FileOutputStream(file);
            if (jta == tb1)
                osw = new OutputStreamWriter(fos);
            else
                osw = new OutputStreamWriter(fos, "Unicode");
            bw = new BufferedWriter(osw);

            String txt = jta.getText();

            OutputStreamWriter oswr = new OutputStreamWriter(System.out, "Unicode");
            oswr.write("\nTEST TO CHECK OSW *\u0915*\u0916*\u0917*\u0918*\u0919*\u0920*\u0921*");
            oswr.flush();
            for (int i = 0; i < txt.length(); i++)
            {
                // Log.logInfo(txt.charAt(i));
                oswr.write(txt.charAt(i));
                oswr.flush();
                bw.write(txt.charAt(i));
            }
            bw.flush();
            bw.close();
            return true;
        }

        catch (FileNotFoundException fnf)
        {
            Log.logInfo("FileNotFoundException");
            String err_msg = "File name you entered \"" + file.getPath() + "\" not found.\nPls try again";
            JOptionPane.showMessageDialog(jta, err_msg, "File Not Found Error", JOptionPane.ERROR_MESSAGE);
            // fnf.printStackTrace();
            return false;
        }

        catch (IOException ioe)
        {
            Log.logInfo("IOException");
            String err_msg = "File name you entered \"" + file.getPath() + "\" cannot be found.\nPls try again";
            JOptionPane.showMessageDialog(jta, err_msg, "Input/Output Error", JOptionPane.ERROR_MESSAGE);
            // ioe.printStackTrace();
            return false;
        }

    }

    // *******************END OF FUNCTION**********************//

    public void copyToClipBoard(String source)
    {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        //Transferable content = clipboard.getContents(this);
        try
        {
            //String data = (String) content.getTransferData(DataFlavor.stringFlavor);
            StringSelection contents;
            if ("cb1".equalsIgnoreCase(source))
                contents = new StringSelection(tb1.getText());
            else if ("cb2".equalsIgnoreCase(source))
                contents = new StringSelection(tb2.getText());
            else
                contents = new StringSelection(tb3.getText());
            clipboard.setContents(contents, this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException
    {
        TransliteratorJFrame u1 = new TransliteratorJFrame();
        u1.setVisible(true);

    } // end of main

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents)
    {

    }

    public void setText()
    {
        tb2.setText(EncodingUtil.convertToDVN(tb1.getText(), encoding));
        tb3.setText(EncodingUtil.convertToIAST(tb1.getText(), encoding));
    }
} // end of sandhi
