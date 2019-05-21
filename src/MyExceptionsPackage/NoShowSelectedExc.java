package MyExceptionsPackage;

public class NoShowSelectedExc extends Exception {

    private static final String MESSAGE = "No show is selected!";

    public NoShowSelectedExc(){
        super(MESSAGE);
    }
}
