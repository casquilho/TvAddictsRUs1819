/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package MyExceptionsPackage;

public class UnknownShowExc extends Exception{

    private static final String MESSAGE = "Unknown show!";

    public UnknownShowExc(){
        super(MESSAGE);
    }
}
