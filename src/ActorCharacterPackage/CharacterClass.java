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
    private Character parent;       //used only for the BFS algorithm


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
        //if(events.size() == 0)
        // return null;
        return events.values().iterator();

    }

    public Iterator<Character> getChildrenIt(){
        //if(children.size() == 0)
        //return null;
        return  children.iterator();
    }

    public Iterator<Character> getParentsIt(){
        //if(parents.size() == 0)
        //return null;
        return parents.iterator();
    }

    public Iterator<Character> getSiblingsIt(Character auxChar){
        if(parents.size() == 0)
            return parents.iterator();

        int counter = 0;
        Character storeChar = null;

        //get's the parent with the most children
        for(Character auxParent : parents)
            if(auxParent.getNumChildren() >= counter) {
                storeChar = auxParent;
                counter = auxParent.getNumChildren();
            }
        //removes the current character from the copy of the children's list
        List<Character> auxClone = new LinkedList<Character>(storeChar.getChildren());
        auxClone.remove(auxChar);
        return auxClone.iterator();
    }

    public Iterator<Character> getRomancesIt(){
        //if(partners.size() == 0)
        //    return null;
        return partners.iterator();
    }

    public List<Character> getChildren(){
        return children;
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
    }

    /*public void addPartner(Character partner){
        this.partners.add(partner);

        if(!partner.hasPartner(this))
            partner.addPartner(this);

        for(Character auxChild: children) {
            if (!partner.hasChild(auxChild))
                partner.addChild(auxChild);
            if (!auxChild.hasParent(partner))
                auxChild.addParents(partner);
        }

        for(Character aux2Child : partner.getChildren()) {
            if (!children.contains(aux2Child))
                children.add(aux2Child);
            if (!aux2Child.hasParent(this))
                aux2Child.addParents(this);
        }
    }

    public void addChild(Character child){
        if(children.contains(child))
            return ;

        children.add(child);
        child.addParents(this);

        for(Character auxP : partners) {
            if (!auxP.hasChild(child))
                auxP.addChild(child);
            if(!child.hasParent(auxP))
                child.addParents(auxP);
        }
    }

    public void addParents(Character aux){
        if(!parents.contains(aux))
            parents.add(aux);
        if(!aux.getChildren().contains(this))
            aux.addChild(this);
    }*/

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
