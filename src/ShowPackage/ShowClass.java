package ShowPackage;

import MyExceptionsPackage.UnknownSeasonExc;
import SeasonPackage.Season;
import SeasonPackage.SeasonClass;

import java.util.ArrayList;
import java.util.List;

public class ShowClass implements Show {

    private String ShowName;
    private int seasonsNumber;
    private int totalEpisodesNumber;
    private List<Season>  seasons;

    public ShowClass(String ShowName){
        this.ShowName = ShowName;
        this.seasons = new ArrayList<Season>();
        this.seasonsNumber = 0;
        this.totalEpisodesNumber = 0;
    }

    public String getName() {
        return ShowName;
    }

    public int getSeasonsNumber() {
        return seasonsNumber;
    }

    public int getTotalEpisodesNumber() {
        return totalEpisodesNumber;
    }

    public void addSeason(){
        Season newSeason = new SeasonClass();
        seasons.add(newSeason);
        incrementSeasonsNumber();
    }

    public int addEpisode(int seasonNumber, String epiName) throws UnknownSeasonExc {

        try{
            Season currentSeason = seasons.get(seasonNumber-1);  //throws IndexOutOfBoundsException if the season doesn't exist
            incrementEpisodesNumber();
            return currentSeason.addEpisode(epiName);
        }
        catch(IndexOutOfBoundsException e){
            throw new UnknownSeasonExc();
        }
    }

    private void incrementEpisodesNumber(){
        this.totalEpisodesNumber++;
    }
    private void incrementSeasonsNumber(){
        this.seasonsNumber++;
    }
}
