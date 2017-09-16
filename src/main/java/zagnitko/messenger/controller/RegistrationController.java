package zagnitko.messenger.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import zagnitko.messenger.entity.User;
import zagnitko.messenger.service.RegistrationService;

/**
 * Registration controller.
 * @author zagnitko
 */
//TODO ����� ���������� � UsersController.
@RequestMapping("/registration")
@RestController
@Component
public class RegistrationController {

    /**
     * slf4j logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    /**
     * Registration service.
     */
    @Autowired
    private RegistrationService service;

    /**
     * Register user.
     * @param username - username.
     * @param password - password.
     * @param name - user's name.
     * @param email - user's email.
     * @return - entityManager.
     */
    @RequestMapping(value = "/register", method = POST, produces = {"application/json"})
    public ResponseEntity<?> userRegistration(@RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "email", required = true) String email) {
        if (service.checkIfExists(username) > 0L) {
            logger.error("User [" + username + "] already exists.");
            return new ResponseEntity<Object>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            service.addUser(new User(username, email, password, name, true));
            logger.info("User [" + username + "] was added successfully.");
            return new ResponseEntity<Object>("success", HttpStatus.OK);
        }
    }
}
