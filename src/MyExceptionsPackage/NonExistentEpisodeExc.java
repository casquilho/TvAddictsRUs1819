package MyExceptionsPackage;

public class NonExistentEpisodeExc extends Exception {

    private static String message;

    public NonExistentEpisodeExc(String showName, int season, int episode) {
        super(String.format("%s S%d| does not have episode %d!", showName, season, episode));
    }

}