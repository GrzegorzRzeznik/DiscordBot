package rzeznik.grzegorz.persistence.user;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class User {

    @Id
    private String userId;
    private String familyName;
    private String characterName;
    @Enumerated(EnumType.STRING)
    private CharacterClass characterClass;
    private int level;
    private int ap;
    private int aap;
    private int dp;
    private String gearScreenshotURL;

    public User() {
    }

    public User(String userId, String familyName, String characterName, CharacterClass characterClass, int level, int ap, int aap, int dp, String gearScreenshotURL) {
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

    public UserDTO toDTO() {
        return new UserDTO(userId, familyName, characterName, characterClass, level, ap, aap, dp, gearScreenshotURL);
    }

    public static User applyDTO(UserDTO dto) {
        return new User(dto.getUserId(),
                dto.getFamilyName(),
                dto.getCharacterName(),
                dto.getCharacterClass(),
                dto.getLevel(),
                dto.getAp(),
                dto.getAap(),
                dto.getDp(),
                dto.getGearScreenshotURL());
    }

    @Override
    public String toString() {
        return "User{" +
                "familyName='" + familyName + '\'' +
                ", characterName='" + characterName + '\'' +
                ", characterClass=" + characterClass +
                ", level=" + level +
                ", ap=" + ap +
                ", aap=" + aap +
                ", dp=" + dp +
                ", gearScreenshotURL='" + gearScreenshotURL + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getLevel() == user.getLevel() && getAp() == user.getAp() && getAap() == user.getAap() && getDp() == user.getDp() && getFamilyName().equals(user.getFamilyName()) && getCharacterName().equals(user.getCharacterName()) && getCharacterClass() == user.getCharacterClass() && Objects.equals(getGearScreenshotURL(), user.getGearScreenshotURL());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFamilyName(), getCharacterName(), getCharacterClass(), getLevel(), getAp(), getAap(), getDp(), getGearScreenshotURL());
    }

}
