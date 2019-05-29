package MyExceptionsPackage;

public class DuplicateRelationshipExc extends Exception {

    private static final String Message = "What else is new? We already know about those two...";

    public DuplicateRelationshipExc(){
        super(Message);
    }
}
