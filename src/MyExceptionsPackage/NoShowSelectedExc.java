/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package MyExceptionsPackage;

public class NoShowSelectedExc extends Exception {

    private static final String MESSAGE = "No show is selected!";

    public NoShowSelectedExc(){
        super(MESSAGE);
    }
}
