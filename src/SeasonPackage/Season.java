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
     * Gets the episode's name.
     *
     * @param epiNumber the number of the episode
     * @return episode name
     * @throws NonExistentEpisodeExc if there is no episode with the given number on the current season
     */
    String getEpisodeName(int epiNumber) throws NonExistentEpisodeExc;

    /**
     * Gets the episode.
     *
     * @param index the number of the episode
     * @return episode
     */
    Episode getEpisode(int index);

    /**
     * Adds an episode to the current season. The episodes are added sequentially in an ArrayList
     *
     * @param epiName the episode's name
     * @return the number of the episode
     */
    int addEpisode(String epiName);

    /**
     * Gets the total number of episodes in the current season.
     *
     * @return episodes number
     */
    int getEpisodesNumber();

    /**
     * Checks if a given character is an active participant in the season.
     *
     * @param charName the character's name
     * @return <tt>true</tt> if the given character is a participant
     */
    boolean participatesIn(String charName);

    /**
     * Add the given character to the season's participants.
     *
     * @param character the character
     * @return <tt>true</tt> if the character was successfully added
     */
    boolean addParticipant(Character character);
}
