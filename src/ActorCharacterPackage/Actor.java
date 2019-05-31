/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package ActorCharacterPackage;

import java.util.Iterator;
import java.util.Map;

/**
 * The Actor interface.
 */
public interface Actor {

    /**
     * Gets the actor's name.
     *
     * @return actorName
     */
    String getName();

    /**
     * Gets number of romantic shows.
     *
     * @return the number of romantic shows
     */
    int getNumberOfRomanticShows();

    /**
     * Gets total romantic num.
     *
     * @return the total romantic num
     */
    int getTotalRomanticNum();

    /**
     * Gets the number off roles played by the actor.
     *
     * @return rolesNumber
     */
    int getNumberOfroles();

    /**
     * Gets num of rom rel by show it.
     *
     * @return the num of rom rel by show it
     */
    Iterator<Map.Entry<String, Integer>> getNumOfRomRelByShowIt();

    /**
     * StarsOn iterator - iterator to the names of
     * the show that the actor plays or played on.
     *
     * @return iterator
     */
    Iterator<String> StarsOn();

    /**
     * Sets num of rom rel by show.
     *
     * @param showName the show name
     */
    void setNumOfRomRelByShow(String showName);

    /**
     * Add a new show name to the set of shows in which the actor took part.
     *
     * @param showName the show name
     */
    void addShowName(String showName);
}
