/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package ShowPediaPackage;

import ActorCharacterPackage.Actor;
import ActorCharacterPackage.ActorClass;
import ActorCharacterPackage.Character;
import CGICompaniesPackage.*;
import EventPackage.Event;
import MyExceptionsPackage.*;
import ShowPackage.*;
import java.util.*;

public class ShowPediaClass implements ShowPedia {

    private Show currentShow;
    private Map<String, Show> myShows;
    private Map<String, Actor> actors;
    private List<CGICompany> companiesCGI;


    public ShowPediaClass(){
        this.currentShow = null;
        this.myShows = new HashMap<String, Show>();
        this.actors  = new HashMap<String, Actor>();
        this.companiesCGI = new LinkedList<CGICompany>();


    }


    public Show getCurrentShow() throws NoShowSelectedExc{
        if(currentShow == null)
            throw new NoShowSelectedExc();

        return currentShow;
    }

    public String printCurrentShow() throws NoShowSelectedExc{
        if(currentShow == null)
            throw new NoShowSelectedExc();

        return String.format("%s. Seasons: %d Episodes: %d",
                              currentShow.getName(),
                                            currentShow.getSeasonsNumber(),
                                                        currentShow.getTotalEpisodesNumber());
    }

    public void addShow(String showName) throws ExistentShowExc{
        if(myShows.containsKey(showName))
            throw new ExistentShowExc();

        Show newShow = new ShowClass(showName);
        myShows.put(showName, newShow);
        currentShow = myShows.get(showName);
        currentShow.addSeason();
    }

    public Show switchShow(String showName)throws UnknownShowExc {
        if(!myShows.containsKey(showName))
            throw new UnknownShowExc();

        return currentShow = myShows.get(showName);
    }

    public void addSeasonToCurrentShow() throws NoShowSelectedExc{
        if(currentShow == null)
            throw new NoShowSelectedExc();

        currentShow.addSeason();
    }

    public int addEpisodeToGivenSeason(int seasonNumber, String epiName) throws NoShowSelectedExc, UnknownSeasonExc {
        if(currentShow == null)
            throw new NoShowSelectedExc();

        return currentShow.addEpisode(seasonNumber, epiName);
    }

    public void addRealCharacter(String charName, String actorName, int cost) throws NoShowSelectedExc, DuplicateCharacterExc, InvalidSalaryExc {
        if(currentShow == null)
            throw new NoShowSelectedExc();

        Actor auxActor;
        //if the actor already exists, pass it the show's name
        if(actors.containsKey(actorName)) {
            auxActor = actors.get(actorName);
            auxActor.addShowName(currentShow.getName());
        }
        //create new actor, give it the show's name and insert into map
        else{
            auxActor = new ActorClass(actorName);
            auxActor.addShowName(currentShow.getName());
            actors.put(actorName, auxActor);
        }

        //add the character to the current show
        currentShow.addRealCharacter(charName, auxActor, cost);

        if(cost < 0)
            throw new InvalidSalaryExc();

        auxActor.incNumberOfRoles();
    }

    public void addCGICharacter(String charName, String company, int cost) throws NoShowSelectedExc, DuplicateCharacterExc, InvalidSalaryExc {
        if(currentShow == null)
            throw new NoShowSelectedExc();


        //add the character to the current show
        currentShow.addCGICharacter(charName, company, cost);

        if(cost < 0)
            throw new InvalidSalaryExc();

        //if the company already exists, do nothing. if it doesn't, create and insert it
        Iterator<CGICompany> it = companiesCGI.iterator();
        CGICompany aux;
        while (it.hasNext()){
            aux = it.next();
            if(aux.getName().equals(company))
                return;
        }
        companiesCGI.add(new CGICompanyClass(company));
    }

    public void addQuote(int season, int episode, String charName, String quoteText) throws NoShowSelectedExc, NonExistentEpisodeExc, NonExistentSeasonExc, UnknownCharacterExc {
        if(currentShow == null)
            throw new NoShowSelectedExc();

        currentShow.addQuote(season, episode, charName, quoteText, companiesCGI);
    }

    public Iterator<String> getFamousQuotes(String quote) throws NoShowSelectedExc, UnknownQuoteExc {
        if(currentShow == null)
            throw new NoShowSelectedExc();

        return currentShow.getFamousQuotes(quote);
    }

    public CGICompany kingOfCgi()throws NoVirtualCharactersExc{
        if(companiesCGI.size() == 0)
            throw new NoVirtualCharactersExc();
        companiesCGI.sort(new CompanyComparator());
        return companiesCGI.get(0);
    }

    public Iterator<String> alsoAppearsOn(String charName) throws NoShowSelectedExc, UnknownCharacterExc, NotRealCharacterExc{
        if(currentShow == null)
            throw new NoShowSelectedExc();

        if(currentShow.realChar(charName))
            return (actors.get(currentShow.getActorNameFromCharName(charName))).StarsOn();
        /*if(actors.containsKey(currentShow.getActorNameFromCharName(charName)))
            return (actors.get(currentShow.getActorNameFromCharName(charName))).StarsOn();*/

        return null;
    }

    public void addEvent(int episode, int season, List<String> characters, String event) throws NoShowSelectedExc, NonExistentSeasonExc, NonExistentEpisodeExc, UnknownCharacterExc {
        if(currentShow == null)
            throw new NoShowSelectedExc();

        currentShow.addEvent(episode, season, characters, event, companiesCGI);
    }

    public Iterator<List<Event>> characterResume(String charName, List<Iterator<Character>> auxList) throws UnknownCharacterExc{
        return currentShow.getCharacterResume(charName, auxList);
    }

    public Actor getActor(String actorName){
        if(!actors.containsKey(actorName))
            return null;
        return actors.get(actorName);
    }

    public void addRelationship(String parent, String child, List<String> aux) throws NoShowSelectedExc, SameCharacterExc, UnknownCharacterExc, DuplicateRelationshipExc {
        if(currentShow == null)
            throw new NoShowSelectedExc();
        if(parent.equals(child))
            throw new SameCharacterExc();

        currentShow.addRelationship(parent, child, aux);
    }

    public void addRomance(String char1, String char2, List<String> aux) throws NoShowSelectedExc, SameCharacterRomanceExc, UnknownCharacterExc, DuplicateRelationshipExc {
        if(currentShow == null)
            throw new NoShowSelectedExc();
        if(char1.equals(char2))
            throw new SameCharacterRomanceExc();

        currentShow.addRomance(char1, char2, aux);
    }

    public Stack<String> howAreTheseTwoRelated(String char1, String char2, List<String> aux) throws NoShowSelectedExc, DuplicateCharRelated, UnknownCharacterExc, NotRelatedExc{

        if(currentShow == null)
            throw new NoShowSelectedExc();
        if(char1.equals(char2))
            throw new DuplicateCharRelated();

        return currentShow.howAreTheseTwoRelated(char1, char2, aux);
    }

    public String getEpisodeName(int season, int episode) throws NonExistentSeasonExc, NonExistentEpisodeExc{
        return currentShow.getEpisodeName(season-1, episode-1);
    }

    public Iterator<List<Event>> getEventsIt(int seasonStart, int seasonEnd) throws InvalidSeasonInterval, NoShowSelectedExc {
        if(currentShow == null)
            throw new NoShowSelectedExc();
        if(currentShow.getSeasonsNumber() < seasonEnd || currentShow.getSeasonsNumber() < seasonStart || seasonStart < 1)
            throw new InvalidSeasonInterval();

        return currentShow.getEvents();
    }


}
