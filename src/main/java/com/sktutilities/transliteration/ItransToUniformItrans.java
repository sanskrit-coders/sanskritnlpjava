package com.sktutilities.transliteration;

public class ItransToUniformItrans
{

    public ItransToUniformItrans()
    {
    }

    /*
     * This Class replaces alternative Itrans Encodings for a given String to
     * only One Uniform Kind of Itrans so that conversion to SLP is easier. For
     * example 'aa' can be written as 'aa' and 'A' but here we replace all 'aa'
     * to A only
     */
    public static String transform(String transformed)
    {
        //Log.logInfo("I2UI before " + transformed);
        transformed = transformed.replaceAll("x", "kSh");
        transformed = transformed.replaceAll("GY", "j~n");
        transformed = transformed.replaceAll("dny", "j~n");
        transformed = transformed.replaceAll("w", "v");
        transformed = transformed.replaceAll("[\\.][a]", "'"); // avagraha
        transformed = transformed.replaceAll("aa", "A");
        transformed = transformed.replaceAll("ii", "I");// what will happen if
                                                        // user inputs RRii
                                                        // implying
        // RRI-kaar ikaar but will end up reading RRI-kaar
        transformed = transformed.replaceAll("uu", "U");

        transformed = transformed.replaceAll("R\\^i", "RRi");
        transformed = transformed.replaceAll("R\\^I", "RRI");
        transformed = transformed.replaceAll("L\\^i", "LLi");
        transformed = transformed.replaceAll("L\\^I", "LLI");
        transformed = transformed.replaceAll("Ch", "C"); // must be above
                                                            // .replaceAll("ch","c");
        transformed = transformed.replaceAll("ch", "c");

        transformed = transformed.replaceAll("N\\^", "~N");
        transformed = transformed.replaceAll("JN", "~n");

        transformed = transformed.replaceAll("w", "v");


        transformed = transformed.replaceAll("\\.n", "M");
        transformed = transformed.replaceAll("\\.m", "M");
        // difficulty handling the dot, special regex meaning ..... 3MAR05 taken
        // care of using \\
        //Log.logInfo("I2UI after = " + transformed );
        return transformed;
    }
}
