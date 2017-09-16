package zagnitko.messenger.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

import zagnitko.messenger.service.MessagesService;

/**
 * Messages controller.
 * @author zagnitko
 */
@RequestMapping("/messages")
@RestController
@Component
public class MessagesController {

    /**
     * slf4j logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(MessagesController.class);

    /**
     * Messages service.
     */
    @Autowired
    private MessagesService service;

    /**
     * Get user's messages.
     * @return - List<Message>.
     */
    @RequestMapping(value = "/getMyMessages", method = GET)
    public ResponseEntity<?> getUserMessages() {
        try {
            return new ResponseEntity<Object>(service.getMessagesByAddresseeName(
                    SecurityContextHolder.getContext().getAuthentication().getName()), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting messages for [" +
                    SecurityContextHolder.getContext().getAuthentication().getName() + "].");
            e.printStackTrace();
            return new ResponseEntity<Object>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all messages.
     * @return - List<Message>.
     */
    @RequestMapping(value = "/getAllMessages", method = GET)
    public ResponseEntity<?> getAllMessages() {
        try {
            return new ResponseEntity<Object>(service.getAllMessages(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting all messages.");
            e.printStackTrace();
            return new ResponseEntity<Object>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Send message.
     * @param addressee - user to send to.
     * @param theme - message theme.
     * @param text - message text.
     * @return - responseEntity.
     */
    @RequestMapping(value = "/sendMessage", method = POST)
    public ResponseEntity<?> sendMessage(@RequestParam(value = "addressee", required = true) String addressee,
            @RequestParam(value = "theme", required = false) String theme,
            @RequestParam(value = "text", required = false) String text) {
        try {
            service.sendMessage(SecurityContextHolder.getContext().getAuthentication().getName(), addressee, theme, text);
            return new ResponseEntity<Object>("success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error sending message from [" +
                    SecurityContextHolder.getContext().getAuthentication().getName() + "].");
            e.printStackTrace();
            return new ResponseEntity<Object>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete message.
     * @param id - message id to delete.
     * @return - responseEntity.
     */
    @RequestMapping(value = "/deleteMessageById", method = POST)
    public ResponseEntity<?> deleteMessageById(@RequestParam(value = "id", required = true) Long id) {
        try {
            service.deleteMessageById(id);
            return new ResponseEntity<Object>("success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting message with id [" + id + "].");
            e.printStackTrace();
            return new ResponseEntity<Object>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
