package ShowPackage;


import ActorCharacterPackage.VirtualCharacter;
import ActorCharacterPackage.Character;
import ActorCharacterPackage.RealCharacter;
import ActorCharacterPackage.*;
import MyExceptionsPackage.*;
import QuotesPackage.*;
import SeasonPackage.*;


import java.util.*;

public class ShowClass implements Show {

    private String showName;
    private int seasonsNumber;
    private int totalEpisodesNumber;
    private List<Season>  seasons;
    private Map<String, Character> characters;
    private Map<String, Set<Quote>> quotes;

    public ShowClass(String ShowName){
        this.showName = ShowName;
        this.seasonsNumber = 0;
        this.totalEpisodesNumber = 0;
        this.seasons = new ArrayList<Season>();
        this.characters = new HashMap<String, Character>();
        this.quotes = new HashMap<String, Set<Quote>>();


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

        RealCharacter actorAux = new RealCharacterClass(charName, actorName, cost);
        characters.put(charName, (Character) actorAux);

    }

    public void addCGICharacter(String charName, String company, int cost) throws DuplicateCharacterExc {
        if (characters.containsKey(charName))
            throw new DuplicateCharacterExc();

        VirtualCharacter actorAux = new VirtualActorClass(charName, company, cost);
        characters.put(charName, (Character) actorAux);
    }

    public void addQuote(int season, int episode, String charName, String quoteText) throws NonExistentSeasonExc, NonExistentEpisodeExc, UnknownCharacterExc {
        if(season > seasonsNumber)
            throw new NonExistentSeasonExc(showName, season);
        if(episode > seasons.get(season).numberOfEpisodes())
            throw new NonExistentEpisodeExc(showName, season, episode);
        if(!characters.containsKey(charName))
            throw new UnknownCharacterExc(charName);

        Quote quoteAux = new QuoteClass(quoteText, charName, season, episode);

        processQuote(quoteAux);

    }

    private void processQuote(Quote quote){}

    private void incrementEpisodesNumber(){
        this.totalEpisodesNumber++;
    }

    private void incrementSeasonsNumber(){
        this.seasonsNumber++;
    }
}
