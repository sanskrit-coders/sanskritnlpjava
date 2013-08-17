package com.sktutilities.util;


public class VisargaUtil {

public VisargaUtil()
{
	Log.logInfo("In Visarga");
}


// the ajadis

	//*******************BEGINNING OF FUNCTION********************//
public static boolean isVisarga(String str)
	{
		if(str.equals("H"))
		return true;
		return false;
	}
//*******************END OF FUNCTION**********************//



//*******************BEGINNING OF FUNCTION********************//
public static boolean isVisargadi(String str)
	{
		
		Log.logInfo(" Checking if  is_visargadi:::");
		String s1 = VarnaUtil.getAdiVarna(str);
		if(isVisarga(s1) )
			{
				Log.logInfo("I am visargadi");
				return true;
			}
		
		return false;
	}
//*******************END OF FUNCTION**********************//


//*******************BEGINNING OF FUNCTION********************//
public static boolean isVisarganta(String str)
	{
		Log.logInfo(" Checking if  is_visarganta:::");
		String s1 = VarnaUtil.getAntyaVarna(str);
		if(isVisarga(s1) )
			return true;		
		return false;
	}
//*******************END OF FUNCTION**********************//



		
} // end of class visarga.java