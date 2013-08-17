package com.sktutilities.transliteration;

import java.util.ArrayList;
import java.util.Hashtable;

import com.sktutilities.util.VowelUtil;

public class SLPToBangla
{
    private Hashtable<String, String> unicode;

    private Hashtable<String, String> matra;

    private final String halant = "\u09Cd"; 
    
    private IndicUnicodeTransformer transformer;
    public SLPToBangla()
    {
        populateHashTable();
    }
    
    public String transform(String slpString)
    {
        return transformer.transform(slpString);
    }
    public String transform1(String slpString)
    {
    
        String transformed = "";
    
        int strLen = slpString.length();
        ArrayList<String> shabda = new ArrayList<String>();
        String lastEntry = "";
        for (int i = 0; i < strLen; i++)
        {
            char c = slpString.charAt(i);
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

    public void populateHashTable()
        {
            unicode = new Hashtable<String, String>();
            matra = new Hashtable<String, String>();
            matra.put("A", "\u09bE");
            matra.put("i", "\u09bF");
            matra.put("I", "\u09C0");
            matra.put("u", "\u09C1");
            matra.put("U", "\u09C2");
            matra.put("f", "\u09C3");
            matra.put("F", "\u09C4");
           // matra.put("x", "\u0962");
            //matra.put("X", "\u0963");
            matra.put("e", "\u09C7");
            matra.put("E", "\u09C8");
            matra.put("o", "\u09Cb");
            matra.put("O", "\u09Cc");
    
            unicode.put("a", "\u0985");
            unicode.put("A", "\u0986");
            unicode.put("i", "\u0987");
            unicode.put("I", "\u0988");
            unicode.put("u", "\u0989");
            unicode.put("U", "\u098a");
            unicode.put("f", "\u098b");
            unicode.put("F", "\u098C");
            //unicode.put("x", "\u090c");
            //unicode.put("X", "\u0961");
            unicode.put("e", "\u098f");
            unicode.put("E", "\u0990");
            unicode.put("o", "\u0993");
            unicode.put("O", "\u0994");
    
            unicode.put("k", "\u0995");
            unicode.put("K", "\u0996");
            unicode.put("g", "\u0997");
            unicode.put("G", "\u0998");
            unicode.put("N", "\u0999");
            unicode.put("c", "\u099a");
            unicode.put("C", "\u099b");
            unicode.put("j", "\u099c");
            unicode.put("J", "\u099d");
            unicode.put("Y", "\u099e");
            unicode.put("w", "\u099f"); // Ta as in Tom
            unicode.put("W", "\u09A0");
            unicode.put("q", "\u09A1"); // Da as in David
            unicode.put("Q", "\u09A2");
            unicode.put("R", "\u09A3");
            unicode.put("t", "\u09A4"); // ta as in tamasha
            unicode.put("T", "\u09A5"); // tha as in thanks
            unicode.put("d", "\u09A6"); // da as in darvaaza
            unicode.put("D", "\u09A7"); // dha as in dhanusha
            unicode.put("n", "\u09A8");
            unicode.put("p", "\u09AA");
            unicode.put("P", "\u09Ab");
            unicode.put("b", "\u09Ac");
            unicode.put("B", "\u09Ad");
            unicode.put("m", "\u09Ae");
            unicode.put("y", "\u09af");
            unicode.put("r", "\u09b0");
            unicode.put("l", "\u09b2");
    
            //unicode.put("L", "\u0933"); // the Marathi and Vedic 'L'
    
            //unicode.put("v", "\u0935");
            unicode.put("S", "\u09b6");
            unicode.put("z", "\u09b7");
            unicode.put("s", "\u09b8");
            unicode.put("h", "\u09b9");
            
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
            transformer = new IndicUnicodeTransformer(unicode, matra,halant);
        }

}
