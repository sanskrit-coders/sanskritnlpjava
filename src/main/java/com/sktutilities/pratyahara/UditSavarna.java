package com.sktutilities.pratyahara;

import com.sktutilities.util.*;

public class UditSavarna {
	
	public UditSavarna()
	{
	}
	
	public boolean is_udita(String s1)
	{
		if(s1.equals("ku") || s1.equals("cu") || s1.equals("wu") 
			|| s1.equals("tu") || s1.equals("pu") )
			return true;
			return false;
	}
	public String vargas(String udita_abbr)
	{
		String return_me = "";
		if( udita_abbr.equals("ku") )
		return_me = "ka, Ka, ga, Ga, Na"; // using Scharfe Transliteration Scheme
		else if( udita_abbr.equals("cu") )
		return_me = "ca, Ca, ja, Ja, Ya"; 
		else if( udita_abbr.equals("wu") )
		return_me = "wa, Wa, qa, Qa, Ra"; 
		else if( udita_abbr.equals("tu") )
		return_me = "ta, Ta, da, Da, na"; 
		else if( udita_abbr.equals("pu") )
		return_me = "pa, Pa, ba, Ba, ma"; 
		
		return EncodingUtil.convertSLPToDevanagari(return_me);
	}
}