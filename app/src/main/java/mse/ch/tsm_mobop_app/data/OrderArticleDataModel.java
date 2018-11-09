package mse.ch.tsm_mobop_app.data;

import java.io.Serializable;

/**
 * This model is for single article in the order element. It is only used within an order and not
 * alone.
 */
public class OrderArticleDataModel implements Serializable {
    private int articleUid;
    private double quantity;

    public OrderArticleDataModel(){

    }

    public OrderArticleDataModel(int articleUid, double quantity) {
        this.articleUid = articleUid;
        this.quantity = quantity;
    }

    public int getArticleUid() {
        return articleUid;
    }

    public void setArticleUid(int articleUid) {
        this.articleUid = articleUid;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
