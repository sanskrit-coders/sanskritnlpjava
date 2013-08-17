package com.sktutilities.sandhi;

import com.sktutilities.notes.*;
import com.sktutilities.util.*;

public class VowelSandhi
{
    private String   combinedSandhiForm;

    private Comments comments;

    private String   depend  = "\n**This particular Form is Conditional and Dependent on usage implying special Meaning, not otherwise.\n";

    private String   padanta = "Padanta Dependency.\n";

    private boolean  pragrhya;

    public VowelSandhi(String p1, String p2, boolean pragrhya)
    {
        this.pragrhya = pragrhya;

        Log.logInfo("VowelSandhi() ");
        comments = new Comments();
        comments.set_sandhi_type("Vowel Sandhi");
        Log.logInfo("done creating a notes object ");
        combinedSandhiForm = combineIntoSandhi(p1, p2);
    }

    public String getCombinedSandhiForm()
    {
        return combinedSandhiForm;
    }

    public String getNotes()
    {
        return comments.getNotes();

    }

    private String combineIntoSandhi(String anta, String adi)
    {
        // first check whether special apavada niyamas apply
        String apavada = apavada_vriddhi(anta, adi);
        if (!apavada.equals("UNAPPLICABLE")) // implies that they apply,
        // hence go no furthur
        return apavada;
        return utsarga_sandhi(anta, adi);
    }

    private String utsarga_sandhi(String anta, String adi)
    {
        String return_me = anta + adi;
        // ayadi sandhi
        if (VowelUtil.isEjanta(anta) && VowelUtil.isAjadi(adi)) // if ends in eC
        // + begins with
        // any Vowel
        {
            Log.logInfo("sending to ayadi");
            return_me = ayadi_sandhi(anta, adi);
        }
        // checked:29-6

        else if (VowelUtil.isAganta(anta) && adi.startsWith("f")) // shakalya
        // ka
        // prakruti
        // bhava
        { // RRiti akaH
            return_me = utsarga_prakruti_bhava(anta, adi);
            // checked:29-6
        }
        else if (VowelUtil.is_iganta(anta) && VowelUtil.isAjadi(adi)) // if
        // ends
        // in ik
        // +
        // begins
        // with
        // any
        // Vowel
        {

            // akaH savarNe dIrghaH
            if (VowelUtil.isSavarna(anta, adi))
            {
                return_me = dirgha_sandhi(anta, adi); // checked:29-6

                // iko yaN aci + prakruti bhava according to Shakalya 6.1.123
            }

            else
            {
                return_me = utsarga_prakruti_bhava(anta, adi);
            }
            // yan_sandhi(anta,adi)
        }

        else if (VowelUtil.isAkaranta(anta)) // if ends in a Vowel
        {
            // akaH savarNe dIrghaH
            if (VowelUtil.isAkaradi(adi))
            {
                // Log.logInfo("sending to dirgha");
                return_me = dirgha_sandhi(anta, adi);
            }
            // Ad guNaH
            else if (VowelUtil.isIgadi(adi))
            {
                return_me = guna_sandhi(anta, adi);
            }

            // vRRidhireci
            else if (VowelUtil.isEjadi(adi))
            {
                return_me = vriddhi_sandhi(anta, adi);
            }
        }
        Log.logInfo("came in makeasandhi: worked on anta " + anta + " + adi " + adi + " == " + return_me);
        return return_me;
    }

