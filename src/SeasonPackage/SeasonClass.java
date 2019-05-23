package SeasonPackage;

import EpisodePackage.*;
import java.util.*;


public class SeasonClass implements Season {

    private List<Episode> episodes;
    private int episodesSeasonNumber;

    public SeasonClass(){
        this.episodes = new ArrayList<Episode>();
        this.episodesSeasonNumber = 0;
    }

    public int addEpisode(String epiName){
        episodes.add(new EpisodeClass(epiName));
        incrementEpisodesNumber();
        return this.episodesSeasonNumber;
    }

    public int numberOfEpisodes(){
        return episodesSeasonNumber;
    }

    private void incrementEpisodesNumber(){
        this.episodesSeasonNumber++;
    }
}
