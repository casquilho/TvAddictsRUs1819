package MyExceptionsPackage;

public class NoVirtualCharactersExc extends Exception {

    private static final String Message = "This is the real thing, this is art!";

    public NoVirtualCharactersExc(){
        super(Message);
    }
}
