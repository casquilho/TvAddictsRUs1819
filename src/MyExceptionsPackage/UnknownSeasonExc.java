/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package MyExceptionsPackage;

public class UnknownSeasonExc extends Exception{

    private static final String MESSAGE = "Unknown season!";

    public UnknownSeasonExc(){
            super(MESSAGE);
    }
}