    public String apavada_vriddhi(String X_anta, String X_adi)
    {

        // ALL VRIDDHI APAVAADA RULES GO HERE; 1 dirgha aapavaada rule

        // making life easier by dealing with ITRANS CODING

        Log.logInfo("*******ENTERED AAPAVADA NIYAMA UNO**********");
        Log.logInfo("X_adi == " + X_adi);
        String anta = EncodingUtil.convertSLPToUniformItrans(X_anta);
        // x.transform(X_anta); // anta is ITRANS equivalent
        String adi = EncodingUtil.convertSLPToUniformItrans(X_adi);
        // x.transform(X_adi); // adi is ITRANS equivalent

        Log.logInfo("adi == " + adi);

        String return_me = "UNAPPLICABLE";

        boolean bool1 = VowelUtil.isAkaranta(X_anta) && (adi.equals("eti") || adi.equals("edhati"));
        boolean bool2 = VowelUtil.isAkaranta(X_anta) && adi.equals("UTh");

        // 203
        // **********IF****************//
        if (X_anta.endsWith("f") && X_adi.startsWith("f")) // watch out!!! must
        // be SLP not ITRANS
        {// checked:29-6
            String strip1 = VarnaUtil.stripAntyaVarna(X_anta);
            String strip2 = VarnaUtil.stripAdiVarna(X_adi);

            return_me = utsarga_sandhi(X_anta, X_adi) + ", " + strip1 + "f" + strip2;

            comments.start_adding_notes();
            comments.setSutraNum("");
            comments.setVartikaPath("RRiti RRi vA vacanam");
            comments.setSutraProc("hrasva RRikara ekadesh");
            comments.setSource(Comments.vartika);
            String cond1 = "small RRi followed by small RRi merge to become small RRi.\n" + "RRi + RRi = RRi";
            comments.setConditions(cond1);

        } // end of main if
        // **********END OF IF****************//

        // 204
        // **********IF****************//
        else if (X_anta.endsWith("f") && X_adi.startsWith("x")) // watch out!!!
        // must be SLP
        // not ITRANS
        { // checked:29-6 // SLP x = ITRANS LLi
            String strip1 = VarnaUtil.stripAntyaVarna(X_anta);
            String strip2 = VarnaUtil.stripAdiVarna(X_adi);

            return_me = utsarga_sandhi(X_anta, X_adi) + ", " + strip1 + "x" + strip2; // SLP
            // x =
            // ITRANS
            // LLi

            comments.start_adding_notes();
            comments.setSutraNum("");
            comments.setVartikaPath("LLiti LLi vA vacanam");
            comments.setSutraProc("hrasva LLikara ekadesh");
            comments.setSource(Comments.vartika);
            String cond1 = " RRi/RRI followed by small LLi merge to become small LLi.\n RRi/RRI + LLi = LLi";
            comments.setConditions(cond1);

        } // end of main if
        // **********END OF IF****************//

        // 207a-b
        // **********ELSE IF****************//
        else if (bool1 || bool2)
        {
            // checked:29-6
            return_me = vriddhi_sandhi(X_anta, X_adi);

            // We have to remove vriddhi_sandhi default notes
            // this is done by
            comments.decrementPointer();
            comments.start_adding_notes();
            comments.setSutraNum("6.1.86");
            comments.setSutraPath("eti-edhati-UThsu");
            comments.setSutraProc("vRRiddhi-ekadesh");
            comments.setSource(Comments.sutra);

            String cond1 = "akaara followed by declensions of 'iN', 'edha' and 'UTh" + "<eti/edhati/UTh> are replaced by their vRRiddhi counterpart.\n" + "a/A/a3 + eti/edhati/UTha = VRRiddhi Counterpart.\n" + "Pls. Note.My Program cannot handle all the declensions of given roots." + "Hence will only work for one instance of Third Person Singular Form";
            comments.setConditions(cond1);

            String cond2 = "Blocks para-rupa Sandhi given by 'e~ni pararUpam' which had blocked Normal Vriddhi Sandhi";

            if (bool1)
                comments.append_condition(cond2);
            else if (bool2) comments.append_condition("Blocks 'Ad guNaH'");

        }

        // **********END OF ELSE IF****************//

        // 208
        // **********ELSE IF****************//
        else if (anta.equals("akSa") && adi.equals("UhinI"))
        {// checked:29-6
            return_me = "akzOhiRI"; // u to have give in SLP..had
            // ITRANS....fixed
            // not sending to vrridhit_sandhi becose of Na-inclusion
            // vriddhi_sandhi(X_anta,X_adi);
            // We have to remove vriddhi_sandhi default notes
            // this is done by
            // ***/vowel_notes.decrement_pointer();/***/

            comments.start_adding_notes();
            comments.setSutraNum("");
            comments.setVartikaPath("akSAdUhinyAm");
            comments.setSutraProc("vRRiddhi-ekadesh");
            comments.setSource(Comments.vartika);
            String cond1 = "akSa + UhinI = akshauhiNI.Vartika blocks guna-sandhi ato allow vRRiddhi-ekadesh";
            comments.setConditions(cond1);

        }
        // **********END OF ELSE IF****************//

        // 209
        // **********ELSE IF****************//
        else if (anta.equals("pra") && (adi.equals("Uha") || adi.equals("UDha") || adi.equals("UDhi") || adi.equals("eSa") || adi.equals("eSya")))
        // checked:29-6
        {
            return_me = vriddhi_sandhi(X_anta, X_adi);

            // We have to remove vriddhi_sandhi default notes
            // this is done by
            /***/
            comments.decrementPointer();
            /***/

            comments.start_adding_notes();
            comments.setSutraNum("");
            comments.setVartikaPath("prAd-Uha-UDha-UDhi-eSa-eSyeSu");
            comments.setSutraProc("vRRiddhi-ekadesh");
            comments.setSource(Comments.vartika);
            String cond1 = "upasarga 'pra' + <prAd/Uha/UDha/UDhi/eSa/eSya> = vRRiddhi-ekadesha." + "\nVartika blocks para-rupa Sandhi and/or guna Sandhi to allow vRRidhi-ekadesh.";

            comments.setConditions(cond1);

        }

        // **********END OF ELSE IF****************//

        // 210
        // **********ELSE IF****************//
        else if (anta.equals("sva") && (adi.equals("ira") || adi.equals("irin")))
        {
            // checked:29-6
            return_me = vriddhi_sandhi(X_anta, X_adi);
            // We have to remove vriddhi_sandhi default notes
            // this is done by
            /***/
            comments.decrementPointer();
            /***/

            comments.start_adding_notes();
            comments.setSutraNum("");
            comments.setVartikaPath("svaadireriNoH");
            comments.setSutraProc("vRRiddhi-ekadesh");
            comments.setSource(Comments.vartika);
            String cond1 = "sva + <ira/irin> = vRRIddhi.\n Blocks Guna Sandhi." + "\nPls. note. My program does not cover sandhi with declensions.";
            comments.setConditions(cond1);
        }

        // **********END OF ELSE IF****************//

        // 211 Vik. Semantic Dependency
        // **********ELSE IF****************//
        else if (VowelUtil.isAkaranta(X_anta) && X_adi.equals("fta"))// adi.equals("RRita"))
        {
            // checked:29-6
            // not working for 'a' but working for 'A'. Find out why...fixed

            return_me = utsarga_prakruti_bhava(X_anta, X_adi) + ", " + vriddhi_sandhi(X_anta, X_adi) + "**";

            // We have to remove vriddhi_sandhi default notes
            // this is done by
            /***/
            comments.decrementPointer();
            /***/

            comments.start_adding_notes();
            comments.setSutraNum("");
            comments.setVartikaPath("RRite ca tRRitIyAsamAse");
            comments.setSutraProc("Vriddhi-ekadesh");
            comments.setSource(Comments.vartika);
            String cond1 = "If an  akaranta ('a/aa/a3'-terminating phoneme) is going to form a Tritiya Samas compound with a RRi-initial word" + " vRRiddhi-ekadesha takes place blocking other rules.\n" + "a/A/a3 + RRi -> Tritiya Samaasa Compound -> vRRiddhi-ekadesh" + depend;
            comments.setConditions(cond1);
        }
        // **********END OF ELSE IF****************//

        // 212-213
        // **********ELSE IF****************//
        else if ((anta.equals("pra") || anta.equals("vatsatara") || anta.equals("kambala") || anta.equals("vasana") || anta.equals("RRiNa") || anta.equals("dasha")) && adi.equals("RRiNa"))

        // checked:29-6
        { // pra condition not working...fixed now . 5 MAR 05
            // return_me = guna_sandhi(X_anta,X_adi) + ", " +
            // prakruti_bhava(X_anta,X_adi)

            return_me = vriddhi_sandhi(X_anta, X_adi);
            // We have to remove vriddhi_sandhi default notes
            // this is done by
            /***/
            comments.decrementPointer();
            /***/

            comments.start_adding_notes();
            // vowel_notes.set_sutra_num("") ;
            comments.setVartikaPath("pra-vatsatara-kambala-vasanArNa dashaanAm RRiNe");
            comments.setSutraProc("Vriddhi-ekadesh");
            comments.setSource(Comments.vartika);
            String cond1 = "If 'pra' etc are followed by the word 'RRiNa'," + " vRRiddhi-ekadesh takes place blocking Guna and Prakruti Bhava Sandhis." + "\n<pra/vatsatara/kambala/vasana/RRiNa/dash> + RRiNa = vRRiddhi";
            comments.setConditions(cond1);
        }
        // ???? also implement prakruti bhava

        /**
         * **YEs ACCORDING TO SWAMI DS but not according to Bhaimi Bhashya else
         * if ( adi.equals("RRiNa") && (anta.equals("RRiNa") ||
         * anta.equals("dasha")) ) { return_me = vriddhi_sandhi(X_anta,X_adi);
         * //*sandhi_notes = VE + apavada + vartika + "'RRiNa dashAbhyAm ca'." +
         * "\nBlocks Guna and Prakruti Bhava Sandhi";
         * 
         * vowel_notes.start_adding_notes(); vowel_notes.set_sutra_num("") ;
         * vowel_notes.set_vartika_path("RRiNa dashAbhyAm ca") ;
         * vowel_notes.set_sutra_proc("Vriddhi-ekadesh");
         * vowel_notes.set_source(tippani.vartika) ; String cond1 =depend +
         * "Blocks Guna and Prakruti Bhava Sandhi";
         * vowel_notes.set_conditions(cond1);
         *  /* return_me = utsarga_prakruti_bhava(X_anta,X_adi) + ", " +
         * vriddhi_sandhi(X_anta,X_adi) + "**"; sandhi_notes = usg1 +
         * sandhi_notes; sandhi_notes+= "\n" + usg2 + VE +apavada + vartika +
         * "'RRiNa dashAbhyAm ca'." + depend; // .... this was when I assumed
         * this niyama is optional with other // ???? also implement prakruti
         * bhava }
         */
        // **********END OF ELSE IF****************//
        // 214 Vik. Semantic Dependency
        // **********ELSE IF****************//
        else if (anta.equals("A") && VowelUtil.isAjadi(X_adi))
        {
            // checked:29-6
            // rules is A + Vowel = VRddhi

            return_me = utsarga_sandhi(X_anta, X_adi) + ", " + vriddhi_sandhi(X_anta, X_adi) + "**";
            /*******************************************************************
             * sandhi_notes = usg1 + sandhi_notes + "\n" + usg2 ; // We have to
             * remove vriddhi_sandhi default notes // this is done by /
             ******************************************************************/
            comments.decrementPointer();
            /***/

            comments.start_adding_notes();
            comments.setSutraNum("6.1.87");
            comments.setSutraPath("ATashca");
            comments.setSutraProc("Vriddhi-ekadesh");
            comments.setSource(Comments.vartika);
            String cond1 = depend + "if String 1 equals 'aa' and implies 'AT'-Agama and " + "String 2 is a verbal form. E.g. A + IkSata = aikSata not ekSata.\n" + " 'aa' + Verbal Form = vRRiddhi-ekadesh";
            comments.setConditions(cond1);
        }
        // **********END OF ELSE IF****************//

        // **********ELSE IF****************//
        // akaranta uparga mein error....fixed 4 MAR
        // 215 Vik Semantic Dependency
        else if (is_akaranta_upsarga(X_anta) && X_adi.startsWith("f")) // RRi
        // ==
        // SLP
        // 'f'USing
        // X_adi,
        // switched
        // to
        // Sharfe
        // Encoding
        { // according to Vedanga Prakash Sandhi Vishaya RRIkara is being
            // translated
            // but checked with SC Basu Trans. it is RRIta not RRikara
            // checked:29-6

            Log.logInfo(" Rules 215 applies");
            return_me = utsarga_sandhi(X_anta, X_adi) + ", " + vriddhi_sandhi(X_anta, X_adi) + "**";
            // We have to remove vriddhi_sandhi default notes
            // this is done by
            // vowel_notes.decrement_pointer();

            // July 14 2005.. I have removed the above line
            // vowel_notes.decrement_pointer();

            comments.start_adding_notes();
            comments.setSutraNum("6.1.88");
            comments.setSutraPath("upasargAdRRiti dhAtau");
            comments.setSutraProc("Vriddhi-ekadesh");
            comments.setSource(Comments.vartika);
            String cond1 = depend + "akaranta upsarga(preverb) followed by verbal form begining with short RRi.\n " + "preverb ending in <a> + verbal form begining with RRi = vRRiddhi-ekadesha\n";

            /*
             * "By 6.1.88 it should block all " + "optional forms but by 'vA
             * supyApishaleH' (6.1.89) subantas are " + "permitted the Guna
             * option.";
             */
            comments.setConditions(cond1);
        }
        // **********END OF ELSE IF****************//

        Log.logInfo("return_me == " + return_me);
        Log.logInfo("*******EXITED AAPAVADA NIYAMA UNO**********");

        if (return_me.equals("UNAPPLICABLE"))
        {
            return_me = apavada_para_rupa(X_anta, X_adi); // search for more
            // apavada rules
        }
        return return_me; // apavada rules formulated by Panini apply
    } // end of apavada_vriddhi

