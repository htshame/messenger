package zagnitko.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zagnitko.messenger.entity.User;

/**
 * User's repository.
 * @author zagnitko.
 */
@Repository
public interface UsersRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE User u set u.password =:password where u.username =:username")
    void changeUsersPassword(@Param("username") String username, @Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM User u where u.username = :userToDelete")
    void deleteUser(@Param("userToDelete") String userToDelete);
}
