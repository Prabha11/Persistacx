import persistacx.annotation.Entity;
import persistacx.annotation.Field;
import persistacx.annotation.ForeignKey;
import persistacx.annotation.PrimaryKey;

@Entity(name = "stacxpos_user")
public class User {

    private int userId;
    private String userName;
    private String password;
    private UserRole userRole;

    public User() {
    }

    public User(int userId, String userName, String password, UserRole userRole) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
    }

    @Field(name = "user_id")
    @PrimaryKey
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Field(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Field(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ForeignKey(name = "user_role_id")
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
