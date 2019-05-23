package ActorCharacterPackage;

public class VirtualActorClass extends CharacterClass implements VirtualCharacter {

    private String companyCGI;

    public VirtualActorClass(String charName, String company, int cost){
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
