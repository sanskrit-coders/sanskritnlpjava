package com.sktutilities.sandhi;

import com.sktutilities.util.*;

public class SandhiBean
{
    String  romanInput1;

    String  romanInput2;

    String  romanInput3;

    String  dvnInput1;

    String  dvnInput2;

    String  dvnInput3;

    String  notes;

    String  encoding;

    boolean padanta;

    boolean pragrhya;

    public SandhiBean()
    {
    }

    public SandhiBean(String prathama, String dvitiya, boolean padanta, boolean pragrhya, String encoding)
    {
        this.padanta = padanta;
        this.pragrhya = pragrhya;
        this.encoding = encoding;
        process(prathama, dvitiya);
    }

    public void process(String prathama, String dvitiya)
    {

        romanInput1 = prathama;
        romanInput2 = dvitiya;

        String tf1InSLP = "";
        String tf2InSLP = "";

        dvnInput1 = EncodingUtil.convertToDVN(prathama, encoding);
        dvnInput2 = EncodingUtil.convertToDVN(dvitiya, encoding);

        tf1InSLP = EncodingUtil.convertToSLP(prathama, encoding);
        tf2InSLP = EncodingUtil.convertToSLP(dvitiya, encoding);

        // convert anta + adi into their Sandhied form(s)
        SandhiMaker sandhiMaker = new SandhiMaker(tf1InSLP, tf2InSLP, padanta, pragrhya);

        String sandhiMergedForm = sandhiMaker.getSandhiCombinedForm(); // Merge the Two Words
        Log.logInfo("sandhiMerged Form " + sandhiMergedForm);

        romanInput3 = EncodingUtil.convertSLPToEncoding(sandhiMergedForm, encoding);
        dvnInput3 = EncodingUtil.convertSLPToDevanagari(sandhiMergedForm);
        notes = sandhiMaker.getSandhiNotes();
    }

    public void setRomanInput1(String romanInput1)
    {
        this.romanInput1 = romanInput1;
    }

    public String getRomanInput1()
    {
        return romanInput1;
    }

    public void setRomanInput2(String romanInput2)
    {
        this.romanInput2 = romanInput2;
    }

    public String getRomanInput2()
    {
        return romanInput2;
    }

    public void setRomanInput3(String romanInput3)
    {
        this.romanInput3 = romanInput3;
    }

    public String getRomanInput3()
    {
        return romanInput3;
    }

    public void setDvnInput1(String dvnInput1)
    {
        this.dvnInput1 = dvnInput1;
    }

    public String getDvnInput1()
    {
        return dvnInput1;
    }

    public void setDvnInput2(String dvnInput2)
    {
        this.dvnInput2 = dvnInput2;
    }

    public String getDvnInput2()
    {
        return dvnInput2;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setDvnInput3(String dvnInput3)
    {
        this.dvnInput3 = dvnInput3;
    }

    public String getDvnInput3()
    {
        return dvnInput3;
    }

    public boolean isPadanta()
    {
        return padanta;
    }

    public void setPadanta(boolean padanta)
    {
        this.padanta = padanta;
    }

    public boolean isPragrhya()
    {
        return pragrhya;
    }

    public void setPragrhya(boolean pragrhya)
    {
        this.pragrhya = pragrhya;
    }

    public String getEncoding()
    {
        return encoding;
    }

    public void setEncoding(String encoding)
    {
        this.encoding = encoding;
    }

}
