package ShowPediaPackage;

import CGICompaniesPackage.CGICompany;
import MyExceptionsPackage.*;
import ShowPackage.*;

import java.util.Iterator;
import java.util.List;

public interface ShowPedia {

    Show getCurrentShow() throws NoShowSelectedExc;

    String printCurrentShow() throws NoShowSelectedExc;

    void addShow(String showName) throws ExistentShowExc;

    void switchShow(String showName)throws UnknownShowExc;

    void addSeasonToCurrentShow() throws NoShowSelectedExc;

    int addEpisodeToGivenSeason(int seasonNumber, String epiName) throws NoShowSelectedExc, UnknownSeasonExc;

    void addRealCharacter(String charName, String actorName, int cost) throws NoShowSelectedExc, DuplicateCharacterExc;

    void addCGICharacter(String charName, String company, int cost) throws NoShowSelectedExc, DuplicateCharacterExc;

    void addQuote(int season, int episode, String charName, String quoteText) throws NoShowSelectedExc, NonExistentEpisodeExc, NonExistentSeasonExc, UnknownCharacterExc;

    Iterator getFamousQuotes(String quote) throws NoShowSelectedExc, UnknownQuoteExc;

    CGICompany kingOfCgi()throws NoVirtualCharactersExc;

    Iterator alsoAppearsOn(String charName) throws NoShowSelectedExc, UnknownCharacterExc;

    void addEvent(int episode, int season, List<String> characters, String event) throws NoShowSelectedExc, NonExistentSeasonExc, NonExistentEpisodeExc, UnknownCharacterExc;

    Iterator characterResume(String charName) throws UnknownCharacterExc;
}
