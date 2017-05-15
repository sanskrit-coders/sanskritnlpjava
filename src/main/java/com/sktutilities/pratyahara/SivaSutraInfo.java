package com.sktutilities.pratyahara;

import com.sktutilities.transliteration.*;
import com.sktutilities.util.Log;
import com.sktutilities.util.SivaSutra;

import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JTextPane;
import javax.swing.text.rtf.*;

public class SivaSutraInfo extends JFrame implements ActionListener
{

    private JEditorPane edit1;

    JButton             b1, b2, b3;

    JPanel              p1;

    JTextArea           tb1;

    JEditorPane         editorPane;

    public SivaSutraInfo()
    {
        super("The Siva Sutras of Panini");
        setSize(650, 550);

        // Buttons

        b1 = new JButton("Back");

        b1.setActionCommand("back");
        b1.addActionListener(this);
        // Panels
        p1 = new JPanel();
        // new JScrollPane(this.p1);

        String pane_text = "";
        // pane_text = read_file("siva1.rtf");
        // pane_text += sutras();
        // pane_text += read_file("siva2.rtf");
        Container contentPane = getContentPane();

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(p1);

        tb1 = new JTextArea(24, 45);
        tb1.setText(pane_text);
        tb1.setLineWrap(true);
        tb1.setWrapStyleWord(true);

        String display_me = readFile("siva.rtf");
        editorPane = new JEditorPane("text/rtf", sutras(false));
        editorPane.setEditable(false);

        // Put the editor pane in a scroll pane.
        JScrollPane editorScrollPane1 = new JScrollPane(editorPane);
        editorScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane1.setPreferredSize(new Dimension(5, 2));
        editorScrollPane1.setMinimumSize(new Dimension(10, 10));

        JTextPane textPane = new JTextPane();
        RTFEditorKit rtfkit = new RTFEditorKit();
        // HTMLEditorKit htmlkit = new HTMLEditorKit();
        textPane.setEditorKit(rtfkit);
        textPane.setEditable(false);

        /*
         * StyleContext sc = new StyleContext(); DefaultStyledDocument dse = new
         * DefaultStyledDocument(sc); textPane.setDocument(dse);
         * 
         * JScrollPane editorScrollPane = new JScrollPane(textPane);
         * editorScrollPane.setVerticalScrollBarPolicy(
         * JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
         * editorScrollPane.setPreferredSize(new Dimension(15, 7));
         * editorScrollPane.setMinimumSize(new Dimension(10, 10));
         */textPane.setText(display_me /* pane_text */);
        // addStylesToDocument(doc);
        textPane.setCaretPosition(0);
        // textPane.scrollRectToVisible(display_me.modelToView(0));

        int dot = textPane.getCaretPosition();
        int len = textPane.getDocument().getLength();
        Log.logInfo("dot position == " + dot + " len == " + len);
        // panel additons
        p1.setLayout(new BoxLayout(this.p1, BoxLayout.Y_AXIS));
        // p1.add(new JScrollPane(tb1));
        p1.add(new JScrollPane(textPane));
        // p1.add(new JScrollPane(editorPane));
        p1.add(b1);
        setVisible(true);

    }

    public String sutras(boolean marker)
    {
        String returnMe = "";
        boolean restore = false;
        SLPToDevanagari dvn = new SLPToDevanagari();
        if (marker == false)
        {
            marker = true;
            restore = true;
        }

        String xxx = (new SivaSutra()).getPratyahaara("al", marker);
        returnMe = dvn.transform(xxx);

        if (restore == true)
        {
            marker = false;
        }

        return returnMe;
    }

    // *******************END OF FUNCTION**********************//

    // ******************READING THE Siva FILE*************************//
    public String readFile(String f)
    {
        String filename = f;

        String return_me = "";

        File siva = new File(filename);

        try
        {
            FileInputStream fis = new FileInputStream(siva);

            // StyledEditorKit kit = new StyledEditorKit();
            // Document doc = new Document();
            // kit.read(fis, doc,0) ;

            String temp = "";
            int i1 = 0;
            while (i1 != -1)
            {
                i1 = fis.read();
                temp += (char) i1;
                // Character c = new Character(temp);
                // return_me += (String) c;
                // //System.out.print(temp);
            }
            return_me = temp;

            fis.close();
        }

        catch (Exception e)
        {
            Log.logInfo("File not found.");
            e.printStackTrace();
        }
        return return_me;
    }

    public void actionPerformed(ActionEvent e)
    {
        if ((e.getActionCommand()).equals("back"))
        {
            this.dispose();
        }
    } // end of actionPerformed

}
