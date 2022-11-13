package AuthenticationProject.Services;

import AuthenticationProject.User;
import AuthenticationProject.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
@Service
public class AuthenticationService {
//    private static AuthenticationService userRepository;
    @Autowired
    private  UserRepository userRepository;
    public static HashMap<String, String> userTokens = new HashMap<>();


    public static User createUser(String name, String email, String password)  {

            if (UserRepository.checkIfEmailExists(email)) {
                throw new IllegalArgumentException("the user has already registered");
            }
            User user = new User(name, email, password);
            UserRepository.createUser(user);
            return user;
    }
//    public HashMap<String, String> logIn(String email, String password) {
public HashMap<String, String> logIn(String email, String password) {

    String id;
        if (UserRepository.checkIfUserExists(email, password)) {
            id = userRepository.getIdByEmail(email);
        } else {
            throw new IllegalArgumentException("the user is not valid");
        }
        if (userTokens.containsKey(id)) {
            throw new IllegalArgumentException("the user is logged in ");
        }

        String token = createToken();
        userTokens.put(id, token);
        HashMap<String, String> res = new HashMap<>();
        res.put(id, token);

        return res;
//        return true;
    }
    public boolean authUser(String id, String token) {
        for (HashMap.Entry<String, String> entry : userTokens.entrySet()) {
            if (entry.getKey().equals(id)) {
                return entry.getValue().equals(token);
            }
        }
        return false;
    }
    public boolean authUser(String token) {
        for (HashMap.Entry<String, String> entry : userTokens.entrySet()) {
            if (entry.getKey().equals(token)) {
                return entry.getValue().equals(token);
            }
        }
        return false;
    }
    public void deleteUserFromMap(String id) {
        userTokens.remove(id);
    }
    private static String getSaltString(int stringLength) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        while (salt.length() < stringLength) {
            int index = (int) (ThreadLocalRandom.current().nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }
    public boolean checkIfEmailExists(String email) {
        return userRepository.checkIfEmailExists(email);
    }
    public static String createToken() {
        return getSaltString(18);
    }
    public static String getId(String token){
        for (HashMap.Entry<String, String> entry : userTokens.entrySet()) {
            if (entry.getValue().equals(token)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationService that = (AuthenticationService) o;
        return Objects.equals(userRepository, that.userRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRepository);
    }
}