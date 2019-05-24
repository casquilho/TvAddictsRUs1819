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
}