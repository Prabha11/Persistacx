import persistacx.annotation.Entity;
import persistacx.annotation.Field;
import persistacx.annotation.ForeignKey;
import persistacx.annotation.PrimaryKey;


@Entity(name = "stacxpos_bill")
public class Bill {
    private int billId;
    private User user;
    private int billNo;

    public Bill() {
    }

    public Bill(int billId, User user, int billNo) {
        this.billId = billId;
        this.user = user;
        this.billNo = billNo;
    }

    @Field(name = "bill_id")
    @PrimaryKey
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    @ForeignKey(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Field(name = "bill_no")
    public int getBillNo() {
        return billNo;
    }

    public void setBillNo(int billNo) {
        this.billNo = billNo;
    }
}
