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

    void addPartner(Character partner);

    boolean addChild(Character child);

    void addParents(Character aux);

    void addParent(Character auxP);

    Character getParent();

    boolean hasChild(Character child);

    boolean hasParent(Character auxP);

    boolean hasPartner(Character auxP);

    List<Character> getChildren();

    int getNumParents();

    int getNumChildren();
}
