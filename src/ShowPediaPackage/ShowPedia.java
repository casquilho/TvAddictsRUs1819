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
 * The ShowPedia interface.
 */
public interface ShowPedia {

    /**
     * Gets the currently selected show.
     *
     * @return the current show
     * @throws NoShowSelectedExc if no show is selected.
     */
    Show getCurrentShow() throws NoShowSelectedExc;

    /**
     * Adds a new show to the showPedia.
     *
     * @param showName the show name
     * @throws ExistentShowExc if there is already a registered show with this showName.
     */
    void addShow(String showName) throws ExistentShowExc;

    /**
     * Switchs the focus to the show with the name passed.
     *
     * @param showName the show name
     * @return the selected show
     * @throws UnknownShowExc if there is no show registered with the given name.
     */
    Show switchShow(String showName)throws UnknownShowExc;

    /**
     * Add a new season to current show. The season is added sequentially after the last existing season in the current show.
     *
     * @throws NoShowSelectedExc if there is no show selected.
     */
    void addSeasonToCurrentShow() throws NoShowSelectedExc;

    /**
     * Add episode to given season. The episode is added sequentially after the last existing episode in the given season.
     *
     * @param seasonNumber the season number
     * @param epiName      the episode name
     * @return the int
     * @throws NoShowSelectedExc if there is no show selected.
     * @throws UnknownSeasonExc  if there is no season with the number passed.
     */
    int addEpisodeToGivenSeason(int seasonNumber, String epiName) throws NoShowSelectedExc, UnknownSeasonExc;

    /**
     * Add real character to the current show. If the actor name passed doesn't correspond to any known actor, then it too is created.
     *
     * @param charName  the character name
     * @param actorName the actor name
     * @param cost      the cost per episode of the character
     * @throws NoShowSelectedExc     if there is no show selected
     * @throws DuplicateCharacterExc if the character is already registered in the current show
     * @throws InvalidSalaryExc      if the cost is a negative integer
     */
    void addRealCharacter(String charName, String actorName, int cost) throws NoShowSelectedExc, DuplicateCharacterExc, InvalidSalaryExc;

    /**
     * Add CGI character to the current show. If the company name passed doesn't correspond to any known company, then it too is created.
     *
     * @param charName the character name
     * @param company  the company name
     * @param cost     the cost per season of the character
     * @throws NoShowSelectedExc     if there is no show selected.
     * @throws DuplicateCharacterExc if the character is already registered in the current show
     * @throws InvalidSalaryExc      if the cost is a negative integer
     */
    void addCGICharacter(String charName, String company, int cost) throws NoShowSelectedExc, DuplicateCharacterExc, InvalidSalaryExc;

    /**
     * Add a parent-child relationship between two characters.
     *
     * @param parent the parent name
     * @param child  the child name
     * @param aux    auxiliary list used to pass values to main class
     * @throws NoShowSelectedExc        if there is no show selected.
     * @throws SameCharacterExc         if the names passed are equal
     * @throws UnknownCharacterExc      if one of the names passed do not correspond to any registered character
     * @throws DuplicateRelationshipExc if the characters passed are already in a parent-child relationship
     */
    void addRelationship(String parent, String child, List<String> aux) throws NoShowSelectedExc, SameCharacterExc, UnknownCharacterExc, DuplicateRelationshipExc;

    /**
     * Add a romantic relationship between two characters.
     *
     * @param char1 the name of one of the lovers
     * @param char2 the name of the other lover
     * @param aux   auxiliary list used to pass values to main class
     * @throws NoShowSelectedExc        if there is no show selected.
     * @throws SameCharacterRomanceExc  if the names passed are equal
     * @throws UnknownCharacterExc      if one of the names passed do not correspond to any registered character
     * @throws DuplicateRelationshipExc if the characters passed are already in a romantic relationship
     */
    void addRomance(String char1, String char2, List<String> aux) throws NoShowSelectedExc, SameCharacterRomanceExc, UnknownCharacterExc, DuplicateRelationshipExc;

    /**
     * Add an event to selected season and episode. the event is stored in a map in both the ShowClass and in the characters that take part in it.
     * The key is the concatenation of the season and episode numbers which stores a list with all the events in that season and episode.
     *
     * @param episode    the episode
     * @param season     the season
     * @param characters list of the characters that take part in the event
     * @param event      the event
     * @throws NoShowSelectedExc     if there is no show selected
     * @throws NonExistentSeasonExc  if there is no season with the number passed in the current show
     * @throws NonExistentEpisodeExc if there is no episode with the number passed in the selected season
     * @throws UnknownCharacterExc   if one of the characters passed is not registered in the show
     */
    void addEvent(int episode, int season, List<String> characters, String event) throws NoShowSelectedExc, NonExistentSeasonExc, NonExistentEpisodeExc, UnknownCharacterExc;

