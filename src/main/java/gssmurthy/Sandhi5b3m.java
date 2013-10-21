package gssmurthy;

import java.io.*;
class Sandhi5b3m
{
 public static void main(String args[])
	{
	
	 
	 String string0=new String();
Section1:/*provides for i/o interaction through monitor*/
	System.out.println("This Sandhi program implements external sandhi rules of Sanskrit."+('\n')+
	"If you input two Sanskrit words in Roman script as per baraha4.0 convention,"+('\n')+
        "it will output the combination of the words as per  sandhi rules of Sanskrit."+('\n')+
	"The baraha4.0 transliteration scheme, as per devanaagaree alphabetical order is, as follows"+('\n')+
	"svara: a, aa or A, i, ee or I, u, uu or U, Ru, RU, lRu, e, ai, o, ou"+('\n')+
	"anusvaara: m, visarga: h"+('\n')+
	"vya~Jjana: ka varga: k, kh or K, g, gh or G, ~G"+('\n')+
	"           ca varga: c, ch or C, j, jh or J, ~J"+('\n')+
	"           Ta varga: T, Th, D, Dh, N"+('\n')+
	"           ta varga: t, th, d, dh, n"+('\n')+
	"           pa varga: p, ph or P, b, bh or B, M"+('\n')+
	"           antasTha: y, r, l,v"+('\n')+
	"           uuShmaa: S, Sh, s, h" +('\n')+
	"           anunaasika: ~M"+('\n')+
	"(NOTE: 1.This program neglects lRu as it occurs very rarely"+('\n')+
	"2.Input words are not validated."+('\n')+
	"3.If compound or joined words are input,the program may not give "+('\n')+
	"  the correct output in case of word-specific sandhi rules."+('\n')+
	"4.G.S.S.MURTHY HAS THE COPYRIGHT FOR THIS PROGRAM : E-mail:murthygss@gmail.com"+('\n'));
 	

	for (int w=0;;w++)	
	{String string1=new String();
	String string2=new String();
	

	{try
	{System.out.println(('\n')+"Pl enter the first word or to exit, enter QUIT.");
	System.out.flush();
  	DataInputStream in=new DataInputStream(System.in);
	string1=in.readLine();/*first word is string1*/
	string1=string1.trim();
	if (string1.equals("QUIT"))
	{break;
	}
	System.out.println("Pl enter the second word");
	System.out.flush();
	string2=in.readLine();/*second word is string2*/
	string2=string2.trim();
	System.out.println('\n');
	}
 	catch(IOException e)
	{System.out.println("I/O/error");
	System.exit(1);
	}	
	
	int stuff=0;
	String string1a=new String();
	char aN[]={'a','e','u'};/*set up a character array for a,e,u*/
	String aaN[]={"A","I","U"};/*set up a string array for A,I,U. 
	String array is required as only strings can be concatenated.*/
	int n1=string1.length();/* compute length of string1*/
	if (n1<4)
	{
	string1="111".concat(string1);
	
	stuff=1;
	n1=n1+3;
	}
	int n2=string2.length();/* compute length of string2*/
	
	int pad=0;
	if (n2<2)
	{string2=string2.concat("11");
	n2=n2+2;
	pad=1;
	}
	char lett11=string1.charAt(n1-1);/*find last char of first word*/	
	
	String string1a1=new String();
	char lett12=string1.charAt(n1-2);/*find last but one char of word1*/
	char lett13=string1.charAt(n1-3);/*last but 2 chars of 1st word*/
section2:
	/*In this "for" loop if the last two letters of the first word
	 are aa,ee,uu
	 they get converted to A,I,U as a process of Normalization
	 before application of subsequent logic.
	string1a is the normalized  version of string1 */
	for (int i=0;i<3;i++)
	{if (lett11==aN[i] && lett12==aN[i])
	{ 	
	string1a=string1.substring(0,n1-2).concat(aaN[i]);
	n1=n1-1;
	break;
	} else
	if (i==2)
	{string1a =string1;
	}
	}

	for (int i=0;i<3;i++)/* if the last but one and the last but two of  the  1st word are a,e, or u
	 they will be replaced by A,I,and U*/
	{if (lett13==aN[i] && lett12==aN[i])
	{	
	string1a1=(((string1.substring(0,n1-3)).concat(aaN[i])).concat(string1.substring(n1-1)));
	break;
	} else
	if (i==2)
	{string1a1 =string1a;
	}
	}

	
	String string2a=new String();
	char lett21=string2.charAt(0);/*find first char of 2nd word*/
	char lett22=string2.charAt(1);/*find 2nd char of 2nd word*/
section3:/*If the first two letters of the second word are aa,ee,uu
	 they get converted to A,I,U as a process of Normalization
	 before application of subsequent logic.
	string2a is the normalized version of string2*/
	for (int i=0;i<3;i++)
	{if (lett21==aN[i] && lett22==aN[i])
	{
	string2a=aaN[i].concat(string2.substring(2,n2));
	
	break;
	} else  
	if (i==2)
	{string2a=string2;
	
	}
	}

	
	String string2b=new String();
	char ao[]={'a','o','R','R','~','~'};
	char iu[]={'i','u','u','U','G','J'};
	String zx[]={"z","x","Z","X","Q","q"};
section4:/*If the first 2  letters of the 2nd word are 'ai','ou','Ru','RU','~G','~J'
	they get converted to 'z','x','Z','X','Q','q' respectively.This avoids clash between sandhi 
	rules*/
	for (int  l=0;l<6;l++)
	{if (lett21==ao[l] && lett22==iu[l])
	{string2b=zx[l].concat(string2.substring(2,n2));
	 
	break;
	} else
	if (l==3)
	{string2b=string2a;
	 
	}
	}

	String string1b=new String();
section5:/*If the last 2  letters of the 1st word are 'ai','ou','Ru','RU','~G','~J'
	they get converted to 'z','x','Z','X','Q','q' respectively.This avoids clash between sandhi 
	rules*/
	for(int l=0;l<5;l++)
	{if (lett11==iu[l] && lett12==ao[l])
	{string1b=(string1a1.substring(0,n1-2)).concat(zx[l]);
	
	break;
	} else
	if (l==3)
	{string1b=string1a1;
	 
	}
	}

	
	int n3=string1b.length();
	int n4=string2b.length();	
	char lett11a=string1b.charAt(n3-1);
	char lett21a=string2b.charAt(0);
	
	
	String stringOut=new String();
	
	int flag0=0;
	String string3=new String();
	char vowels[]={'a','A','i','I','u','U','e','o','z','x','Z','X'};
section6:/*implements prakRutibhaavasandhi rules specific to vowels*/
	if (lett11a=='I' || lett11a=='U' || lett11a=='e')
	{   for (int i=0;i<12;i++)
	   { if (lett21a==vowels[i])
	     {	 try
		{System.out.println(('\n')+"if the first is a dvivacana(dual)"+('\n')+
	 	"enter y else any other letter");
		System.out.flush();
  		DataInputStream in=new DataInputStream(System.in);
		string3=in.readLine();
		string3=string3.trim();	
		System.out.println(('\n'));
		}
 		catch(IOException e)
		{System.out.println("I/O/error");
		System.exit(1);
		}
	      break;	
	     }
	   }
	}
		if (string3.equals("y") || string1b.equals("111amI") || string1b.equals("amI")
		|| string1b.equals("111he") || string1b.equals("111aho") || string1b.equals("111bho")
		|| string1b.equals("111u"))
		{stringOut=string1b.concat((" ").concat(string2b));
		
		System.out.println("The operative rule is : Final I,U,and e in dual forms, as also I in 'amI'"+('\n')+
		"remain unchanged when followed by initial vowels and all initial vowels "+('\n')+
		"remain unchanged after them."+('\n')+
		"Interjections of only one vowel and the final e and o of interjectional"+('\n')+
		"particles remain unchanged.");
		
		flag0=0;
		}
	        else flag0=1;

	
	int flag1=0;
	char aiu[]={'a','i','u','Z'};
	char AN[]={'A','I','U','X'};/*set up a character array for A,I,U*/
	String savarna[]={"A","I","U","RU"};
section7:/* implements savarnadirghasandhi rules*/
	if (flag0==1)
	{for (int i=0;i<4;i++)
	{if ((lett11a==aiu[i] || lett11a==AN[i])
		&& (lett21a==aiu[i] || lett21a==AN[i]))
	{
	stringOut=((string1b.substring(0,n3-1)).concat(savarna[i])).concat(string2b.substring(1,n4));
	
	System.out.println("The operative savarNadIrgha rule is:"+('\n')+
	"(a or A) +(a or A)=A"+('\n')+
	"(i or I)+ (i or I)=I"+('\n')+
	"(u or U)+ (u or U)=U"+('\n'));
	
	flag1=0;
	break;
	} else
	{flag1=1;
	}
	}
	
	}

	int flag2=0;
	int alt1=0;
	String stringOutAlt1= new String();
	char guna1[]={'a','A'};
	char guna2[]={'i','I','u','U','e','o','z','x','Z','X'};
	String guna3[]={"e","e","o","o","ai","ou","ai","ou","ar","ar"};	
section8:/*implements guna and vruddhi rules*/
	if (flag1==1)
	{  if (lett11a=='a' || lett11a=='A')
	   {for (int l=0;l<10;l++)
		if (lett21a==guna2[l])
		{stringOut=((string1b.substring(0,n3-1)).concat(guna3[l])).concat(string2b.substring(1,n4));
		  if (l<4 || l>7)
		  {System.out.println("The operative guNa rule is:"+('\n')+
		   "(a or A) + (i or I) = e "+('\n')+
		   "(a or A) + (u or U) = o "+('\n')+
		   "(a or A) + (Ru or RU) = ar "+('\n'));
		  }
		   else
		  {System.out.println("The operative vRuddhi rule is:"+('\n')+
		   "(a or A) + (e or ai) = ai"+('\n')+
		   "(a or A) + (o or ou) = ou"+('\n'));
		    if (l==8)
		    {System.out.println("It has a variant");
		     stringOutAlt1=string1b.substring(0,n3-1).concat("aRu").concat(string2b.substring(1));
		     alt1=1;
		    }
	 	  }		
		flag2=0;
		break;	
		} else
		{ flag2=1;
	        }

	   } else
	   {flag2=1;
           }
	}
		
		int flag3=0;		
		char yan1[]={'i','I','u','U','Z','X'};
		
		String yan3[]={"y","y","v","v","r","r"};
		int numb[]={8,9,10,11};
		String twin[]={"ai","ou","Ru","RU","~G","~J"};
section9:/*implements yaN sandhi rules*/	
		if (flag2==1)
	  loop1:{for (int l=0;l<6;l++)
		if (lett11a==yan1[l])
			{for (int m=0;m<12 ;m++)
			if (lett21a==vowels[m])
			 {for (int t=0;t<4  ;t++)
			  if (m==numb[t]) 
	                  {string2b=twin[t].concat(string2b.substring(1,n4));
			  } 			    				
			 stringOut=((string1b.substring(0,n3-1)).concat(yan3[l])).concat(string2b);
			 System.out.println("The operative yaN rule is :"+('\n')+
			 "i or I + any vowel other than i or I = y + that vowel"+('\n')+
			 "u or U + any vowel other than u or U = v + that vowel"+('\n')+
  			 "Ru or RU + any vowel other than Ru or RU = r + that vowel");
			
			 flag3=0;
			 break loop1;
			}			 
			}			 
			{flag3=1;
			}
		}	
		
		int flag4=0;
		if (flag3==1)
section10:/*implements pUrvarUpasandhi rules*/
		{if (((lett11a=='o') || (lett11a=='e')) && (lett21a=='a'))
		   {stringOut=string1b.concat(("&").concat(string2b.substring(1,n4)));
		   System.out.println("The operative pUrvarUpa rule is :"+('\n')+
		   "e + a = e"+('\n')+
		   "o + a = o"+('\n')+
		   "The initial a dropped is generally shown by the avagraha sign &.");	
		  
		   flag4=0;	
		   } else
		   {flag4=1;
		   }	
		}
		
		int flag5=0;	
			
		char ayav1[]={'e','o','z','x'};
		char ayav2[]={'a','A','i','I','u','U','e','o','z','x'};
		String ayav3[]={"ay","av","Ay","Av"};
		String ayavAlt[]={"a ","a ","A ","A "};
		

section11:
	/*implements ayav sandhi rules*/
		if (flag4==1)
	outer:	{for (int l=0;l<4;l++)
		  if (lett11a==ayav1[l])
		    {for (int m=0;m<10;m++)
		      if (lett21a==ayav2[m])
 			{stringOut=((string1b.substring(0,n3-1)).concat(ayav3[l])).concat(string2b);
			System.out.println("The operative (ayavAyAv)rule is:"+('\n')+
			"Final e and o  before any initial vowel other than a are changed to respectively ay and av, or both to a"+('\n')+
			"Final ai and ou  before any initial vowel are changed to respectively Ay and Av, or both to A");
			stringOutAlt1=((string1b.substring(0,n3-1)).concat(ayavAlt[l])).concat(string2b);
			flag5=0;
			alt1=1;
			break outer;
			} 
		    }else 			
		     flag5=1;
	        }		

		int flag6=0;
				
section12:/*implements anusvaara sandhi rules*/
		if (flag5==1)
		{if ((lett11a=='M') || (lett11a=='m'))
	  loop2:{for (int l=0;l<12;l++)
		{if (lett21a==vowels[l])
			{for (int m=0;m<4;m++)
			{if (l==numb[m])
			  {string2b=twin[m].concat(string2b.substring(1,n4));
			  }
			stringOut=(string1b.substring(0,n3-1).concat("m")).concat(string2b);
			System.out.println("The operative rule is :"+('\n')+
			"Final M or m followed by a vowel combines with that vowel.");
			flag6=0;
			break loop2;
			} 
			}else  flag6=1;
		}
		} else flag6=1;
		}
		

		int flag7=0;
	        char vyanjana[]={'k','K','g','G','c','C','j','J','T','D','t','d'};
	        String nasal[]={"~G","~G","~G","~G","~J","~J","~J","~J","N","N","n","n"};
section13:/*implements prakRutibhaavasandhi  rules specific to anusvaara*/
		if (flag6==1)
		{  if ((lett11a=='M') || (lett11a=='m'))
	           { for (int l=0;l<12;l++)
			   if (lett21a==vyanjana[l])
			   {System.out.println("The operative rule is :"+('\n')+
			    "Final M before consonants other than sibilants(UShma) and semi-vowels"+('\n')+
			    "is changed  to an anusvAra or to the nasal of the class to which the initial"+('\n')+
			    "consonant belongs.");
			    stringOut=(string1b.substring(0,n3-1).concat("m ")).concat(string2b);
			    stringOutAlt1=(string1b.substring(0,n3-1).concat(nasal[l]).concat(string2b));
			    alt1=1;
			    flag7=0;	
			    break;
			   } else
			     if (l==11)  
			     {stringOut=(string1b.substring(0,n3-1).concat("m ")).concat(string2b);
			     System.out.println("The operative rule is :"+('\n')+
			     "Final M is changed to anusvAra before sibilants and semi-vowels");
			     flag7=0;
			     }	
	           } else
		   {flag7=1;
	           }
		}
		

	int flag8=0;
section14:/*implements sandhi rules specific to the first word being "sah" or "eShah"*/
	if (flag7==1)
	{if (string1b.equals("111sah") || string1b.equals("eShah"))	
		
		  {if (lett21a=='a')
			{System.out.println("The operative (o~GAdeSa) rule is word-specific :"+('\n')+
			 "When the final visarga of 'sah' and 'eShah' is followed by a, the preceding and"+('\n')+
			 "the following a with the visarga in between are together  changed to o."+('\n')+
			 "The initial a dropped is normally indicated by avagraha(&) sign");
			 stringOut=(string1b.substring(0,n3-2).concat("o&")).concat(string2b.substring(1,n4));
			} else
			{System.out.println("The operative (visrgalopa) rule is word-specific :"+('\n')+
			 "The final visarga of 'sah' and 'eShah' is dropped before all consonants"+('\n')+
			 "and vowels except a."
                         );
			 stringOut=(string1b.substring(0,n3-1).concat(" ")).concat(string2b);
			}
		 
		 flag8=0;
		} else
		{flag8=1;
		}
	} 

	int flag9=0;
	int flag9a=0;
	char lett12a=string1b.charAt(n3-2);
section15:/*implements rules when the 1st word ends in a visarga preceded by 'a'and
the 2nd word starts with a vowel*/
	
	if (flag8==1)
	{    if (string1b.equals("punah") && (string2b.charAt(0)=='r' ||string2b.charAt(0)=='R'))
	     {flag9a=0;
	      System.out.println("The operative rule is :"+('\n')
	                +"The final h of punah which is an inflexed form of punar is dropped "+('\n')
	                +"before r and the preceding a lengthened to A"+('\n'));
		stringOut="punA".concat(" ").concat(string2b);
	     } else
	     { flag9a=1;
             }
        }
        if (flag9a==1)

	{if (lett11a=='h' && lett12a=='a')	
	 	{for (int l=0;l<12;l++)
			if (lett21a==vowels[l])
			{
			  if (l==0)/*  if the 1st letter of 2nd word is 'a'*/
			  {
                          System.out.println("The operative (o~GAdeSa) rule is :"+('\n')+
			 "When  final visarga is preceded by a and is followed by a, the preceding and"+('\n')+
			 "the following a with the visarga in between are together  changed to o."+('\n')+
			 "The initial a dropped is normally indicated by avagraha(&) sign.");
			  stringOut=(string1b.substring(0,n3-2).concat("o&")).concat(string2b.substring(1,n4));
			  flag9=0;
			  break;
			  } else
				{for (int t=0;t<4  ;t++)
			        if (l==numb[t]) 
	                        {string2b=twin[t].concat(string2b.substring(1,n4));
			        }
			        } 
			  {System.out.println("The operative (visargalopa) rule is :"+('\n')+
			   "Final visarga preceded by a and followed  by an vowel except a is dropped.");
			   stringOut=(string1b.substring(0,n3-1).concat(" ")).concat(string2b);
			   flag9=0;
			   break;
			  }
			        
			} else 
		        {flag9=1;
			 
		        }
		}else 
		{flag9=1;
		 
		}	
	} 
	

		int flag10=0;
		char lett13a=string1b.charAt(n3-3);
		char hash[]={'g','G','~','j','J','d','n','D','N','b','B','m','y','r','l','v','h'};
section16:/* when the last letter of the 1st word is visarga preceded by 'a' and the 2nd word starts with 'hash' letters*/
		if (flag9==1)
	        {
		  if (lett11a=='h' && lett12a=='a' && lett13a!='a')
		  {
		    
		    for (int l=0;l<17;l++)
		     if (lett21a==hash[l])
		     {
		       System.out.println("The operative  rule is :"+('\n')+
		       "When final visarga is preceded by a and followed by a soft consonant, the a "+('\n')+
		       "and the visarga are together changed to o.");
		       stringOut=(string1b.substring(0,n3-2).concat("o ")).concat(string2b);
		       flag10=0;
		       break;	
		     }else 
		     {flag10=1;
		     }
		 }
		 else 
		   {flag10=1;
		   }
	      } 

		int flag11=0;
		int numb1[]={8,9,10,11,14,17};
		char ash[]={'a','A','i','I','u','U','e','o','z','x','Z','X','g','G','Q','j','J','q','d','n','D','N','b','B','m','y','r','l','v','h'};
section17:/*Final visarga preceded by'A' before all 'ash'letters (soft consonants and vowels)*/	if (flag10==1)
		if (flag10==1)
                {
		  if ((lett11a=='h' && ((lett12a=='A') || (lett12a=='a'  && lett13a=='a') 
			|| (string1b.equals("bhoh")) || (string1b.equals("Boh")))))
		  {
		    for (int l=0;l<30;l++)
		      if (lett21a==ash[l])
		      { {for (int t=0;t<6  ;t++)
			  if (l==numb1[t]) 
	                  {string2b=twin[t].concat(string2b.substring(1,n4));
			  }
			  } 
			   
			System.out.println("The operative rule is :"+('\n')+
			"Final visarga preceded by A as well as the final visarga of 'Boh',"+('\n')+
			"is dropped before all soft consonants and vowels.");
			stringOut=(string1b.substring(0,n3-1).concat(" ")).concat(string2b);
			flag11=0;
			
			break;
		      }
		       else
		       {
        	       flag11=1;
		       } 	
		  } 
		  else
		  {
		  flag11=1;
		  }
		} 

	
	  int flag12=0;
section18:/*final visarga preceded by any vowel other than 'a' or 'A' */if (flag11==1)
	{  if (lett11a=='h')
	   {  for (int l=0;l<30;l++)
	        if (lett21a==ash[l] && l!=26)/*'r' is excluded as it is taken care of
	in section 22*/		  
		{	  {for (int t=0;t<4  ;t++)
			  if (l==numb[t]) 
	                  {string2b=twin[t].concat(string2b.substring(1,n4));
			  }
			  } 
		 System.out.println("The operative rule is :"+('\n')+
		 "Final visarga preceded by any vowel other than a or A is changed to"+('\n')+
		 "r before vowels and soft consonants.");
		 stringOut=(string1b.substring(0,n3-1).concat("r")).concat(string2b);
		 flag12=0;
		 break;
		} else
		{flag12=1;
		}
	   } else
	   {flag12=1;
	   }
	}

	int flag13=0;
	
	
	char khar1[]={'c','C','t','T','s'};
	String khar1Rep[]={"S","S","s","Sh","s"};
	char lett22a=string2b.charAt(1);
section19:/*if final visarga is followed by c,C,t,T,or s */
	
	if (flag12==1)
	{ alt1=0;
	  if (lett11a=='h')
	  {
	    for  (int l=0;l<5;l++)
	      if (lett21a==khar1[l])
	      { if (l==4)
		{ System.out.println("The operative rule is :"+('\n')+
		"Final visarga may be changed to S, Sh or s before S, Sh or s"+('\n')+
	        "respectively or remain unchanged");
		  alt1=1;
		  stringOut=(((string1b.substring(0,n3-1)).concat(khar1Rep[l])).concat(string2b));	
		  stringOutAlt1=((string1b.concat(" ")).concat(string2b));
		} else
		if ((l==2)&&(lett22a=='s'))
		{ System.out.println("The operative rule is :"+('\n')+
		"Final visarga remains unchanged  when followed by ts.");
		  stringOut=((string1b.concat(" ")).concat(string2b));
		} else  	

		{ System.out.println("The operative rule is :"+('\n')+
		"Final visarga is changed to S  before c or C, to Sh before T or Th, to s before t or th.");
		stringOut=(((string1b.substring(0,n3-1)).concat(khar1Rep[l])).concat(string2b));
		}
	        					
	       flag13=0;
	       break;
	      } else
		{flag13=1;
		}
	  } else
	    {flag13=1;
	    }
	}	

	int flag14=0;
	
section20:/* if final visarga is followed by "Sh" or "S"*/
	if (flag13==1) 
	{if (lett11a=='h' && lett21a=='S') 
	  {
	  System.out.println("The operative rule is :"+('\n')+
	  "Final visarga may be changed to S, Sh or s before S, Sh or s"+('\n')+
	  "respectively or remain unchanged");
	  stringOutAlt1=((string1b.concat(" ")).concat(string2b));
	  alt1=1;
		if (lett22a=='h')
		{
	        stringOut=(((string1b.substring(0,n3-1)).concat("Sh")).concat(string2b));
	        } else
		{
		stringOut=(((string1b.substring(0,n3-1)).concat("S")).concat(string2b));
		}
	  } else 
	  {flag14=1;
	  }
	}

	int flag15=0;
	char kay[]={'k','K','p','P'};
section21:/* when final visarga is followed by k,K,p,orP*/
	if (flag14==1)
	{  if (lett11a=='h')   
	   { for (int l=0;l<4;l++)
		if (lett21a==kay[l])
		{System.out.println("The operative rule is :"+('\n')+
		"Final visarga  remains  unchanged before k, K, p, or P.");
		 stringOut=((string1b.concat(" ")).concat(string2b));
		 flag15=0;
		 break;
		} else
		{flag15=1;
		}
	   } else 
	   {flag15=1;
	   }
	}

	int flag16=0;
	String vowelsRep[]={"A","A","I","I","U","U","e","o","z","x","X","X"};
section22: if (flag15==1)
	{
	 if ((lett11a=='h' || lett11a=='r') && (lett21a=='r')) 
	 {  
	  for (int l=0;l<12;l++)
	  if (lett12a==vowels[l])
	  {
	  System.out.println("The operative rule is :"+('\n')+
	  "Final h followed by r and preceded by repha is dropped and the preceding vowel if short is made long.");
	  
	  stringOut=(((string1b.substring(0,n3-2)).concat(vowelsRep[l])).concat(" ").concat(string2b)); 			
  	  flag16=0;
	  break;
	  } else
	  {flag16=1;
	  }	 		
	 } else 
	 {flag16=1;
	 }
        }
	

	int flag17=0;
	char gam[]={'n','N','Q'};
	String avart[]={"nn","NN","~G~G"};
section23:/*when final 'n','N' or '~G' preceded by a short vowel is followed by any vowel*/
	if (flag16==1)
loopOut:  {for (int l=0;l<3;l++)
	 if (lett11a==gam[l] && (lett12a=='a' || lett12a=='u' || lett12a=='i'))
	 { 
loopIn:	  for (int i=0;i<12;i++)
          if (lett21a==vowels[i])
	  {for (int t=0;t<4;t++)
			  {if (i==numb[t]) 
	                   {string2b=twin[t].concat(string2b.substring(1,n4));
			   }
			  }
	   System.out.println("The operative rule is :"+('\n')+
	   "Final n, ~G,  N when prceded by a short vowel and  followed by any vowel"+('\n')+
	   "is doubled."); 
	   stringOut=string1b.substring(0,n3-1).concat(avart[l]).concat(string2b);
	   flag17=0;
	   break loopOut;
	   } else 
	   {flag17=1;	    
	   } 
        } else
	{flag17=1;
	} 
     }

	int flag18=0;
	char vyanj1[]={'c','C','T','t','j','J','S','D','l'};
	String vyanjRep[]={"mS","mS","mSh","ms","~J","~J","~J","N","~Ml"};

section24:/*when final 'n' is followed  by vyanj1 letters*/
	if (flag17==1)
	{   if (lett11a=='n') 
	    { for (int l=0;l<9;l++)
		
	        if (lett21a==vyanj1[l])
		{  if (l==6)
		   { if (lett21a=='S' && lett22a!='h')/*'Sh' is excluded*/
		     {System.out.println("The operative rule is :"+('\n')+
		     "Final n before S is changed to ~J or optionally nS changed to ~JC.");
			stringOut=string1b.substring(0,n3-1).concat(vyanjRep[l]).concat(string2b);
			stringOutAlt1=string1b.substring(0,n3-1).concat("~JC").concat(string2b.substring(1));
			alt1=1;
			flag18=0;
			break;
		     } 
		   } else
		   {System.out.println("The operative rule is :"+('\n')+
		   "Final n before c or C is changed to anusvaara and S"+('\n')+
		   "Final n before T or Th is changed to anusvaara and Sh"+('\n')+
		   "Final n before t or th is changed to anusvaara and s"+('\n')+
		   "Final n before j or J is changed to ~J"+('\n')+
		   "Final n before D or Dh is changed to N"+('\n')+
		   "Final n before l is changed to nasal ~Ml");
		    stringOut=string1b.substring(0,n3-1).concat(vyanjRep[l]).concat(string2b);		
		    flag18=0;
		    break;
		   }
		} else 
		{ flag18=1;
		}
	   } else
	   { flag18=1;
	   }
	}
	

	int flag19=0;
	char vyanj2[]={'c','C','S','j','J','T','D','l'};
	String vyanjAdes[]={"c","c","c","j","j","T","D","l"};

section25:/*if final 't' is followed by vyanj2 letters */
	if (flag18==1)
	{ if (lett11a=='t')
	  { for (int l=0;l<8;l++)
	    if (lett21a==vyanj2[l])
	    { if (l==2)
	      { if (lett22a!='h')
                {System.out.println("The operative rule is :"+('\n')+
		"Final t before S is changed to c or optionally tS may be changed to cC.");
		 stringOut=string1b.substring(0,n3-1).concat(vyanjAdes[l]).concat(string2b);			 
		 stringOutAlt1=string1b.substring(0,n3-1).concat("cC").concat(string2b.substring(1));
		 alt1=1;
		 flag19=0;
		 break;
		}  else
		{flag19=1;
		}
	      } else
	      {System.out.println("The operative rule is :"+('\n')+
	      "Final t before c or C is changed to c, before j or J to j,"+('\n')+
	      "before T or Th to T, before D or Dh to D, before l to l.");
		 stringOut=string1b.substring(0,n3-1).concat(vyanjAdes[l]).concat(string2b);			 
		 flag19=0;
		 break;
	      }
	    } else
	    { flag19=1;
	    }
	  } else
	  { flag19=1;
	  }
	}		

	int flag20=0;
	
section26:/*if final 't' is followed by ash(soft consonants and
	 vowels) letters other than vyanj2 letters */
	if (flag19==1)
	{ if (lett11a=='t')
	  { for (int l=0;l<30;l++)
	      if (lett21a==ash[l])
	      { for (int t=0;t<6;t++)
		{ if (l==numb1[t]) 
	          {string2b=twin[t].concat(string2b.substring(1,n4));
		  }
		}
		if (l==14 || l==17 || l==19 || l==21 || l==24 || l==29)
		{alt1=1;
		 System.out.println("The operative rule is :"+('\n')+
		 "Final t before all nasals is usually changed to n and optionally to d."+('\n')+
		 "Final t before h is changed to d or optionally th is changed to ddh.");
		 if (l==29)/*lett21a=h*/
		 {stringOutAlt1=string1b.substring(0,n3-1).concat("dd").concat(string2b);
	         } else/*lett21a is a nasal*/
                 { stringOutAlt1=string1b.substring(0,n3-1).concat("n").concat(string2b);	
                 }
		} else 	  
	        {System.out.println("The operative(jastva) rule is :"+('\n')+
	        "Final t before g, G, d, dh, b, B and all vowels is changed to d.");
		}
	       stringOut=string1b.substring(0,n3-1).concat("d").concat(string2b);
	       flag20=0;
	       break;
	      } else
	      { flag20=1;
   	      }
	  } else
	  { flag20=1;
	  }
	}

	int flag21=0;
	char ktp[]={'k','T','p'};
	String ktpRep[]={"g","D","b"};
	String ktpRepAlt[]={"~G","N","m"};
	String ktpRepAl2[]={"gg","DD","bb"};
section27:/*Final 'k','T','p' are changed to 'g','D', and 'b' respectively
            before soft consonants and vowels.e.g. 'ash' letters */
	  if (flag20==1)			
outerFor: {  for (int i=0;i<3;i++)
	   if (lett11a==ktp[i])
innerFor:   {  for (int j=0;j<30;j++)
	        if (lett21a==ash[j])
		{System.out.println("The operative rule is :"+('\n')+
		"Final k, T and p are changed to g,D and b respectively before"+('\n')+
		"soft consonants and vowels.");
		 for (int t=0;t<6;t++)
		  {   if (j==numb1[t]) 
	            {string2b=twin[t].concat(string2b.substring(1,n4));
		    }
		  }
		 stringOut=string1b.substring(0,n3-1).concat(ktpRep[i]).concat(string2b);
		   if (j==14 || j==17 || j==19 || j==21  || j==24 || j==29)
		   {System.out.println("Before nasals they are optionally changed to"+('\n')+
		   "nasals of their class.Before h they along with h are optionally changed to"+('\n')+
		   "gG, DDh, bB respectively");
		    alt1=1;
		      if (j==29)/*lett21a=h*/
		      {stringOutAlt1=string1b.substring(0,n3-1).concat(ktpRepAl2[i]).concat(string2b);
		      } else/*lett21a= a nasal*/	
		      {stringOutAlt1=string1b.substring(0,n3-1).concat(ktpRepAlt[i]).concat(string2b);
		      }
		   }	  
                 flag21=0;
		 break outerFor;
		} else
		{ flag21=1;
		}
	    } else
	    { flag21=1;
	    }
	 }

	int flag22=0;
	char longVowel[]={'A','I','U','X'};
	String aiuRep[]={"ac","ic","uc","Ruc"};
	String longVowelRep[]={"Ac","Ic","Uc","RUc"};
section28:/* Initial 'C' or 'ch' is changed to 'cC'after short vowels */
	if (flag21==1)
	{ for (int l=0;l<4;l++)/* l<4 ensures that only a,iand u are covered among aiu[] array*/
	  {  if (lett11a==aiu[l])
	    {  if (lett21a=='C'||(lett21a=='c' && lett22a=='h'))
	       {System.out.println("The operative rule is :"+('\n')+
	       "Initial C after shortvowels is changed to cC");
	        stringOut=string1b.substring(0,n3-1).concat(aiuRep[l]).concat(string2b);
		flag22=0;
		break;
	       }
	    } else
	    { flag22=1;
	    }  
	  }
	}

	int flag23=0;
sectio29:/*Initial 'C' after short vowels or the particles 'A' and 'mA'is changed to 'cC' */
	if (flag22==1)
	  { if (lett21a=='C' ||(lett21a=='c' && lett22a=='h'))
	    { if (string1b.equals("111A") || string1b.equals("111mA"))
	      {System.out.println("The operative rule is :"+('\n')+
	      "Initial C after the particles A and mA is changed to cC, although it is optional"+('\n')+
	      "after long  vowels");
		    System.out.println("n3="+n3);
	            stringOut=string1b.substring(0,n3-1).concat("Ac").concat(string2b);
		    System.out.println(stringOut);	
		    flag23=0;	
	      } else
              { for (int k=0;k<4;k++)
		    {  if(lett11a==longVowel[k])/* The change is optional after long vowels*/
		       { System.out.println("The operative rule is :"+('\n')+
		       "Initial C after long vowels is optionally changed to cC.");
   			 alt1=1;
			 stringOutAlt1=string1b.concat(" ").concat(string2b);
			 stringOut=string1b.substring(0,n3-1).concat(longVowelRep[k]).concat(string2b);	
			 flag23=0;
			 break;
		       } else
		       { flag23=1;
		       }		
		    }
              }
          } else
       	  { flag23=1;
	  }
	    		
	} 
 	
	int n5=0;
	int n6=0;
	
	
	int flag24=0;

section30:
	if (flag23==1)
	{ if (lett11a=='n' && lett21a=='s')
	  {stringOut=string1b.concat(string2b);
	   n5=stringOut.length();
	   stringOutAlt1=string1b.concat("t").concat(string2b);
	   System.out.println("The operative rule is :"+('\n')+
	   "If final n is followed by initial s, t may be inserted between them optionally.");
	   alt1=1;
	   flag24=0;
	  } else 
	  { flag24=1;
          }
	} 
 		

	
	int flag25=0;
section31:
	if (flag24==1)
	{ 
	 
	 
	 for (int l=0;l<12;l++)
	   if (lett11a==vowels[l])
	   {for (int t=0;t<4;t++)
			  if (l==numb[t]) 
	                  {string1b=string1b.substring(0,n3-1).concat(twin[t]);
			  }
	    stringOut=string1b.concat(" ").concat(string2b);
	    System.out.println("The words remain as they are (prakRutibhaava).");
	    flag25=0;
	    break;
	   } else
	   { flag25=1;
	   }
	}

	int flag26=0;
	char nG[]={'N','Q'};
	char shar[]={'S','s'};
	
section32:
	  if (flag25==1)
forOuter: {for (int l=0;l<2;l++)
	    if (lett11a==nG[l])
forInner:   {for (int m=0;m<2;m++)
	      if (lett21a==shar[m])
	      {System.out.println("The operative rule is :"+('\n')+
	       "If final N or ~G is followed by S, Sh or s, T or k"+('\n')+
	       "may respectively be inserted after them.");
	         if (l==0)
	         {stringOut=string1b.concat(string2b);
		  stringOutAlt1=string1b.concat("T").concat(string2b);
		  flag26=0;
		  alt1=1;
	          break forOuter;	
		 } else
		 {stringOut=string1b.substring(0,n3-1).concat("~G").concat(string2b);
                  stringOutAlt1=string1b.substring(0,n3-1).concat("~Gk").concat(string2b);
	          flag26=0;
		  alt1=1;
		  break forOuter;
	         }
              } else
	      { flag26=1;
	      }
	    } else
	    { flag26=1;
	    }
	  }

	int flag27=0;
section33:
	if (flag26==1)
	{ if (lett11a=='T' &&  lett21a=='s')
	  { System.out.println("The operative rule is :"+('\n')+
	  "If final T is followed by s, t may be optionally inserted between them.");
	    stringOut=string1b.concat(string2b); 
	    stringOutAlt1=string1b.concat("t").concat(string2b);
	    alt1=1;
	    flag27=0;
	  } else
	  { flag27=1;
	  }
        } 
sectionL:/* covers cases where none of the above rules apply and the two words are just joined without any modification")*/
	if (flag27==1)
	{  if (stuff==1)
	   {string1b=string1b.substring(3,n3);
	   }
	   if (pad==1)
	   {if (n2<3)
	   {string2b=" ";
	   } else 
	   {string2b=string2b.substring(0,n2-2);
	   }
	   } 
	 stringOut=string1b.concat(string2b);
	 System.out.println("The two words are joined without any modification."); 
	 
	 System.out.println(('\n')+string1b+"+"+string2b+"="+stringOut);
	} 
	else/*provides print statement for all other cases except the last above*/
	{
	  if (stuff==1)  
	  {string1a=string1a.substring(3,n1);
	   n5=stringOut.length();
           stringOut=stringOut.substring(3,n5);
	    if (alt1==1)
	    {n6=stringOutAlt1.length();
	     stringOutAlt1=stringOutAlt1.substring(3,n6); 	
	    }
	  }
	  
	  if (pad==1)
	  {if (n2<3)
	   {string2a=" ";
	   } else 
	   {string2a=string2a.substring(0,n2-2);
	   } 
	   int n7=stringOut.length();
	   stringOut=stringOut.substring(0,n7-2);
	    if (alt1==1)
	    {int n8=stringOutAlt1.length();
	     stringOutAlt1=stringOutAlt1.substring(0,n8-2);
	    }
          } 	
	System.out.println(('\n')+string1a+"+"+string2a+"="+stringOut+('\n'));
	  if (alt1==1)
	  {
	  System.out.println(('\n')+string1a+"+"+string2a+"="+stringOutAlt1+('\n'));
	  }
	}
	
	
}}}}



	
