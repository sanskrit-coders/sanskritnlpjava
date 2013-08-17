/*
 * Purpose: Converts SLP to Devanagari
 */

package com.sktutilities.transliteration;

import com.sktutilities.util.*;

import java.util.*;

public class SLPToDevanagari
{
    private Hashtable<String, String> unicode;

    private Hashtable<String, String> matra;

    private final String halant = "\u094d"; 
    public void populateHashTable()
        {
            unicode = new Hashtable<String, String>();
            matra = new Hashtable<String, String>();
            matra.put("A", "\u093E");
            matra.put("i", "\u093F");
            matra.put("I", "\u0940");
            matra.put("u", "\u0941");
            matra.put("U", "\u0942");
            matra.put("f", "\u0943");
            matra.put("F", "\u0944");
            matra.put("x", "\u0962");
            matra.put("X", "\u0963");
            matra.put("e", "\u0947");
            matra.put("E", "\u0948");
            matra.put("o", "\u094b");
            matra.put("O", "\u094c");
    
            unicode.put("a", "\u0905");
            unicode.put("A", "\u0906");
            unicode.put("i", "\u0907");
            unicode.put("I", "\u0908");
            unicode.put("u", "\u0909");
            unicode.put("U", "\u090a");
            unicode.put("f", "\u090b");
            unicode.put("F", "\u0960");
            unicode.put("x", "\u090c");
            unicode.put("X", "\u0961");
            unicode.put("e", "\u090f");
            unicode.put("E", "\u0910");
            unicode.put("o", "\u0913");
            unicode.put("O", "\u0914");
    
            unicode.put("k", "\u0915");
            unicode.put("K", "\u0916");
            unicode.put("g", "\u0917");
            unicode.put("G", "\u0918");
            unicode.put("N", "\u0919");
            unicode.put("c", "\u091a");
            unicode.put("C", "\u091b");
            unicode.put("j", "\u091c");
            unicode.put("J", "\u091d");
            unicode.put("Y", "\u091e");
            unicode.put("w", "\u091f"); // Ta as in Tom
            unicode.put("W", "\u0920");
            unicode.put("q", "\u0921"); // Da as in David
            unicode.put("Q", "\u0922");
            unicode.put("R", "\u0923");
            unicode.put("t", "\u0924"); // ta as in tamasha
            unicode.put("T", "\u0925"); // tha as in thanks
            unicode.put("d", "\u0926"); // da as in darvaaza
            unicode.put("D", "\u0927"); // dha as in dhanusha
            unicode.put("n", "\u0928");
            unicode.put("p", "\u092a");
            unicode.put("P", "\u092b");
            unicode.put("b", "\u092c");
            unicode.put("B", "\u092d");
            unicode.put("m", "\u092e");
            unicode.put("y", "\u092f");
            unicode.put("r", "\u0930");
            unicode.put("l", "\u0932");
    
            unicode.put("L", "\u0933"); // the Marathi and Vedic 'L'
    
            unicode.put("v", "\u0935");
            unicode.put("S", "\u0936");
            unicode.put("z", "\u0937");
            unicode.put("s", "\u0938");
            unicode.put("h", "\u0939");
            unicode.put("M", "\u0902"); // anusvara
            unicode.put("H", "\u0903"); // visarga
            unicode.put("~", "\u0901"); // anunAsika - cchandra bindu, using ~ to
                                        // represent it\
            unicode.put("'", "\u093d"); // avagraha using "'"
            unicode.put("3", "\u0969"); // 3 equals to pluta
            unicode.put("Z", "\u014F");// Z equals to upadhamaniya
            unicode.put("V", "\u0CF1");// V equals to jihvamuliya....but what character have u settled for jihvamuliya
            unicode.put("Ω", "\u0950"); // aum 
            unicode.put("κ", "\u0958"); // Urdu qaif
            unicode.put("Κ", "\u0959"); //Urdu qhe
            unicode.put("γ", "\u095A"); // Urdu gain
            unicode.put("ζ", "\u095B"); //Urdu zal, ze, zoe
            unicode.put("φ", "\u095E"); // Urdu f
            unicode.put("δ", "\u095C"); // Hindi 'dh' as in padh
            unicode.put("Δ", "\u095D"); // hindi dhh
            unicode.put("τ", "\u0926\u093C"); // Urdu dwad
            unicode.put("θ", "\u0924\u093C"); // Urdu toe
            unicode.put("σ", "\u0938\u093C"); // Urdu swad, se

            /*
    transformed = transformed.replaceAll("AUM","Ω"); //omega or Ohm
    transformed = transformed.replaceAll("OM","Ω"); //Omega or Ohm

    transformed = transformed.replaceAll("q","κ");  //Greek kappa
    transformed = transformed.replaceAll("K","Κ");  // Greek Capital Kappa
    transformed = transformed.replaceAll("G","γ"); //gamma
    transformed = transformed.replaceAll("z","ζ"); //Zeta
    transformed = transformed.replaceAll("J","ζ"); //Zeta
    transformed = transformed.replaceAll("f","φ"); //phi
    transformed = transformed.replaceAll(".Dh","Δ");  //Capital Delta
    transformed = transformed.replaceAll(".D","δ"); //delta
    transformed = transformed.replaceAll(".d","τ"); //tau
    transformed = transformed.replaceAll(".t","θ"); //theta
    transformed = transformed.replaceAll(".s","σ"); //sigma
    transformed = transformed.replaceAll(".c","ω"); //omega
    */
            
        }

    public SLPToDevanagari()
    {
        populateHashTable();
    }

    public String transform(String s1)
    {
    
        String transformed = "";
    
        int strLen = s1.length();
        ArrayList<String> shabda = new ArrayList<String>();
        String lastEntry = "";
        for (int i = 0; i < strLen; i++)
        {
            char c = s1.charAt(i);
            String varna = String.valueOf(c);
            
            if (VowelUtil.isConsonant(varna))
            {
                shabda.add(unicode.get(varna));
                shabda.add(halant); //halant
                lastEntry = halant;
            }
    
            else if (VowelUtil.isVowel(varna))
            {
                 if (halant.equals(lastEntry))
                {
                    if (varna.equals("a"))
                    {
                        shabda.set(shabda.size() - 1,"");
                    }
                    else
                    {
                        shabda.set(shabda.size() - 1, matra.get(varna));
                    }
                }
    
                else
                {
                    shabda.add(unicode.get(varna));
                }
                 lastEntry = unicode.get(varna);
            } // end of else if is-Vowel
    
            else if (unicode.containsKey(varna))
            {
                shabda.add(unicode.get(varna));
                lastEntry = unicode.get(varna);
            }
            else
            {
                shabda.add(varna);
                lastEntry = varna;
            }
            
        } // end of for
    
        for (String string: shabda)
        {
            transformed += string;
        }
        
        //Discard the shabda array
        shabda = null;
        return transformed; // return transformed;
    }

}
