/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package ActorCharacterPackage;

import EventPackage.Event;
import ShowPackage.*;

import java.text.Collator;
import java.util.*;

public abstract class CharacterClass implements Character{



    protected String charName;
    protected Show show;
    protected int cost;
    protected Map<String ,List<Event>> events;

    //Used for relationship operations
    private List<Character> partners;
    private List<Character> children;
    private List<Character> parents;
    private List<Character> siblings;
    //used only for the BFS algorithm
    private Character parent;


    CharacterClass(String charName, Show show, int cost) {
        this.charName = charName;
        this.show = show;
        this.cost = cost;
        this.events = new TreeMap<String, List<Event>>(Collator.getInstance());

        this.partners = new LinkedList<>();
        this.children = new LinkedList<>();
        this.parents  = new LinkedList<>();
        this.siblings = new LinkedList<>();
        this.parent = null;
    }

    public String getCharName() {
        return charName;
    }

    public Show getShow() {
        return show;
    }

    public int getCost() {
        return cost;
    }

    public int getNumParents(){
        return parents.size();
    }

    public int getNumChildren(){
        return children.size();
    }

    public Character getParent(){
        return parent;
    }

    public Iterator<List<Event>> getCharacterEvents(){
        return events.values().iterator();
    }

    public Iterator<Character> getChildrenIt(){
        return  children.iterator();
    }

    public Iterator<Character> getParentsIt(){
        return parents.iterator();
    }

    public Iterator<Character> getSiblingsIt(Character auxChar){
        return  siblings.iterator();
    }

    public Iterator<Character> getRomancesIt(){
        return partners.iterator();
    }

    public List<Character> getChildren(){
        return children;
    }

    public List<Character> getSiblings(){
        return siblings;
    }

    public void addEvent(String key, Event value){
        if(events.containsKey(key)) {
            if (!events.get(key).contains(value))
                events.get(key).add(value);
        }
        else{
            List<Event> auxList = new LinkedList<Event>();
            auxList.add(value);
            events.put(key, auxList);
        }
    }

    public void addPartner(Character partner){
        if(!partners.contains(partner) && partner != this)
            partners.add(partner);
    }

    public void addChild(Character child){
        if(!children.contains(child) && child != this)
            children.add(child);
    }

    public void addParents(Character parent){
        if(!parents.contains(parent) && parent != this)
            parents.add(parent);

        for(Character child : parent.getChildren()) {
            if (!siblings.contains(child) && !child.equals(this))
                siblings.add(child);
            if(!child.getSiblings().contains(this) && !child.equals(this))
                child.getSiblings().add(this);
        }
    }

    public void addParent(Character auxP){
        this.parent = auxP;
    }

    public boolean hasChild(Character child){
        return children.contains(child);
    }

    public boolean hasParent(Character auxP){
        return parents.contains(auxP);
    }

    public boolean hasPartner(Character auxP){
        return partners.contains(auxP);
    }
}