    // *******************END OF
    // FUNCTION**************************************************************//

    public String apavada_para_rupa(String X_anta, String X_adi)
    {

        // this will deal with para_rupa and purva_rupa aapavaads

        // making life easier by dealing with ITRANS CODING

        Log.logInfo("*******ENTERED AAPAVADA NIYAMA 2**********");
        String anta = EncodingUtil.convertSLPToUniformItrans(X_anta); // anta
        // is
        // ITRANS
        // equivalent
        String adi = EncodingUtil.convertSLPToUniformItrans(X_adi); // adi is
        // ITRANS
        // equivalent

        String return_me = "UNAPPLICABLE";

        // 216

        // 218 Vik Semantic Dependency
        // **********ELSE IF****************//
        if (VowelUtil.isAkaranta(anta) && (adi.equals("eva")))

        {
            Log.logInfo(" Rules 215 applies");
            return_me = utsarga_sandhi(X_anta, X_adi) + ", " + para_rupa(X_anta, X_adi) + "**";

            comments.start_adding_notes();
            comments.setSutraNum("");
            comments.setVartikaPath("eve caniyoge");
            comments.setSutraProc("para-rupa-ekadesh");
            comments.setSource(Comments.vartika);
            String cond1 = depend + "akaranta word followed by the word 'eva' used to imply uncertainity.\n" + " <a> (a/A/a3) + eva (implying uncertainty) = para-rupa ekadesha\n" + "Blocks all other rules. If the condition of uncertainty is " + "not expressed this rule doesnot apply.";
            comments.setConditions(cond1);
        }
        // **********END OF ELSE IF****************//

        // 217 Vik Semantic Dependency not working
        // **********ELSE IF****************//
        else if (is_akaranta_upsarga(X_anta) && (X_adi.startsWith("e") || X_adi.startsWith("o"))) // USing
        // X_adi,
        // switched
        // to
        // Sharfe
        // Encoding
        {
            Log.logInfo(" Came in Rule 217 ");
            return_me = utsarga_sandhi(X_anta, X_adi) + ", " + para_rupa(X_anta, X_adi) + "**";

            comments.start_adding_notes();
            comments.setSutraNum("6.1.97");
            comments.setSutraPath("e~Ni pararUpam");
            comments.setSutraProc("para-rupa-ekadesh");
            comments.setSource(Comments.sutra);
            String cond1 = depend + "akaranta-upsarga followed by verbal form beginning with " + "'e' or 'o' results in 'para-rupa-ekadesha.\n" + "<akranta-upasarga> a/A/a3 + <e~N> e/o-initial Verbal Form = para-rupa ekadesha\n" + "This Condition will block autsargic vriddhi.";
            comments.setConditions(cond1);

        }
        // **********END OF ELSE IF****************//

        // Removed Below We are not dealing with Internal sandhi any more

        /***********************************************************************
         * // From SKT Pathan Path Pg. 107 //**********ELSE IF
         **********************************************************************/
        //
        // else if ( /*(sandhi.internal == true ) && */ Vowel.is_aganta(X_anta)
        /***********************************************************************
         * && X_adi.equals("am") ) { return_me = purva_rupa(X_anta,X_adi);
         * //sandhi_notes = PRV + apavada + sutra + "'ami pUrvaH'(6.1.107)." +
         * "\nBlocks Ayadi Sandhi"; vowel_notes.start_adding_notes();
         * vowel_notes.set_sutra_num("6.1.107") ; vowel_notes.setSutraPath("ami
         * pUrvaH") ; vowel_notes.set_sutra_proc("purva-rupa-ekadesh");
         * vowel_notes.set_source(tippani.sutra) ; String cond1 ="Blocks Ayadi
         * Sandhi."; vowel_notes.set_conditions(cond1); } //**********END OF
         * ELSE IF
         **********************************************************************/
        //
        // 219
        // 220
        // **********ELSE IF****************//
        else if (anta.equals("sIma") && (adi.equals("anta"))) // USing X_adi,
        // switched to
        // Sharfe
        // Encoding
        {// checked:29-6
            Log.logInfo(" Rules 220 applies");
            return_me = utsarga_sandhi(X_anta, X_adi) + ", " + para_rupa(X_anta, X_adi) + "**";

            comments.start_adding_notes();
            comments.setSutraNum("");
            comments.setVartikaPath("sImantaH kesheSu");
            comments.setSutraProc("para-rupa-ekadesh");
            comments.setSource(Comments.vartika);
            String cond1 = depend + "if the word sImanta is used in relation to hairs of the " + "the head(kesha) this is the correct form.Otherwise not.\n" + "sIma + anta = para-rupa ekadesh. If used in relation to hairs";
            comments.setConditions(cond1);
        }

        // **********END OF ELSE IF****************//
        // 221
        else if (VowelUtil.isAkaranta(X_anta) && (adi.equals("otu") || adi.equals("oSTha"))) // USing
        // X_adi,
        // switched
        // to
        // Sharfe
        // Encoding
        {// checked:29-6
            Log.logInfo(" Rules 220 applies");
            return_me = utsarga_sandhi(X_anta, X_adi) + ", " + para_rupa(X_anta, X_adi) + "**";

            comments.start_adding_notes();
            comments.setSutraNum("");
            comments.setVartikaPath("otvoSThayoH samAse vA");
            comments.setSutraProc("para-rupa-ekadesh");
            comments.setSource(Comments.vartika);
            String cond1 = depend + "This is a optional form which is permitted along " + "with the regular form if an akaranta word is followed by 'otu' and 'oSTha' " + "to form a Compund Word.\n" + " a + otu/oSTha = para-rupa, other Sandhis permitted";
            comments.setConditions(cond1);

        }
        // 222
        // 223a -now working... make diff rule for "A" or consider it...did
        // check 223b
        // **********ELSE IF****************//
        else if (VowelUtil.isAkaranta(X_anta) && (adi.equals("om") && adi.equals("A"))) // USing
        // X_adi,
        // switched
        // to
        // Sharfe
        // Encoding
        {// checked:29-6
            return_me = para_rupa(X_anta, X_adi);
            // sandhi_notes = PAR + apavada + sutra + "'omA~Nosca'(6.1.92)." +
            // "\nBlocks Vrddhi Sandhi";
            comments.start_adding_notes();
            comments.setSutraNum("6.1.92");
            comments.setSutraPath("omA~Noshca");
            comments.setSutraProc("para-rupa ekadesh");
            comments.setSource(Comments.sutra);
            String cond1 = "om/aa~N + iti = para-rupa sandhi.Blocks Vrddhi and/or Dirgha Sandhi";
            comments.setConditions(cond1);
        }
        // **********END OF ELSE IF****************//

        // 223b
        // **********ELSE IF****************//
        /*
         * else if (Vowel.is_akaranta(X_anta) && adi.equals("A") ) // USing
         * X_adi, switched to Sharfe Encoding { return_me =
         * utsarga_sandhi(X_anta,X_adi) + ", " + para_rupa(X_anta,X_adi) + "**";
         * /*sandhi_notes = usg1 + sandhi_notes + "\n" + usg2 ; //sandhi_notes+=
         * PAR + apavada + sutra + "'omA~Nosca'(6.1.92)." + depend + //
         * "\nCondition: If the 'A' of String 2 is 'A~N' then this rule is used "+ // "
         * which blocks Dirgha Sandhi"; // vowel_notes.start_adding_notes();
         * vowel_notes.set_sutra_num("6.1.92") ;
         * vowel_notes.setSutraPath("omA~Nosca") ;
         * vowel_notes.set_sutra_proc("para-rupa ekadesh");
         * vowel_notes.set_source(tippani.sutra) ; String cond1 ="If the 'A' of
         * String 2 is 'A~N' then this rule is used "+ " which blocks Dirgha
         * Sandhi"; vowel_notes.set_conditions(cond1);
         *  }
         */
        // **********END OF ELSE IF****************//
        // 224 Vik Semantic Dependency - apadAnta
        // **********ELSE IF****************//
        else if ( /*
                     * (sandhi.internal == true ) && (SandhiBean.padanta ==
                     * false) &&
                     */
        VowelUtil.isAkaranta(X_anta) && adi.equals("us"))
        {// checked:29-6
            return_me = para_rupa(X_anta, X_adi);
            comments.start_adding_notes();
            comments.setSutraNum("6.1.63");
            comments.setSutraPath("usyapadAntAt");
            comments.setSutraProc("para-rupa ekadesh");
            comments.setSource(Comments.sutra);
            String cond1 = "Non-padanta <a> + 'us' affix = para-rupa.Blocks Vrddhi Sandhi";
            comments.setConditions(cond1);
        }
        // **********END OF ELSE IF****************//

        // 225 Vik Semantic Dependency - apadAnta
        // **********ELSE IF****************//
        // else if ( /*(sandhi.internal == true )&& ( SandhiBean.padanta ==
        // false) && */
        /*
         * X_anta.endsWith("a") && ( X_adi.startsWith("a") ||
         * X_adi.startsWith("e") || X_adi.startsWith("o") ) ) {//checked:29-6
         * 
         * return_me = para_rupa(X_anta,X_adi);
         * vowel_notes.start_adding_notes(); vowel_notes.set_sutra_num("6.1.94") ;
         * vowel_notes.setSutraPath("ato guNe") ;
         * vowel_notes.set_sutra_proc("para-rupa ekadesh");
         * vowel_notes.set_source(tippani.sutra) ; String cond1 ="Blocks Vrddhi
         * Sandhi"; vowel_notes.set_conditions(cond1); }
         */// **********END OF ELSE IF****************//
        // 226 - 231
        // 232 Vik Semantic Dependency - padanta
        // Purva Rupa Sandhi Begins
        // **********ELSE IF****************//
        else if ( /* (SandhiBean.padanta == true ) && */
        (X_anta.endsWith("e") || X_anta.endsWith("o")) && X_adi.startsWith("a"))
        {// checked:29-6
            if (anta.equals("go"))
                ; // condition will be handled elsewhere
            else
            {

                return_me = utsarga_sandhi(X_anta, X_adi) + ", " + purva_rupa(X_anta, X_adi);

                comments.start_adding_notes();
                comments.setSutraNum("6.1.105");
                comments.setSutraPath("e~NaH padAntAdati");
                comments.setSutraProc("purva-rupa ekadesh");
                comments.setSource(Comments.sutra);
                String cond1 = padanta + "If a padanta word ending in either 'e' or 'o' is followed by an 'a' purva-rupa ekadesh takes place" + "\npadanta <e~N> 'e/o' + 'a' = purva-rupa ekadesha. Blocks Ayadi Sandhi";
                comments.setConditions(cond1);

            }
        }
        // **********END OF ELSE IF****************//

        Log.logInfo("return_me == " + return_me);
        Log.logInfo("*******EXITED AAPAVADA NIYAMA 2**********");

        if (return_me.equals("UNAPPLICABLE"))
        {
            return_me = apavada_prakruti_bhava(X_anta, X_adi); // search for
            // more apavada
            // rules
        }
        return return_me; // apavada rules formulated by Panini apply
    }

