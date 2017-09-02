package uk.wangbot.fw.character;

import java.io.Serializable;

/**
 * Created by wjw on 20/08/2017.
 */
public class Level implements Serializable {
    private SkillType skillType;

    public Level(SkillType skillType) {
        this.skillType = skillType;
    }

    public SkillType getSkillType() {
        return this.skillType;
    }
}
