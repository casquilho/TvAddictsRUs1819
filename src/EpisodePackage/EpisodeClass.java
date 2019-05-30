/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package EpisodePackage;

import ActorCharacterPackage.Character;

import java.util.HashMap;
import java.util.Map;

public class EpisodeClass implements Episode{

    private String episodeName;
    private int seasonNumber;
    private int episodeNumber;
    private Map<String, Character> participants;

    public EpisodeClass(String episodeName, int seasonNumber, int episodeNumber) {
        this.episodeName = episodeName;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
        this.participants = new HashMap<String, Character>();
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public boolean participatesIn(String charName){
        return participants.containsKey(charName);
    }

    public boolean addParticipant(Character character){
        if(participants.containsKey(character.getCharName()))
            return false;

        participants.put(character.getCharName(), character);
        return true;
    }
}
