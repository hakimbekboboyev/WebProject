import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@ToString

public class Users{
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String aPassword;
    private boolean isAdmin;
    private boolean isActive;

    public Users(Integer id, String firstname, String lastname, String username, String password, boolean isAdmin, boolean isActive) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }
    public Users(String firstname, String lastname, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }
}
