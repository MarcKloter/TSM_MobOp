package mse.ch.tsm_mobop_app.data;

/**
 * This interface belongs to the DataController classes which have async data receiving methods
 * (e.g. receiving data async from the server). This interfaces need to be implemented from caller
 * classes of these DataControllers, to provide a callback method.
 */
public interface OnDataReceivedEventListener {
    /**
     * This method returns an object of the desired class, when the responsible DataController
     * received the data from the server.
     * @param object Is the object which the Listener wants to receive from the DataController.
     */
    void onDataReceived(Object object);
}
