package uk.wangbot.fw.race;

import java.io.Serializable;

/**
 * Created by wjw on 27/08/2017.
 */
public enum ClassableRace implements RacialArchetype, Serializable {
    SNOWMAN;

    @Override
    public int getStatGrowth() {
        return 1;
    }
}
