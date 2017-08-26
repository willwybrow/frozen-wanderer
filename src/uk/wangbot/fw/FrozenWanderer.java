package uk.wangbot.fw;

import uk.wangbot.fw.character.*;
import uk.wangbot.fw.character.Character;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by wjw on 19/08/2017.
 */
public class FrozenWanderer {
    private static Random random = new Random();

    public static void main(String[] args) {
        Character myCharacter = new NPC();
        myCharacter.declareReligion(Religion.ANCIENT);
        myCharacter.chooseClass(CharacterClass.HEALER);

        for (SkillType skillType : myCharacter.getStats().keySet()) {
            System.out.println(String.format("%s: %d", skillType.name(), myCharacter.getStat(skillType)));
        }

        while (!myCharacter.canLevelUp()) {
            int expGained = random.nextInt(1000);
            System.out.println(String.format("Gaining %d exp", expGained));
            myCharacter.gainExperience(expGained);
        }

        myCharacter.levelUp(new Level(new PlayerChosenStatBlock(SkillType.ATTACK)));
        try {
            FileOutputStream savedCharacter = new FileOutputStream("myCharacter.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(savedCharacter);
            objectOutputStream.writeObject(myCharacter);
            objectOutputStream.close();
            savedCharacter.close();
        } catch (IOException exception) {
            System.err.print("Exception!");
        }

        for (SkillType skillType : myCharacter.getStats().keySet()) {
            System.out.println(String.format("%s: %d", skillType.name(), myCharacter.getStat(skillType)));
        }
    }
}
