package ShowPackage;

import MyExceptionsPackage.*;

public interface Show {

    String getName();

    int getSeasonsNumber();

    int getTotalEpisodesNumber();

    void addSeason();

    int addEpisode(int seasonsNumber, String epiName) throws UnknownSeasonExc;

    void addRealCharacter(String charName, String actorName, int cost) throws DuplicateCharacterExc;

    void addCGICharacter(String charName, String company, int cost) throws DuplicateCharacterExc;
}