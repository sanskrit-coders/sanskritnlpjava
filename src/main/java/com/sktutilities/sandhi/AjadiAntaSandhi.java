package com.sktutilities.sandhi;

import com.sktutilities.util.Log;

public class AjadiAntaSandhi {

// private String check_me;

public AjadiAntaSandhi()
{
	Log.logInfo("In ajadi_anta");
}


// the ajadis

	//*******************BEGINNING OF FUNCTION********************//
public boolean isEjadi(String str)
	{
		Log.logInfo("came in is_ejadi");
		if( str.startsWith("e") || str.startsWith("E") ||
			str.startsWith("o") || str.startsWith("O") || 
			 str.startsWith("e3") || str.startsWith("E3") ||
			str.startsWith("o3") || str.startsWith("O3"))
		{
			Log.logInfo("is an ejadi");		
			return true;
		}
		
		return false;
	}
//*******************END OF FUNCTION**********************//

//*******************END OF FUNCTION**********************//
public boolean isRRigadi(String str)
	{
		Log.logInfo("came in _RRigadi");
		if(str.startsWith("f") || str.startsWith("F") ||
		str.startsWith("x") || str.startsWith("X") )
		return true;
		
		return false;
	}
//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//
public boolean isInadi(String str)
	{
		Log.logInfo("came in _inadi");
		if( str.startsWith("i") || str.startsWith("I") ||
			str.startsWith("u") || str.startsWith("U") )
		return true;
		
		return false;
	}
//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//
	public boolean isIgadi(String str)
	{
		Log.logInfo("came in is_igadi");
		if( isRRigadi(str) || isInadi(str)  )
		return true;
		
		return false;
	}

//*******************END OF FUNCTION**********************//



//*******************BEGINNING OF FUNCTION********************//
public boolean isAkaradi(String str)
	{
		Log.logInfo("came in is_akaradi");
		if( str.startsWith("a") || str.startsWith("A") ||
			str.startsWith("a3")|| str.startsWith("A3")  )
		return true;
		
		return false;
	}
//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//
public boolean isIkaradi(String str)
	{
		Log.logInfo("came in is_ikaradi");
		if( str.startsWith("i") || str.startsWith("I") || 
			str.startsWith("i3") ||str.startsWith("I3")  )
		return true;
		
		return false;
	}
//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//
public boolean isUkaradi(String str)
	{
		Log.logInfo("came in is_ukaradi");
		if( str.startsWith("u") || str.startsWith("U")  || 
			str.startsWith("u3")|| str.startsWith("U3") )
		return true;
		
		return false;
	}
//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//
public boolean isRRikaradi(String str)
	{
		Log.logInfo("came in is_RRikaradi");
		if( str.startsWith("f") || str.startsWith("F") || 
			str.startsWith("f3") || str.startsWith("F3"))
		return true;
		
		return false;
	}
//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//
public boolean isLLikaradi(String str)
	{
		Log.logInfo("came in is_LLikaradi");
		if( str.startsWith("x") || str.startsWith("X")  )
		return true;
		
		return false;
	}
//*******************END OF FUNCTION**********************//

//*******************END OF FUNCTION**********************//

public boolean isAgadi(String str)
	{
		Log.logInfo("came in is_agadi");
		if( isIgadi(str) || isAkaradi(str)  )
		return true;
		
		return false;
	}



//*******************BEGINNING OF FUNCTION********************//


// the ajantas


//*******************BEGINNING OF FUNCTION********************//

public boolean isEjanta(String str)
	{
	Log.logInfo("came in is_ejanta");
		if( str.endsWith("e") || str.endsWith("E") ||
			str.endsWith("o") || str.endsWith("O") ||
			str.endsWith("e3") || str.endsWith("E3") ||
			str.endsWith("o3") || str.endsWith("O3") )
		{
			Log.logInfo("is ejanta");
			return true;
		}
		
		return false;
	}

//*******************END OF FUNCTION**********************//


//*******************BEGINNING OF FUNCTION********************//
public boolean isRRiganta(String str)
	{
		Log.logInfo("came in is_RRiganta");
		if( isRRikaranta(str)  || isLLikaranta(str) )
		return true;
		
		return false;
	}
//*******************END OF FUNCTION**********************//


//*******************BEGINNING OF FUNCTION********************//
public boolean isInanta(String str)
	{
	Log.logInfo("came in is_inanta");
		if( isIkaranta(str) || isUkaranta(str) )
		return true;
		
		return false;
	}

//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//
	public boolean isIganta(String str)
	{
		Log.logInfo("came in is_iganta");
		if( isRRiganta(str) || isInanta(str)  )
		return true;
		
		return false;
	}

//*******************END OF FUNCTION**********************//


//*******************BEGINNING OF FUNCTION********************//
public boolean isAkaranta(String str)
	{
	Log.logInfo("came in is_akaranta:");
		if( str.endsWith("a") || str.endsWith("A") ||
			str.endsWith("a3") || str.endsWith("A3")  )
		{ 
			Log.logInfo("I am akaranta, returning truw");
			return true;
			}
		
		return false;
	}

//*******************END OF FUNCTION**********************//

public boolean isaganta(String str)
	{
		Log.logInfo("came in is_aganta");
		if(  isAkaranta(str) || isIganta(str)  )
		{
			Log.logInfo("is an aganta");
			return true;
		}
		
		return false;
	}



//*******************BEGINNING OF FUNCTION********************//
public boolean isIkaranta(String str)
	{
	Log.logInfo("came in is_ikaranta");
		
		if( str.endsWith("i") || str.endsWith("I") ||
			str.endsWith("i3") || str.endsWith("I3")  )
		return true;
		
		return false;
	}

//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//
public boolean isUkaranta(String str)
	{
	Log.logInfo("came in is_ukaranta");
		
		if( str.endsWith("u") || str.endsWith("U") ||
			str.endsWith("u3")|| str.endsWith("U3")  )
		return true;
		
		return false;
	}

//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//
public boolean isRRikaranta(String str)
	{
		Log.logInfo("came in is_RRikaranta");
		if( str.endsWith("f") || str.endsWith("F") ||
			str.endsWith("f3") || str.endsWith("F3") )
		return true;
		
		return false;
	}

//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//
public boolean isLLikaranta(String str)
	{
	Log.logInfo("came in is_LLikaranta");
		if( str.endsWith("x") || str.endsWith("X")  )
		return true;
		
		return false;
	}

//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//
public boolean isPlutanta(String str)
	{
	Log.logInfo("came in is_plutanta:");
	int str_len = str.length();
	if( str_len == 1 ) return false; // i.e user is inputting just 3 
		if( str.endsWith("3") && isVowel(str.charAt(str_len - 2))  )
		{	
			Log.logInfo("is_plutanta: True");
			return true;			
		}
		
		Log.logInfo("is_plutanta: False");
		return false;
	}

//*******************END OF FUNCTION**********************//
//*******************BEGINNING OF FUNCTION********************//
public boolean isSavarna( String str1, String str2)
	{
		Log.logInfo(" in is_savarna " );
		if( isAkaranta(str1) && isAkaradi(str2)  )
		return true;
		if( isIkaranta(str1) && isIkaradi(str2)  )
		return true;
		if( isUkaranta(str1) && isUkaradi(str2)  )
		return true;
		if( isRRikaranta(str1) && isRRikaradi(str2)  )
		return true;
		if( isRRikaranta(str1) && isLLikaradi(str2)  )
		return true;
		if( isLLikaranta(str1) && isRRikaradi(str2)  )
		return true;
		if( isLLikaranta(str1) && isLLikaradi(str2)  )
		return true;
		Log.logInfo(" is not savarna " );
		
		return false;
	}

//*******************END OF FUNCTION**********************//


//*******************BEGINNING OF FUNCTION********************//

public boolean isAjadi(String str)
	{
		Log.logInfo("came in is_ajadi: Checking whether begins with vowel");
		if( isEjadi(str) || isAkaradi(str) || isIgadi(str) )
		{
			Log.logInfo("Begins with a Vowel");
			return true; 
		}
		
		return false;

	}
//*******************END OF FUNCTION**********************//


//*******************BEGINNING OF FUNCTION********************//

public boolean isAjanta(String str)
	{
		Log.logInfo("came in is_ajanta: Checking whether ends in vowel");
		if( isEjanta(str) || isAkaranta(str) || isIganta(str) )
			{
			Log.logInfo("Ends with a Vowel");
			return true; 
		}
		
		return false;

	}
//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//

public boolean isVowel(char c)
	{
		Log.logInfo("came in is_vowel: Checking whether string is a vowel");
		if( c == 'a' || c == 'A' || c == 'i' || c == 'I' || c == 'u' || c == 'U' || c == 'f' || 
			c == 'F' || c == 'x' || c == 'X' || c == 'e' || c == 'E' || c == 'o' || c == 'O' )
			{
			Log.logInfo("Is a Vowel");
			return true; 
		}
		
		return false;

	}
//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//

public boolean isvowel(String s1)
	{
		//Log.logInfo("came in is_vowel: Checking whether string is a vowel");
		if( s1.length() != 1 )
			return false;
		
		return isVowel(s1.charAt(0));

	}
//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//

public boolean isConsonant(char c)
	{
		Log.logInfo("came in is_consonant: Checking whether string is a consonant");
		if( c == 'k' || c == 'K' || c == 'g' || c == 'G' || c == 'N' || 
			c == 'c' || c == 'C' || c == 'j' || c == 'J' || c == 'Y' || 
			c == 't' || c == 'T' || c == 'd' || c == 'D' || c == 'R' || 
			c == 'w' || c == 'W' || c == 'q' || c == 'Q' || c == 'n' || 
			c == 'p' || c == 'P' || c == 'b' || c == 'B' || c == 'm' || 
			c == 'y' || c == 'r' || c == 'l' || c == 'v' || 
			c == 'S' || c == 'z' || c == 's' || c == 'h'  )
			{
			Log.logInfo("Is a Consonant");
			return true; 
		}
		
		return false;

	}
//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//

public boolean isAconsonant(String s1)
	{
		//Log.logInfo("came in is_consonant: Checking whether string is a vowel");
		if( s1.length() != 1 )
			return false;
		
		return isConsonant(s1.charAt(0));

	}
//*******************END OF FUNCTION**********************//

} //end of class
