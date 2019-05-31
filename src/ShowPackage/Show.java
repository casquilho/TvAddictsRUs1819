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
 * The Show's interface.
 */
public interface Show {

    /**
     * Gets the show's name.
     *
     * @return show name
     */
    String getName();

    /**
     * Gets the name of a given episode in a given season in the current show.
     *
     * @param season  the season number
     * @param episode the episode number
     * @return episode name
     * @throws NonExistentEpisodeExc if the episode's number passed does not correspond to an existing episode in the given season
     * @throws NonExistentSeasonExc  if the season's number passed does not correspond to an existing season in the current show
     */
    String getEpisodeName(int season, int episode) throws NonExistentEpisodeExc, NonExistentSeasonExc;

    /**
     * Gets the actor's name from a character's name who he plays name.
     *
     * @param charName character name
     * @return actor name
     * @throws UnknownCharacterExc if the character's name passed does not correspond to nd existing character in the current show
     */
    String getActorNameFromCharName(String charName) throws UnknownCharacterExc;

    /**
     * Gets the number of seasons in the current show.
     *
     * @return seasons number
     */
    int getSeasonsNumber();

    /**
     * Gets the total number of episodes in the current show.
     *
     * @return episodes number
     */
    int getTotalEpisodesNumber();

    /**
     * Gets the number of parents from a character with a given name.
     *
     * @param charName character name
     * @return parents number
     */
    int getNumParentsFromName(String charName);

    /**
     * Gets the number of children from a character with a given name.
     *
     * @param charName character name
     * @return children number
     */
    int getNumChildrenFromName(String charName);

    /**
     * Gets a season from the current show.
     *
     * @param seasonNumber season number
     * @param seasonStart  season start number, can be used for an interval validation or just given the same number as seasonNumber
     * @param seasonEnd    season end number, can be used for an interval validation or just given the same number as seasonNumber
     * @return season
     * @throws InvalidSeasonInterval if any of the values passed represent and invalid season
     */
    Season getSeason(int seasonNumber, int seasonStart, int seasonEnd) throws InvalidSeasonInterval;

    /**
     * Gets all the events from one episode.
     *
     * @param key the key - the key passed should be a concatenation of the season and episode's numbers (season+""+episode)
     * @return iterator to the events from the episode
     */
    Iterator<Event> getEventsFromEpisode(String key);

    /**
     * Gets famous quotes.
     *
     * @param quoteText the quote text
     * @return iterator of names of the characters who said the given quote
     * @throws UnknownQuoteExc if the quote text is not registered in the show
     */
    Iterator<String> getFamousQuotes(String quoteText) throws UnknownQuoteExc;

    /**
     * Returns the iterator to lists containing the character's events, each list represents one episode.
     *
     * @param charName the character name
     * @param auxList  auxiliary list used to pass different iterators used in the main class
     * @return the iterator to the lists of events
     * @throws UnknownCharacterExc if the character passed is not registered in the show
     */
    Iterator<List<Event>> getCharacterResume(String charName, List<Iterator<Character>> auxList) throws UnknownCharacterExc;

    /**
     * Returns the iterator to lists containing the all the events in the show, each list represents one episode.
     *
     * @return the iterator to the lists of events
     */
    Iterator<List<Event>> getEvents();

    /**
     * Add a new episode to the given season.
     *
     * @param seasonsNumber season number
     * @param epiName       episode name
     * @return number of the episode
     * @throws UnknownSeasonExc if the season's number passed does not correspond to an existing season in the current show
     */
    int addEpisode(int seasonsNumber, String epiName) throws UnknownSeasonExc;

    /**
     * Add a new season to current show. The season is added sequentially after the last existing season in the current show.
     */
    void addSeason();

    /**
     * Add real character to the current show. If the actor name passed doesn't correspond to any known actor, then it too is created.
     *
     * @param charName  the character name
     * @param actor     the actor name who plays the character
     * @param cost      the cost per episode of the character
     * @throws DuplicateCharacterExc if the character is already registered in the current show
     */
    void addRealCharacter(String charName, Actor actor, int cost) throws DuplicateCharacterExc;

