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
    private Character parent;


    CharacterClass(String charName, Show show, int cost) {
        this.charName = charName;
        this.show = show;
        this.cost = cost;
        this.events = new TreeMap<String, List<Event>>(Collator.getInstance());

        this.partners = new LinkedList<>();
        this.children = new LinkedList<>();
        this.parents = new LinkedList<>();
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

    public Iterator<List<Event>> getCharacterEvents(){
        return events.values().iterator();
    }


    public void addPartner(Character partner){
        this.partners.add(partner);

        if(!partner.hasPartner(this))
            partner.addPartner(this);

        for(Character auxChild: children)
            if(!partner.hasChild(auxChild))
                partner.addChild(auxChild);

        for(Character aux2Child : partner.getChildren())
            if(!children.contains(aux2Child))
                children.add(aux2Child);


    }

    public boolean addChild(Character child){
        if(children.contains(child))
            return false;

        children.add(child);
        child.addParents(this);

        for(Character auxP : partners) {
            if (!auxP.hasChild(child)) {
                auxP.addChild(child);
                child.addParents(auxP);
            }
        }
        return true;
    }

    public void addParents(Character aux){
        if(!parents.contains(aux))
            parents.add(aux);
    }

    public int getNumParents(){
        return parents.size();
    }

    public int getNumChildren(){
        return children.size();
    }

    public void addParent(Character auxP){
        this.parent = auxP;
    }

    public Character getParent() {
        return parent;
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

    public List<Character> getChildren(){
        return  children;
    }
}
