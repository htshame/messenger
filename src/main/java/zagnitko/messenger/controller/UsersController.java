package zagnitko.messenger.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
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
@Component
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
    @RequestMapping(value = "/changeMyPassword", method = POST, produces = {"application/json"})
    public ResponseEntity<?> changeMyPassword(@RequestParam(value = "password", required = true) String password) {
        try {
            service.changeUsersPassword(
                    SecurityContextHolder.getContext().getAuthentication().getName(), password);
            return new ResponseEntity<Object>("success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error changing password for user [" +
                    SecurityContextHolder.getContext().getAuthentication().getName() + "].");
            e.printStackTrace();
            return new ResponseEntity<Object>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all users for select2 component.
     * @return - List<UserDTO>.
     */
    @RequestMapping(value = "/getAllUsersSelect2", method = GET, produces = {"application/json"})
    public ResponseEntity<?> getAllUsers() {
        try {
            return new ResponseEntity<Object>(service.getAllUsersForSelect2(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting all users.");
            e.printStackTrace();
            return new ResponseEntity<Object>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all users and roles for select2 component.
     * @return - List<UserDTO>.
     */
    @RequestMapping(value = "/getAllUsersSelect2WithRoles", method = GET, produces = {"application/json"})
    public ResponseEntity<?> getAllUsersWithRoles() {
        try {
            return new ResponseEntity<Object>(service.getAllUsersForSelect2WithRoles(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting all users.");
            e.printStackTrace();
            return new ResponseEntity<Object>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Remove user.
     * @param userToDelete - user to remove.
     * @return - responseEntity.
     */
    @RequestMapping(value = "/deleteUser", method = POST, produces = {"application/json"})
    public ResponseEntity<?> deleteUser(@RequestParam(value = "userToDelete", required = true) String userToDelete) {
        try {
            service.deleteUser(userToDelete);
            return new ResponseEntity<Object>("success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting user [" + userToDelete + "].");
            e.printStackTrace();
            return new ResponseEntity<Object>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
