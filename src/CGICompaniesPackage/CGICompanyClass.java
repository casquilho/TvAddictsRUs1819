package CGICompaniesPackage;

import ActorCharacterPackage.*;

import java.util.*;

public class CGICompanyClass implements CGICompany {


    private String name;
    private int totalProfit;
    private int numberChars;

    public CGICompanyClass(String name, int cost){
        this.name = name;
        this.totalProfit = cost;
        this.numberChars = 1;
    }



    public String getName(){
        return name;
    }

    public void addCharacter(int value){
        this.addProfit(value);
        this.incNumberChars();
    }

    public int getProfit() {
        return totalProfit;
    }


    public int getNumberChars() {
        return numberChars;
    }

    private void incNumberChars(){
        this.numberChars++;
    }

    private void addProfit(int value){
        this.totalProfit += value;
    }
}
