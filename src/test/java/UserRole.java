import persistacx.annotation.Entity;
import persistacx.annotation.Field;
import persistacx.annotation.ForeignKey;
import persistacx.annotation.PrimaryKey;

@Entity(name = "stacxpos_user_role")
public class UserRole {
    private String userRoleName;
    private int userRoleId;

    public UserRole() {
    }

    public UserRole(String userRoleName, int userRoleId) {
        this.userRoleName = userRoleName;
        this.userRoleId = userRoleId;
    }

    @Field(name = "user_role_name")
    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    @Field(name = "user_role_id")
    @PrimaryKey
    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }
}
