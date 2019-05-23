package EpisodePackage;

import ActorCharacterPackage.Character;

public interface Episode {

    String getEpisodeName();

    int getSeasonNumber();

    int getEpisodeNumber();

    boolean participatesIn(String charName);

    boolean addParticipant(Character character);
}
