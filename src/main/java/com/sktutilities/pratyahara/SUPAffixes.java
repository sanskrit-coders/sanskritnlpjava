package com.sktutilities.pratyahara;

import com.sktutilities.util.*;

public class SUPAffixes {
EncodingUtil encod;	

	public SUPAffixes()
	{
	encod = new EncodingUtil();	
	}
	
	public boolean is_sup(String s1)
	{
		if(s1.equals("suw") || s1.equals("sup") ) // using scharfe
			return true;
			return false;
	}
	
	
	public String printSupAffixes(String abbr)
	{
		String return_me = "";
		
		if(abbr.equals("suw") ) 
		return_me = "su, au, jas, am, auT";
		
		else {
		return_me = "su, au, jas,\n";	
		return_me += "am, auT, shas,\n";
		return_me += "TA, bhyAm, bhis,\n";
		return_me += "~Ne, bhyAm, bhyas,\n";
		return_me += "~Nasi, bhyAm, bhyas,\n";
		return_me += "~Nas, os, Am,\n";
		return_me += "~Ni, os, sup\n";
		}
		
				
		return encod.convertRawItransToDevanagari(return_me);
	}
}