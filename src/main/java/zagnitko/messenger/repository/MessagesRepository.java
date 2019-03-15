package zagnitko.messenger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import zagnitko.messenger.entity.Message;

/**
 * Messages repository.
 * @author zagnitko.
 */
@Repository
public interface MessagesRepository extends JpaRepository<Message, Long>, JpaSpecificationExecutor<Message> {

    @Query(value = "SELECT m FROM Message m WHERE m.addressee = :username")
    List<Message> getMessagesByAddresseeName(@Param("username") String username);
}
