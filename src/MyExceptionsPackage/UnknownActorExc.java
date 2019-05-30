/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package MyExceptionsPackage;

public class UnknownActorExc extends Exception {

    private static String MESSAGE= "Who is %s?";

    public UnknownActorExc() {
        super(MESSAGE);
    }
}