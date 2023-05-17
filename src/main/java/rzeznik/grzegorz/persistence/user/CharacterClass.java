package rzeznik.grzegorz.persistence.user;

public enum CharacterClass {
    ARCHER ("Archer"),
    BERSERKER ("berserker"),
    DARK_KNIGHT ("Dark Knight"),
    KUNOICHI ("Kunoichi"),
    LAHN ("Lahn"),
    MAEHWA ("Maehwa"),
    MUSA ("Musa"),
    MYSTIC ("Mystic"),
    NINJA ("Ninja"),
    RANGER ("Ranger"),
    SORCERESS ("Sorceress"),
    STRIKER ("Striker"),
    TAMER ("Tamer"),
    VALKYRIE ("Valkyrie"),
    WARRIOR ("Warrior"),
    WITCH ("Witch"),
    WIZARD ("Wizard"),
    SHAI ("Shai"),
    GUARDIAN ("Guardian"),
    HASHASHIN ("Hashashin"),
    NOVA ("Nova"),
    SAGE ("Sage"),
    CORSAIR ("Corsair");

    private final String name;

    CharacterClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
