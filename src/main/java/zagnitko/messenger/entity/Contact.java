package zagnitko.messenger.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Contact entity.
 * @author zagnitko.
 */
@Entity
@Table(name = "CONTACTS")
public class Contact {

    public Contact() {

    }

    public Contact(Long id, String master, String slave) {
        this.id = id;
        this.master = master;
        this.slave = slave;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "master")
    private String master;

    @Column(name = "slave")
    private String slave;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getSlave() {
        return slave;
    }

    public void setSlave(String slave) {
        this.slave = slave;
    }
}
