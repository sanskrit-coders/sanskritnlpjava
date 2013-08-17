package com.sktutilities.sandhi;

import com.sktutilities.transliteration.*;
import com.sktutilities.util.*;

public class VowelConsonantSandhi {
private ConsonantUtil con;
private VowelUtil vowel;
private String sandhi_krt;
private String notes;
private SLPToItrans x;
private static final String apavada = "\nAn Apavad Niyama.";
private static final String vartika = "\nGiven By Kat. Var. ";
private static final String sutra = "\nGiven by Pan. Sut. ";
private static final String opt2 = "Optional Form 2: \n"; 
private static final String opt3 = "Optional Form 3: \n";
private static final String usg1 = "Usage 1: \n";
private static final String usg2 = "Usage 2: \n"; 
private static final String padanta = "Condition: Only if String 1 is padanta";

	public VowelConsonantSandhi(String anta, String adi, boolean padanta, boolean pragrhya, boolean useSLP)
	{
		vowel = new VowelUtil();
		con = new ConsonantUtil();
		x = new SLPToItrans();
		notes = "No Notes";
		sandhi_krt = make_sandhi(anta, adi, padanta, pragrhya, useSLP);
	}
	
	private String make_sandhi(String anta, String adi, boolean padanta, boolean pragrhya, boolean useSLP)
	{
		String return_me = anta + adi;
		
		if (  ( ConsonantUtil.is_halanta(anta) || VowelUtil.isAjanta(anta) ) && 
			  ( VowelUtil.isAjadi(adi)  || ConsonantUtil.is_haladi(adi) ) )
		{
			//261
			if( ConsonantUtil.is_chavarganta(anta) && 
					(padanta == true  || ConsonantUtil.is_jhaladi(adi) ) )
				{
					return_me = choh_kuh(anta,adi);
					notes = "Halanta Sandhi." + sutra + "'coH kuH'(8.2.30)" +
					"\n" + padanta + ". Or is followed by a jhala-beginning Word";
					Log.logInfo(" Sending to choh_kuh");
				}
			//262
			else if( ConsonantUtil.is_jhalanta(anta) && (padanta == true) )
				{
				return_me = jhalam_jash(anta,adi);	
				notes = "Halanta Sandhi." + sutra + "'jhalAm jasho.ante'(8.2.39)" +
						"\n" + padanta;
				Log.logInfo(" Sending to jhalam_jash");
				}
		}
		
		
					

	return return_me;
	}
	


//*******************BEGINNING OF FUNCTION********************//	
public String getsandhi_krt()
{
return sandhi_krt;
}

//*******************END OF FUNCTION**********************//	



//*******************BEGINNING OF FUNCTION********************//	
public String getNotes()
{
return notes;
}

//*******************END OF FUNCTION**********************//



//*******************BEGINNING OF FUNCTION********************//	
public String jhalam_jash(String anta,String adi)
{
	Log.logInfo(" Welcome to jhalam_jash: anta == " + anta + " adi == " + adi);
	String anta_varna = VarnaUtil.getAntyaVarna(anta);
	String stripped = strip_anta(anta);
	String return_me = anta + adi;
	if( con.is_jhal(anta_varna) ) // corrected mistake I earlier has is_jash instead of is_jhal
		{
			if (con.is_kavarganta(anta) || anta.endsWith("h"))
			return_me = stripped + "g" + adi;
			
			else if (con.is_chavarganta(anta) || anta.endsWith("S") )
			return_me = stripped + "j" + adi;
			
			else if (con.is_Tavarganta(anta) || anta.endsWith("z"))  // is Tavarganta Ta like in Tom etc 
			return_me = stripped + "q" + adi;
			
			else if (con.is_tavarganta(anta) || anta.endsWith("s") )
			return_me = stripped + "d" + adi;
			
			else if (con.is_pavarganta(anta) )
			return_me = stripped + "b" + adi;
			
		
			
			// INCOMPLETE ... need to check for adeshas for 'sha' 'Sa' amd 'sa'..take care off
		}
		
			Log.logInfo(" Quitting jhalam_jash: return_me == " + return_me);
			return return_me;
}

//*******************END OF FUNCTION**********************//	


//*******************BEGINNING OF FUNCTION********************//	
public String choh_kuh(String anta,String adi)
{
	String anta_varna = VarnaUtil.getAntyaVarna(anta);
	String stripped = strip_anta(anta);
	String return_me = anta;
	if( ConsonantUtil.is_chavarganta(anta) )
		{
			if (anta_varna.equals("c") )
				return_me = stripped + "k" + adi;
				
			else if (anta_varna.equals("C") )
				return_me = stripped + "K" + adi;
				
			else if (anta_varna.equals("j") )
				return_me = stripped + "g"+ adi;
				
			else if (anta_varna.equals("J") )
				return_me = stripped + "G"+ adi;
				
			else if (anta_varna.equals("Y") )
				return_me = stripped + "N"+ adi;
			
		}
		return return_me;
}

//*******************END OF FUNCTION**********************//



//*******************BEGINNING OF FUNCTION********************//
public String strip_adi(String s1)
	{
		int str_len = s1.length();
		String stripped1 = s1.substring(1, str_len);   // remove the initial phoneme
		if (stripped1.startsWith("3")&& str_len >= 2 )   // pls observer checking for stripped2 not s2.
				// cos s2 == A3laya but only stripped2 == 3laya
		{ stripped1 = strip_adi(stripped1);}
		
		return stripped1;
	}

//*******************END OF FUNCTION**********************//



//*******************BEGINNING OF FUNCTION********************//
public String strip_anta(String s1)
	{
		int str_len = s1.length();
		String stripped1 = s1.substring(0, str_len - 1);  // removes the terminal phoneme
		
		if (s1.endsWith("3") && str_len >= 2 )
		{ stripped1 = strip_anta(stripped1);}
				
		return stripped1;
	}


//*******************END OF FUNCTION**********************//



}