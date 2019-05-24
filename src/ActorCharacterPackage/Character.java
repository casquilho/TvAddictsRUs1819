package ActorCharacterPackage;

import EventPackage.Event;
import ShowPackage.Show;

public interface Character {
    static final String REAL="real";
    static final String VIRTUAL="virtual";

    String getCharName();

    Show getShow();

    int getCost();

    void addEvent(String key, Event newEvent);
}
