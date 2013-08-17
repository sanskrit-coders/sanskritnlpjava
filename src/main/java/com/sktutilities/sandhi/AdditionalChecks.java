package com.sktutilities.sandhi;
/*Purpose: This class takes the result of Sandhi Operations done by one of Vowel,
 * Consonant and Visarga Sandhi CLasses. Parses the Strings on Comma separated Values.
 * Then checks if other rules such as 'anaci ca', jharo jhari savarNe apply
 * 
 */
 
import com.sktutilities.notes.*;
import com.sktutilities.pratyahara.*;
import com.sktutilities.util.*;

public class AdditionalChecks 
{
private String modifiedString;
private VowelUtil vowel;
private PratyaharaDecoder pratyahara1;
private ConsonantUtil con;



public AdditionalChecks()
  {    
  vowel = new VowelUtil();
  pratyahara1 = new PratyaharaDecoder() ;
  modifiedString = "";
  con = new ConsonantUtil();
  } //end of Constructor
  
  
  //*******************BEGINNING OF FUNCTION********************//	
public String anaciCa(String shabda, Comments tip)
{
  boolean spotted_change = false;
  boolean even_more_sandhis = false;
  String return_me = shabda;
  int len = shabda.length();
  char []mem = new char[len+3]; // Assuming there no more than three instances of yar_dvitva!!!!
  int mem_index = 0;
     Log.logInfo("anaci ca ******shabda == " + shabda);

  //For anaci_ca to be meaning ful it must have atleast 3 chars
  if(len < 3 ) {return return_me ;}
      for(int i = 0; i < len; i++ )
        {
         if(even_more_sandhis == false) mem[mem_index++] = shabda.charAt(i);
          else {even_more_sandhis = false;}
        Log.logInfo("mem_index== " + (mem_index-1) + " " + " mem[mem_index]== " + mem[mem_index-1] );
          if(VowelUtil.isHrasva(shabda.charAt(i)) && i < len - 2 ) // must be atleast the third last word
          {
            String char_after = (new Character(shabda.charAt(i+1))).toString();
          
            if(pratyahara1.anta_varna_in_pratyahara("yar",char_after) && !(vowel.isVowel(shabda.charAt(i+2))))
            {
          
            //we are sending to Consonant Sandhi to check if their are other Sandhi's Involved
            String eka = (new Character(shabda.charAt(i+1))).toString();           
            
            String any_new_sandhis = (new ConsonantSandhi(eka,eka,false)).getCombinedSandhiForm();
            //TODO
            //I have left the above and just used false.
            //I should not do this.
            //I will figure it some other time
            if(!any_new_sandhis.equals(eka+eka))
            {
              even_more_sandhis = true;
              int ind = 0;
              while(ind < any_new_sandhis.length() )
              {
                mem[mem_index++] = any_new_sandhis.charAt(ind++) ;
              }
            }
            
            else  mem[mem_index++] = shabda.charAt(i+1) ; // optional dvitva
            
              
            Log.logInfo(" ^^^^^^^^^^^^error_chk == " + any_new_sandhis);                
            spotted_change = true;
            tip.start_adding_notes();					
            tip.setSutraNum("8.4.47") ;
            tip.setSutraPath("anaci ca") ;
            tip.setSutraProc("Reduplication of yar");
            tip.setSource(Comments.sutra) ;
            String cond1 ="If a short vowel is followed by a <yar> phoneme which is followed by a non-vowel, then the <yar> is optionally reduplicated" + 
            "\nshort vowel + <yar> + non-vowel = short vowel + <yar>  + <yar> + non-vowel.\n" ;
            tip.setConditions(cond1);
            }
          }
        }
        
        //fillin rest of the characters with default values
        while(mem_index < mem.length - 1)
        {
           mem[mem_index++] = '\u0000';
        }
         
    
        //if their was a change, then reutrn modified string
        if(spotted_change == true)
        {
        String new_str = "";
        for(int i = 0; i < mem.length;i++)
        {
        Log.logInfo("mem ==[" + i + "]== " + mem[i]);
         if(mem[i] == '\u0000') break;
          new_str += (new Character(mem[i])).toString();         
        }
        Log.logInfo("new_str == " + new_str);
         shabda = new_str;        
         
        }
        return shabda;
}

/******************8END OF METHOD*******************************/

  
  //*******************BEGINNING OF FUNCTION********************//	
public String yanAfterMayDvitva(String shabda,Comments tip)
{
  boolean spotted_change = false;

  String return_me = shabda;
  int len = shabda.length();
  char []mem = new char[len+3]; // Assuming there no more than three instances of yan_dvitva!!!!
  int mem_index = 0;
     Log.logInfo("yan_after_may_divtva******shabda == " + shabda);

  //For anaci_ca to be meaning ful it must have atleast 3 chars
  if(len < 2 ) {return return_me ;}
      for(int i = 0; i < len; i++ )
        {
         mem[mem_index++] = shabda.charAt(i);
         
         Log.logInfo("mem_index== " + (mem_index-1) + " " + " mem[mem_index]== " + mem[mem_index-1] );
         
         String char1 = (new Character(shabda.charAt(i))).toString();
         
          if(pratyahara1.anta_varna_in_pratyahara("may",char1) && i < len - 1 ) // must be atleast the 2nd last word
          {
            String char_after = (new Character(shabda.charAt(i+1))).toString();     
            if(pratyahara1.anta_varna_in_pratyahara("yaR",char_after)) // watch oout using SLP
            {
            mem[mem_index++] = shabda.charAt(i+1) ; // optional dvitva
            spotted_change = true;
            tip.start_adding_notes();					
            tip.setSutraNum("") ;
            tip.setVartikaPath("yaNomayAd-dve-vAcye") ;
            tip.setSutraProc("Reduplication of yaN");
            tip.setSource(Comments.sutra) ;
            String cond1 ="If a <may> phoneme is followed by a <yar> phoneme, the  <yaN> is optionally reduplicated" + 
            "\n<yar> + <yaN> = <yar> + <yaN> + <yaN> .\n" ;
            tip.setConditions(cond1);
            }
          }
        }
        
        //fillin rest of the characters with default values
        while(mem_index < mem.length - 1)
        {
           mem[mem_index++] = '\u0000';
        }
         
    
        //if their was a change, then reutrn modified string
        if(spotted_change == true)
        {
        String new_str = "";
        for(int i = 0; i < mem.length;i++)
        {
        Log.logInfo("mem ==[" + i + "]== " + mem[i]);
         if(mem[i] == '\u0000') break;
          new_str += (new Character(mem[i])).toString();         
        }
        Log.logInfo("new_str == " + new_str);
         shabda = new_str;        
         
        }
        return shabda;
}

/******************8END OF METHOD*******************************/
//*******************BEGINNING OF FUNCTION********************//	
public String savarnaJharoJhariLopa(String shabda,Comments tip)
{
  boolean spotted_change = false;
    boolean skip = false;
  String return_me = shabda;
  int len = shabda.length();
  char []mem = new char[len+5]; // Assuming there no more than three instances of yan_dvitva!!!!
  int mem_index = 0;
     Log.logInfo("savarna_jharo_jhari_lopa******shabda == " + shabda);

  //For savarna_jharo_jhari_lopa to be meaning ful it must have atleast 2 chars
  if(len < 3 ) {return return_me ;}
      for(int i = 0; i < len; i++ )
        {
         if(skip == false )mem[mem_index++] = shabda.charAt(i);
         else { skip = false; }
         Log.logInfo("mem_index== " + (mem_index-1) + " " + " mem[mem_index]== " + mem[mem_index-1] );
      
        if(i < len - 2  )
        {
             String char1 = (new Character(shabda.charAt(i))).toString();
               String char2 = (new Character(shabda.charAt(i+1))).toString();
               String char3 = (new Character(shabda.charAt(i+2))).toString();
        
      
            if(pratyahara1.anta_varna_in_pratyahara("hal",char1) &&
            (pratyahara1.anta_varna_in_pratyahara("Jar",char2) )&& 
            (pratyahara1.adi_varna_in_pratyahara("Jar",char3) ) &&
            con.is_savarna(char2,char3)) // watch oout using SLP
            {
            
              //mem[mem_index++] = shabda.charAt(i+1) ; // optional dvitva
              spotted_change = true;
              skip = true;
              /****************************/
              tip.start_adding_notes();		
              tip.setSutraNum("8.4.64") ;
              tip.setSutraPath("jharo jhari savarNe") ;				
              tip.setSutraProc("Optional jhar-lopa");
              tip.setSource(Comments.sutra) ;
              String cond1 ="consonanat followed by a <jhar> phoneme followed by its savarna <jhar> " +
              " result in the optional elision of the first occuring <jhar>.\n" +
              "<hal><jhar> + <jhar> = <hal>null + <jhar> Optionally";			
              tip.setConditions(cond1);                  
              /***************************/	
              }
          } // end of for
        }
        
        //fillin rest of the characters with default values
        while(mem_index < mem.length - 1)
        {
           mem[mem_index++] = '\u0000';
        }
         
    
        //if their was a change, then reutrn modified string
        if(spotted_change == true)
        {
        String new_str = "";
        for(int i = 0; i < mem.length;i++)
        {
        Log.logInfo("mem ==[" + i + "]== " + mem[i]);
         if(mem[i] == '\u0000') break;
          new_str += (new Character(mem[i])).toString();         
        }
        Log.logInfo("new_str == " + new_str);
         shabda = new_str;        
         
        }
        return shabda;
}

/******************8END OF METHOD*******************************/

  public String getModified_string(String shabda, Comments tip)
  {
   Log.logInfo("ADDITIONAL CHECKS ******shabda == " + shabda);
    int len = shabda.length();
    if(len < 2 ) ;
    else 
    {
      String[] strings;
      strings = shabda.split(",");
      for(int i = 0; i < strings.length; i++ )
      {
        String uno = anaciCa(strings[i],tip);
        if(!uno.equals(strings[i]))
        {
          modifiedString += ", " + uno;
          
          String doosra = yanAfterMayDvitva(uno,tip);
          if(!uno.equals(doosra))
            { 
              modifiedString += ", " + doosra;
            }
          String teesra = savarnaJharoJhariLopa(doosra,tip);
           if(!doosra.equals(teesra))
            { 
              modifiedString += ", " + teesra;
            }
        }
      }
    }
    
    return modifiedString;
  }

//*******************END OF FUNCTION**********************//	

}