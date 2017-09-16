package zagnitko.messenger.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zagnitko.messenger.dto.UserDTO;
import zagnitko.messenger.entity.User;
import zagnitko.messenger.entity.UserRole;
import zagnitko.messenger.repository.UserRolesRepository;
import zagnitko.messenger.repository.UsersRepository;
import zagnitko.messenger.service.UserService;

/**
 * Users service implelentation.
 * @author zagnitko
 */
@Service
public class UsersServiceImpl implements UserService {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    public void changeUsersPassword(String username, String password) {
        repository.changeUsersPassword(username, password);
    }

    public List<UserDTO> getAllUsersForSelect2() {
        List<User> userList = repository.findAll();
        List<UserDTO> retList = new ArrayList<>();
        for (User user : userList) {
            retList.add(new UserDTO(user.getUsername() + ", " + user.getName(), user.getUsername()));
        }
        return retList;
    }

    public List<UserDTO> getAllUsersForSelect2WithRoles() {
        List<User> userList = repository.findAll();
        List<UserDTO> retList = new ArrayList<>();
        for (User user : userList) {
            String userRoles = "";
            for (UserRole urole : userRolesRepository.getUserRoleByUsername(user.getUsername())) {
                userRoles += urole.getRole() + "; ";
            }
            retList.add(new UserDTO(user.getUsername() + "; " + user.getName() + "; " + userRoles, user.getUsername()));
        }
        return retList;
    }

    public void deleteUser(String userToDelete) {
        userRolesRepository.deleteUserRolesByUsername(userToDelete);
        repository.deleteUser(userToDelete);
    }
}
