/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package QuotesPackage;

/**
 * The interface Quote.
 */
public interface Quote {

    /**
     * Gets the quote's text.
     *
     * @return quote text
     */
    String getQuote();

    /**
     * Gets the character's name.
     *
     * @return character name
     */
    String getCharName();

    /**
     * Gets the season's number.
     *
     * @return season number
     */
    int getSeason();

    /**
     * Gets the episode's number.
     *
     * @return episode number
     */
    int getEpisode();
}
