/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package EventPackage;

import ActorCharacterPackage.Character;
import java.util.List;

public class EventClass implements Event {

    private String event;
    private int season;
    private int episode;
    private List<Character> participants;

    public EventClass(int episode, int season, String event, List<Character> characters){
        this.episode = episode;
        this.season = season;
        this.event = event;
        this.participants = characters;
    }

    public String getEvent() {
        return event;
    }

    public int getSeason() {
        return season;
    }

    public int getEpisode() {
        return episode;
    }
}
