/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package ActorCharacterPackage;

import EventPackage.Event;
import ShowPackage.Show;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The Character interface.
 */
public interface Character {
    /**
     * The constant REAL - possible type of character to exist.
     */
    static final String REAL="real";
    /**
     * The constant VIRTUAL - possible type of character to exist.
     */
    static final String VIRTUAL="virtual";

    /**
     * Gets the character's name.
     *
     * @return the character's name
     */
    String getCharName();

    /**
     * Gets show in which the character is registered.
     *
     * @return the Show
     */
    Show getShow();

    /**
     * Gets cost per episode of this character.
     *
     * @return the cost per episode
     */
    int getCost();

    /**
     * Gets the number parents that this character has.
     *
     * @return the number of parents
     */
    int getNumParents();

    /**
     * Gets the number children that this character has.
     *
     * @return the number of children
     */
    int getNumChildren();

    /**
     * Gets parent - this is only used to reference the parent node when running
     * the familyBFS private method for howAreTheseTwoRelated command.
     *
     * @return the parent
     */
    Character getParent();

    /**
     * Gets the iterator for all the events in which this character participates.
     *
     * @return the character events
     */
    Iterator<List<Event>> getCharacterEvents();

    /**
     * Gets the character's children iterator.
     *
     * @return children iterator
     */
    Iterator<Character> getChildrenIt();

    /**
     * Gets the character's parents iterator.
     *
     * @return parents iterator
     */
    Iterator<Character> getParentsIt();

    /**
     * Gets the character's siblings iterator.
     *
     * @return siblings iterator
     */
    Iterator<Character> getSiblingsIt();

    /**
     * Gets the character's romantic partners iterator.
     *
     * @return partners iterator
     */
    Iterator<Character> getRomancesIt();

    /**
     * Gets the character's children list.
     *
     * @return children list
     */
    List<Character> getChildren();

    /**
     * Gets the character's siblings list.
     *
     * @return siblings list
     */
    List<Character> getSiblings();

    /**
     * Add a new event to the character's event map.
     *
     * @param key   the key - seasonNumber+episodeNumber String
     * @param value the event
     */
    void addEvent(String key, Event value);

    /**
     * Add a new romantic partner partner.
     *
     * @param partner the partner
     */
    void addPartner(Character partner);

    /**
     * Add a new child.
     *
     * @param child the child
     */
    void addChild(Character child);

    /**
     * Add a new parent.
     *
     * @param aux the aux
     */
    void addParents(Character aux);

    /**
     * Add a parent for referencing the parent node,
     * only used for the familyBFS private method
     * for howAreTheseTwoRelated command.
     *
     * @param auxP the aux p
     */
    void addParent(Character auxP);

    /**
     * Checks if the character has the child passed as argument.
     *
     * @param child the child
     * @return <tt>true</tt> if the character's children list contains the child
     */
    boolean hasChild(Character child);

    /**
     * Checks if the character has the parent passed as argument.
     *
     * @param auxP the parent
     * @return <tt>true</tt> if the character's parents list contains the parent
     */
    boolean hasParent(Character auxP);

    /**
     *  Checks if the character has the romantic partner passed as argument.
     *
     * @param auxP the partner
     * @return <tt>true</tt> if the character's partners list contains the partner
     */
    boolean hasPartner(Character auxP);
}
