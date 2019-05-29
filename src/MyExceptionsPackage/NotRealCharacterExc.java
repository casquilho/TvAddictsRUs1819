package MyExceptionsPackage;

public class NotRealCharacterExc extends Exception {

    private static String Message = "%s is played by a virtual actor!";

    public NotRealCharacterExc(){
        super(Message);
    }
}
