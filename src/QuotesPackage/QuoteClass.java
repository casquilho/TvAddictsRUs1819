/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package QuotesPackage;

public class QuoteClass implements Quote {

    private String quote;
    private String charName;
    private int    season;
    private int    episode;



    public QuoteClass(String quote, String charName, int season, int episode) {
        this.quote = quote;
        this.season = season;
        this.episode = episode;
        this.charName = charName;
    }

    public String getQuote() {
        return quote;
    }

    public String getCharName() {
        return charName;
    }

    public int getSeason() {
        return season;
    }

    public int getEpisode() {
        return episode;
    }
}
