package MyExceptionsPackage;

public class UnknownCharacterExc extends Exception {

    private static String message;

    public UnknownCharacterExc(String charName) {
        super();
        message = String.format("Who is %s?", charName);
    }

}
