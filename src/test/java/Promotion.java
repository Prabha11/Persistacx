import persistacx.annotation.Entity;
import persistacx.annotation.Field;
import persistacx.annotation.ForeignKey;
import persistacx.annotation.PrimaryKey;

@Entity(name = "stacxpos_promotion")
public class Promotion {
    private int PromotionId;
    private Item item;
    private String description;
    private float percentage;
    private float maximumAmount;

    public Promotion() {
    }

    public Promotion(int promotionId, Item item, String description, float percentage, float maximumAmount) {
        PromotionId = promotionId;
        this.item = item;
        this.description = description;
        this.percentage = percentage;
        this.maximumAmount = maximumAmount;
    }

    @Field(name = "promotion_id")
    @PrimaryKey
    public int getPromotionId() {
        return PromotionId;
    }

    public void setPromotionId(int promotionId) {
        PromotionId = promotionId;
    }

    @ForeignKey(name = "item_id")
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Field(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Field(name = "percentage")
    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    @Field(name = "maximum_amount")
    public float getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(float maximumAmount) {
        this.maximumAmount = maximumAmount;
    }
}
