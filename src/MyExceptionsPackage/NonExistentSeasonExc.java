/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package MyExceptionsPackage;

public class NonExistentSeasonExc extends Exception {

    private static String message = "%s does not have season %d!";

    public NonExistentSeasonExc() {
        super(message);

    }

}
