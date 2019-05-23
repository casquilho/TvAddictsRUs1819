package SeasonPackage;

import ActorCharacterPackage.Character;
import EpisodePackage.Episode;
import EpisodePackage.EpisodeClass;

public interface Season  {


    int addEpisode(String epiName);

    Episode getEpisode(int index);

    boolean participatesIn(String charName);

    boolean addParticipant(Character character);

}
