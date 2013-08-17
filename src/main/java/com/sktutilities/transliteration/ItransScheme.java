package com.sktutilities.transliteration;

import java.util.*;

public class ItransScheme
{
    private String data, scheme;

    private Vector<String> itransVowels, slpVowels;

    private Vector<String> itransConsonants, slpConsonants;

    // *******************BEGINNING OF CONSTRUCTOR********************//

    public ItransScheme(String scheme1)
    {

        data = ""; // / initializing data
        scheme = scheme1;
        if (scheme.equals("SLP"))
        {
            slpVowels = new Vector<String>(22);
            slpConsonants = new Vector<String>(42);
            slpVector();
            // insert elements into Vector

            data = displayVector(slpVowels, slpConsonants);
            // Arrange the Vectors content in a String with proper formatting
            // and user-friendly view
        }

        else
        {
            itransVowels = new Vector<String>(22);
            itransConsonants = new Vector<String>(42);
            itransVector();
            // insert elements into Vector
            data = displayVector(itransVowels, itransConsonants);
            // Arrange the Vectors content in a String with proper formatting
            // and user-friendly view
        }

    }

    // *******************END OF CONSTRUCTOR********************//

    // *******************BEGINNING OF FUNCTION********************//
    public String getData()
    {
        return data;
    }

    // *******************END OF FUNCTION********************//

    // *******************BEGINNING OF FUNCTION********************//
    public String displayVector(Vector<String> v1, Vector<String> v2)
    {
        // Enumeration vowel_enum = itrans_vowels.elements();
        // Enumeration con_enum = itrans_consonants.elements();

        Enumeration<String> vowelEnum = v1.elements();
        Enumeration<String> conEnum = v2.elements();

        String transliterationSchemeDisplay = "";

        if (scheme.equals("SLP"))
        {
            transliterationSchemeDisplay += "SLP Transliteration Scheme is a one-to-one Roman-Devanagari " + "Encoding Scheme devised by Prof. Scharfe and used at his site http://www.sanskritlibrary.org. " + "Its main advantage lies in it's 'One Roman Equivalent for One Devanagari Symbol' approach.";
        }
        else
        {
            transliterationSchemeDisplay += "Itrans Transliteration Scheme 5.30 is possibly the most popular and easy Roman-Devanagari " + "Encoding Scheme ever devised.It is authored  by Avinash Chopde and widely used on the Web( details available on http://www.aczone.com/itrans/. " + "It is extremely easy to use and memorize due to its phonetic character.";
        }

        int check = 1;
        transliterationSchemeDisplay += "\n\nVowels: \n\n";
        // Display Elements
        while (vowelEnum.hasMoreElements())
        {
            if (check % 5 == 0)
            {
                transliterationSchemeDisplay += vowelEnum.nextElement() + "\n\n";
            }
            else
            {
                transliterationSchemeDisplay += vowelEnum.nextElement() + "             ";
            }
            check++;
        }

        int check1 = 1;
        transliterationSchemeDisplay += "\n\nConsonants: \n\n";

        // Display Elements
        while (conEnum.hasMoreElements())
        {
            if (check1 % 5 == 0)
            {
                transliterationSchemeDisplay += conEnum.nextElement() + "\n\n";
            }
            else
            {
                transliterationSchemeDisplay += conEnum.nextElement() + "              ";
            }
            check1++;
        }

        // Log.logInfo(" transliteration_scheme_display == " +
        // transliteration_scheme_display );
        return transliterationSchemeDisplay;
    }

    // *******************END OF FUNCTION********************//

