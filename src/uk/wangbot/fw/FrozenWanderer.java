package uk.wangbot.fw;

import uk.wangbot.fw.character.*;
import uk.wangbot.fw.character.Character;
import uk.wangbot.fw.language.Language;
import uk.wangbot.fw.race.ClassableRace;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
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
        /*
        HashMap<String, Integer> resultCount = new HashMap<>();
        for (int i = 1; i <= 20; i++) {
            System.out.println(String.format("Simulating level %d", i));
            for (int j = 0; j < 1000; j++) {

                System.out.println(String.format("Iteration %d", j));
                String result = simulateBattle(i, SkillType.ATTACK, SkillType.DEFENCE);
                resultCount.put(result, (resultCount.get(result) == null ? 0 : resultCount.get(result)) + 1);
            }
        }*/
        HashMap<String, Integer> resultCount;

        System.out.println("Attack vs defence level 1");
        resultCount = powerCurveSimulation(1, new SkillType[] {SkillType.ATTACK}, new SkillType[] {SkillType.DEFENCE});

        for (Map.Entry<String, Integer> entry : resultCount.entrySet()) {
            System.out.println(String.format("%s: %d", entry.getKey(), entry.getValue()));
        }

        System.out.println("Attack vs defence level 20");
        resultCount = powerCurveSimulation(20, new SkillType[] {SkillType.ATTACK}, new SkillType[] {SkillType.DEFENCE});

        for (Map.Entry<String, Integer> entry : resultCount.entrySet()) {
            System.out.println(String.format("%s: %d", entry.getKey(), entry.getValue()));
        }

        System.out.println("Attack vs health level 1");
        resultCount = powerCurveSimulation(1, new SkillType[] {SkillType.ATTACK}, new SkillType[] {SkillType.HEALTH});

        for (Map.Entry<String, Integer> entry : resultCount.entrySet()) {
            System.out.println(String.format("%s: %d", entry.getKey(), entry.getValue()));
        }

        System.out.println("Attack vs health level 20");
        resultCount = powerCurveSimulation(20, new SkillType[] {SkillType.ATTACK}, new SkillType[] {SkillType.HEALTH});

        for (Map.Entry<String, Integer> entry : resultCount.entrySet()) {
            System.out.println(String.format("%s: %d", entry.getKey(), entry.getValue()));
        }

        System.out.println("Attack vs health + defence level 1");
        resultCount = powerCurveSimulation(1, new SkillType[] {SkillType.ATTACK}, new SkillType[] {SkillType.DEFENCE, SkillType.HEALTH});

        for (Map.Entry<String, Integer> entry : resultCount.entrySet()) {
            System.out.println(String.format("%s: %d", entry.getKey(), entry.getValue()));
        }

        System.out.println("Attack vs health + defence level 20");
        resultCount = powerCurveSimulation(20, new SkillType[] {SkillType.ATTACK}, new SkillType[] {SkillType.DEFENCE, SkillType.HEALTH});

        for (Map.Entry<String, Integer> entry : resultCount.entrySet()) {
            System.out.println(String.format("%s: %d", entry.getKey(), entry.getValue()));
        }


        /*
        System.out.println("Attack vs health");
        resultCount = powerCurveSimulation(new SkillType[] {SkillType.ATTACK}, new SkillType[] {SkillType.HEALTH});

        for (Map.Entry<String, Integer> entry : resultCount.entrySet()) {
            System.out.println(String.format("%s: %d", entry.getKey(), entry.getValue()));
        }

        System.out.println("Attack vs health+defence");
        resultCount = powerCurveSimulation(new SkillType[] {SkillType.ATTACK}, new SkillType[] {SkillType.HEALTH, SkillType.DEFENCE});

        for (Map.Entry<String, Integer> entry : resultCount.entrySet()) {
            System.out.println(String.format("%s: %d", entry.getKey(), entry.getValue()));
        }
        */
    }

    public static HashMap<String, Integer> powerCurveSimulation(int level, SkillType[] attackerSkills, SkillType[] defenderSkills) {

        HashMap<String, Integer> resultCount = new HashMap<>();

        Character attackingCharacter = new NPC(ClassableRace.SNOWMAN);
        Character defendingCharacter = new NPC(ClassableRace.SNOWMAN);


        attackingCharacter.declareReligion(Religion.ANCIENT);
        defendingCharacter.declareReligion(Religion.ANCIENT);

        int attackerSkillCounter = 0;
        int defenderSkillCounter = 0;

        for (int i = 0; i < level; i++) {
            attackingCharacter.gainExperience(attackingCharacter.experienceToNextLevel());
            attackingCharacter.levelUp(new Level(attackerSkills[attackerSkillCounter % attackerSkills.length]));

            defendingCharacter.gainExperience(defendingCharacter.experienceToNextLevel());
            defendingCharacter.levelUp(new Level(defenderSkills[defenderSkillCounter % defenderSkills.length]));

            attackerSkillCounter += 1;
            defenderSkillCounter += 1;
        }

        for (int j = 0; j < 1000; j++) {
            while (attackingCharacter.getCurrentHealth() < attackingCharacter.getMaxHealth()) {
                attackingCharacter.healDamage(attackingCharacter.getMaxHealth());
            }
            while (defendingCharacter.getCurrentHealth() < defendingCharacter.getMaxHealth()) {
                defendingCharacter.healDamage(defendingCharacter.getMaxHealth());
            }
            while (!attackingCharacter.isDead() && !defendingCharacter.isDead()) {
                attackingCharacter.basicAttackCharacter(defendingCharacter);
                defendingCharacter.basicAttackCharacter(attackingCharacter);
            }
            String winner = "draw";
            if (attackingCharacter.isDead() && defendingCharacter.isDead()) {
                winner = "draw";
            } else if (attackingCharacter.isDead()) {
                winner = "defender";
            } else if (defendingCharacter.isDead()) {
                winner = "attacker";
            }

            resultCount.put(winner, (resultCount.get(winner) == null ? 0 : resultCount.get(winner)) + 1);
        }


        return resultCount;
    }

    public static String simulateBattle(int numberOfLevels, SkillType attackerSkill, SkillType defenderSkill) {

        Character attackingCharacter = new NPC(ClassableRace.SNOWMAN);
        Character defendingCharacter = new NPC(ClassableRace.SNOWMAN);

        attackingCharacter.setName("Attacker");
        defendingCharacter.setName("Defender");

        attackingCharacter.declareReligion(Religion.ANCIENT);
        defendingCharacter.declareReligion(Religion.ANCIENT);

        for (int i = 0; i < numberOfLevels; i++) {

            attackingCharacter.gainExperience(attackingCharacter.experienceToNextLevel());
            defendingCharacter.gainExperience(defendingCharacter.experienceToNextLevel());

            attackingCharacter.levelUp(new Level(attackerSkill));
            defendingCharacter.levelUp(new Level(defenderSkill));
        }

        printStats(attackingCharacter);
        printStats(defendingCharacter);
        printStatus(attackingCharacter);
        printStatus(defendingCharacter);

        int round = 0;
        double damageDealt = 0.0;

        while (!attackingCharacter.isDead() && !defendingCharacter.isDead()) {
//            System.out.println(String.format("ROUND %d", round));
//
            damageDealt = attackingCharacter.basicAttackCharacter(defendingCharacter);
//            System.out.println(String.format("Attacker hits for %f", damageDealt));
            damageDealt = defendingCharacter.basicAttackCharacter(attackingCharacter);
//            System.out.println(String.format("Defender hits back for %f", damageDealt));

//            printStatus(attackingCharacter);
//            printStatus(defendingCharacter);

            round += 1;
        }

        if (attackingCharacter.isDead() && !defendingCharacter.isDead()) {
//            System.out.println("Defender wins!");
            return "Defender";
        } else if (!attackingCharacter.isDead() && defendingCharacter.isDead()) {
//            System.out.println("Attacker wins!");
            return "Attacker";
        } else {
//            System.out.println("Mutual destruction!");
            return "Draw";
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
