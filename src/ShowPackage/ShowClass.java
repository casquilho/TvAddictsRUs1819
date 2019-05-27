package ShowPackage;


import ActorCharacterPackage.VirtualCharacter;
import ActorCharacterPackage.Character;
import ActorCharacterPackage.RealCharacter;
import ActorCharacterPackage.*;
import CGICompaniesPackage.CGICompany;
import EpisodePackage.Episode;
import EventPackage.Event;
import EventPackage.EventClass;
import MyExceptionsPackage.*;
import QuotesPackage.*;
import SeasonPackage.*;


import java.text.Collator;
import java.util.*;

public class ShowClass implements Show {

    private String showName;
    private int seasonsNumber;
    private int totalEpisodesNumber;
    private List<Season>  seasons;
    private Map<String, Character> characters;
    private Map<String, List<String>> quotes;
    private Map<String, List<Event>> events;

    public ShowClass(String ShowName){
        this.showName = ShowName;
        this.seasonsNumber = 0;
        this.totalEpisodesNumber = 0;
        this.seasons    = new ArrayList<Season>();
        this.characters = new HashMap<String, Character>();
        this.quotes     = new HashMap<String, List<String>>();
        this.events     = new TreeMap<String, List<Event>>(Collator.getInstance());
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

    public void addRealCharacter(String charName, Actor actor, int cost) throws DuplicateCharacterExc {

        if(characters.containsKey(charName))
            throw new DuplicateCharacterExc();

        characters.put(charName, new RealCharacterClass(charName, actor, this,cost));

    }

    public void addCGICharacter(String charName, String company, int cost) throws DuplicateCharacterExc {
        if (characters.containsKey(charName))
            throw new DuplicateCharacterExc();

        characters.put(charName, new VirtualCharacterClass(charName,company, this, cost));
    }

    public void addQuote(int season, int episode, String charName, String quoteText, List<CGICompany> companies) throws NonExistentSeasonExc, NonExistentEpisodeExc, UnknownCharacterExc {
        if( season < 1 || season > seasonsNumber)
            throw new NonExistentSeasonExc();
        if( episode < 1 || episode > seasons.get(season-1).getEpisodesNumber() )
            throw new NonExistentEpisodeExc();
        if(!characters.containsKey(charName))
            throw new UnknownCharacterExc();

        Quote quoteAux = new QuoteClass(quoteText, charName, season, episode);

        processQuote(quoteAux, characters.get(charName), season, episode, companies);
    }

    private void processQuote(Quote quote, Character character, int thisSeason, int thisEpisode, List<CGICompany> companies){

        Season season = seasons.get(thisSeason - 1);
        Episode episode = season.getEpisode(thisEpisode -1);


        addCharToEpisodeSeason(season, episode, character, companies);

        //if this particular quote already exists, add the character to the list of characters who said it
        if(quotes.containsKey(quote.getQuote())){
            if(!quotes.get(quote.getQuote()).contains(character.getCharName()))
                quotes.get(quote.getQuote()).add(character.getCharName());
        }

        //otherwise, create a new entry for it
        else {
            List aux = new LinkedList<String>();
            aux.add(character.getCharName());
            quotes.put(quote.getQuote(), aux);

        }
    }

    private void addCharToEpisodeSeason(Season season, Episode episode, Character character, List<CGICompany> companies){

        //if character is not on the season, add him.
        // if it's a virtual character, appearing in the season for the first time, add its value to the company's profits
        if(season.addParticipant(character) && character instanceof VirtualCharacter){
            String companyName = ((VirtualCharacter) character).getCompanyCGI();
            for (CGICompany company : companies) {
                if (company.getName().equals(companyName))
                    company.addProfit(character.getCost());
            }
        }
        //if character is not on the episode, add him.
        // if it's a real character appearing in the episode for the first time, add its value to its profits
        if(episode.addParticipant(character) && character instanceof RealCharacter)
            ((RealCharacter) character).addProfitFromAppearance();
    }

    public Iterator getFamousQuotes(String quoteText) throws UnknownQuoteExc{
        if(!quotes.containsKey(quoteText))
            throw new UnknownQuoteExc();
        quotes.get(quoteText).sort(Collator.getInstance());
        return quotes.get(quoteText).iterator();
    }

    public String getActorNameFromCharName(String charName) throws UnknownCharacterExc{
        if(!characters.containsKey(charName))
            throw new UnknownCharacterExc();

        RealCharacter aux = (RealCharacter) characters.get(charName);
        return aux.getActorName();
    }

    public void addEvent(int thisEpisode, int thisSeason, List<String> chars, String event, List<CGICompany> companies) throws NonExistentSeasonExc, NonExistentEpisodeExc, UnknownCharacterExc{
        if( thisSeason < 1 || thisSeason > seasonsNumber)
            throw new NonExistentSeasonExc();
        if( thisEpisode < 1 || thisEpisode > seasons.get(thisSeason-1).getEpisodesNumber() )
            throw new NonExistentEpisodeExc();

        List<Character> charsObj = new LinkedList<Character>();
        Character aux;

        Season season = seasons.get(thisSeason - 1);
        Episode episode = season.getEpisode(thisEpisode -1);

        //check if the character names passed correspond to existing characters and if so,
        // get the characters and insert then into a new list
        for (String charName: chars) {
            if(!characters.containsKey(charName)) {
                chars.add(0, charName);
                throw new UnknownCharacterExc();
            }
            aux = characters.get(charName);
            if(!charsObj.contains(aux))
                charsObj.add(aux);
        }

        Event newEvent = new EventClass(thisEpisode, thisSeason, event, charsObj);

        String key = season +""+ episode;

        if(events.containsKey(key)) {
            if (!events.get(key).contains(newEvent))
                events.get(key).add(newEvent);
        }
        else{
            List<Event> auxList = new LinkedList<Event>();
            auxList.add(newEvent);
            events.put(key, auxList);
        }

        for (Character auxChar: charsObj ) {
            addCharToEpisodeSeason(season, episode, auxChar, companies);
            auxChar.addEvent(key, newEvent);
        }
    }

    public Iterator getCharacterResume(String charName) throws UnknownCharacterExc{
        if(!characters.containsKey(charName))
            throw new UnknownCharacterExc();
        Character auxChar = characters.get(charName);

        return auxChar.getCharacterEvents();
    }

    public Iterator getEvents(){
        return events.values().iterator();
    }

    private void incrementEpisodesNumber(){
        this.totalEpisodesNumber++;
    }

    private void incrementSeasonsNumber(){
        this.seasonsNumber++;
    }
}
