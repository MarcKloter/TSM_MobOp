package mse.ch.tsm_mobop_app.Data;

/**
    Exception for the read / write action of the articles in the database.
 */
public class ArticleNotAvailableException extends Exception {

    public ArticleNotAvailableException() {
        super();
    }

    public ArticleNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArticleNotAvailableException(String message) {
        super(message);
    }

    public ArticleNotAvailableException(Throwable cause) {
        super(cause);
    }
}
