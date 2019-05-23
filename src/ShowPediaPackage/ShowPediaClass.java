/**
 * @author Joao Casquilho 54440
 */

package ShowPediaPackage;

import ActorCharacterPackage.Character;
import CGICompaniesPackage.*;
import MyExceptionsPackage.*;
import ShowPackage.*;
import javafx.collections.transformation.SortedList;

import java.util.*;

public class ShowPediaClass implements ShowPedia {

    private Map<String, Show> myShows;
    private List<CGICompany> companiesCGI;
    private Map<String,LinkedList<String>> actorAppearences;
    private Show currentShow;

    public ShowPediaClass(){
        this.myShows = new HashMap<String, Show>();
        this.companiesCGI = new LinkedList<CGICompany>();
        this.actorAppearences = new HashMap<String,LinkedList<String>>();
        this.currentShow = null;
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

        currentShow.addRealCharacter(charName, actorName, cost);

    }

    public void addCGICharacter(String charName, String company, int cost) throws NoShowSelectedExc, DuplicateCharacterExc {
        if(currentShow == null)
            throw new NoShowSelectedExc();

        currentShow.addCGICharacter(charName, company, cost);
    }

    public void addQuote(int season, int episode, String charName, String quoteText) throws NoShowSelectedExc, NonExistentEpisodeExc, NonExistentSeasonExc, UnknownCharacterExc {
        if(currentShow == null)
            throw new NoShowSelectedExc();

        currentShow.addQuote(season, episode, charName, quoteText, companiesCGI);
    }






}
