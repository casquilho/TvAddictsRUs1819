/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package CGICompaniesPackage;

import ActorCharacterPackage.*;

import java.util.*;

public class CGICompanyClass implements CGICompany {


    private String name;
    private int totalProfit;
    private int numberChars;

    public CGICompanyClass(String name){
        this.name = name;
        this.totalProfit = 0;
        this.numberChars = 1;
    }



    public String getName(){
        return name;
    }

    public int getProfit() {
        return totalProfit;
    }

    public int getNumberChars() {
        return numberChars;
    }

    public void addCharacter(int value){
        this.addProfit(value);
        this.incNumberChars();
    }

    public void addProfit(int value){
        this.totalProfit += value;
    }

    private void incNumberChars(){
        this.numberChars++;
    }
}
