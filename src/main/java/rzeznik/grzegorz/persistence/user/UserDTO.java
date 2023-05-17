package rzeznik.grzegorz.persistence.user;


public class UserDTO {

    private String userId;
    private String familyName;
    private String characterName;
    private CharacterClass characterClass;
    private int level;
    private int ap;
    private int aap;
    private int dp;
    private String gearScreenshotURL;

    public UserDTO() {
    }

    public UserDTO(String userId, String familyName, String characterName, CharacterClass characterClass, int level, int ap, int aap, int dp, String gearScreenshotURL) {
        this.userId = userId;
        this.familyName = familyName;
        this.characterName = characterName;
        this.characterClass = characterClass;
        this.level = level;
        this.ap = ap;
        this.aap = aap;
        this.dp = dp;
        this.gearScreenshotURL = gearScreenshotURL;
    }

    public String getUserId() {
        return userId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public int getLevel() {
        return level;
    }

    public int getAp() {
        return ap;
    }

    public int getAap() {
        return aap;
    }

    public int getDp() {
        return dp;
    }

    public String getGearScreenshotURL() {
        return gearScreenshotURL;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public void setAap(int aap) {
        this.aap = aap;
    }

    public void setDp(int dp) {
        this.dp = dp;
    }

    public void setGearScreenshotURL(String gearScreenshotURL) {
        this.gearScreenshotURL = gearScreenshotURL;
    }

    @Override
    public String toString() {
        return  "Family Name: " + familyName +
                "\nCharacter Name: " + characterName +
                "\nClass: " + characterClass.toString() +
                "\nLevel: " + level +
                "\nAP: " + ap +
                "\nAAP: " + aap +
                "\nDP: " + dp +
                "\nGear: " + gearScreenshotURL;
    }
}
