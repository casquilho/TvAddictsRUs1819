package ActorCharacterPackage;

import java.text.Collator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class RealActorClass extends CharacterClass implements RealActor {

    private String actorName;




    public RealActorClass(String charName, String actorName, int cost){
        super(charName, cost);
        this.actorName = actorName;
    }



    public String getCharName(){
        return super.charName;
    }

    public String getActorName() {
        return actorName;
    }


}
