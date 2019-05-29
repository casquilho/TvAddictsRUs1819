package MyExceptionsPackage;

public class InvalidSeasonInterval extends Exception{

    private final static String message = "Invalid seasons interval!";

    public InvalidSeasonInterval(){
        super(message);
    }

}
