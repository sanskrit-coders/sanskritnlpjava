package com.sktutilities.junit;

import com.sktutilities.chanda.MetricAnalyzer;
import com.sktutilities.transliteration.DvnToSLP;
import com.sktutilities.transliteration.IASTToSLP;
import com.sktutilities.transliteration.SLPToDevanagari;
import com.sktutilities.util.EncodingUtil;
import com.sktutilities.util.Log;

public class SandhiTester
{

    public void test1()
    {
        // TODO Auto-generated method stub
        String testString = "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n"

        + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n" + "aham Adir hI BUtAnAm mattaH sarvam eva pravartate na me viduH suragaRAH na maharzayaH rAmeRa bAReRa hato vAli tasya aham na praRaSyAmi sa ca me na praRaSyati\n";
        System.gc();
        long mem0 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long time1 = System.currentTimeMillis();
        // new SLPToDevanagari().transformOld(testString);
        long time2 = System.currentTimeMillis();
        long totalTime = time2 - time1;
        long mem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        Log.logInfo("Total Time: [" + time2 + " - " + time1 + "]" + totalTime);
        Log.logInfo("Total Mem Usage: [" + mem1 + " - " + mem0 + "]" + (mem1 - mem0));

        // System.gc();
        long mem3 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long time3 = System.currentTimeMillis();
        new SLPToDevanagari().transform(testString);
        long time4 = System.currentTimeMillis();
        long totalTime2 = time4 - time3;
        long mem4 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        Log.logInfo("Total Time: [" + time4 + " - " + time3 + "]" + totalTime2);
        Log.logInfo("Total Mem Usage: [" + mem4 + " - " + mem3 + "]" + (mem4 - mem3));
        
        

    }

    public void test2()
    {
        DvnToSLP x = new DvnToSLP();
    }

    public void test3()
    {
        String s1 = "";
        s1 = "mUQa jahIhi DanAgama tfzRAm kuru sadbudDIm manasi vitfzRAm yallaBase nija karmopAttam vittam tena vinodaya cittam";
        //s1 = "yasyAH praTame pAde dvAdaSamAtrAstathA tu tftIye'pi "
        MetricAnalyzer.analyze(s1);
    }
    
    public void test4()
    {
        //String s1 = "rāma nāma aṁkita ati suṁdara rāmaḥ paṭha paḍha patha";
        String s1 = "yogaścittavṛttinirodhaḥ rAma rIma rUma rAima rauma. sa~njanayan sa~Nkalpa raNam Ta Tha Da Dha yaralava";
        String s2 = "rāma rīma rūma rāima rauma. saṇjanayan saṅkalpa raṇam ṭa ṭha ḍa ḍha yaralava";
        String inSLP = EncodingUtil.convertIASTToSLP(s2);
        Log.logInfo("\nIAST To SLP->\n");
        Log.logInfo("Original: " + s1 + "\n");
        Log.logInfo(inSLP  + "\n");
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        new SandhiTester().test4();
    }

}
