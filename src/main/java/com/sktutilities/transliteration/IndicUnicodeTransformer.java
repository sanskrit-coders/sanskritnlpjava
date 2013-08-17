package com.sktutilities.transliteration;

import java.util.ArrayList;
import java.util.Hashtable;

import com.sktutilities.util.VowelUtil;

public class IndicUnicodeTransformer
{

    private Hashtable<String, String> unicode;

    private Hashtable<String, String> matra;

    private String halant = "";
    
    public IndicUnicodeTransformer()
    {
        
    }
    public IndicUnicodeTransformer(Hashtable<String, String> unicode, Hashtable<String, String> matra, String halant)
    {
        this.unicode = unicode;
        this.matra = matra;
        this.halant = halant;
       
    }
    
    public String transform(String slpString)
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
}
