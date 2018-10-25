package mse.ch.tsm_mobop_app.data;

import java.io.Serializable;

/**
 * Data model for a single article in the database with its UID.
 */
public class ArticleDataModel extends ArticleDataModelReduced implements Serializable {
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
