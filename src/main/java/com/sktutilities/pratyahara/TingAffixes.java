package com.sktutilities.pratyahara;

import com.sktutilities.util.*;

public class TingAffixes {
EncodingUtil encod;	

	public TingAffixes()
	{
	encod = new EncodingUtil();	
	}
	
	public boolean is_ting(String s1)
	{
		if(s1.equals("taN") || s1.equals("tiN") ) // using scharfe
			return true;
			return false;
	}
	
	
	public String printTingAffixes(String abbr)
	{
		String tis = "";
		String tang = "";
		String return_me = "";
		
		tis = "tip, tas, jhi,\n";
		tis += "sip, thas, tha,\n";
		tis += "mib, vas, mas,\n";
		
		tang += "ta, AtAm, jha,\n";
		tang += "thAs, AthAm, dhvam\n";
		tang += "iD, vahi, mahi~N\n";
		
		if(abbr.equals("taN") ) {
		return_me = tang;
		}
		else {
		return_me = tis + tang;		
		}
		
				
		return encod.convertRawItransToDevanagari(return_me);
	}
}