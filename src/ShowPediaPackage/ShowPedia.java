/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package ShowPediaPackage;

import ActorCharacterPackage.*;
import ActorCharacterPackage.Character;
import CGICompaniesPackage.CGICompany;
import EventPackage.Event;
import MyExceptionsPackage.*;
import ShowPackage.*;
import java.util.*;

public interface ShowPedia {

    Show getCurrentShow() throws NoShowSelectedExc;

    void addShow(String showName) throws ExistentShowExc;

    Show switchShow(String showName)throws UnknownShowExc;

    void addSeasonToCurrentShow() throws NoShowSelectedExc;

    int addEpisodeToGivenSeason(int seasonNumber, String epiName) throws NoShowSelectedExc, UnknownSeasonExc;

    void addRealCharacter(String charName, String actorName, int cost) throws NoShowSelectedExc, DuplicateCharacterExc, InvalidSalaryExc;

    void addCGICharacter(String charName, String company, int cost) throws NoShowSelectedExc, DuplicateCharacterExc, InvalidSalaryExc;

    void addRelationship(String parent, String child, List<String> aux) throws NoShowSelectedExc, SameCharacterExc, UnknownCharacterExc, DuplicateRelationshipExc;

    void addRomance(String char1, String char2, List<String> aux) throws NoShowSelectedExc, SameCharacterRomanceExc, UnknownCharacterExc, DuplicateRelationshipExc;

    void addEvent(int episode, int season, List<String> characters, String event) throws NoShowSelectedExc, NonExistentSeasonExc, NonExistentEpisodeExc, UnknownCharacterExc;

    void addQuote(int season, int episode, String charName, String quoteText) throws NoShowSelectedExc, NonExistentEpisodeExc, NonExistentSeasonExc, UnknownCharacterExc;

    Iterator<List<Event>> characterResume(String charName, List<Iterator<Character>> auxList) throws UnknownCharacterExc;

    Stack<Character> howAreTheseTwoRelated(String char1, String char2, List<String> aux) throws NoShowSelectedExc, DuplicateCharRelated, UnknownCharacterExc, NotRelatedExc;

    Iterator<String> getFamousQuotes(String quote) throws NoShowSelectedExc, UnknownQuoteExc;

    Iterator<String> alsoAppearsOn(String charName) throws NoShowSelectedExc, UnknownCharacterExc, NotRealCharacterExc;

    Iterator<Actor> mostRomantic(String actorName) throws UnknownActorExc, LoveIsntInTheAirExc;

    CGICompany kingOfCgi()throws NoVirtualCharactersExc;

    String getEpisodeName(int season, int episode) throws NonExistentSeasonExc, NonExistentEpisodeExc;

    Actor getActor(String actorName);

    Iterator<List<Event>> getEventsIt(int season, int episode) throws InvalidSeasonInterval, NoShowSelectedExc;
}
