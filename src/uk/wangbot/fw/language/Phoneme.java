package uk.wangbot.fw.language;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wjw on 27/08/2017.
 */
public enum Phoneme {
    B_AS_IN_BUY("b", PhonemeClass.CONSONANT),
    D_AS_IN_DYE("d", PhonemeClass.CONSONANT),
    J_AS_IN_JAM("dʒ", PhonemeClass.CONSONANT),
    TH_AS_IN_THY("ð", PhonemeClass.CONSONANT),
    F_AS_IN_FAN("f", PhonemeClass.CONSONANT),
    G_AS_IN_GUY("ɡ", PhonemeClass.CONSONANT),
    H_AS_IN_HIGH("h", PhonemeClass.CONSONANT),
    WH_AS_IN_WHY("hw", PhonemeClass.CONSONANT),
    Y_AS_IN_YES("j", PhonemeClass.CONSONANT),
    K_AS_IN_SKY("k", PhonemeClass.CONSONANT),
    L_AS_IN_LIE("l", PhonemeClass.CONSONANT),
    M_AS_IN_MY("m", PhonemeClass.CONSONANT),
    N_AS_IN_NIGH("n", PhonemeClass.CONSONANT),
    NG_AS_IN_SING("ŋ", PhonemeClass.CONSONANT),
    P_AS_IN_PIE("p", PhonemeClass.CONSONANT),
    R_AS_IN_RYE("r", PhonemeClass.CONSONANT),
    S_AS_IN_SIGH("s", PhonemeClass.CONSONANT),
    SH_AS_IN_SHY("ʃ", PhonemeClass.CONSONANT),
    T_AS_IN_TIE("t", PhonemeClass.CONSONANT),
    CH_AS_IN_CHAI("tʃ", PhonemeClass.CONSONANT),
    TH_AS_IN_THIGH("θ", PhonemeClass.CONSONANT),
    V_AS_IN_VIE("v", PhonemeClass.CONSONANT),
    W_AS_IN_WYE("w", PhonemeClass.CONSONANT),
    Z_AS_IN_ZOO("z", PhonemeClass.CONSONANT),
    ZH_AS_IN_BEIGE("ʒ", PhonemeClass.CONSONANT),
    CH_AS_IN_LOCH("x", PhonemeClass.MARIGNAL_SEGMENT),
    GLOTTAL_STOP("ʔ", PhonemeClass.MARIGNAL_SEGMENT),
    A_AS_IN_FATHER("ɑː", PhonemeClass.FULL_VOWEL),
    O_AS_IN_POD("ɒ", PhonemeClass.FULL_VOWEL),
    A_AS_IN_TRAP("æ", PhonemeClass.FULL_VOWEL),
    I_AS_IN_PRICE("aɪ", PhonemeClass.FULL_VOWEL),
    OW_AS_IN_FOWL("aʊ", PhonemeClass.FULL_VOWEL),
    A_AS_IN_FACE("eɪ", PhonemeClass.FULL_VOWEL),
    I_AS_IN_IT("ɪ", PhonemeClass.FULL_VOWEL),
    EE_AS_IN_FLEET("iː", PhonemeClass.FULL_VOWEL),
    AU_AS_IN_AUGHT("ɔː", PhonemeClass.FULL_VOWEL),
    OI_AS_IN_CHOICE("ɔɪ", PhonemeClass.FULL_VOWEL),
    OA_AS_IN_OAT("oʊ", PhonemeClass.FULL_VOWEL),
    OO_AS_IN_FOOT("ʊ", PhonemeClass.FULL_VOWEL),
    OO_AS_IN_BOOT("uː", PhonemeClass.FULL_VOWEL),
    U_AS_IN_CUTE("juː", PhonemeClass.FULL_VOWEL),
    U_AS_IN_SLUT("ʌ", PhonemeClass.FULL_VOWEL),
    A_AS_IN_AGO("ə", PhonemeClass.REDUCED_VOWEL),
    Y_AS_IN_HAPPY("i", PhonemeClass.REDUCED_VOWEL),
    U_AS_IN_SUPERFLUOUS("u", PhonemeClass.REDUCED_VOWEL),
    R_AS_IN_START("r", PhonemeClass.VOWEL_ELONGATOR);

    private final String IPA;

    public String getIPA() {
        return IPA;
    }

    public PhonemeClass getPhonemeClass() {
        return phonemeClass;
    }

    private final PhonemeClass phonemeClass;
    Phoneme(String IPA, PhonemeClass phonemeClass) {
        this.IPA = IPA;
        this.phonemeClass = phonemeClass;
    }

    public static List<Phoneme> getAllVowels() {
        ArrayList<Phoneme> allVowels = new ArrayList<>(Phoneme.values().length);
        for (Phoneme phoneme : Phoneme.values()) {
            if ((phoneme.phonemeClass == PhonemeClass.FULL_VOWEL) || (phoneme.phonemeClass == PhonemeClass.REDUCED_VOWEL)) {
                allVowels.add(phoneme);
            }
        }
        return allVowels;
    }

    public static List<Phoneme> getFullVowels() {
        ArrayList<Phoneme> allVowels = new ArrayList<>(Phoneme.values().length);
        for (Phoneme phoneme : Phoneme.values()) {
            if (phoneme.phonemeClass == PhonemeClass.FULL_VOWEL) {
                allVowels.add(phoneme);
            }
        }
        return allVowels;
    }

    public static List<Phoneme> getReducedVowels() {
        ArrayList<Phoneme> allVowels = new ArrayList<>(Phoneme.values().length);
        for (Phoneme phoneme : Phoneme.values()) {
            if (phoneme.phonemeClass == PhonemeClass.REDUCED_VOWEL) {
                allVowels.add(phoneme);
            }
        }
        return allVowels;
    }

    public static List<Phoneme> getAllConsonants() {
        ArrayList<Phoneme> allVowels = new ArrayList<>(Phoneme.values().length);
        for (Phoneme phoneme : Phoneme.values()) {
            if ((phoneme.phonemeClass == PhonemeClass.CONSONANT) || (phoneme.phonemeClass == PhonemeClass.MARIGNAL_SEGMENT)) {
                allVowels.add(phoneme);
            }
        }
        return allVowels;
    }

    public static List<Phoneme> getConsonants() {
        ArrayList<Phoneme> allVowels = new ArrayList<>(Phoneme.values().length);
        for (Phoneme phoneme : Phoneme.values()) {
            if (phoneme.phonemeClass == PhonemeClass.CONSONANT) {
                allVowels.add(phoneme);
            }
        }
        return allVowels;
    }

    public static List<Phoneme> getMarginalSegments() {
        ArrayList<Phoneme> allVowels = new ArrayList<>(Phoneme.values().length);
        for (Phoneme phoneme : Phoneme.values()) {
            if (phoneme.phonemeClass == PhonemeClass.MARIGNAL_SEGMENT) {
                allVowels.add(phoneme);
            }
        }
        return allVowels;
    }
}
