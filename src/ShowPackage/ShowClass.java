package ShowPackage;


import ActorCharacterPackage.CGIActor;
import ActorCharacterPackage.Character;
import ActorCharacterPackage.RealActor;
import ActorCharacterPackage.*;
import MyExceptionsPackage.*;
import SeasonPackage.*;


import java.util.*;

public class ShowClass implements Show {

    private String showName;
    private int seasonsNumber;
    private int totalEpisodesNumber;
    private List<Season>  seasons;
    private Map<String, Character> characters;
    private Map<String, ArrayList<String>> quotes;

    public ShowClass(String ShowName){
        this.showName = ShowName;
        this.seasonsNumber = 0;
        this.totalEpisodesNumber = 0;
        this.seasons = new ArrayList<Season>();
        this.characters = new HashMap<String, Character>();
        this.quotes = new HashMap<String, ArrayList<String>>();


    }

    public String getName() {
        return showName;
    }

    public int getSeasonsNumber() {
        return seasonsNumber;
    }

    public int getTotalEpisodesNumber() {
        return totalEpisodesNumber;
    }

    public void addSeason(){
        Season newSeason = new SeasonClass();
        seasons.add(newSeason);
        incrementSeasonsNumber();
    }

    public int addEpisode(int seasonNumber, String epiName) throws UnknownSeasonExc {

        try{
            Season currentSeason = seasons.get(seasonNumber-1);  //throws IndexOutOfBoundsException if the season doesn't exist
            incrementEpisodesNumber();
            return currentSeason.addEpisode(epiName);
        }
        catch(IndexOutOfBoundsException e){
            throw new UnknownSeasonExc();
        }
    }

    public void addRealCharacter(String charName, String actorName, int cost) throws DuplicateCharacterExc {

        if(characters.containsKey(charName))
            throw new DuplicateCharacterExc();

        RealActor actorAux = new RealActorClass(charName, actorName, cost);
        characters.put(charName, (Character) actorAux);

    }

    public void addCGICharacter(String charName, String company, int cost) throws DuplicateCharacterExc {
        if (characters.containsKey(charName))
            throw new DuplicateCharacterExc();

        CGIActor actorAux = new CGIActorClass(charName, company, cost);
        characters.put(charName, (Character) actorAux);
    }

    public void addQuote(int season, int episode, String charName, String Quote) throws NonExistentSeasonExc, NonExistentEpisodeExc {
        if(season > seasonsNumber)
            throw new NonExistentSeasonExc(showName, season);
        if(episode > seasons.get(season).numberOfEpisodes())
            throw new NonExistentEpisodeExc(showName, season, episode);

        if(!characters.containsKey(charName))
            throw new UnknownCharacter//TODO

    }


    private void incrementEpisodesNumber(){
        this.totalEpisodesNumber++;
    }

    private void incrementSeasonsNumber(){
        this.seasonsNumber++;
    }
}
