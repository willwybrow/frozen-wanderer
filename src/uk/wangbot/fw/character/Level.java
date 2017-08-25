package uk.wangbot.fw.character;

/**
 * Created by wjw on 20/08/2017.
 */
public class Level {
    private StatBlock statBlock;

    public Level(StatBlock statBlock) {
        this.statBlock = statBlock;
    }

    public StatBlock getStatBlock() {
        return this.statBlock;
    }
}
