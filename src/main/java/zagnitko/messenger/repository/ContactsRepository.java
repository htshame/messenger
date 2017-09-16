package zagnitko.messenger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zagnitko.messenger.entity.Contact;

/**
 * Contacts repository.
 * @author zagnitko
 */
@Repository
public interface ContactsRepository extends JpaRepository<Contact, Long>, JpaSpecificationExecutor<Contact> {

    @Query("SELECT c FROM Contact c where c.master = :username")
    List<Contact> getUserContacts(@Param("username") String username);

    @Modifying
    @Transactional
    @Query("DELETE FROM Contact c where c.master = :username and c.slave = :userToDelete")
    void deleteContactByUser(@Param("username") String username, @Param("userToDelete") String userToDelete);
}
