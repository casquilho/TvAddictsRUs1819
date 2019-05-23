/**
 * @author Joao Casquilho 54440
 */

package ShowPediaPackage;

import ActorCharacterPackage.Actor;
import ActorCharacterPackage.ActorClass;
import CGICompaniesPackage.*;
import MyExceptionsPackage.*;
import ShowPackage.*;
import java.util.*;

public class ShowPediaClass implements ShowPedia {

    private Map<String, Show> myShows;
    private List<CGICompany> companiesCGI;
    private Map<String,LinkedList<String>> actorAppearences;
    private Show currentShow;
    private Map<String, Actor> actors;

    public ShowPediaClass(){
        this.myShows = new HashMap<String, Show>();
        this.companiesCGI = new LinkedList<CGICompany>();
        this.actorAppearences = new HashMap<String,LinkedList<String>>();
        this.currentShow = null;
        this.actors = new HashMap<String, Actor>();
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
    }

    public void switchShow(String showName)throws UnknownShowExc {
        if(!myShows.containsKey(showName))
            throw new UnknownShowExc();

        currentShow = myShows.get(showName);
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

    public void addRealCharacter(String charName, String actorName, int cost) throws NoShowSelectedExc, DuplicateCharacterExc {
        if(currentShow == null)
            throw new NoShowSelectedExc();

        //add the character to the current show
        currentShow.addRealCharacter(charName, actorName, cost);

        //create new actor, give it the show's name and insert into map
        Actor aux = new ActorClass(actorName);
        aux.addShowName(currentShow.getName());
        actors.put(actorName, aux);

    }

    public void addCGICharacter(String charName, String company, int cost) throws NoShowSelectedExc, DuplicateCharacterExc {
        if(currentShow == null)
            throw new NoShowSelectedExc();

        //add the character to the current show
        currentShow.addCGICharacter(charName, company, cost);

        //if the company already exists, do nothing. if it doesn't, create and insert it
        Iterator it = companiesCGI.iterator();
        CGICompany aux;
        while (it.hasNext()){
            aux = (CGICompany) it.next();
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






}
