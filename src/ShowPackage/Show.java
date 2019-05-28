package ShowPackage;

import ActorCharacterPackage.Actor;
import CGICompaniesPackage.CGICompany;
import MyExceptionsPackage.*;

import java.util.Iterator;
import java.util.List;

public interface Show {

    String getName();

    int getSeasonsNumber();

    int getTotalEpisodesNumber();

    void addSeason();

    int addEpisode(int seasonsNumber, String epiName) throws UnknownSeasonExc;

    void addRealCharacter(String charName, Actor actor, int cost) throws DuplicateCharacterExc;

    void addCGICharacter(String charName, String company, int cost) throws DuplicateCharacterExc;

    void addQuote(int season, int episode, String charName, String quoteText, List<CGICompany> companies) throws NonExistentSeasonExc, NonExistentEpisodeExc, UnknownCharacterExc;

    Iterator getFamousQuotes(String quoteText) throws UnknownQuoteExc;

    String getActorNameFromCharName(String charName) throws UnknownCharacterExc;

    void addEvent(int episode, int season, List<String> characters, String event, List<CGICompany> companiesCGI) throws NonExistentSeasonExc, NonExistentEpisodeExc, UnknownCharacterExc;

    Iterator getCharacterResume(String charName) throws UnknownCharacterExc;

    Iterator getEvents();

    void addRelationship(String parent, String child, List<String> aux) throws UnknownCharacterExc;

    void addRomance(String char1, String char2, List<String> aux) throws UnknownCharacterExc;

    int getNumParentsFromName(String charName);

    int getNumChildrenFromName(String charName);

}