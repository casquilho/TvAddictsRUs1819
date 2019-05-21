package ActorCharacterPackage;

import java.text.Collator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class RealActorClass extends CharacterClass implements RealActor {

    private String actorName;
    private int costPerEpisode;
    private Set<String> starsOn;

    public RealActorClass(String charName, String actorName, int cost){
        super(charName);
        this.actorName = actorName;
        this.costPerEpisode = cost;
        this.starsOn = new TreeSet<>(Collator.getInstance());
    }

    public void addShowAppearence(String showName){
        this.starsOn.add(showName);
    }

    public String getCharName(){
        return super.charName;
    }

    public String getActorName() {
        return actorName;
    }

    public int getCostPerEpisode() {
        return costPerEpisode;
    }


}
