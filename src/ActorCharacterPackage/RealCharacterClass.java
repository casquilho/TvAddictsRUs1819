package ActorCharacterPackage;

import ShowPackage.Show;

public class RealCharacterClass extends CharacterClass implements RealCharacter {

    private String actorName;




    public RealCharacterClass(String charName, String actorName, Show show, int cost){
        super(charName, show, cost);
        this.actorName = actorName;
    }



    public String getCharName(){
        return super.charName;
    }



}
