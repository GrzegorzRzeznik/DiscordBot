package rzeznik.grzegorz.persistence.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    boolean isConnectionValid();

    List<User> findAll();

    void save(User user);

    Optional<User> findByFamilyName(String familyName);

    void deleteByFamilyName(String familyName);

    List<User> findByClass(CharacterClass characterClass);

    Optional<User> findByUserId(String userId);

    void update(User user);
}
