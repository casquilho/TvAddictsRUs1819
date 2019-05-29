package MyExceptionsPackage;

public class NotRelatedExc extends Exception {

    private static String message = "These characters are not related!";

    public NotRelatedExc(){
        super(message);
    }
}
