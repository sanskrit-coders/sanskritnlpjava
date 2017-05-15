package com.sktutilities.chanda;

import java.util.ArrayList;

import com.sktutilities.util.Log;
import com.sktutilities.util.MetricUtil;
import com.sktutilities.util.VowelUtil;

public class MetricAnalyzer
{

    public static String analyze(String meter)
    {
        // must be SLP
        String str = "";
        char lastEntry = ' ';
        ArrayList<String> lengthList = new ArrayList<String>();

        char meterAsCharArray[] = meter.toCharArray();
        int size = meterAsCharArray.length;
        for (int i = 0; i < size; i++)
        {
            char varna = meterAsCharArray[i];

            if (VowelUtil.isVowel(varna))
            {
                if (MetricUtil.isLaghu(varna))
                {
                    lengthList.add( "L");
                }

                else
                    lengthList.add( "G");
            }

            else
            {
                // peeking a single char for visrga, anusvara
                if ((varna == 'H' || varna == 'M') && MetricUtil.isLaghu(lastEntry))
                {
                    lengthList.set(lengthList.size() - 1, "G");
                }

                // peeking a char for sanyuktakshara or anusvara [plain
                // halanta-yukta makaara]
                else if (VowelUtil.isConsonant(varna) && ((i+1) > size) )
                {
                    if (VowelUtil.isConsonant(meterAsCharArray[i + 1]) && MetricUtil.isLaghu(lastEntry))
                    {
                        lengthList.set(lengthList.size() - 1, "G");
                    }

                    else if (varna == 'm' && Character.isSpaceChar((int) meterAsCharArray[i + 1]) && MetricUtil.isLaghu(lastEntry))
                    {
                        lengthList.set(lengthList.size() - 1, "G");
                    }
                }
            }
            lastEntry = varna;
        }
        
        for(String s: lengthList)
        {
            Log.logInfo(s);
        }
        return str;
    }
}
