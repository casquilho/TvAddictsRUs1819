/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package ActorCharacterPackage;

import ShowPackage.Show;

public class VirtualCharacterClass extends CharacterClass implements VirtualCharacter {

    private String companyCGI;

    public VirtualCharacterClass(String charName, String companyName, Show show, int cost){
        super(charName,show, cost);
        this.companyCGI = companyName;
    }

    public String getCharName(){
        return super.charName;
    }

    public String getCompanyCGI() {
        return companyCGI;
    }

}
