package com.sktutilities.transliteration;

public class KrutiToeLatin
{
String transformed;

public KrutiToeLatin()
	{
		transformed = "";
	}

public String transform(String s1)
	{
		
	transformed = s1;

	transformed = transformed.replaceAll("\\.a","'");   // SLP for avagraha,,,,,using from own system
										// watch out!!!! use "\\.a" not ".a"
	transformed = transformed.replaceAll("RRi","f");	
	
	transformed = transformed.replaceAll("RRI","F");
	transformed = transformed.replaceAll("LLi","x");
	transformed = transformed.replaceAll("LLI","X");

	transformed = transformed.replaceAll("ai","E");
	transformed = transformed.replaceAll("au","O");
	transformed = transformed.replaceAll("kh","K");

	transformed = transformed.replaceAll("gh","G");
		
	
	/***NOTE 1 ***/
	// we have to convert ITRANS '~N' - SKT LIB 'N' but confuses with ITRANS 'N'
	//therefore mdifying
	//transformed = transformed.replaceAll("~N","N"); // watch out!!!!
	// to
	transformed = transformed.replaceAll("~N","5"); // itrans N is fifth of T-vargas, hence 5
	// SKT LIB 'N' = 5, later 5 back again to 'N' at the end

	/***Note 1 ***/
	transformed = transformed.replaceAll("\\.N","~"); // chandra-bindu...watch out do not just use .N use \\.N
													// must be below .replaceAll("~N","5")
	transformed = transformed.replaceAll("Dh","Q"); // must be before .replaceAll("dh","D");
	transformed = transformed.replaceAll("Th","W"); // must be before .replaceAll("th","T");
	transformed = transformed.replaceAll("Ch","C");
	transformed = transformed.replaceAll("jh","J"); 
	transformed = transformed.replaceAll("~n","Y");
	transformed = transformed.replaceAll("T","w");
	
	transformed = transformed.replaceAll("D","q");
	
	transformed = transformed.replaceAll("N","R");// confusion with .replaceAll("~N","N")
																	
	transformed = transformed.replaceAll("th","T"); // watch out!!!!
	transformed = transformed.replaceAll("dh","D");// watch out!!!!
	transformed = transformed.replaceAll("ph","P"); 
	transformed = transformed.replaceAll("bh","B");
	transformed = transformed.replaceAll("S","z");// must be above .replaceAll("sh","S")
																	// to avoid confusion

	transformed = transformed.replaceAll("sh","S");// watchout!!!!
	


	transformed = transformed.replaceAll("5","N"); // refer to NOTE 1

		
	// return transformed;
	return transformed;
	}
}
