package uk.wangbot.fw.crafting;

import uk.wangbot.fw.character.PlayerClass;
import uk.wangbot.fw.character.Religion;
import uk.wangbot.fw.character.StatBlock;

import java.io.Serializable;

/**
 * Created by wjw on 02/09/2017.
 */
public abstract class Item implements Serializable {
    private Size size;
    private Material primaryMaterial;
    private Religion religion;
    private PlayerClass playerClass;
    private StatBlock[] enchantments;
    private ItemSlot equippableIn;

    public boolean isEquippable() {
        return this.equippableIn != null;
    }

}
