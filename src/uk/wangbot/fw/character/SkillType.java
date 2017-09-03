package uk.wangbot.fw.character;

import java.io.Serializable;

/**
 * Created by wjw on 20/08/2017.
 */
public enum SkillType implements Serializable {
    ATTACK(17.5, 4.5),
    DEFENCE(10.0, 1.75),
    HEALTH(250.0, 12.5),
    ABILITY(15.0, 1.75);

    double baseValue;
    double growthByLevel;

    SkillType(double baseValue, double growthByLevel) {
        this.baseValue = baseValue;
        this.growthByLevel = growthByLevel;
    }
}
