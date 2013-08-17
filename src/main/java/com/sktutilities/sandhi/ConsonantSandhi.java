package com.sktutilities.sandhi;

import com.sktutilities.notes.*;
import com.sktutilities.pratyahara.*;
import com.sktutilities.util.*;

public class ConsonantSandhi
{

    private PratyaharaDecoder   decoder;
    private String              combinedSandhiForm;
    private Comments            cnotes;

    private static final String PADANTA = "Padanta-Dependency.\n";

    public boolean              padanta;

    public ConsonantSandhi(String anta, String adi, boolean padanta)
    {
        this.padanta = padanta;
        decoder = new PratyaharaDecoder();
        cnotes = new Comments();
        cnotes.set_sandhi_type("Consonant Sandhi");
        combinedSandhiForm = agamas(anta, adi);

        if (combinedSandhiForm.equals("INAPPLICABLE"))
        {
            combinedSandhiForm = combineIntoSandhi(anta, adi);
        }
    }

    /** *****End of Constructor************ */

    public String combineIntoSandhi(String anta, String adi)
    {
        String return_me = anta + adi;
        Log.logInfo(" I am in Consonant Sandhi.make_sandhi:::");

        String stripped_anta = VarnaUtil.stripAntyaVarna(anta);
        String anta_varna = VarnaUtil.getAntyaVarna(anta);
        String stripped_adi = VarnaUtil.stripAdiVarna(adi); // what if adi is
                                                            // only == one
                                                            // letter

        /** ***********If***************** */
        if (this.padanta == true && (!anta.equals("praSAn")) && anta.endsWith("n") && (adi.length() > 1) && decoder.adi_varna_in_pratyahara("Cav", adi) && ConsonantUtil.is_am(new Character(adi.charAt(1)).toString()))
        {
            String step1 = stripped_anta + "~s";
            String step2 = stripped_anta + "Ms";

            String step3 = step1 + adi + ", " + step2 + adi;

            if (ConsonantUtil.is_chavargadi(adi)) // if c,Ch
                step3 = dental_to_palatal(step1, adi) + ", " + dental_to_palatal(step2, adi);

            else if (ConsonantUtil.is_Tavargadi(adi)) // if Ta, Tha
            step3 = dental_to_palatal(step1, adi) + ", " + dental_to_palatal(step2, adi);

            return_me = step3;

            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.3.7");
            cnotes.setSutraPath("nashChavyaprashAn");
            cnotes.setSutraProc("ru.N-aadesha to 'n' with anusvArAgama ");
            cnotes.setSource(Comments.sutra);
            String cond1 = PADANTA + "padanta 'n' followed by a word starting in <Chav> phoneme followed " + "by <am> gets ru.N-Adesh which goes through the following transformation with anusVarAgama\n" + "Step 1. padanta 'n' + <Chav><am> ->\n" + "Step 2. 'n' + ru.N + <Chav><am> by 8.3.7 ->\n" + "Step 3. '.N' + ru.N + <Chav><am> by 'anunAsikAt paro'nusvAraH' (8.3.4)   ->\n" + "Step 4. '.N; + r + <Chav><am> by it-lopa sutras ->\n" + "Step 5. '.N' + H + <Chav><am> by kharavasAnyoH visarjanIyaH(8.3.15) ->\n" + "Step 6. '.N' + s + <Chav><am> by 'visarjanIyasya saH'(8.3.34) ->\n" + "Step 3 differentiates this form from option below.";

            cnotes.setConditions(cond1);

            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.3.7");
            cnotes.setSutraPath("nashChavyaprashAn");
            cnotes.setSutraProc("ru.N-aadesha to 'n' with anunaasikaadesha");
            cnotes.setSource(Comments.sutra);
            String cond2 = PADANTA + "padanta 'n' followed by a word starting in <Chav> phoneme followed " + "by <am> gets ru.N-Adesh which goes through the following transformation with anunAsikaadesha\n" + "Step 1. padanta 'n' + <Chav><am> ->\n" + "Step 2. 'n' + ru.N + <Chav><am> by 8.3.7 ->\n" + "Step 3. 'M' + ru.N + <Chav><am> by 'atraanunaasikaH pUrvasya tu Va' (8.3.7)   ->\n" + "Step 4. 'M; + r + <Chav><am> by it-lopa sutras ->\n" + "Step 5. 'M' + H + <Chav><am> by kharavasAnyoH visarjanIyaH(8.3.15) ->\n" + "Step 6. 'M' + s + <Chav><am> by 'visarjanIyasya saH'(8.3.34) ->\n" + "Step 3 differentiates this form from option above.";

            cnotes.setConditions(cond2);

        }
        /** ***********End of If***************** */

        // **************else If*******************//
        else if (anta.endsWith("n") && adi.startsWith("S") && padanta == true)
        {
            return_me = dental_to_palatal(anta, adi);

            // /we also need to implement jharo jhari savrNe...check page 131
            /** ************************* */
            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.4.39");
            cnotes.setSutraPath("stoH shcunA shcuH");
            cnotes.setSutraProc("s-cutva-Adesha");
            cnotes.setSource(Comments.sutra);
            String cond01 = "'s' and ta-varga phonemes followed by or preceded by 'sh' and ca-varga phonemes" + " are replaced by 'sh' and <ca-varga> in the following manner" + "\n's'/<ta-varga>(t/th/d/dh/n) + sh/<ca-varga>(c/chh/j/jh/~n) = sh/<ca-varga> + sh/<ca-varga>" + "\nsh/<ca-varga> + s/<ta-varga> = sh/<ca-varga> + sh/<ca-varga>";
            cnotes.setConditions(cond01);
            /** ************************* */
            String step1 = stripped_anta + "YcC" + stripped_adi;

            /** ************************* */
            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.3.31");
            cnotes.setSutraPath("shi tuk");
            cnotes.setSutraProc("Optional tuk-Agama -> schutva -> optional Chakaar-aadesh to shakaar");
            cnotes.setSource(Comments.sutra);
            String cond1 = PADANTA + "padanta 'n' followed by 'sh' get a tuk-Agama(t) " + "which is futhur modified by 'stoH schunA schuH'\n" + "the 'sh' after the aagama of 'c' a <jhaya> is modified to 'Ch' by 'shashCho'Ti'\n" + "padanta n + 'sh' = n + 'tuk' + 'sh' by 8.3.31->\n" + "n + t + 'sh' by it-lopa -> \n" + "n + c + 'sh' by schutva\n" + "~n + c + 'sh'by schutva\n" + "~n + c + 'Ch' by 'shashCho'Ti'";
            cnotes.setConditions(cond1);
            /** ************************* */

            String step2 = savarna_jharo_jhari_lopa((stripped_anta + "Yc"), ("C" + stripped_adi));
            String step3 = stripped_anta + "Yc" + adi;

            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.3.31");
            cnotes.setSutraPath("shi tuk");
            cnotes.setSutraProc("Optional tuk-Agama -> schutva -> NO Chakaar-aadesh to shakaar");
            cnotes.setSource(Comments.sutra);

            String cond2 = PADANTA + "padanta 'n' followed by 'sh' get a tuk-Agama(t) " + "They are furthur modified by 'stoH schunA schuH' as shown below.\n" + "Since 'shashCho'Ti' is optional, it is not operating in this case\n" + "padanta n + 'sh' = n + 'tuk' + 'sh' by 8.3.31->\n" + "n + t + 'sh' by it-lopa -> \n" + "n + c + 'sh' by schutva\n" + "~n + c + 'sh'by schutva\n";
            cnotes.setConditions(cond2);

            if (step2.equals(""))
                return_me += ", " + step1 + ", " + step3;

            else
                return_me += ", " + step1 + ", " + step2 + ", " + step3;
        }

        /** ***********End of If****************** */

        // **************else If****************************//
        else if (ConsonantUtil.is_jhay(anta_varna) && adi.startsWith("S") && (padanta == true) && (stripped_adi.length() > 0) && (ConsonantUtil.is_yanadi(stripped_adi) || VowelUtil.isVowel(VarnaUtil.getAdiVarna(stripped_adi)) || ConsonantUtil.is_vargiya5(VarnaUtil.getAdiVarna(stripped_adi)) || stripped_adi.startsWith("S")))
        // jhaya + S.followed by am
        {
            return_me = Ch_to_sh(anta, adi);

        }

        // **************End of else if**********************//

        // **************Else If****************************//
        else if ((ConsonantUtil.is_vargiya1(anta_varna) || ConsonantUtil.is_vargiya2(anta_varna) || ConsonantUtil.is_vargiya3(anta_varna) || ConsonantUtil.is_vargiya4(anta_varna)) && adi.startsWith("h") /*
                                                                                                                                                                                                             * &&
                                                                                                                                                                                                             * SandhiBean.padanta ==
                                                                                                                                                                                                             * true
                                                                                                                                                                                                             */)
        {

            if (padanta == true)
            {
                String step1 = jhalam_jash(anta, adi); // Question, what abt
                                                        // khari ca.....hal is
                                                        // part of ash

                /** ****Docs Start*********** */
                cnotes.start_adding_notes();
                cnotes.setSutraNum("8.2.39");
                cnotes.setSutraPath("jhalAm jasho.ante");
                cnotes.setSutraProc("jashatva Adesha to jhal phonemes");
                cnotes.setSource(Comments.sutra);
                String cond1 = "Padanta-Dependency.\n" + "A <jhal> phoneme at the end of a padanta word followed by technically anything " + "is replaced by corresponding 'jash'.\nHowever 'khari ca'(8.4.54) limits scope of this rules so " + "the initial phoneme of the succeding word is limited to <ash> phonemes." + "\nHence padanta <jhal> + <ash> = <jash> + ash.";

                // Add more details
                cnotes.setConditions(cond1);
                /** ****Docs end************* */

                String anta_modified = VarnaUtil.stripAntyaVarna(step1, adi.length());
                return_me = step1 + ", " + jhayo_ha(anta_modified, adi);
            }

            else
            {
                return_me = anta + " " + adi + ", " + jhayo_ha(anta, adi);
                cnotes.start_adding_notes();
                cnotes.setSutraNum("8.4.61");
                cnotes.setSutraPath("jhayo ho.anyatarasyaam");
                cnotes.setSutraProc("purva-savarNa Adesha Not Used");
                cnotes.setSource(Comments.sutra);
                String cond1 = "<jhay> phoneme followed by 'h' is not getting purva-savarNa because it is optional.\n" + "<jhay> + h = <jhay> + h";
                // Add more details
                cnotes.setConditions(cond1);

            }
            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.4.61");
            cnotes.setSutraPath("jhayo ho.anyatarasyam");
            cnotes.setSutraProc("purva-savarNa Adesha");
            cnotes.setSource(Comments.sutra);
            String cond2 = "<jhay> phoneme followed by 'h' is optionally replaced by the purva-savarNa\n" + "<jhay> + h = <jhay> + purva-savrna";
            // Add more details
            cnotes.setConditions(cond2);

        }
        // **************End of elseif**********************//

        // **************Else if*****************************//
        else if (ConsonantUtil.is_jhalanta(anta) && ConsonantUtil.is_ashadi(adi) && padanta == true)
        {

            Log.logInfo("****** Entering in jhalaam jasho.ante");
            return_me = jhalam_jash(anta, adi);

            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.2.39");
            cnotes.setSutraPath("jhalAm jasho.ante");
            cnotes.setSutraProc("jashatva Adesha to jhal phonemes");
            cnotes.setSource(Comments.sutra);
            String cond1 = "Padanta-Dependency.\n" + "A <jhal> phoneme at the end of a padanta word followed by technically anything " + "is replaced by corresponding 'jash'.\nHowever 'khari ca'(8.4.54) limits scope of this rules so " + "the initial phoneme of the succeding word is limited to <ash> phonemes." + "\nHence padanta <jhal> + <ash> = <jash> + ash.";
            cnotes.setConditions(cond1);

            if (ConsonantUtil.is_varganta(anta) && (decoder).adi_varna_in_pratyahara("Yam", adi)) // scharfe
            return_me += ", " + yar_anunasik(anta, adi);
            Log.logInfo("****** Leaving in jhalaam jasho.ante");

        }
        // **************End of else if**********************//

        // **************Else If****************************//
        else if (ConsonantUtil.is_tavarganta(anta) && adi.startsWith("l"))
        {
            return_me = para_savarna(anta, adi);

            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.4.59");
            cnotes.setSutraPath("torli");
            cnotes.setSutraProc("para-savarNa Adesha");
            cnotes.setSource(Comments.sutra);
            String cond1 = "Padanta-Dependency. <ta-varga>t/th/d/dh/n + l = ll. In case of n + l( = .Nll), lakaara-aadesha will be sanumasika.";
            cnotes.setConditions(cond1);

        }
        // **************End of elseif**********************//

        // **************Else if*****************************//
        // 269
        else if (anta.endsWith("M") && ConsonantUtil.is_yayadi(adi))
        {

            if (padanta == false/* sandhi.internal == true */)
            {
                return_me = yayi_parasvarna(anta, adi);

                cnotes.start_adding_notes();
                cnotes.setSutraNum("8.4.57");
                cnotes.setSutraPath("anusvArasya yayi parasavarNaH");
                cnotes.setSutraProc("para-savarNa Adesha");
                cnotes.setSource(Comments.sutra);
                String cond1 = "If an anusvaara is followed by a <yay> phoneme, the anusvaaara " + "is replaced by its para-savarna. In case of a padanta anusvaara this change is optional.\n" + "'anusvaara' + <yay> = <para-savrNA + <yay>";
                cnotes.setConditions(cond1);

            }

            else
            // if (SandhiBean.padanta == true)
            {
                Log.logInfo(" Sending to yayi_parasavarna");
                return_me = yayi_parasvarna(anta, adi) + " " + anusvarikaran(anta, adi, "yes");

                /** *********************** */
                cnotes.start_adding_notes();
                cnotes.setSutraNum("8.4.57");
                cnotes.setSutraPath("anusvArasya yayi parasavarNa");
                cnotes.setSutraProc("para-savarNa Adesha");
                cnotes.setSource(Comments.sutra);
                String cond1 = "Non-Padanta Dependency. If a anusvaara-ending " + "non-padanta word is followed by a 'yay' phoneme, the anusvaaara " + "is replaced by the para-savarna.";
                cnotes.setConditions(cond1);

                /** *********************** */

                /** *********************** */
                cnotes.start_adding_notes();
                cnotes.setSutraNum("8.4.58");
                cnotes.setSutraPath("vA padAntasya");
                cnotes.setSutraProc("No change");
                cnotes.setSource(Comments.sutra);
                String cond2 = "Padanta Dependency. If  a anusvaara-ending padanta word " + "is followed by a 'yay' phoneme the anusvaaara " + "is replaced by the para-savarna. 'vA'makes it optional";
                cnotes.setConditions(cond2);

                /** *********************** */
            }

            if (adi.startsWith("r"))
            {
                return_me = anta + " " + adi;

                cnotes.start_adding_notes();
                cnotes.setSutraNum("8.4.57");
                cnotes.setSutraPath("anusvArasya yayi parasavarNa");
                cnotes.setSutraProc("para-savarNa Adesha Inapplicable");
                cnotes.setSource(Comments.sutra);
                String cond1 = "This combination is eligble for para-savarna adesha, " + "given by 'anusvArasya yayi parasavarNa'(8.4.57) and " + "'vA padantasya(8.4.58). However, since 'refas' donot have" + " a savarna they cannot get a para-savarna adesha...\n" + "Refer to Bhaimi Bhashya on Laghu Siddhanta Kaumudi " + "Part I, page # 122 for this explanation";
                cnotes.setConditions(cond1);

            }
        }
        // **************End of else if**********************//

        // **************Else if*****************************//
        else if (ConsonantUtil.is_varganta(anta) && ConsonantUtil.is_vargiya5(VarnaUtil.getAdiVarna(adi)) && padanta == true)
        // yaro'nnasike condition...even though it refers to yaras which are
        // all consonants save 'h'
        {
            return_me = yar_anunasik(anta, adi);

        }
        // **************End of else if**********************//

        // **************Else if*****************************//
        else if (((ConsonantUtil.is_tavarganta(anta) || anta.endsWith("s")) && (ConsonantUtil.is_Tavargadi(adi) || adi.startsWith("z"))) || ((ConsonantUtil.is_Tavarganta(anta) || anta.endsWith("z")) && (ConsonantUtil.is_tavargadi(adi) || adi.startsWith("s"))))

        {
            if (padanta == true && anta.endsWith("w") && (!adi.equals("nAm") && !adi.equals("navati") && !adi.equals("nagarI")) && (ConsonantUtil.is_tavargadi(adi) || adi.startsWith("s")))
            {
                return_me = jhalam_char(anta, adi); // khari ca B74

                cnotes.start_adding_notes();
                cnotes.setSutraNum("8.4.41");
                cnotes.setSutraPath("na padantaTTornAm");
                cnotes.setSutraProc("STutva-niSedha and charatva Adesha ");
                cnotes.setSource(Comments.sutra);
                String cond1 = "Padanta Dependency.\n" + "jhal-phoneme followed by khar-ending word gets char-adesha.\n" + "<jhal> + <khar> = <char> + <khar>\n" + "Pls. note: This operation was eligible for 'STutva', given by Pan. Sut. 'STunA STuH'(8.4.40).\n " + "However, it has been " + "blocked by Sutra 'na padAntATTornAm'(8.4.41) and/or vartika " + "'anAmnavati-nagarINamiti vAcyam' allowing 'khari ca'(8.4.54) to operate\n";

                cnotes.setConditions(cond1);

            }
            else
            {

                if (ConsonantUtil.is_tavargadi(adi) && adi.startsWith("z")) // SLP
                                                                            // z =
                                                                            // 'brahmin'
                                                                            // sha
                {
                    return_me = jhalam_char(anta, adi);
                    cnotes.start_adding_notes();
                    cnotes.setSutraNum("8.4.42");
                    cnotes.setSutraPath("toH Si");
                    cnotes.setSutraProc("S-Tutva-Adesha niSedha");
                    cnotes.setSource(Comments.sutra);
                    String cond2 = "According to 'STunA STuH'(8.4.40) this particular operation is eligible" + " for tavarga-to-Tavarga or corresponding Ta-varga phonemes in place of ta-varga phonemes.\n" + "8.4.42 negates this provision for <tavargas> followed by 'S' allowing 'khari ca'(8.4.54) to start operating\n." + "<ta-varga> + 'S' = <char>('t') + 'S' ";
                    cnotes.setConditions(cond2);
                }

                else
                {
                    return_me = dental_to_cerebral(anta, adi);
                    cnotes.start_adding_notes();
                    cnotes.setSutraNum("8.4.40");
                    cnotes.setSutraPath("STunA STuH");
                    cnotes.setSutraProc("S-Tutva-Adesha");
                    cnotes.setSource(Comments.sutra);
                    String cond3 = "'s' and ta-varga phonemes followed by or preceded by 'S' and Ta-varga phonemes" + "are replaced by 'S' and <Ta-varga>  in the following manner" + "\n's'/<ta-varga>(t/th/d/dh/n) + 'S'/<Ta-varga>(T/Th/D/Dh/N) = 'S'/<Ta-varga>" + "\n<'S'/Ta-varga> + 's'/<ta-varga> = 'S'/<Ta-varga>";
                    cnotes.setConditions(cond3);
                } // end of innner else

            } // end of outer else
        } // end of else if
        // **************End of else if**********************//

        // **************Else if*****************************//
        else if (((ConsonantUtil.is_tavarganta(anta) || anta.endsWith("s")) && (ConsonantUtil.is_chavargadi(adi) || adi.startsWith("S"))) || ((ConsonantUtil.is_chavarganta(anta) || anta.endsWith("S")) && (ConsonantUtil.is_tavargadi(adi) || adi.startsWith("s"))))
        {
            if (anta.endsWith("S") && ConsonantUtil.is_tavargadi(adi)) // SLP
                                                                        // 'S'
                                                                        // ==
                                                                        // 'sh'
            {
                return_me = anta + adi;
                cnotes.start_adding_notes();
                cnotes.setSutraNum("8.4.43");
                cnotes.setSutraPath("shaat");
                cnotes.setSutraProc("tavarga-cutva niSedha");
                cnotes.setSource(Comments.sutra);
                String cond1 = "According to 'stoH shcunaa shcuH'(8.4.39) this particular operation is eligible" + " for tavarga-cutva or corresponding ca-varga phonemes in place of ta-varga phonemes.\n" + "8.4.43 negates this provision for 'sh' followed by <tavargas> phonemes\n." + "'sh' + <ta-varga> = 'sh' + <ta-varga>";
                cnotes.setConditions(cond1);
            }

            else
            {

                return_me = dental_to_palatal(anta, adi);
                cnotes.start_adding_notes();
                cnotes.setSutraNum("8.4.39");
                cnotes.setSutraPath("stoH shcunA shcuH");
                cnotes.setSutraProc("s-cutva-Adesha");
                cnotes.setSource(Comments.sutra);
                String cond1 = "'s' and ta-varga phonemes followed by or preceded by 'sh' and ca-varga phonemes" + " are replaced by 'sh' and <ca-varga> in the following manner" + "\n's'/<ta-varga>(t/th/d/dh/n) + sh/<ca-varga>(c/chh/j/jh/~n) = sh/<ca-varga> + sh/<ca-varga>" + "\nsh/<ca-varga> + s/<ta-varga> = sh/<ca-varga> + sh/<ca-varga>";
                cnotes.setConditions(cond1);
            }

        }
        // **************End of else if**********************//

        // **************Else if*****************************//
        else if (anta.endsWith("m") && adi.startsWith("hn") && padanta == true)
        {
            return_me = stripped_anta + "n" + adi + ", " + anusvarikaran(anta, adi, "yes");

            /** ********************* */
            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.4.27");
            cnotes.setSutraPath("napare naH");
            cnotes.setSutraProc("nakaara-adesha to makaar");
            cnotes.setSource(Comments.sutra);
            String cond1 = "Padanta Dependency.n(padanta) m + 'hn'(-initial word) = nhn.";
            cnotes.setConditions(cond1);

            /** ********************* */

            /** ********************* */
            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.3.23");
            cnotes.setSutraPath("mo.anusvAraH");
            cnotes.setSutraProc("makaar-AnusvarikaraN");
            cnotes.setSource(Comments.sutra);
            String cond2 = "Padanta-Dependency. padanta m + consonant = anusvaara + consonant.";
            cnotes.setConditions(cond2);

            /** ********************* */

        }
        // **************End of else if**********************//

        // **************Else if*****************************//
        else if (anta.endsWith("m") && adi.startsWith("hm") && padanta == true)
        {
            return_me = anta + adi + ", " + anusvarikaran(anta, adi, "yes");

            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.3.26");
            cnotes.setSutraPath("he mapare vA");
            cnotes.setSutraProc("makaara-adesha to makaar");
            cnotes.setSource(Comments.sutra);
            String cond1 = "Padanta-Dependency. (padanta) m + 'hm'(-initial word) = mhm.No change " + "in essence because makaaara adesh of makaara is makaar.";
            cnotes.setConditions(cond1);

            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.3.23");
            cnotes.setSutraPath("mo.anusvAraH");
            cnotes.setSutraProc("makaar-AnusvarikaraN");
            cnotes.setSource(Comments.sutra);
            String cond2 = "Padanta-Dependency.\n padanta m + consonant = anusvaara + consonant.";
            cnotes.setConditions(cond2);

        }
        // **************End of else if**********************//

        // **************Else if*****************************//
        else if (anta.endsWith("m") && padanta == true && (adi.startsWith("hy") || adi.startsWith("hv") || adi.startsWith("hl")))

        {
            String dvitiya_adi_varna = VarnaUtil.getAdiVarna((VarnaUtil.stripAdiVarna(adi)));
            return_me = stripped_anta + dvitiya_adi_varna + "~" + adi + ", " + anusvarikaran(anta, adi, "yes");

            /** ************************* */
            cnotes.start_adding_notes();
            cnotes.setSutraNum("");
            cnotes.setVartikaPath("yavalapare yavalA vA");
            cnotes.setSutraProc("anunasik ya/va/la-adesha to makaar");
            cnotes.setSource(Comments.vartika);
            String cond1 = "Padanta-Dependency.\n(padanta) m + 'hy'/'hv'/'hl'(-initial word) = yM hy/vM hv/lM hl.s";
            cnotes.setConditions(cond1);
            //	
            /** ************************* */

            /** ************************* */
            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.3.23");
            cnotes.setSutraPath("mo.anusvAraH");
            cnotes.setSutraProc("makaar-AnusvarikaraN");
            cnotes.setSource(Comments.sutra);
            String cond2 = "Padanta-Dependency. padanta m + consonant = anusvaara + consonant.";
            cnotes.setConditions(cond2);

            /** ************************ */

        }
        // **************End of else if**********************//

        // **************Else if*****************************//
        // 264
        else if ((anta.endsWith("m") || anta.endsWith("n")) && ConsonantUtil.is_jhaladi(adi) && padanta == false /*
                                                                                                                     * &&
                                                                                                                     * sandhi.internal ==
                                                                                                                     * true
                                                                                                                     */)
        {
            Log.logInfo(" Sending to anusvarikaran");
            String step1 = anusvarikaran(anta, adi);
            String anta_modified1 = VarnaUtil.stripAntyaVarna(step1, adi.length());

            if (anta_modified1.endsWith("M") && ConsonantUtil.is_yayadi(adi) && !adi.startsWith("r"))
            {
                return_me = "( " + anusvarikaran(anta, adi) + "-> )" + yayi_parasvarna(anta_modified1, adi);

                /** ************************* */
                cnotes.start_adding_notes();
                cnotes.setSutraNum("8.4.57");
                cnotes.setSutraPath("anusvArasya yayi parasavarNa");
                cnotes.setSutraProc("anusvaarikaran leading to para-savarNa Adesha");
                cnotes.setSource(Comments.sutra);
                String cond1 = "String 1 is altered(anusvaaarized) by Sutra 'nascA.apadAntasya jhali'(8.3.24)" + " which is furthur altered by 'anusvArasya yayi parasavarNa'(8.4.57) " + " to yield the given form." + "\nNon-Padanta Dependency.\n" + " (non-padanta) 'm'/'n' + <yay> pratyahaara -> M + <yay> pratyahaara\n" + "-> corresponding savarna phoneme of <yay> + <yay> pratyahaara.\n" + "If a anusvaara-ending " + "non-padanta word is followed by a <yay> phoneme, the anusvaaara " + "is replaced by the para-savarna.";
                cnotes.setConditions(cond1);

                /** ************************ */
            }

            else
            {
                return_me = anusvarikaran(anta, adi);

                /** ************************* */
                cnotes.start_adding_notes();
                cnotes.setSutraNum("8.3.24");
                cnotes.setSutraPath("nashcA.apadAntasya jhali");
                cnotes.setSutraProc("Anusvarization of final 'm' and n");
                cnotes.setSource(Comments.sutra);
                String cond1 = "non-padanta. If 'm' or 'n' in a non-padanta word " + "are followed by 'jhal' phoneme, the 'm' or 'n' are replaced by 'anusvara'\n" + "non-padanta 'm'/'n' + <jhal> = anusvaar + <jhal>";
                cnotes.setConditions(cond1);

                /** ************************ */

            }
        }
        // **************End of else if**********************//

        // **************Else if*****************************//
        else if (VowelUtil.isHrasvanta(anta) && adi.startsWith("C"))
        {
            return_me = anta + 'c' + adi;

            /** ************************ */
            cnotes.start_adding_notes();
            cnotes.setSutraNum("6.1.71");
            cnotes.setSutraPath("Che ca");
            cnotes.setSutraProc("tuk-Agama");
            cnotes.setSource(Comments.sutra);
            String cond1 = "A short vowel followed by 'Ch' gets tuk-Agama which by shcutva becomes 'ca'\n " + "<short vowel> + 'Ch' = <short vowel> + tuk +'Ch' by (6.1.71)-> \n" + "<short vowel> + t +'Ch' ...by it-lopa sutras -> \n" + "<short vowel> + c +'Ch'... by schutva-vidhi given by 'stoH schunA schuH' (8.4.39)";
            cnotes.setConditions(cond1);
            /** ************************ */
        }

        else if (VowelUtil.isDirghanta(anta) && (adi.equals("A") || adi.equals("mA")))
        {
            return_me = anta + 'c' + adi;

            /** ************************ */
            cnotes.start_adding_notes();
            cnotes.setSutraNum("6.1.72");
            cnotes.setSutraPath("A~NmA~Noshcha");
            cnotes.setSutraProc("nitya tuk-Agama");
            cnotes.setSource(Comments.sutra);
            String cond1 = "A long vowel followed by 'Ch' gets tuk-Agama which by shcutva becomes 'ca'\n " + "<short vowel> + 'Ch' = <short vowel> + tuk +'Ch' by (6.1.71)-> \n" + "<short vowel> + t +'Ch' ...by it-lopa sutras -> \n" + "<short vowel> + c +'Ch'... by schutva-vidhi given by 'stoH schunA schuH' (8.4.39)\n" + "Pls. Note. This sutra blocks 'dIrghAt padAntAdvA'(6.1.73) which allows tuk-aagama only optionally " + "if the (pUrva-pada)prior word is padanta";
            cnotes.setConditions(cond1);
            /** ************************ */
        }
        else if (VowelUtil.isDirghanta(anta) && adi.startsWith("C"))
        {
            return_me = anta + 'c' + adi;

            /** ************************ */
            cnotes.start_adding_notes();
            cnotes.setSutraNum("6.1.73");
            cnotes.setSutraPath("dIrghAt padAntAdvA");
            cnotes.setSutraProc("tuk-Agama");
            cnotes.setSource(Comments.sutra);
            String cond1 = "A long vowel followed by 'Ch' gets tuk-Agama which by shcutva becomes 'ca'\n " + "<short vowel> + 'Ch' = <short vowel> + tuk +'Ch' by (6.1.71)-> \n" + "<short vowel> + t +'Ch' ...by it-lopa sutras -> \n" + "<short vowel> + c +'Ch'... by schutva-vidhi given by 'stoH schunA schuH' (8.4.39)";
            cnotes.setConditions(cond1);
            /** ************************ */

            if (padanta == true)
            {
                return_me += ", " + anta + adi;

                /** ************************ */
                cnotes.start_adding_notes();
                cnotes.setSutraNum("6.1.73");
                cnotes.setSutraPath("dIrghAt padAntAdvA");
                cnotes.setSutraProc("Optional tuk-Agama");
                cnotes.setSource(Comments.sutra);
                String cond2 = PADANTA + "A long vowel in a padanta followed by 'Ch' gets tuk-Agama only optionally ";
                cnotes.setConditions(cond2);
                /** ************************ */
            }
        }

        // **************End of else if**********************//

        // **************Else if*****************************//
        // 263
        else if (anta.endsWith("m") && ConsonantUtil.is_haladi(adi) && padanta == true)
        {
            String step1 = anusvarikaran(anta, adi);
            String anta_modified1 = VarnaUtil.stripAntyaVarna(step1, adi.length());

            Log.logInfo("In mo.anusvaaraH anta_modified is ==" + anta_modified1);

            if (anta_modified1.endsWith("M") && ConsonantUtil.is_yayadi(adi) && !adi.startsWith("r"))
            {
                return_me = step1 + ", " + yayi_parasvarna(anta_modified1, adi);

                /** ************************ */
                cnotes.start_adding_notes();
                cnotes.setSutraNum("8.3.23");
                cnotes.setSutraPath("mo.anusvAraH");
                cnotes.setSutraProc("makaar-AnusvarikaraN");
                cnotes.setSource(Comments.sutra);
                String cond1 = PADANTA + "padanta 'ma' followed by any consonant gets nasalized\n" + "padanta 'm' + consonant = anusvaara + consonant.";
                cnotes.setConditions(cond1);
                /** ************************ */

                /** ************************ */
                cnotes.start_adding_notes();
                cnotes.setSutraNum("8.4.57");
                cnotes.setSutraPath("anusvArasya yayi parasavarNa");
                cnotes.setSutraProc("para-savarNa Adesha");
                cnotes.setSource(Comments.sutra);
                String cond2 = PADANTA + "anusvaara followed by a <yay> phoneme is replaced by its para-savarNa.\n" + "Sutra 8.4.58 'vA padAntasya' makes this optional." + "padanta anusvaar + <yay> = ITSELF OR \n" + "padanta anusvaar + <yay> = para-savrNa + <yay>";
                cnotes.setConditions(cond2);
                /** ************************ */
            }

            else
            {
                Log.logInfo(" Sending to anusvarikaran");
                return_me = anusvarikaran(anta, adi, "yes");
                /** ************************ */
                cnotes.start_adding_notes();
                cnotes.setSutraNum("8.3.23");
                cnotes.setSutraPath("mo.anusvAraH");
                cnotes.setSutraProc("makaar-AnusvarikaraN");
                cnotes.setSource(Comments.sutra);
                String cond1 = PADANTA + "padanta 'ma' followed by any consonant gets nasalized\n" + "padanta 'm' + consonant = anusvaara + consonant.";
                cnotes.setConditions(cond1);
                /** ************************ */

            } // end of else

        } // end of else if
        // **************End of else if**********************//

        // **************Else if*****************************//
        // else if( Consonant.is_jhalanta(anta) && Consonant.is_kharadi(adi) )
        else if (decoder.anta_varna_in_pratyahara("Jal", anta) && decoder.adi_varna_in_pratyahara("Kar", adi)) // /
                                                                                                                        // use
                                                                                                                        // SLP
        {

            Log.logInfo(" Sending to jhalam_char: anta == " + anta + " adi == " + adi);
            Log.logInfo("******jhalam_char test");
            Log.logInfo("Consonant.is_jhalanta(anta) == " + ConsonantUtil.is_jhalanta(anta));
            Log.logInfo("Consonant.is_kharadi(adi) == " + ConsonantUtil.is_kharadi(adi));
            Log.logInfo("******jhalam_char test over");

            return_me = jhalam_char(anta, adi); // khari ca B74

            /** ************************ */
            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.4.54");
            cnotes.setSutraPath("khari ca");
            cnotes.setSutraProc("charatva Adesha");
            cnotes.setSource(Comments.sutra);
            String cond2 = "<jhal>-phoneme followed by a <khar> gets charatva-adesha.\n" + "<jhal> + <khar> = <char> + <khar>";
            cnotes.setConditions(cond2);
            /** ************************ */

        }
        // **************End of else if**********************//

        /** ***********ELSE IF ******************* */
        else if (decoder.anta_varna_in_pratyahara("Jal", anta) && decoder.adi_varna_in_pratyahara("JaS", adi) && padanta == false) // SLP
                                                                                                                                            // JaS
                                                                                                                                            // =
                                                                                                                                            // jash
                                                                                                                                            // in
                                                                                                                                            // itrans
        // is this right place for jhalAm jash jhashi
        {
            return_me = jhalam_jash(anta, adi); // jhalam jash jhashi

            /** ************************ */
            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.4.52");
            cnotes.setSutraPath("jhalAm jash jhashi");
            cnotes.setSutraProc("jashatva Adesha");
            cnotes.setSource(Comments.sutra);
            String cond2 = "<jhal>-phoneme followed by a <jhash> inside a pada gets jash-adesha.\n" + "<jhal> + <jhash> = <jash> + <jhash>\n" + "Restriction: Only applies to operations of Internal Sandhi ";
            cnotes.setConditions(cond2);
            /** ************************ */
        }

        /** ***********END OF ELSE IF ******************* */

        /** ***********ELSE IF ******************* */
        else if (ConsonantUtil.is_chavarganta(anta) && ( /*
                                                             * SandhiBean.padanta ==
                                                             * true ||
                                                             */ConsonantUtil.is_jhaladi(adi)))
        {
            // commenting above because conflicts with jhalAm jasho'ante
            return_me = choh_kuh(anta, adi);

            /** ********************************** */
            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.2.30");
            cnotes.setSutraPath("coH kuH");
            cnotes.setSutraProc("kavarga-aadesha to chavarga");
            cnotes.setSource(Comments.sutra);
            String cond1 = "Padanta-Dependency.Or independent of padanta-status is followed by a jhala-initial Word." + "cha-varga + <jhal> = ka-varga OR cha-varga in padanta = ka-varga";
            cnotes.setConditions(cond1);
            /** ********************************** */

            Log.logInfo(" Sending to choh_kuh");
        }
        // shouldnot be here but inserting for lack of time

        /** ***********END OF ELSE IF ******************* */

        /** ***********ELSE IF ******************* */
        else if (decoder.anta_varna_in_pratyahara("hal", anta))
        {
            return_me = anta + adi;
            /** ********************************** */
            cnotes.start_adding_notes();
            cnotes.set_sandhi_type("NO Sandhi");
            // cnotes.set_sutra_num("8.2.30") ;
            cnotes.setVartikaPath("ajjhInam pareNa saMyojyam");
            cnotes.setSutraProc("Non-Vowel Ending Word merges with Word After");
            cnotes.setSource("Rules of Devanagari Script: ");
            String cond1 = "A word terminating in a non-vowel(ac-hInam) should be joined to the word after.";
            cnotes.setConditions(cond1);
            /** ********************************** */

            /** ***********NESTED IF ******************* */
            if ((anta.endsWith("ay") || anta.endsWith("Ay") || anta.endsWith("a3y") || anta.endsWith("av") || anta.endsWith("Av") || anta.endsWith("a3v")) && padanta == true && ConsonantUtil.is_ashadi(adi))
            {
                return_me = (new MixedSandhi()).shaakalya_ya_va_lopa(anta, adi, cnotes);
            }

            /** ***********END OF NESTED IF ******************* */

        }

        /** ***********END OF ELSE IF ******************* */

        Log.logInfo(" Leaving Consonant Sandhi with rturn_me == " + return_me);
        return return_me;
    }

