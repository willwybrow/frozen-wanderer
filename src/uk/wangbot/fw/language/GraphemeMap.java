package uk.wangbot.fw.language;

/**
 * Created by wjw on 27/08/2017.
 */
public class GraphemeMap {
    public String[] getValidInitials() {
        return validInitials;
    }

    public String[] getValidMiddles() {
        return validMiddles;
    }

    public String[] getValidFinals() {
        return validFinals;
    }

    private final String[] validInitials;
    private final String[] validMiddles;
    private final String[] validFinals;

    public GraphemeMap(String[] validInitials, String[] validMiddles, String[] validFinals) {
        this.validInitials = validInitials;
        this.validMiddles = validMiddles;
        this.validFinals = validFinals;
    }

    public GraphemeMap(String[] validRepresentations) {
        this.validInitials = validRepresentations;
        this.validMiddles = validRepresentations;
        this.validFinals = validRepresentations;
    }

    public static GraphemeMap roughlyEnglish(Phoneme phoneme) {
        switch (phoneme) {
            case B_AS_IN_BUY:
                return new GraphemeMap(new String[] {"b"});
            case D_AS_IN_DYE:
                return new GraphemeMap(new String[] {"d"});
            case J_AS_IN_JAM:
                return new GraphemeMap(new String[] {"g", "j"}, new String[] {"g", "j", "dg"}, new String[] {"ge", "dge"});
            case TH_AS_IN_THY:
                return new GraphemeMap(new String[] {"th"}, new String[] {"th"}, new String[] {"the"});
            case F_AS_IN_FAN:
                return new GraphemeMap(new String[] {"f", "ph"}, new String[] {"f", "ff", "fh", "ph"}, new String[] {"f", "ff", "fh", "ph"});
            case G_AS_IN_GUY:
                return new GraphemeMap(new String[] {"g", "gh"}, new String[] {"g", "gg"}, new String[] {"g", "gh"});
            case H_AS_IN_HIGH:
                return new GraphemeMap(new String[] {"h"});
            case WH_AS_IN_WHY:
                return new GraphemeMap(new String[] {"wh"}, new String[] {"h"}, new String[] {"h"});
            case Y_AS_IN_YES:
                return new GraphemeMap(new String[] {"y", "j"});
            case K_AS_IN_SKY:
                return new GraphemeMap(new String[] {"c", "k", "kh", "ch"}, new String[] {"c", "k", "ch", "ck"}, new String[] {"c", "k", "ch", "ck"});
            case L_AS_IN_LIE:
                return new GraphemeMap(new String[] {"l"}, new String[] {"l", "ll"}, new String[] {"l", "ll"});
            case M_AS_IN_MY:
                return new GraphemeMap(new String[] {"m"});
            case N_AS_IN_NIGH:
                return new GraphemeMap(new String[] {"n"}, new String[] {"n", "nn"}, new String[] {"n"});
            case NG_AS_IN_SING:
                return new GraphemeMap(new String[] {"ng"});
            case P_AS_IN_PIE:
                return new GraphemeMap(new String[] {"p"}, new String[] {"p", "pp"}, new String[] {"p", "pp"});
            case R_AS_IN_RYE:
                return new GraphemeMap(new String[] {"r", "rh"}, new String[] {"r", "rr"}, new String[] {"r"});
            case S_AS_IN_SIGH:
                return new GraphemeMap(new String[] {"s"}, new String[] {"s", "ss"}, new String[] {"s", "ss"});
            case SH_AS_IN_SHY:
                return new GraphemeMap(new String[] {"sh"}, new String[] {"sh", "ti"}, new String[] {"sh"});
            case T_AS_IN_TIE:
                return new GraphemeMap(new String[] {"th"}, new String[] {"t", "tt"}, new String[] {"t", "tt"});
            case CH_AS_IN_CHAI:
                return new GraphemeMap(new String[] {"ch"}, new String[] {"ch", "tch"}, new String[] {"ch", "tch"});
            case TH_AS_IN_THIGH:
                return new GraphemeMap(new String[] {"th"});
            case V_AS_IN_VIE:
                return new GraphemeMap(new String[] {"v"}, new String[] {"v", "vv"}, new String[] {"v"});
            case W_AS_IN_WYE:
                return new GraphemeMap(new String[] {"w"});
            case Z_AS_IN_ZOO:
                return new GraphemeMap(new String[] {"x", "z"}, new String[] {"zz", "z", "s"}, new String[] {"zz", "z", "s"});
            case ZH_AS_IN_BEIGE:
                return new GraphemeMap(new String[] {"zh", "j"}, new String[] {"zh", "g", "j", "si"}, new String[] {"ge"});
            case CH_AS_IN_LOCH:
                return new GraphemeMap(new String[] {"ch", "gh"});
            case GLOTTAL_STOP:
                return new GraphemeMap(new String[] {"'"});
            case A_AS_IN_FATHER:
                return new GraphemeMap(new String[] {"a", "ah", "aa"});
            case O_AS_IN_POD:
                return new GraphemeMap(new String[] {"o"});
            case A_AS_IN_TRAP:
                return new GraphemeMap(new String[] {"a"});
            case I_AS_IN_PRICE:
                return new GraphemeMap(new String[] {"i", "ei", "ai"}, new String[] {"i", "y", "ei", "ai", "igh", "ay"} ,new String[] {"i", "y", "igh"});
            case OW_AS_IN_FOWL:
                return new GraphemeMap(new String[] {"ow", "ou", "au"}, new String[] {"ow", "ou", "au"}, new String[] {"ow", "au"});
            case A_AS_IN_FACE:
                return new GraphemeMap(new String[] {"a", "ay", "é"}, new String[] {"a", "eh", "ay"}, new String[] {"ay", "eh"});
            case I_AS_IN_IT:
                return new GraphemeMap(new String[] {"i", "y"});
            case EE_AS_IN_FLEET:
                return new GraphemeMap(new String[] {"e", "i"}, new String[] {"e", "y", "ee", "ea", "ie"}, new String[] {"i",  "y"});
            case AU_AS_IN_AUGHT:
                return new GraphemeMap(new String[] {"au", "or", "ou", "aw"}, new String[] {"au", "or", "ou", "aw"}, new String[] {"or", "aw"});
            case OI_AS_IN_CHOICE:
                return new GraphemeMap(new String[] {"oi", "oy"});
            case OA_AS_IN_OAT:
                return new GraphemeMap(new String[] {"o", "oh", "oa"});
            case OO_AS_IN_FOOT:
                return new GraphemeMap(new String[] {"oo", "ou", "u"});
            case OO_AS_IN_BOOT:
                return new GraphemeMap(new String[] {"oo", "ou", "u"});
            case U_AS_IN_CUTE:
                return new GraphemeMap(new String[] {"yu", "u"});
            case U_AS_IN_SLUT:
                return new GraphemeMap(new String[] {"uh", "u"});
            case A_AS_IN_AGO:
                return new GraphemeMap(new String[] {"a", "u", "e"});
            case Y_AS_IN_HAPPY:
                return new GraphemeMap(new String[] {"i", "y"});
            case U_AS_IN_SUPERFLUOUS:
                return new GraphemeMap(new String[] {"u"});
            default:
                return new GraphemeMap(new String[] {""});
        }
    }

    public static GraphemeMap EnglishIPA(Phoneme phoneme) {
        String[] representations = {phoneme.getIPA()};
        return new GraphemeMap(representations, representations, representations);
    }
}
