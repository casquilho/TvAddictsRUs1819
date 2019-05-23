package ActorCharacterPackage;

import java.text.Collator;
import java.util.*;

public class ActorClass implements Actor{

    private String name;
    private Set<String> starsOn;

    public ActorClass(String name) {
        this.name = name;
        this.starsOn = new TreeSet<String>(Collator.getInstance());
    }

    public String getName() {
        return name;
    }

    public Iterator<String> StarsOn() {
        return starsOn.iterator();
    }

    public void addShowName(String showName){
        this.starsOn.add(showName);
    }


}