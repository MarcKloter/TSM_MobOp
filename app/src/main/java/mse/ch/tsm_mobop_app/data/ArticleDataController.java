package mse.ch.tsm_mobop_app.data;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Data Manager for Articles. CRUD operations for all articles in the database.
 * Singleton.
 */
public class ArticleDataController {
    private static final String ARTICLE_REFERENCE = "article";
    private static ArticleDataController instance;

    private FirebaseDatabase database;
    private DatabaseReference articleReference;

    public static ArticleDataController getInstance(){
        if(instance == null){
            instance = new ArticleDataController();
        }
        return instance;
    }

    private ArticleDataController(){
        this.database = FirebaseDatabase.getInstance();
        this.articleReference = database.getReference(ARTICLE_REFERENCE);
    }

    public void getArticleById(final int uid, final OnDataReceivedEventListener callbackListener) throws ArticleNotAvailableException{
        try {
            articleReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    ArticleDataModelReduced reducedModel = dataSnapshot.child("" + uid).getValue(ArticleDataModelReduced.class);
                    ArticleDataModel article = new ArticleDataModel();
                    if(reducedModel != null){
                        article = ArticleDataModelReduced.toArticleDataModel(reducedModel, uid);
                    }
                    callbackListener.onDataReceived(article);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    //do nothing. Would need to create new error handling with own exception
                }
            });
        }
        catch (Exception ex){
            throw new ArticleNotAvailableException(ex.getMessage());
        }
    }

    public void saveArticleById(ArticleDataModel article){
        articleReference.child("" + article.getUid()).setValue(
                ArticleDataModelReduced.fromArticleDataModel(article));
    }
}
