package com.sktutilities.sandhi;

import com.sktutilities.notes.*;
import com.sktutilities.pratyahara.*;
import com.sktutilities.util.*;

public class VisargaDisplay {
private String combinedSandhiForm;
private String visarga = "Visarga Sandhi";

private static final String PADANTA = "Padanta Dependency\n" ;
private boolean padanta;
private boolean pragrhya;
private Comments notes1;
private PratyaharaDecoder pratyahara1;


	public VisargaDisplay(String anta, String adi, boolean padanta, boolean pragrhya)
	{
        this.padanta = padanta;
        this.pragrhya = pragrhya;
        
		Log.logInfo("VisargaDisplay()");
		pratyahara1 = new PratyaharaDecoder();
		
		notes1 = new Comments();
		notes1.set_sandhi_type(visarga);
		
		combinedSandhiForm = combineIntoSandhi(anta, adi);
	}

	
//*******************BEGINNING OF FUNCTION********************//
	
	private String combineIntoSandhi(String anta, String adi)
	{
		
		String return_me = anta + " " + adi;
		String stripped_anta = VarnaUtil.stripAntyaVarna(anta);	
   		int anta_length = anta.length();
		
		
	
			//******BLOCK begins******//
		if ( ( anta.equals("saH") || anta.equals("ezaH")) && 
		pratyahara1.adi_varna_in_pratyahara("hal",adi))
		{
			return_me = stripped_anta+ " " + adi;	//SLP 'z' = ITRANS 'S'
			notes1.start_adding_notes();
			notes1.setSutraNum("6.1.128") ; // 6.1.128 accd to Brahamdutt Jignasu Ashtadhyayim 132 according to B-A-C
			notes1.setSutraPath("etattadoH sulopo'koran~nsamAse hali") ;
			notes1.setSutraProc("visarga-lopa");
			notes1.setSource(Comments.sutra) ;
			String cond1 = "'saH'(tad) and 'eSaH'(etad) pronouns followed by a consonant leads to visarga-lopa\n" +
			"Restrictions: 'saH' and 'eSaH' should be in na~n-samaasa, niether be modified by affixes(e.g. akac) " + 
			" which intoruce a 'k' in them.";
			notes1.setConditions(cond1);
		}
		
		//******BLOCK ends******//
		
		
		//******BLOCK begins******//
		//Brihad Anuvad Chandrika pg24
		
	   else	if( (VisargaUtil.isVisarganta(anta)) &&
			(anta_length > 1) &&  
			(("i".equals((new Character(anta.charAt(anta_length - 2))).toString()) )||
			("u".equals((new Character(anta.charAt(anta_length - 2))).toString())) ) &&
			( ConsonantUtil.is_kavargadi(adi) || ConsonantUtil.is_pavargadi(adi) ) )
			
			
			
			{
				return_me = stripped_anta+ "z" + adi;	//SLP 'z' = ITRANS 'S'
				notes1.start_adding_notes();
				notes1.setSutraNum("8.3.41") ;
				notes1.setSutraPath("idudupadhasya cApratyayasya") ;
				notes1.setSutraProc("Sa-kAra-Adesha");
				notes1.setSource(Comments.sutra) ;
				String cond1 = "[(i,u) + H] + <ka,pa-varga> = [(i,u) + S] + <ka,pa-varga>\n" +
								"Restriction: the visrga should not belong to an affix";
				notes1.setConditions(cond1);
			}
	
		//******BLOCK ends******//
		
				
		
		
				
		
		//******BLOCK begins******//	
	
		 //B98
			//		if( Visarga.is_visarganta(anta) && ( Consonant.is_pavargadi(adi) || Consonant.is_kavargadi(adi ) ) )
			//altering above in light of information in BV 138

			else if( VisargaUtil.isVisarganta(anta) && 
			(adi.startsWith("k") || adi.startsWith("K") || adi.startsWith("p") || adi.startsWith("P")) )
				//SLP
				
		{//checked 6-30
			return_me = jihva_etc(anta, adi) + ", " + anta + " " + adi;
			
			
			//******OPT1******//
			notes1.start_adding_notes();
			notes1.setSutraNum("8.3.37") ;
			notes1.setSutraPath("kupvoH\u014Fka\u014Fpau ca") ;
			notes1.setSutraProc("jihvAmUliya/upadhAmanIya-adesh(\u014F) to kavarga/pavarga respectively");
			notes1.setSource(Comments.sutra) ;
			String cond1 = "visarga-ending word followed by ka-varga and pavarga get a jihvAmUliya/upadhAmanIya-adesh\n" +
			
			"\n\nHowever according to Bhaimi Vyakhya, 'kharavasAnyorvisarjanIyaH'(8.3.15) is the only sutra " +
			"that allows a 'visarga-aadesh to a s-ending word' - and is also the only Sutra that makes " + 
			"Visargas possible in the Sanskrit World from -s-terminating Words - when a <khar> phoneme follows.\nSince 'k''kh','p' and'ph' are " +
			"the only kavarga and pavarga phonemes that are included in pratyahaara <khar>, hence this operation is only limited to these four phonemes" +
			"visarga-ending + ka-varga/pa-varga = jihvAmUliya/upadhAmanIya + ka-varga/pa-varga\n";
			notes1.setConditions(cond1);
			//******OPT1******//
			
			
			//******OPT2******//
			notes1.start_adding_notes();
			notes1.setSutraNum("8.3.37") ;
			notes1.setSutraPath("kupvoH\u014Fka\u014Fpau ca") ;
			notes1.setSutraProc("visarga-adesh to visarga");
			notes1.setSource(Comments.sutra) ;
			String cond2 = "visarga-ending + ka-varga/pa-varga = visarga-ending + ka-varga/pa-varga." +
			"The 'ca' (and) of 8.3.37 allows the visarga form." ;
			 notes1.setConditions(cond2);
			//******OPT2******//
			String cond3 = "\n\nPls. Note: \u014F is the nearest Unicode visual equivalent I could find for the Devanagari Symbol for jihvAmUliya/upadhAmanIya\n" +
			"'8' is how it is represented in itrans/SLP.";
			notes1.append_condition(cond3);
			
			//check_padanta(true);
		}
		//******BLOCK ends******//
		
		
		//******BLOCK begins******//
		//Brihad Anuvad Chandrika pg24
		else if( VisargaUtil.isVisarganta(anta) && 
				   (pratyahara1.adi_varna_in_pratyahara("Kar",adi)) /*this has to be SLP */ && 
				   (adi.length() > 1) &&
				 (ConsonantUtil.is_shar((new Character(adi.charAt(1))).toString() ) ) )// scharfe
		{
			Log.logInfo("adi == " + adi);
			return_me = anta + " " + adi;
			
			//******OPT1******//
			notes1.start_adding_notes();
			notes1.setSutraNum("8.3.36") ;
			notes1.setSutraPath("sharpare visarjanIyaH") ; 
			notes1.setSutraProc("sakaar-adesh to visarga Blocked");
			notes1.setSource(Comments.sutra) ;
			String cond1 ="visarga-ending word followed by a khar-initial word which has a <shar> initial right "
			 + "after is not eligible for 'sakaara-aadesh' given by 'visarjanIyasya saH'(8.3.34).\n" + 
							"H + <khar><shar> = Sakaara-Adesha to Visarga Blocked by 8.3.36";
			notes1.setConditions(cond1);
			//******OPT1******//
		}
		
		
		
		//******BLOCK ends******//
		
		
		//******BLOCK begins******//
		//B104
		else if(VisargaUtil.isVisarganta(anta) && ConsonantUtil.is_shar(VarnaUtil.getAdiVarna(adi) ) )
		{//checked 6-30
			return_me = visarjaniya_saH(anta, adi) + ", " + anta + " " + adi;
						
			//******OPT1******//
			notes1.start_adding_notes();
			notes1.setSutraNum("8.3.34") ;
			notes1.setSutraPath("visarjanIyasya saH") ; 
			notes1.setSutraProc("sakaar-adesh to visarga");
			notes1.setSource(Comments.sutra) ;
			String cond1 ="visarga followed by a khar-initial word gets sakaar-adesh\n" + 
							"H + <khar> = s + <khar>";
			notes1.setConditions(cond1);
			//******OPT1******//
			
			//notes1.increment_pointer(); // increment the pointer
			
			
			//******OPT2******//
			notes1.start_adding_notes();
			notes1.setSutraNum("8.3.36") ;
			notes1.setSutraPath("vA shari") ;
			notes1.setSutraProc("visarga-adesh to visarga");
			notes1.setSource(Comments.sutra) ;
			String cond2 ="visarga followed by a shar-initial word gets sakaara-adesh only optionally, hence visarga-adesha here.\n" + 
							"H + <shar> = H + <shar>";
			notes1.setConditions(cond2);
			//******OPT2******//
			
			
			
		}
			//******BLOCK ends******//
		
		//******BLOCK begins******//
		//B103
		else if(VisargaUtil.isVisarganta(anta) && ConsonantUtil.is_kharadi(adi) )
		{//checked 6-30
			return_me = visarjaniya_saH(anta, adi);
			notes1.start_adding_notes();						
			notes1.setSutraNum("8.3.34") ;
			notes1.setSutraPath("visarjanIyasya saH") ;
			notes1.setSutraProc("sakaar-adesh to visarga");
			notes1.setSource(Comments.sutra) ;
			String cond2 ="visarga followed by a khar-initial word gets sakaar-adesh\n" + 
							"H + khar = s + khar";
			notes1.setConditions(cond2);			
			
			
		}
		//******BLOCK ends******//
		
		//******BLOCK begins******//
		//B105-106
		else if(anta.endsWith("aH") &&  adi.startsWith("a") )
		{ //checked 6-30
			return_me = u_adesh(anta, adi);
			notes1.start_adding_notes();						
			notes1.setSutraNum("6.1.109") ;
			notes1.setSutraPath("ato roraplutAdaplute") ;
			notes1.setSutraProc("ukaar-adesh to visarga");
			notes1.setSource(Comments.sutra) ;
			String cond1 ="'aH' followed by 'a' gets u-kaara adesh\n" + 
							"padanta 'as' + 'a' = aru.N + 'a' By 'sa-sajuSo ru.NH'(8.2.66)\n"
							+ " aru.N + 'a' = a(+ut) + 'a' By 6.1.110\n" +
							"a + ut + 'a' = o + 'a' by Guna Sandhi\n"
							+ "o + a = para-rupa Sandhi";
			notes1.setConditions(cond1);			
			
			//check_padanta(true);
		}
		//******BLOCK ends******//
		
		//******BLOCK begins******//		
		
		//B107
		else if( anta.endsWith("aH") && 
		(pratyahara1).adi_varna_in_pratyahara("haS",adi) ) // scharfe
		{
			return_me = u_adesh(anta, adi);
			notes1.start_adding_notes();						
			notes1.setSutraNum("6.1.110") ;
			notes1.setSutraPath("hashi ca") ;
			notes1.setSutraProc("ukaar-adesh to visarga");
			notes1.setSource(Comments.sutra) ;
			String cond1 ="'aH' followed by <hash> pratyahaara phonemes gets u-kaara adesh\n" + 
							"padanta 'as' + <hash> = aru.N + <hash> By 'sa-sajuSo ru.NH'(8.2.66)\n"
							+ " aru.N + <hash> = a(+ut) + <hash> By 6.1.110\n" +
							"a + ut + <hash> = o + <hash> by Guna Sandhi\n" ;
			notes1.setConditions(cond1);			
		
		
		}
		
		//******BLOCK ends******//
		
		//******BLOCK begins******//		
		//B108-109
		else if( ( anta.equals("Bos") || anta.equals("Bagos") || anta.equals("aGos") ||
				   anta.equals("BoH") || anta.equals("BagoH") || anta.equals("aGoH") || 
				   anta.endsWith("aH") || anta.endsWith("AH") || anta.endsWith("a3H") || 
				   anta.endsWith("as") || anta.endsWith("As") || anta.endsWith("a3s")   )  )
				
		{
			Log.logInfo("******bho-bhago Condition");
			
			//B108
			if(VowelUtil.isAjadi(adi) )
			{
					return_me =  stripped_anta + "y" + adi;
					
					
					//***********OPT1***********//
					notes1.start_adding_notes();
					notes1.setSutraNum("8.3.17 ") ;
					notes1.setSutraPath("bho-bhago-agho-a-pUrvasya yo.ashi");
					notes1.set_sandhi_type(visarga);
					notes1.setSutraProc("ya-kaara-adesha");
					notes1.setSource(Comments.sutra) ;
					String cond1 ="padanta bhos-bhagos-aghos and 'as'/'aH'/'As'/'AH'/'a3s'/'a3H'-ending word +" + 
					"'hash'-pratyahara beginning word gets a 'yakaar-adesha(8.3.17)";
					notes1.setConditions(cond1);
					//***********OPT1***********//
				
					if( anta.endsWith("aH") || anta.endsWith("AH") || anta.endsWith("a3H") || 
					   anta.endsWith("as") || anta.endsWith("As") || anta.endsWith("a3s") )
					{   
					
					return_me += ", " + stripped_anta + " " + adi;
					
					//***********OPT2***********//
					notes1.start_adding_notes();	
					notes1.setSutraNum("8.3.19 ") ;
					notes1.setSutraPath("lopaH shAkalayasya");
					notes1.set_sandhi_type(visarga);
					notes1.setSutraProc("yakaara-lopa(elision)");
					notes1.setSource(Comments.sutra) ;
					String cond2 ="<a> followed by 'y'/'v' at the end of a pada, followed by <ash> phoneme lead to optional 'y/v' ellision.\n" +
					 " padanta <a>y/<a>v + <ash> = <a> + <ash>\n";
					notes1.setConditions(cond2);
					//***********OPT2***********//
				
					} // end of inner if
					
					
					if(anta.equals("saH"))   /// && Vowel.is_ajadi(adi)
					{
						VowelSandhi vs;
						vs = new VowelSandhi(stripped_anta,adi, pragrhya);
						
						String temp1 = stripped_anta + " " + adi;
						String temp2 = vs.getCombinedSandhiForm();
						
						
						if(temp2.equals(temp1))
						return_me += ", " + temp1;
						else
						return_me += ", (" + temp1 +"->) " + temp2;
						
						
						notes1.start_adding_notes();
						notes1.setSutraNum("6.1.130") ; // 6.1.128 accd to Brahamdutt Jignasu Ashtadhyayim 132 according to B-A-C
						notes1.setSutraPath("so'ci lope cetpAdapUraNam") ;
						notes1.setSutraProc("optional visarga-lopa");
						notes1.setSource(Comments.sutra) ;
						String cond3 = "optional visarga-lopa of Word 'saH' if followed by a Vowel " + 
						"for pAda-pUraNam(metric-consistency) in a Chanda\n" + 
						"saH + Vowel = sa + Vowel\n" + 
						"sa + Vowel leads to furthur Vowel Sandhis";
						notes1.setConditions(cond3);
					}
				
			} // end of outer if
			
			
		//B109	
			else if ( pratyahara1.adi_varna_in_pratyahara("haS",adi) )   
				// scharfe
				{ // checked 6-30
					return_me = stripped_anta + " " + adi;
										
					notes1.start_adding_notes();
					notes1.setSutraNum("8.3.22") ;
					notes1.setSutraPath("hali sarveSaam") ;
					notes1.set_sandhi_type(visarga);
					notes1.setSutraProc("ya-kaara-adesha followed by 'ya-kaara-lopa'");
					notes1.setSource(Comments.sutra) ;
					String cond ="pada-ending bhos-bhagos-aghos and 'as'/'aH'/'As'/'AH'/'a3s'/'a3H'-ending word " + 
					"followed by 'hash'-pratyahara beginning word gets a 'yakaar-adesha by 8.3.17 which gets deleted by 8.3.22\n"
					+"bhoH/bhagoH/aghoH/word ending in <a>H + <hash> = bhoy/bhagoy/aghoy/<a>y + <hash> by 8.3.17\n" +
					"bhoy/bhagoy/aghoy/<a>y + <hash> = bho/bhago.agho/<a> + <hash>. Deletion of 'y'";
					
					 
					notes1.setConditions(cond);
					
				}  // end of inner else if
				
			
		} // end of main else if		
		//******BLOCK ends******//
		
		
			//******BLOCK begins******//			
		//B111   ..... also implement in Consonant Sandhi
		else if(VisargaUtil.isVisarganta(anta) && ( adi.startsWith("r") ) )
		{															
		// checked 6-30
			 /// ********if***********///
			if(anta.endsWith("aH") || anta.endsWith("iH")|| anta.endsWith("uH") || 
			   anta.endsWith("AH") || anta.endsWith("IH")|| anta.endsWith("i3H")|| 
			   anta.endsWith("a3H") || anta.endsWith("i3H")|| anta.endsWith("u3H")	)
			{
				return_me = dirghaTo_aN(anta, adi);				
			
				
				notes1.start_adding_notes();
				notes1.setSutraNum("6.3.110") ;
				notes1.setSutraPath("Dhralope pUrvasya dIrgho.aNaH") ;
				notes1.set_sandhi_type(visarga);
				notes1.setSutraProc("ref-lopa followed by 'aN'-dIrghIkaraNa");
				notes1.setSource(Comments.sutra) ;
				String cond1 ="aN-pratyahara(a,A,a3,i,I,i3,u,U,u3) phoneme and visarga " +
				"when followed by refa-initial in second word lead to refa-lopa and aN-dIrghIkaraN\n" +				
				"A visarga-ending word - which is actually a 'sakaranta(ending in 's') - " +
				" when followed by refa-initial second word, goes through the following transformation :\n" + 
				"(1) <aN>s + r (Original State) -> H + r by 'kharavasAnyoH visarjaniyaH'(8.3.34) \n" +
				"(2) <aN>s + r -> <aN>ru.N + r ...by 'sa-sajusho ru.NH'-> \n" +  
				"(3) <aN>ru.N + r -> <aN>r + r ...by 'upadeshe'canunAsika-it'() \n" +
				"(4) <aN>r + r -> <aN> + null + r ...by 'ro ri'(8.3.14)\n"	+
				"(5) <aN> + r -> dirgha <aN> + r \n" +
				"In (4) the first refa is deleted by 8.3.14 and in (5) <aN> phoneme becomes long('dIrgha') by 6.3.110";
		 		notes1.setConditions(cond1);
					
			}
			/// ********end of if***********///
			
			/// ********else***********///
			else  { //if adi.startsWith("r")
				return_me = VarnaUtil.stripAntyaVarna(anta) + " " + adi;	
							
				notes1.start_adding_notes();
				notes1.setSutraNum("8.3.14") ;
				notes1.setSutraPath("ro ri")  ;
				notes1.set_sandhi_type(visarga);
				notes1.setSutraProc("refa-Adesh folllowed by ref-lopa due to two adjacent refas");
				notes1.setSource(Comments.sutra) ;
				String cond ="Two adjoining refas(r's) result in ellision of one of them\n." +
				"A visarga-ending word - which is actually a 'sakaranta(ending in 's') - " +
				" when followed by refa-initial second word, goes through the following transformation :\n" + 
				"(1) s + r (Original State) -> H + r by 'kharavasAnyoH visarjaniyaH'(8.3.34) \n" + 
				"(2) s + r -> ru.N + r ...by 'sa-sajusho ru.NH'-> \n" +  
				"(3) ru.N + r -> r + r ...by 'upadeshe'canunAsika-it'() \n" +
				"(4) r + r -> null + r ...by 'ro ri'(8.3.14)\n"	;
				notes1.setConditions(cond);
				
				} // end of inner else
					check_padanta(true);
				/// ********end of else***********///	
			
				
		} // end of main else_if
		//******BLOCK ends******//
		
				
	
		
		//******BLOCK begins******//
		else if(VisargaUtil.isVisarganta(anta) && !pratyahara1.adi_varna_in_pratyahara("Kar",adi)  ) //SLP..in ITRAMS 'khar'
		{
			return_me = VarnaUtil.stripAntyaVarna(anta) + "r" + adi;
										
			notes1.start_adding_notes();
			notes1.setSutraNum("8.3.33") ;
			notes1.setSutraPath("sa-sajuSo ru.NH") ;
			//notes1.set_sandhi_type(visarga);
			notes1.setSutraProc("refa-adesha'");
			notes1.setSource(Comments.sutra) ;
			String cond = PADANTA + "the terminal 's' of padanta words become visarga through the following steps\n" +
			"(hari is being used as a generic example below)\n" + 
			"hari + s -> hari + ru.N ...by 'sasajuSo ru.NH'(8.3.33)\n" +
			"-> hari + r ... by anubandha-lopa sutra 'upadeshe'c anunAsika-it(1.3.2)\n" +
			"-> hari + H...by 'khar-avasAnayorvisarjanIyaH' (8.3.15) \n" + 
			"in 'avasana' ( at the end ) or if a 'khar' pratyahaara phoneme follows 'r' become 'H'.\n" + 
			"-> If what follows after hariH is not a 'khar' phoneme then the 'r' is not transformed into visarga. " + 
			"unless another rule finds scope.";
			
			 
			notes1.setConditions(cond);
		}
		//******BLOCK ends******//
	
		
		
   Log.logInfo("quitting visarga::make_sandhi:: return_me ==  "+ return_me);
		return return_me;
	}

//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//	
public String dirghaTo_aN(String anta, String adi)
{
	String return_me = anta + " " + adi;
	
	// anta is something like rAmaH.... we need to remove "H"
	String stripped = VarnaUtil.stripAntyaVarna(anta); 
	// now we have rama...but we must also remove 'a' so that we get ram
														
	String stripped2 = VarnaUtil.stripAntyaVarna(stripped); 
	
	
	
	if(stripped.endsWith("a") || stripped.endsWith("a3") )
	return_me = stripped2 + "A" + " "  + adi;
	
	else if(stripped.endsWith("i") || stripped.endsWith("i3") )
	return_me = stripped2 + "I" + " "  + adi;
	
	else if(stripped.endsWith("u") || stripped.endsWith("u3") )
	return_me = stripped2 + "U" + " "  + adi;
	
	Log.logInfo("quitting dirgha_to_aN   return_me ==  "+ return_me);
	
	return return_me;
}

//*******************END OF FUNCTION**********************//		
	

//*******************BEGINNING OF FUNCTION********************//	
public String u_adesh(String anta, String adi)
{
	Log.logInfo("********Came in u_adesh");
	Log.logInfo(" in u_adesh" );
	String return_me = anta + " " + adi;
	String strip_anta = anta.substring(0,anta.length()-1);
	
	
	VowelSandhi vs = new VowelSandhi(strip_anta,"u", pragrhya);
	String step1 = vs.guna_sandhi(strip_anta,"u");
	String step2;
		Log.logInfo("step1 == " + step1);
	
	if(ConsonantUtil.is_haladi(adi))
	step2 = step1 + " " + adi;//if adi is a consonant then no need to go furthur

	else
	{
		VowelSandhi vs2 = new VowelSandhi(step1,adi, pragrhya);
		step2 = vs2.purva_rupa(step1,adi);
	}
	
	
	return_me =  step2;
    Log.logInfo("***********quitting u_adesh return_me ==  "+ return_me);
	return return_me;
	
}

//*******************END OF FUNCTION**********************//		
	
//*******************BEGINNING OF FUNCTION********************//	
public String visarjaniya_saH(String anta, String adi)
{
	Log.logInfo(" in visarjaniya_saH" );
	String return_me = anta + " " + adi;
	String strip_anta = anta.substring(0,anta.length()-1);
	String sakar_adesh = strip_anta + "s";
	ConsonantSandhi sc = new ConsonantSandhi(sakar_adesh, adi, padanta);
	return_me = sc.combineIntoSandhi(sakar_adesh, adi);
	//tippani con_tip = sc.getNotesObject();
	//notes1.copyNotes(con_tip);
	
	
   Log.logInfo("quitting visarjaniya_saH return_me ==  "+ return_me);
	return return_me;
	
}

//*******************END OF FUNCTION**********************//	


//*******************BEGINNING OF FUNCTION********************//

public void check_padanta(boolean b)
{
	if(SandhiJFrame.padanta == b )
		;
	else 
	SandhiJFrame.padanta = b;	
}


//*******************END OF FUNCTION**********************//	





//*******************BEGINNING OF FUNCTION********************//	
	public String jihva_etc(String anta, String adi)
{
	Log.logInfo(" in jihva_etc" );
	String return_me = anta + " " + adi;
	String strip_anta = anta.substring(0,anta.length()-1);
	return_me =  strip_anta + "8" + adi;
	
	
   Log.logInfo("quitting jihva_etc return_me ==  "+ return_me);
	return return_me;
	
}
	
	
//*******************END OF FUNCTION**********************//


	


	
//*******************BEGINNING OF FUNCTION********************//	
public String getCombinedSandhiForm()
{
return combinedSandhiForm;
}

//*******************END OF FUNCTION**********************//	



//*******************BEGINNING OF FUNCTION********************//	
public String getNotes()
{
return notes1.getNotes();
}

//*******************END OF FUNCTION**********************//	

} // end of class