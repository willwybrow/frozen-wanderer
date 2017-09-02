package uk.wangbot.fw.crafting;

import uk.wangbot.fw.character.PlayerClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wjw on 02/09/2017.
 */
public class EquipmentProfile {

    private HashMap<ItemSlot, Item[]> slots;

    public HashMap<ItemSlot, Item[]> getSlots() {
        return slots;
    }

    //private ArrayList<ItemSlot> slots;

    public EquipmentProfile() {
        this.slots = new HashMap<>();
    }

    public void addItemSlot(ItemSlot itemSlot, int howMany) {
        this.slots.put(itemSlot, new Item[howMany]);
    }

    public void addItemSlot(ItemSlot itemSlot) {
        this.addItemSlot(itemSlot, 1);
    }

    public static EquipmentProfile presetHumanoid() {
        EquipmentProfile equipmentProfile = new EquipmentProfile();
        equipmentProfile.addItemSlot(ItemSlot.HEAD);
        equipmentProfile.addItemSlot(ItemSlot.NECK);
        equipmentProfile.addItemSlot(ItemSlot.SHOULDERS);
        equipmentProfile.addItemSlot(ItemSlot.CHEST);
        equipmentProfile.addItemSlot(ItemSlot.BACK);
        equipmentProfile.addItemSlot(ItemSlot.HAND, 2);
        equipmentProfile.addItemSlot(ItemSlot.BELT);
        equipmentProfile.addItemSlot(ItemSlot.LEGS);
        equipmentProfile.addItemSlot(ItemSlot.FEET);
        equipmentProfile.addItemSlot(ItemSlot.FINGER);
        equipmentProfile.addItemSlot(ItemSlot.WRIST);

        return equipmentProfile;
    }

    public ArrayList<Item> getAllEquippedItems() {
        ArrayList<Item> equippedItems = new ArrayList<>();
        for (Map.Entry<ItemSlot, Item[]> entry : slots.entrySet()) {
            for (int i = 0; i < entry.getValue().length; i++) {
                if (entry.getValue()[i] != null) {
                    equippedItems.add(entry.getValue()[i]);
                }
            }
        }
        return equippedItems;
    }
}
