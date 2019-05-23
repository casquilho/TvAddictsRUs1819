package ActorCharacterPackage;

public class RealCharacterClass extends CharacterClass implements RealCharacter {

    private String actorName;




    public RealCharacterClass(String charName, String actorName, int cost){
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