    // *******************BEGINNING OF FUNCTION********************//
    public String agamas(String anta, String adi)
    {
        String return_me = "INAPPLICABLE";
        // String agama_con_notes = con_notes;
        String agama = "";
        String step3 = "";
        String stripped_anta = VarnaUtil.stripAntyaVarna(anta);

        if (anta.endsWith("q") && adi.startsWith("s") && padanta == true)
        {
            // agama = "D"; // D as in dhanush...
            // step1 = make_sandhi(anta,adi) ; // regular sandhi if any
            // agama_con_notes = con_notes;
            // agama_step = make_sandhi(agama,adi);
            // step2 = ", (" + anta + agama + adi + "-> " + anta + "+"+
            // agama_step +"->) " + make_sandhi(anta,agama_step);
            // return_me = step1 + step2;

            return_me = VarnaUtil.stripAntyaVarna(anta) + "wt" + adi + ", " + VarnaUtil.stripAntyaVarna(anta) + "w " + adi; // SLP
                                                                                                                            // Usage
            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.3.29");
            cnotes.setSutraPath("DaH si dhu.NT");
            cnotes.setSutraProc("Optional dhu.NT-agama");
            cnotes.setSource(Comments.sutra);
            String cond1 = padanta + "padanta 'D followed by a 's' gets a dhu.NT-agama optionally\n" + "padanta 'D' + 's' = 'D' + dhu.NT + 's'\n" + "-> 'D' + 'dh' + 's' by it-lopa Sutras\n" + "-> 'D' + (['dh' + 's'] -> ['t' + 's']) by 'khari ca' \n" + "-> [('D' + 't') -> ('T' -> 't')] + 's'  by 'khari ca'\n" + "-> 'D' + 't' + 's'";
            cnotes.setConditions(cond1);

            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.3.29");
            cnotes.setSutraPath("DaH si dhu.NT");
            cnotes.setSutraProc("Optional no dhu.NT-agama");
            cnotes.setSource(Comments.sutra);
            String cond2 = padanta + "D-terminating word is followed by a s-inital " + "word does not get dhu.NT-agama as it is optional." + "\nInstead 'khari ca'(8.4.54) starts operating.\n" + " <jhal>('D') + <khar>('s') = <car>('T') + <khar>('s')";
            cnotes.setConditions(cond2);

        }

        else if ((anta.endsWith("N") || anta.endsWith("R")) && ConsonantUtil.is_shar(VarnaUtil.getAdiVarna(adi)) && padanta == true)
        {

            // step1 = make_sandhi(anta,adi) ; // regular sandhi if any
            // return_me = step1; ... theis in no regular sandhi July-4-2005

            if (anta.endsWith("N"))
            {
                agama = "k";
                // //agama_con_notes = con_notes;
                // agama_step = make_sandhi(agama,adi);
                // step2 = make_sandhi(anta,agama_step);
                // test_step = make_sandhi(anta,agama+adi);
            }

            else if (anta.endsWith("R"))
            {
                agama = "w"; //
                // agama_con_notes = con_notes;
                // agama_step = make_sandhi(agama,adi);
                // step2 = make_sandhi(anta,agama_step);
                // test_step = make_sandhi(anta,agama+adi);
            }
            // if( step2.equals(test_step))
            // {step3 = step2;}
            // else
            // {
            // step3 = ", (" + anta + agama + adi + "-> " + anta + "+"+
            // agama_step +"->) " + step2;
            // }

            step3 = anta + agama + adi;

            return_me = step3 + ", " + anta + " " + adi;

            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.3.28");
            cnotes.setSutraPath("~NNoH ku.Nk-Tu.Nk shari");
            cnotes.setSutraProc("Optional ku.Nk/Tu.Nk-agama.");
            cnotes.setSource(Comments.sutra);
            String cond1 = padanta + "~N/N-terminating word is followed by a shar-pratyahaara-inital " + "word  and gets ku.Nk-Tu.Nk-agama.\nPls.Note: Pasuhkarasadi View has been discarded " + "as given in the Vartika: chayo dvitIyAH shari pauSkarasAderiti vacyam\n" + "~N/N + <shar>('sh','S','s') = ~N/N + ku.Nk/Tu.Nk + <shar> \n" + "-> ~N/N + 'k'/'T' + <shar> it-lopas by it-sutras.\n" + "['~N' + 'k' + <shar>] OR ['N' + 'T' + <shar>]";
            cnotes.setConditions(cond1);
            // padanta only status verified

            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.3.28");
            cnotes.setSutraPath("~NNoH ku.Nk-Tu.Nk shari");
            cnotes.setSutraProc("No ku.Nk/Tu.Nk-agama.");
            cnotes.setSource(Comments.sutra);
            String cond2 = padanta + "~N/N-terminating word is followed by a shar-pratyahaara-inital " + "word does not get ku.Nk-Tu.Nk-agama as it is optional";
            cnotes.setConditions(cond2);
            // padanta status verified
        }

        /** *********************************** */

        else if (anta.endsWith("n") && adi.startsWith("s") && padanta == true)
        {
            // step1 = make_sandhi(anta,adi) ; // regular sandhi if any
            // agama = "D"; // D becomes 't' ..refer to Bhaimi vyakhya page 126
            // agama_con_notes = con_notes;
            // agama_step = make_sandhi(agama,adi);
            // step2 = make_sandhi(anta,agama_step);
            // test_step = make_sandhi(anta,agama+adi);
            // if( step2.equals(test_step))
            // {step3 = step2;}
            // else
            // {
            // step3 = ", (" + anta + agama + adi + "-> " + anta + "+"+
            // agama_step +"->) " + step2;
            // }

            return_me = anta + "t" + adi + ", " + anta + adi;

            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.3.30");
            cnotes.setSutraPath("nashca");
            cnotes.setSutraProc("Optional dhu.NT-agama");
            cnotes.setSource(Comments.sutra);
            String cond1 = padanta + "padanta 'n' followed by 's' gets an optional dhu.NT-Agama\n " + "padanta 'n' + 's' = 'n' + dhu.NT + 's'\n" + "-> n + dh + s (Removal of itas)\n" + "-> n + [(dh + s)->(t + s)] by 'khari ca' \n" + "-> n + t + s";

            cnotes.setConditions(cond1);

            /** *************** */
            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.3.30");
            cnotes.setSutraPath("nashca");
            cnotes.setSutraProc("No dhu.NT-agama");
            cnotes.setSource(Comments.sutra);
            String cond2 = padanta + "padanta 'n' followed by 's' does not get a" + " dhu.NT-Agama as it is optional.";
            cnotes.setConditions(cond2);
            /** ************** */

        }

        /** ********************************** */

        /** ********************************** */
        else if (anta.length() > 1 && VowelUtil.isHrasva(VarnaUtil.getAntyaVarna(stripped_anta)) && (anta.endsWith("N") || anta.endsWith("n") || anta.endsWith("R")) && VowelUtil.isVowel(VarnaUtil.getAdiVarna(adi)) && padanta == true)
        {
            // step1 = make_sandhi(anta,adi) ; // regular sandhi if any

            if (anta.endsWith("N"))
            {
                agama = "N";
                /*
                 * agama_con_notes = con_notes; agama_step =
                 * make_sandhi(agama,adi); step2 = make_sandhi(anta,agama_step);
                 * test_step = make_sandhi(anta,agama+adi);
                 */
            }

            else if (anta.endsWith("n"))
            {
                agama = "n"; //
                /*
                 * agama_con_notes = con_notes; agama_step =
                 * make_sandhi(agama,adi); step2 = make_sandhi(anta,agama_step);
                 * test_step = make_sandhi(anta,agama+adi);
                 */
            }
            else if (anta.endsWith("R"))
            {
                agama = "R"; //
                /*
                 * agama_con_notes = con_notes; agama_step =
                 * make_sandhi(agama,adi); step2 = make_sandhi(anta,agama_step);
                 * test_step = make_sandhi(anta,agama+adi);
                 */

            }
            /*
             * if( step2.equals(test_step)) {step3 = step2;} else { step3 = ", (" +
             * anta + agama + adi + "-> " + anta + "+"+ agama_step +"->) " +
             * step2; }
             */
            return_me = anta + agama + adi + ", " + anta + adi;

            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.3.32");
            cnotes.setSutraPath("~Nmo hrasvAdaci ~namu.NNnityam");
            cnotes.setSutraProc("Optional ~Nmu.NT-agama");
            cnotes.setSource(Comments.sutra);
            String cond1 = padanta + "shortvowel + ~N/~n/N-terminating padanta followed by a vowel-initial " + "word gets one of ~N/~n/N as agama.\n" + "Pls. Note: Bhaimi Vyakhya, part-I page 130 explains 'nitya' as bahudha or more-often-than-not " + "and gives jnapakas(hints) from Paninian usage scuh as 'iko yaN aci'.\n";

            cnotes.setConditions(cond1);

            /** ************************* */
            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.3.32");
            cnotes.setSutraPath("~Nmo hrasvAdaci ~namu.NNnityam");
            cnotes.setSutraProc("No ~Nmu.NT-agama");
            cnotes.setSource(Comments.sutra);
            String cond2 = padanta + "shortvowel + ~N/~n/N-terminating padanta followed by a vowel-initial " + "word does not get ~N/~n/N as agama as it is optional.\n" + "Pls. Note: Bhaimi Vyakhya, part-I page 130 explains 'nitya' as bahudha or more-often-than-not " + "and gives jnapakas(hints) from Paninian usage scuh as 'iko yaN aci'. This allows form 1.\n";
            cnotes.setConditions(cond2);
            /** ************************* */
        }

        /** ********************************** */

        return return_me;
    }

