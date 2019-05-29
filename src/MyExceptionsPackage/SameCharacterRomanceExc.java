package MyExceptionsPackage;

public class SameCharacterRomanceExc extends Exception{

    private static String message = "%s cannot be in a single person romantic relationship!";

    public SameCharacterRomanceExc(){
        super(message);
    }
}
