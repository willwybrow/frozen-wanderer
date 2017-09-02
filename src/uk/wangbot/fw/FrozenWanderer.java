package uk.wangbot.fw;

import uk.wangbot.fw.character.*;
import uk.wangbot.fw.character.Character;
import uk.wangbot.fw.language.Language;
import uk.wangbot.fw.race.ClassableRace;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 * Created by wjw on 19/08/2017.
 */
public class FrozenWanderer {
    private static Random random = new Random();

    public static void main(String[] args) {
        Character myCharacter = new NPC(ClassableRace.SNOWMAN);
        myCharacter.declareReligion(Religion.ANCIENT);
        myCharacter.chooseClass(PlayerClass.HEALER);

        for (SkillType skillType : myCharacter.getStats().keySet()) {
            System.out.println(String.format("%s: %d", skillType.name(), myCharacter.getStat(skillType)));
        }

        while (!myCharacter.canLevelUp()) {
            int expGained = random.nextInt(1000);
            System.out.println(String.format("Gaining %d exp", expGained));
            myCharacter.gainExperience(expGained);
        }

        myCharacter.levelUp(new Level(SkillType.ATTACK));
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

        Language myLanguage = Language.createRandomLanguage();
        String myWord = myLanguage.generateWord();

        System.out.println(String.format("Your word is %s", myWord.substring(0, 1).toUpperCase() + myWord.substring(1)));

        /*
        Character classlessCharacter = new NPC(ClassableRace.SNOWMAN);
        classlessCharacter.declareReligion(Religion.ANCIENT);

        System.out.println(String.format("Level: %d. Exp needed to next level: %d", classlessCharacter.getLevel(), classlessCharacter.experienceToNextLevel()));
        classlessCharacter.gainExperience(classlessCharacter.experienceToNextLevel());
        System.out.println(String.format("Level: %d. Exp needed to next level: %d", classlessCharacter.getLevel(), classlessCharacter.experienceToNextLevel()));
        classlessCharacter.levelUp(new Level(SkillType.ATTACK));
        System.out.println(String.format("Level: %d", classlessCharacter.getLevel()));

        System.out.println(String.format("Level: %d. Exp needed to next level: %d", classlessCharacter.getLevel(), classlessCharacter.experienceToNextLevel()));
        classlessCharacter.gainExperience(classlessCharacter.experienceToNextLevel());
        System.out.println(String.format("Level: %d. Exp needed to next level: %d", classlessCharacter.getLevel(), classlessCharacter.experienceToNextLevel()));
        classlessCharacter.levelUp(new Level(SkillType.ATTACK));
        System.out.println(String.format("Level: %d", classlessCharacter.getLevel()));

        for (SkillType skillType : classlessCharacter.getStats().keySet()) {
            System.out.println(String.format("%s: %d", skillType.name(), classlessCharacter.getStat(skillType)));
        }
        System.out.println(String.format("Attack damage: %f\nArmour:        %f\nAbility power: %f\nHealth:        %f", classlessCharacter.getAttackDamage(), classlessCharacter.getArmour(), classlessCharacter.getAbilityPower(), classlessCharacter.getMaxHealth()));
        */

        Character attackingCharacter = new NPC(ClassableRace.SNOWMAN);
        Character defendingCharacter = new NPC(ClassableRace.SNOWMAN);

        attackingCharacter.setName("Attacker");
        defendingCharacter.setName("Defender");

        attackingCharacter.declareReligion(Religion.ANCIENT);
        defendingCharacter.declareReligion(Religion.ANCIENT);

        attackingCharacter.gainExperience(attackingCharacter.experienceToNextLevel());
        defendingCharacter.gainExperience(defendingCharacter.experienceToNextLevel());

        attackingCharacter.levelUp(new Level(SkillType.ATTACK));
        defendingCharacter.levelUp(new Level(SkillType.HEALTH));

        attackingCharacter.gainExperience(attackingCharacter.experienceToNextLevel());
        defendingCharacter.gainExperience(defendingCharacter.experienceToNextLevel());

        attackingCharacter.levelUp(new Level(SkillType.ATTACK));
        defendingCharacter.levelUp(new Level(SkillType.DEFENCE));

        printStats(attackingCharacter);
        printStats(defendingCharacter);
        printStatus(attackingCharacter);
        printStatus(defendingCharacter);

        int round = 0;

        while (!attackingCharacter.isDead() && !defendingCharacter.isDead()) {
            System.out.println(String.format("ROUND %d", round));

            System.out.println(String.format("Attacker hits for %f", attackingCharacter.basicAttackCharacter(defendingCharacter)));
            System.out.println(String.format("Defender hits back for %f", defendingCharacter.basicAttackCharacter(attackingCharacter)));

            printStatus(attackingCharacter);
            printStatus(defendingCharacter);

            round += 1;
        }

        if (attackingCharacter.isDead() && !defendingCharacter.isDead()) {
            System.out.println("Defender wins!");
        } else if (!attackingCharacter.isDead() && defendingCharacter.isDead()) {
            System.out.println("Attacker wins!");
        } else {
            System.out.println("Mutual destruction!");
        }

    }

    public static void printStats(Character character) {

        System.out.println(String.format("%s's stats:", character.getName()));

        System.out.println(String.format("Level: %d. Exp needed to next level: %d", character.getLevel(), character.experienceToNextLevel()));
        System.out.println(String.format("Health: %f. Alive? %b", character.getCurrentHealth(), !character.isDead()));

        for (SkillType skillType : character.getStats().keySet()) {
            System.out.println(String.format("%s: %d", skillType.name(), character.getStat(skillType)));
        }
        System.out.println(String.format("Attack damage: %f\nArmour:        %f\nAbility power: %f\nHealth:        %f", character.getAttackDamage(), character.getArmour(), character.getAbilityPower(), character.getMaxHealth()));
    }

    public static void printStatus(Character character) {

        System.out.println(String.format("%s's status:", character.getName()));
        System.out.println(String.format("Health: %f. Alive? %b", character.getCurrentHealth(), !character.isDead()));
    }
}