    public String getCombinedSandhiForm()
    {
        return combinedSandhiForm;
    }

    public String getNotes()
    {

        return cnotes.getNotes();
    }

    public Comments getNotesObject()
    {

        return cnotes;
    }

    public String anusvarikaran(String anta, String adi)
    {
        String stripped1 = VarnaUtil.stripAntyaVarna(anta);
        return stripped1 + "M" + adi;
    }

    public String anusvarikaran(String anta, String adi, String yes)
    {
        if (VowelUtil.isAjadi(adi)) anusvarikaran(anta, adi);
        return anusvarikaran(anta, " " + adi); // for padanta anta with
                                                // non-vowel adi: grAmam yAti
    }

    public String para_savarna(String anta, String adi)
    {
        String adi_varna = VarnaUtil.getAdiVarna(adi);
        String stripped_anta = VarnaUtil.stripAntyaVarna(anta);

        if (ConsonantUtil.is_vargiya5(VarnaUtil.getAntyaVarna(anta))) return stripped_anta + "~" + adi_varna + adi; // page
                                                                                                                    // 110
                                                                                                                    // Bhaimi
                                                                                                                    // Vyakhya
        return stripped_anta + adi_varna + adi; // for padanta anta with
                                                // non-vowel adi: grAmam yAti
    }

