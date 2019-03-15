package zagnitko.messenger.service;

import java.util.List;

import zagnitko.messenger.dto.UserDTO;

/**
 * User's service.
 * @author zagnitko.
 */
public interface UserService {

    void changeUsersPassword(String username, String password);

    List<UserDTO> getAllUsersForSelect2();

    List<UserDTO> getAllUsersForSelect2WithRoles();

    void deleteUser(String userToDelete);
}
