package uk.wangbot.fw.character;

import java.io.Serializable;

/**
 * Created by wjw on 20/08/2017.
 */
public class StatBlock implements Serializable {
    private SkillType skillType;
    private int modifier;

    public StatBlock(SkillType skillType, int modifier) {
        this.skillType = skillType;
        this.modifier = modifier;
    }

    public SkillType getSkillType() {
        return this.skillType;
    }

    public int getModifier() {
        return this.modifier;
    }
}
