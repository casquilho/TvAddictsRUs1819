/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package MyExceptionsPackage;

public class InvalidSeasonInterval extends Exception{

    private final static String message = "Invalid seasons interval!";

    public InvalidSeasonInterval(){
        super(message);
    }

}
