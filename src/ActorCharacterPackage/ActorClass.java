package ActorCharacterPackage;

import java.text.Collator;
import java.util.*;

public class ActorClass {

    private String name;
    private Set<String> starsOn;

    public ActorClass(String name, Map<String, String> starsOn) {
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
