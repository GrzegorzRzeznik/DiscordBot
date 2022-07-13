package rzeznik.grzegorz.recruitment.persistence.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rzeznik.grzegorz.persistence.user.CharacterClass;
import rzeznik.grzegorz.persistence.user.JpaUserRepository;
import rzeznik.grzegorz.persistence.user.User;
import rzeznik.grzegorz.persistence.user.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JpaUserRepositoryTest {
    private final static String FAMILY_NAME = "A";
    private final static String USER_ID = "123456";
    private final static User TEST_USER = new User(USER_ID, FAMILY_NAME, "A", CharacterClass.RANGER, 60, 10, 10, 20, "www.u.pl");

    private final UserRepository userRepository = new JpaUserRepository();


    @BeforeEach @AfterEach
    void addUser(){
        userRepository.deleteByFamilyName(FAMILY_NAME);
    }

    @Test
    void testConnectionToDatabase() {
        //when
        boolean isConnectionValid = userRepository.isConnectionValid();
        //then
        assertTrue(isConnectionValid);
    }

    @Test
    void testSaveUser() {
        userRepository.save(TEST_USER);
        Optional<User> actualUser = userRepository.findByUserId(USER_ID);
        assertTrue(actualUser.isPresent());
        assertEquals(actualUser.get(), TEST_USER);
    }

    @Test
    void testFindByFamilyName(){
        userRepository.save(TEST_USER);
        Optional<User> actualUser = userRepository.findByFamilyName(FAMILY_NAME);
        assertTrue(actualUser.isPresent());
        assertEquals(actualUser.get(), TEST_USER);
    }


}