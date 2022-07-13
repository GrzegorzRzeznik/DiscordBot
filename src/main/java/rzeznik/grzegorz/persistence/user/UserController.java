package rzeznik.grzegorz.persistence.user;

import java.util.List;
import java.util.Optional;

public class UserController {
    private final UserService userService = new UserService();

    public boolean isConnectionValid(){
        return userService.isConnectionValid();
    }

    public List<UserDTO> findAll(){
        return userService.findAll();
    }

    public List<UserDTO> findByClass(CharacterClass characterClass) {return userService.findByClass(characterClass);}

    public void save(UserDTO userDTO){
        userService.save(userDTO);
    }

    public void update(UserDTO userDTO){
        userService.update(userDTO);
    }

    public Optional<UserDTO> findByFamilyName(String familyName){
        return userService.findByFamilyName(familyName);
    }

    public Optional<UserDTO> findByUserId(String userId){
        return userService.findByUserId(userId);
    }

    public void deleteByFamilyName(String familyName){
        userService.deleteByFamilyName(familyName);
    }
}
