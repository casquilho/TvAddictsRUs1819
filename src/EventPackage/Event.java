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
     * Gets the event's name.
     *
     * @return event name
     */
    String getEvent();

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
