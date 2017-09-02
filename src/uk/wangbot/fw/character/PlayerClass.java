package uk.wangbot.fw.character;

import java.io.Serializable;
import java.util.*;

/**
 * Created by wjw on 20/08/2017.
 */

public enum PlayerClass implements Serializable {
    FIGHTER(SkillType.ATTACK, SkillType.DEFENCE, SkillType.ABILITY, SkillType.HEALTH),
    HEALER(SkillType.DEFENCE, SkillType.HEALTH, SkillType.ATTACK, SkillType.ABILITY),
    MAGE(SkillType.ABILITY, SkillType.ATTACK, SkillType.HEALTH, SkillType.DEFENCE),
    UTILITY(SkillType.HEALTH, SkillType.ABILITY, SkillType.DEFENCE, SkillType.ATTACK);

    public enum Primacy {
        PRIMARY,
        SECONDARY,
        TERTIARY,
        QUATERNARY
    }

    private static final List<Primacy> SkillPrimacy;
    static {
        List<Primacy> skillPrimacy = new ArrayList<>(10);
        skillPrimacy.add(Primacy.PRIMARY);
        skillPrimacy.add(Primacy.PRIMARY);
        skillPrimacy.add(Primacy.SECONDARY);
        skillPrimacy.add(Primacy.PRIMARY);
        skillPrimacy.add(Primacy.SECONDARY);
        skillPrimacy.add(Primacy.TERTIARY);
        skillPrimacy.add(Primacy.PRIMARY);
        skillPrimacy.add(Primacy.SECONDARY);
        skillPrimacy.add(Primacy.TERTIARY);
        skillPrimacy.add(Primacy.QUATERNARY);
        SkillPrimacy = Collections.unmodifiableList(skillPrimacy);
    }

    private final SkillType primarySkill;
    private final SkillType secondarySkill;
    private final SkillType tertiarySkill;
    private final SkillType quaternarySkill;
    PlayerClass(SkillType primarySkill, SkillType secondarySkill, SkillType tertiarySkill, SkillType quaternarySkill) {
        this.primarySkill = primarySkill;
        this.secondarySkill = secondarySkill;
        this.tertiarySkill = tertiarySkill;
        this.quaternarySkill = quaternarySkill;
    }

    public SkillType autoSkillByLevel(int level) {
        switch (SkillPrimacy.get((level - 1) % SkillPrimacy.size())) {
            case PRIMARY:
                return this.primarySkill;
            case SECONDARY:
                return this.secondarySkill;
            case TERTIARY:
                return this.tertiarySkill;
            case QUATERNARY:
                return this.quaternarySkill;
        }
        return this.primarySkill;  //unreachable!
    }
}

