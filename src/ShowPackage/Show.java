package ShowPackage;

import CGICompaniesPackage.CGICompany;
import MyExceptionsPackage.*;

import java.util.List;

public interface Show {

    String getName();

    int getSeasonsNumber();

    int getTotalEpisodesNumber();

    void addSeason();

    int addEpisode(int seasonsNumber, String epiName) throws UnknownSeasonExc;

    void addRealCharacter(String charName, String actorName, int cost) throws DuplicateCharacterExc;

    void addCGICharacter(String charName, String company, int cost) throws DuplicateCharacterExc;

    void addQuote(int season, int episode, String charName, String quoteText, List<CGICompany> companies) throws NonExistentSeasonExc, NonExistentEpisodeExc, UnknownCharacterExc;
}