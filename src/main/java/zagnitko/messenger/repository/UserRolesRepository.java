package zagnitko.messenger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zagnitko.messenger.entity.UserRole;

/**
 * User's roles repository.
 * @author zagnitko
 */
@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, Long>, JpaSpecificationExecutor<UserRole> {

    @Query(name="SELECT r from UserRole r where r.username = :username")
    List<UserRole> getUserRoleByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(name = "DELETE FROM UserRole r where r.username = :userToDelete")
    void deleteUserRolesByUsername(@Param("userToDelete") String userToDelete);

    //TODO crutch.! because there's a strange error with removing admin role
    /*@Modifying
    @Transactional
    @Query(name = "DELETE FROM UserRole r where r.username = :userToDelete and r.role = :role")
    void deleteAdminRolesByUsername(@Param("userToDelete") String userToDelete, @Param("role") String role);*/
}
