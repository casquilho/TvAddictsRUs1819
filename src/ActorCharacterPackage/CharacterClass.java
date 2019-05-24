package ActorCharacterPackage;

import EventPackage.Event;
import ShowPackage.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class CharacterClass implements Character{



    protected String charName;
    protected Show show;
    protected int cost;
    protected Map<String ,List<Event>> events;


    CharacterClass(String charName, Show show, int cost) {
        this.charName = charName;
        this.show = show;
        this.cost = cost;
        this.events = new HashMap<String, List<Event>>();
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
        if(events.containsKey(key))
            events.get(key).add(value);
        else{
            List<Event> auxList = new LinkedList<Event>();
            auxList.add(value);
            events.put(key, auxList);
        }
    }

}
