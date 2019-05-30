/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package EpisodePackage;

import ActorCharacterPackage.Character;

public interface Episode {

    String getEpisodeName();

    int getSeasonNumber();

    int getEpisodeNumber();

    boolean addParticipant(Character character);

    boolean participatesIn(String charName);
}
