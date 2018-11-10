package mse.ch.tsm_mobop_app;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import mse.ch.tsm_mobop_app.data.OrderArticleDataModel;
import mse.ch.tsm_mobop_app.data.OrderDataModel;

public class OrderDataModelTest {

    @Test
    public void testConstructor(){
        int articleUid = 99;
        double quantity = 4;

        int uid = 120;
        double totalPrice = 20;
        String user = "Testuser";
        String paymentType = "CreditCard";
        List<OrderArticleDataModel> articles = new ArrayList<>();
        articles.add(new OrderArticleDataModel(articleUid, quantity));

        OrderDataModel order = new OrderDataModel(uid, totalPrice, user, paymentType, articles);

        Assert.assertEquals(order.getUid(), uid);
        Assert.assertEquals(order.getTotalPrice(), totalPrice, 0);
        Assert.assertEquals(order.getUser(), user);
        Assert.assertEquals(order.getPaymentType(), paymentType);
        Assert.assertEquals(order.getArticles().get(0).getArticleUid(), articleUid);
        Assert.assertEquals(order.getArticles().get(0).getQuantity(), quantity, 0);
    }
}