    /**
     * Add a quote by a character to the selected season and episode.
     *
     * @param season    the season number
     * @param episode   the episode number
     * @param charName  the character's name
     * @param quoteText the quote text
     * @throws NoShowSelectedExc     if there is no show selected.
     * @throws NonExistentEpisodeExc if there is no episode with the number passed in the selected season
     * @throws NonExistentSeasonExc  if there is no season with the number passed in the current show
     * @throws UnknownCharacterExc   if the character passed is not registered in the show
     */
    void addQuote(int season, int episode, String charName, String quoteText) throws NoShowSelectedExc, NonExistentEpisodeExc, NonExistentSeasonExc, UnknownCharacterExc;

    /**
     * Returns the iterator containing the character's events.
     *
     * @param charName the character name
     * @param auxList  auxiliary list used to pass different iterators used in the main class
     * @return the iterator to the lists of events
     * @throws UnknownCharacterExc if the character passed is not registered in the show
     */
    Iterator<List<Event>> characterResume(String charName, List<Iterator<Character>> auxList) throws UnknownCharacterExc;

    /**
     * Returns a stack of characters containing the order in which the two character given are related.
     *
     * @param char1 name of one of the characters
     * @param char2 name of the other character
     * @param aux    auxiliary list used to pass values to main class
     * @return the stack of related characters or null
     * @throws NoShowSelectedExc    if there is no show selected.
     * @throws DuplicateCharRelated if the names passed are equal
     * @throws UnknownCharacterExc  if the one of the characters passed is not registered in the show
     * @throws NotRelatedExc        is there is no family relationship between the two characters
     */
    Stack<Character> howAreTheseTwoRelated(String char1, String char2, List<String> aux) throws NoShowSelectedExc, DuplicateCharRelated, UnknownCharacterExc, NotRelatedExc;

    /**
     * Returns an iterator to the names of the characters how have said the given quote.
     *
     * @param quote the quote text
     * @return iterator of character names
     * @throws NoShowSelectedExc if there is no show selected.
     * @throws UnknownQuoteExc   if the quote given does not exist in the show
     */
    Iterator<String> getFamousQuotes(String quote) throws NoShowSelectedExc, UnknownQuoteExc;

    /**
     * Returns an iterator with all the shows the actor, who plays the character passed, played/plays in.
     *
     * @param charName the character's name
     * @return iterator of show names
     * @throws NoShowSelectedExc   if there is no show selected.
     * @throws UnknownCharacterExc if the given character is not registered in the show
     * @throws NotRealCharacterExc if the given character is not real and so does not have an actor
     */
    Iterator<String> alsoAppearsOn(String charName) throws NoShowSelectedExc, UnknownCharacterExc, NotRealCharacterExc;

    /**
     * Returns and iterator for the actors who are more or as much romantic as the actor passed.
     *
     * @param actorName the actor's name
     * @return the iterator of actors
     * @throws UnknownActorExc     if the actor is not registered in the system
     * @throws LoveIsntInTheAirExc if there are no romantic relationships registered
     */
    Iterator<Actor> mostRomantic(String actorName) throws UnknownActorExc, LoveIsntInTheAirExc;

    /**
     * King of cgi: returns the company who made the most amount of money from their CGI characters.
     *
     * @return the company
     * @throws NoVirtualCharactersExc if there are no CGI characters in the system
     */
    CGICompany kingOfCgi()throws NoVirtualCharactersExc;

    /**
     * Given a season and an episode, returns the episode's name.
     *
     * @param season  the season
     * @param episode the episode
     * @return the episode name
     * @throws NonExistentSeasonExc  if the season does not exist in the current show
     * @throws NonExistentEpisodeExc if the episode does not exist in the given season
     */
    String getEpisodeName(int season, int episode) throws NonExistentSeasonExc, NonExistentEpisodeExc;

    /**
     * Given the actor's name, returns the actor.
     *
     * @param actorName the actor name
     * @return the actor
     */
    Actor getActor(String actorName);

    /**
     *Returns an iterator to all the events of the current show. given an interval of seasons, it also validates that it is valid
     *
     * @param seasonStart  the starting season
     * @param seasonEnd the ending season
     * @return the events iterator
     * @throws InvalidSeasonInterval if the seasons passed do not correspond to seasons in the current show
     * @throws NoShowSelectedExc     if there is no show selected.
     */
    Iterator<List<Event>> getEventsIt(int seasonStart, int seasonEnd) throws InvalidSeasonInterval, NoShowSelectedExc;
}
