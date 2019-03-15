package zagnitko.messenger.service;

import zagnitko.messenger.entity.User;

/**
 * Registration service.
 * @author zagnitko.
 */
public interface RegistrationService {

    void addUser(User user);

    Long checkIfExists(String username);
}
