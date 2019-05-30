/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package MyExceptionsPackage;

public class NotRelatedExc extends Exception {

    private static String message = "These characters are not related!";

    public NotRelatedExc(){
        super(message);
    }
}
