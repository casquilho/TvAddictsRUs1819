package MyExceptionsPackage;

public class ExistentShowExc extends Exception {

    private static final String MESSAGE = "Show already exists!";

    public ExistentShowExc(){
        super(MESSAGE);
    }
}