    // *******************BEGINNING OF FUNCTION********************//
    public void itransVector()
    {

        // itrans_consonants = new Vector();

        itransVowels.add("a : \u0905");
        itransVowels.add("aa/A : \u0906");
        itransVowels.add("i : \u0907");
        itransVowels.add("ii/I : \u0908");
        itransVowels.add("u : \u0909");
        itransVowels.add("uu/U : \u090a");
        itransVowels.add("RRi/R^i : \u090b");
        itransVowels.add("RRI/R^I : \u0960");
        itransVowels.add("LLi/L^i : \u090c");
        itransVowels.add("LLI/L^I : \u0961");
        itransVowels.add("e : \u090f");
        itransVowels.add("ai : \u0910");
        itransVowels.add("o : \u0913");
        itransVowels.add("au : \u0914");
        itransVowels.add(".n/M/.m : \u0905\u0902"); // anusvara
        itransVowels.add("H : \u0905\u0903"); // visarga
        itransVowels.add(".N : \u0905\u0901"); // anunAsika - cchandra bindu,
                                                // using V to represent it\
        itransVowels.add(".a : \u093d"); // avagraha using "'"
        itransVowels.add("pluta sign : \u0969"); // 3 equals to pluta

        itransConsonants.add("k : \u0915");
        itransConsonants.add("K : \u0916");
        itransConsonants.add("g : \u0917");
        itransConsonants.add("G : \u0918");
        itransConsonants.add("N : \u0919");
        itransConsonants.add("c : \u091a");
        itransConsonants.add("C : \u091b");
        itransConsonants.add("j : \u091c");
        itransConsonants.add("J : \u091d");
        itransConsonants.add("Y : \u091e");
        itransConsonants.add("w : \u091f"); // Ta as in Tom
        itransConsonants.add("W : \u0920");
        itransConsonants.add("q : \u0921"); // Da as in David
        itransConsonants.add("Q : \u0922");
        itransConsonants.add("R : \u0923");
        itransConsonants.add("t : \u0924"); // ta as in tamasha
        itransConsonants.add("T : \u0925"); // tha as in thanks
        itransConsonants.add("d : \u0926"); // da as in darvaaza
        itransConsonants.add("D : \u0927"); // dha as in dhanusha
        itransConsonants.add("n : \u0928");
        itransConsonants.add("p : \u092a");
        itransConsonants.add("P : \u092b");
        itransConsonants.add("b : \u092c");
        itransConsonants.add("B : \u092d");
        itransConsonants.add("m : \u092e");
        itransConsonants.add("y : \u092f");
        itransConsonants.add("r : \u0930");
        itransConsonants.add("l : \u0932");
        itransConsonants.add("v : \u0935");
        itransConsonants.add("S : \u0936");
        itransConsonants.add("z : \u0937");
        itransConsonants.add("s : \u0938");
        itransConsonants.add("h : \u0939");

    }

    // *******************END OF FUNCTION********************//

    // *******************BEGINNING OF FUNCTION********************//
    public void slpVector()
    {

        // itrans_consonants = new Vector();

        slpVowels.add("a : \u0905");
        slpVowels.add("A : \u0906");
        slpVowels.add("i : \u0907");
        slpVowels.add("I : \u0908");
        slpVowels.add("u : \u0909");
        slpVowels.add("U : \u090a");
        slpVowels.add("f : \u090b");
        slpVowels.add("F : \u0960");
        slpVowels.add("x : \u090c");
        slpVowels.add("X : \u0961");
        slpVowels.add("e : \u090f");
        slpVowels.add("E : \u0910");
        slpVowels.add("o : \u0913");
        slpVowels.add("O : \u0914");
        slpVowels.add("M : \u0905\u0902"); // anusvara
        slpVowels.add("H : \u0905\u0903"); // visarga
        slpVowels.add("~ : \u0905\u0901"); // anunAsika - cchandra bindu, using
                                            // V to represent it\
        slpVowels.add("' : \u093d"); // avagraha using "'"
        slpVowels.add("pluta sign : \u0969"); // 3 equals to pluta

        slpConsonants.add("k : \u0915");
        slpConsonants.add("kh : \u0916");
        slpConsonants.add("g : \u0917");
        slpConsonants.add("gh : \u0918");
        slpConsonants.add("~N : \u0919");
        slpConsonants.add("c : \u091a");
        slpConsonants.add("chh : \u091b");
        slpConsonants.add("j : \u091c");
        slpConsonants.add("jh : \u091d");
        slpConsonants.add("~n : \u091e");
        slpConsonants.add("T : \u091f"); // Ta as in Tom
        slpConsonants.add("Th : \u0920");
        slpConsonants.add("D : \u0921"); // Da as in David
        slpConsonants.add("Dh : \u0922");
        slpConsonants.add("N : \u0923");
        slpConsonants.add("t : \u0924"); // ta as in tamasha
        slpConsonants.add("th : \u0925"); // tha as in thanks
        slpConsonants.add("d : \u0926"); // da as in darvaaza
        slpConsonants.add("dh : \u0927"); // dha as in dhanusha
        slpConsonants.add("n : \u0928");
        slpConsonants.add("p : \u092a");
        slpConsonants.add("ph : \u092b");
        slpConsonants.add("b : \u092c");
        slpConsonants.add("bh : \u092d");
        slpConsonants.add("m : \u092e");
        slpConsonants.add("y : \u092f");
        slpConsonants.add("r : \u0930");
        slpConsonants.add("l : \u0932");
        slpConsonants.add("v/w : \u0935");
        slpConsonants.add("sh : \u0936");
        slpConsonants.add("S : \u0937");
        slpConsonants.add("s : \u0938");
        slpConsonants.add("h : \u0939");

    }
    // *******************END OF FUNCTION********************//

}
