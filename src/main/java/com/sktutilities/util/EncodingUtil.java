package com.sktutilities.util;

import java.io.UnsupportedEncodingException;

import com.sktutilities.transliteration.*;

public class EncodingUtil
{


    public static String DEFAULT_ENCODING = "ITRANS";
    
    public static String ITRANS = "ITRANS";

    public static String SLP    = "SLP";

    public static String HK     = "HK";
    
    public static String UNICODE_DVN     = "DVN";
    
    public static String IAST     = "IAST";

    public EncodingUtil()
    {
    }

    public static String getRawItransInput(String alter)
    {
        return alter;
    }

    public static String convertRawItransToUniformItrans(String alter)
    {
        return ItransToUniformItrans.transform(alter);
    }

    public static String convertUniformItransToSLP(String alter)
    {
        return ItransToSLPConverter.transform(alter);
    }

    public static String convertRawItransToIAST(String alter)
    {
        return convertSLPToIAST(convertRawItransToSLP(alter));
    }

    public static String convertRawItransToSLP(String alter)
    {
        return convertUniformItransToSLP(convertRawItransToUniformItrans(alter));
    }

    public static String convertHLToIAST(String alter)
    {
        return convertSLPToIAST(convertHKToSLP(alter));
    }

    public static String convertSLPToDevanagari(String alter)
    {
        return (new SLPToDevanagari()).transform(alter);
       // return (new SLPToBangla()).transform(alter);
    }

    public static String convertSLPToIAST(String alter)
    {
        return new SLPToIAST().transform(alter);
    }

    public static String convertUniformItransToDevanagari(String alter)
    {
        return convertSLPToDevanagari(convertUniformItransToSLP(alter));
    }
    public static String convertRawItransToDevanagari(String alter)
    {
        return convertSLPToDevanagari(convertRawItransToSLP(alter));
    }

    public static String convertSLPToUniformItrans(String alter)
    {
        return SLPToItrans.transform(alter);
    }

    public static String convertSLPToHK(String alter)
    {
        return SLPToHK.transform(alter);
    }

    
    public static String convertDevanagariToSLP(String alter)
    {
        return new DvnToSLP().transform(alter);
    }

    public static String convertDevanagariToUniformItrans(String alter)
    {
        return convertSLPToUniformItrans(convertDevanagariToSLP(alter));
    }

    public static String convertIASTToSLP(String alter)
    {
        return IASTToSLP.transform(alter);
    }

    public static String convertIASTToUniformItrans(String alter)
    {
        return convertSLPToUniformItrans(convertIASTToSLP(alter));
    }

    public static String convertHKToSLP(String string)
    {
        return HKToSLP.transform(string);
    }
    public static String convertHKToIAST(String string)
    {
        return convertSLPToIAST(convertHKToSLP(string));
    }
    public static String convertHKToDVN(String string)
    {
        return convertSLPToDevanagari(convertHKToSLP(string));
    }
    
    public static String convertDVNToIAST(String string)
    {
        String wordInSLP = convertDevanagariToSLP(string);
        //Log.logInfo("" + wordInSLP);
        return convertSLPToIAST(wordInSLP);
    }
    
    public static String convertIASTToDVN(String string)
    {
        String wordInSLP = convertIASTToSLP(string);
        return convertSLPToDevanagari(wordInSLP);
    }
    
    public static String convertToDVN(String str, String encoding)
    {
        if (EncodingUtil.ITRANS.equalsIgnoreCase(encoding))
            return convertRawItransToDevanagari(str);
        else if (EncodingUtil.HK.equalsIgnoreCase(encoding))
            return convertHKToDVN(str);
        else if (EncodingUtil.SLP.equalsIgnoreCase(encoding))
            return convertSLPToDevanagari(str);
        else if (EncodingUtil.IAST.equalsIgnoreCase(encoding))
            return convertIASTToDVN(str);
        else
            return str;
    }
    public static String convertToSLP(String str, String encoding)
    {
        if (EncodingUtil.ITRANS.equalsIgnoreCase(encoding))
            return  convertRawItransToSLP(str);
        else if (EncodingUtil.HK.equalsIgnoreCase(encoding))
            return convertHKToSLP(str);
        else  if (EncodingUtil.UNICODE_DVN.equalsIgnoreCase(encoding))
            return convertDevanagariToSLP(str);
        else  if (EncodingUtil.IAST.equalsIgnoreCase(encoding))
            return convertIASTToSLP(str);
        else
            return str;
    }
    
    public static String convertSLPToEncoding(String str, String encoding)
    {
        if (EncodingUtil.ITRANS.equalsIgnoreCase(encoding))
            return convertSLPToUniformItrans(str);
        else if (EncodingUtil.HK.equalsIgnoreCase(encoding))
            return convertSLPToHK(str);
        else if (EncodingUtil.UNICODE_DVN.equalsIgnoreCase(encoding))
            return convertSLPToDevanagari(str);
        else if (EncodingUtil.IAST.equalsIgnoreCase(encoding))
            return convertSLPToIAST(str);
        else
            return str;
    }
    public static String convertToIAST(String str, String encoding)
    {
        if (EncodingUtil.ITRANS.equalsIgnoreCase(encoding))
            return  convertRawItransToIAST(str);
        else if (EncodingUtil.HK.equalsIgnoreCase(encoding))
            return  convertHKToIAST(str);
        else if (EncodingUtil.SLP.equalsIgnoreCase(encoding))
            return convertSLPToIAST(str);
        else if (EncodingUtil.UNICODE_DVN.equalsIgnoreCase(encoding))
            return  convertDVNToIAST(str);
        else return str;
    }

        public static String toUTF8(String isoString)
        /**
         * Convert ISO8859-1 format string (which is the default sent by IE
         * to the UTF-8 format that the database is in.
         */
        {
         String utf8String = "";
         if (isoString != null && isoString.length() > 0)
         {
          try
          {
           byte[] stringBytesISO = isoString.getBytes("ISO-8859-1");
           utf8String = new String(stringBytesISO, "UTF-8");
          }
          catch(UnsupportedEncodingException e)
          {
           // As we can't translate just send back the best guess.
           Log.logInfo("UnsupportedEncodingException is: " + e.getMessage());
           utf8String = isoString;
          }
         }
         else
         {
          utf8String = isoString;
         }
         return utf8String;
    }
}