    public String yayi_parasvarna(String anta, String adi)
    {
        Log.logInfo("Welcome to yayi_parasavarna");
        String return_me = anta + adi;
        String stripped_anta = VarnaUtil.stripAntyaVarna(anta);

        if (ConsonantUtil.is_yayadi(adi))
        {
            if (ConsonantUtil.is_kavargadi(adi))
                return_me = stripped_anta + "N" + adi;

            else if (ConsonantUtil.is_chavargadi(adi))
                return_me = stripped_anta + "Y" + adi;

            else if (ConsonantUtil.is_Tavargadi(adi)) // is Tavarganta Ta like
                                                        // in Tom etc
                return_me = stripped_anta + "R" + adi;

            else if (ConsonantUtil.is_tavargadi(adi))
                return_me = stripped_anta + "n" + adi;

            else if (ConsonantUtil.is_pavargadi(adi))
                return_me = stripped_anta + "m" + adi;

            else if (ConsonantUtil.is_yanadi(adi) && !adi.startsWith("r"))
            // chec thhis.. i think it is wrong...checked with Bhaimi fine
            { // ref has no savarna Bhaimi pg. 122
                String adi_varna = VarnaUtil.getAdiVarna(adi);
                return_me = stripped_anta + adi_varna + "~" + adi;

            }
        }
        Log.logInfo("Exiting yayi_parasavarna: returning " + return_me);
        return return_me;
    }


