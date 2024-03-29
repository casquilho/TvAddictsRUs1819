/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
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

    public String getEpisodeName(int season, int episode) throws NonExistentEpisodeExc, NonExistentSeasonExc{
        if(season > seasons.size())
            throw new NonExistentSeasonExc();

        return seasons.get(season).getEpisode(episode).getEpisodeName();
    }

    public String getActorNameFromCharName(String charName) throws UnknownCharacterExc{
        if(!characters.containsKey(charName) )
            throw new UnknownCharacterExc();

        Character aux = characters.get(charName);
        if(aux instanceof RealCharacter)
            return ((RealCharacter) aux).getActorName();

        return null;
    }

    public int getSeasonsNumber() {
        return seasonsNumber;
    }

    public int getTotalEpisodesNumber() {
        return totalEpisodesNumber;
    }

    public int getNumParentsFromName(String charName){
        return characters.get(charName).getNumParents();
    }

    public int getNumChildrenFromName(String charName){
        return characters.get(charName).getNumChildren();
    }

    public Season getSeason(int seasonNumber, int seasonStart, int seasonEnd) throws InvalidSeasonInterval {
        if(this.getSeasonsNumber() < seasonEnd || this.getSeasonsNumber() < seasonStart || seasonStart < 1)
            throw new InvalidSeasonInterval();
        return seasons.get(seasonNumber-1);
    }

    public Iterator<Event> getEventsFromEpisode(String key){
        if(!events.containsKey(key))
            return null;
        return events.get(key).iterator();
    }

    public Iterator<String> getFamousQuotes(String quoteText) throws UnknownQuoteExc{
        if(!quotes.containsKey(quoteText))
            throw new UnknownQuoteExc();
        quotes.get(quoteText).sort(Collator.getInstance());
        return quotes.get(quoteText).iterator();
    }

    public Iterator<List<Event>> getCharacterResume(String charName, List<Iterator<Character>> auxList) throws UnknownCharacterExc{
        if(!characters.containsKey(charName))
            throw new UnknownCharacterExc();

        Character auxChar = characters.get(charName);

        auxList.add(0,auxChar.getParentsIt());  //iterator to the character's parents, acessed by index in the for loop
        auxList.add(1,auxChar.getChildrenIt()); //iterator to the character's children, acessed by index in the for loop
        auxList.add(2,auxChar.getSiblingsIt()); //iterator to the character's siblings, acessed by index in the for loop
        auxList.add(3,auxChar.getRomancesIt()); //iterator to the character's romantic partners, acessed by index in the for loop

        return auxChar.getCharacterEvents();
    }

    public Iterator<List<Event>> getEvents(){
        return events.values().iterator();
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

    public void addSeason(){
        Season newSeason = new SeasonClass(seasons.size()+1);
        seasons.add(newSeason);
        incrementSeasonsNumber();
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

        String key = thisSeason +""+ thisEpisode;

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

    public void addRelationship(String parent, String child, List<String> aux) throws UnknownCharacterExc, DuplicateRelationshipExc {
        if(!characters.containsKey(parent)) {
            aux.add(0, parent);
            throw new UnknownCharacterExc();
        }
        if(!characters.containsKey(child)){
            aux.add(0, child);
            throw new UnknownCharacterExc();
        }

        Character auxParent = characters.get(parent);
        Character auxChild = characters.get(child);

        if(auxChild.hasParent(auxParent) && auxParent.hasChild(auxChild))
            throw new DuplicateRelationshipExc();

        auxParent.addChild(auxChild);
        auxChild.addParents(auxParent);
    }

    public void addRomance(String char1, String char2, List<String> aux) throws UnknownCharacterExc, DuplicateRelationshipExc{
        if(!characters.containsKey(char1)) {
            aux.add(0, char1);
            throw new UnknownCharacterExc();
        }
        if(!characters.containsKey(char2)){
            aux.add(0, char2);
            throw new UnknownCharacterExc();
        }

        Character auxChar1 = characters.get(char1);
        Character auxChar2 = characters.get(char2);

        if(auxChar1.hasPartner(auxChar2))
            throw new DuplicateRelationshipExc();

        auxChar1.addPartner(auxChar2);
        auxChar2.addPartner(auxChar1);
    }

    public Stack<Character> howAreTheseTwoRelated(String char1, String char2, List<String> aux) throws UnknownCharacterExc, NotRelatedExc{
        if(!characters.containsKey(char1)) {
            aux.add(0, char1);
            throw new UnknownCharacterExc();
        }
        if(!characters.containsKey(char2)){
            aux.add(0, char2);
            throw new UnknownCharacterExc();
        }

        Character auxChar1 = characters.get(char1);
        Character auxChar2 = characters.get(char2);

        Stack<Character> stack1 = familyBFS(auxChar1, auxChar2);
        Stack<Character> stack2 = familyBFS(auxChar2, auxChar1);

        if(stack1 == null && stack2 == null)
            throw new NotRelatedExc();

        if(stack1 != null) {
            return stack1;
        }else
            return stack2;
    }

    public boolean realChar(String charName) throws NotRealCharacterExc{
        Character auxChar = characters.get(charName);

        if(auxChar instanceof VirtualCharacter)
            throw new NotRealCharacterExc();

        return true;
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
            List<String> aux = new LinkedList<String>();
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

    private void incrementEpisodesNumber(){
        this.totalEpisodesNumber++;
    }

    private void incrementSeasonsNumber(){
        this.seasonsNumber++;
    }

    private Stack<Character> familyBFS(Character x, Character y) {

        Queue<Character> queue = new LinkedList<>();
        List<Character> visited = new LinkedList<>();
        Stack<Character> descendants = new Stack<>();

        visited.add(x);
        queue.add(x);

        while (!queue.isEmpty()) {
            Character v = queue.poll();
            for (Character child : v.getChildren()) {
                if (!visited.contains(child)) {
                    visited.add(child);
                    queue.add(child);
                    child.addParent(v);     //add a reference to the parent node
                }
                //when we find the desired node, trace back the descendants and add their names to a list
                if (child.equals(y)) {
                    while (child.getParent() != null) {
                        assert child.hasParent(child.getParent()) && child.getParent().hasChild(child) : child.getCharName();
                        descendants.add(child);
                        child = child.getParent();
                    }
                    descendants.add(child);
                    //clear the parent field in all the nodes
                    for (Character auxP : visited)
                        auxP.addParent(null);

                    return descendants;
                }
            }
        }
        return null;
    }
}
