package com.sktutilities.util;

public class VarnaUtil
{

    public VarnaUtil()
    {
    }

    // *******************BEGINNING OF FUNCTION********************//
    public static String stripAntyaVarna(String s1)
    {
        int str_len = s1.length();
        String stripped1 = s1.substring(0, str_len - 1); // removes the
                                                            // terminal phoneme

        if (s1.endsWith("3") && str_len >= 2)
        {
            stripped1 = stripAntyaVarna(stripped1);
        }

        return stripped1;
    }

    public static String stripAntyaVarna(String s1, int i)
    {
        int str_len = s1.length();
        String stripped1 = s1.substring(0, str_len - i); // Remove everything after the i-th position
        

        if (s1.endsWith("3") && str_len >= 2)
        {
            stripped1 = stripAntyaVarna(stripped1);
        }
        return stripped1;
    }

    public static String stripAdiVarna(String s1)
    {
        int str_len = s1.length();
        String stripped1 = s1.substring(1, str_len); // remove the initial
                                                        // phoneme

        if (stripped1.startsWith("3") && str_len >= 2) // pls observe checking
                                                        // for stripped1 not s2.
        // cos s2 == A3laya but only stripped2 == 3laya
        {
            stripped1 = stripAdiVarna(stripped1);
        }

        return stripped1;
    }

    // *******************END OF FUNCTION**********************//

    public static String getAdiVarna(String str)
    {
        if (str.length() == 0) return null;
        String adiVarna = String.valueOf(str.charAt(0));

        if (str.length() > 1 && str.charAt(1) == '3') // for pluta
        {
            adiVarna += String.valueOf(str.charAt(1));
        }

        return adiVarna;

    }

    // *******************BEGINNING OF FUNCTION********************//
    public static String getAntyaVarna(String str)
    {
        if (str.length() == 0) return null;
        String antyaVarna = String.valueOf(str.charAt(str.length() - 1));

        if (str.length() > 1 && antyaVarna.equals("3")) // for pluta
        {
            antyaVarna = str.charAt(str.length() - 2) + antyaVarna;
        }

        return antyaVarna;

    }
    // *******************END OF FUNCTION**********************//

}
