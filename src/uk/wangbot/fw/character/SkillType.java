package uk.wangbot.fw.character;

import java.io.Serializable;

/**
 * Created by wjw on 20/08/2017.
 */
public enum SkillType implements Serializable {
    ATTACK(50.0, 2.5),
    DEFENCE(25.0, 4.0),
    HEALTH(525.0, 75.0),
    ABILITY(15.0, 1.0);

    double baseValue;
    double growthByLevel;

    SkillType(double baseValue, double growthByLevel) {
        this.baseValue = baseValue;
        this.growthByLevel = growthByLevel;
    }
}
