package SeasonPackage;

import ActorCharacterPackage.Character;
import EpisodePackage.Episode;
import EpisodePackage.EpisodeClass;
import MyExceptionsPackage.NonExistentEpisodeExc;

public interface Season  {


    int addEpisode(String epiName);

    Episode getEpisode(int index);

    int getEpisodesNumber();

    boolean participatesIn(String charName);

    boolean addParticipant(Character character);

    String getEpisodeName(int epiNumber) throws NonExistentEpisodeExc;

}
