package zagnitko.messenger.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * User's role entity.
 * @author zagnitko.
 */
@Entity
@Table(name = "USER_ROLES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRole {

    public UserRole() {

    }

    public UserRole(Long userRoleId, String username, String role) {
        this.userRoleId = userRoleId;
        this.username = username;
        this.role = role;
    }

    @Id
    @Column(name = "user_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleId;

    @Column(name = "username")
    private String username;

    @Column(name = "role")
    private String role;

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
