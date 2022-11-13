package AuthenticationProject.Controllers;

import AuthenticationProject.Requests.UpdateEmail;
import AuthenticationProject.Requests.UpdateName;
import AuthenticationProject.Requests.UpdatePassword;
import AuthenticationProject.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.*;
@RestController
@RequestMapping("/UserController")
public class UserController {

    private static UserController userController;
    private static UserService userService;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z]).{8,20}$");
    private static final Pattern emailPattern = Pattern.compile(".+@.+\\.[a-z]+");
    @Autowired
    private AuthenticationService authenticationService;
    private UserController() {
    }
    public boolean authenticateUser(String id, String token) {
        return authenticationService.authUser(id, token);
    }
    public boolean authenticateUser(String token) {
        return authenticationService.authUser(token);
    }
    public boolean validateEmail(String email) {
        Matcher m = emailPattern.matcher(email);
        boolean matchFound = m.matches();
        if (matchFound) {
            if (userService.checkIfEmailExists(email)) {
                throw new IllegalArgumentException("You cant change the email to the same one");
            }
            return true;
        }
        return false;
    } //get
    public boolean validatePassword(String password) {
        Matcher m = PASSWORD_PATTERN.matcher(password);
        boolean matchFound = m.matches();
        if (matchFound) {
            return true;
        }
        return false;
    }




    @RequestMapping(value = "filter", method = RequestMethod.DELETE)
//    public ResponseEntity<Void> deleteUser(@RequestParam("id") String id, @RequestParam("token") String token) {
//    public ResponseEntity<Void> deleteUser(@RequestParam("id") String id) {
        public ResponseEntity<Void> deleteUser(@RequestParam String id) {

//        if (authenticateUser(id, token)) {
            authenticationService.deleteUserFromMap(id);
            UserService.deleteUser(id);
            return ResponseEntity.noContent().build();
//        } else {
//            throw new IllegalStateException("The user was not authenticated");
//        }
    }


    @RequestMapping(value = "/updateName", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateUserName(@RequestHeader String token, @RequestBody UpdateName name) {
        if (authenticateUser(AuthenticationService.getId(token), token)) {
            userService.updateUserName(AuthenticationService.getId(token), name.getName());
            return ResponseEntity.status(HttpStatus.OK).body(" your name was update");
        }
        throw new IllegalStateException("The user was not authenticated");
    }
    @RequestMapping(value = "/updatePassword", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateUserPassword(@RequestHeader String token, @RequestBody UpdatePassword Password) {
        if (authenticateUser(token)) {
            if (validatePassword(Password.getPassword())) {
                userService.updatePassword(AuthenticationService.getId(token), Password.getPassword());
                return ResponseEntity.status(HttpStatus.OK).body(" your password was update");
            } else {
                throw new IllegalArgumentException("Invalid password inserted");
            }
        }
        throw new IllegalStateException("The user was not authenticated");
    }
    @RequestMapping(value = "/updateEmail", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateUserEmail(@RequestHeader String token, @RequestBody UpdateEmail email) {
        if (authenticateUser(token)) {
            userService.updateEmail(AuthenticationService.getId(token), email.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(" your email was update");
        }
        throw new IllegalStateException("The user was not authenticated");
    }

}
