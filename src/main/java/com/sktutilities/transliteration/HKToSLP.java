package com.sktutilities.transliteration;

public class HKToSLP
{

    public static String transform(String s1)
    {
        String transformed = s1;
        transformed = transformed.replaceAll("ai", "E");
        transformed = transformed.replaceAll("au", "O");

        transformed = transformed.replaceAll("IRR", "X");
        transformed = transformed.replaceAll("IR", "x");
        transformed = transformed.replaceAll("RR", "F");
        transformed = transformed.replaceAll("R", "f");
        

        transformed = transformed.replaceAll("N", "R");
        transformed = transformed.replaceAll("G", "N");
        transformed = transformed.replaceAll("J", "Y"); 
        
        transformed = transformed.replaceAll("kh", "K");
        transformed = transformed.replaceAll("gh", "G");
        
        transformed = transformed.replaceAll("ch", "C");
        transformed = transformed.replaceAll("jh", "J");
        

        transformed = transformed.replaceAll("Th", "W");  
        transformed = transformed.replaceAll("T", "w");    
        transformed = transformed.replaceAll("Dh", "Q");  
        transformed = transformed.replaceAll("D", "q");
        

        transformed = transformed.replaceAll("th", "T");
        transformed = transformed.replaceAll("dh", "D");

        transformed = transformed.replaceAll("ph", "P");
        transformed = transformed.replaceAll("bh", "B");
        

        transformed = transformed.replaceAll("z", "@@"); //'@@' is a temporary holder.
        transformed = transformed.replaceAll("S", "z");
        transformed = transformed.replaceAll("@@", "S");
        
        transformed = transformed.replaceAll("\\.a", "'"); // SLP for
                                                            // avagraha,,,,,using
                                                            // from own system
        // watch out!!!! use "\\.a" not ".a"



        /** *NOTE 1 ** */
        transformed = transformed.replaceAll("\\.N", "~"); // chandra-bindu...watch
                                                            // out do not just
                                                            // use .N use \\.N

        // return transformed;
        return transformed;
    }
}
