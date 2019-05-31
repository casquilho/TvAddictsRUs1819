/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package SeasonPackage;

import ActorCharacterPackage.Character;
import EpisodePackage.Episode;
import MyExceptionsPackage.NonExistentEpisodeExc;

/**
 * The interface Season.
 */
public interface Season  {

    /**
     * Gets episode name.
     *
     * @param epiNumber the epi number
     * @return the episode name
     * @throws NonExistentEpisodeExc the non existent episode exc
     */
    String getEpisodeName(int epiNumber) throws NonExistentEpisodeExc;

    /**
     * Gets episode.
     *
     * @param index the index
     * @return the episode
     */
    Episode getEpisode(int index);

    /**
     * Add episode int.
     *
     * @param epiName the epi name
     * @return the int
     */
    int addEpisode(String epiName);

    /**
     * Gets episodes number.
     *
     * @return the episodes number
     */
    int getEpisodesNumber();

    /**
     * Participates in boolean.
     *
     * @param charName the char name
     * @return the boolean
     */
    boolean participatesIn(String charName);

    /**
     * Add participant boolean.
     *
     * @param character the character
     * @return the boolean
     */
    boolean addParticipant(Character character);
}
