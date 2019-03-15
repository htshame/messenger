package zagnitko.messenger.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import zagnitko.messenger.service.UserService;

/**
 * User's controller.
 * @author zagnitko
 */
@RequestMapping("/users")
@RestController
public class UsersController {

    /**
     * slf4j logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    /**
     * User service.
     */
    @Autowired
    private UserService service;

    /**
     * Change current user's password.
     * @param password - new password.
     * @return - entityManager.
     */
    @PostMapping(value = "/changeMyPassword",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> changeMyPassword(@RequestParam(value = "password") String password) {
        try {
            service.changeUsersPassword(
                    SecurityContextHolder.getContext().getAuthentication().getName(), password);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error changing password for user [" +
                    SecurityContextHolder.getContext().getAuthentication().getName() + "].", e);

            return new ResponseEntity<>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all users for select2 component.
     * @return - List<UserDTO>.
     */
    @GetMapping(value = "/getAllUsersSelect2", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getAllUsers() {
        try {
            return new ResponseEntity<>(service.getAllUsersForSelect2(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting all users.", e);
            return new ResponseEntity<>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all users and roles for select2 component.
     * @return - List<UserDTO>.
     */
    @GetMapping(value = "/getAllUsersSelect2WithRoles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getAllUsersWithRoles() {
        try {
            return new ResponseEntity<>(service.getAllUsersForSelect2WithRoles(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting all users.", e);
            return new ResponseEntity<>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Remove user.
     * @param userToDelete - user to remove.
     * @return - responseEntity.
     */
    @PostMapping(value = "/deleteUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> deleteUser(@RequestParam(value = "userToDelete") String userToDelete) {
        try {
            service.deleteUser(userToDelete);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting user [{}].", userToDelete, e);
            return new ResponseEntity<>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