    /**
     * Add CGI character to the current show. If the company name passed doesn't correspond to any known company, then it too is created.
     *
     * @param charName the character name
     * @param company  the company name
     * @param cost     the cost per season of the character
     * @throws DuplicateCharacterExc if the character is already registered in the current show
     */
    void addCGICharacter(String charName, String company, int cost) throws DuplicateCharacterExc;

    /**
     * Add a quote by a character to the selected season and episode.
     *
     * @param season    the season number
     * @param episode   the episode number
     * @param charName  the character's name
     * @param quoteText the quote text
     * @param companies the cgi companies list
     * @throws NonExistentSeasonExc  if the season's number passed does not correspond to an existing season in the current show
     * @throws NonExistentEpisodeExc if the episode's number passed does not correspond to an existing episode in the given season
     * @throws UnknownCharacterExc   if the character's name passed does not correspond to nd existing character in the current show
     */
    void addQuote(int season, int episode, String charName, String quoteText, List<CGICompany> companies) throws NonExistentSeasonExc, NonExistentEpisodeExc, UnknownCharacterExc;

    /**
     * Add an event to selected season and episode. the event is stored in a map in both the ShowClass and in the characters that take part in it.
     * The key is the concatenation of the season and episode numbers which stores a list with all the events in that season and episode.
     *
     * @param episode    the episode
     * @param season     the season
     * @param characters list of the characters that take part in the event
     * @param event      the event
     * @param companiesCGI the cgi companies list
     * @throws NonExistentSeasonExc  if the season's number passed does not correspond to an existing season in the current show
     * @throws NonExistentEpisodeExc if the episode's number passed does not correspond to an existing episode in the given season
     * @throws UnknownCharacterExc   if the character's name passed does not correspond to nd existing character in the current show
     */
    void addEvent(int episode, int season, List<String> characters, String event, List<CGICompany> companiesCGI) throws NonExistentSeasonExc, NonExistentEpisodeExc, UnknownCharacterExc;

    /**
     * Add a parent-child relationship between two characters.
     *
     * @param parent the parent name
     * @param child  the child name
     * @param aux    auxiliary list used to pass values to main class
     * @throws UnknownCharacterExc      if the character's name passed does not correspond to nd existing character in the current show
     * @throws DuplicateRelationshipExc if the characters passed are already in a parent-child relationship
     */
    void addRelationship(String parent, String child, List<String> aux) throws UnknownCharacterExc, DuplicateRelationshipExc;

    /**
     * Add a romantic relationship between two characters.
     *
     * @param char1 the name of one of the lovers
     * @param char2 the name of the other lover
     * @param aux   auxiliary list used to pass values to main class
     * @throws UnknownCharacterExc      if the character's name passed does not correspond to nd existing character in the current show
     * @throws DuplicateRelationshipExc if the characters passed are already in a romantic relationship
     */
    void addRomance(String char1, String char2, List<String> aux) throws UnknownCharacterExc, DuplicateRelationshipExc;

    /**
     * Returns a stack of characters containing the order in which the two character given are related.
     *
     * @param char1 name of one of the characters
     * @param char2 name of the other character
     * @param aux    auxiliary list used to pass values to main class
     * @return the stack of related characters or null
     * @throws UnknownCharacterExc if the character's name passed does not correspond to nd existing character in the current show
     * @throws NotRelatedExc       is there is no family relationship between the two characters
     */
    Stack<Character> howAreTheseTwoRelated(String char1, String char2, List<String> aux) throws UnknownCharacterExc, NotRelatedExc;

    /**
     * Check whether the given character is a real or virtual character
     *
     * @param charName the character's name
     * @return the boolean
     * @throws NotRealCharacterExc if the character is not and instanceOf realCharacter
     */
    boolean realChar(String charName) throws NotRealCharacterExc;


}