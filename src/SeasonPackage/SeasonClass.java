package SeasonPackage;

import ActorCharacterPackage.Character;
import EpisodePackage.*;
import MyExceptionsPackage.NonExistentEpisodeExc;

import java.util.*;


public class SeasonClass implements Season {

    private int seasonNumber;
    private List<Episode> episodes;
    private Map<String, Character> participants;

    public SeasonClass(int seasonNumber){
        this.seasonNumber = seasonNumber;
        this.episodes     = new ArrayList<Episode>();
        this.participants = new HashMap<String, Character>();
    }

    public int addEpisode(String epiName){
        episodes.add(new EpisodeClass(epiName, seasonNumber, episodes.size()+1));
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

    public String getEpisodeName(int epiNumber) throws NonExistentEpisodeExc {
        if(episodes.size() < epiNumber)
            throw new NonExistentEpisodeExc();
        return episodes.get(epiNumber).getEpisodeName();
    }

}
