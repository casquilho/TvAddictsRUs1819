package MyExceptionsPackage;

public class NonExistentSeasonExc extends Exception {

    private static String message;


    public NonExistentSeasonExc(String showName, int season) {
        super();
        message = String.format("%s does not have season %d!", showName, season);

    }

}
