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

import zagnitko.messenger.service.UserRolesService;

/**
 * User roles controller.
 * @author zagnitko
 */
@RequestMapping("/userRoles")
@RestController
@Component
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
    @RequestMapping(value = "/makeUserAdmin", method = POST, produces = {"application/json"})
    public ResponseEntity<?> makeUserAdmin(@RequestParam(value = "userToMakeAdmin", required = true) String userToMakeAdmin) {
        try {
            service.makeUserAdmin(userToMakeAdmin);
            return new ResponseEntity<Object>("success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error making user [" + userToMakeAdmin + "] admin.");
            e.printStackTrace();
            return new ResponseEntity<Object>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Remove admin role (ROLE_ADMIN).
     * @param userToRevertAdmin - remove this user's role.
     * @return - entityManager.
     */
    @RequestMapping(value = "/removeAdminRole", method = POST, produces = {"application/json"})
    public ResponseEntity<?> removeAdminRole(@RequestParam(value = "userToRevertAdmin", required = true) String userToRevertAdmin) {
        try {
            service.revertAdminForUser(userToRevertAdmin, "ROLE_ADMIN");
            return new ResponseEntity<Object>("success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting ROLE_ADMIN for user [" + userToRevertAdmin + "].");
            e.printStackTrace();
            return new ResponseEntity<Object>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
