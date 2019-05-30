/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package MyExceptionsPackage;

public class UnknownCharacterExc extends Exception {

    private static String message = "Who is %s?";

    public UnknownCharacterExc() {
        super(message);
    }

}
