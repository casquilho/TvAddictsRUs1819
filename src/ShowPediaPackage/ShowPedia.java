package ShowPediaPackage;

import ActorCharacterPackage.Actor;
import CGICompaniesPackage.CGICompany;
import MyExceptionsPackage.*;
import ShowPackage.*;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public interface ShowPedia {

    Show getCurrentShow() throws NoShowSelectedExc;

    String printCurrentShow() throws NoShowSelectedExc;

    void addShow(String showName) throws ExistentShowExc;

    Show switchShow(String showName)throws UnknownShowExc;

    void addSeasonToCurrentShow() throws NoShowSelectedExc;

    int addEpisodeToGivenSeason(int seasonNumber, String epiName) throws NoShowSelectedExc, UnknownSeasonExc;

    void addRealCharacter(String charName, String actorName, int cost) throws NoShowSelectedExc, DuplicateCharacterExc, InvalidSalaryExc;

    void addCGICharacter(String charName, String company, int cost) throws NoShowSelectedExc, DuplicateCharacterExc, InvalidSalaryExc;

    void addQuote(int season, int episode, String charName, String quoteText) throws NoShowSelectedExc, NonExistentEpisodeExc, NonExistentSeasonExc, UnknownCharacterExc;

    Iterator getFamousQuotes(String quote) throws NoShowSelectedExc, UnknownQuoteExc;

    CGICompany kingOfCgi()throws NoVirtualCharactersExc;

    Iterator alsoAppearsOn(String charName) throws NoShowSelectedExc, UnknownCharacterExc;

    void addEvent(int episode, int season, List<String> characters, String event) throws NoShowSelectedExc, NonExistentSeasonExc, NonExistentEpisodeExc, UnknownCharacterExc;

    void characterResume(String charName, List<Iterator> auxList) throws UnknownCharacterExc;

    Actor getActor(String actorName);

    void addRelationship(String parent, String child, List<String> aux) throws NoShowSelectedExc, SameCharacterExc, UnknownCharacterExc, DuplicateRelationshipExc;

    void addRomance(String char1, String char2, List<String> aux) throws NoShowSelectedExc, SameCharacterExc, UnknownCharacterExc, DuplicateRelationshipExc;

    Stack<String> howAreTheseTwoRelated(String char1, String char2, List<String> aux) throws NoShowSelectedExc, SameCharacterExc, UnknownCharacterExc;

}
