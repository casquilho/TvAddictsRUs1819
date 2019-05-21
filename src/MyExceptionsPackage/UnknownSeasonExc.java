package MyExceptionsPackage;

public class UnknownSeasonExc extends Exception{

    private static final String MESSAGE = "Unknown season!";

    public UnknownSeasonExc(){
            super(MESSAGE);
    }
}
