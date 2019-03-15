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

import zagnitko.messenger.service.MessagesService;

/**
 * Messages controller.
 * @author zagnitko.
 */
@RequestMapping("/messages")
@RestController
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
    @GetMapping(value = "/getMyMessages", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getUserMessages() {
        try {
            return new ResponseEntity<>(service.getMessagesByAddresseeName(
                    SecurityContextHolder.getContext().getAuthentication().getName()), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting messages for [{}].",
                    SecurityContextHolder.getContext().getAuthentication().getName(), e);
            return new ResponseEntity<>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all messages.
     * @return - List<Message>.
     */
    @GetMapping(value = "/getAllMessages", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getAllMessages() {
        try {
            return new ResponseEntity<>(service.getAllMessages(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting all messages.", e);
            return new ResponseEntity<>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Send message.
     * @param addressee - user to send to.
     * @param theme - message theme.
     * @param text - message text.
     * @return - responseEntity.
     */
    @PostMapping(value = "/sendMessage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> sendMessage(@RequestParam(value = "addressee") String addressee,
            @RequestParam(value = "theme", required = false) String theme,
            @RequestParam(value = "text", required = false) String text) {
        try {
            service.sendMessage(SecurityContextHolder.getContext().getAuthentication().getName(), addressee, theme,
                    text);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error sending message from [{}].",
                    SecurityContextHolder.getContext().getAuthentication().getName(), e);
            return new ResponseEntity<>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete message.
     * @param id - message id to delete.
     * @return - responseEntity.
     */
    @PostMapping(value = "/deleteMessageById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> deleteMessageById(@RequestParam(value = "id") Long id) {
        try {
            service.deleteMessageById(id);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting message with id [{}].", id, e);
            return new ResponseEntity<>("Houston, we have a problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
