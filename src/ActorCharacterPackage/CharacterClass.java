package ActorCharacterPackage;

public abstract class CharacterClass implements Character{

    protected String charName;
    protected int cost;

    public CharacterClass(String charName, int cost){
        this.charName = charName;
        this.cost = cost;
    }

}
