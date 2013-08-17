package com.sktutilities.pratyahara;

import java.util.ArrayList;
import java.util.Iterator;

import com.sktutilities.util.EncodingUtil;
import com.sktutilities.util.Log;
import com.sktutilities.util.VowelUtil;

public class SivaSutra
{
    ArrayList<String> listOfElements;

    public SivaSutra()
    {
        populateList();
    }

    public void populateList()
    {
        listOfElements = new ArrayList<String>();
        listOfElements.add("a");
        listOfElements.add("i");
        listOfElements.add("u");
        listOfElements.add("-R");
        listOfElements.add("f");
        listOfElements.add("x");
        listOfElements.add("-k");
        listOfElements.add("e");
        listOfElements.add("o");
        listOfElements.add("-N");
        listOfElements.add("E");
        listOfElements.add("O");
        listOfElements.add("-c");

        listOfElements.add("ha");
        listOfElements.add("ya");
        listOfElements.add("va");
        listOfElements.add("ra");
        listOfElements.add("-w");
        listOfElements.add("la");
        listOfElements.add("-R");
        listOfElements.add("Ya");
        listOfElements.add("ma");
        listOfElements.add("Na");
        listOfElements.add("Ra");
        listOfElements.add("na");
        listOfElements.add("-m");
        listOfElements.add("Ja");
        listOfElements.add("Ba");
        listOfElements.add("-Y");
        listOfElements.add("Ga");
        listOfElements.add("Qa");
        listOfElements.add("Da");
        listOfElements.add("-z");
        listOfElements.add("ja");
        listOfElements.add("ba");
        listOfElements.add("ga");
        listOfElements.add("qa");
        listOfElements.add("da");
        listOfElements.add("-S");
        listOfElements.add("Ka");
        listOfElements.add("Pa");
        listOfElements.add("Ca");
        listOfElements.add("Wa");
        listOfElements.add("Ta");
        listOfElements.add("ca");
        listOfElements.add("wa");
        listOfElements.add("ta");
        listOfElements.add("-v");
        listOfElements.add("ka");
        listOfElements.add("pa");
        listOfElements.add("-y");
        listOfElements.add("Sa");
        listOfElements.add("za");
        listOfElements.add("sa");
        listOfElements.add("-r");
        listOfElements.add("ha");
        listOfElements.add("-l");

    }

    // *****************************************//
    public String getPrathamVarna(String s1)
    {
        String returnString = "";
        if (VowelUtil.isVowel(s1.charAt(0)))
        {
            returnString = s1.substring(0, 1);
        }

        else if (VowelUtil.isConsonant(s1.charAt(0)) && s1.length() > 1)
        {
            returnString = s1.substring(0, 2);
        }
        return returnString;
    }

    // *****************************************//
    /*
     * Date May 12 2007 This Method saves us from the blunder done in the One
     * Argument get_pratyahaara(SLP). Where the boolean that decides whether
     * markers should be used or not is tied up with the VIEW. Hence making it
     * poor design and unfir for Using with the Web Version. The changes below
     * remove this problem.
     */
    public String getPratyahaara(String strInSLP, boolean hideMarkers)
    {
        String returnVal = "";
        
        // I if empty String is entered
        if (strInSLP.length() == 0)
        {
            returnVal = "Error: Cannot process Empty String";
        }

        // II if length of SLP Representation of String is greater than 3
        else if (strInSLP.length() > 3)
        {
            returnVal = "Error: " + EncodingUtil.convertSLPToDevanagari(strInSLP) + " is not a Vaid Pratyahara";
        }

        else if ((VowelUtil.isVowel(getPrathamVarna(strInSLP)) && strInSLP.length() == 2) || (VowelUtil.isConsonant(strInSLP.charAt(0)) && strInSLP.length() == 3))
        {
            String helperString = "";
            Iterator<String> iterator = listOfElements.iterator();
            boolean check = false;
            boolean Nakaar = false;
            String prathamaVarna = getPrathamVarna(strInSLP);
            // Display Elements
            while (iterator.hasNext())
            {
                String element = (String) iterator.next();
                String last = "-" + strInSLP.substring(strInSLP.length() - 1, strInSLP.length());

                if (last.equals("-R")) // equal to ITRANS 'N'
                {
                    if (element.equals("a") || element.equals("i") || element.equals("u") && check == true)
                    {
                        Nakaar = true;
                    }
                }

                if (prathamaVarna.equals(element))
                {
                    check = true; // found the first varna for pratyahaara
                    // generation
                }

                if (check == true)
                {
                    if (element.equals(last))
                    {
                        if (hideMarkers == false)
                        {
                            returnVal = helperString + element.substring(element.length() - 1, element.length()) + "\n";
                            if (Nakaar == true)
                            {
                                helperString = "1: \n" + returnVal + "\n2: \n" + returnVal;
                                Nakaar = false;
                                continue;
                            }
                        }
                        else
                        {
                            returnVal = helperString.substring(0, helperString.length() - 2);// remove
                            // extra
                            // comma
                            // from
                            // end
                            // of
                            // list
                            if (Nakaar == true)
                            {
                                helperString = "1: \n" + returnVal + "\n\n2: \n" + returnVal + " \n";
                                Nakaar = false;
                                continue;
                            }
                        }

                        break;

                    }

                    else if (element.startsWith("-"))
                    {
                        if (hideMarkers == false)
                        {
                            helperString += element.substring(element.length() - 1, element.length()) + "\n";
                            continue;
                        }
                        else
                        {
                            helperString = helperString.substring(0, helperString.length() - 2) + "\n"; //remove terminal comma
                            continue;
                        }
                    }

                    else
                        helperString += element + ", ";
                }

            } // end of while
            returnVal = EncodingUtil.convertSLPToDevanagari(returnVal);
            if(returnVal.trim().length() == 0)
            {
                returnVal = "Error: " + EncodingUtil.convertSLPToDevanagari(strInSLP) + " is not a Vaid Pratyahara";
            }
        } // end of if

        else
        {
            returnVal = "Error: " + EncodingUtil.convertSLPToDevanagari(strInSLP) + " is not a Vaid Pratyahara";
        }
        
       // Log.logInfo("return_me == " + returnVal);
        return returnVal;

    }

}
