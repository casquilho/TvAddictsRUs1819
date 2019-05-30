/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package MyExceptionsPackage;

public class SameCharacterRomanceExc extends Exception{

    private static String message = "%s cannot be in a single person romantic relationship!";

    public SameCharacterRomanceExc(){
        super(message);
    }
}
