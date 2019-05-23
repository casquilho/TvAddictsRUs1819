package ActorCharacterPackage;

import ShowPackage.*;

public abstract class CharacterClass implements Character{



    protected String charName;
    protected Show show;
    protected int cost;


    CharacterClass(String charName, Show show, int cost) {
        this.charName = charName;
        this.show = show;
        this.cost = cost;
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
}
