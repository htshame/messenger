package zagnitko.messenger.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zagnitko.messenger.entity.Message;
import zagnitko.messenger.repository.MessagesRepository;
import zagnitko.messenger.service.MessagesService;

/**
 * Messages service implementation.
 * @author zagnitko
 */
@Service
@Primary
@Transactional("transactionManager")
public class MessagesServiceImpl implements MessagesService {

    @Autowired
    private MessagesRepository repository;

    @Override
    public List<Message> getMessagesByAddresseeName(String username) {
        return repository.getMessagesByAddresseeName(username);
    }

    @Override
    public void sendMessage(String username, String addressee, String theme, String text) {
        repository.save(new Message(null, username, addressee, theme, text, new Timestamp((new Date()).getTime())));
    }

    @Override
    public List<Message> getAllMessages() {
        return repository.findAll();
    }

    @Override
    public void deleteMessageById(Long id) {
        repository.delete(id);
    }
}
