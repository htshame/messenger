package zagnitko.messenger.service;

import java.util.List;

import zagnitko.messenger.dto.ContactDTO;

/**
 * Contacts service.
 * @author zagnitko.
 */
public interface ContactsService {

    List<ContactDTO> getUserContactsSelect2(String username);

    void addUserToContacts(String username, String userToAdd);

    void deleteContactByUser(String username, String userToDelete);
}
