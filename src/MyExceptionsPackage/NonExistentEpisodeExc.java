/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package MyExceptionsPackage;

public class NonExistentEpisodeExc extends Exception {

    private static String message ="%s S%d does not have episode %d!";

    public NonExistentEpisodeExc() {
        super(message);
    }

}
