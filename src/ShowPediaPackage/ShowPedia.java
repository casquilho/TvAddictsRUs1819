package ShowPediaPackage;

import MyExceptionsPackage.*;
import ShowPackage.*;

public interface ShowPedia {

    Show getCurrentShow() throws NoShowSelectedExc;

    String printCurrentShow() throws NoShowSelectedExc;

    void addShow(String showName) throws ExistentShowExc;

    void switchShow(String showName)throws UnknownShowExc;

    void addSeasonToCurrentShow() throws NoShowSelectedExc;

    int addEpisodeToGivenSeason(int seasonNumber, String epiName) throws NoShowSelectedExc, UnknownSeasonExc;

}
