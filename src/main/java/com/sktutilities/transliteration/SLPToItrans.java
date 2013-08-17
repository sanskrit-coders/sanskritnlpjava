package com.sktutilities.transliteration;

public class SLPToItrans
{

public static String transform(String transformed)
	{
	
//	Log.logInfo("SLPToItrans: " + transformed  );


	transformed = transformed.replaceAll("f", "6"); // if I make f == "RRi" 
											//then RRi will later become NNI, 
											//hence storing in 6
	
	/***NOTE 1 ***/
	transformed = transformed.replaceAll("N","5"); // N (kvargIya) -> 5 -> ~N 
	/***Note 1 ***/
	
	transformed = transformed.replaceAll("R","N");// confusion with .replaceAll("N","~N"), ref. note 1
														//  must be above replaceAll("f", "RRi");
	//transformed = transformed.replaceAll("f", "RRi");	
		
	transformed = transformed.replaceAll("F","7");// same reason as above
	transformed = transformed.replaceAll("x","LLi");
	transformed = transformed.replaceAll("X","LLI");

	transformed = transformed.replaceAll("E","ai");
	transformed = transformed.replaceAll("O","au");
	transformed = transformed.replaceAll("K","kh");

	transformed = transformed.replaceAll("G","gh");

    transformed = transformed.replaceAll("c","ch");
	transformed = transformed.replaceAll("C","Ch");
	transformed = transformed.replaceAll("J","jh"); 
	transformed = transformed.replaceAll("Y","~n");

	transformed = transformed.replaceAll("T","th"); // watch out!!!!
																// must be above .replaceAll("w","T")
	transformed = transformed.replaceAll("D","dh");// watch out!!!!
																// must be above .replaceAll("q","D")
	transformed = transformed.replaceAll("w","T");
	transformed = transformed.replaceAll("W","Th");
	transformed = transformed.replaceAll("q","D");
	transformed = transformed.replaceAll("Q","Dh");
																	

	transformed = transformed.replaceAll("P","ph"); 
	transformed = transformed.replaceAll("B","bh");
	
	transformed = transformed.replaceAll("S","sh");
	transformed = transformed.replaceAll("z","Sh");
	
	
	transformed = transformed.replaceAll("~",".N"); // chandrabindu 
	transformed = transformed.replaceAll("'", ".a");	// avagraha
	
	transformed = transformed.replaceAll("5","~N"); // refer to NOTE 1, 5 -> ~N ( kavargIya )
													//must be below ...replaceAll("~",".N") otherwise confusion

				
	
	transformed = transformed.replaceAll("6", "RRi");
	transformed = transformed.replaceAll("7", "RRI");
	
	//Log.logInfo("SLPToItrans: transformed" +  transformed );			
	return transformed; // return transformed;
	}

}
