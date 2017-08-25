package uk.wangbot.fw.character;

import com.sun.org.apache.regexp.internal.RE;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Vector;

/**
 * Created by wjw on 20/08/2017.
 */
public abstract class Character {
    private int experience;
    private CharacterClass classType;
    private Vector<Level> levels;
    private Religion religion;

    public Character() {
        this.experience = 0;
        this.classType = null;
        this.levels = new Vector<>(10);
        this.religion = null;
    }

    private static int xpToLevel(int level) {
        return (int)Math.round(2000 * Math.pow(level, 3));
    }

    public boolean canLevelUp() {
        return this.religion != null && this.experience > Character.xpToLevel(levels.size() + 1);
    }


    public int getStat(SkillType skillType) {
        int stat = 0;
        for (ListIterator<Level> i = this.levels.listIterator(); i.hasNext();) {
            int levelNumber = i.nextIndex() + 1;
            Level level = i.next();

            StatBlock thisLevelStatBlock = (level).getStatBlock();
            if (thisLevelStatBlock.getSkillType() == skillType) {
                stat += thisLevelStatBlock.getModifier();
            }
            SkillType thisLevelAutoSkill = this.classType.autoSkillByLevel(levelNumber);
            if (thisLevelAutoSkill == skillType) {
                stat += 1;
            }
        }
        return stat;
    }

    public HashMap<SkillType, Integer> getStats() {
        SkillType[] availableSkillTypes = SkillType.class.getEnumConstants();
        HashMap<SkillType, Integer> stats = new HashMap<>(availableSkillTypes.length);
        for (SkillType skillType : availableSkillTypes) {
            stats.put(skillType, this.getStat(skillType));
        }
        return stats;
    }

    public boolean declareReligion(Religion religion) {
        if (this.religion == null) {
            this.religion = religion;
            return (this.religion != null);
        } else {
            return false;
        }
    }

    public boolean chooseClass(CharacterClass characterClass) {
        if (this.classType == null) {
            this.classType = characterClass;
            return (this.classType != null);
        } else {
            return false;
        }
    }

    public boolean levelUp(Level level) {
        if (this.canLevelUp()) {
            this.levels.add(level);
            return true;
        } else {
            return false;
        }
    }

    public void gainExperience(int experience) {
        this.experience += experience;
    }
}
