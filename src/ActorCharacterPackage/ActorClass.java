package ActorCharacterPackage;

import ShowPackage.*;

import java.util.*;

public class ActorClass {

    private String name;
    private Map<String, String> starsOn;

    public ActorClass(String name, Map<String, String> starsOn) {
        this.name = name;
        this.starsOn = starsOn;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getStarsOn() {
        return starsOn;
    }
}
