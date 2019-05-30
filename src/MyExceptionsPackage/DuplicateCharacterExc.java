/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package MyExceptionsPackage;

public class DuplicateCharacterExc extends Exception {

    private static final String MESSAGE = "Duplicate character names are not allowed!";

    public DuplicateCharacterExc(){
        super(MESSAGE);
    }
}