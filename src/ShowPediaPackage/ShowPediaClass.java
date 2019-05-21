/**
 * @author Joao Casquilho 54440
 */

package ShowPediaPackage;

import MyExceptionsPackage.*;
import ShowPackage.*;
import java.util.*;

public class ShowPediaClass implements ShowPedia {

    private Map<String,Show> myShows;
    private Show currentShow;

    public ShowPediaClass(){
        this.myShows = new HashMap<String, Show>();
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

        return String.format("%s. Seasons: %s Episodes: %s",
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
}
