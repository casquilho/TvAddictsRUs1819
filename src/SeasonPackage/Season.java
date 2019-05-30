/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package SeasonPackage;

import ActorCharacterPackage.Character;
import EpisodePackage.Episode;
import MyExceptionsPackage.NonExistentEpisodeExc;

public interface Season  {

    String getEpisodeName(int epiNumber) throws NonExistentEpisodeExc;

    Episode getEpisode(int index);

    int addEpisode(String epiName);

    int getEpisodesNumber();

    boolean participatesIn(String charName);

    boolean addParticipant(Character character);
}