    public String apavada_prakruti_bhava(String X_anta, String X_adi)
    {

        // ALL VRIDDHI APAVAADA RULES GO HERE; 1 dirgha aapavaada rule

        // making life easier by dealing with ITRANS CODING

        Log.logInfo("*******ENTERED AAPAVADA NIYAMA 3**********");
        String anta = EncodingUtil.convertSLPToUniformItrans(X_anta); // anta
        // is
        // ITRANS
        // equivalent
        String adi = EncodingUtil.convertSLPToUniformItrans(X_adi); // adi is
        // ITRANS
        // equivalent

        String return_me = "UNAPPLICABLE";
        // 249
        if (VowelUtil.isPlutanta(X_anta) && X_adi.equals("iti"))
        {// checked:29-6

            return_me = prakruti_bhava(X_anta, X_adi) + ", " + utsarga_sandhi(X_anta, X_adi) + "**";
            ;
            /*
             * sandhi_notes = usg1 + sandhi_notes + "\nRegular Sandhis which
             * were being blocked by " + "pluta-pragRRihyA-aci-nityam(6.1.121)
             * are allowed by " + " 'apluta-vadupasthite' (6.2.125)" + "\n" +
             * usg2 ;
             * 
             * sandhi_notes+= Prkr + apavada + depend + sutra +
             * "pluta-pragRRihyA aci nityam' (6.1.121)" + "\nCondition: Only if
             * String 1 is a Vedic Usage(Arsha-prayoga)";
             * 
             * //This note below goes after the Notes returned fropm
             * utsarga_sandhi above String cond1 = "\nRegular Sandhis which were
             * being blocked by " + "'pluta-pragRRihyA-aci-nityam'(6.1.121) are
             * allowed by " + " 'apluta-vadupasthite' (6.2.125)";
             * vowel_notes.append_condition(cond1); ;
             */
            // We have to remove vriddhi_sandhi default notes
            // this is done by
            comments.decrementPointer();

            comments.start_adding_notes();
            comments.setSutraNum("6.1.121");
            comments.setSutraPath("pluta-pragRRihyA aci nityam");
            comments.setSutraProc("Prakruti Bhava");
            comments.setSource(Comments.sutra);
            String cond1 = "pluta-ending word or a pragRRihya followed by any Vowel result in Prakruti bhava sandhi.\n" + "<pluta-ending> || pragRRihya + vowel = prakruti bhava.";
            comments.setConditions(cond1);

            comments.start_adding_notes();
            comments.setSutraNum("6.2.125");
            comments.setSutraPath("apluta-vadupasthite");
            comments.setSutraProc("utsargic Sandhis Unblocked");
            comments.setSource(Comments.sutra);
            String cond2 = depend + "According to 6.1.121 plutantas followed by Vowels result in prakruti-bhaava\n" + "However if the word 'iti' used is non-Vedic, then regular sandhis block 6.1.121.";

            comments.setConditions(cond2);

        }

        // 250
        else if ((X_anta.endsWith("I3") || X_anta.endsWith("i3")) && VowelUtil.isAjadi(X_adi))
        // was making mistake of using vowel.is_Vowel(X_adi) */ )
        {// checked:29-6
            Log.logInfo("came in 250");
            return_me = utsarga_sandhi(X_anta, X_adi); // fixed error above:
            // was sending ITRANS
            // values rather than
            // SLP
            /*
             * sandhi_notes += apavada + sutra + "'I3 cAkravarmaNasya'
             * (6.1.126)" + "Blocks 'pluta-pragRRihyA aci nityam' (6.1.121)";
             */
            // vowel_notes.start_adding_notes();
            // vowel_notes.set_sutra_num("6.1.126") ;
            // vowel_notes.setSutraPath("I3 cAkravarmaNasya") ;
            // vowel_notes.set_sutra_proc("para-rupa ekadesh");
            // vowel_notes.set_source(tippani.sutra) ;
            String cond1 = "According to chaakravarman pluta 'i' should be trated as non-plutanta.\n" + "Given in Panini Sutra 'I3 cAkravarmaNasya' (6.1.126). This sutra allows General Sandhis to operate by blocking" + "'pluta-pragRRihyA aci nityam' (6.1.121)";
            comments.append_condition(cond1);

        }
        // prakrutibhava starts
        // 233-239 Vedic Usages
        // 243 : error (now fixed) shivA# isAgacha printing as sh-kaar ha-kaar
        // ikaar etc should be shakaar ikaar
        // **********ELSE IF****************//
        else if ((VowelUtil.isPlutanta(X_anta) || this.pragrhya == true) && VowelUtil.isAjadi(X_adi))
        // was making mistake of using Vowel.is_Vowel(X_adi) */ )
        {// checked:29-6
            Log.logInfo("came in 243");
            return_me = prakruti_bhava(X_anta, X_adi); // fixed error above:
            // was sending ITRANS
            // values rather than
            // SLP
            // sandhi_notes = Prkr + sutra + "pluta-pragRRihyA aci nityam'
            // (6.1.121)";
            comments.start_adding_notes();
            comments.setSutraNum("6.1.121");
            comments.setSutraPath("pluta-pragRRihyA aci nityam");
            comments.setSutraProc("prakruti bhava");
            comments.setSource(Comments.sutra);
            String cond1 = "pragRRihyas or plutantas followed by any vowel result in NO SANDHI which is prakruti bhava"; // Fill
            // Later
            comments.setConditions(cond1);

        }
        // **********END OF ELSE IF****************//

        // **********ELSE IF****************//
        else if (anta.equals("go") && adi.equals("indra")) // Avan~Na Adesh
        {// checked:29-6
            String avang_adesh = EncodingUtil.convertSLPToUniformItrans("gava"); // transform
            // ITRANS
            // gava
            // to
            // SLP
            return_me = guna_sandhi(avang_adesh, X_adi);
            // We have to remove guna_sandhi default notes
            // this is done by
            comments.decrementPointer();

            comments.start_adding_notes();
            comments.setSutraNum("6.1.120");
            comments.setSutraPath("indre ca");
            comments.setSutraProc("ava~nga Adesha followed by Guna Sandhi");
            comments.setSource(Comments.sutra);
            String cond1 = "Blocks Prakruti Bhava, and Ayadi Sandhi.\n go + indra = go + ava~N + indra = gava + indra = gavendra."; // Fill
            // Later
            comments.setConditions(cond1);
        }
        // **********END OF ELSE IF****************//

        // 241 242 Vik.
        // **********ELSE IF****************//
        else if (anta.equals("go") && VowelUtil.isAjadi(X_adi))
        {// checked:29-6

            return_me = utsarga_sandhi(X_anta, X_adi); //
            String avang_adesh = EncodingUtil.convertSLPToUniformItrans("gava"); // transform
            // ITRANS
            // gava
            // to
            // SLP
            return_me += ", " + utsarga_sandhi(avang_adesh, X_adi);

            // We have to remove utsarga_sandhi default notes
            // this is done by
            comments.decrementPointer();

            comments.start_adding_notes();
            comments.setSutraNum("6.1.119");
            comments.setSutraPath("ava~N sphoTayanasya");
            comments.setSutraProc("ava~nga Adesha followed by Regular Vowel Sandhis");
            comments.setSource(Comments.sutra);
            String cond1 = padanta + "View Only Supported by Sphotaayana Acharya.\n" + "padanta 'go' + Vowel gets an avana~N-adesh resulting in gava + Vowel."; // Fill
            // Later...filled
            comments.setConditions(cond1);

            if (X_adi.startsWith("a"))
            {
                return_me += ", " + prakruti_bhava(X_anta, X_adi);

                comments.start_adding_notes();
                comments.setSutraNum("6.1.118");
                comments.setSutraPath("sarvatra vibhaaSaa goH");
                comments.setSutraProc("Optional Prakruti Bhava for 'go'(cow)implying words ending in 'e' or 'o'.");
                comments.setSource(Comments.sutra);
                String cond2 = "Padanta Dependency. Prakruti bhava if 'go' is followed by any other Phoneme."; // Fill
                // Later
                comments.setConditions(cond2);

                return_me += ", " + purva_rupa(X_anta, X_adi);

                comments.start_adding_notes();
                comments.setSutraNum("6.1.105");
                comments.setSutraPath("e~NaH padAntAdati");
                comments.setSutraProc("purva-rupa ekadesh");
                comments.setSource(Comments.sutra);
                String cond3 = padanta + "If a padanta word ending in either 'e' or 'o' is followed by an 'a' purva-rupa ekadesh takes place" + "\npadanta <e~N> 'e/o' + 'a' = purva-rupa ekadesha. Blocks Ayadi Sandhi";
                comments.setConditions(cond3);

            }
        }
        // **********END OF ELSE IF****************//

        // 243

        // 244

        // 246 -250 , 253-260

        Log.logInfo("return_me == " + return_me);
        Log.logInfo("*******EXITED AAPAVADA NIYAMA3s**********");

        return return_me; // apavada rules formulated by Panini
    }

