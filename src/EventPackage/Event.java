/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package EventPackage;

/**
 * The interface Event.
 */
public interface Event {

    /**
     * Gets event.
     *
     * @return the event
     */
    String getEvent();

    /**
     * Gets season.
     *
     * @return the season
     */
    int getSeason();

    /**
     * Gets episode.
     *
     * @return the episode
     */
    int getEpisode();
}
