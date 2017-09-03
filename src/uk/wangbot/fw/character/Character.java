package uk.wangbot.fw.character;

import uk.wangbot.fw.crafting.EquipmentProfile;
import uk.wangbot.fw.crafting.Item;
import uk.wangbot.fw.crafting.Size;
import uk.wangbot.fw.race.RacialArchetype;
import uk.wangbot.fw.society.Faction;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by wjw on 20/08/2017.
 */
public abstract class Character implements Serializable {
    Logger log = Logger.getLogger(Character.class.getName());
    Random random = new Random();

    private int experience;
    private PlayerClass classType; // no multiclassing!
    private Vector<Level> levels;
    private Religion religion;
    private Faction faction;
    private RacialArchetype race;
    private Vector<StatBlock> effects;
    private EquipmentProfile equipmentProfile; // body type
    private ArrayList<Item> inventory;
    private Size size;
    private Size equipmentSize;
    private double damageTaken;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Character(RacialArchetype race) {
        this.experience = 0;
        this.classType = null;
        this.levels = new Vector<>(10);
        this.religion = null;
        this.race = race;
        this.effects = new Vector<>(10);
        this.equipmentProfile = EquipmentProfile.presetHumanoid();
        this.inventory = new ArrayList<>();
        this.damageTaken = 0;
    }

    private static int xpToLevel(int level) {
        return (int)Math.round(2000 * Math.pow(level, 3));
    }

    public boolean canLevelUp() {
        return levels.size() < 20 && this.religion != null && this.experience >= Character.xpToLevel(levels.size() + 1);
    }

    public int getRawStat(SkillType skillType) {
        int stat = 0;
        for (ListIterator<Level> i = this.levels.listIterator(); i.hasNext();) {
            int levelNumber = i.nextIndex() + 1;
            Level level = i.next();

            SkillType levelledSkill = level.getSkillType();
            if (levelledSkill == skillType) {
                stat += this.race.getStatGrowth();
            }
            if (this.classType != null) {
                SkillType thisLevelAutoSkill = this.classType.autoSkillByLevel(levelNumber);
                if (thisLevelAutoSkill == skillType) {
                    stat += this.race.getStatGrowth();
                }
            }
        }
        return stat;
    }

    public int getStat(SkillType skillType) {
        int stat = getRawStat(skillType);
        for (StatBlock effect : effects) {
            if (effect.getSkillType() == skillType) {
                stat += effect.getModifier();
            }
        }
        return stat;
    }

    public HashMap<SkillType, Integer> getStats() {
        SkillType[] availableSkillTypes = SkillType.class.getEnumConstants();
        HashMap<SkillType, Integer> stats = new HashMap<>(availableSkillTypes.length);
        for (SkillType skillType : availableSkillTypes) {
            stats.put(skillType, this.getStat(skillType));
        }
        return stats;
    }

    public boolean declareReligion(Religion religion) {
        if (this.religion == null) {
            this.religion = religion;
            return (this.religion != null);
        } else {
            return false;
        }
    }

    public boolean chooseClass(PlayerClass characterClass) {
        if (this.classType == null) {
            this.classType = characterClass;
            return (this.classType != null);
        } else {
            return false;
        }
    }

    public boolean levelUp(Level level) {
        if (this.canLevelUp()) {
            this.levels.add(level);
            return true;
        } else {
            return false;
        }
    }

    public int experienceToNextLevel() {
        return xpToLevel(this.levels.size() + 1) - this.experience;
    }

    public void gainExperience(int experience) {
        this.experience += experience;
    }

    public void takeDamage(double damage) {
        this.damageTaken += damage;
    }

    public void healDamage(double healing) {
        this.damageTaken = Math.max(0.0, this.damageTaken - healing);
    }

    public int getLevel() {
        return this.levels.size();
    }

    public double getCurrentHealth() {
        return Math.max(0.0, this.getMaxHealth() - this.damageTaken);
    }

    public double getMaxHealth() {
        return SkillType.HEALTH.baseValue + ((this.levels.size() + this.getStat(SkillType.HEALTH)) * SkillType.HEALTH.growthByLevel);
    }

    public double getHealthRegen() {
        return ((this.levels.size() + this.getStat(SkillType.HEALTH)) * SkillType.HEALTH.growthByLevel);
    }

    public double getAttackDamage() {
        return SkillType.ATTACK.baseValue + ((this.levels.size() + this.getStat(SkillType.ATTACK)) * SkillType.ATTACK.growthByLevel);
    }

    public double getCritDamage() {
        return ((this.levels.size() + this.getStat(SkillType.ATTACK)) * SkillType.ATTACK.growthByLevel);
    }

    public double getArmour() {
        return SkillType.DEFENCE.baseValue + ((this.levels.size() + this.getStat(SkillType.DEFENCE)) * SkillType.DEFENCE.growthByLevel);
    }

    public double getDodgeChance() {
        return ((this.levels.size() + this.getStat(SkillType.DEFENCE)) * SkillType.DEFENCE.growthByLevel);
    }

    public double getAbilityPower() {
        return SkillType.ABILITY.baseValue + ((this.levels.size() + this.getStat(SkillType.ABILITY)) * SkillType.ABILITY.growthByLevel);
    }

    public double getCritChance() {
        return ((this.levels.size() + this.getStat(SkillType.ABILITY)) * SkillType.ABILITY.growthByLevel);
    }


    public boolean isDead() {
        return this.damageTaken >= this.getMaxHealth();
    }

    public double basicAttackCharacter(Character target) {
        double attackDamage = this.getAttackDamage();
        double targetArmour = target.getArmour();

        double critChance = this.getCritChance();

        //System.out.println(String.format("Crit chance: %f", critChance));

        boolean isCrit = (random.nextDouble() * 100.0) < critChance;

        if (isCrit) {
//            System.out.println("Critical hit!");
            double critDamage = this.getCritDamage();
            attackDamage += critDamage;

            if (critChance > 100.0) {
                double megaCritCount = critChance - 100.0;
                while (megaCritCount > 0) {
                    attackDamage += critDamage;
                    megaCritCount -= 100.0;
                }
            }
        }

        double damageDealt = attackDamage * (20.0 / (20.0 + targetArmour));

        double dodgeChance = target.getDodgeChance();

        boolean isDodge = (random.nextDouble() * 100.0) < dodgeChance;

        if (isDodge) {

//            System.out.println("Dodged!");
            damageDealt = 0.0;
        }

        target.takeDamage(damageDealt);

        return damageDealt;
    }
}
