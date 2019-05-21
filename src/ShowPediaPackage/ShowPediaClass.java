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


    public String getCurrentShow() throws NoShowSelectedExc{
        if(currentShow == null)
            throw new NoShowSelectedExc();

        return currentShow.getName()+". Seasons: "+ currentShow.getSeasonsNumber()+" Episodes: "+ currentShow.getTotalEpisodesNumber();
    }

    public void addShow(String showName) throws ExistentShowExc{
        if(myShows.containsKey(showName))
            throw new ExistentShowExc();

        Show newShow = new ShowClass(showName);
        myShows.put(showName, newShow);
    }

    public void swtichShow(String showName)throws UnknownShowExc {
        if(!myShows.containsKey(showName))
            throw new UnknownShowExc();

        currentShow = myShows.get(showName);
    }
}