    public String utsarga_prakruti_bhava(String anta, String adi)
    {
        String return_me = anta + adi;
        String hrasva = make_hrasvanta(anta); // make hrasvanta for
        // prakrutibhava

        Log.logInfo("***Welcome to Utsarga Prakruti Bhava sandhi");
        // 248a
        boolean bool1 = VowelUtil.isAkaranta(anta) && adi.startsWith("f");
        boolean bool2 = (VowelUtil.isRRikaranta(anta) || VowelUtil.isLLikaranta(anta)) && adi.startsWith("f");
        boolean bool3 = VowelUtil.is_iganta(anta) && adi.startsWith("f");
        boolean bool4 = VowelUtil.is_iganta(anta) && VowelUtil.isAjadi(adi) && (!VowelUtil.isSavarna(anta, adi));

        if (bool1 || bool2 || bool3)
        {
            if (bool1)
            {
                return_me = guna_sandhi(anta, adi) + ", " + prakruti_bhava(hrasva, adi);
            }

            else if (bool2)
            {
                return_me = dirgha_sandhi(anta, adi) + ", " + prakruti_bhava(hrasva, adi);

            }

            else if (bool3)
            {
                return_me = yan_sandhi(anta, adi) + ", " + prakruti_bhava(hrasva, adi);
            }

            comments.start_adding_notes();
            comments.setSutraNum("6.1.124");
            comments.setSutraPath("RRityakaH");
            comments.setSutraProc("prakruti bhava and hrasvikaraNa");
            comments.setSource(Comments.sutra);

            String cond1 = padanta + "Optional Form According to Sakalya\n" + "If a padanta word ending in an <ak> phoneme is followed by short RRi, " + "the <ak> phoneme gets shortened followed by Prakruti Bhava, according to Shakalya" + "padanta <ak> (a/i/u/RRi/LLi) + Small RRi = shortened <ak> + small RRi.\n" + "shortening of <ak> and Prakruti Bhava."; // Fill
            // Later...filled

            comments.setConditions(cond1);
        }

        // 245
        else if (bool4)
        {
            return_me = yan_sandhi(anta, adi) + ", " + prakruti_bhava(hrasva, adi);

            if (bool3)
                ;
            else
            {
                comments.start_adding_notes();
                comments.setSutraNum("6.1.123");
                comments.setSutraPath("iko.asavarNe shAkalyasya hrasvasca");
                comments.setSutraProc("hrasvikaraN and prakruti bhava");
                comments.setSource(Comments.sutra);
                String cond1 = padanta + "Optional Form According to Sakalya.\n" + "Padanta <ik>(i/u/RRi/LLi) + non-savarNa <ac>(vowel) = NO Sandhi i.e. Prakruti Bhava.\n" + "Preceded by shortening of <ik>\n" + "Pls Note. Shortening of <ik> prohibited in Compounds by Kat. Var. 'na samaase'"; // Fill
                // Later...filed
                comments.setConditions(cond1);
            }
        }

        else if (VowelUtil.isAkaranta(anta) && adi.startsWith("F")) // f ==
        // ITRANS
        // RRI
        {
            return_me = guna_sandhi(anta, adi); // prakruti bhava doesnot apply
        }

        Log.logInfo("****Leaving Utsarga Prakruti Bhava sandhi == " + return_me);
        return return_me;
    }

