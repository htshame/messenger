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

import zagnitko.messenger.service.ContactsService;

/**
 * Contacts controller.
 * @author zagnitko.
 */
@RequestMapping("/contacts")
@RestController
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
    @GetMapping(value = "/getMyContactsSelect2", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getMyContactsSelect2() {
        try {
            return new ResponseEntity<>(service.getUserContactsSelect2(
                    SecurityContextHolder.getContext().getAuthentication().getName()), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting contacts for user [{}].",
                    SecurityContextHolder.getContext().getAuthentication().getName(), e);
            return new ResponseEntity<>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Add user to contacts.
     * @param userToAdd - user to add.
     * @return - responseEntity.
     */
    @PostMapping(value = "/addUserToContacts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> addUserToContacts(@RequestParam(value = "userToAdd") String userToAdd) {
        try {
            service.addUserToContacts(SecurityContextHolder.getContext().getAuthentication().getName(), userToAdd);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error adding user to [{}]'s contact list.",
                    SecurityContextHolder.getContext().getAuthentication().getName(), e);
            return new ResponseEntity<>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Remove contact from contact list.
     * @param userToDelete - contact to delete.
     * @return - responseEntity.
     */
    @PostMapping(value = "/deleteContact", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> deleteContactByUser(@RequestParam(value = "userToDelete") String userToDelete) {
        try {
            service.deleteContactByUser(SecurityContextHolder.getContext().getAuthentication().getName(), userToDelete);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting contact from [{}]'s contact list.",
                    SecurityContextHolder.getContext().getAuthentication().getName(), e);
            return new ResponseEntity<>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