    public String jhayo_ha(String anta, String adi)
    {
        Log.logInfo(" Welcome to jhayo_ha: anta == " + anta + " adi == " + adi);

        String stripped_adi = VarnaUtil.stripAdiVarna(adi);
        String return_me = anta + adi;

        if (ConsonantUtil.is_kavarganta(anta))
            return_me = anta + "G" + stripped_adi;

        else if (ConsonantUtil.is_chavarganta(anta))
            return_me = anta + "J" + stripped_adi;

        else if (ConsonantUtil.is_Tavarganta(anta)) // is Tavarganta Ta like in
                                                    // Tom etc
            return_me = anta + "Q" + stripped_adi;

        else if (ConsonantUtil.is_tavarganta(anta))
            return_me = anta + "D" + stripped_adi;

        else if (ConsonantUtil.is_pavarganta(anta)) return_me = anta + "B" + stripped_adi;

        Log.logInfo(" Quitting jhayo_ha: return_me == " + return_me);
        return return_me;
    }

    public String dental_to_palatal(String anta, String adi)
    // stoh schunA schuH
    {

        Log.logInfo("Welcome to dental_to_palatal");
        String anta_varna = VarnaUtil.getAntyaVarna(anta);
        String adi_varna = VarnaUtil.getAdiVarna(adi);
        String return_me = anta + adi;
        String stripped1 = VarnaUtil.stripAntyaVarna(anta);
        String stripped2 = VarnaUtil.stripAdiVarna(adi);

        // s/tu + S/cu = S/cu + S/cu
        if ((ConsonantUtil.is_tavarganta(anta) || anta_varna.equals("s")) && (ConsonantUtil.is_chavargadi(adi) || adi_varna.equals("S")))
        {
            if (anta_varna.equals("s"))
                return_me = stripped1 + "S" + adi;

            else if (anta_varna.equals("t"))
                return_me = stripped1 + "c" + adi;

            else if (anta_varna.equals("T"))
                return_me = stripped1 + "C" + adi;

            else if (anta_varna.equals("d"))
                return_me = stripped1 + "j" + adi;

            else if (anta_varna.equals("D"))
                return_me = stripped1 + "J" + adi;

            else if (anta_varna.equals("n")) return_me = stripped1 + "Y" + adi;
        }

        // S/cu + s/tu = S/cu + S/cu
        else if ((ConsonantUtil.is_chavarganta(anta) /*
                                                         * ||
                                                         * anta_varna.equals("S")
                                                         */) && (ConsonantUtil.is_tavargadi(adi) || adi_varna.equals("s")))
        {// by 'shAt' sh + tavarga != stoH scuH vritti... hence commented
            // condition in if statement above
            if (adi_varna.equals("s"))
                return_me = anta + "S" + stripped2;

            else if (adi_varna.equals("t"))
                return_me = anta + "c" + stripped2;

            else if (adi_varna.equals("T"))
                return_me = anta + "C" + stripped2;

            else if (adi_varna.equals("d"))
                return_me = anta + "j" + stripped2;

            else if (adi_varna.equals("D"))
                return_me = anta + "J" + stripped2;

            else if (adi_varna.equals("n")) return_me = anta + "Y" + stripped2;
        }
        else if (anta_varna.equals("S") && adi_varna.equals("s"))
        {
            return_me = anta + "S" + stripped2;
        }
        Log.logInfo("Exiting dental_to_palatal: returning " + return_me);
        return return_me;
    }

