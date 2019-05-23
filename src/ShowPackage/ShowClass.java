package ShowPackage;


import ActorCharacterPackage.VirtualCharacter;
import ActorCharacterPackage.Character;
import ActorCharacterPackage.RealCharacter;
import ActorCharacterPackage.*;
import CGICompaniesPackage.CGICompany;
import EpisodePackage.Episode;
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
    private Map<String, List<Character>> quotes;

    public ShowClass(String ShowName){
        this.showName = ShowName;
        this.seasonsNumber = 0;
        this.totalEpisodesNumber = 0;
        this.seasons = new ArrayList<Season>();
        this.characters = new HashMap<String, Character>();
        this.quotes = new HashMap<String, List<Character>>();


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
        Season newSeason = new SeasonClass(seasons.size()+1);
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

        characters.put(charName, new RealCharacterClass(charName, actorName, this,cost));

    }

    public void addCGICharacter(String charName, String company, int cost) throws DuplicateCharacterExc {
        if (characters.containsKey(charName))
            throw new DuplicateCharacterExc();

        characters.put(charName, new VirtualCharacterClass(charName,company, this, cost));
    }

    public void addQuote(int season, int episode, String charName, String quoteText, List<CGICompany> companies) throws NonExistentSeasonExc, NonExistentEpisodeExc, UnknownCharacterExc {
        if( season < 1 || season > seasonsNumber)
            throw new NonExistentSeasonExc(showName, season);
        if( episode < 1 || episode > seasons.get(season).getEpisodesNumber() )
            throw new NonExistentEpisodeExc(showName, season, episode);
        if(!characters.containsKey(charName))
            throw new UnknownCharacterExc(charName);

        Quote quoteAux = new QuoteClass(quoteText, charName, season, episode);

        processQuote(quoteAux, characters.get(charName), season, episode, companies);

    }

    private void processQuote(Quote quote, Character character, int thisSeason, int thisEpisode, List<CGICompany> companies){

        Season season = seasons.get(thisSeason);
        Episode episode = season.getEpisode(thisEpisode);

        if(season.addParticipant(character))                //if character is not on the season, add him and if it is a virtual character, add the cost to the company's profits
            if(character instanceof VirtualCharacter) {
                String companyName = ((VirtualCharacter) character).getCompanyCGI();
                for (CGICompany company : companies) {
                    if (company.getName().equals(companyName))
                        company.addProfit(character.getCost());
                }
            }

        episode.addParticipant(character); //if character is not on the episode, add him


        if(quotes.containsKey(quote.getQuote()))
            quotes.get(quote.getQuote()).add(character);
        else {
            List aux = new LinkedList<Character>();
            aux.add(character);
            quotes.put(quote.getQuote(), aux);

        }
    }

    private void incrementEpisodesNumber(){
        this.totalEpisodesNumber++;
    }

    private void incrementSeasonsNumber(){
        this.seasonsNumber++;
    }
}
