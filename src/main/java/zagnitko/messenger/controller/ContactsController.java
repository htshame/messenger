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

import zagnitko.messenger.service.ContactsService;

/**
 * Contacts controller.
 * @author zagnitko
 */
@RequestMapping("/contacts")
@RestController
@Component
public class ContactsController {

    /**
     * slf4j logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ContactsController.class);

    /**
     * Contacts list.
     */
    @Autowired
    private ContactsService service;

    /**
     * Get contact list for current user for select2.
     * @return List<ContactDTO> - contact's id and username.
     */
    @RequestMapping(value = "/getMyContactsSelect2", method = GET, produces = {"application/json"})
    public ResponseEntity<?> getMyContactsSelect2() {
        try {
            return new ResponseEntity<Object>(service.getUserContactsSelect2(
                    SecurityContextHolder.getContext().getAuthentication().getName()), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting contacts for user [" +
                    SecurityContextHolder.getContext().getAuthentication().getName() + "].");
            e.printStackTrace();
            return new ResponseEntity<Object>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Add user to contacts.
     * @param userToAdd - user to add.
     * @return - responseEntity.
     */
    @RequestMapping(value = "/addUserToContacts", method = POST, produces = {"application/json"})
    public ResponseEntity<?> addUserToContacts(@RequestParam(value = "userToAdd", required = true) String userToAdd) {
        try {
            service.addUserToContacts(SecurityContextHolder.getContext().getAuthentication().getName(), userToAdd);
            return new ResponseEntity<Object>("success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error adding user to [" +
                    SecurityContextHolder.getContext().getAuthentication().getName() + "]'s contact list.");
            e.printStackTrace();
            return new ResponseEntity<Object>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Remove contact from contact list.
     * @param userToDelete - contact to delete.
     * @return - responseEntity.
     */
    @RequestMapping(value = "/deleteContact", method = POST, produces = {"application/json"})
    public ResponseEntity<?> deleteContactByUser(@RequestParam(value = "userToDelete", required = true) String userToDelete) {
        try {
            service.deleteContactByUser(SecurityContextHolder.getContext().getAuthentication().getName(), userToDelete);
            return new ResponseEntity<Object>("success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting contact from [" +
                    SecurityContextHolder.getContext().getAuthentication().getName() + "]'s contact list.");
            e.printStackTrace();
            return new ResponseEntity<Object>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
