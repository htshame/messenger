package zagnitko.messenger.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import zagnitko.messenger.entity.User;
import zagnitko.messenger.service.RegistrationService;

/**
 * Registration controller.
 * @author zagnitko.
 */
@RequestMapping("/registration")
@RestController
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
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> userRegistration(@RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "email") String email) {
        if (service.checkIfExists(username) > 0L) {
            logger.error("User [{}] already exists.", username);
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            service.addUser(new User(username, email, password, name, true));
            logger.info("User [{}] was added successfully.", username);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
    }
}
