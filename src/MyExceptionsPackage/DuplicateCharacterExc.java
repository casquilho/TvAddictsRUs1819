package MyExceptionsPackage;

public class DuplicateCharacterExc extends Exception {

    private static final String MESSAGE = "Duplicate character names are not allowed!";

    public DuplicateCharacterExc(){
        super(MESSAGE);
    }
}