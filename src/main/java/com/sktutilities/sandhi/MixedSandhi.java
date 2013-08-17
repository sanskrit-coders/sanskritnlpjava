/* this class handles sandhis which are cross-category i.e. apply for Vowel, Consonant and/or Visarga sandhi.
 * This class uses static methods so that we donot have to initialize anything and by one call take care
 * of everything with least hassle.
 */
 
package com.sktutilities.sandhi;
import com.sktutilities.util.*;
import com.sktutilities.notes.*;
public class MixedSandhi
{


private ConsonantUtil con;
	
	public MixedSandhi()
	{
		con = new ConsonantUtil();
	}
	

//*******************BEGINNING OF FUNCTION********************//	

public String shaakalya_ya_va_lopa(String anta, String adi, Comments tip)
  {
      String return_me = "";
      
      
	String stripped_anta = VarnaUtil.stripAntyaVarna(anta);
      
      if((anta.endsWith("ay") || anta.endsWith("Ay") || anta.endsWith("a3y") || 
         anta.endsWith("av") || anta.endsWith("Av") ||anta.endsWith("a3v")) && 
         con.is_ashadi(adi))
	      {
		      return_me = stripped_anta + " " + adi ;
		      
		      
		      /***************************/
		      tip.start_adding_notes();		
		      tip.setSutraNum("8.3.19") ;
		      tip.setSutraPath("lopaH shAkalyasya") ;				
		      tip.setSutraProc("Optional yakaara-vakaara lopa");
		      tip.setSource(Comments.sutra) ;
		      String cond2 = ConsonantUtil.padanta + " <a> phoneme followed by yakaara-vakaara in padanta " + 
		      "followed by an <ash> phoneme leads to optional elision of yakaar-vakaar\n" + 
		      "padanta ['a'/A'/'a3' + 'y'/v'] + <ash> =  'a'/A'/'a3' + <ash> \n" + 
		      "Pls. Note: If String 1 is not padanta, this form is invalid." ;
		      
		      tip.setConditions(cond2);
		      /***************************/
		 }
	 
	 return return_me;
  }

//*******************END OF FUNCTION**********************//	
	
}