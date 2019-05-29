package MyExceptionsPackage;

public class SameCharacterExc extends Exception {
    private static final String message = "%s cannot be parent and child at the same time!";

    public SameCharacterExc(){
        super(message);
    }
}
