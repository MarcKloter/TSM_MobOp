package mse.ch.tsm_mobop_app.Data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
    DEV utility to maintain the data in the firebase database. This class enables the developer
    to write dump data and reset the database.
 */
public class DatabaseDevUtility {

    private static final String ARTICLE_REFERENCE = "article";

    private FirebaseDatabase database;
    private DatabaseReference articleReference;

    public DatabaseDevUtility(){
        this.database = FirebaseDatabase.getInstance();
        this.articleReference = database.getReference();
    }


    public void resetDatabaseWithDefaultValues() {
        //Reset the database
        articleReference.removeValue();

        //Fill Database with articles.
        for(ArticleDataModel current : this.createDumpArticleData()){
            articleReference.child(ARTICLE_REFERENCE).child("" + current.getUid()).setValue(ArticleDataModelReduced.FromArticleDataModel(current));
        }
    }

    private ArrayList<ArticleDataModel> createDumpArticleData(){
        ArrayList<ArticleDataModel> list = new ArrayList<>();

        //String name, String description, QuantityType quantityType, double pricePerQty, int uid
        list.add(new ArticleDataModel("Suppe" ,"Delicious tomato soup.", QuantityType.PER_PIECE, 5.5 , 5213));
        list.add(new ArticleDataModel("Penne", "Bella Vista Penne Teigwaren", QuantityType.PER_PIECE, 2.8, 5222));
        list.add(new ArticleDataModel("RedBull", "Energy Drink", QuantityType.PER_PIECE, 4.95, 2352 ));
        list.add(new ArticleDataModel("Reis", "Oncle Ben's Reis", QuantityType.PER_KILO, 5.85, 2436));

        return list;
    }
}
