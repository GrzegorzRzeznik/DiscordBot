package rzeznik.grzegorz.persistence.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository userRepository = new JpaUserRepository();

    public boolean isConnectionValid() {
        return userRepository.isConnectionValid();
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(User::toDTO).collect(Collectors.toList());
    }

    public List<UserDTO> findByClass(CharacterClass characterClass) {
        return userRepository.findByClass(characterClass).stream().map(User::toDTO).collect(Collectors.toList());
    }

    public void save(UserDTO user) {
        userRepository.save(User.applyDTO(user));
    }

    public void update(UserDTO userDTO){
        userRepository.update(User.applyDTO(userDTO));
    }

    public Optional<UserDTO> findByFamilyName(String familyName) {
        Optional<User> user = userRepository.findByFamilyName(familyName);
        return user.map(User::toDTO);
    }

    public Optional<UserDTO> findByUserId(String userId) {
        Optional<UserDTO> userDTO = Optional.empty();
        if (userRepository.findByUserId(userId).isPresent()) {
            userDTO = Optional.of(userRepository.findByUserId(userId).get().toDTO());
        }
        return userDTO;
    }

    public void deleteByFamilyName(String familyName) {
        userRepository.deleteByFamilyName(familyName);
    }
}
