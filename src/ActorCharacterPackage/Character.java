package ActorCharacterPackage;

import EventPackage.Event;
import ShowPackage.Show;

import java.util.Iterator;
import java.util.List;

public interface Character {
    static final String REAL="real";
    static final String VIRTUAL="virtual";

    String getCharName();

    Show getShow();

    int getCost();

    void addEvent(String key, Event newEvent);

    Iterator<List<Event>> getCharacterEvents();
}
