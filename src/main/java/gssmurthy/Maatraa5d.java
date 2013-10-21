package gssmurthy;

import java.io.*;
class Maatraa5d
{/*1*//*When a quarter of a Sanskrit stanza is input in Roman script as per "baraha" transliteration scheme, this program scans the input, identifies the vRutta (metre) and outputs the name of the vRutta*/
 public static void main(String args[])
	{/*2*/String string1=new String();/*create a string object */
	      String stringOut=new String();
	System.out.println("    When a quarter of a Sanskrit stanza is input in Roman script"+('\n')+
      "    as per 'baraha' transliteration scheme,"+('\n')+
      "    this program, called MAATRAA, scans the input, identifies the vRutta (metre) and outputs the name of the vRutta"+('\n')+
      "    The baraha4.0 transliteration scheme, as per devanaagaree alphabetical order is, as follows"+('\n')+
	"    svara: a, aa or A, i, ee or I, u, uu or U, Ru, RU, lRu, e, ai, o, ou"+('\n')+
	"    anusvaara: m, visarga: h"+('\n')+
	"    vya~Jjana: ka varga: k, kh or K, g, gh or G, ~G"+('\n')+
	"           ca varga: c, ch or C, j, jh or J, ~J"+('\n')+
	"           Ta varga: T, Th, D, Dh, N"+('\n')+
	"           ta varga: t, th, d, dh, n"+('\n')+
	"           pa varga: p, ph or P, b, bh or B, M"+('\n')+
	"           antasTha: y, r, l,v"+('\n')+
	"           uuShmaa: S, Sh, s, h" +('\n')+
	"           anunaasika: ~M"+('\n')+
	"    (NOTE:  G.S.S.MURTHY HAS THE COPYRIGHT FOR THIS PROGRAM :"+('\n')+
      "            E-mail:murthygss@gmail.com)"+('\n'));
	for (int w=0; ;w++)
section1:{try/*i/o interaction through monitor*/
	{/*4*/System.out.println("Pl enter a quarter of a Sanskrit stanza in Roman script as per 'baraha'");
       System.out.println("transliteration scheme given above. If you want to quit the program enter QUIT ."+('\n'));
	System.out.flush();
  	DataInputStream in=new DataInputStream(System.in);
	string1=in.readLine();/* Input is string1*/
	if (string1.equals("QUIT"))
	{break;
	}
	}/*-4*/
 	catch(IOException e)
	{/*5*/System.out.println("I/O/error");
	System.exit(1);
	}/*-5*/
String svara1[]={"a","e","u","a","o","R","R","k","g","~","c","j","~","t","d","p","b","S","w","q"};
String svara2[]={"a","e","u","i","u","u","U","h","h","G","h","h","J","h","h","h","h","h","h","h"};
String svarep[]={"A","I","U","E","O","Z","X","K","G","q","C","J","Q","T","D","P","B","x","W","Q"};
	int pad=0;
	int n1=string1.length();/* compute length of string1*/
	if  (n1<3)
	{/*5.1*/string1=string1.concat("111");
	 pad=1;
	 n1=n1+3;
	}/*-5.1*/
	int n4=n1;
	int space[]=new int[n4];
	int count1=0;
	String varNa[]=new String[n1];
	String step[]=new String[n1+1];
	for (int i=0;i<n1;i++)/*creates an array(varNa[]) of characters thet form the input.*/
	{/*6*/varNa[i]=string1.substring(i,i+1);
	   if (varNa[i].equals(" "))
	   {
	    space[count1]=i;
	    count1++;
	   }
	}/*-6*/
	n4=count1;
	
	StringBuffer str=new StringBuffer(0);
	for (int n=0;n<n1;n++)/* removes all spaces in the input*/
	if (string1.charAt(n)!=' ')
	{str=str.append(varNa[n]);
	 
	}
	string1=str.toString();	
	
section2:/*All 2-letter representations are replaced by single letter representation internally in a unique manner*/
	string1=string1.replace('T','q');/*T of baraha is replaced by q internally as T has been used for th in this program.*/
	string1=string1.replace('D','w');/*D of baraha is replaced by w internally as D has been used for dh in this  program,*/ 
	step[0]=string1;
	int count=0;
	int n2=0;
	int n3=0;
	for (int i=0;i<20;i++)
	if (varNa[0].equals(svara1[i]) && varNa[1].equals(svara2[i]))
	{/*7*/step[1]=svarep[i].concat(step[0].substring(2));
	 step[1]=step[1].concat("f");
	 
	 count=1;
	 break;
        }/*-7*/else
	{/*8*/step[1]=string1;
	}/*-8*/
	n2=n1;
	for (int j=1;j<n2-1;j++)
	{/*9*/	
	 n2=step[j].length();
	for (int k=0;k<n2;k++)
	{/*10*/varNa[k]=step[j].substring(k,k+1);
	
	}/*-10*/
	 for (int i=0;i<20;i++)
	if (varNa[j].equals(svara1[i]) && varNa[j+1].equals(svara2[i]))
	{/*11*/step[j+1]=step[j].substring(0,j).concat(svarep[i]).concat(step[j].substring(j+2));
	 
	 step[j+1]=step[j+1].concat("f");
	 count=count+1;
	 break;
	}/*-11*/ else
	{/*12*/step[j+1]=step[j];
	}/*-12*/
	
	
	string1=step[j+1].substring(0,n2-count);
	n3=string1.length();
	if (pad==1)
	string1=string1.substring(0,n3-3);
	}/*-9*/
	
	n3=string1.length();
	
	
	String svara[]={"a","i","u","Z","A","I","U","e","o","E","O","X"};
	int letCount=0;
	int m=0;
	int maatraa[]=new int[n3+3];
section3:/*implements the algorithm for determining the maatraa of each akShara.*/
loop1:	for (m=0;m<n3;m++)
	{/*13*/varNa[m]=string1.substring(m,m+1);
	
loop2:	      {for (int j=0;j<12;j++)
              	if (varNa[m].equals(svara[j]))
		{     if  (j<4)
		     {maatraa[m]=1;
		    
	      	     } else
		     {maatraa[m]=2;
		      }
		break;
		} else
		{maatraa[m]=0;
		}
	      }
	}/*-13*/ 
		maatraa[n3+1]=0;
		maatraa[n3+2]=0;
		int akShara=0;
		int sum=0;
		int q=0;
		int count2[]=new int[15];
		int pause[][]=new int[6][15];
		int nonZero[]=new int[n3]; 
	for (int j=0;j<n3+1;j++)
	{if (maatraa[j]==1 && maatraa[j+2]==0)
	  {maatraa[j]=2;
	  }
	 if (maatraa[j]!=0)
	{akShara++;
	 nonZero[akShara]=maatraa[j];
	 sum=sum+maatraa[j];
	 for ( q=2;q<10;q++)
	 if (sum%q==0) 
         {count2[q]++;
	  
	 }
	}
	}
	StringBuffer str1=new StringBuffer(0);
	String str2=new String();
	String scan[]=new  String[akShara+1];
	for (int n=1;n<akShara+1;n++)/* converts the result of scan to a string*/
	
	{scan[n]=Integer.toString(nonZero[n]);
	 str1=str1.append(scan[n]);
	 
	 str2=str1.toString();
	}
	StringBuffer str3=new  StringBuffer(0);
	for (int p=0;(p-1)<akShara/3;p++)
	{  
	   str3=str1.insert(4*p,"-");
	   
	}
	str3.setCharAt(0,' ');
	
	System.out.println('\n'+"Result of scan is:"+str3);
	System.out.println("Total maatraas="+sum+",Total akSharas="+akShara);
	String  vRutta[]={"11121112","12121212","21122112","111111222","112121212",
      "211222112","1111211112","2222111122","21121121122","22221111112","2112221122","22122112122","12122112122","22222122122","21122111122",
	"21211121212","121221121212","221221121212","111211211212","111122111122","222222122122222222122122",
	"111121121122","122122122122","212212212212","111211121212","221122221122",
	"221211121212","2221111212122","1121211121212","11111121111112","22121112112122",
	"111111222122122","12222211111221112","22221111122122122","12111212111212212","2221121211122212212",
	"11221121211122212212","222212211111122122122","112112121211221121212","1111112121221111211212122","11111121212111121121212",
	"11111222221211212","21211212112121122","11111112211111122","1111111121111111122","11112121112112112",
	"11211212122","112211212122"};
	String vRuttaName[]={"gajagatih","pramaaNikaa","maaNavakaM","bhujagaSiSubhRutaa","bhujamgasamgataa",
      "maNimadhyaM","tvaritagatih","mattaa","dodhakaM","bhramaravilasitaM","campakamaalaa","indravajraa","upendravajraa","Saalinee","stree",
        "rathoddhataa","vamSastha","indravamSaa","drutavilambitam","kusumavicitraa","vaiSvadevee",
	"taamarasam","bhuja~Ggaprayaatam","sragviNee","priyamvadaa","maNimaalaa",
	"lalitaa","praharShiNee","ma~JjubhaaShiNee","praharaNakalikaa","vasantatilakaa",
	"maalinee","SikhariNee","mandaakraantaa","pRuthvee","SaarduulavikreeDitam",
	"mattebhavikreeDitam","sragdharaa","lalitaa","puShpitaagraa","vaalmeeki32",
	"hariNee","jayadeva3","jayadeva5puurvaardha","jayadeva5uttaraardha","kuTaka",
	"deSikadayaapUrvaardha","deSikadayaa_uttaraardha"};
	int flag1=0;
	int flag2=0;
section4:/*checks if the input vRutta is anuShTup*/
        if (akShara==8) 
        {   if (nonZero[5]==1 && nonZero[6]==1 && nonZero[7]==2)
            { flag1=1;
              flag2=1;
            }
	    if (nonZero[5]==1 && nonZero[6]==2 && nonZero[7]==1)
            { flag1=1;
              flag2=1;
            }  	
            if (flag1==0) 
              {if(((nonZero[2]==1 && nonZero[3]==2 && nonZero[4]==2 &&
                   nonZero[5]==1 && nonZero[6]==2 && nonZero[7]==2 )||
                  (nonZero[2]==2 && nonZero[3]==2 && nonZero[4]==2 &&
                   nonZero[5]==1 && nonZero[6]==2 && nonZero[7]==2 )||
                  (nonZero[2]==2 && nonZero[3]==2 && nonZero[4]==1 &&
                   nonZero[5]==1 && nonZero[6]==2 && nonZero[7]==2 )||
                  (nonZero[2]==2 && nonZero[3]==1 && nonZero[4]==2 &&
                   nonZero[5]==1 && nonZero[6]==2 && nonZero[7]==2 )||
                  (nonZero[2]==1 && nonZero[3]==2 && nonZero[4]==1 &&
                   nonZero[5]==1 && nonZero[6]==2 && nonZero[7]==2 )||
                  (nonZero[2]==2 && nonZero[3]==1 && nonZero[4]==1 &&
                   nonZero[5]==1 && nonZero[6]==2 && nonZero[7]==2 )||	
                  (nonZero[2]==2 && nonZero[3]==1 && nonZero[4]==2 &&
                   nonZero[5]==2 && nonZero[6]==2 && nonZero[7]==2 )||
                  (nonZero[2]==1 && nonZero[3]==2 && nonZero[4]==2 &&
                   nonZero[5]==2 && nonZero[6]==1 && nonZero[7]==2 )||
                  (nonZero[2]==2 && nonZero[3]==2 && nonZero[4]==2 &&
                   nonZero[5]==2 && nonZero[6]==1 && nonZero[7]==2 )||
                  (nonZero[2]==2 && nonZero[3]==1 && nonZero[4]==2 &&
                   nonZero[5]==2 && nonZero[6]==1 && nonZero[7]==2 )||
                  (nonZero[2]==1 && nonZero[3]==2 && nonZero[4]==2 &&
                   nonZero[5]==2 && nonZero[6]==1 && nonZero[7]==1 )||
                  (nonZero[2]==2 && nonZero[3]==2 && nonZero[4]==2 &&
                   nonZero[5]==2 && nonZero[6]==1 && nonZero[7]==1 )||
                  (nonZero[2]==2 && nonZero[3]==1 && nonZero[4]==2 &&
                   nonZero[5]==2 && nonZero[6]==1 && nonZero[7]==1 )||
                  (nonZero[2]==1 && nonZero[3]==2 && nonZero[4]==2 &&
                   nonZero[5]==1 && nonZero[6]==1 && nonZero[7]==1 )||
                  (nonZero[2]==2 && nonZero[3]==2 && nonZero[4]==2 &&
                   nonZero[5]==1 && nonZero[6]==1 && nonZero[7]==1 )||
                  (nonZero[2]==2 && nonZero[3]==1 && nonZero[4]==2 &&
                   nonZero[5]==1 && nonZero[6]==1 && nonZero[7]==1 )))
           {System.out.println("The vRutta is odd paada of anuShTup"+('\n'));
            flag1=1;
	    flag2=0;
	   } else
	   {flag2=1;
	   }}
	} else
	{flag2=1;
	}
     if (flag1==1 && flag2==1 )
        {if((nonZero[5]==1 && nonZero[6]==2 && nonZero[7]==1) &&
            ((nonZero[2]==2 && nonZero[3]==2 && nonZero[4]==2 )||
             (nonZero[2]==2 && nonZero[3]==2 && nonZero[4]==1 )||
             (nonZero[2]==1 && nonZero[3]==2 && nonZero[4]==1 )||
             (nonZero[2]==2 && nonZero[3]==1 && nonZero[4]==1 )||
             (nonZero[2]==1 && nonZero[3]==2 && nonZero[4]==2 )))
           {System.out.println("The vRutta is even paada of anuShTup"+('\n'));
	    flag1=1;
	    flag2=0;
	   }
       } 
	int flag3=0;
section5:/*checks for other sama vRuttas*/
	
	
	if (flag2==1)
	{for (int k=0;k<vRutta.length;k++)
	
	if (str2.equals(vRutta[k]))
	{System.out.println("The vRutta is "+vRuttaName[k]+('\n'));
	 flag2=0;
	 flag3=0;
	 break;
	} else
	{flag3=1;
	}
	}
	int flag4=0;
	if (flag3==1)
	{ if (sum==30)
	  {System.out.println("It is puurvaardha of aaryaa."+('\n'));
	   flag4=0;
	  } else
	  if (sum==27)
	  {System.out.println("It is uttaraardha of aaryaa."+('\n'));
	   flag4=0; 
	  } else
	  {flag4=1;
	  }
	}
	if (flag4==1)
	{System.out.println("I am not aware of this vRutta; perhaps it is not a vRutta."+('\n'));
	 
	}
	

        
	
	
}/*-2*/
}}

