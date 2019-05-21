package MyExceptionsPackage;

public class UnknownShowExc extends Exception{

    private static final String MESSAGE = "Unknown show!";

    public UnknownShowExc(){
        super(MESSAGE);
    }
}
