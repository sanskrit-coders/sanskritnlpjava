package com.sktutilities.util;

public class ConsonantUtil
{
    // private String varga[][];

    public static final String padanta = "Padanta-Dependency.\n";

    public ConsonantUtil()
    {
        // Log.logInfo("I came in Consonant.java");
    }

    // *******************BEGINNING OF FUNCTION********************//
   /* public static String VarnaUtil.get_adi(String str)
    {
        if (str.length() < 1) return null;
        return String.valueOf(str.charAt(0));

    }
*/
    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
 /*   public static String VarnaUtil.get_anta(String str)
    {
        if (str.length() < 1) return null;

        char c = str.charAt(str.length() - 1);
        Character ch1 = new Character(c);
        String s1 = ch1.toString();
        return s1;

    }*/

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//


    // *******************END OF FUNCTION**********************//

 
    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_jhaladi(String str)
    {
        String s1 = VarnaUtil.getAdiVarna(str);
        if (is_jhal(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_kharadi(String str)
    {

        // Log.logInfo(" Checking if is_kharadi:::");
        String s1 = VarnaUtil.getAdiVarna(str);
        if (is_khar(s1))
        {
            // Log.logInfo("I am kharadi");
            return true;
        }

        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_haladi(String str)
    {
        String s1 = VarnaUtil.getAdiVarna(str);
        if (is_kavarga(s1) || is_chavarga(s1) || is_Tavarga(s1) || is_tavarga(s1) || is_pavarga(s1) || is_shar(s1) || is_yan(s1) || s1.equals("h") || s1.equals("M")) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_ashadi(String str)
    {
        String s1 = VarnaUtil.getAdiVarna(str);
        if (is_ash(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_kavargadi(String str)
    {
        String s1 = VarnaUtil.getAdiVarna(str);
        if (is_kavarga(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_amadi(String str)
    {
        String s1 = VarnaUtil.getAdiVarna(str);
        if (is_am(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_chavargadi(String str)
    {
        String s1 = VarnaUtil.getAdiVarna(str);
        if (is_chavarga(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_Tavargadi(String str)
    {
        String s1 = VarnaUtil.getAdiVarna(str);
        if (is_Tavarga(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_tavargadi(String str)
    {
        String s1 = VarnaUtil.getAdiVarna(str);
        if (is_tavarga(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_pavargadi(String str)
    {
        String s1 = VarnaUtil.getAdiVarna(str);
        if (is_pavarga(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_vargadi(String str)
    {
        String s1 = VarnaUtil.getAdiVarna(str);
        if (is_varga(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_yayadi(String str)
    {
        // System.out.print("Entered is_yayadi, returning: ");
        String s1 = VarnaUtil.getAdiVarna(str);
        if (is_yay(s1))
        {
            // Log.logInfo("true");
            return true;
        }
        // Log.logInfo("false");
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_yanadi(String str)
    {

        String s1 = VarnaUtil.getAdiVarna(str);
        if (is_yan(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // halanta

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_jhalanta(String str)
    {
        // System.out.print("Entered is_jhalanta, returning: ");
        String s1 = VarnaUtil.getAntyaVarna(str);
        if (is_jhal(s1))
        {
            // Log.logInfo("true");
            return true;
        }
        // Log.logInfo("false");
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_kharanta(String str)
    {
        String s1 = VarnaUtil.getAntyaVarna(str);
        if (is_khar(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_kavarganta(String str)
    {

        // System.out.print("Entered is_kavarganta, returning: ");
        String s1 = VarnaUtil.getAntyaVarna(str);
        // System.out.print("s1 == " + s1);
        if (is_kavarga(s1))
        {
            // Log.logInfo("true");
            return true;
        }
        // Log.logInfo("false");
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_chavarganta(String str)
    {
        String s1 = VarnaUtil.getAntyaVarna(str);
        if (is_chavarga(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************/

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_Tavarganta(String str)
    {
        String s1 = VarnaUtil.getAntyaVarna(str);
        if (is_Tavarga(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************/

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_tavarganta(String str)
    {
        String s1 = VarnaUtil.getAntyaVarna(str);
        if (is_tavarga(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************/

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_pavarganta(String str)
    {
        String s1 = VarnaUtil.getAntyaVarna(str);
        if (is_pavarga(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************/

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_amanta(String str)
    {
        String s1 = VarnaUtil.getAntyaVarna(str);
        if (is_am(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_halanta(String str)
    {
        String s1 = VarnaUtil.getAntyaVarna(str);
        if (is_kavarga(s1) || is_chavarga(s1) || is_Tavarga(s1) || is_tavarga(s1) || is_pavarga(s1) || is_shar(s1) || is_yan(s1) || s1.equals("h") || s1.equals("M")) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_yayanta(String str)
    {
        String s1 = VarnaUtil.getAntyaVarna(str);
        if (is_yay(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_yananta(String str)
    {

        String s1 = VarnaUtil.getAntyaVarna(str);
        if (is_yan(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_ashanta(String str)
    {

        String s1 = VarnaUtil.getAntyaVarna(str);
        if (is_ash(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_varganta(String str)
    {

        String s1 = VarnaUtil.getAntyaVarna(str);
        if (is_varga(s1)) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_jhal(String str)
    {

        if (is_vargiya4(str) || is_vargiya3(str) || is_vargiya2(str) || is_vargiya1(str) || is_shar(str) || str.equals("h")) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_ash(String str)
    {
        if (VowelUtil.isVowel(str) || is_vargiya5(str) || is_vargiya4(str) || is_vargiya3(str) || is_yan(str) || str.equals("h")) return true;
        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_khar(String str)
    {
        if (is_vargiya1(str) || is_vargiya2(str) || is_shar(str)) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_jash(String str)
    {
        return is_vargiya3(str);
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_yay(String str)
    {

        if (is_yan(str) || is_vargiya5(str) || is_vargiya4(str) || is_vargiya3(str) || is_vargiya2(str) || is_vargiya1(str)) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//

    // the is_vargas

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_kavarga(String str)
    {

        if (str.equals("k") || str.equals("K") || str.equals("g") || str.equals("G") || str.equals("N")) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_chavarga(String str)
    {

        if (str.equals("c") || str.equals("C") || str.equals("j") || str.equals("J") || str.equals("Y")) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_Tavarga(String str)
    {

        if (str.equals("w") || str.equals("W") || str.equals("q") || str.equals("Q") || str.equals("R")) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_tavarga(String str)
    {

        if (str.equals("t") || str.equals("T") || str.equals("d") || str.equals("D") || str.equals("n")) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_pavarga(String str)
    {

        if (str.equals("p") || str.equals("P") || str.equals("b") || str.equals("B") || str.equals("m")) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_varga(String str)
    {

        if (is_vargiya5(str) || is_vargiya4(str) || is_vargiya3(str) || is_vargiya2(str) || is_vargiya1(str)) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//
    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_yan(String str)
    {

        if (str.equals("y") || str.equals("r") || str.equals("l") || str.equals("v")) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_shar(String str)
    {

        if (str.equals("S") || str.equals("z") || str.equals("s")) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_jhay(String str)
    {

        if (is_vargiya1(str) || is_vargiya2(str) || is_vargiya3(str) || is_vargiya4(str)) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_am(String str)
    {
        Log.logInfo("in is_am");
        if (VowelUtil.isVowel(str) || str.equals("h") || str.equals("y") || str.equals("v") || str.equals("r") || str.equals("l") || str.equals("Y") || str.equals("m") || str.equals("N") || str.equals("R") || str.equals("n")) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//
    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_vargiya1(String str)
    {

        if (str.equals("k") || str.equals("c") || str.equals("w") || str.equals("t") || str.equals("p")) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_vargiya2(String str)
    {

        if (str.equals("K") || str.equals("C") || str.equals("W") || str.equals("T") || str.equals("P")) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_vargiya3(String str)
    {

        if (str.equals("g") || str.equals("j") || str.equals("q") || str.equals("d") || str.equals("b")) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_vargiya4(String str)
    {

        if (str.equals("G") || str.equals("J") || str.equals("Q") || str.equals("D") || str.equals("B")) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_vargiya5(String str)
    {

        if (str.equals("Y") || str.equals("m") || str.equals("N") || str.equals("R") || str.equals("n")) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//
    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_vargiya5_anta(String str)
    {

        if (is_vargiya5(VarnaUtil.getAntyaVarna(str))) return true;

        return false;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//
    public static boolean is_savarna(String str1, String str2)
    {
        // Log.logInfo(" in is_savarna " );
        if (is_kavarganta(str1) && is_kavargadi(str2)) return true;
        if (is_chavarganta(str1) && is_chavargadi(str2)) return true;
        if (is_Tavarganta(str1) && is_Tavargadi(str2)) return true;
        if (is_tavarganta(str1) && is_tavargadi(str2)) return true;
        if (is_pavarganta(str1) && is_pavargadi(str2)) return true;

        // what abt the others y,r,;,v etc

        // Log.logInfo(" is not savarna " );

        return false;
    }

    // *******************END OF FUNCTION**********************//

} // end of class
