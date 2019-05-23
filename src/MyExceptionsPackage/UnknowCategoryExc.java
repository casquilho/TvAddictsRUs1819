package MyExceptionsPackage;

public class UnknowCategoryExc extends Exception {

    private static final String MESSAGE = "Unknown actor category!";

    public UnknowCategoryExc(){
        super(MESSAGE);
    }

}
