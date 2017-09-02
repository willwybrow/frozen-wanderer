package uk.wangbot.fw.society;

/**
 * Created by wjw on 26/08/2017.
 */
public class InterFactionalRelationship {
    public Faction[] getParties() {
        return parties;
    }

    public DiplomaticPosition getDiplomaticPosition() {
        return diplomaticPosition;
    }

    private Faction[] parties;
    private DiplomaticPosition diplomaticPosition;
    public InterFactionalRelationship(Faction[] parties, DiplomaticPosition diplomaticPosition) {
        this.parties = parties;
        this.diplomaticPosition = diplomaticPosition;
    }
}
