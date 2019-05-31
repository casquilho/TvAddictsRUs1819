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
     * Gets number of shows where this actor
     * has romantic relationships
     *
     * @return the number of "romantic" shows
     */
    int getNumberOfRomanticShows();

    /**
     * Gets total sum of all romantic relationships that actor has
     * in all shows he plays
     *
     * @return int of the total number of romantic relationships
     */
    int getTotalRomanticNum();

    /**
     * Gets the number off roles played by the actor.
     *
     * @return rolesNumber
     */
    int getNumberOfroles();

    /**
     * Gets the iterator to the map where:
     * (String,Integer) corresponds to (showName, numberOfRomanticRelationships)
     *
     * @return iterator
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
     * Sets number of romantic relationships for each show.
     * If the show is already registered, we increment that value
     *
     * @param showName the show name that we want
     * to save or update the correspondent number of romantic
     * relationships that actor has in that show
     */
    void setNumOfRomRelByShow(String showName);

    /**
     * Add a new show name to the set of shows in which the actor took part.
     *
     * @param showName the show name
     */
    void addShowName(String showName);
}