    public String dirgha_sandhi(String s1, String s2)
    {
        if (!VowelUtil.isSavarna(s1, s2))
        {
            Log.logInfo("NOT ELIGIBLE FOR dIrgha sandhi");
            return "Error";
        }

        String stripped1, stripped2;
        stripped1 = VarnaUtil.stripAntyaVarna(s1);
        stripped2 = VarnaUtil.stripAdiVarna(s2);

        String return_me = stripped1 + stripped2;

        if (VowelUtil.isAkaranta(s1) && VowelUtil.isAkaradi(s2))
            return_me = stripped1 + "A" + stripped2;
        else if (VowelUtil.isIkaranta(s1) && VowelUtil.isIkaradi(s2))
            return_me = stripped1 + "I" + stripped2;
        else if (VowelUtil.isUkaranta(s1) && VowelUtil.isUkaradi(s2))
            return_me = stripped1 + "U" + stripped2;
        else if (VowelUtil.isRRikaranta(s1) && VowelUtil.isRRikaradi(s2))
        {
            return_me = stripped1 + "F" + stripped2;
        }

        else if (VowelUtil.isRRikaranta(s1) && VowelUtil.isLLikaradi(s2))
        {
            return_me = stripped1 + "F" + stripped2;
        } // dirgha between RRi/RRI + LLi/LLI = RRi
        else if (VowelUtil.isLLikaranta(s1) && VowelUtil.isRRikaradi(s2))
        {
            return_me = stripped1 + "F" + stripped2;
        } // dirgha between LLi/LLI + RRi/RRI = RRi

        else if (VowelUtil.isLLikaranta(s1) && VowelUtil.isLLikaradi(s2))
        {
            return_me = stripped1 + "F" + stripped2;
        }

        comments.start_adding_notes();

        comments.setSutraNum("6.1.101");
        comments.setSutraPath("akaH savarNe dIrghaH");
        comments.setSutraProc("dIrgha-adesha");
        comments.setSource(Comments.sutra);
        String cond1 = "simple vowel(<ak>) followed by its savarna merge to be replaced by their corresponding long form.\n" + "Example a/A + a/A = A";
        comments.setConditions(cond1);

        return return_me;

    }

