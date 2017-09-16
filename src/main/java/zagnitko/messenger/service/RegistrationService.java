package zagnitko.messenger.service;

import zagnitko.messenger.entity.User;

/**
 * Registration service.
 * @author zagnitko
 */
//TODO ����� ���������� � UserService
public interface RegistrationService {

    void addUser(User user);

    Long checkIfExists(String username);
}
