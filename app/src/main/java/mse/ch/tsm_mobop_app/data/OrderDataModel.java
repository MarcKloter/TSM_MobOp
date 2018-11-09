package mse.ch.tsm_mobop_app.data;


import java.io.Serializable;
import java.util.List;

/**
 * Data model for a single order in the database with its UID.
 */
public class OrderDataModel extends OrderDataModelRecuded implements Serializable {
    private int uid;

    public OrderDataModel() {
        super();
    }

    public OrderDataModel(int uid, double totalPrice, String user, String paymentType, List<OrderArticleDataModel> articles) {
        super(totalPrice, user, paymentType, articles);
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
