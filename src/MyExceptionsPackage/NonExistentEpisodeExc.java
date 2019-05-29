package MyExceptionsPackage;

public class NonExistentEpisodeExc extends Exception {

    private static String message ="%s S%d does not have episode %d!";

    public NonExistentEpisodeExc() {
        super(message);
    }

}
