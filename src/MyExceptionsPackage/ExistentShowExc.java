/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package MyExceptionsPackage;

public class ExistentShowExc extends Exception {

    private static final String MESSAGE = "Show already exists!";

    public ExistentShowExc(){
        super(MESSAGE);
    }
}
