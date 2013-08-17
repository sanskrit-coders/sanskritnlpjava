package com.sktutilities.transliteration;

import com.sktutilities.util.Log;

public class IASTToSLP
{

    public IASTToSLP()
    {
    }

public static String transform(String transformed)
    {
    transformed = transformed.toLowerCase();
    Log.logInfo("IASTToSLP: " + transformed  );
    //ā
    // Vowels
    transformed = transformed.replaceAll( "ā","A" ); 
    transformed = transformed.replaceAll("ī" ,"I"); 
    transformed = transformed.replaceAll( "ū", "U");
    transformed = transformed.replaceAll("ṛ" ,"f"); 
    transformed = transformed.replaceAll( "ṝ" , "F"); 
    transformed = transformed.replaceAll( "ḷ", "x"); 
    transformed = transformed.replaceAll( "ḹ", "X"); 
   
    transformed = transformed.replaceAll("ai","E");
    transformed = transformed.replaceAll("au","O");
    
    transformed = transformed.replaceAll( "ḥ", "H"); 
    transformed = transformed.replaceAll( "ṃ","M");
    
    transformed = transformed.replaceAll("kh","K");
    transformed = transformed.replaceAll("gh","G");

    transformed = transformed.replaceAll("ch","C");
    transformed = transformed.replaceAll("jh","J");


    transformed = transformed.replaceAll( "ṭh", "W");
    transformed = transformed.replaceAll( "ṭ", "w");  
    
    transformed = transformed.replaceAll( "ḍh" , "Q");
    // vargiyas
    transformed = transformed.replaceAll( "ḍ" , "q"); 

    transformed = transformed.replaceAll("th","T"); 
    transformed = transformed.replaceAll("dh","D"); 

    transformed = transformed.replaceAll( "ph", "P"); 
    transformed = transformed.replaceAll( "bh", "B"); 

    // Nasals:
    transformed = transformed.replaceAll( "ñ","Y"); // represents
    // SLP
    // "Y"(jYaana)
    transformed = transformed.replaceAll( "ṅ","N"); // represents
    // SLP
    // "N"(kalaNka)
    transformed = transformed.replaceAll( "ṇ","R"); // represents
    // SLP
    // "R"(N)
    transformed = transformed.replaceAll("ś", "S"); // represents
    // SLP
    // "S"(Sh
    // as
    // in
    // Sharma)

    transformed = transformed.replaceAll("ṣ","z"); // represents
    // SLP
    // "z"(kzaNa)
    return transformed;
    }
}
