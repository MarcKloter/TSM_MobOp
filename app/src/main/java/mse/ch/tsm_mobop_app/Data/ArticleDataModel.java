package mse.ch.tsm_mobop_app.Data;

/**
 * Data model for a single article in the database with its UID.
 */
public class ArticleDataModel extends ArticleDataModelReduced {
    private int uid;

    public ArticleDataModel(String name, String description, QuantityType quantityType, double pricePerQty, int uid) {
        super(name, description, quantityType, pricePerQty);
        this.uid = uid;
    }

    public ArticleDataModel() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
