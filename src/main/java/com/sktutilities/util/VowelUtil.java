package com.sktutilities.util;

public class VowelUtil
{


    public VowelUtil()
    {
    }

    // the ajadis

    public static boolean isEjadi(String str)
    {
        if (str.startsWith("e") || str.startsWith("E") || str.startsWith("o") || str.startsWith("O") || str.startsWith("e3") || str.startsWith("E3") || str.startsWith("o3") || str.startsWith("O3"))
        {
            return true;
        }

        return false;
    }

    public static boolean isRRigadi(String str)
    {
        // //Log.logInfo("came in _RRigadi");
        if (str.startsWith("f") || str.startsWith("F") || str.startsWith("x") || str.startsWith("X")) return true;

        return false;
    }

    public static boolean isInadi(String str)
    {
        // //Log.logInfo("came in _inadi");
        if (str.startsWith("i") || str.startsWith("I") || str.startsWith("u") || str.startsWith("U")) return true;

        return false;
    }

    public static boolean isIgadi(String str)
    {
        // //Log.logInfo("came in is_igadi");
        if (isRRigadi(str) || isInadi(str)) return true;

        return false;
    }

    public static boolean isAkaradi(String str)
    {
        // //Log.logInfo("came in is_akaradi");
        if (str.startsWith("a") || str.startsWith("A") || str.startsWith("a3") || str.startsWith("A3")) return true;

        return false;
    }

    public static boolean isIkaradi(String str)
    {
        // //Log.logInfo("came in is_ikaradi");
        if (str.startsWith("i") || str.startsWith("I") || str.startsWith("i3") || str.startsWith("I3")) return true;

        return false;
    }

    public static boolean isUkaradi(String str)
    {
        // //Log.logInfo("came in is_ukaradi");
        if (str.startsWith("u") || str.startsWith("U") || str.startsWith("u3") || str.startsWith("U3")) return true;

        return false;
    }
    public static boolean isRRikaradi(String str)
    {
        // //Log.logInfo("came in is_RRikaradi");
        if (str.startsWith("f") || str.startsWith("F") || str.startsWith("f3") || str.startsWith("F3")) return true;

        return false;
    }

    public static boolean isLLikaradi(String str)
    {
        // //Log.logInfo("came in is_LLikaradi");
        if (str.startsWith("x") || str.startsWith("X")) return true;

        return false;
    }

    public static boolean isAgadi(String str)
    {
        // //Log.logInfo("came in is_agadi");
        if (isIgadi(str) || isAkaradi(str)) return true;

        return false;
    }

    public static boolean isEjanta(String str)
    {
        // //Log.logInfo("came in is_ejanta");
        if (str.endsWith("e") || str.endsWith("E") || str.endsWith("o") || str.endsWith("O") || str.endsWith("e3") || str.endsWith("E3") || str.endsWith("o3") || str.endsWith("O3"))
        {
            // //Log.logInfo("is ejanta");
            return true;
        }

        return false;
    }

    public static boolean isRRiganta(String str)
    {
        // //Log.logInfo("came in is_RRiganta");
        if (isRRikaranta(str) || isLLikaranta(str)) return true;

        return false;
    }
    public static boolean isInanta(String str)
    {
        // //Log.logInfo("came in is_inanta");
        if (isIkaranta(str) || isUkaranta(str)) return true;

        return false;
    }

    public static boolean is_iganta(String str)
    {
        // Log.logInfo("came in is_iganta");
        if (isRRiganta(str) || isInanta(str)) return true;

        return false;
    }

    public static boolean isAkaranta(String str)
    {
        // Log.logInfo("came in is_akaranta:");
        if (str.endsWith("a") || str.endsWith("A") || str.endsWith("a3") || str.endsWith("A3"))
        {
            // Log.logInfo("I am akaranta, returning truw");
            return true;
        }

        return false;
    }


    public static boolean isAganta(String str)
    {
        // Log.logInfo("came in is_aganta");
        if (isAkaranta(str) || is_iganta(str))
        {
            // Log.logInfo("is an aganta");
            return true;
        }

        return false;
    }

    public static boolean isIkaranta(String str)
    {
        // Log.logInfo("came in is_ikaranta");

        if (str.endsWith("i") || str.endsWith("I") || str.endsWith("i3") || str.endsWith("I3")) return true;

        return false;
    }

    public static boolean isUkaranta(String str)
    {
        // Log.logInfo("came in is_ukaranta");

        if (str.endsWith("u") || str.endsWith("U") || str.endsWith("u3") || str.endsWith("U3")) return true;

        return false;
    }

    public static boolean isRRikaranta(String str)
    {
        // Log.logInfo("came in is_RRikaranta");
        if (str.endsWith("f") || str.endsWith("F") || str.endsWith("f3") || str.endsWith("F3")) return true;

        return false;
    }

    public static boolean isLLikaranta(String str)
    {
        // Log.logInfo("came in is_LLikaranta");
        if (str.endsWith("x") || str.endsWith("X")) return true;

        return false;
    }