    public String dental_to_cerebral(String anta, String adi)
    {

        Log.logInfo("Welcome to dental_to_cerebral");
        String anta_varna = VarnaUtil.getAntyaVarna(anta);
        String adi_varna = VarnaUtil.getAdiVarna(adi);
        String return_me = anta + adi;
        String stripped1 = VarnaUtil.stripAntyaVarna(anta);
        String stripped2 = VarnaUtil.stripAdiVarna(adi);

        // s/tu + z/Tu = z/Tu + z/Tu
        if ((ConsonantUtil.is_tavarganta(anta) || anta_varna.equals("s")) && (ConsonantUtil.is_Tavargadi(adi) /*
                                                                                                                 * ||
                                                                                                                 * adi_varna.equals("z")
                                                                                                                 */))
        {
            // becoause of toH Si, I have commented out line from Above
            if (anta_varna.equals("s"))
                return_me = stripped1 + "z" + adi;

            else if (anta_varna.equals("t"))
                return_me = stripped1 + "w" + adi;

            else if (anta_varna.equals("T"))
                return_me = stripped1 + "W" + adi;

            else if (anta_varna.equals("d"))
                return_me = stripped1 + "q" + adi;

            else if (anta_varna.equals("D"))
                return_me = stripped1 + "Q" + adi;

            else if (anta_varna.equals("n")) return_me = stripped1 + "R" + adi;
        }

        // z/Tu + s/tu = z/Tu + z/Tu
        else if ((ConsonantUtil.is_Tavarganta(anta) || anta_varna.equals("z")) && (ConsonantUtil.is_tavargadi(adi) || adi_varna.equals("s")))
        {
            if (adi_varna.equals("s"))
                return_me = anta + "z" + stripped2;

            else if (adi_varna.equals("t"))
                return_me = anta + "w" + stripped2;

            else if (adi_varna.equals("T"))
                return_me = anta + "W" + stripped2;

            else if (adi_varna.equals("d"))
                return_me = anta + "q" + stripped2;

            else if (adi_varna.equals("D"))
                return_me = anta + "Q" + stripped2;

            else if (adi_varna.equals("n")) return_me = anta + "R" + stripped2;
        }

        else if (anta_varna.equals("s") && adi_varna.equals("z"))
        {
            return_me = stripped1 + "z" + adi;
        }
        Log.logInfo("Exiting dental_to_cerebral: returning " + return_me);
        return return_me;
    }

