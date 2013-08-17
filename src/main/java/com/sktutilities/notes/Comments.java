package com.sktutilities.notes;


import com.sktutilities.sandhi.SandhiJFrame;
import com.sktutilities.util.EncodingUtil;
import com.sktutilities.util.Log;

public class Comments
{
	//private Vector<String> notes_vec;
	public final static String sutra = "Given by Panini Sutra";
	public final static String vartika = "Given by Katyayana Vartika";
	static int options;
	private String sutraNum[]; // number such as 1.4.42
	private String sutraPath[]; // Pan Sutra such as 'ade~N guNaH.
	private String vartikaPath[]; // Kat Vartika such as "RRiti RRi va vacanam
	private String sandhiType[]; // Vowel Sandhi, Con Sandhi, Visarga Sandhi
	private String sutraProc[]; // schutva adesha etc
	private String notesNotes;
	private String source[]; // sutra or vartika
	
	private String conditions[];
	private int optionsPointer; 
	private String default_notes = "Two words in proximity";
	private final int DEFAULT_ARRAY_SIZE = 6;
	//private EncodingUtil encoding;

//*******************BEGINNING OF Default Constructor********************//	
	public Comments()
	{
		Log.logInfo("i came in constructor of tippani ");
		//encoding = new EncodingUtil();		
		notesNotes = default_notes;
		options = 1;
		optionsPointer = -1;
		sutraNum = new String[DEFAULT_ARRAY_SIZE] ;
		sutraPath = new String[DEFAULT_ARRAY_SIZE] ;
		vartikaPath = 	new String[DEFAULT_ARRAY_SIZE] ;
		sandhiType = new String[DEFAULT_ARRAY_SIZE] ;	
		sutraProc = new String[DEFAULT_ARRAY_SIZE] ;	
		source = new String[DEFAULT_ARRAY_SIZE] ;	
		conditions = new String[DEFAULT_ARRAY_SIZE] ;
		Log.logInfo("isEmpty() ?" + isEmpty());
		Log.logInfo("i came in constructor of tippani ");		
	}
//*******************END OF Default Constructor**********************//

//*******************BEGINNING OF FUNCTION********************//	
	public void increment_pointer()
	{
		//if(options_pointer < options - 1) // changed on 12:17 PM 8jun
		optionsPointer++;	
	}
//*******************END OF FUNCTION**********************//



//*******************BEGINNING OF FUNCTION********************//	
	public void decrementPointer()
	{
		//if(options_pointer < options - 1) // changed on 12:17 PM 8jun
		if(optionsPointer > -1) 
		{
			//set_sandhi_type("");
			setSutraProc("");
			setSource(""); 
			setSutraPath(""); 
			setSutraNum("");
			setVartikaPath("");		
			setConditions("");
			optionsPointer--;	
		}
	}
//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//	
	public boolean isEmpty()
	{ 
		Log.logInfo("i am in isEmpty()");
		if(optionsPointer == -1)
		return true;	
		return false;
	}
//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//	
	public boolean rikta_asti()
	{
		Log.logInfo("i am in isEmpty()"); flush();
		if(optionsPointer == -1)
		return true;	
		return false;
	}
//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//	
	public void setNotes()
	{
		notesNotes = get_sandhi_type(0) + "\n"; // what if their are many other kinds of sandhis
		
		for(int i = 0; i <= optionsPointer; i++)
		{
		if(optionsPointer > 0 ) notesNotes += "Optional Form " + (i + 1) + ": " + "\n";
		
		notesNotes += get_sutra_proc(i) + "\n";
		notesNotes += get_source(i) + " "; 
		notesNotes += get_sutra_path(i) + " "; 
		//if(get_sutra_num(i) != null ||  get_sutra_num(i).length() <= 0 || get_sutra_num(i).equals("") ) 
		notesNotes += get_sutra_num(i) ;
		
		//if(get_vartika_path(i) != null || get_vartika_path(i).length() <= 0) 
		notesNotes += " " +  get_vartika_path(i) + " ";
		
		notesNotes += "\n" + "Conditions: " + "\n"; 
		notesNotes += get_conditions(i) + "\n\n"; 				
		}
		
		Log.logInfo("*_*_*_*_*_*_*_*_options_pointer == " + optionsPointer );
		Log.logInfo( "\n *_*_*_*_*_*_*_*_notes_notes == "  + notesNotes);

	}

//*******************END OF FUNCTION**********************//

//*******************BEGINNING OF FUNCTION********************//
public void copyNotes(Comments n)
{
	//setNotes();
	for(int i = 0; i <= n.get_options_pointer(); i++)
	{
	start_adding_notes();
	setSutraNum(n.get_sutra_num(i));
	setSutraPath(n.get_sutra_path(i));
	setSutraProc(n.get_sutra_proc(i));
	setSource(n.get_source(i));
	setConditions(n.get_conditions(i));
	setVartikaPath(n.get_vartika_path(i));
		
	}
}
//*******************END OF FUNCTION**********************//


//*******************BEGINNING OF FUNCTION********************//	
	public String getNotes()
	{
		setNotes();
		
		return notesNotes;
	}
//*******************END OF FUNCTION**********************//



public void start_adding_notes() {
	 Log.logInfo( "*_*_*_*_*_*_*_*_ started Adding Notes... "); 
		Log.logInfo("*_*_*_*_*_*_*_*_*_*_options_pointer == " + optionsPointer );		
	 optionsPointer++;	 
	 }
	 
	 
//setters
public void setSutraNum(String s1) {	
	sutraNum[optionsPointer] = s1; 	
	}

public void setSutraPath(String s1) {	
	if(SandhiJFrame.romanSutra == false )
	sutraPath[optionsPointer] = EncodingUtil.convertRawItransToDevanagari(s1) ; 		
	else {sutraPath[optionsPointer] = s1;	}	
	}
	
	
public void set_sandhi_type(String s1) {sandhiType[0] = s1; }
public void setSutraProc(String s1) {sutraProc[optionsPointer] = s1; }

public void setSource(String s1) {
	source[optionsPointer] = s1;	 
	Log.logInfo("*_*_*_*_*_*_*_*_*_*_options_pointer == " + optionsPointer );
	}

public void setVartikaPath(String s1) {
	if(SandhiJFrame.romanSutra == false )
	vartikaPath[optionsPointer] = EncodingUtil .convertRawItransToDevanagari(s1) ; 		
	else {vartikaPath[optionsPointer] = s1;	}	
	}
	
public void setConditions(String s1) {conditions[optionsPointer] = s1; }
public void append_condition(String s1) {
	String append_me = get_conditions(optionsPointer) + "\n\n" + s1;
	conditions[optionsPointer] = append_me; 
	
	}
public void set_options(int i1) {options = i1; }
public void set_options_pointer(int i1) {optionsPointer = i1; }

//getters
public String get_sutra_num(int i)
{
	if(sutraNum[i] == null || sutraNum[i].length() == 0 ) return "";
	else return "(" + sutraNum[i]  + ")"; 
}

public String get_sutra_path(int i){
	if (sutraPath[i] == null || sutraPath[i].length() == 0) return "";
	else return "'" + sutraPath[i]  + "'"; 
	}
public String get_vartika_path(int i){
	if (vartikaPath[i] == null || vartikaPath[i].length() == 0) return "";
	else return "'" + vartikaPath[i]  + "'"; 
	}
	
public String get_sandhi_type(int i) {return sandhiType[i];}
public String get_sutra_proc(int i){return sutraProc[i];}
public String get_source(int i){return source[i]; }
public String get_conditions(int i){return conditions[i] ; }
public int get_options(){return options; }
public int get_options_pointer(){return optionsPointer; }


//*******************BEGINNING OF FUNCTION********************//	
	public void flush()
	{
		
		notesNotes = default_notes;
		//set_options(1);
		for(int i = 0; i < DEFAULT_ARRAY_SIZE; i++)
		{
		set_sandhi_type("");
		setSutraProc("");
		setSource(""); 
		setSutraPath(""); 
		setSutraNum("");
		setVartikaPath("");		
		setConditions(""); 				
		}
		optionsPointer = -1;
	}
//*******************END OF FUNCTION**********************//


}