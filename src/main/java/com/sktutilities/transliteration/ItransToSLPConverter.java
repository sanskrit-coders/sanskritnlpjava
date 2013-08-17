package com.sktutilities.transliteration;

public class ItransToSLPConverter
{

    public static String transform(String transformed)
    {
        transformed = transformed.replaceAll("AUM", "Ω"); // omega or Ohm
        transformed = transformed.replaceAll("OM", "Ω"); // Omega or Ohm
        transformed = transformed.replaceAll("q", "κ"); // Greek kappa
        transformed = transformed.replaceAll("K", "Κ"); // Greek Capital Kappa
        transformed = transformed.replaceAll("G", "γ"); // gamma
        transformed = transformed.replaceAll("z", "ζ"); // Zeta
        transformed = transformed.replaceAll("J", "ζ"); // Zeta
        transformed = transformed.replaceAll("f", "φ"); // phi
        transformed = transformed.replaceAll("\\.Dh", "Δ"); // Capital Delta
        transformed = transformed.replaceAll("\\.D", "δ"); // delta
        transformed = transformed.replaceAll("\\.d", "τ"); // tau
        transformed = transformed.replaceAll("\\.t", "θ"); // theta
        transformed = transformed.replaceAll("\\.s", "σ"); // sigma
        // transformed = transformed.replaceAll("\\.c", "ω"); //omega
        transformed = transformed.replaceAll("RRi", "f");

        transformed = transformed.replaceAll("RRI", "F");
        transformed = transformed.replaceAll("LLi", "x");
        transformed = transformed.replaceAll("LLI", "X");

        transformed = transformed.replaceAll("ai", "E");
        transformed = transformed.replaceAll("au", "O");

        transformed = transformed.replaceAll("kh", "K");

        transformed = transformed.replaceAll("gh", "G");

        /** *NOTE 1 ** */
        // we have to convert ITRANS '~N' - SKT LIB 'N' but confuses with ITRANS
        // 'N'
        // therefore mdifying
        // transformed = transformed.replaceAll("~N","N"); // watch out!!!!
        // to
        transformed = transformed.replaceAll("~N", "@@"); // itrans N is fifth
        // of T-vargas,
        // hence @@
        // SKT LIB 'N' = @@, later @@ back again to 'N' at the end

        /** *Note 1 ** */
        transformed = transformed.replaceAll("\\.N", "~"); // chandra-bindu...watch
        // out do not just
        // use .N use \\.N
        // must be below .replaceAll("~N","5")
        transformed = transformed.replaceAll("Dh", "Q"); // must be before
        // .replaceAll("dh","D");
        transformed = transformed.replaceAll("Th", "W"); // must be before
        // .replaceAll("th","T");
        // transformed = transformed.replaceAll("Ch","C");
        transformed = transformed.replaceAll("jh", "J");
        transformed = transformed.replaceAll("~n", "Y");
        transformed = transformed.replaceAll("T", "w");

        transformed = transformed.replaceAll("D", "q");

        transformed = transformed.replaceAll("N", "R");// confusion with
        // .replaceAll("~N","N")

        transformed = transformed.replaceAll("th", "T"); // watch out!!!!
        transformed = transformed.replaceAll("dh", "D");// watch out!!!!
        transformed = transformed.replaceAll("ph", "P");
        transformed = transformed.replaceAll("bh", "B");

        transformed = transformed.replaceAll("Sh", "z");
        transformed = transformed.replaceAll("sh", "S");
        transformed = transformed.replaceAll("@@", "N"); // refer to NOTE 1

        // Log.logInfo("I2S after : " + transformed);
        // return transformed;
        return transformed;
    }
}
