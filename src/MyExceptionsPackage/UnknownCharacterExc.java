package MyExceptionsPackage;

public class UnknownCharacterExc extends Exception {

    private static String message = "Who is %s?";

    public UnknownCharacterExc() {
        super(message);
    }

}
