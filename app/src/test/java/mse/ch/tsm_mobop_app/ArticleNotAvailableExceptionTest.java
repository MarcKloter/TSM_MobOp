package mse.ch.tsm_mobop_app;

import org.junit.Assert;
import org.junit.Test;

import mse.ch.tsm_mobop_app.data.ArticleNotAvailableException;

public class ArticleNotAvailableExceptionTest {

    private static final String EXCEPTION_MESSAGE = "Test";
    private static final Throwable THROWABLE = new Throwable("Test");

    @Test
    public void testExceptionString(){
        try{
            throw new ArticleNotAvailableException(EXCEPTION_MESSAGE);
        }
        catch (ArticleNotAvailableException ex){
            Assert.assertEquals(EXCEPTION_MESSAGE, ex.getMessage());
        }
    }

    @Test
    public void testExceptionCause(){
        try{
            throw new ArticleNotAvailableException(THROWABLE);
        }
        catch (ArticleNotAvailableException ex){
            Assert.assertEquals(THROWABLE, ex.getCause());
        }
    }

    @Test
    public void testExceptionStringAndCause(){
        try{
            throw new ArticleNotAvailableException(EXCEPTION_MESSAGE, THROWABLE);
        }
        catch (ArticleNotAvailableException ex){
            Assert.assertEquals(EXCEPTION_MESSAGE, ex.getMessage());
            Assert.assertEquals(THROWABLE, ex.getCause());
        }
    }
}