    public String guna_sandhi(String s1, String s2)
    {
        Log.logInfo("Welcome to guNa Sandhi");

        if (!(VowelUtil.isAkaranta(s1) || VowelUtil.isIgadi(s2)))
        {
            Log.logInfo("NOT ELIGIBLE FOR guNa sandhi");
            return "Error";
        }

        String return_me = s1 + s2;
        String stripped1, stripped2;

        stripped1 = VarnaUtil.stripAntyaVarna(s1); // s1.substring(0, str_len1
        // - 1); // remove the
        // terminal 'a' or'A'
        stripped2 = VarnaUtil.stripAdiVarna(s2); // s2.substring(reduce_by2,
        // str_len2);

        if (s2.startsWith("i") || s2.startsWith("I"))
            return_me = stripped1 + "e" + stripped2;

        else if (s2.startsWith("u") || s2.startsWith("U"))
            return_me = stripped1 + "o" + stripped2;

        else if (s2.startsWith("f") || s2.startsWith("F"))
            return_me = stripped1 + "ar" + stripped2;

        else if (s2.startsWith("x") || s2.startsWith("X")) return_me = stripped1 + "al" + stripped2;

        comments.start_adding_notes();
        comments.setSutraNum("6.1.84");
        comments.setSutraPath("Ad guNaH");
        comments.setSutraProc("guNa-ekadesha");
        comments.setSource(Comments.sutra);
        String cond1 = "<a> (a/A/a3) followed by a non-savarNa simple vowel(<ak>) merge to be replaced by their guNa<a/e/o>  counterpart\n" + "a/A + <ik>(i/u/RRi/LLi) = e/o/ar/al";
        comments.setConditions(cond1);

        return return_me;
    }

    public String vriddhi_sandhi(String s1, String s2)
    {

        if (!(VowelUtil.isAjanta(s1) || VowelUtil.isAjadi(s2)))
        {
            Log.logInfo("NOT ELIGIBLE FOR Vowel Sandhi");
            return "Error";
        }

        String return_me = s1 + s2;
        String stripped1 = VarnaUtil.stripAntyaVarna(s1);
        String stripped2 = VarnaUtil.stripAdiVarna(s2);

        // apavaad vriddhi sandhis
        if (VowelUtil.isIkaradi(s2)) // if begins with i/I
            return_me = stripped1 + "E" + stripped2;

        else if (VowelUtil.isUkaradi(s2)) // if begins with u/U
            return_me = stripped1 + "O" + stripped2;

        else if (VowelUtil.isRRikaradi(s2)) // if begins with u/U
            return_me = stripped1 + "Ar" + stripped2;

        else if (VowelUtil.isLLikaradi(s2)) // if begins with u/U
            return_me = stripped1 + "Al" + stripped2; // / Al???? or 'l' matra

        // regular vrddhi sandhis
        else if (s2.startsWith("a") || s2.startsWith("A"))
        {
            return_me = stripped1 + "A" + stripped2;
        }
        else if (s2.startsWith("e") || s2.startsWith("E"))
        {
            return_me = stripped1 + "E" + stripped2;
        }
        else if (s2.startsWith("o") || s2.startsWith("O"))
        {
            return_me = stripped1 + "O" + stripped2;
        }

        comments.start_adding_notes();
        comments.setSutraNum("6.1.88");
        comments.setSutraPath("vRRiddhireci");
        comments.setSutraProc("vRRiddhi-ekadesha");
        comments.setSource(Comments.sutra);
        String cond1 = "a/A followed by an <ec>-vowel merge and are replaced by their vRRiddhi<ai/au>  counterpart\na/A + <ec>(e/ai/o/au) = ai/au";
        comments.setConditions(cond1);

        Log.logInfo(" string " + s1 + " + " + s2 + " == after vriddhi == " + return_me);
        return return_me;
    }

