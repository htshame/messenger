package zagnitko.messenger.dto;

/**
 * User DTO.
 * @author zagnitko
 *
 */
public class UserDTO {

    public UserDTO() {

    }

    public UserDTO(String text, String id) {
        this.text = text;
        this.id = id;
    }

    private String username;

    private String name;

    private String text;

    private String id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
