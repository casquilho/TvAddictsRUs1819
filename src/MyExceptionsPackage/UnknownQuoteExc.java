/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package MyExceptionsPackage;

public class UnknownQuoteExc extends Exception {

    private static final String Message = "First time I hear that!";

    public UnknownQuoteExc(){
        super(Message);
    }

}
