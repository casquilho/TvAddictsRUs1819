package ActorCharacterPackage;

public class CGIActorClass extends CharacterClass implements CGIActor {

    private String companyCGI;
    private int costPerSeason;

    public CGIActorClass(String charName, String company, int cost){
        super(charName);
        this.companyCGI = company;
        this.costPerSeason = cost;
    }

    public String getCharName(){
        return super.charName;
    }

    public String getCompanyCGI() {
        return companyCGI;
    }

    public int getCostPerSeason() {
        return costPerSeason;
    }
}
