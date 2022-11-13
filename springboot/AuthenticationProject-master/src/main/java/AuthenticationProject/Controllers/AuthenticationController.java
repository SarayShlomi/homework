package AuthenticationProject.Controllers;
import AuthenticationProject.Requests.CreateUser;
import AuthenticationProject.Requests.LogIn;
import AuthenticationProject.Services.AuthenticationService;
import AuthenticationProject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@RestController
@RequestMapping("/AuthenticationController")

public class AuthenticationController {
    private static AuthenticationController authenticationController;
    @Autowired
    private  AuthenticationService authenticationService;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z]).{8,20}$");
    private static final Pattern emailPattern = Pattern.compile(".+@.+\\.[a-z]+");

    private AuthenticationController() {

    }
    public boolean authUser(String id, String token) {
        if (token.length() != 18) {
            throw new IllegalArgumentException("the token is not valid");
        }
        return authenticationService.authUser(id, token);
    }


    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody CreateUser U)  {
        return ResponseEntity.ok(AuthenticationService.createUser(U.getName(), U.getEmail(), U.getPassword()));
    }

    @RequestMapping(value = "/logIn", method = RequestMethod.POST)
//    public ResponseEntity<HashMap<String, String>> login(@RequestBody LogIn user){
    public ResponseEntity<HashMap<String,String>> login(@RequestBody LogIn user){

        Matcher matchMail = emailPattern.matcher(user.getEmail());
        Matcher matchPassword = PASSWORD_PATTERN.matcher(user.getPassword());
        boolean emailMatchFound = matchMail.matches();
        boolean passwordMatchFound = matchPassword.matches();
        if (!emailMatchFound) {
            throw new IllegalArgumentException("write email properly example@ex.com");
        }
        if (!passwordMatchFound) {
            throw new IllegalArgumentException("password isn't proper password");
        }
        if (authenticationService.checkIfEmailExists(user.getEmail())) {
            return ResponseEntity.ok(authenticationService.logIn(user.getEmail(), user.getPassword()));
        }
        throw new IllegalArgumentException("user is not registered");
    }

}
