package com.sktutilities.transliteration;

import java.util.*;

import com.sktutilities.util.Log;
@Deprecated
public class Latin
{
private String transformed;
private Hashtable<String,String> unicode;


public Latin()
	{
	Log.logInfo("**** Entered Latin:::");
	transformed = "";
	unicode = new Hashtable<String,String>();
	
	
	
	//matra = new Hashtable<String,String>();
	//my_hashtable();
	Log.logInfo("**** Leaving Latin:::");
	}


//*******************BEGINNING OF FUNCTION********************//
public String transform(String s1)
	{

	transformed = "";
		
	unicode.put("Q", "ph"); // q in KEYBOARD, u in e-latin/kruti_Deva
	unicode.put("W", ""); // w
	unicode.put("E", "m"); //e
	unicode.put("R", "t");//r
	unicode.put("T", "j");//t
	unicode.put("Y", "l");//y
	unicode.put("U", "n");//u
	unicode.put("I", "p");//i
	unicode.put("O","v");//o
	unicode.put("P", "c");//p
	unicode.put( "[", "kh");     //[
	unicode.put( "]", ",");          //]	
	
	unicode.put("q", "u"); // q in KEYBOARD, u in e-latin/kruti_Deva
	unicode.put("w", "u\u0304"); // w
	unicode.put("e", "a"); //e
	unicode.put("r", "ta");//r
	unicode.put("t", "ja");//t
	unicode.put("y", "la");//y
	unicode.put("u", "na");//u
	unicode.put("i", "pa");//i
	unicode.put("o","va");//o
	unicode.put("p", "ca");//p
	unicode.put( "{", "kh");     //[
	unicode.put( "{", ",");          //]	
	
	
	
	unicode.put( "a", "m\u0307"); //anusvaar
	unicode.put( "s", "e");
	unicode.put( "d", "k");
	unicode.put( "f", "i");
	unicode.put( "g", "d\u0323a");
	unicode.put( "h", "i\u0304");
	unicode.put( "j", "ra");
	unicode.put( "k", "a\u0304");
	unicode.put( "l", "s"); //unicode for brahmin sh
	unicode.put( ";", "ya");
	unicode.put( "\'", "s\u0301");	
	unicode.put( "\\", "?");
	
	unicode.put( "A", "|");
	unicode.put( "S", "ai");
	unicode.put( "D", "k");
	unicode.put( "F", "th");
	unicode.put( "G", "l\u0323a");
	unicode.put( "H", "bha");
	unicode.put( "J", "s\u0301ra");
	unicode.put( "K", "jn\u0304a");
	unicode.put( "L", "s"); //unicode for brahmin sh
	unicode.put( ":", "ya");
	unicode.put( "\"", "s\u0301");	
	unicode.put( "|", "?");
	
	unicode.put( "Z", "r");
	unicode.put( "X", "g");
	unicode.put( "C", "b");
	unicode.put( "V", "t\u0323a");
	unicode.put( "B", "tH\u0323"); 
	unicode.put( "N", "d\u0323a"); 
	unicode.put( "M", "e");
	unicode.put( "<", "e");
	unicode.put( ">", "n\u0323"); // Na as in ravan, 
	unicode.put("?", "dh"); // 
	
	unicode.put( "z", "r");
	unicode.put( "x", "ga");
	unicode.put( "c", "ba");
	unicode.put( "v", "a");
	unicode.put( "b", "i"); 
	unicode.put( "n", "da"); //
	unicode.put( "m", "u");
	unicode.put( ",", "e");
	unicode.put( ".", "n\u0323"); // Na as in ravan,
	unicode.put("/", "dh"); // 
	
	//unicode.put("Enter", "\n"); // Enter
	unicode.put("\n", "\n"); // Enter
	unicode.put(" ", " "); // Space
	unicode.put("\t", "\t"); // Tab
	
	for(int i = 0; i < s1.length(); i++ )
	{
		char c = s1.charAt(i);
		Character C = new Character(c);
		String str = (String) C.toString();
		if(unicode.containsKey(str))
		transformed += unicode.get(str);		
	}
	
	return transformed; // return transformed;
	}

}
