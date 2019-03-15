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

import zagnitko.messenger.service.UserRolesService;

/**
 * User roles controller.
 * @author zagnitko.
 */
@RequestMapping("/userRoles")
@RestController
public class UserRolesController {

    /**
     * slf4j logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UserRolesController.class);

    /**
     * User roles service.
     */
    @Autowired
    private UserRolesService service;

    /**
     * Make user Admin (ROLE_ADMIN).
     * @param userToMakeAdmin - user who is about to become admin.
     * @return - responseEntity.
     */
    @PostMapping(value = "/makeUserAdmin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> makeUserAdmin(@RequestParam(value = "userToMakeAdmin") String userToMakeAdmin) {
        try {
            service.makeUserAdmin(userToMakeAdmin);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error making user [{}] admin.", userToMakeAdmin, e);
            return new ResponseEntity<>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Remove admin role (ROLE_ADMIN).
     * @param userToRevertAdmin - remove this user's role.
     * @return - entityManager.
     */
    @PostMapping(value = "/removeAdminRole", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> removeAdminRole(@RequestParam(value = "userToRevertAdmin") String userToRevertAdmin) {
        try {
            service.revertAdminForUser(userToRevertAdmin, "ROLE_ADMIN");
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting ROLE_ADMIN for user [{}].", userToRevertAdmin, e);
            return new ResponseEntity<>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
