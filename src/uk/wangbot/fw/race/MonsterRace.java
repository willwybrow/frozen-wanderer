package uk.wangbot.fw.race;

import java.io.Serializable;

/**
 * Created by wjw on 27/08/2017.
 */
public enum MonsterRace implements RacialArchetype, Serializable {
    FAST_GROWING(3),
    MEDIUM_GROWING(2),
    SLOW_GROWING(1),
    NON_GROWING(0);

    private final int statGrowth;

    MonsterRace(int statGrowth) {
        this.statGrowth = statGrowth;
    }

    @Override
    public int getStatGrowth() {
        return this.statGrowth;
    }
}
