package SeasonPackage;

import ActorCharacterPackage.Character;
import EpisodePackage.*;
import java.util.*;


public class SeasonClass implements Season {

    int seasonNumber;
    private List<Episode> episodes;
    private Map<String, Character> participants;

    public SeasonClass(int seasonNumber){
        this.seasonNumber = seasonNumber;
        this.episodes = new ArrayList<Episode>();
        this.participants = new HashMap<String, Character>();
    }

    public int addEpisode(String epiName){
        episodes.add(new EpisodeClass(epiName, seasonNumber, episodes.size()));
        return episodes.size();
    }

    public Episode getEpisode(int index){
        return episodes.get(index);
    }

    public int getEpisodesNumber(){
        return episodes.size();
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