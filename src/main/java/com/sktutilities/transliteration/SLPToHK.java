package com.sktutilities.transliteration;

import com.sktutilities.util.Log;

public class SLPToHK
{

    public static String transform(String s1)
        {
        
        String transformed = s1;
        Log.logInfo("SLPToItrans: " + s1  );

        transformed = transformed.replaceAll("N","0");  
        transformed = transformed.replaceAll("Y","1");          
        transformed = transformed.replaceAll("R","N");
        transformed = transformed.replaceAll("f", "R");            
        transformed = transformed.replaceAll("F","RR");// same reason as above
        transformed = transformed.replaceAll("x","IR");
        transformed = transformed.replaceAll("X","IRR");

        
        
        /***NOTE 1 ***/
        /***Note 1 ***/
        
        transformed = transformed.replaceAll("E","ai");
        transformed = transformed.replaceAll("O","au");
        
        transformed = transformed.replaceAll("K","kh");
        transformed = transformed.replaceAll("G","gh");
        
        transformed = transformed.replaceAll("C","ch");
        transformed = transformed.replaceAll("J","jh"); 
        
        transformed = transformed.replaceAll("T","th"); // watch out!!!!
                                                                    // must be above .replaceAll("w","T")
        transformed = transformed.replaceAll("D","dh");// watch out!!!!
                                                                    // must be above .replaceAll("q","D")
        transformed = transformed.replaceAll("w","T");
        transformed = transformed.replaceAll("W","Th");
        transformed = transformed.replaceAll("q","D");
        transformed = transformed.replaceAll("Q","Dh");
                                                                        

        transformed = transformed.replaceAll("P","ph"); 
        transformed = transformed.replaceAll("B","bh");
        transformed = transformed.replaceAll("S","Z");// temporary place holder
        transformed = transformed.replaceAll("z","S"); 
        transformed = transformed.replaceAll("Z","z"); 
        transformed = transformed.replaceAll("'", ".a");    // avagraha
        
        transformed = transformed.replaceAll("0","G");  
        transformed = transformed.replaceAll("1","J"); 
        
        Log.logInfo("SLPToItrans: transformed" + s1 + " = " + transformed );            
        return transformed; // return transformed;
        }

    }

