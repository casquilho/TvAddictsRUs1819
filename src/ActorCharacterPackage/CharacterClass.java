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


    CharacterClass(String charName, Show show, int cost) {
        this.charName = charName;
        this.show = show;
        this.cost = cost;
        this.events = new TreeMap<String, List<Event>>(Collator.getInstance());
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

}
