package mse.ch.tsm_mobop_app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import mse.ch.tsm_mobop_app.activities.PurchaseActivity;
import mse.ch.tsm_mobop_app.cart.CartItem;
import mse.ch.tsm_mobop_app.data.ArticleDataModel;
import mse.ch.tsm_mobop_app.data.QuantityType;

public class PurchaseActivityTest {

    private ArticleDataModel article;
    private CartItem cartItem;

    @Before
    public void Setup(){
        this.article = new ArticleDataModel("Test", "Test Test", QuantityType.PER_KILO, 23.50, 9);
    }

    @Test
    public void testConvertFromArticleDataModel(){
        PurchaseActivity purchaseActivity = new PurchaseActivity();
        this.cartItem = purchaseActivity.convertFromArticleDataModel(article);
        Assert.assertEquals(article.getName(), cartItem.getLabel());
        Assert.assertEquals(article.getDescription(), cartItem.getDescription());
        Assert.assertEquals(article.getPricePerQty(), cartItem.getPrice().doubleValue(), 0);
        Assert.assertEquals(article.getUid(), Integer.parseInt(cartItem.getId()));
    }
}
