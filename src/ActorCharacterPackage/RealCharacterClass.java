package ActorCharacterPackage;

import ShowPackage.Show;

public class RealCharacterClass extends CharacterClass implements RealCharacter {

    private Actor actor;




    public RealCharacterClass(String charName, Actor actor, Show show, int cost){
        super(charName, show, cost);
        this.actor = actor;
    }



    public String getCharName(){
        return super.charName;
    }

    public String getActorName(){return actor.getName();}

    public Actor  getActor(){return actor;}



}
