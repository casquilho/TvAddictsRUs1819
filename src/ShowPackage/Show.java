/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package ShowPackage;

import ActorCharacterPackage.Actor;
import ActorCharacterPackage.Character;
import CGICompaniesPackage.CGICompany;
import EventPackage.Event;
import MyExceptionsPackage.*;
import SeasonPackage.Season;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * The interface Show.
 */
public interface Show {

    /**
     * Gets name.
     *
     * @return the name
     */
    String getName();

    /**
     * Gets episode name.
     *
     * @param season  the season
     * @param episode the episode
     * @return the episode name
     * @throws NonExistentEpisodeExc the non existent episode exc
     * @throws NonExistentSeasonExc  the non existent season exc
     */
    String getEpisodeName(int season, int episode) throws NonExistentEpisodeExc, NonExistentSeasonExc;

    /**
     * Gets actor name from char name.
     *
     * @param charName the char name
     * @return the actor name from char name
     * @throws UnknownCharacterExc the unknown character exc
     */
    String getActorNameFromCharName(String charName) throws UnknownCharacterExc;

    /**
     * Gets seasons number.
     *
     * @return the seasons number
     */
    int getSeasonsNumber();

    /**
     * Gets total episodes number.
     *
     * @return the total episodes number
     */
    int getTotalEpisodesNumber();

    /**
     * Gets num parents from name.
     *
     * @param charName the char name
     * @return the num parents from name
     */
    int getNumParentsFromName(String charName);

    /**
     * Gets num children from name.
     *
     * @param charName the char name
     * @return the num children from name
     */
    int getNumChildrenFromName(String charName);

    /**
     * Gets season.
     *
     * @param seasonNumber the season number
     * @param seasonStart  the season start
     * @param seasonEnd    the season end
     * @return the season
     * @throws InvalidSeasonInterval the invalid season interval
     */
    Season getSeason(int seasonNumber, int seasonStart, int seasonEnd) throws InvalidSeasonInterval;

    /**
     * Gets events from episode.
     *
     * @param key the key
     * @return the events from episode
     */
    Iterator<Event> getEventsFromEpisode(String key);

    /**
     * Gets famous quotes.
     *
     * @param quoteText the quote text
     * @return the famous quotes
     * @throws UnknownQuoteExc the unknown quote exc
     */
    Iterator<String> getFamousQuotes(String quoteText) throws UnknownQuoteExc;

    /**
     * Gets character resume.
     *
     * @param charName the char name
     * @param auxList  the aux list
     * @return the character resume
     * @throws UnknownCharacterExc the unknown character exc
     */
    Iterator<List<Event>> getCharacterResume(String charName, List<Iterator<Character>> auxList) throws UnknownCharacterExc;

    /**
     * Gets events.
     *
     * @return the events
     */
    Iterator<List<Event>> getEvents();

    /**
     * Add episode int.
     *
     * @param seasonsNumber the seasons number
     * @param epiName       the epi name
     * @return the int
     * @throws UnknownSeasonExc the unknown season exc
     */
    int addEpisode(int seasonsNumber, String epiName) throws UnknownSeasonExc;

    /**
     * Add season.
     */
    void addSeason();

    /**
     * Add real character.
     *
     * @param charName the char name
     * @param actor    the actor
     * @param cost     the cost
     * @throws DuplicateCharacterExc the duplicate character exc
     */
    void addRealCharacter(String charName, Actor actor, int cost) throws DuplicateCharacterExc;

    /**
     * Add cgi character.
     *
     * @param charName the char name
     * @param company  the company
     * @param cost     the cost
     * @throws DuplicateCharacterExc the duplicate character exc
     */
    void addCGICharacter(String charName, String company, int cost) throws DuplicateCharacterExc;

    /**
     * Add quote.
     *
     * @param season    the season
     * @param episode   the episode
     * @param charName  the char name
     * @param quoteText the quote text
     * @param companies the companies
     * @throws NonExistentSeasonExc  the non existent season exc
     * @throws NonExistentEpisodeExc the non existent episode exc
     * @throws UnknownCharacterExc   the unknown character exc
     */
    void addQuote(int season, int episode, String charName, String quoteText, List<CGICompany> companies) throws NonExistentSeasonExc, NonExistentEpisodeExc, UnknownCharacterExc;

    /**
     * Add event.
     *
     * @param episode      the episode
     * @param season       the season
     * @param characters   the characters
     * @param event        the event
     * @param companiesCGI the companies cgi
     * @throws NonExistentSeasonExc  the non existent season exc
     * @throws NonExistentEpisodeExc the non existent episode exc
     * @throws UnknownCharacterExc   the unknown character exc
     */
    void addEvent(int episode, int season, List<String> characters, String event, List<CGICompany> companiesCGI) throws NonExistentSeasonExc, NonExistentEpisodeExc, UnknownCharacterExc;

    /**
     * Add relationship.
     *
     * @param parent the parent
     * @param child  the child
     * @param aux    the aux
     * @throws UnknownCharacterExc      the unknown character exc
     * @throws DuplicateRelationshipExc the duplicate relationship exc
     */
    void addRelationship(String parent, String child, List<String> aux) throws UnknownCharacterExc, DuplicateRelationshipExc;

    /**
     * Add romance.
     *
     * @param char1 the char 1
     * @param char2 the char 2
     * @param aux   the aux
     * @throws UnknownCharacterExc      the unknown character exc
     * @throws DuplicateRelationshipExc the duplicate relationship exc
     */
    void addRomance(String char1, String char2, List<String> aux) throws UnknownCharacterExc, DuplicateRelationshipExc;

    /**
     * How are these two related stack.
     *
     * @param char1 the char 1
     * @param char2 the char 2
     * @param aux   the aux
     * @return the stack
     * @throws UnknownCharacterExc the unknown character exc
     * @throws NotRelatedExc       the not related exc
     */
    Stack<Character> howAreTheseTwoRelated(String char1, String char2, List<String> aux) throws UnknownCharacterExc, NotRelatedExc;

    /**
     * Real char boolean.
     *
     * @param charName the char name
     * @return the boolean
     * @throws NotRealCharacterExc the not real character exc
     */
    boolean realChar(String charName) throws NotRealCharacterExc;


}