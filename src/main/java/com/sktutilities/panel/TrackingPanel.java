package com.sktutilities.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.sktutilities.sandhi.SandhiJFrame;
import com.sktutilities.util.Log;

public class TrackingPanel extends JPanel implements ActionListener
{
    JButton previousItem, nextItem;

    SandhiJFrame frame;
    
  
    public TrackingPanel(SandhiJFrame frame)
    {
        super();
        this.frame = frame;

        previousItem = new JButton("<<<");
        previousItem.setActionCommand("previous");
        previousItem.setToolTipText("Go to Previous Entry.");
        previousItem.setEnabled(false);

        nextItem = new JButton(">>>");
        nextItem.setActionCommand("next");
        nextItem.setToolTipText("Go to Next Entry.");
        nextItem.setEnabled(false);

        // Adding ActionListener
        previousItem.addActionListener(this);
        nextItem.addActionListener(this);
        
        this.add(previousItem);
        this.add(nextItem);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if ((e.getActionCommand()).equals("previous"))
        {
            // if u r trying to scroll on the firsts added element
            // and for some reason it is not disabled, then time to disable it
            if (frame.getVectorTracker() <= 0) previousItem.setEnabled(false);

            Log.logInfo("previous -> :  vector_tracker " + frame.getVectorTracker());
            nextItem.setEnabled(true);

            frame.setText(frame.getBeanVector().elementAt(frame.preDeccrementVectorTracker()));
            if (frame.getVectorTracker() <= 0) previousItem.setEnabled(false);

            Log.logInfo("previous -> :  vector_tracker" + frame.getVectorTracker());
        }

        else if ((e.getActionCommand()).equals("next"))
        {
            Log.logInfo("next -> :  vector_tracker" + frame.getVectorTracker());
            previousItem.setEnabled(true);
            frame.setText(frame.getBeanVector().elementAt(frame.preIncrementVectorTracker()));
            if (frame.getVectorTracker() >= frame.getBeanVector().size() - 1) nextItem.setEnabled(false);

            Log.logInfo("next -> :  vector_tracker" + frame.getVectorTracker());
        }

    }

    public JButton getPreviousItem()
    {
        return previousItem;
    }

    public JButton getNextItem()
    {
        return nextItem;
    }
}
