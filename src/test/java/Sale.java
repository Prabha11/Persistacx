import persistacx.annotation.Entity;
import persistacx.annotation.Field;
import persistacx.annotation.ForeignKey;
import persistacx.annotation.PrimaryKey;

@Entity(name = "stacxpos_sale")
public class Sale {
    private int saleId;
    private Bill bill;
    private Item item;
    private int quantity;
    private float amount;
    private float discount;

    public Sale() {
    }

    public Sale(int saleId, Bill bill, Item item, int quantity, float amount, float discount) {
        this.saleId = saleId;
        this.bill = bill;
        this.item = item;
        this.quantity = quantity;
        this.amount = amount;
        this.discount = discount;
    }

    @Field(name = "sale_id")
    @PrimaryKey
    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    @ForeignKey(name = "bill_id")
    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @ForeignKey(name = "item_id")
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Field(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Field(name = "amount")
    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Field(name = "discount")
    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
