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
}
