package zagnitko.messenger.service;

import java.util.List;

import zagnitko.messenger.entity.Message;

/**
 * Messages service.
 * @author zagnitko
 */
public interface MessagesService {

    List<Message> getMessagesByAddresseeName(String username);

    void sendMessage(String username, String addressee, String theme, String text);

    List<Message> getAllMessages();

    void deleteMessageById(Long id);
}
