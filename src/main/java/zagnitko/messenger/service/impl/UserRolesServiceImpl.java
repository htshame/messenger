package zagnitko.messenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zagnitko.messenger.entity.UserRole;
import zagnitko.messenger.repository.UserRolesRepository;
import zagnitko.messenger.service.UserRolesService;

/**
 * UserRoles service implementation.
 * @author zagnitko.
 */
@Service
@Primary
@Transactional("transactionManager")
public class UserRolesServiceImpl implements UserRolesService {

    @Autowired
    private UserRolesRepository repository;

    @Override
    public void makeUserAdmin(String userToMakeAdmin) {
        int i = 0;
        for (UserRole r : repository.findAll()) {
            if (r.getUsername().equals(userToMakeAdmin) && r.getRole().equals("ROLE_ADMIN")) {
                i++;
            }
        }
        if (i == 0) {
            repository.save(new UserRole(null, userToMakeAdmin, "ROLE_ADMIN"));
        }
    }

    @Override
    public void revertAdminForUser(String userToRevertAdmin, String role) {
        for (UserRole r : repository.findAll()) {
            if (r.getUsername().equals(userToRevertAdmin) && r.getRole().equals(role)) {
                repository.delete(r);
            }
        }
    }
}
