package mse.ch.tsm_mobop_app;

import org.junit.Assert;
import org.junit.Test;

import mse.ch.tsm_mobop_app.data.ArticleDataModel;
import mse.ch.tsm_mobop_app.data.QuantityType;

public class ArticleDataModelTest {

    @Test
    public void testConstructor(){
        String name = "Test";
        String description = "Test Test Test";
        QuantityType quantityType = QuantityType.PER_KILO;
        double pricePerQuantity = 23.50;
        int uid = 9;


        ArticleDataModel article = new ArticleDataModel(name, description, quantityType, pricePerQuantity, uid);

        Assert.assertEquals(article.getName(), name);
        Assert.assertEquals(article.getDescription(), description);
        Assert.assertEquals(article.getQuantityType(), quantityType);
        Assert.assertEquals(article.getPricePerQty(), pricePerQuantity, 0);
        Assert.assertEquals(article.getUid(), uid);
    }
}