    public String jhalam_jash(String anta, String adi)
    {
        Log.logInfo(" Welcome to jhalam_jash: anta == " + anta + " adi == " + adi);
        String anta_varna = VarnaUtil.getAntyaVarna(anta);
        String stripped_anta = VarnaUtil.stripAntyaVarna(anta);
        String return_me = anta + adi;
        if (ConsonantUtil.is_jhal(anta_varna)) // corrected mistake I earlier
                                                // has is_jash instead of
                                                // is_jhal
        {
            if (ConsonantUtil.is_kavarganta(anta) || anta.endsWith("h"))
                return_me = stripped_anta + "g" + adi;

            else if (ConsonantUtil.is_chavarganta(anta) || anta.endsWith("S"))
                return_me = stripped_anta + "j" + adi;

            else if (ConsonantUtil.is_Tavarganta(anta) || anta.endsWith("z")) // is
                                                                                // Tavarganta
                                                                                // Ta
                                                                                // like
                                                                                // in
                                                                                // Tom
                                                                                // etc
                return_me = stripped_anta + "q" + adi;

            else if (ConsonantUtil.is_tavarganta(anta) || anta.endsWith("s"))
                return_me = stripped_anta + "d" + adi;

            else if (ConsonantUtil.is_pavarganta(anta)) return_me = stripped_anta + "b" + adi;

            // INCOMPLETE ... need to check for adeshas for 'sha' 'Sa' amd
            // 'sa'..taken care off
        }

        Log.logInfo(" Quitting jhalam_jash: return_me == " + return_me);
        return return_me;
    }