    public String yan_sandhi(String s1, String s2)
    {
        Log.logInfo("Weclome to YaN Sandhi");
        if (VowelUtil.isSavarna(s1, s2)) Log.logInfo("NOT ELIGIBLE FOR yaN sandhi");

        String stripped = VarnaUtil.stripAntyaVarna(s1); // s1.substring(0,
        // str_len - 1);

        if (VowelUtil.isRRikaranta(s1))
        {
            s1 = stripped + "r" + s2;
        }
        else if (VowelUtil.isLLikaranta(s1))
        {
            s1 = stripped + "l" + s2;
        }
        else if (VowelUtil.isIkaranta(s1))
        {
            s1 = stripped + "y" + s2;
        }
        else if (VowelUtil.isUkaranta(s1))
        {
            s1 = stripped + "v" + s2;
        }

        // "yaN Sandhi. \nGiven by Pan. Sut. 'iko yaN aci' (6.1.77)";
        comments.start_adding_notes();
        comments.setSutraNum("6.1.77");
        comments.setSutraPath("iko yaN aci");
        comments.setSutraProc("yaN-aadesha");
        comments.setSource(Comments.sutra);
        String cond1 = "If an <ik> phoneme is followed by an <ac> - vowel - " + "it is replaced by its corresponding <yaN>" + "<ik> (i/u/RRi/LLi) + <ac>(Vowel) = <yaN> y/v/r/l + <ac>(Vowel) "; // Fill
        // Later
        comments.setConditions(cond1);
        Log.logInfo("came in iko_yan: string modified is " + s1);

        return s1;
    }

    public String ayadi_sandhi(String s1, String s2)
    {
        // DONOT STRIP ADI i.e s2
        Log.logInfo("Welcome to ayadi Sandhi");

        if (!VowelUtil.isEjanta(s1)) Log.logInfo("NOT ELIGIBLE FOR ayadi sandhi");

        if (!VowelUtil.isAjadi(s2)) Log.logInfo("NOT ELIGIBLE FOR ayadi sandhi");
        // if 2nd string is not a Vowel, then cannot proceed.
        String return_me = s1 + s2;

        String stripped1;
        stripped1 = VarnaUtil.stripAntyaVarna(s1); // s1.substring(0, str_len1
        // - reduce_by1);

        String modified_anta = s1 + s2;

        if (s1.endsWith("e") || s1.endsWith("e3"))
        {
            modified_anta = stripped1 + "ay";
        }

        else if (s1.endsWith("E") || s1.endsWith("E3"))
        {
            modified_anta = stripped1 + "Ay";
        }

        else if (s1.endsWith("o") || s1.endsWith("o3"))
        {
            modified_anta = stripped1 + "av";
        }

        else if (s1.endsWith("O") || s1.endsWith("O3"))
        {
            modified_anta = stripped1 + "Av";
        }

        return_me = modified_anta + s2;

        comments.start_adding_notes();
        comments.setSutraNum("6.1.78");
        comments.setSutraPath("eco.ayavAyAvaH");
        comments.setSutraProc("ayAdi-Adesha");
        comments.setSource(Comments.sutra);
        String cond1 = "When a word terminating in an <ec> pratyahara " + "is followed by any vowel the <ec>as are replaced by ayadi." + "\n<ec>(e/ai/o/au) + any vowel = ay/Ay/av/Av respectively.";
        comments.setConditions(cond1);

        // The words are furthur modified by the principle of yakkar-vakaar lopa
        return_me += ", " + (new MixedSandhi()).shaakalya_ya_va_lopa(modified_anta, s2, comments);

        return return_me;
    }

    public String purva_rupa(String s1, String s2)
    {
        Log.logInfo("Welcome to purva_rupa Sandhi");

        if (!(VowelUtil.isAjanta(s1) && VowelUtil.isAjadi(s2))) // if
        // consonant-anta
        // or
        // consonant-ado
        {
            Log.logInfo("NOT ELIGIBLE FOR purva_rupa sandhi");
            return "Error";
        }
        String return_me = s1 + s2;

        int str_len2 = s2.length();
        String stripped1 = VarnaUtil.stripAdiVarna(s2);

        if (s2.startsWith("a") && str_len2 != 1 && !VowelUtil.isAganta(s1)) // consider
            // agne
            // + a
            // =
            // agne.a(WRNG)
            return_me = s1 + "'" + stripped1; // "'" == avagraha equals to
        // purva rupa
        else
            return_me = s1 + stripped1;

        Log.logInfo("Welcome to para_rupa Sandhi: returning string == " + return_me);
        return return_me;

    }

    public String para_rupa(String s1, String s2)
    {
        Log.logInfo("Welcome to para_rupa Sandhi");

        if (!(VowelUtil.isAjanta(s1) && VowelUtil.isAjadi(s2))) // if
        // consonant-anta
        // or
        // consonant-ado
        {
            Log.logInfo("NOT ELIGIBLE FOR para_rupa sandhi");
            return "Error";
        }

        String return_me = s1 + s2;
        String stripped = VarnaUtil.stripAntyaVarna(s1);

        return_me = stripped + s2; // equals to para-rupa
        return return_me;
    }

    public String prakruti_bhava(String s1, String s2)
    {
        Log.logInfo("Welcome to prakruti_bhava Sandhi");

        if (!(VowelUtil.isAjanta(s1) && VowelUtil.isAjadi(s2))) // if
        // consonant-anta
        // or
        // consonant-adi
        {
            Log.logInfo("NOT ELIGIBLE FOR para_rupa sandhi");
            return "Error";
        }

        Log.logInfo("Leaving prakruti_bhava Sandhi:");

        return s1 + " " + s2;
    }

    // *******************END OF FUNCTION**********************//

    // *******************BEGINNING OF FUNCTION********************//

    public String make_hrasvanta(String s1)
    {
        String return_me = s1;
        if (!VowelUtil.isAjanta(s1)) Log.logInfo("ERROR: not a Vowel.Cant make hrasvanta");

        String stripped = VarnaUtil.stripAntyaVarna(s1);

        if (VowelUtil.isAkaranta(s1))
            return_me = stripped + "a";
        else if (VowelUtil.isIkaranta(s1))
            return_me = stripped + "i";
        else if (VowelUtil.isUkaranta(s1))
            return_me = stripped + "u";
        else if (VowelUtil.isRRikaranta(s1)) return_me = stripped + "f";

        return return_me;

    }

    public boolean is_akaranta_upsarga(String s1) // / s1 is guaranteed SLP
    // not ITRANS
    {

        /*
         * Since all upsargas are of length less than or equal to 5, their is no
         * point in checking for lengths greater than 5
         */

        if (s1.length() > 5) return false;

        String upsarga_array[] =
        { "pra", "parA", "apa", "sam", "anu", "ava", "nis", "nir", "dus", "dur", "vi", "AN", "ni", "aDi", "api", "ati", "su", "ut", "aBi", "prati", "pari", "upa" }; // all
        // are
        // in
        // SLP

        for (int i = 0; i < upsarga_array.length; i++)
        {
            if ((s1).equals(upsarga_array[i]) && VowelUtil.isAkaranta(s1)) return true;
        }

        return false;

    }

} // end of class
