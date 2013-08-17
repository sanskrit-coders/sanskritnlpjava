package com.sktutilities.pratyahara;

import java.util.*;

import com.sktutilities.util.Log;

public class HelpItem
{
private String itrans_display;
private Vector<String>  itrans_vowels;
private Vector<String>  itrans_consonants;


//*******************BEGINNING OF FUNCTION********************//
public String display_Vector()
{
	Enumeration vowel_enum = itrans_vowels.elements();
	Enumeration con_enum = itrans_consonants.elements();
	
	int check = 1;
	itrans_display = "Vowels: \n";
	//Display Elements
	while(vowel_enum.hasMoreElements() )
	{	
		if( check%4 == 0)
			{itrans_display += vowel_enum.nextElement() +  "\n"; }
		else 
			{itrans_display += vowel_enum.nextElement() +  "   |    ";}
		check++;
	}	
	
	
	int check1 = 1;
	itrans_display += "\nConsonants: \n";
	
	//Display Elements
	while(con_enum.hasMoreElements() )
	{	
		if( check1%5 == 0)
			{itrans_display += con_enum.nextElement() +  "\n"; }
		else 
			{itrans_display += con_enum.nextElement() +  "   |    ";}
		check1++;
	}	
	
	Log.logInfo(" itrans_display == " + itrans_display );
	return itrans_display;
}

//*******************END OF FUNCTION********************//


//*******************BEGINNING OF CONSTRUCTOR********************//

public HelpItem()
	{
	itrans_display = "";
	itrans_vowels = new Vector<String> (22);
	itrans_consonants = new Vector<String> (42);
	my_Vector();
	}


//*******************END OF CONSTRUCTOR********************//




//*******************BEGINNING OF FUNCTION********************//
public void my_Vector ()
	{
		
	//itrans_consonants = new Vector();
	
	itrans_vowels.add("a : \u0905");
	itrans_vowels.add("aa/A : \u0906");
	itrans_vowels.add("i : \u0907");
	itrans_vowels.add("ii/I : \u0908");
	itrans_vowels.add("u : \u0909");
	itrans_vowels.add("uu/U : \u090a");
	itrans_vowels.add("RRi/R^i : \u090b");
	itrans_vowels.add("RRI/R^I : \u0960");
	itrans_vowels.add("LLi/L^i : \u090c");
	itrans_vowels.add("LLI/L^I : \u0961");
	itrans_vowels.add("e : \u090f");
	itrans_vowels.add("ai : \u0910");
	itrans_vowels.add("o : \u0913");
	itrans_vowels.add("au : \u0914");
	itrans_vowels.add(".n/M/.m : \u0905\u0902"); // anusvara
	itrans_vowels.add("H : \u0905\u0903"); // visarga
	
	itrans_consonants.add("k : \u0915");
	itrans_consonants.add("kh : \u0916");
	itrans_consonants.add("g : \u0917");
	itrans_consonants.add("gh : \u0918");
	itrans_consonants.add("~N : \u0919");
	itrans_consonants.add("c : \u091a");
	itrans_consonants.add("chh : \u091b");
	itrans_consonants.add("j : \u091c");
	itrans_consonants.add("jh : \u091d");
	itrans_consonants.add("~n : \u091e");
	itrans_consonants.add("T : \u091f"); // Ta as in Tom
	itrans_consonants.add("Th : \u0920"); 
	itrans_consonants.add("D : \u0921"); // Da as in David
	itrans_consonants.add("Dh : \u0922");
	itrans_consonants.add("N : \u0923");
	itrans_consonants.add("t : \u0924"); // ta as in tamasha
	itrans_consonants.add("th : \u0925"); // tha as in thanks
	itrans_consonants.add("d : \u0926"); // da as in darvaaza
	itrans_consonants.add("dh : \u0927"); // dha as in dhanusha
	itrans_consonants.add("n : \u0928");
	itrans_consonants.add("p : \u092a");
	itrans_consonants.add("ph : \u092b");
	itrans_consonants.add("b : \u092c");
	itrans_consonants.add("bh : \u092d");
	itrans_consonants.add("m : \u092e");
	itrans_consonants.add("y : \u092f");
	itrans_consonants.add("r : \u0930");
	itrans_consonants.add("l : \u0932");
	itrans_consonants.add("v/w : \u0935");
	itrans_consonants.add("sh : \u0936");
	itrans_consonants.add("S : \u0937");
	itrans_consonants.add("s : \u0938");
	itrans_consonants.add("h : \u0939");
	itrans_vowels.add(".N : \u0905\u0901"); // anunAsika - cchandra bindu, using V to represent it\
	itrans_vowels.add(".a : \u093d"); // avagraha using "'"
	itrans_vowels.add("pluta sign : \u0969"); // 3 equals to pluta
}
//*******************END OF FUNCTION********************//


}