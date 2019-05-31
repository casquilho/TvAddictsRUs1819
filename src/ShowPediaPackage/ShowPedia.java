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

/**
 * The interface Show pedia.
 */
public interface ShowPedia {

    /**
     * Gets current show.
     *
     * @return the current show
     * @throws NoShowSelectedExc the no show selected exc
     */
    Show getCurrentShow() throws NoShowSelectedExc;

    /**
     * Add show.
     *
     * @param showName the show name
     * @throws ExistentShowExc the existent show exc
     */
    void addShow(String showName) throws ExistentShowExc;

    /**
     * Switch show show.
     *
     * @param showName the show name
     * @return the show
     * @throws UnknownShowExc the unknown show exc
     */
    Show switchShow(String showName)throws UnknownShowExc;

    /**
     * Add season to current show.
     *
     * @throws NoShowSelectedExc the no show selected exc
     */
    void addSeasonToCurrentShow() throws NoShowSelectedExc;

    /**
     * Add episode to given season int.
     *
     * @param seasonNumber the season number
     * @param epiName      the epi name
     * @return the int
     * @throws NoShowSelectedExc the no show selected exc
     * @throws UnknownSeasonExc  the unknown season exc
     */
    int addEpisodeToGivenSeason(int seasonNumber, String epiName) throws NoShowSelectedExc, UnknownSeasonExc;

    /**
     * Add real character.
     *
     * @param charName  the char name
     * @param actorName the actor name
     * @param cost      the cost
     * @throws NoShowSelectedExc     the no show selected exc
     * @throws DuplicateCharacterExc the duplicate character exc
     * @throws InvalidSalaryExc      the invalid salary exc
     */
    void addRealCharacter(String charName, String actorName, int cost) throws NoShowSelectedExc, DuplicateCharacterExc, InvalidSalaryExc;

    /**
     * Add cgi character.
     *
     * @param charName the char name
     * @param company  the company
     * @param cost     the cost
     * @throws NoShowSelectedExc     the no show selected exc
     * @throws DuplicateCharacterExc the duplicate character exc
     * @throws InvalidSalaryExc      the invalid salary exc
     */
    void addCGICharacter(String charName, String company, int cost) throws NoShowSelectedExc, DuplicateCharacterExc, InvalidSalaryExc;

    /**
     * Add relationship.
     *
     * @param parent the parent
     * @param child  the child
     * @param aux    the aux
     * @throws NoShowSelectedExc        the no show selected exc
     * @throws SameCharacterExc         the same character exc
     * @throws UnknownCharacterExc      the unknown character exc
     * @throws DuplicateRelationshipExc the duplicate relationship exc
     */
    void addRelationship(String parent, String child, List<String> aux) throws NoShowSelectedExc, SameCharacterExc, UnknownCharacterExc, DuplicateRelationshipExc;

    /**
     * Add romance.
     *
     * @param char1 the char 1
     * @param char2 the char 2
     * @param aux   the aux
     * @throws NoShowSelectedExc        the no show selected exc
     * @throws SameCharacterRomanceExc  the same character romance exc
     * @throws UnknownCharacterExc      the unknown character exc
     * @throws DuplicateRelationshipExc the duplicate relationship exc
     */
    void addRomance(String char1, String char2, List<String> aux) throws NoShowSelectedExc, SameCharacterRomanceExc, UnknownCharacterExc, DuplicateRelationshipExc;

    /**
     * Add event.
     *
     * @param episode    the episode
     * @param season     the season
     * @param characters the characters
     * @param event      the event
     * @throws NoShowSelectedExc     the no show selected exc
     * @throws NonExistentSeasonExc  the non existent season exc
     * @throws NonExistentEpisodeExc the non existent episode exc
     * @throws UnknownCharacterExc   the unknown character exc
     */
    void addEvent(int episode, int season, List<String> characters, String event) throws NoShowSelectedExc, NonExistentSeasonExc, NonExistentEpisodeExc, UnknownCharacterExc;

    /**
     * Add quote.
     *
     * @param season    the season
     * @param episode   the episode
     * @param charName  the char name
     * @param quoteText the quote text
     * @throws NoShowSelectedExc     the no show selected exc
     * @throws NonExistentEpisodeExc the non existent episode exc
     * @throws NonExistentSeasonExc  the non existent season exc
     * @throws UnknownCharacterExc   the unknown character exc
     */
    void addQuote(int season, int episode, String charName, String quoteText) throws NoShowSelectedExc, NonExistentEpisodeExc, NonExistentSeasonExc, UnknownCharacterExc;

    /**
     * Character resume iterator.
     *
     * @param charName the char name
     * @param auxList  the aux list
     * @return the iterator
     * @throws UnknownCharacterExc the unknown character exc
     */
    Iterator<List<Event>> characterResume(String charName, List<Iterator<Character>> auxList) throws UnknownCharacterExc;

    /**
     * How are these two related stack.
     *
     * @param char1 the char 1
     * @param char2 the char 2
     * @param aux   the aux
     * @return the stack
     * @throws NoShowSelectedExc    the no show selected exc
     * @throws DuplicateCharRelated the duplicate char related
     * @throws UnknownCharacterExc  the unknown character exc
     * @throws NotRelatedExc        the not related exc
     */
    Stack<Character> howAreTheseTwoRelated(String char1, String char2, List<String> aux) throws NoShowSelectedExc, DuplicateCharRelated, UnknownCharacterExc, NotRelatedExc;

    /**
     * Gets famous quotes.
     *
     * @param quote the quote
     * @return the famous quotes
     * @throws NoShowSelectedExc the no show selected exc
     * @throws UnknownQuoteExc   the unknown quote exc
     */
    Iterator<String> getFamousQuotes(String quote) throws NoShowSelectedExc, UnknownQuoteExc;

    /**
     * Also appears on iterator.
     *
     * @param charName the char name
     * @return the iterator
     * @throws NoShowSelectedExc   the no show selected exc
     * @throws UnknownCharacterExc the unknown character exc
     * @throws NotRealCharacterExc the not real character exc
     */
    Iterator<String> alsoAppearsOn(String charName) throws NoShowSelectedExc, UnknownCharacterExc, NotRealCharacterExc;

    /**
     * Most romantic iterator.
     *
     * @param actorName the actor name
     * @return the iterator
     * @throws UnknownActorExc     the unknown actor exc
     * @throws LoveIsntInTheAirExc the love isnt in the air exc
     */
    Iterator<Actor> mostRomantic(String actorName) throws UnknownActorExc, LoveIsntInTheAirExc;

    /**
     * King of cgi cgi company.
     *
     * @return the cgi company
     * @throws NoVirtualCharactersExc the no virtual characters exc
     */
    CGICompany kingOfCgi()throws NoVirtualCharactersExc;

    /**
     * Gets episode name.
     *
     * @param season  the season
     * @param episode the episode
     * @return the episode name
     * @throws NonExistentSeasonExc  the non existent season exc
     * @throws NonExistentEpisodeExc the non existent episode exc
     */
    String getEpisodeName(int season, int episode) throws NonExistentSeasonExc, NonExistentEpisodeExc;

    /**
     * Gets actor.
     *
     * @param actorName the actor name
     * @return the actor
     */
    Actor getActor(String actorName);

    /**
     * Gets events it.
     *
     * @param season  the season
     * @param episode the episode
     * @return the events it
     * @throws InvalidSeasonInterval the invalid season interval
     * @throws NoShowSelectedExc     the no show selected exc
     */
    Iterator<List<Event>> getEventsIt(int season, int episode) throws InvalidSeasonInterval, NoShowSelectedExc;
}
