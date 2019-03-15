package zagnitko.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import zagnitko.messenger.entity.User;

/**
 * Registration repository.
 * @author zagnitko.
 */
@Repository
public interface RegistrationRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query(value = "SELECT COUNT (u) FROM User u WHERE u.username = :username")
    Long checkIfExists(@Param("username") String username);
}
