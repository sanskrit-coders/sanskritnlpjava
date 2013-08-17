package com.sktutilities.transliteration;

import com.sktutilities.util.Log;
@Deprecated
public class ElatinToUniformItrans
{
    public static String transform(String s1)
    {
    String transformed= "";
    transformed = s1;
    Log.logInfo("ElatinToUniformItrans");

    // Vowels
    transformed = transformed.replaceAll( "â","a"); // seems
    // like
    // a
    // variation
    // of
    // a)
    transformed = transformed.replaceAll( "á","a"); // represents
    // SLP
    // "a"(aham)
    //transformed = transformed.replaceAll( "&#x0101;", "\u0101"); //   
    transformed = transformed.replaceAll( "ā","A" ); // represents
    // SLP
    // "A"
    transformed = transformed.replaceAll( "Ā","A"); // capitalized
    // version
    // of
    // 'A'

    transformed = transformed.replaceAll("ī" ,"I"); // represents
    // SLP
    // "I"(vIra)
    transformed = transformed.replaceAll("í" ,"I"); // represents
    // SLP
    // "I"(vIra)
    transformed = transformed.replaceAll( "Ī","I"); // capitalized
    // "I"(vIra)

    transformed = transformed.replaceAll( "ū", "U"); // represents
    // SLP
    // "U"(krUra)
    transformed = transformed.replaceAll( "ú", "U"); // represents
    // SLP
    // "U"(krUra)
    transformed = transformed.replaceAll( "Ū" ,"U"); // capitalized
    // "U"(krUra)
    transformed = transformed.replaceAll( "û" , "U"); // capitalized
    // "U"(krUra)

    transformed = transformed.replaceAll("ṛ" ,"RRi"); // represents
    // SLP
    // "f"(RRi)
    transformed = transformed.replaceAll( "Ṛ","RRi"); // capitalized
    // "f"(RRi)
    transformed = transformed.replaceAll( "ṝ" , "RRI"); // capitalized
    // "f"(RRi)
    transformed = transformed.replaceAll( "Ḷ", "LLi"); // capitalized
    // "f"(RRi)

    transformed = transformed.replaceAll(  "é", "e"); //
    transformed = transformed.replaceAll(  "ê", "ai"); //

    transformed = transformed.replaceAll(  "ô", "o"); // represents
    // SLP
    // "o"(meghodaya)
    transformed = transformed.replaceAll(  "ó", "o"); // represents
    // SLP
    // "o"(meghodaya)

    transformed = transformed.replaceAll( "ṃ","M"); // represents
    // SLP
    // "M"(aMsha)
    transformed = transformed.replaceAll( "ḥ", "H"); // represents
    // visarga

    // vargiyas
    transformed = transformed.replaceAll( "ḍ" , "D"); // represents
    // SLP
    // "q"(D
    // as
    // in
    // Danda)
    transformed = transformed.replaceAll( "Ḍ" ,"D"); // represents
    // SLP
    // "q"(D
    // as
    // in
    // Danda)

    transformed = transformed.replaceAll( "ṭ", "T"); // represents
    // SLP
    // "w"(T
    // as
    // in
    // TamaaTar)

    // Nasals:
    transformed = transformed.replaceAll( "ñ","~n"); // represents
    // SLP
    // "Y"(jYaana)
    transformed = transformed.replaceAll( "ṅ","Y"); // represents
    // SLP
    // "N"(kalaNka)
    transformed = transformed.replaceAll( "ṇ","N"); // represents
    // SLP
    // "R"(N)

    // sh-sh
    transformed = transformed.replaceAll("ś", "sh"); // represents
    // SLP
    // "S"(Sh
    // as
    // in
    // Sharma)
    transformed = transformed.replaceAll("Ś","sh"); // Capitalized
    // form
    // of
    // SLP
    // "S"(Sh
    // as
    // in
    // Sharma)

    transformed = transformed.replaceAll("ṣ","z"); // represents
    // SLP
    // "z"(kzaNa)
    transformed = transformed.replaceAll("Ṣ","z");
        
    // return transformed;
    return transformed;
    }
}