    public String yar_anunasik(String anta, String adi)
    {
        Log.logInfo("Welcome to yar_anunasika");
        String return_me = anta + adi;
        String strippedLastWord = VarnaUtil.stripAntyaVarna(anta);

        if (ConsonantUtil.is_kavarganta(anta))
            return_me = strippedLastWord + "N" + adi;

        else if (ConsonantUtil.is_chavarganta(anta))
            return_me = strippedLastWord + "Y" + adi;

        else if (ConsonantUtil.is_Tavarganta(anta)) // is Tavarganta Ta like in
                                                    // Tom etc
            return_me = strippedLastWord + "R" + adi;

        else if (ConsonantUtil.is_tavarganta(anta))
            return_me = strippedLastWord + "n" + adi;

        else if (ConsonantUtil.is_pavarganta(anta)) return_me = strippedLastWord + "m" + adi;

        cnotes.start_adding_notes();
        cnotes.setSutraNum("8.4.44");
        cnotes.setSutraPath("yaro.anunaasike.anunaasiko vA");
        cnotes.setVartikaPath(", pratyaye bhaaShaayAm nityam");
        cnotes.setSutraProc("savarNa-anunaasika-aadesha");
        cnotes.setSource(Comments.sutra + " and Katyayana Vartika ");
        String cond1 = "Padanta Dependency.\n" + "padanta <yar>** + anunasika = savarNa-anunasika + anunasika.\n" + "Optional in Vedic but compulsory in Classical Sanskrit if the succeeding item is a nasal affix" + "\n**According to Bhaimi Vyakhya, <yar> implies only the 'sparshas'(k-m)";
        cnotes.setConditions(cond1);

        Log.logInfo("Exiting yar_anunasika: returning " + return_me);
        return return_me;
    }

    public String jhalam_char(String anta, String adi)
    {
        // // jhal + khar = car
        Log.logInfo("Welcome to jhalam_char");
        String returnMe = anta + adi;
        String stripped_anta = VarnaUtil.stripAntyaVarna(anta);

        if (ConsonantUtil.is_kavarganta(anta) && !ConsonantUtil.is_vargiya5_anta(anta)) // making
                                                                                        // sure
                                                                                        // doesnt
                                                                                        // have
                                                                                        // Y,m,N,R,n
            returnMe = stripped_anta + "k" + adi;

        else if (ConsonantUtil.is_chavarganta(anta) && !ConsonantUtil.is_vargiya5_anta(anta))
            returnMe = stripped_anta + "c" + adi;

        else if (ConsonantUtil.is_Tavarganta(anta) && !ConsonantUtil.is_vargiya5_anta(anta)) // is
                                                                                                // Tavarganta
                                                                                                // Ta
                                                                                                // like
                                                                                                // in
                                                                                                // Tom
                                                                                                // etc
            returnMe = stripped_anta + "w" + adi;

        else if (ConsonantUtil.is_tavarganta(anta) && !ConsonantUtil.is_vargiya5_anta(anta))
            returnMe = stripped_anta + "t" + adi;

        else if (ConsonantUtil.is_pavarganta(anta) && !ConsonantUtil.is_vargiya5_anta(anta)) returnMe = stripped_anta + "p" + adi;

        // adeshas for S,z, and s are they themselves
        Log.logInfo("Exiting jhalam_char: returning " + returnMe);
        return returnMe;
    }

    public String choh_kuh(String anta, String adi)
    {
        String anta_varna = VarnaUtil.getAntyaVarna(anta);
        String stripped_anta = VarnaUtil.stripAntyaVarna(anta);
        String return_me = anta;
        if (ConsonantUtil.is_chavarganta(anta))
        {
            if (anta_varna.equals("c"))
                return_me = stripped_anta + "k" + adi;

            else if (anta_varna.equals("C"))
                return_me = stripped_anta + "K" + adi;

            else if (anta_varna.equals("j"))
                return_me = stripped_anta + "g" + adi;

            else if (anta_varna.equals("J"))
                return_me = stripped_anta + "G" + adi;

            else if (anta_varna.equals("Y")) return_me = stripped_anta + "N" + adi;

        }
        return return_me;
    }
    public String savarna_jharo_jhari_lopa(String anta, String adi)
    {
        String stripped_anta = VarnaUtil.stripAntyaVarna(anta);
        String return_me = "";

        int len = anta.length();
        boolean hal_upadha_check = false;

        if (len > 2)
        {
            char second_last = anta.charAt(len - 2);
            Character second_last_Char = new Character(second_last);
            String second_last_string = second_last_Char.toString();
            if (ConsonantUtil.is_halanta(second_last_string)) hal_upadha_check = true;
        }

        if (hal_upadha_check && (decoder.anta_varna_in_pratyahara("Jar", anta)) && (decoder.adi_varna_in_pratyahara("Jar", adi)))
        {
            if (ConsonantUtil.is_savarna(anta, adi)) return_me = stripped_anta + adi;

            /** ************************* */
            cnotes.start_adding_notes();
            cnotes.setSutraNum("8.4.64");
            cnotes.setSutraPath("jharo jhari savarNe");
            cnotes.setSutraProc("Optional jhar-lopa");
            cnotes.setSource(Comments.sutra);
            String cond1 = "consonanat followed by a <jhar> phoneme followed by its savarna <jhar> " + " result in the optional elision of the first occuring <jhar>.\n" + "<hal><jhar> + <jhar> = <hal>null + <jhar> Optionally";
            cnotes.setConditions(cond1);

            /** ************************ */
        }

        return return_me;
    }

    public String Ch_to_sh(String anta, String adi)
    {
        String return_me = anta + adi;
        String stripped_adi = VarnaUtil.stripAdiVarna(adi);
        Log.logInfo(" Came in chhakaar adeh condition");
        String step1 = dental_to_palatal(anta, adi);
        String anta_modified1 = VarnaUtil.stripAntyaVarna(step1, adi.length());
        String step2 = jhalam_char(anta_modified1, adi); // khari ca
        String anta_modified2 = VarnaUtil.stripAntyaVarna(step2, adi.length());

        return_me = "(" + step1 + " ->) " + step2 + ", " + anta_modified2 + "C" + stripped_adi; // /
                                                                                                // Chakaara-adesha

        cnotes.start_adding_notes();
        cnotes.setSutraNum("8.4.39 -> 8.4.54");
        cnotes.setSutraPath("stoH shcunA shcuH -> khari ca");
        cnotes.setSutraProc("sh-cutva-aadesha -> charatva");
        cnotes.setSource(Comments.sutra);
        String cond1 = "<jhay> + 'sh'<am> is eligible for Chakaar aadesh by 8.4.62. " + "\nHowever since it is optional 8.4.39 starts operating followed by 8.4.54\n" + "<jhay> + 'sh'<am> \n" + "-> <cu> + 'sh'<am> sh-cutva by 'stoH shcunaa shcuH \n" + "-> <car> + 'sh'<am> -> caratva by 'khari ca'\n"; // Add
                                                                                                                                                                                                                                                                                                                // more
                                                                                                                                                                                                                                                                                                                // details...did

        cnotes.setConditions(cond1);

        cnotes.start_adding_notes();
        cnotes.setSutraNum("8.4.62");
        cnotes.setSutraPath("shashCho.aTi");
        cnotes.setVartikaPath("ChatvamamIti vAcyam");
        cnotes.setSutraProc("Chakaara Adesha to shakaar");
        cnotes.setSource(Comments.sutra + " and Katyayana Vartika ");
        // Add more details...did
        cnotes.setConditions(cond1);
        return return_me;
    }
}
