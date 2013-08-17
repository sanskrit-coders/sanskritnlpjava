package com.sktutilities.transliteration;

import java.util.Hashtable;

public class SLPToIAST
{
    private String                    transformed;

    private Hashtable<String, String> unicode;

    public SLPToIAST()
    {
        // Log.logInfo("**** EnteredSLPToeLatin");
        transformed = "";
        unicode = new Hashtable<String, String>();
        myHashTable();
    }

    public void myHashTable()
    {

//        String dot_below = "\u0323";
//        String bar_above = "\u0304";
//        String dot_above = "\u0307";
//        String slash_above = "\u0301";

        //unicode.put("Z", "\u014F");// Z equals to upadhamaniya
        //unicode.put("V", "\u0CF1");// V equals to jihvamuliya....but what character have u settled for jihvamuliya
       
        unicode.put("Ω", "\u0950"); // aum 
        unicode.put("κ", "q"); // Urdu qaif
        unicode.put("Κ", "qh"); //Urdu qhe
        unicode.put("γ", "g" ); // Urdu gain
        unicode.put("ζ", "z"); //Urdu zal, ze, zoe
        unicode.put("φ", "f"); // Urdu f
        unicode.put("δ",  "ṛ" ); // Hindi 'dh' as in padh
        unicode.put("Δ",  "ṝ" + "h"); // hindi dhh
        unicode.put("τ", "d"); // Urdu dwad
        unicode.put("θ", "t"); // Urdu toe
        unicode.put("σ", "s"); // Urdu swad, se
        
        
        unicode.put("A","ā" /*"a" + bar_above*/); // a + bar above
        // unicode.put("i", "\u0907");
        unicode.put("I", "ī");// i + bar above
        // unicode.put("u", "\u0909");
        unicode.put("U", "ū");// u + bar above
        unicode.put("f", "ṛ"); // r + dot below
        unicode.put("F", "ṝ"); // r + dot below and bar
                                                        // above
        unicode.put("x", "ḷ"); // l + dot below
        unicode.put("X", "ḹ"); // l + dot below and bar
                                                        // above
        unicode.put("E", "ai");
        unicode.put("O", "au");

        unicode.put("K", "kh");
        unicode.put("G", "gh");
        unicode.put("N", "ṅ"); // n + dot above
        unicode.put("C", "ch");
        unicode.put("J", "jh");
        unicode.put("Y", "ñ"); // n + bar above
        unicode.put("w", "ṭ"); // t + dot below // Ta as in Tom
        unicode.put("W", "ṭh"); // t + dot below
        unicode.put("q", "ḍ"); // t + dot below // Da as in David
        unicode.put("Q", "ḍh"); // t + dot below
        unicode.put("R", "ṇ"); // n + dot below
        // unicode.put("t", "\u0924"); // ta as in tamasha
        unicode.put("T", "th"); // tha as in thanks
        // unicode.put("d", "\u0926"); // da as in darvaaza
        unicode.put("D", "dh"); // dha as in dhanusha
        // unicode.put("n", "\u0928");
        // unicode.put("p", "\u092a");
        unicode.put("P", "ph");
        // unicode.put("b", "\u092c");
        unicode.put("B", "bh");
        // unicode.put("m", "\u092e");
        // unicode.put("y", "\u092f");
        // unicode.put("r", "\u0930");
        // unicode.put("l", "\u0932");
        // unicode.put("v", "\u0935");
        unicode.put("S", "ś"); // slash above
        unicode.put("z", "ṣ");
        // unicode.put("s", "\u0938");
        // unicode.put("h", "\u0939");
        unicode.put("M", "ṃ"); // anusvara
        unicode.put("H","ḥ"); // visarga
        unicode.put("~", "\u0310"); // anunAsika - cchandra bindu, using ~ to
                                    // represent it\
        // unicode.put("'", "\u093d"); // avagraha using "'" : same in extended
        // latin
        // unicode.put("3", "\u0969"); // 3 equals to pluta
        // unicode.put("8", "\u014F");//8 equals to upadhamaniya

    }

    // *******************END OF FUNCTION********************//

    // *******************BEGINNING OF FUNCTION********************//
    public String transform(String vakya)
    {

        String transformed = "";
        String shabdArray[] = vakya.split(" "); // for example 'saH punaH
                                                    // chalati' ->
        // array_of_shabdas[0] = saH, [1] = punaH etc

        // StringTokenizer st = new StringTokenizer(s1, " \t\n\r\f",true);

        for (int i = 0; i < shabdArray.length; i++)
        {
            String word = shabdArray[i]; // example "saH"

            for (int j = 0; j < word.length(); j++)
            {
                String varna = String.valueOf(word.charAt(j));

                if (unicode.containsKey(varna))
                    transformed += unicode.get(varna);
                else
                    transformed += varna;
            }
            transformed += " ";

        }
        shabdArray = null;
        // Log.logInfo(" altered from " + s1 + " to == " + transformed );
        return transformed; // return transformed;
    }

}
