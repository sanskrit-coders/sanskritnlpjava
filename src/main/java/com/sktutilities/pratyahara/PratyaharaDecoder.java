package com.sktutilities.pratyahara;


public class PratyaharaDecoder
{
    /*
     */

    public PratyaharaDecoder()
    {
    }

    public boolean adi_varna_in_pratyahara(String short_form, String adi)
    // must be in SLP Encoding
    {
        char c = adi.charAt(0);
        Character ch1 = new Character(c);
        String s1 = ch1.toString();

        return contains(short_form, s1);

    }

    public boolean anta_varna_in_pratyahara(String short_form, String anta)
    // must be in SLP Encoding
    {
        char c = anta.charAt(anta.length() - 1);
        Character ch1 = new Character(c);
        String s1 = ch1.toString();
        return contains(short_form, s1);
    }

    public boolean contains(String short_form, String varna)
    // must be in SLP Encoding
    {
        String expanded_form = decodePratyahara(short_form, true);
        // The second Argument has been set to true.
        // But it could have been set to false.
        // It will not make a Difference
        
        
        // Log.logInfo( "*********the pratyahara for short form " +
        // short_form + " is == \n" + expanded_form + "*********\n and varna ==
        // " + varna + "*********\n" );

        // all pratyaharas generated will contain an 'a' therefore special
        // conditions for 'a'
        if (varna.equals("a") && expanded_form.startsWith("a")) return true;

        if (varna.equals("a") && !expanded_form.startsWith("a"))
            return false;

        else if (expanded_form.contains(varna) && !(varna.equals(",")) && !(varna.equals(" ")))
        {
            // Log.logInfo( "I came in main condition for
            // contains....retrning true");

            return true;
        }

        return false;

    }


    public String decodePratyahara(String str, boolean excludeMarkers) // str must be in SLP
    {
        String strInSLP = str.trim();
        String result = "";
        UditSavarna us = new UditSavarna();
        SUPAffixes sup = new SUPAffixes();
        TingAffixes ting = new TingAffixes();
        SivaSutra ss = new SivaSutra();
        
        // I if String is eligible for udita-savarna operations
        if (us.is_udita(strInSLP))
            result = us.vargas(strInSLP);

        // II if String is eligible for 'sup'-abbreviatory operations
        else if (sup.is_sup(strInSLP))
            result = sup.printSupAffixes(strInSLP);

        // III if String is eligible for 'tinga'-abbreviatory operations
        else if (ting.is_ting(strInSLP))
            result = ting.printTingAffixes(strInSLP);

        // IV else check for Siva Sutra operations
        else
        {
            // This line modified to use the Two Argument Method in siva_sutra
            // Class
            result = ss.getPratyahaara(strInSLP, excludeMarkers);
        }
        
        return result; // Plain English

    }

} // end of get_pratyahaar
