package ActorCharacterPackage;

import EventPackage.Event;
import ShowPackage.Show;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public interface Character {
    static final String REAL="real";
    static final String VIRTUAL="virtual";

    String getCharName();

    Show getShow();

    int getCost();

    int getNumParents();

    int getNumChildren();

    Character getParent();

    Iterator<List<Event>> getCharacterEvents();

    Iterator<Character> getChildrenIt();

    Iterator<Character> getParentsIt();

    Iterator<Character> getSiblingsIt(Character auxChar);

    Iterator<Character> getRomancesIt();

    List<Character> getChildren();

    void addEvent(String key, Event value);

    void addPartner(Character partner);

    void addChild(Character child);

    void addParents(Character aux);

    void addParent(Character auxP);

    boolean hasChild(Character child);

    boolean hasParent(Character auxP);

    boolean hasPartner(Character auxP);
}
