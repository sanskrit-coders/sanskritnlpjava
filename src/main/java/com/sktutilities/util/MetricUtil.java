package com.sktutilities.util;

public class MetricUtil
{
    public static boolean isGuru(char c)
    {
        return isGuru(String.valueOf(c));
    }

    public static boolean isGuru(String str)
    {
        return !isLaghu(str);
    }

    public static boolean isLaghu(char c)
    {
        return isLaghu(String.valueOf(c));
    }

    public static boolean isLaghu(String str)
    {
        if (VowelUtil.isVowel(str))
        {
            if( str.equals("a") || str.equals("i") || str.equals("u")
                || str.equals("f")|| str.equals("x") )
                return true;
        }
        return false;
    }
}
