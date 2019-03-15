package zagnitko.messenger.dto;

/**
 * Contact DTO.
 * @author zagnitko.
 */
public class ContactDTO {

    public ContactDTO() {

    }

    public ContactDTO(String id, String text) {
        this.id = id;
        this.text = text;
    }

    private String id;

    private String master;

    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
