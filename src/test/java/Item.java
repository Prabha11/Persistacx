import persistacx.annotation.Entity;
import persistacx.annotation.Field;
import persistacx.annotation.ForeignKey;
import persistacx.annotation.PrimaryKey;

@Entity(name = "stacxpos_item")
public class Item {
    private int itemId;
    private float price;
    private String productCode;
    private String name;

    public Item() {
    }

    public Item(int itemId, float price, String productCode, String name) {
        this.itemId = itemId;
        this.price = price;
        this.productCode = productCode;
        this.name = name;
    }

    @Field(name = "item_id")
    @PrimaryKey
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Field(name = "price")
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Field(name = "product_code")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Field(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
