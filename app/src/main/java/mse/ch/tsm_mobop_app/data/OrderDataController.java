package mse.ch.tsm_mobop_app.data;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Data Manager for Orders. CRUD operations for all orders in the database.
 * Singleton.
 */
public class OrderDataController {
    private static final String ORDER_REFERENCE = "order";
    private static OrderDataController instance;

    private FirebaseDatabase database;
    private DatabaseReference orderReference;

    public static OrderDataController getInstance(){
        if(instance == null){
            instance = new OrderDataController();
        }
        return instance;
    }

    private OrderDataController(){
        this.database = FirebaseDatabase.getInstance();
        this.orderReference = database.getReference(ORDER_REFERENCE);
    }

    //TODO: Implement Transaction -> Multi-user safety!
    public void saveNewOrder(final OrderDataModelRecuded order){

        //Get latest Order-ID and wait for the store process
        try {
            orderReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int highestUid = 0;
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        int current = Integer.parseInt(postSnapshot.getKey());
                        if(current > highestUid){
                            highestUid = current;
                        }
                    }
                    proceedWithOrder(order, highestUid+1);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    throw new RuntimeException("Not implemented!");
                }
            });
        }
        catch (Exception ex){

        }
    }

    private void proceedWithOrder(OrderDataModelRecuded order, int uid){
        orderReference.child("" + uid).setValue(order);
    }
}
