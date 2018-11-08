package mse.ch.tsm_mobop_app.data;

import java.io.Serializable;
import java.util.List;

/**
 *  Data model for a single order in the database WITHOUT UID.
 */
public class OrderDataModelRecuded implements Serializable {

    private double totalPrice;
    private String user;
    private String paymentType;
    private List<OrderArticleDataModel> articles;

    public OrderDataModelRecuded(){

    }

    public OrderDataModelRecuded(double totalPrice, String user, String paymentType, List<OrderArticleDataModel> articles) {
        this.totalPrice = totalPrice;
        this.user = user;
        this.paymentType = paymentType;
        this.articles = articles;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public List<OrderArticleDataModel> getArticles() {
        return articles;
    }

    public void setArticles(List<OrderArticleDataModel> articles) {
        this.articles = articles;
    }
}
