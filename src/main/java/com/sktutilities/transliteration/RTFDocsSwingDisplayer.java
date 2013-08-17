package com.sktutilities.transliteration;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.rtf.RTFEditorKit;

import com.sktutilities.util.Log;

public class RTFDocsSwingDisplayer extends JFrame implements ActionListener
{
    JButton     b1, b2, b3;

    JPanel      p1;

    JTextArea   tb1;

    JEditorPane editorPane;

    public RTFDocsSwingDisplayer(String heading, String filename, String jarName)
    {
        super(heading);
        setSize(650, 550);

        // Initialize String which will go into the JTextPane
        String display_me = readFile(filename, jarName);
        // Buttons
        b1 = new JButton("Back");
        b1.setActionCommand("back");
        b1.addActionListener(this);

        // Initializing JTextPane()
        JTextPane textPane = new JTextPane();
        RTFEditorKit rtfkit = new RTFEditorKit();
        // HTMLEditorKit htmlkit = new HTMLEditorKit();
        textPane.setEditorKit(rtfkit); // set Kit which will read RTF Doc
        // textPane.setEditorKit(htmlkit);
        textPane.setEditable(false); // make uneditable
        textPane.setText(display_me); // set the Text
        textPane.setCaretPosition(0); // set Cret position to 0

        // Panels and addition to container
        p1 = new JPanel();
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        p1.setLayout(new BoxLayout(this.p1, BoxLayout.Y_AXIS));
        p1.add(new JScrollPane(textPane));
        p1.add(b1);
        contentPane.add(p1);
        setVisible(true);

    }

    // *******************END OF FUNCTION**********************//

    // ******************READING THE Siva FILE*************************//
    public String readFile(String fileName, String jarName)
    {
        File file = null;
        BufferedReader reader = null;
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();
        try
        {
            file = new File(fileName);

            // When we run this program from Eclipse, it doesnt need the Code
            // described below.
            // But when it is being run as a JAR File, then we cannot specify a
            // PATH as the files are located in the JAR
            // Hence this !file.exists allows us to
            if (!file.exists())
            {
                Log.logInfo("No file: Will try retirieving using JarFile Logic for " + fileName);
                JarFile jarFile = new JarFile(jarName);
                Log.logInfo("file for reading: " + jarFile.getName());
                JarEntry jarEntry = jarFile.getJarEntry(fileName);
                inputStream = jarFile.getInputStream(jarEntry);
                byte[] b = new byte[8092];
                int n = 0;
                while ((n = inputStream.read(b)) > 0)
                {
                    for (int i = 0; i < b.length; i++)
                    {
                        builder.append((char) b[i]);
                    }
                }
            }
            else
            {
                Log.logInfo("file for reading: " + file.getAbsolutePath());
                reader = new BufferedReader(new FileReader(file));

                String inputLine = "";
                while ((inputLine = reader.readLine()) != null)
                {
                    builder.append(inputLine);
                }
            }
        }

        catch (Exception e)
        {
            Log.logInfo("File not found.");
            e.printStackTrace();
        }

        finally
        {
            try
            {
                if (reader != null) reader.close();
                if (inputStream != null) inputStream.close();

            }

            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }

    // ******************READING THE Siva FILE*************************//

    public void actionPerformed(ActionEvent e)
    {
        if ((e.getActionCommand()).equals("back")) this.dispose();
    }

}
