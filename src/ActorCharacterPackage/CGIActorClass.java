package ActorCharacterPackage;

public class CGIActorClass extends CharacterClass implements CGICharacter {

    private String companyCGI;

    public CGIActorClass(String charName, String company, int cost){
        super(charName, cost);
        this.companyCGI = company;
    }

    public String getCharName(){
        return super.charName;
    }

    public String getCompanyCGI() {
        return companyCGI;
    }

}
