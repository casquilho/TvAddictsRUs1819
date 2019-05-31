/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package EpisodePackage;

import ActorCharacterPackage.Character;

/**
 * The Episode's interface.
 */
public interface Episode {

    /**
     * Gets the episode's name.
     *
     * @return episodeName
     */
    String getEpisodeName();

    /**
     * Gets the season number.
     *
     * @return seasonNumber
     */
    int getSeasonNumber();

    /**
     * Gets the episode number.
     *
     * @return episodeNumber
     */
    int getEpisodeNumber();

    /**
     * Add a characters to the participants in the episode.
     *
     * @param character the character
     * @return <tt>true</tt> if the character is added
     */
    boolean addParticipant(Character character);

    /**
     * Check if a given character is a partipant in the episode.
     *
     * @param charName the chararacter's name
     * @return <tt>true</tt> if the character is a participant in the episode
     */
    boolean participatesIn(String charName);
}
