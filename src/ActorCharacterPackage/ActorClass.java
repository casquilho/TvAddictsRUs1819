/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package ActorCharacterPackage;

import java.text.Collator;
import java.util.*;
import java.util.Map.Entry;

public class ActorClass implements Actor{

    private String name;
    private int numberOfRoles;
    private Set<String> starsOn;
    private Map<String, Integer> numOfRomRelByShow;

    public ActorClass(String name) {
        this.name = name;
        this.starsOn = new TreeSet<String>(Collator.getInstance());
        this.numOfRomRelByShow = new TreeMap<String, Integer>();
        this.numberOfRoles = 0;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfRomanticShows() {
        return this.numOfRomRelByShow.size();
    }

    public int getTotalRomanticNum() {
        Iterator<Entry<String, Integer>> it= this.getNumOfRomRelByShowIt();
        int counter = 0;
        while(it.hasNext()) {
            counter+=it.next().getValue();
        }
        return counter;
    }

    public int getNumberOfroles(){
        return numberOfRoles;
    }

    public Iterator<Entry<String, Integer>> getNumOfRomRelByShowIt() {
        Set<Entry<String, Integer>> set = this.numOfRomRelByShow.entrySet();
        return set.iterator();
    }

    public Iterator<String> StarsOn() {
        return starsOn.iterator();
    }

    public void setNumOfRomRelByShow(String showName) {
        if(this.numOfRomRelByShow.containsKey(showName))
            this.numOfRomRelByShow.put(showName,numOfRomRelByShow.get(showName)+1 );
        else
            this.numOfRomRelByShow.put(showName, 1 );
    }

    public void addShowName(String showName){
        this.starsOn.add(showName);
        incNumberOfRoles();
    }

    private void incNumberOfRoles(){
        this.numberOfRoles++;
    }






}