    public static boolean isPlutanta(String str)
    {
        // Log.logInfo("came in is_plutanta:");
        int str_len = str.length();
        if (str_len == 1) return false; // i.e user is inputting just 3
        if (str.endsWith("3") && isVowel(str.charAt(str_len - 2)))
        {
            // Log.logInfo("is_plutanta: True");
            return true;
        }

        // Log.logInfo("is_plutanta: False");
        return false;
    }

    public static boolean isSavarna(String str1, String str2)
    {
        // Log.logInfo(" in is_savarna " );
        if (isAkaranta(str1) && isAkaradi(str2)) return true;
        if (isIkaranta(str1) && isIkaradi(str2)) return true;
        if (isUkaranta(str1) && isUkaradi(str2)) return true;
        if (isRRikaranta(str1) && isRRikaradi(str2)) return true;
        if (isRRikaranta(str1) && isLLikaradi(str2)) return true;
        if (isLLikaranta(str1) && isRRikaradi(str2)) return true;
        if (isLLikaranta(str1) && isLLikaradi(str2)) return true;
        // Log.logInfo(" is not savarna " );

        return false;
    }

    public static boolean isAjadi(String str)
    {
        // Log.logInfo("came in is_ajadi: Checking whether begins with Vowel");
        if (isEjadi(str) || isAkaradi(str) || isIgadi(str))
        {
            // Log.logInfo("Begins with a Vowel");
            return true;
        }

        return false;

    }


    public static boolean isAjanta(String str)
    {
        // Log.logInfo("came in is_ajanta: Checking whether ends in Vowel");
        if (isEjanta(str) || isAkaranta(str) || is_iganta(str))
        {
            // Log.logInfo("Ends with a Vowel");
            return true;
        }

        return false;

    }

    public static boolean isVowel(char c)
    {
        // Log.logInfo("came in is_Vowel: Checking whether string is a Vowel");
        if (c == 'a' || c == 'A' || c == 'i' || c == 'I' || c == 'u' || c == 'U' || c == 'f' || c == 'F' || c == 'x' || c == 'X' || c == 'e' || c == 'E' || c == 'o' || c == 'O')
        {
            // Log.logInfo("Is a Vowel");
            return true;
        }

        return false;

    }


    public static boolean isHrasva(char c)
    {
        // Log.logInfo("came in is_Vowel: Checking whether string is a Vowel");
        if (c == 'a' || c == 'i' || c == 'u' || c == 'f' || c == 'x')
        {
            // Log.logInfo("Is a Vowel");
            return true;
        }

        return false;

    }

    public static boolean isDirgha(char c)
    {
        // Log.logInfo("came in is_Vowel: Checking whether string is a Vowel");
        if (c == 'A' || c == 'I' || c == 'U' || c == 'F' || c == 'X' || c == 'e' || c == 'E' || c == 'o' || c == 'O')
        {
            // Log.logInfo("Is a Vowel");
            return true;
        }

        return false;

    }


    public static boolean isHrasva(String s1)
    {
        // //Log.logInfo("came in is_Vowel: Checking whether string is a
        // Vowel");
        if (s1.length() != 1) return false;

        return isHrasva(s1.charAt(0));

    }


    public static boolean isHrasvanta(String s1)
    {
        // //Log.logInfo("came in is_Vowel: Checking whether string is a
        // Vowel");

        int len = s1.length();

        if (len < 1) return false;

        return isHrasva(s1.charAt(len - 1));

    }

    public static boolean isDirgha(String s1)
    {
        // //Log.logInfo("came in is_Vowel: Checking whether string is a
        // Vowel");
        if (s1.length() != 1) return false;

        return isHrasva(s1.charAt(0));

    }

    public static boolean isDirghanta(String s1)
    {
        int len = s1.length();

        if (len < 1) return false;

        return isDirgha(s1.charAt(len - 1));

    }

    public static boolean isVowel(String s1)
    {
        // //Log.logInfo("came in is_Vowel: Checking whether string is a
        // Vowel");
        if (s1.length() != 1) return false;

        return isVowel(s1.charAt(0));

    }

    public static boolean isConsonant(char c)
    {
        // Log.logInfo("came in is_consonant: Checking whether string is a
        // consonant");
        if (c == 'k' || c == 'K' || c == 'g' || c == 'G' || c == 'N' || c == 'c' || c == 'C' || c == 'j' || c == 'J' || c == 'Y' || c == 't' || c == 'T' || c == 'd' || c == 'D' || c == 'R' || c == 'w' || c == 'W' || c == 'q' || c == 'Q' || c == 'n' || c == 'p' || c == 'P' || c == 'b' || c == 'B' || c == 'm' || c == 'y' || c == 'r' || c == 'l' || c == 'v' || c == 'L' || c == 'S' || c == 'z' || c == 's' || c == 'h' || c == 'κ' || c == 'Κ' || c == 'γ' || c == 'ζ' || c == 'δ' || c == 'Δ' || c == 'φ' || c == 'τ' || c == 'θ' || c == 'σ')
        {
            return true;
        }

        return false;

    }

    public static boolean isConsonant(String s1)
    {
        // //Log.logInfo("came in is_consonant: Checking whether string is a
        // Vowel");
        if (s1.length() != 1) return false;

        return isConsonant(s1.charAt(0));

    }

} // end of class
