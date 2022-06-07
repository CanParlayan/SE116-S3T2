package Objects;

public class PlayableHero {
    private static int intelligence;
    private static int strength;
    private static int vitality;
    private static String playableHeroClass;
    private static int playableHeroCurrentDamage;

    public PlayableHero(int intelligence, int strength, int vitality, String playableHeroClass) {
        PlayableHero.intelligence = intelligence;
        PlayableHero.strength = strength;
        PlayableHero.vitality = vitality;
        PlayableHero.playableHeroClass = playableHeroClass;
    }

    public static int getIntelligence() {
        return intelligence;
    }

    public static void setIntelligence(int intelligence) {
        PlayableHero.intelligence = intelligence;
    }

    public static int getStrength() {
        return strength;
    }

    public static void setStrength(int strength) {
        PlayableHero.strength = strength;
    }

    public static int getVitality() {
        return vitality;
    }

    public static void setVitality(int vitality) {
        PlayableHero.vitality = vitality;
    }

    public static String getPlayableHeroClass() {
        return playableHeroClass;
    }

    public static void setPlayableHeroClass(String playableHeroClass) {
        PlayableHero.playableHeroClass = playableHeroClass;
    }
}

   /* public static int getPlayableHeroCurrentDamage() {
        return playableHeroCurrentDamage;
    }
        public static void setPlayableHeroCurrentDamage(int damage) {
            playableHeroCurrentDamage = (int)(Weapon.getDamage()* PlayableHero.getIntelligence()); Why are all these static anyway?

        }
    }*/