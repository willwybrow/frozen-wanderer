package uk.wangbot.fw.crafting;

/**
 * Created by wjw on 02/09/2017.
 */
public enum Size {
    HANDY(0), // pocketable, zero cost
    SMALL(1), // can hold in a hand but not too many. a brick, a notebook (a sword?!)
    MEDIUM(2), // need to hold with 2 hands, cumbersome to carry, just bigger than a backpack
    LARGE(4), // chest, large box, sideboard, needs 2 or more to lift, maybe 1 to drag?
    HUGE(8), // car
    GARGANTUAN(16), // house
    SKYSCRAPER(32); // skyscraper

    public final int volume;
    Size(int volume) {
        this.volume = volume;
    }
}
