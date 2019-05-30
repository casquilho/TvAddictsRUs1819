/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package MyExceptionsPackage;

public class LoveIsntInTheAirExc extends Exception {

    private static final String MESSAGE = "Love is not in the air!";

    public LoveIsntInTheAirExc(){
        super(MESSAGE);
    }
}
